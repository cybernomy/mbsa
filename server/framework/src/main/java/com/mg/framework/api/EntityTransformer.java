/* EntityTransformator.java
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
package com.mg.framework.api;

/**
 * ��������� �������������� ���������
 * S - ����� ������� ���������
 * D - ��� ���������� �������������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityTransformer.java,v 1.3 2007/09/21 09:53:23 safonov Exp $ 
 */
public interface EntityTransformer {

	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:service=EntityTransformerService";

	/**
	 * �������������� ��������
	 * 
	 * @param srcObj
	 * 			�������� ������
	 * @param dstClass
	 * 			�����, � ������� ������������� srcObj
	 * @param mapId
	 * 			������������� ��������
	 * @return ��������������� �����
	 */
	<S, D> D map(S srcObj, Class<D> dstClass, String mapId);
	
	/**
	 * �������������� ��������
	 * 
	 * @param srcObj
	 * 			�������� ������
	 * @param dstClass
	 * 			�����, � ������� ������������� srcObj
	 * @return ��������������� �����
	 */
	<S, D> D map(S srcObj, Class<D> dstClass);
	
	/**
	 * �������������� ��������
	 * 
	 * @param srcObj
	 * 			�������� ������
	 * @param dstObj
	 * 			������-�������
	 * @param mapId
	 * 			������������� ��������
	 */
	<S, D> void map(S srcObj, D dstObj, String mapId);
	
	/**
	 * �������������� ��������
	 * 
	 * @param srcObj
	 * 			�������� ������
	 * @param dstObj
	 * 			������-�������
	 */
	<S, D> void map(S srcObj, D dstObj);
	
	/**
	 * ���������� ������ �������� �������������� ���������
	 *
	 */
	void rebuildMapping();

}
