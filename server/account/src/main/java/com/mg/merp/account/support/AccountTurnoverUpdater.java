/*
 * AccountTurnoverUpdater.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.jdbc.CallableStatementCreator;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.merp.account.model.EconomicOper;

/**
 * Класс обертка изменения оборотных ведомостей
 * 
 * @author Oleg V. Safonov
 * @version $Id: AccountTurnoverUpdater.java,v 1.1 2007/01/16 11:45:51 safonov Exp $
 */
public class AccountTurnoverUpdater {

	private static class OperInfo {
		Timestamp keepDate;
		Integer fromId;
		Integer toId;
		String baseDocType;
		String baseDocNumber;
		Timestamp baseDocDate;
		String confirmDocType;
		String confirmDocNumber;
		Timestamp confirmDocDate;
		String contractType;
		String contractNumber;
		Timestamp contractDate;
		String specMark;
	}
	
	private static OperInfo loadEconomicOper(int operId) {
		List<OperInfo> list = JdbcTemplate.getInstance().query("select keepdate, from_id, to_id, docbasetype, docbasenumber, docbasedate, doctype, docnumber, docdate, contracttype, contractnumber, contractdate, specmark from economicoper where id = ?", new Object[] {operId}, new RowMapper<OperInfo>() {

			public OperInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				OperInfo result = new OperInfo();
				result.keepDate = rs.getTimestamp("keepdate");
				result.fromId = (Integer) rs.getObject("from_id");
				result.toId = (Integer) rs.getObject("to_id");
				result.baseDocType = rs.getString("docbasetype");
				result.baseDocNumber = rs.getString("docbasenumber");
				result.baseDocDate = rs.getTimestamp("docbasedate");
				result.confirmDocType = rs.getString("doctype");
				result.confirmDocNumber = rs.getString("docnumber");
				result.confirmDocDate = rs.getTimestamp("docdate");
				result.contractType = rs.getString("contracttype");
				result.contractNumber = rs.getString("contractnumber");
				result.contractDate = rs.getTimestamp("contractdate");
				result.specMark = rs.getString("specmark");
				return result;
			}
			
		});
		if (list.isEmpty())
			throw new IllegalArgumentException();
		return list.get(0);
	}
	
	private static void updateTurnover(final int economicOperId, final OperInfo oper, final boolean isOperRemoving) {
		JdbcTemplate.getInstance().call(new CallableStatementCreator() {

			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement result = con.prepareCall("{call ACC_UPDATE_ALL_REMN(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
				result.setInt(1, economicOperId);
				result.setTimestamp(2, oper.keepDate);
				result.setShort(3, isOperRemoving ? (short) 1 : (short) 0);
				result.setObject(4, oper.fromId);
				result.setObject(5, oper.toId);
				result.setString(6, oper.baseDocType);
				result.setString(7, oper.baseDocNumber);
				result.setTimestamp(8, oper.baseDocDate);
				result.setString(9, oper.confirmDocType);
				result.setString(10, oper.confirmDocNumber);
				result.setTimestamp(11, oper.confirmDocDate);
				result.setString(12, oper.contractType);
				result.setString(13, oper.contractNumber);
				result.setTimestamp(14, oper.contractDate);
				result.setString(15, oper.specMark);
				return result;
			}
			
		}
		, new ArrayList<Object>());
	}
	
	/*public static void execute(final EconomicOper economicOper, final boolean remove) {
		JdbcTemplate.getInstance().call(new CallableStatementCreator() {

			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement result = con.prepareCall("{call ACC_UPDATE_ALL_REMN(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
				result.setInt(1, economicOper.getId());
				result.setTimestamp(2, new Timestamp(economicOper.getKeepDate().getTime()));
				result.setShort(3, remove ? (short) 1 : (short) 0);
				result.setObject(4, economicOper.getFrom() != null ? economicOper.getFrom().getId() : null);
				result.setObject(5, economicOper.getTo() != null ? economicOper.getTo().getId() : null);
				result.setString(6, economicOper.getBaseDocType() != null ? economicOper.getBaseDocType().getUpCode() : null);
				result.setString(7, economicOper.getBaseDocNumber());
				result.setTimestamp(8, economicOper.getBaseDocDate() != null ? new Timestamp(economicOper.getBaseDocDate().getTime()) : null);
				result.setString(9, economicOper.getConfirmDocType() != null ? economicOper.getConfirmDocType().getUpCode() : null);
				result.setString(10, economicOper.getConfirmDocNumber());
				result.setTimestamp(11, economicOper.getConfirmDocDate() != null ? new Timestamp(economicOper.getConfirmDocDate().getTime()) : null);
				result.setString(12, economicOper.getContractType() != null ? economicOper.getContractType().getUpCode() : null);
				result.setString(13, economicOper.getContractNumber());
				result.setTimestamp(14, economicOper.getContractDate() != null ? new Timestamp(economicOper.getContractDate().getTime()) : null);
				result.setString(15, economicOper.getSpecMark() != null ? economicOper.getSpecMark().getUpCode() : null);
				return result;
			}
			
		}
		, new ArrayList<Object>());

	}*/

	/**
	 * запуск изменения оборотных ведомостей
	 * 
	 * @param economicOper ХО
	 * @param isOperRemoving признак удаления ХО, необходимо установить в <code>true</code> если ХО удаляется
	 */
	public static void execute(final EconomicOper economicOper, final boolean isOperRemoving) {
		OperInfo oper = new OperInfo();
		oper.keepDate = new Timestamp(economicOper.getKeepDate().getTime());
		oper.fromId = economicOper.getFrom() != null ? economicOper.getFrom().getId() : null;
		oper.toId = economicOper.getTo() != null ? economicOper.getTo().getId() : null;
		oper.baseDocType = economicOper.getBaseDocType() != null ? economicOper.getBaseDocType().getUpCode() : null;
		oper.baseDocNumber = economicOper.getBaseDocNumber();
		oper.baseDocDate = economicOper.getBaseDocDate() != null ? new Timestamp(economicOper.getBaseDocDate().getTime()) : null;
		oper.confirmDocType = economicOper.getConfirmDocType() != null ? economicOper.getConfirmDocType().getUpCode() : null;
		oper.confirmDocNumber = economicOper.getConfirmDocNumber();
		oper.confirmDocDate = economicOper.getConfirmDocDate() != null ? new Timestamp(economicOper.getConfirmDocDate().getTime()) : null;
		oper.contractType = economicOper.getContractType() != null ? economicOper.getContractType().getUpCode() : null;
		oper.contractNumber = economicOper.getContractNumber();
		oper.contractDate = economicOper.getContractDate() != null ? new Timestamp(economicOper.getContractDate().getTime()) : null;
		oper.specMark = economicOper.getSpecMark() != null ? economicOper.getSpecMark().getUpCode() : null;
		updateTurnover(economicOper.getId(), oper, isOperRemoving);
	}
	
	/**
	 * запуск изменения оборотных ведомостей
	 * 
	 * @param economicOperId идентификатор ХО
	 * @param isOperRemoving признак удаления ХО, необходимо установить в <code>true</code> если ХО удаляется
	 */
	public static void execute(int economicOperId, boolean isOperRemoving) {
		updateTurnover(economicOperId, loadEconomicOper(economicOperId), isOperRemoving);
	}

}
