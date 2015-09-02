/*
 * CreateDocOnComponentsImpl.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.document.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.Logger;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.CreateDocOnComponents;
import com.mg.merp.document.CreateDocumentBasisOfCallback;
import com.mg.merp.document.GoodsDocument;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.reference.model.SetOfGood;

/**
 * Реализация сервиса создания документа на комплектующие
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: CreateDocOnComponentsImpl.java,v 1.2 2008/02/14 13:02:52 alikaev Exp $
 */
public class CreateDocOnComponentsImpl<S extends DocHead, D extends DocHead, P extends DocHeadModel> extends CreateDocumentBasisOfImpl<S, D, P> implements CreateDocOnComponents<S, D, P> {

	private Logger logger = ServerUtils.getLogger(CreateDocumentBasisOfImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.support.CreateDocumentBasisOfImpl#doCopyDocSpecs(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, java.util.Date, java.util.List, com.mg.merp.document.CreateDocumentBasisOfCallback)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doCopyDocSpecs(S srcDoc, D dstDoc, Date date, List<DocumentSpecItem> specList,
			CreateDocumentBasisOfCallback createCallback) {
		if (specList != null && specList.size() > 0){
			if (dstDoc.getDocSection().isWithSpec()) {
				//оба документа со спецификациями
				GoodsDocumentSpecification dstDocSpecServ = DocumentUtils.getGoodsDocumentSpecificationService(dstDoc.getDocSection());
				GoodsDocumentSpecification srcDocSpecServ = DocumentUtils.getGoodsDocumentSpecificationService(srcDoc.getDocSection());
				List<DocSpec> newDocSpecs = new LinkedList<DocSpec>();
				GoodsDocument dstDocServ = DocumentUtils.getGoodsDocumentService(dstDoc.getDocSection());
				for(DocumentSpecItem dsi : specList){
					DocSpec srcDocSpec = dsi.getDocSpec();
					if (CatalogType.SET_OF_GOODS.compareTo(srcDocSpec.getCatalog().getGoodType()) == 0) {
						Set<SetOfGood> components =  srcDocSpec.getCatalog().getSetOfSetOfGood();
						for (SetOfGood component : components){
							DocSpec dstDocSpec = (DocSpec) dstDocSpecServ.initialize();
							for(String key : srcDocSpec.getAllAttributes().keySet()){
								if (isMutableDocSpecAttribute(key)){
									Object o = srcDocSpec.getAttribute(key); 
									if (o != null && dstDocSpec.hasAttribute(key)) {
										if (logger.isDebugEnabled())
											logger.debug(String.format("set document line attribute %s to %s", key, o.toString()));
										dstDocSpec.setAttribute(key, o);
									}
								}
							}
							dstDocSpec.setDocHead(dstDoc);

							CurrencyServiceLocal curServ = (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
							Configuration config = dstDocServ.getConfiguration();
							BigDecimal price1 = BigDecimal.ZERO;
							BigDecimal price2 = BigDecimal.ZERO;
							if (component.getPriceRelate() != null) {
								BigDecimal quantrelate = component.getQuantity();
								BigDecimal pricerelate = quantrelate.multiply(component.getPriceRelate().divide(new BigDecimal(100)));
								BigDecimal price = srcDocSpec.getPrice();
								if (price != null)
									price1 = MathUtils.round(curServ.conversion(dstDoc.getCurrency(), srcDoc.getCurrency(), 
											dstDoc.getCurrencyRateAuthority(), dstDoc.getCurrencyRateType(), date, price.multiply(pricerelate).divide(quantrelate)),
											new RoundContext(config.getCurrencyScale()));
								price = srcDocSpec.getPrice1();
								if (price != null)
									price2 = MathUtils.round(curServ.conversion(dstDoc.getCurrency(), srcDoc.getCurrency(), 
											dstDoc.getCurrencyRateAuthority(), dstDoc.getCurrencyRateType(), date, price.multiply(pricerelate).divide(quantrelate)),
											new RoundContext(config.getCurrencyScale()));
							}
							dstDocSpec.setPrice(price1);
							dstDocSpec.setPrice1(price2);
							dstDocSpec.setQuantity(dsi.getPerformedQuantity1());
							dstDocSpec.setQuantity2(dsi.getPerformedQuantity2());

							//clone custom fields
							CustomFieldsManagerLocator.locate().cloneValues(srcDocSpecServ, srcDocSpec, dstDocSpecServ, dstDocSpec);

							ajustDocSpec(srcDocSpec, dstDocSpec, component);
							//выполним дополнительную обработку, если существует
							if (createCallback != null) {
								logger.debug("execute document line callback");
								createCallback.processDocumentSpec(dstDoc, srcDoc, dstDocSpec, srcDocSpec);
							}

							dstDocSpec.setBulkOperation(true); //чтобы не изменял заголовок, после добавления всех применим массовую операцию на заголовке
							dstDocSpecServ.create(dstDocSpec);

							newDocSpecs.add(dstDocSpec);
						}
					}
				}
				DocSpec [] newDSpecs = new DocSpec[newDocSpecs.size()];
				dstDocServ.createSpecifaction(newDocSpecs.toArray(newDSpecs));
			} 
		}
	}

	/**
	 * Изменяем спецификацию 
	 * 
	 * @param docSpec		
	 * 		спецификация документа источника
	 * @param dstDocSpec
	 * 		спецификация документа приемника
	 * @param component
	 * 		комплектующая
	 */
	private void ajustDocSpec(DocSpec docSpec, DocSpec dstDocSpec, SetOfGood component) {
		Catalog catalogComponent = component.getCatalogComponent();
		dstDocSpec.setCatalog(catalogComponent);
		dstDocSpec.setMeasure1(catalogComponent.getMeasure1());
		dstDocSpec.setMeasure2(catalogComponent.getMeasure2());
		BigDecimal quantDstSpec = component.getQuantity().multiply(docSpec.getQuantity());
		dstDocSpec.setQuantity(quantDstSpec);	
		dstDocSpec.setSumma(dstDocSpec.getPrice().multiply(quantDstSpec));
		dstDocSpec.setSumma1(dstDocSpec.getPrice1().multiply(quantDstSpec));
		if (catalogComponent.getWeight() != null)
			dstDocSpec.setWeight(catalogComponent.getWeight().multiply(quantDstSpec));
		else
			dstDocSpec.setWeight(null);
		if (catalogComponent.getVolume() != null)
			dstDocSpec.setVolume(catalogComponent.getVolume().multiply(quantDstSpec));
		else
			dstDocSpec.setVolume(null);
	}

}
