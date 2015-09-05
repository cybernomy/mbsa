/**
 * ContextUtils.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import com.mg.framework.api.Logger;

/**
 * A static utility class for common JNDI operations.
 * 
 * @author Oleg V. Safonov
 * @version $Id: ContextUtils.java,v 1.1 2008/10/29 12:41:09 safonov Exp $
 */
public class ContextUtils {
	private static Logger logger = com.mg.framework.api.Logger.getLogger(ContextUtils.class);

	/** Bind val to name in ctx, and make sure that all intermediate contexts exist
	 * @param ctx, the parent JNDI Context under which value will be bound
	 * @param name, the name relative to ctx where value will be bound
	 * @param value, the value to bind.
	 */
	public static void bind(Context ctx, String name, Object value) throws NamingException {
		Name n = ctx.getNameParser("").parse(name);
		bind(ctx, n, value);
	}

	/** Bind val to name in ctx, and make sure that all intermediate contexts exist
	 * @param ctx, the parent JNDI Context under which value will be bound
	 * @param name, the name relative to ctx where value will be bound
	 * @param value, the value to bind.
	 */
	public static void bind(Context ctx, Name name, Object value) throws NamingException {
		int size = name.size();
		String atom = name.get(size - 1);
		Context parentCtx = createSubcontext(ctx, name.getPrefix(size - 1));
		parentCtx.bind(atom, value);
	}

	/** Create a subcontext including any intermediate contexts.
	 * @param ctx, the parent JNDI Context under which value will be bound
	 * @param name, the name relative to ctx of the subcontext.
	 * @return The new or existing JNDI subcontext
	 * @throws NamingException on any JNDI failure
	 */
	public static Context createSubcontext(Context ctx, Name name) throws NamingException {
		Context subctx = ctx;
		for (int pos = 0; pos < name.size(); pos++)	{
			String ctxName = name.get(pos);
			try	{
				subctx = (Context) ctx.lookup(ctxName);
			} catch (NameNotFoundException e) {
				subctx = ctx.createSubcontext(ctxName);
			}
			// The current subctx will be the ctx for the next name component
			ctx = subctx;
		}
		return subctx;
	}

	/** Unbinds a name from ctx, and removes parents if they are empty
	 * @param ctx, the parent JNDI Context under which the name will be unbound
	 * @param name, The name to unbind
	 */
	public static void unbind(Context ctx, String name) throws NamingException {
		unbind(ctx, ctx.getNameParser("").parse(name));
	}

	/** Unbinds a name from ctx, and removes parents if they are empty
	 * @param ctx, the parent JNDI Context under which the name will be unbound
	 * @param name, The name to unbind
	 */
	public static void unbind(Context ctx, Name name) throws NamingException {
		ctx.unbind(name); //unbind the end node in the name
		int sz = name.size();
		// walk the tree backwards, stopping at the domain
		while (--sz > 0) {
			Name pname = name.getPrefix(sz);
			try	{
				ctx.destroySubcontext(pname);
			} catch (NamingException e)	{
				logger.trace("Unable to remove context " + pname, e);
				break;
			}
		}
	}

	/**
	 * Lookup an object in the default initial context
	 * 
	 * @param name the name to lookup
	 * @param clazz the expected type
	 * @return the object
	 * @throws Exception for any error
	 */
	public static <T> T lookup(String name, Class<T> clazz) throws NamingException {
		InitialContext ctx = new InitialContext();
		try	{
			return lookup(ctx, name, clazz);
		} finally {
			ctx.close();
		}
	}

	/**
	 * Lookup an object in the default initial context
	 * 
	 * @param name the name to lookup
	 * @param clazz the expected type
	 * @return the object
	 * @throws Exception for any error
	 */
	public static <T> T lookup(Name name, Class<T> clazz) throws NamingException {
		InitialContext ctx = new InitialContext();
		try	{
			return lookup(ctx, name, clazz);
		} finally {
			ctx.close();
		}
	}

	/**
	 * Lookup an object in the given context
	 * 
	 * @param context the context
	 * @param name the name to lookup
	 * @param clazz the expected type
	 * @return the object
	 * @throws Exception for any error
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lookup(Context context, String name, Class<T> clazz) throws NamingException	{
		Object result = context.lookup(name);
		return (T) result;
	}

	/**
	 * Lookup an object in the given context
	 * 
	 * @param context the context
	 * @param name the name to lookup
	 * @param clazz the expected type
	 * @return the object
	 * @throws NamingException 
	 * @throws Exception for any error
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lookup(Context context, Name name, Class<T> clazz) throws NamingException {
		Object result = context.lookup(name);
		return (T) result;
	}

}
