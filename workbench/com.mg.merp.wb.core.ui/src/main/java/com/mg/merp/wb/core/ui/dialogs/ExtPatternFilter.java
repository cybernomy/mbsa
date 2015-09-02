/* ExtPatternFilter.java
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
package com.mg.merp.wb.core.ui.dialogs;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.ui.dialogs.PatternFilter;

/**
 * Фильтр, используемый в объектах типа FilteredTree.
 * Есть возможность отмечать объекты как соответствующие фильтру принудительно,
 * т.е. даже если они не подходят по маске
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: ExtPatternFilter.java,v 1.3 2007/07/11 05:57:03 poroxnenko Exp $ 
 */
public class ExtPatternFilter extends PatternFilter {
	
	private Set<String> matches = new LinkedHashSet<String>();
	
	protected boolean wordMatches(String text) {
		if (matches.contains(text))
			return true;
		else 
			return super.wordMatches(text);
	}
	
	/**
	 * Добавление объекта в список "подходящих".
	 * Объект с именем matchStr будет выделен всегда, независимо от соответствия фильтру
	 * @param matchStr
	 * 			принудительно добавляемый объект
	 */
	public void addMatch(String matchStr){
		matches.add(matchStr);
	}
	
	public void removeMatch(String matchStr) {
		matches.remove(matchStr);
	}
	
	public void setMatches(String[] matches){
		this.matches = new LinkedHashSet<String>(Arrays.asList(matches));
	}

	public String[] getMatches() {
		return matches.toArray(new String[matches.size()]);
	}
}
