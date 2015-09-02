/*
 * FinRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.framework.generic.ui.DefaultLegacySearchHelp;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;

/**
 * @author leonova
 * @version $Id: FinRest.java,v 1.1 2006/12/19 06:55:24 leonova Exp $
 */
public class FinRest extends DefaultHierarhyRestrictionForm{
	protected Account srcAcc = null;
	protected Account dstAcc = null;
	@DataItemName("Finance.Cond.FeatureAccount")
	protected Account featAcc = null;			
	protected String anlLevel1SrcName = "";
	protected String anlLevel2SrcName = "";
	protected String anlLevel3SrcName = "";
	protected String anlLevel4SrcName = "";
	protected String anlLevel5SrcName = "";
	protected String anlLevel1DstName = "";
	protected String anlLevel2DstName = "";
	protected String anlLevel3DstName = "";
	protected String anlLevel4DstName = "";
	protected String anlLevel5DstName = "";
	protected String anlLevel1FeatName = "";
	protected String anlLevel2FeatName = "";
	protected String anlLevel3FeatName = "";
	protected String anlLevel4FeatName = "";
	protected String anlLevel5FeatName = "";		
	protected Integer anlLevel1SrcId = null;
	protected Integer anlLevel2SrcId = null;
	protected Integer anlLevel3SrcId = null;
	protected Integer anlLevel4SrcId = null;
	protected Integer anlLevel5SrcId = null;	
	protected Integer anlLevel1DstId = null;
	protected Integer anlLevel2DstId = null;
	protected Integer anlLevel3DstId = null;
	protected Integer anlLevel4DstId = null;
	protected Integer anlLevel5DstId = null;
	protected Integer anlLevel1FeatId = null;
	protected Integer anlLevel2FeatId = null;
	protected Integer anlLevel3FeatId = null;
	protected Integer anlLevel4FeatId = null;
	protected Integer anlLevel5FeatId = null;
	
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.srcAcc = null;
		this.dstAcc = null;	
		this.featAcc = null;	
		this.anlLevel1SrcName = "";
		this.anlLevel2SrcName = "";
		this.anlLevel3SrcName = "";
		this.anlLevel4SrcName = "";
		this.anlLevel5SrcName = "";		
		this.anlLevel1DstName = "";
		this.anlLevel2DstName = "";
		this.anlLevel3DstName = "";
		this.anlLevel4DstName = "";
		this.anlLevel5DstName = "";				
		this.anlLevel1FeatName = "";
		this.anlLevel2FeatName = "";
		this.anlLevel3FeatName = "";
		this.anlLevel4FeatName = "";
		this.anlLevel5FeatName = "";			
		this.anlLevel1SrcId = null;
		this.anlLevel2SrcId = null;
		this.anlLevel3SrcId = null;
		this.anlLevel4SrcId = null;
		this.anlLevel5SrcId = null;		
		this.anlLevel1DstId = null;
		this.anlLevel2DstId = null;
		this.anlLevel3DstId = null;
		this.anlLevel4DstId = null;
		this.anlLevel5DstId = null;				
		this.anlLevel1FeatId = null;
		this.anlLevel2FeatId = null;
		this.anlLevel3FeatId = null;
		this.anlLevel4FeatId = null;
		this.anlLevel5FeatId = null;			

	}
	
	/**
	 * @return Returns the dstAcc.
	 */
	protected Account getDstAcc() {
		return dstAcc;
	}

	/**
	 * @return Returns the featAcc.
	 */
	protected Account getFeatAcc() {
		return featAcc;
	}
	
	/**
	 * @return Returns the srcAcc.
	 */
	protected Account getSrcAcc() {
		return srcAcc;
	}
	
	private SearchHelp setSearchHelp(final SysClass classAnl) {
		return new DefaultLegacySearchHelp() {

			@Override
			protected String getServiceName() {
				return FinUtils.getBeanName(classAnl);
			}
			
		};
	}
	
	private void setIdAnalit(String fieldName, Integer value){
		if (fieldName.equals("anlLevel1SrcName")) {
			setAnlLevel1SrcId(value);	
		}	
		if (fieldName.equals("anlLevel2SrcName")) {
			setAnlLevel2SrcId(value);	
		}	
		if (fieldName.equals("anlLevel3SrcName")) {
			setAnlLevel3SrcId(value);	
		}	
		if (fieldName.equals("anlLevel4SrcName")) {
			setAnlLevel4SrcId(value);	
		}
		if (fieldName.equals("anlLevel5SrcName")) {
			setAnlLevel5SrcId(value);	
		}			
		if (fieldName.equals("anlLevel1DstName")) {
			setAnlLevel1DstId(value);	
		}	
		if (fieldName.equals("anlLevel2DstName")) {
			setAnlLevel2DstId(value);	
		}	
		if (fieldName.equals("anlLevel3DstName")) {
			setAnlLevel3DstId(value);	
		}	
		if (fieldName.equals("anlLevel4DstName")) {
			setAnlLevel4DstId(value);	
		}
		if (fieldName.equals("anlLevel5DstName")) {
			setAnlLevel5DstId(value);	
		}	
		if (fieldName.equals("anlLevel1FeatName")) {
			setAnlLevel1FeatId(value);	
		}	
		if (fieldName.equals("anlLevel2FeatName")) {
			setAnlLevel2FeatId(value);	
		}	
		if (fieldName.equals("anlLevel3FeatName")) {
			setAnlLevel3FeatId(value);	
		}	
		if (fieldName.equals("anlLevel4FeatName")) {
			setAnlLevel4FeatId(value);	
		}
		if (fieldName.equals("anlLevel5FeatName")) {
			setAnlLevel5FeatId(value);	
		}			
	}
	
	@SuppressWarnings("unused")
	private void runSearch(final Account account, String anlBusinessServiceName, final String anlClassName, final String anlLevelName, FinAnlPlanSearchHelp accSearchHelp) throws Exception{
		if ((Boolean)account.getAttribute(anlBusinessServiceName)) {			
			final SysClass classAnl = ServerUtils.getPersistentManager().find(SysClass.class, ((SysClass)account.getAttribute(anlClassName)).getPrimaryKey());		
			SearchHelp searchHelp = setSearchHelp(classAnl);
		
			searchHelp.addSearchHelpListener(new SearchHelpListener() {

				public void searchCanceled(SearchHelpEvent event) {
				
				}
				
				public void searchPerformed(SearchHelpEvent event) {
					PersistentObject entity = event.getItems()[0];					
					try {
						setFieldValue(anlLevelName, FinUtils.getAnlName(FinUtils.getBeanName((SysClass)account.getAttribute(anlClassName)), (Integer)(entity.getAttribute("Id"))));
						setIdAnalit(anlLevelName, (Integer)entity.getPrimaryKey());						
					} catch (NoSuchFieldException e) {
						getLogger().error(e);
					} catch (IllegalAccessException e) {						
						getLogger().error(e);
					}
					view.flushModel();
				}
			
			});
			searchHelp.search();
		} else {
			accSearchHelp.setAccPlanName(account);	

			accSearchHelp.addSearchHelpListener(new SearchHelpListener() {

				public void searchCanceled(SearchHelpEvent event) {
					
				}

				public void searchPerformed(SearchHelpEvent event) {					
					Analytics entity = (Analytics)event.getItems()[0];	
					try {
						setFieldValue(anlLevelName, entity.getCode());
						setIdAnalit(anlLevelName, (Integer)entity.getPrimaryKey());
					} catch (NoSuchFieldException e) {
						getLogger().error(e);
					} catch (IllegalAccessException e) {						
						getLogger().error(e);
					}
					view.flushModel();
				}
				
			});			
			accSearchHelp.search();
		}

	}

	private Account getCurrentSrc(){
		Account srcAccount = ServerUtils.getPersistentManager().find(Account.class, getSrcAcc().getId());
		return srcAccount;
	}

	private Account getCurrentDst(){
		Account dstAccount = ServerUtils.getPersistentManager().find(Account.class, getDstAcc().getId());
		return dstAccount;
	}

	private Account getCurrentFeat(){
		Account featAccount = ServerUtils.getPersistentManager().find(Account.class, getFeatAcc().getId());
		return featAccount;
	}

	protected void onActionClearAnlLevel1Src(WidgetEvent event) {		
		anlLevel1SrcName = null;
	}
	
	protected void onActionClearAnlLevel2Src(WidgetEvent event) {		
		anlLevel2SrcName = null;
	}
	
	protected void onActionClearAnlLevel3Src(WidgetEvent event) {
		anlLevel3SrcName = null;
	}
	
	protected void onActionClearAnlLevel4Src(WidgetEvent event) {
		anlLevel4SrcName = null;
	}
	
	protected void onActionClearAnlLevel5Src(WidgetEvent event) {
		anlLevel5SrcName = null;
	}
	
	protected void onActionClearAnlLevel1Dst(WidgetEvent event) {
		anlLevel1DstName = null;
	}
	
	protected void onActionClearAnlLevel2Dst(WidgetEvent event) {
		anlLevel2DstName = null;
	}
	
	protected void onActionClearAnlLevel3Dst(WidgetEvent event) {
		anlLevel3DstName = null;
	}
	
	protected void onActionClearAnlLevel4Dst(WidgetEvent event) {
		anlLevel4DstName = null;
	}
	
	protected void onActionClearAnlLevel5Dst(WidgetEvent event) {
		anlLevel5DstName = null;
	}
	
	protected void onActionClearAnlLevel1Feat(WidgetEvent event) {
		anlLevel1FeatName = null;
	}
	
	protected void onActionClearAnlLevel2Feat(WidgetEvent event) {
		anlLevel2FeatName = null;
	}
	
	protected void onActionClearAnlLevel3Feat(WidgetEvent event) {
		anlLevel3FeatName = null;
	}
	
	protected void onActionClearAnlLevel4Feat(WidgetEvent event) {
		anlLevel4FeatName = null;
	}
	
	protected void onActionClearAnlLevel5Feat(WidgetEvent event) {
		anlLevel5FeatName = null;
	}
	
	protected void onActionChooseAnlLevel1Src(WidgetEvent event) throws Exception{		
		if (getSrcAcc() != null) {			
			runSearch(getCurrentSrc(), "Anl1Kind", "Anl1Class", "anlLevel1SrcName", new FinAnlPlanSrcAnlLevel1SearchHelp());			
		}				
	}
	
	protected void onActionChooseAnlLevel2Src(WidgetEvent event) throws Exception{
		if (getSrcAcc() != null) {		
			runSearch(getCurrentSrc(), "Anl2Kind", "Anl2Class", "anlLevel2SrcName", new FinAnlPlanSrcAnlLevel2SearchHelp());	
		}
	}
	
	protected void onActionChooseAnlLevel3Src(WidgetEvent event) throws Exception{
		if (getSrcAcc() != null) {		
			runSearch(getCurrentSrc(), "Anl3Kind", "Anl3Class", "anlLevel3SrcName", new FinAnlPlanSrcAnlLevel3SearchHelp());			
		}		
	}
	
	protected void onActionChooseAnlLevel4Src(WidgetEvent event) throws Exception{
		if (getSrcAcc() != null) {		
			runSearch(getCurrentSrc(), "Anl4Kind", "Anl4Class", "anlLevel4SrcName", new FinAnlPlanSrcAnlLevel4SearchHelp());			
		}		
	}
	
	protected void onActionChooseAnlLevel5Src(WidgetEvent event) throws Exception{
		if (getSrcAcc() != null) {		
			runSearch(getCurrentSrc(), "Anl5Kind", "Anl5Class", "anlLevel5SrcName", new FinAnlPlanSrcAnlLevel5SearchHelp());			
		}		
	}

	protected void onActionChooseAnlLevel1Dst(WidgetEvent event) throws Exception{		
		if (getDstAcc() != null) {			
			runSearch(getCurrentDst(), "Anl1Kind", "Anl1Class", "anlLevel1DstName", new FinAnlPlanDstAnlLevel1SearchHelp());			
		}				
	}
	
	protected void onActionChooseAnlLevel2Dst(WidgetEvent event) throws Exception{
		if (getDstAcc() != null) {		
			runSearch(getCurrentDst(), "Anl2Kind", "Anl2Class", "anlLevel2DstName", new FinAnlPlanDstAnlLevel2SearchHelp());	
		}
	}
	
	protected void onActionChooseAnlLevel3Dst(WidgetEvent event) throws Exception{
		if (getDstAcc() != null) {		
			runSearch(getCurrentDst(), "Anl3Kind", "Anl3Class", "anlLevel3DstName", new FinAnlPlanDstAnlLevel3SearchHelp());			
		}		
	}
	
	protected void onActionChooseAnlLevel4Dst(WidgetEvent event) throws Exception{
		if (getDstAcc() != null) {		
			runSearch(getCurrentDst(), "Anl4Kind", "Anl4Class", "anlLevel4DstName", new FinAnlPlanDstAnlLevel4SearchHelp());			
		}		
	}
	
	protected void onActionChooseAnlLevel5Dst(WidgetEvent event) throws Exception{
		if (getDstAcc() != null) {		
			runSearch(getCurrentDst(), "Anl5Kind", "Anl5Class", "anlLevel5DstName", new FinAnlPlanDstAnlLevel5SearchHelp());			
		}		
	}	
	
	protected void onActionChooseAnlLevel1Feat(WidgetEvent event) throws Exception{		
		if (getFeatAcc() != null) {			
			runSearch(getCurrentFeat(), "Anl1Kind", "Anl1Class", "anlLevel1FeatName", new FinAnlPlanSrcAnlLevel1SearchHelp());			
		}				
	}
	
	protected void onActionChooseAnlLevel2Feat(WidgetEvent event) throws Exception{
		if (getFeatAcc() != null) {		
			runSearch(getCurrentFeat(), "Anl2Kind", "Anl2Class", "anlLevel2FeatName", new FinAnlPlanSrcAnlLevel2SearchHelp());	
		}
	}
	
	protected void onActionChooseAnlLevel3Feat(WidgetEvent event) throws Exception{
		if (getFeatAcc() != null) {		
			runSearch(getCurrentFeat(), "Anl3Kind", "Anl3Class", "anlLevel3FeatName", new FinAnlPlanSrcAnlLevel3SearchHelp());			
		}		
	}
	
	protected void onActionChooseAnlLevel4Feat(WidgetEvent event) throws Exception{
		if (getFeatAcc() != null) {		
			runSearch(getCurrentFeat(), "Anl4Kind", "Anl4Class", "anlLevel4FeatName", new FinAnlPlanSrcAnlLevel4SearchHelp());			
		}		
	}
	
	protected void onActionChooseAnlLevel5Feat(WidgetEvent event) throws Exception{
		if (getFeatAcc() != null) {		
			runSearch(getCurrentFeat(), "Anl5Kind", "Anl5Class", "anlLevel5FeatName", new FinAnlPlanSrcAnlLevel5SearchHelp());			
		}		
	}

	public Integer getAnlLevel1SrcId() {
		return anlLevel1SrcId;
	}

	public void setAnlLevel1SrcId(Integer anlLevel1SrcId) {
		this.anlLevel1SrcId = anlLevel1SrcId;
	}

	public Integer getAnlLevel2SrcId() {
		return anlLevel2SrcId;
	}

	public void setAnlLevel2SrcId(Integer anlLevel2SrcId) {
		this.anlLevel2SrcId = anlLevel2SrcId;
	}

	public Integer getAnlLevel3SrcId() {
		return anlLevel3SrcId;
	}

	public void setAnlLevel3SrcId(Integer anlLevel3SrcId) {
		this.anlLevel3SrcId = anlLevel3SrcId;
	}

	public Integer getAnlLevel4SrcId() {
		return anlLevel4SrcId;
	}

	public void setAnlLevel4SrcId(Integer anlLevel4SrcId) {
		this.anlLevel4SrcId = anlLevel4SrcId;
	}

	public Integer getAnlLevel5SrcId() {
		return anlLevel5SrcId;
	}

	public void setAnlLevel5SrcId(Integer anlLevel5SrcId) {
		this.anlLevel5SrcId = anlLevel5SrcId;
	}

	public Integer getAnlLevel1DstId() {
		return anlLevel1DstId;
	}

	public void setAnlLevel1DstId(Integer anlLevel1DstId) {
		this.anlLevel1DstId = anlLevel1DstId;
	}

	public Integer getAnlLevel2DstId() {
		return anlLevel2DstId;
	}

	public void setAnlLevel2DstId(Integer anlLevel2DstId) {
		this.anlLevel2DstId = anlLevel2DstId;
	}

	public Integer getAnlLevel3DstId() {
		return anlLevel3DstId;
	}

	public void setAnlLevel3DstId(Integer anlLevel3DstId) {
		this.anlLevel3DstId = anlLevel3DstId;
	}

	public String getAnlLevel4FeatName() {
		return anlLevel4FeatName;
	}

	public void setAnlLevel4FeatName(String anlLevel4FeatName) {
		this.anlLevel4FeatName = anlLevel4FeatName;
	}

	public Integer getAnlLevel5DstId() {
		return anlLevel5DstId;
	}

	public void setAnlLevel5DstId(Integer anlLevel5DstId) {
		this.anlLevel5DstId = anlLevel5DstId;
	}

	public Integer getAnlLevel1FeatId() {
		return anlLevel1FeatId;
	}

	public void setAnlLevel1FeatId(Integer anlLevel1FeatId) {
		this.anlLevel1FeatId = anlLevel1FeatId;
	}

	public Integer getAnlLevel2FeatId() {
		return anlLevel2FeatId;
	}

	public void setAnlLevel2FeatId(Integer anlLevel2FeatId) {
		this.anlLevel2FeatId = anlLevel2FeatId;
	}

	public Integer getAnlLevel3FeatId() {
		return anlLevel3FeatId;
	}

	public void setAnlLevel3FeatId(Integer anlLevel3FeatId) {
		this.anlLevel3FeatId = anlLevel3FeatId;
	}

	public Integer getAnlLevel4DstId() {
		return anlLevel4DstId;
	}

	public void setAnlLevel4DstId(Integer anlLevel4DstId) {
		this.anlLevel4DstId = anlLevel4DstId;
	}

	public Integer getAnlLevel4FeatId() {
		return anlLevel4FeatId;
	}

	public Integer getAnlLevel5FeatId() {
		return anlLevel5FeatId;
	}

	public void setAnlLevel5FeatId(Integer anlLevel5FeatId) {
		this.anlLevel5FeatId = anlLevel5FeatId;
	}

	public void setAnlLevel4FeatId(Integer anlLevel4FeatId) {
		this.anlLevel4FeatId = anlLevel4FeatId;
	}

	
}
