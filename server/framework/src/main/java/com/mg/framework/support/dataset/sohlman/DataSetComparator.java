package com.mg.framework.support.dataset.sohlman;

import java.util.Comparator;

/**
 * Internal use
 * 
 * @author  Sampsa Sohlman
 * @version 2003-03-21
 */
final class DataSetComparator implements Comparator<RowContainer>
{
	private Comparator<Row> i_Comparator;
	/** Creates new DataSetComparator */
	public DataSetComparator(Comparator<Row> a_Comparator)
	{
		if(a_Comparator==null)
		{
			throw new NullPointerException("Null value not accepted");
		}
		i_Comparator = a_Comparator;
	}

	public int compare(RowContainer a_Object_1, RowContainer a_Object_2)
	{
		RowContainer l_RowContainer_1 = (RowContainer) a_Object_1;
		RowContainer l_RowContainer_2 = (RowContainer) a_Object_2;	
			
		return i_Comparator.compare(l_RowContainer_1.i_Row_Current, l_RowContainer_2.i_Row_Current);
	}

	public boolean equals(Object a_Object)
	{
		return i_Comparator.equals(a_Object);
	}
	
	public Comparator getComparator()
	{
		return i_Comparator;
	}
}
