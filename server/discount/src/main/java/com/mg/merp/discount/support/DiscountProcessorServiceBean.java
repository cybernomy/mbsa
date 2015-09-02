/*
 * DiscountProcessorServiceBean.java.java
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
package com.mg.merp.discount.support;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BusinessAddin;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.core.model.Folder;
import com.mg.merp.discount.ApplyDiscountListener;
import com.mg.merp.discount.CalculateDiscountListener;
import com.mg.merp.discount.CalculatePromotionDiscountListener;
import com.mg.merp.discount.DiscountProcessorServiceLocal;
import com.mg.merp.discount.PromotionServiceLocal;
import com.mg.merp.discount.model.Discount;
import com.mg.merp.discount.model.PromotionLine;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.support.DocumentUtils;

/**
 * ���������� ������-���������� "��������� ������� ������/�������"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: DiscountProcessorServiceBean.java,v 1.7 2009/01/22 14:53:06 sharapov Exp $
 */
@Stateful(name="merp/discount/DiscountProcessorService") //$NON-NLS-1$
public class DiscountProcessorServiceBean extends AbstractPOJOBusinessObjectStatefulServiceBean implements DiscountProcessorServiceLocal {

	protected PromotionServiceLocal promotionService = (PromotionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PromotionServiceLocal.SERVICE_NAME);
	protected Map<String, Object> processBAiContext = new HashMap<String, Object>();
	protected OrmTemplate ormTemplate = OrmTemplate.getInstance();
	protected ApplyDiscountListener �pplyDiscountListener;


	private class RecursionBusinessAddinListenerImpl implements BusinessAddinListener<DiscountResult> {
		private BusinessAddinListener<DiscountResult> listener;
		private int index;
		private List<Discount> discounts;
		private Map<String, Object> params;
		private DiscountResult discountResult;

		private RecursionBusinessAddinListenerImpl(BusinessAddinListener<DiscountResult> listener, int index, List<Discount> discounts, Map<String, Object> params, DiscountResult discountResult) {
			this.listener = listener;
			this.index = index;
			this.discounts = discounts;
			this.params = params;
			this.discountResult = discountResult;
		}

		public void aborted(BusinessAddinEvent<DiscountResult> event) {
			listener.aborted(event);
		}

		public void completed(BusinessAddinEvent<DiscountResult> event) {
			try {
				//��������� ����� ������� ��������� � ����� ������/�������, ���������� ��� ���� ��������� �������
				BigDecimal discountValue = event.getResult().getDiscount();
				if(discountValue != null)
					discountResult.setDiscount(discountResult.getDiscount() == null ? discountValue : discountResult.getDiscount().add(discountValue));
				discountResult.setPriceWithDiscount(event.getResult().getPriceWithDiscount());
				discountResult.setIsApplyDiscountOnDoc(event.getResult().getIsApplyDiscountOnDoc());
				discountResult.setIsApplied(event.getResult().getIsApplied());

				if (getLogger().isDebugEnabled())
					getLogger().debug(String.format("RecursionDiscountBAiListenerImpl �ompleted: [Discount = %1$s]", discountResult.getDiscount())); //$NON-NLS-1$

				performCalculateDiscount(discounts, index + 1, params, discountResult, listener, event.getAddin());
			} catch (RuntimeException e) {
				listener.aborted(event);
				throw e;
			}
		}
	}

	private class RecursionPromotionBAiListenerImpl implements BusinessAddinListener<PromotionDiscountResult> {
		private BusinessAddinListener<PromotionDiscountResult> listener;
		private int index;
		private List<PromotionLine> promotionLines;
		private Map<String, Object> params;
		private PromotionDiscountResult promotionDiscountResult;

		private RecursionPromotionBAiListenerImpl(BusinessAddinListener<PromotionDiscountResult> listener, int index, List<PromotionLine> promotionLines, Map<String, Object> params, PromotionDiscountResult promotionDiscountResult) {
			this.listener = listener;
			this.index = index;
			this.promotionLines = promotionLines;
			this.params = params;
			this.promotionDiscountResult = promotionDiscountResult;
		}

