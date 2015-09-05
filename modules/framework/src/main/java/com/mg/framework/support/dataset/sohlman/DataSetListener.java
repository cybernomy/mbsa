package com.mg.framework.support.dataset.sohlman;

import java.util.EventListener;


/**
 *
 * @author  Sampsa Sohlman
 * @version 2001-10-22
 */
public interface DataSetListener extends EventListener
{
	public void dataSetChanged(DataSetEvent a_DataSetEvent);
}
