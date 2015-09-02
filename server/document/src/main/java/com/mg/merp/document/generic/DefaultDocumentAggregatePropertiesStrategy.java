/**
 * DefaultDocumentAggregatePropertiesStrategy.java
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
package com.mg.merp.document.generic;

import java.math.BigDecimal;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.GoodsDocument;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;

/**
 * ����������� ���������� ��������� ��������� ������������ ������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultDocumentAggregatePropertiesStrategy.java,v 1.5 2009/01/23 14:16:58 safonov Exp $
 */
public class DefaultDocumentAggregatePropertiesStrategy extends
		AbstractDocumentAggregatePropertiesStrategy {
	private String[] docHeadFields;
	private String[] docSpecFields;
	//private Projection[] docSpecProjections;
	//private Class<DocSpec> docSpecClass;
	@SuppressWarnings("unchecked")
	private GoodsDocument documentService;
	//private DocHead docHead;
	private DocSpec[] docSpecs;
	private boolean excludeSpecs = true;
	private int currencyScale;
	private boolean isRemove;

	@SuppressWarnings("unchecked")
	public DefaultDocumentAggregatePropertiesStrategy(GoodsDocument documentService, DocSpec[] docSpecs, boolean isRemove, int currencyScale) {
		this(documentService, docSpecs, new String[] {"SumCur", "Volume", "Weight"}, new String[] {"Summa", "Volume", "Weight"}, isRemove, currencyScale);
	}

	@SuppressWarnings("unchecked")
	public DefaultDocumentAggregatePropertiesStrategy(GoodsDocument documentService, DocSpec[] docSpecs, String[] docHeadFields, String[] docSpecFields, boolean isRemove, int currencyScale) {
		this.documentService = documentService;
		this.docSpecs = docSpecs;
		this.currencyScale = currencyScale;
		this.isRemove = isRemove;
		this.docHeadFields = docHeadFields;
		this.docSpecFields = docSpecFields;
	}

	private BigDecimal[] loadAggregateValues(DocHead docHead) {
		Object[] ids = new Integer[docSpecs.length];
		for (int i = 0; i < docSpecs.length; i++) {
			DocSpec docSpec = docSpecs[i];
			if (docSpec.getDocHead() == null || docSpec.getDocHead().getId() != docHead.getId())
				throw new IllegalStateException("Effort creating specifications from different documents");
			ids[i] = docSpec.getId();
		}
		
		Class<DocSpec> docSpecClass = DocSpec.class; //������ ������� ����� ������������
		if (docSpecs.length > 0) //�� ���� ���� �����������, �� ������ ��� �� ����� ������������
			docSpecClass = ReflectionUtils.getEntityClass(docSpecs[0]);
		
		Projection[] docSpecProjections = new Projection[docSpecFields.length];
		for (int i = 0; i < docSpecFields.length; i++)
			docSpecProjections[i] = Projections.sum(docSpecFields[i]);
		
		Criteria criteria = OrmTemplate.createCriteria(docSpecClass)
				.setProjection(Projections.projectionList(docSpecProjections))
				.add(Restrictions.eq("DocHead", docHead));
				//.setFlushMode(FlushMode.MANUAL); //comment for fix http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4647
		if (excludeSpecs && ids.length > 0)
			criteria.add(Restrictions.not(Restrictions.in("Id", ids)));
		Object[] values = (Object[]) OrmTemplate.getInstance().findUniqueByCriteria(criteria);
		BigDecimal[] result = new BigDecimal[docSpecProjections.length];
		for (int i = 0; i < docSpecProjections.length; i++)
			result[i] = values[i] != null ? (BigDecimal) values[i] : BigDecimal.ZERO;
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private DocHead loadDocHead() {
		DocHead docHead = docSpecs[0].getDocHead();
		if (docHead == null)
			throw new IllegalStateException("document is null");
		
		//�������� ���� �� � ������ (����� ������ ��������� ����� ��� ���������� �������� ��� ��������)
		return (DocHead) documentService.load(docHead.getId());
	}
	
	/**
	 * @return the docHeadFields
	 */
	protected String[] getDocHeadFields() {
		return docHeadFields;
	}

	/**
	 * @return the docSpecFields
	 */
	protected String[] getDocSpecFields() {
		return docSpecFields;
	}

	/**
	 * @return the docSpecs
	 */
	protected DocSpec[] getDocSpecs() {
		return docSpecs;
	}

	/**
	 * @return the currencyScale
	 */
	protected int getCurrencyScale() {
		return currencyScale;
	}

	/**
	 * @return the isRemove
	 */
	protected boolean isRemove() {
		return isRemove;
	}

	/**
	 * ���������� �������������� ����� ���������
	 * 
	 * @param docHead
	 */
	protected void doAdjustDocHead(DocHead docHead) {
		//�������� ����� � ��������� ������
		docHead.setSumNat(MathUtils.multiply(docHead.getSumCur(), docHead.getCurCource(), new RoundContext(currencyScale)));
		// �������� ����� � ������ �� ��������, ������������� � ������������ ������
		docHead.setSumCur(MathUtils.round(docHead.getSumCur(), new RoundContext(currencyScale)));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.AbstractDocumentAggregatePropertiesStrategy#doCalculate()
	 */
	@Override
	protected void doCalculate() {
		//��� ���������� ������������, �� �������� ���������
		if (docSpecs == null || docSpecs.length == 0)
			return;
		
		if (docSpecFields.length != docHeadFields.length)
			throw new IllegalStateException("lenght of fields list mismatch");
		
		DocHead docHead = loadDocHead();
		//��� ����������� ��������� �� ��������� ��, ����� ���������� ��������������
		//�������� �������� �������� �������� ����� ����������� ��, �������� �� ����������� ��
		//��������� �� ���������� ���, ��� ��� ��� ���������, �������� ��� �������������
		//��������� ��������� ������������� (http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4436)
		//DocFlowHelper.checkStatus(docHead);
		
		BigDecimal[] aggregateValues = loadAggregateValues(docHead);
		//������� ������������ �� ������, �.�. ��� �� ����������� � �������� ������������ �������� (����� ���������)
		if (!isRemove)
			for (DocSpec spec : docSpecs) {
				for (int i = 0; i < docSpecFields.length; i++) {
					BigDecimal specAttr = (BigDecimal) spec.getAttribute(docSpecFields[i]);
					if (specAttr != null)
						aggregateValues[i] = aggregateValues[i].add(specAttr);
				}
			}
		
		//��������� ������������ �������� � ���������
		for (int i = 0; i < docHeadFields.length; i++) {
			docHead.setAttribute(docHeadFields[i], aggregateValues[i]);
		}
		
		doAdjustDocHead(docHead);

		//������� ��������� ��������� ��������� � ����, ��. https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4985
		ServerUtils.getPersistentManager().flush();
	}

}