		public void aborted(BusinessAddinEvent<PromotionDiscountResult> event) {
			listener.aborted(event);
		}

		public void completed(BusinessAddinEvent<PromotionDiscountResult> event) {
			try {
				// ��������� ����� ������� ��������� � ����� ������� ���������� �����������, ���������� ��� ���� ���������� �������
				BigDecimal discountValue = event.getResult().getDiscount();
				if(discountValue != null)
					promotionDiscountResult.setDiscount(promotionDiscountResult.getDiscount() == null ? discountValue : promotionDiscountResult.getDiscount().add(discountValue));
				promotionDiscountResult.setPriceWithDiscount(event.getResult().getPriceWithDiscount());
				promotionDiscountResult.setAlonePromotion(event.getResult().isAlonePromotion());
				promotionDiscountResult.setIsApplied(event.getResult().getIsApplied());

				if (getLogger().isDebugEnabled())
					getLogger().debug(String.format("RecursionPromotionBAiListenerImpl �ompleted: [Discount = %1$s]", promotionDiscountResult.getDiscount())); //$NON-NLS-1$

				performCalculatePromotionDiscount(promotionLines, index + 1, params, promotionDiscountResult, listener, event.getAddin());
			} catch (RuntimeException e) {
				listener.aborted(event);
				throw e;
			}
		}
	}

	private void performCalculateDiscount(final List<Discount> discounts, final int index, final Map<String, Object> params, final DiscountResult discountResult, final BusinessAddinListener<DiscountResult> listener, BusinessAddin<DiscountResult> businessAddin) {
		if (getLogger().isDebugEnabled())
			getLogger().debug(String.format("PerformCalculateDiscount() [DiscountIndex = %1$s]", index)); //$NON-NLS-1$

		//��� ���������� ���������� �������� �������� ������� � ���������� �������
		if (index >= discounts.size()) {
			listener.completed(new BusinessAddinEvent<DiscountResult>(businessAddin, discountResult));
			return;
		}

		Discount discount = discounts.get(index);
		params.put(DiscountBusinessAddin.DISCOUNT_PARAM_NAME, discount);
			BusinessAddinEngineLocator.locate().perform(discount.getAlg(), params,
					new RecursionBusinessAddinListenerImpl(listener, index, discounts, params, discountResult));
	}

	private void performCalculatePromotionDiscount(final List<PromotionLine> promotionLines, final int index, final Map<String, Object> params, final PromotionDiscountResult promotionDiscountResult, final BusinessAddinListener<PromotionDiscountResult> listener, BusinessAddin<PromotionDiscountResult> businessAddin) {
		if (getLogger().isDebugEnabled())
			getLogger().debug(String.format("PerformCalculateDiscount() [PromotionLineIndex = %1$s]", index)); //$NON-NLS-1$
		// ��� ���������� ���������� ���������� ����������� ��� 
		// �������������� �������� "������ ������/������� �������������� � ������ ������ ���������� �����������" 
		// �������� ������� � ���������� �������
		if (index >= promotionLines.size() || promotionDiscountResult.isAlonePromotion()) {
			listener.completed(new BusinessAddinEvent<PromotionDiscountResult>(businessAddin, promotionDiscountResult));
			return;
		}

		PromotionLine promotionLine = promotionLines.get(index);
		// ����� BAi ��: BAi "������� ���������� �����������" ����� ��������� ���� ��� BAi "���������� �����������"
		Repository promoBAi;
		if(promotionLine.getPromotionType() != null)
			promoBAi = promotionLine.getPromotionType().getBai();
		else
			promoBAi = promotionLine.getPromotion().getPromotionType().getBai();

		params.put(PromotionDiscountBusinessAddin.PROMOTION_LINE_PARAM, promotionLine);
		BusinessAddinEngineLocator.locate().perform(promoBAi, params,
					new RecursionPromotionBAiListenerImpl(listener, index, promotionLines, params, promotionDiscountResult));
	}

	private class CalculateDiscountListenerImpl implements CalculateDiscountListener {
		private DocHead docHead;
		private List<DocSpec> specs;
		private int index;
		private GoodsDocumentSpecification<DocSpec, Integer> specService;

