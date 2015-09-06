package com.mg.merp.paymentalloc;

/**
 * Generated from IDL definition of struct "Config"
 *
 * @author JacORB IDL compiler
 */

public final class ConfigHolder
    implements org.omg.CORBA.portable.Streamable {
  public com.mg.merp.paymentalloc.Config value;

  public ConfigHolder() {
  }

  public ConfigHolder(final com.mg.merp.paymentalloc.Config initial) {
    value = initial;
  }

  public org.omg.CORBA.TypeCode _type() {
    return com.mg.merp.paymentalloc.ConfigHelper.type();
  }

  public void _read(final org.omg.CORBA.portable.InputStream _in) {
    value = com.mg.merp.paymentalloc.ConfigHelper.read(_in);
  }

  public void _write(final org.omg.CORBA.portable.OutputStream _out) {
    com.mg.merp.paymentalloc.ConfigHelper.write(_out, value);
  }
}
