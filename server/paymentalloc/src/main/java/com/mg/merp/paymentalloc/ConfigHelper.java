package com.mg.merp.paymentalloc;


/**
 * Generated from IDL definition of struct "Config"
 *
 * @author JacORB IDL compiler
 */

public final class ConfigHelper {
  private static org.omg.CORBA.TypeCode _type = null;

  public static org.omg.CORBA.TypeCode type() {
    if (_type == null) {
      _type = org.omg.CORBA.ORB.init().create_struct_tc(com.mg.merp.paymentalloc.ConfigHelper.id(), "Config", new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("BaseCurrency", org.omg.CORBA.ORB.init().create_wstring_tc(0), null), new org.omg.CORBA.StructMember("NatCurrency", org.omg.CORBA.ORB.init().create_wstring_tc(0), null), new org.omg.CORBA.StructMember("CurrencyPrecision", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null), new org.omg.CORBA.StructMember("CurRateType", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null), new org.omg.CORBA.StructMember("CurRateAutor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
    }
    return _type;
  }

  public static void insert(final org.omg.CORBA.Any any, final com.mg.merp.paymentalloc.Config s) {
    any.type(type());
    write(any.create_output_stream(), s);
  }

  public static com.mg.merp.paymentalloc.Config extract(final org.omg.CORBA.Any any) {
    return read(any.create_input_stream());
  }

  public static String id() {
    return "IDL:com/mg/merp/paymentalloc/Config:1.0";
  }

  public static com.mg.merp.paymentalloc.Config read(final org.omg.CORBA.portable.InputStream in) {
    com.mg.merp.paymentalloc.Config result = new com.mg.merp.paymentalloc.Config();
    result.BaseCurrency = in.read_wstring();
    result.NatCurrency = in.read_wstring();
    result.CurrencyPrecision = in.read_long();
    result.CurRateType = in.read_long();
    result.CurRateAutor = in.read_long();
    return result;
  }

  public static void write(final org.omg.CORBA.portable.OutputStream out, final com.mg.merp.paymentalloc.Config s) {
    out.write_wstring(s.BaseCurrency);
    out.write_wstring(s.NatCurrency);
    out.write_long(s.CurrencyPrecision);
    out.write_long(s.CurRateType);
    out.write_long(s.CurRateAutor);
  }
}