		private CalculateDiscountListenerImpl(GoodsDocumentSpecification<DocSpec, Integer> specService, DocHead docHead, List<DocSpec> specs, int index) {
			this.docHead = docHead;
			this.specs = specs;
			this.index = index;
			this.specService = specService;
		}

		public void aborted() {
			UIUtils.endConversation();
			abortApplyDiscount();
		}

		public void completed(DiscountResult result) {
			try {
				DocSpec spec = specs.get(index);

				if (getLogger().isDebugEnabled())
					getLogger().debug(String.format("Calculation discount by group completed [specID = %1$s] CalculationResult([IsApplied = %2$s] [Discount = %3$s] [PriceWithDiscount = %4$s])", //$NON-NLS-1$
							spec.getId(), result.getIsApplied(), result.getDiscount(), result.getPriceWithDiscount()));

				updateSpecByDiscountResult(spec, docHead, specService, result, null);

				performApplyDiscount(specService, docHead, specs, index + 1);
			} catch (RuntimeException e) {
				aborted();
				throw e;
			}
		}
	}

	private class PromotionDiscountListenerImpl implements CalculatePromotionDiscountListener {
		private DocHead docHead;
		private List<DocSpec> specs;
		private int index;
		private GoodsDocumentSpecification<DocSpec, Integer> specService;
		private PromotionLine promotionLine;
		private BigDecimal promotionDiscount;

		private PromotionDiscountListenerImpl(GoodsDocumentSpecification<DocSpec, Integer> specService, DocHead docHead, List<DocSpec> specs, int index, PromotionLine promotionLine) {
			this.docHead = docHead;
			this.specs = specs;
			this.index = index;
			this.specService = specService;
			this.promotionLine = promotionLine;
		}

		public void aborted() {
			UIUtils.endConversation();
			abortApplyDiscount();
		}

		public void completed(PromotionDiscountResult result) {
			try {
				final DocSpec spec = specs.get(index);
				if (getLogger().isDebugEnabled())
					getLogger().debug(String.format("Calculation discount by promotion completed [specID = %1$s] [promotionLineID = %2$s] CalculationResult([IsApplied = %3$s] [Discount = %4$s] [PriceWithDiscount = %5$s])", //$NON-NLS-1$
							spec.getId(), promotionLine.getId(), result.getIsApplied(), result.getDiscount(), result.getPriceWithDiscount()));

				if(result.getIsApplied()) { // ���� �� ���������
					// ���� � ������� �� ���������� ������� "��������� ���������� �/� �� ��������"
					if(promotionLine.getIsApplyDiscountOnDoc()) 
						spec.setAttribute("DocDiscount", docHead.getAttribute("DiscountOnDoc")); //$NON-NLS-1$ //$NON-NLS-2$

					if(result.getDiscount() != null) { // ���� ��������� ������ 
						promotionDiscount = result.getDiscount();
						spec.setAttribute("ExternalDiscountValue", promotionDiscount); //$NON-NLS-1$
					}
					else { // ���� ��������� ���� �� �������
						spec.setAttribute("PriceWithDiscount", result.getPriceWithDiscount()); //$NON-NLS-1$
						spec.setAttribute("Price1", null); //$NON-NLS-1$
					}
					// ���� � ������� �� ���������� ������� "��������� ���������� ������ �/�"
					spec.setBulkOperation(true);
					specService.store(spec);
					if(promotionLine.getIsApplyDiscountGroup())
						calculateDiscountValue(docHead.getDiscountFolder(), spec, new CalculateDiscountListenerImpl(specService, docHead, specs, index) {

							/* (non-Javadoc)
							 * @see com.mg.merp.discount.support.DiscountProcessorServiceBean.CalculateDiscountListenerImpl#aborted()
							 */
							@Override
							public void aborted() {
								super.aborted();
							}

							/* (non-Javadoc)
							 * @see com.mg.merp.discount.support.DiscountProcessorServiceBean.CalculateDiscountListenerImpl#completed(com.mg.merp.discount.support.DiscountResult)
							 */
							@Override
							public void completed(DiscountResult result) {
								updateSpecByDiscountResult(spec, docHead, specService, result, promotionDiscount);
								// ��������� ������ c/� ��� ��������� ������� ������������
								performApplyDiscount(specService, docHead, specs, index + 1);
							}
						});
					else { // ���� � ������� �� �� ���������� ������� "��������� ���������� ������ �/�"
						specService.store(spec);
						// ��������� ������ c/� ��� ��������� ������� ������������
						performApplyDiscount(specService, docHead, specs, index + 1);
					}
				} else // ���� �� �� ���������, ��������� ������ ������ �/�
					calculateDiscountValue(docHead.getDiscountFolder(), spec, new CalculateDiscountListenerImpl(specService, docHead, specs, index) {

						/* (non-Javadoc)
						 * @see com.mg.merp.discount.support.DiscountProcessorServiceBean.CalculateDiscountListenerImpl#aborted()
						 */
						@Override
						public void aborted() {
							super.aborted();
						}

						/* (non-Javadoc)
						 * @see com.mg.merp.discount.support.DiscountProcessorServiceBean.CalculateDiscountListenerImpl#completed(com.mg.merp.discount.support.DiscountResult)
						 */
						@Override
						public void completed(DiscountResult result) {
							updateSpecByDiscountResult(spec, docHead, specService, result, null);
							performApplyDiscount(specService, docHead, specs, index + 1);
						}
					});
			} catch (RuntimeException e) {
				aborted();
				throw e;
			}
		}
	}

