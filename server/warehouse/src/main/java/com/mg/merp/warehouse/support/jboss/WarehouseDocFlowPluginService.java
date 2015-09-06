/* WarehouseDocFlowPluginService.java
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
package com.mg.merp.warehouse.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.warehouse.support.CalcPricesByWarehouseDataDocFlowPluginFactory;
import com.mg.merp.warehouse.support.CustomsDeclarationByStockBachDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseAssembleProductDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseDisAssembleProductDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseInteractiveTransactionCalcCostDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseInteractiveTransactionDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehousePlanTransactionDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehousePlanWithdrawalTransactionDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseReservTransactionDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseReservWithdrawalTransactionDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseTransactionCalcCostDocFlowPluginFactory;
import com.mg.merp.warehouse.support.WarehouseTransactionDocFlowPluginFactory;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Склад"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseDocFlowPluginService.java,v 1.7 2008/08/27 09:41:48 sharapov Exp $
 */
@Service(objectName = WarehouseDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(WarehouseDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class WarehouseDocFlowPluginService extends ServiceMBeanSupport
    implements WarehouseDocFlowPluginServiceMBean {

  private DocFlowPluginFactory warehouseTransactionDocFlowPluginFactory;
  private DocFlowPluginFactory warehouseTransactionCalcCostDocFlowPluginFactory;
  private DocFlowPluginFactory warehouseInteractiveTransactionDocFlowPluginFactory;
  private DocFlowPluginFactory warehouseInteractiveTransactionCalcCostDocFlowPluginFactory;

  private DocFlowPluginFactory warehousePlanTransactionDocFlowPluginFactory;
  private DocFlowPluginFactory warehousePlanWithdrawalTransactionDocFlowPluginFactory;

  private DocFlowPluginFactory warehouseReservTransactionDocFlowPluginFactory;
  private DocFlowPluginFactory warehouseReservWithdrawalTransactionDocFlowPluginFactory;

  private DocFlowPluginFactory calcPricesByWarehouseDataDocFlowPluginFactory;

  private DocFlowPluginFactory warehouseAssembleProductDocFlowPluginFactory;
  private DocFlowPluginFactory warehouseDisAssembleProductDocFlowPluginFactory;

  private DocFlowPluginFactory customsDeclarationByStockBachDocFlowPluginFactory;


  protected void createService() throws Exception {
    warehouseTransactionDocFlowPluginFactory = new WarehouseTransactionDocFlowPluginFactory();
    warehouseTransactionCalcCostDocFlowPluginFactory = new WarehouseTransactionCalcCostDocFlowPluginFactory();
    warehouseInteractiveTransactionDocFlowPluginFactory = new WarehouseInteractiveTransactionDocFlowPluginFactory();
    warehouseInteractiveTransactionCalcCostDocFlowPluginFactory = new WarehouseInteractiveTransactionCalcCostDocFlowPluginFactory();

    warehousePlanTransactionDocFlowPluginFactory = new WarehousePlanTransactionDocFlowPluginFactory();
    warehousePlanWithdrawalTransactionDocFlowPluginFactory = new WarehousePlanWithdrawalTransactionDocFlowPluginFactory();

    warehouseReservTransactionDocFlowPluginFactory = new WarehouseReservTransactionDocFlowPluginFactory();
    warehouseReservWithdrawalTransactionDocFlowPluginFactory = new WarehouseReservWithdrawalTransactionDocFlowPluginFactory();

    calcPricesByWarehouseDataDocFlowPluginFactory = new CalcPricesByWarehouseDataDocFlowPluginFactory();

    warehouseAssembleProductDocFlowPluginFactory = new WarehouseAssembleProductDocFlowPluginFactory();
    warehouseDisAssembleProductDocFlowPluginFactory = new WarehouseDisAssembleProductDocFlowPluginFactory();

    customsDeclarationByStockBachDocFlowPluginFactory = new CustomsDeclarationByStockBachDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseTransactionCalcCostDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseInteractiveTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseInteractiveTransactionCalcCostDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehousePlanTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehousePlanWithdrawalTransactionDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseReservTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseReservWithdrawalTransactionDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(calcPricesByWarehouseDataDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseAssembleProductDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(warehouseDisAssembleProductDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(customsDeclarationByStockBachDocFlowPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseTransactionCalcCostDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseInteractiveTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseInteractiveTransactionCalcCostDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehousePlanTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehousePlanWithdrawalTransactionDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseReservTransactionDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseReservWithdrawalTransactionDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(calcPricesByWarehouseDataDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseAssembleProductDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(warehouseDisAssembleProductDocFlowPluginFactory);

    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(customsDeclarationByStockBachDocFlowPluginFactory);
  }

  protected void destroyService() throws Exception {
    warehouseTransactionDocFlowPluginFactory = null;
    warehouseTransactionCalcCostDocFlowPluginFactory = null;
    warehouseInteractiveTransactionDocFlowPluginFactory = null;
    warehouseInteractiveTransactionCalcCostDocFlowPluginFactory = null;

    warehousePlanTransactionDocFlowPluginFactory = null;
    warehousePlanWithdrawalTransactionDocFlowPluginFactory = null;

    warehouseReservTransactionDocFlowPluginFactory = null;
    warehouseReservWithdrawalTransactionDocFlowPluginFactory = null;

    calcPricesByWarehouseDataDocFlowPluginFactory = null;

    warehouseAssembleProductDocFlowPluginFactory = null;
    warehouseDisAssembleProductDocFlowPluginFactory = null;

    customsDeclarationByStockBachDocFlowPluginFactory = null;
  }

}
