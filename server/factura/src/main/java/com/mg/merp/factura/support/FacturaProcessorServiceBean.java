/*
 * FacturaProcessorServiceBean.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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

package com.mg.merp.factura.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.BuyBookServiceLocal;
import com.mg.merp.account.SaleBookServiceLocal;
import com.mg.merp.account.model.AccConfig;
import com.mg.merp.account.model.BuyBook;
import com.mg.merp.account.model.SaleBook;
import com.mg.merp.account.support.ConfigurationHelper;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecTax;
import com.mg.merp.factura.FacturaProcessorServiceLocal;
import com.mg.merp.factura.model.FacturaHead;
import com.mg.merp.reference.model.TaxForm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Реализация процессора счетов-фактур
 *
 * @author Artem V. Sharapov
 * @version $Id: FacturaProcessorServiceBean.java,v 1.4 2009/03/16 14:30:34 sharapov Exp $
 */
@Stateful(name = "merp/factura/FacturaProcessorService") //$NON-NLS-1$
public class FacturaProcessorServiceBean extends AbstractPOJOBusinessObjectStatefulServiceBean implements FacturaProcessorServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#registerFactura(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void registerFactura(DocFlowPluginInvokeParams params) {
    doRegisterFactura(params);
  }

  protected void doRegisterFactura(DocFlowPluginInvokeParams params) {
    FacturaHead facturaHead = (FacturaHead) params.getDocument();
    facturaHead.setInDate(params.getProcessDate());
    storeFacturaHead(facturaHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#rollBackRegisterFactura(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void rollBackRegisterFactura(DocFlowPluginInvokeParams params) {
    doRollBackRegisterFactura(params);
  }

  protected void doRollBackRegisterFactura(DocFlowPluginInvokeParams params) {
    FacturaHead facturaHead = (FacturaHead) params.getDocument();
    facturaHead.setInDate(null);
    storeFacturaHead(facturaHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#registerStockDate(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void registerStockDate(DocFlowPluginInvokeParams params) {
    doRegisterStockDate(params);
  }

  protected void doRegisterStockDate(DocFlowPluginInvokeParams params) {
    FacturaHead facturaHead = (FacturaHead) params.getDocument();
    facturaHead.setStockDate(params.getProcessDate());
    storeFacturaHead(facturaHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#rollBackRegisterStockDate(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void rollBackRegisterStockDate(DocFlowPluginInvokeParams params) {
    doRollBackRegisterStockDate(params);
  }

  protected void doRollBackRegisterStockDate(DocFlowPluginInvokeParams params) {
    FacturaHead facturaHead = (FacturaHead) params.getDocument();
    facturaHead.setStockDate(null);
    storeFacturaHead(facturaHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#registerPayDate(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void registerPayDate(DocFlowPluginInvokeParams params) {
    doRegisterPayDate(params);
  }

  protected void doRegisterPayDate(DocFlowPluginInvokeParams params) {
    FacturaHead facturaHead = (FacturaHead) params.getDocument();
    facturaHead.setPayDate(params.getProcessDate());
    storeFacturaHead(facturaHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#rollBackRegisterPayDate(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void rollBackRegisterPayDate(DocFlowPluginInvokeParams params) {
    doRollBackRegisterPayDate(params);
  }

  protected void doRollBackRegisterPayDate(DocFlowPluginInvokeParams params) {
    FacturaHead facturaHead = (FacturaHead) params.getDocument();
    facturaHead.setPayDate(null);
    storeFacturaHead(facturaHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#registerInBuyBook(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void registerInBuyBook(DocFlowPluginInvokeParams params) {
    doRegisterInBuyBook(params);
  }

  protected void doRegisterInBuyBook(DocFlowPluginInvokeParams params) {
    BuyBook buyBook = null;
    Folder folder = params.getPerformedStage().getLinkDocDestFolder();
    Date processDate = params.getProcessDate();
    List<DocumentSpecItem> documentSpecItemList = params.getSpecList();
    for (DocumentSpecItem documentSpecItem : documentSpecItemList) {
      DocSpec docSpec = documentSpecItem.getDocSpec();
      buyBook = doRegisterFacturaInBuyBook((FacturaHead) docSpec.getDocHead(), docSpec, folder, processDate, documentSpecItem.getPerformedSum(), buyBook);
    }
    if (buyBook != null && !documentSpecItemList.isEmpty()) {
      AccConfig config = ConfigurationHelper.getConfiguration();
      if (config != null)
        doRoundBuyBook(buyBook, config.getCurrencyPrec());
      BuyBookServiceLocal buyBookService = (BuyBookServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/BuyBook");
      buyBookService.create(buyBook);
      params.setHeadStateValue(buyBook.getId());
    }
  }

  /**
   * Регистрация счета-фактуры в книге покупок
   *
   * @param docHead     - счет-фактура
   * @param docSpec     - позиция документа
   * @param dstFolder   - папка-приемник
   * @param processDate - дата регистрации в книге покупок
   * @param sum         - сумма позиции документа
   * @param book        - книга покупок
   * @return книга покупок
   */
  protected BuyBook doRegisterFacturaInBuyBook(FacturaHead docHead, DocSpec docSpec, Folder dstFolder, Date processDate, BigDecimal sum, BuyBook book) {
    final RoundContext RC = new RoundContext(4);
    BigDecimal specSum = docSpec.getSumma();
    if (MathUtils.compareToZeroOrNull(specSum) == 0)
      return null;

    BigDecimal f = MathUtils.divide(sum, specSum, RC);
    BigDecimal nsp = BigDecimal.ZERO;
    BigDecimal sumWO10 = BigDecimal.ZERO;
    BigDecimal sumWO18 = BigDecimal.ZERO;
    BigDecimal sumWO20 = BigDecimal.ZERO;
    BigDecimal nds10 = BigDecimal.ZERO;
    BigDecimal nds18 = BigDecimal.ZERO;
    BigDecimal nds20 = BigDecimal.ZERO;
    BigDecimal notTaxable = specSum;
    final BigDecimal EIGHTEEN = new BigDecimal(BigInteger.valueOf(18), 0);
    final BigDecimal TWENTY = new BigDecimal(BigInteger.valueOf(20), 0);

    for (TaxData taxData : getTaxData(docSpec)) {
      switch (taxData.taxForm) {
        case NDS:
          if (BigDecimal.TEN.compareTo(taxData.dRate) == 0) {
            nds10 = nds10.add(taxData.sumTax);
            sumWO10 = MathUtils.add(sumWO10, MathUtils.subtract(specSum, taxData.sumTax, RC), RC);
          } else if (EIGHTEEN.compareTo(taxData.dRate) == 0) {
            nds18 = nds18.add(taxData.sumTax);
            sumWO18 = MathUtils.add(sumWO18, MathUtils.subtract(specSum, taxData.sumTax, RC), RC);
          } else if (TWENTY.compareTo(taxData.dRate) == 0) {
            nds20 = nds20.add(taxData.sumTax);
            sumWO20 = MathUtils.add(sumWO18, MathUtils.subtract(specSum, taxData.sumTax, RC), RC);
          }
          notTaxable = BigDecimal.ZERO;
          break;
        case OTHER:
          nsp = nsp.add(taxData.sumTax);
          if (MathUtils.compareToZeroOrNull(sumWO10) != 0)
            sumWO10 = MathUtils.subtract(sumWO10, taxData.sumTax, RC);
          else if (MathUtils.compareToZeroOrNull(sumWO20) != 0)
            sumWO20 = MathUtils.subtract(sumWO20, taxData.sumTax, RC);
          else if (MathUtils.compareToZeroOrNull(sumWO18) != 0)
            sumWO18 = MathUtils.subtract(sumWO18, taxData.sumTax, RC);
          notTaxable = BigDecimal.ZERO;
          break;
      }
    }
    nds10 = MathUtils.multiply(nds10, f, RC);
    nds20 = MathUtils.multiply(nds20, f, RC);
    nds18 = MathUtils.multiply(nds18, f, RC);
    sumWO10 = MathUtils.multiply(sumWO10, f, RC);
    sumWO20 = MathUtils.multiply(sumWO20, f, RC);
    sumWO18 = MathUtils.multiply(sumWO18, f, RC);
    nsp = MathUtils.multiply(nsp, f, RC);
    notTaxable = MathUtils.multiply(notTaxable, f, RC);

    if (book == null) {
      BuyBookServiceLocal buyBookService = (BuyBookServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/BuyBook");
      book = buyBookService.initialize();
      book.setFolder(dstFolder);
      book.setDocHead(docHead);
      book.setDocType(docHead.getDocType());
      book.setDocNumber(docHead.getDocNumber());
      book.setDocDate(docHead.getDocDate());
      book.setInsertDate(processDate);
      book.setInDate(docHead.getInDate());
      book.setStockDate(docHead.getStockDate());
      book.setPayDate(docHead.getPayDate());
      book.setProvider(docHead.getFrom());
      book.setTotalSum(sum);
      book.setSumWithoutNds1(sumWO20);
      book.setSumWithoutNds2(sumWO10);
      book.setSumWithoutNds3(sumWO18);
      book.setNds1Sum(nds20);
      book.setNds2Sum(nds10);
      book.setNds3Sum(nds18);
      book.setNotTaxableSum(notTaxable);
      book.setNspSum(nsp);
    } else {
      book.setTotalSum(MathUtils.addNullable(book.getTotalSum(), sum, RC));
      book.setSumWithoutNds1(MathUtils.addNullable(book.getSumWithoutNds1(), sumWO20, RC));
      book.setSumWithoutNds2(MathUtils.addNullable(book.getSumWithoutNds2(), sumWO10, RC));
      book.setSumWithoutNds3(MathUtils.addNullable(book.getSumWithoutNds3(), sumWO18, RC));
      book.setNds1Sum(MathUtils.addNullable(book.getNds1Sum(), nds20, RC));
      book.setNds2Sum(MathUtils.addNullable(book.getNds2Sum(), nds10, RC));
      book.setNds3Sum(MathUtils.addNullable(book.getNds3Sum(), nds18, RC));
      book.setNotTaxableSum(MathUtils.addNullable(book.getNotTaxableSum(), notTaxable, RC));
      book.setNspSum(MathUtils.addNullable(book.getNspSum(), nsp, RC));
    }
    return book;
  }

  /**
   * Выполнить округление сумм книги покупок
   *
   * @param book      - книга покупок
   * @param roundPrec - точность округления
   */
  protected void doRoundBuyBook(BuyBook book, Integer roundPrec) {
    if (book != null && roundPrec != null) {
      final RoundContext RC = new RoundContext(roundPrec);
      book.setTotalSum(MathUtils.round(book.getTotalSum(), RC));
      book.setSumWithoutNds1(MathUtils.round(book.getSumWithoutNds1(), RC));
      book.setSumWithoutNds2(MathUtils.round(book.getSumWithoutNds2(), RC));
      book.setSumWithoutNds3(MathUtils.round(book.getSumWithoutNds3(), RC));
      book.setNds1Sum(MathUtils.round(book.getNds1Sum(), RC));
      book.setNds2Sum(MathUtils.round(book.getNds2Sum(), RC));
      book.setNds3Sum(MathUtils.round(book.getNds3Sum(), RC));
      book.setNotTaxableSum(MathUtils.round(book.getNotTaxableSum(), RC));
      book.setNspSum(MathUtils.round(book.getNspSum(), RC));
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#rollBackRegisterInBuyBook(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void rollBackRegisterInBuyBook(DocFlowPluginInvokeParams params) {
    doRollBackRegisterInBuyBook(params);
  }

  protected void doRollBackRegisterInBuyBook(DocFlowPluginInvokeParams params) {
    Integer bookId = (Integer) params.getHeadStateValue();
    if (bookId != null) {
      BuyBookServiceLocal buyBookService = (BuyBookServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/BuyBook");
      buyBookService.erase(bookId);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#registerInSaleBook(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void registerInSaleBook(DocFlowPluginInvokeParams params) {
    doRegisterInSaleBook(params);
  }

  protected void doRegisterInSaleBook(DocFlowPluginInvokeParams params) {
    SaleBook saleBook = null;
    Folder folder = params.getPerformedStage().getLinkDocDestFolder();
    Date processDate = params.getProcessDate();
    List<DocumentSpecItem> documentSpecItemList = params.getSpecList();
    for (DocumentSpecItem documentSpecItem : documentSpecItemList) {
      DocSpec docSpec = documentSpecItem.getDocSpec();
      saleBook = doRegisterFacturaInSaleBook((FacturaHead) docSpec.getDocHead(), docSpec, folder, processDate, documentSpecItem.getPerformedSum(), saleBook);
    }
    if (saleBook != null && !documentSpecItemList.isEmpty()) {
      AccConfig config = ConfigurationHelper.getConfiguration();
      if (config != null)
        doRoundSaleBook(saleBook, config.getCurrencyPrec());
      SaleBookServiceLocal saleBookService = (SaleBookServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/SaleBook");
      saleBookService.create(saleBook);
      params.setHeadStateValue(saleBook.getId());
    }
  }

  /**
   * Регистрация счета-фактуры в книге покупок
   *
   * @param docHead     - счет-фактура
   * @param docSpec     - позиция документа
   * @param dstFolder   - папка-приемник
   * @param processDate - дата регистрации в книге покупок
   * @param sum         - сумма позиции документа
   * @param book        - книга покупок
   * @return книга покупок
   */
  protected SaleBook doRegisterFacturaInSaleBook(FacturaHead docHead, DocSpec docSpec, Folder dstFolder, Date processDate, BigDecimal sum, SaleBook book) {
    final RoundContext RC = new RoundContext(4);
    BigDecimal specSum = docSpec.getSumma();
    if (MathUtils.compareToZeroOrNull(specSum) == 0)
      return null;

    BigDecimal f = MathUtils.divide(sum, specSum, RC);
    BigDecimal nsp = BigDecimal.ZERO;
    BigDecimal sumWO10 = BigDecimal.ZERO;
    BigDecimal sumWO18 = BigDecimal.ZERO;
    BigDecimal sumWO20 = BigDecimal.ZERO;
    BigDecimal nds10 = BigDecimal.ZERO;
    BigDecimal nds18 = BigDecimal.ZERO;
    BigDecimal nds20 = BigDecimal.ZERO;
    BigDecimal notTaxable = specSum;
    final BigDecimal EIGHTEEN = new BigDecimal(BigInteger.valueOf(18), 0);
    final BigDecimal TWENTY = new BigDecimal(BigInteger.valueOf(20), 0);

    for (TaxData taxData : getTaxData(docSpec)) {
      switch (taxData.taxForm) {
        case NDS:
          if (BigDecimal.TEN.compareTo(taxData.dRate) == 0) {
            nds10 = nds10.add(taxData.sumTax);
            sumWO10 = MathUtils.add(sumWO10, MathUtils.subtract(specSum, taxData.sumTax, RC), RC);
          } else if (EIGHTEEN.compareTo(taxData.dRate) == 0) {
            nds18 = nds18.add(taxData.sumTax);
            sumWO18 = MathUtils.add(sumWO18, MathUtils.subtract(specSum, taxData.sumTax, RC), RC);
          } else if (TWENTY.compareTo(taxData.dRate) == 0) {
            nds20 = nds20.add(taxData.sumTax);
            sumWO20 = MathUtils.add(sumWO18, MathUtils.subtract(specSum, taxData.sumTax, RC), RC);
          }
          notTaxable = BigDecimal.ZERO;
          break;
        case OTHER:
          nsp = nsp.add(taxData.sumTax);
          if (MathUtils.compareToZeroOrNull(sumWO10) != 0)
            sumWO10 = MathUtils.subtract(sumWO10, taxData.sumTax, RC);
          else if (MathUtils.compareToZeroOrNull(sumWO20) != 0)
            sumWO20 = MathUtils.subtract(sumWO20, taxData.sumTax, RC);
          else if (MathUtils.compareToZeroOrNull(sumWO18) != 0)
            sumWO18 = MathUtils.subtract(sumWO18, taxData.sumTax, RC);
          notTaxable = BigDecimal.ZERO;
          break;
      }
    }
    nds10 = MathUtils.multiply(nds10, f, RC);
    nds20 = MathUtils.multiply(nds20, f, RC);
    nds18 = MathUtils.multiply(nds18, f, RC);
    sumWO10 = MathUtils.multiply(sumWO10, f, RC);
    sumWO20 = MathUtils.multiply(sumWO20, f, RC);
    sumWO18 = MathUtils.multiply(sumWO18, f, RC);
    nsp = MathUtils.multiply(nsp, f, RC);
    notTaxable = MathUtils.multiply(notTaxable, f, RC);

    if (book == null) {
      SaleBookServiceLocal saleBookService = (SaleBookServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/SaleBook");
      book = saleBookService.initialize();
      book.setFolder(dstFolder);
      book.setDocHead(docHead);
      book.setDocType(docHead.getDocType());
      book.setDocNumber(docHead.getDocNumber());
      book.setDocDate(docHead.getDocDate());
      book.setInsertDate(processDate);
      book.setPayDate(docHead.getPayDate());
      book.setCustomer(docHead.getTo());
      book.setTotalSum(sum);
      book.setSumWithoutNds1(sumWO20);
      book.setSumWithoutNds2(sumWO10);
      book.setSumWithoutNds3(sumWO18);
      book.setNds1Sum(nds20);
      book.setNds2Sum(nds10);
      book.setNds3Sum(nds18);
      book.setNotTaxableSum(notTaxable);
      book.setNspSum(nsp);
    } else {
      book.setTotalSum(MathUtils.addNullable(book.getTotalSum(), sum, RC));
      book.setSumWithoutNds1(MathUtils.addNullable(book.getSumWithoutNds1(), sumWO20, RC));
      book.setSumWithoutNds2(MathUtils.addNullable(book.getSumWithoutNds2(), sumWO10, RC));
      book.setSumWithoutNds3(MathUtils.addNullable(book.getSumWithoutNds3(), sumWO18, RC));
      book.setNds1Sum(MathUtils.addNullable(book.getNds1Sum(), nds20, RC));
      book.setNds2Sum(MathUtils.addNullable(book.getNds2Sum(), nds10, RC));
      book.setNds3Sum(MathUtils.addNullable(book.getNds3Sum(), nds18, RC));
      book.setNotTaxableSum(MathUtils.addNullable(book.getNotTaxableSum(), notTaxable, RC));
      book.setNspSum(MathUtils.addNullable(book.getNspSum(), nsp, RC));
    }
    return book;
  }

  /**
   * Выполнить округление сумм книги продаж
   *
   * @param book      - книга продаж
   * @param roundPrec - точность округления
   */
  protected void doRoundSaleBook(SaleBook book, Integer roundPrec) {
    if (book != null && roundPrec != null) {
      final RoundContext RC = new RoundContext(roundPrec);
      book.setTotalSum(MathUtils.round(book.getTotalSum(), RC));
      book.setSumWithoutNds1(MathUtils.round(book.getSumWithoutNds1(), RC));
      book.setSumWithoutNds2(MathUtils.round(book.getSumWithoutNds2(), RC));
      book.setSumWithoutNds3(MathUtils.round(book.getSumWithoutNds3(), RC));
      book.setNds1Sum(MathUtils.round(book.getNds1Sum(), RC));
      book.setNds2Sum(MathUtils.round(book.getNds2Sum(), RC));
      book.setNds3Sum(MathUtils.round(book.getNds3Sum(), RC));
      book.setNotTaxableSum(MathUtils.round(book.getNotTaxableSum(), RC));
      book.setNspSum(MathUtils.round(book.getNspSum(), RC));
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.factura.FacturaProcessorServiceLocal#rollBackRegisterInSaleBook(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @Remove
  @PermitAll
  public void rollBackRegisterInSaleBook(DocFlowPluginInvokeParams params) {
    doRollBackRegisterInSaleBook(params);
  }

  protected void doRollBackRegisterInSaleBook(DocFlowPluginInvokeParams params) {
    Integer bookId = (Integer) params.getHeadStateValue();
    if (bookId != null) {
      SaleBookServiceLocal saleBookService = (SaleBookServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/SaleBook");
      saleBookService.erase(bookId);
    }
  }

  /**
   * Получить данные о налогах для позиции документа
   *
   * @param docSpec - позиция документа
   * @return данные о налогах
   */
  private List<TaxData> getTaxData(DocSpec docSpec) {
    return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DocumentSpecTax.class, "dst")
        .setProjection(Projections.projectionList(
            Projections.property("dst.SumElement"),
            Projections.property("tax.DirectRate"),
            Projections.property("tax.TaxForm")))
        .createAlias("dst.Tax", "tax")
        .add(Restrictions.eq("DocSpec", docSpec))
        .setFlushMode(FlushMode.MANUAL)
        .setResultTransformer(new ResultTransformer<TaxData>() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
           */
          public TaxData transformTuple(Object[] tuple, String[] aliases) {
            return new TaxData((BigDecimal) tuple[0], (BigDecimal) tuple[1], (TaxForm) tuple[2]);
          }
        }));
  }

  private void storeFacturaHead(FacturaHead facturaHead) {
    ServerUtils.getPersistentManager().merge(facturaHead);
  }

  /**
   * Элемент данных о налогах позиции документа
   */
  private class TaxData {

    BigDecimal sumTax;
    BigDecimal dRate;
    TaxForm taxForm;

    public TaxData(BigDecimal sumTax, BigDecimal dRate, TaxForm taxForm) {
      this.sumTax = sumTax;
      this.dRate = dRate;
      this.taxForm = taxForm;
    }

  }

}