	private void performApplyDiscount(GoodsDocumentSpecification<DocSpec, Integer> specService, DocHead docHead, List<DocSpec> specs, int index) {
		if (index >= specs.size()) {
			try {
				ServerUtils.getPersistentManager().flush();
				DocumentUtils.getGoodsDocumentService(docHead.getDocSection()).modifySpecifaction(specs.toArray(new DocSpec[specs.size()]));
				UIUtils.endConversation(); 
				completeApplyDiscount();
			} catch (RuntimeException e) {
				UIUtils.abortConversation();
				throw e;
			}
			return;
		}

		DocSpec docSpec = specs.get(index);
		if(isRetailDocument(docHead)) {
			List<PromotionLine> promotions = promotionService.getPromotions(docSpec.getDocHead().getDocDate(), docSpec.getCatalog(), docSpec.getCatalog().getFolder()); 

			if(!promotions.isEmpty())
				calculateDiscountValuesByPromotion(docSpec, promotions, new PromotionDiscountListenerImpl(specService, docHead, specs, index, promotions.get(0)));
			else
				calculateDiscountValue(docHead.getDiscountFolder(), docSpec, new CalculateDiscountListenerImpl(specService, docHead, specs, index));
		}
		else
			calculateDiscountValue(docHead.getDiscountFolder(), docSpec, new CalculateDiscountListenerImpl(specService, docHead, specs, index));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.discount.DiscountProcessorServiceLocal#calculateDiscountValue(com.mg.merp.core.model.Folder, com.mg.merp.document.model.DocSpec, com.mg.merp.discount.CalculateDiscountListener)
	 */
	@PermitAll
	@Remove
	public void calculateDiscountValue(Folder discountGroup, DocSpec docSpec, final	CalculateDiscountListener listener) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DiscountBusinessAddin.DOCUMENT_SPEC_PARAM_NAME, docSpec);
		params.put(DiscountBusinessAddin.PROCESS_CONTEXT_PARAM_NAME, processBAiContext);

		List<Discount> discounts = getDiscounts(discountGroup);
		if (discounts.isEmpty()) {
			listener.completed(new DiscountResult());
			return;
		}

