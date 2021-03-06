package com.mg.framework.support.dataset.sohlman;

/**
 * This only for Strings
 *
 * @author Sampsa Sohlman
 * @version 2003-02-27
 */
public class GroupSubstringToAll extends GroupCalc {
  private int ii_start = 0;
  private int ii_end = 0;

  /**
   * Constructor for GroupSubstringToAll.
   */
  public GroupSubstringToAll(int ai_columnId) {
    super(ai_columnId);
  }

  /**
   * Constructor for GroupSubstringToAll.
   */
  public GroupSubstringToAll(int ai_columnId, int ai_start, int ai_end) {
    super(ai_columnId);
    ii_start = ai_start;
    ii_end = ai_end;
  }

  /**
   * @see com.sohlman.dataset.GroupCalc#calculateGroupBy(Object[])
   */
  public Object calculateGroupBy(Object[] a_Objects) {
    if (a_Objects == null) {
      return null;
    }

    String l_String = (String) a_Objects[0];
    if (ii_end == 0) {
      return l_String;
    } else {
      int li_end = ii_end;

      if (l_String.length() < li_end) {
        li_end = l_String.length();
      }

      return l_String.substring(ii_start, li_end);
    }
  }

}
