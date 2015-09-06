/*
 * FinFeatTotals.java
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
package com.mg.merp.finance.totals;

import com.mg.framework.utils.StringUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind.ftkFeat;

/**
 * @author Valentin A. Poroxnenko
 * @author Konstantin S. Alikaev
 * @version $Id: FinFeatTotals.java,v 1.5 2007/09/17 12:12:25 alikaev Exp $
 */
public class FinFeatTotals extends FinanceTotals {

  static final String JOIN_ACC_PLAN = " left join finaccount a on a.id=ta.acc_id"; //$NON-NLS-1$
  static final String JOIN_FEAT_PLAN = " left join finaccount f on f.id=r.feat_id"; //$NON-NLS-1$
  protected Set<Integer> featureIds;
  private List<Integer> featureFolderIds;
  private Map<Integer, Set<Integer>> featAnlIdList;

  public FinFeatTotals() {
    super();
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.totals.FinanceTotals#init()
   */
  protected void init() {
    ftKind = ftkFeat;
    table = "finturnfeat"; //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.totals.FinanceTotals#createSQLParts(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  protected String[] createSQLParts(String fields, String from, String where, String order) {
    boolean isAccplanJoined = false;
    boolean isFeatAccJoined = false;
    from = from + " left join finturnacc ta on ta.id = r.finturnacc_id"; //$NON-NLS-1$
    if (this.fields.contains("PNAME")) //$NON-NLS-1$
      fields = fields + ", p.PNAME"; //$NON-NLS-1$
    if (this.fields.contains("DATETO")) //$NON-NLS-1$
      fields = fields + ", p.DATETO";     //$NON-NLS-1$
    if (this.fields.contains("FEAT_ID")) //$NON-NLS-1$
      fields = fields + ", r.FEAT_ID"; //$NON-NLS-1$
    if (this.fields.contains("FEATFOLDER_ID")) { //$NON-NLS-1$
      fields = fields + ", f.FOLDER_ID FEATFOLDER_ID"; //$NON-NLS-1$
      from = from + JOIN_FEAT_PLAN;
      isFeatAccJoined = true;
    }
    if (this.fields.contains("FEAT_CODE")) { //$NON-NLS-1$
      fields = fields + ", f.CODE FEAT_CODE"; //$NON-NLS-1$
      if (!isFeatAccJoined) {
        from = from + JOIN_FEAT_PLAN;
        isFeatAccJoined = true;
      }
    }
    for (byte i = 1; i <= MAX_FINANLLEVEL; i++) {
      if (this.fields.contains("FANL" + i + "_ID")) //$NON-NLS-1$ //$NON-NLS-2$
        fields = fields + ", r.FANL" + i + "_ID"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (this.fields.contains("ACC_ID")) //$NON-NLS-1$
      fields = fields + ", ta.ACC_ID";         //$NON-NLS-1$
    if (this.fields.contains("ACCFOLDER_ID")) { //$NON-NLS-1$
      fields = fields + ", a.FOLDER_ID ACCFOLDER_ID"; //$NON-NLS-1$
      from = from + JOIN_ACC_PLAN;
      isAccplanJoined = true;
    }
    if (this.fields.contains("ACC_CODE")) { //$NON-NLS-1$
      fields = fields + ", a.CODE ACC_CODE"; //$NON-NLS-1$
      if (!isAccplanJoined) {
        from = from + JOIN_ACC_PLAN;
        isAccplanJoined = true;
      }
    }

    if (this.fields.contains("CURCODE")) //$NON-NLS-1$
      fields = fields + ", r.curcode"; //$NON-NLS-1$
    for (byte i = 1; i <= MAX_FINANLLEVEL; i++) {
      if (this.fields.contains("ANL" + i + "_ID")) //$NON-NLS-1$ //$NON-NLS-2$
        fields = fields + ", ta.ANL" + i + "_ID"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (this.fields.contains("REMNBEGNAT")) //$NON-NLS-1$
      fields = fields + ", r.REMNBEGNAT"; //$NON-NLS-1$
    if (this.fields.contains("REMNBEGCUR")) //$NON-NLS-1$
      fields = fields + ", r.REMNBEGCUR"; //$NON-NLS-1$

    if (this.fields.contains("REMNBEGNATPLAN")) //$NON-NLS-1$
      fields = fields + ", r.REMNBEGNATPLAN"; //$NON-NLS-1$
    if (this.fields.contains("REMNBEGCURPLAN")) //$NON-NLS-1$
      fields = fields + ", r.REMNBEGCURPLAN"; //$NON-NLS-1$

    int sz = featureIds != null ? featureIds.size() : 0;
    String s = StringUtils.EMPTY_STRING;
    if (sz > 0) {
      Integer[] ids = featureIds.toArray(new Integer[featureIds.size()]);
      if (sz == 1)
        where = where + " and (r.feat_id = " + ids[0] + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      else {
        for (int i = 0; i < sz - 1; i++)
          s = s + ids[i] + ","; //$NON-NLS-1$
        s = s + ids[sz - 1];
        where = where + " and (r.feat_id in (" + s + "))"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    sz = featureFolderIds != null ? featureFolderIds.size() : 0;
    s = StringUtils.EMPTY_STRING;
    if (sz > 0) {
      if (!isAccplanJoined) {
        from = from + JOIN_FEAT_PLAN;
        isAccplanJoined = true;
      }
      if (sz == 1)
        where = where + " and (f.folder_id = " + featureFolderIds.get(0) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      else {
        for (int i = 0; i < sz - 1; i++)
          s = s + featureFolderIds.get(i) + ","; //$NON-NLS-1$
        s = s + featureFolderIds.get(sz - 1);
        where = where + " and (f.folder_id (" + s + "))"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    if (!featAnlIdList.isEmpty()) {
      for (Integer key = 1; key <= MAX_FINANLLEVEL; key++)
        if (featAnlIdList.containsKey(key)) {
          Set<Integer> featAnlIds = featAnlIdList.get(key);
          sz = featAnlIds != null ? featAnlIds.size() : 0;
          if (sz > 0) {
            s = StringUtils.EMPTY_STRING;
            for (Iterator it = featAnlIds.iterator(); it.hasNext(); ) {
              Integer id = (Integer) it.next();
              s = s + id;
              if (it.hasNext())
                s = s + ",";     //$NON-NLS-1$
            }
            if (sz == 1)
              where = where + "and (r.fanl" + key + "_id=" + s + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            else
              where = where + " and (r.fanl" + key + "_id in (" + s + "))"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          }
        }
    }
    String[] ret = {fields, from, where, order};
    return ret;
  }

  public void setFeatureIds(Set<Integer> featureIds) {
    this.featureIds = featureIds;
  }

  public void setFeatureFolderId(List<Integer> featureFolderId) {
    featureFolderIds = featureFolderId;
  }

  public void setFeatAnlIdList(Map<Integer, Set<Integer>> analytics) {
    featAnlIdList = analytics;
  }

  public void addFeature(int featureId) {
    featureIds.add(featureId);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.totals.FinanceTotals#getPeriodBeginDate(int)
   */
  @Override
  protected Date getPeriodBeginDate(int periodId) {
    // TODO Auto-generated method stub
    return null;
  }

}