		performCalculateDiscount(discounts, 0, params, new DiscountResult(), new BusinessAddinListener<DiscountResult>() {

			public void aborted(BusinessAddinEvent<DiscountResult> event) {
				listener.aborted();
			}

			public void completed(BusinessAddinEvent<DiscountResult> event) {
				listener.completed(event.getResult());
			}
		}, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.discount.DiscountProcessorServiceLocal#calculateDiscountValuesByPromotion(com.mg.merp.document.model.DocSpec, java.util.List, com.mg.merp.discount.CalculatePromotionDiscountListener)
	 */
	@PermitAll
	public void calculateDiscountValuesByPromotion(DocSpec docSpec, List<PromotionLine> promotions, final CalculatePromotionDiscountListener listener) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PromotionDiscountBusinessAddin.DOC_SPEC_PARAM, docSpec);
		params.put(PromotionDiscountBusinessAddin.PROMOTION_LINE_PARAM, promotions.get(0));
		params.put(PromotionDiscountBusinessAddin.PROCESS_CONTEXT_PARAM, processBAiContext);

		performCalculatePromotionDiscount(promotions, 0, params, new PromotionDiscountResult(), new BusinessAddinListener<PromotionDiscountResult>() {

			public void aborted(BusinessAddinEvent<PromotionDiscountResult> event) {
				listener.aborted();
			}

			public void completed(BusinessAddinEvent<PromotionDiscountResult> event) {
				listener.completed(event.getResult());
			}
		}, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.discount.DiscountProcessorServiceLocal#applyDiscount(com.mg.merp.document.model.DocHead)
	 */
	@PermitAll
	@Remove
	public void applyDiscount(DocHead docHead) {
//		if (getLogger().isDebugEnabled())
//			getLogger().debug("apply discount for document " + docHead.getId()); //$NON-NLS-1$
//
//		if (docHead == null)
//			throw new IllegalArgumentException("document is null"); //$NON-NLS-1$
//
//		if (docHead.getId() == null) {
//			getLogger().info("applay discount aborted, document references an unsaved transient instance"); //$NON-NLS-1$
//			return;
//		}
//
//		GoodsDocumentSpecification<DocSpec, Integer> specService = DocumentUtils.getGoodsDocumentSpecificationService(docHead.getDocSection());
//		List<DocSpec> specs = specService.findByCriteria(Restrictions.eq("DocHead", docHead)); //$NON-NLS-1$
//		if (specs.isEmpty())
//			return;
//
//		UIUtils.startConversation();
//		try {
//			performApplyDiscount(specService, docHead, specs, 0);
//		} catch (RuntimeException e) {
//			UIUtils.abortConversation();
//			throw e;
//		}
		GoodsDocumentSpecification<DocSpec, Integer> specService = DocumentUtils.getGoodsDocumentSpecificationService(docHead.getDocSection());
		List<DocSpec> specs = specService.findByCriteria(Restrictions.eq("DocHead", docHead)); //$NON-NLS-1$
		doApplyDiscount(docHead, specs);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.discount.DiscountProcessorServiceLocal#applyDiscount(com.mg.merp.document.model.DocHead, com.mg.merp.discount.ApplyDiscountListener)
	 */
	@PermitAll
	@Remove
	public void applyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener) {
		initApplyDiscountListener(�pplyDiscountListener);
		applyDiscount(docHead);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.discount.DiscountProcessorServiceLocal#applyDiscount(com.mg.merp.document.model.DocHead, java.util.List, com.mg.merp.discount.ApplyDiscountListener)
	 */
	@PermitAll
	@Remove
	public void applyDiscount(DocHead docHead, List<DocSpec> specs, ApplyDiscountListener �pplyDiscountListener) {
		initApplyDiscountListener(�pplyDiscountListener);
		doApplyDiscount(docHead, specs);
	}
			
	/* (non-Javadoc)
	 * @see com.mg.merp.discount.DiscountProcessorServiceLocal#applyDiscount(com.mg.merp.document.model.DocHead, java.util.List)
	 */
	@PermitAll
	@Remove
	public void applyDiscount(DocHead docHead, List<DocSpec> specs) {
		doApplyDiscount(docHead, specs);
	}
	
	/**
	 * ���������������� ��������� ���������� ������/�������
	 * @param �pplyDiscountListener - ��������� ���������� ������/�������
	 */
	private void initApplyDiscountListener(ApplyDiscountListener �pplyDiscountListener) {
		this.�pplyDiscountListener = �pplyDiscountListener;
	}
	
	/**
	 * ������� ��������� ���������� ������/�������
	 */
	private void completeApplyDiscount() {
		if (�pplyDiscountListener != null)
			�pplyDiscountListener.completed();
	}
	
	/**
	 * �������� ���������� ������/�������
	 */
	private void abortApplyDiscount() {
		if (�pplyDiscountListener != null)
			�pplyDiscountListener.aborted();
	}
	
	/**
	 * ��������� ������/������� �� �������� ��� ������� ������������
	 * @param docHead - ��������
	 * @param specs - ������ ������� ������������
	 */
	protected void doApplyDiscount(DocHead docHead, List<DocSpec> specs) {
		if (getLogger().isDebugEnabled())
			getLogger().debug("apply discount for document" + docHead.getId()); //$NON-NLS-1$

		if (docHead == null)
			throw new IllegalArgumentException("document is null"); //$NON-NLS-1$

		if (docHead.getId() == null) {
			getLogger().info("apply discount aborted, document references an unsaved transient instance"); //$NON-NLS-1$
			return;
		}
		
		if (specs.isEmpty())
			return;
		
		GoodsDocumentSpecification<DocSpec, Integer> specService = DocumentUtils.getGoodsDocumentSpecificationService(docHead.getDocSection());
		UIUtils.startConversation();
		try {
			performApplyDiscount(specService, docHead, specs, 0);
		} catch (RuntimeException e) {
			UIUtils.abortConversation();
			throw e;
		}
	}

	protected boolean isRetailDocument(DocHead docHead) {
		return docHead.hasAttribute("DiscountCard"); //$NON-NLS-1$
	}

	/**
	 * �������� ������� ������������ � ������������ � ���������� ���������� ������/�������
	 * @param spec - ������� ������������
	 * @param docHead - ��������� ���������
	 * @param specService - ������ ������� ������������
	 * @param result - ��������� ���������� ������/�������
	 * @param promotionDiscount - �������� ������/������� �� ��
	 */
	protected void updateSpecByDiscountResult(DocSpec spec, DocHead docHead, GoodsDocumentSpecification<DocSpec, Integer> specService, DiscountResult result, BigDecimal promotionDiscount) {
		if(result.getIsApplied()) {
			if(result.getIsApplyDiscountOnDoc()) // ���� ��������� ��������� "������ �� ��������"
				spec.setAttribute("DocDiscount", docHead.getAttribute("DiscountOnDoc")); //$NON-NLS-1$ //$NON-NLS-2$
			if(result.getDiscount() != null) // ���� ������������� ������, �� ����� ������ ��
				spec.setAttribute("ExternalDiscountValue", result.getDiscount().add(promotionDiscount == null ? BigDecimal.ZERO : promotionDiscount)); //$NON-NLS-1$
			else { // ���� ������������� ���� �� �������
				spec.setAttribute("PriceWithDiscount", result.getPriceWithDiscount()); //$NON-NLS-1$
				spec.setAttribute("Price1", null); //$NON-NLS-1$
			}
		} else // ���� ������/������� �� ��������� � ��������� ������-������, �� ��������� "������ �� ��������"
			spec.setAttribute("DocDiscount", docHead.getAttribute("DiscountOnDoc")); //$NON-NLS-1$ //$NON-NLS-2$
		spec.setBulkOperation(true);
		specService.store(spec);
	}
	
	/**
	 * �������� ������ ������/������� � ������� ���������� BAi �������
	 * @param discountGroup - ����� ������/�������
	 * @return ������ ������/������� � ������� ���������� BAi �������
	 */
	protected List<Discount> getDiscounts(Folder discountGroup) {
		return ormTemplate.findByCriteria(OrmTemplate.createCriteria(Discount.class)
			.add(Restrictions.eq("Folder", discountGroup)) //$NON-NLS-1$
			.add(Restrictions.isNotNull("Alg")) //$NON-NLS-1$
			.setFlushMode(FlushMode.MANUAL));
	}

}
