package com.mg.merp.paymentalloc;

/**
 *	Generated from IDL definition of struct "Config"
 *	@author JacORB IDL compiler 
 */

public final class Config
	implements org.omg.CORBA.portable.IDLEntity
{
	public Config(){}
	public java.lang.String BaseCurrency = "";
	public java.lang.String NatCurrency = "";
	public int CurrencyPrecision;
	public int CurRateType;
	public int CurRateAutor;
	public Config(java.lang.String BaseCurrency, java.lang.String NatCurrency, int CurrencyPrecision, int CurRateType, int CurRateAutor)
	{
		this.BaseCurrency = BaseCurrency;
		this.NatCurrency = NatCurrency;
		this.CurrencyPrecision = CurrencyPrecision;
		this.CurRateType = CurRateType;
		this.CurRateAutor = CurRateAutor;
	}
}
