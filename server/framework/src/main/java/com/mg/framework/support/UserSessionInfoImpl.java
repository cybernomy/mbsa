/**
 * UserSessionInfoImpl.java
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
package com.mg.framework.support;

import java.util.Date;

import com.mg.framework.api.UserSessionInfo;

/**
 * Реализация информации о сессии пользователя
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserSessionInfoImpl.java,v 1.1 2008/07/14 14:12:54 safonov Exp $
 */
public class UserSessionInfoImpl implements UserSessionInfo {
	private String userName;
	private boolean isActive;
	private boolean isCurrentPrincipal;
	private Date creationTime;
	private Date lastAccessedTime;
	private Date usedServerTime;
	private Date idleTime;
	private Date ttl;
	private String sessionId;
	private String remoteHost;
	private int lastUsedTime;
	private int minUsedTime;
	private int maxUsedTime;
	private int hits;
	private long size;
	private long lastRequestSize;
	private long lastResponseSize;
	private long totalRequestSize;
	private long totalResponseSize;


	public UserSessionInfoImpl(String userName) {
		super();
		this.userName = userName;
		this.isActive = false;
	}

	public UserSessionInfoImpl(String userName, Date creationTime,
			Date lastAccessedTime, Date usedServerTime, Date idleTime,
			Date ttl, String sessionId) {
		super();
		this.userName = userName;
		this.creationTime = creationTime;
		this.lastAccessedTime = lastAccessedTime;
		this.usedServerTime = usedServerTime;
		this.idleTime = idleTime;
		this.ttl = ttl;
		this.sessionId = sessionId;
		this.isActive = true;
	}

	public UserSessionInfoImpl(String userName, boolean isCurrentPrincipal,
			Date creationTime, Date lastAccessedTime, Date usedServerTime,
			Date idleTime, Date ttl, String sessionId, String remoteHost,
			int lastUsedTime, int minUsedTime, int maxUsedTime, int hits,
			long size, long lastRequestSize, long lastResponseSize,
			long totalRequestSize, long totalResponseSize) {
		super();
		this.userName = userName;
		this.isCurrentPrincipal = isCurrentPrincipal;
		this.creationTime = creationTime;
		this.lastAccessedTime = lastAccessedTime;
		this.usedServerTime = usedServerTime;
		this.idleTime = idleTime;
		this.ttl = ttl;
		this.sessionId = sessionId;
		this.remoteHost = remoteHost;
		this.lastUsedTime = lastUsedTime;
		this.minUsedTime = minUsedTime;
		this.maxUsedTime = maxUsedTime;
		this.hits = hits;
		this.size = size;
		this.lastRequestSize = lastRequestSize;
		this.lastResponseSize = lastResponseSize;
		this.totalRequestSize = totalRequestSize;
		this.totalResponseSize = totalResponseSize;
		this.isActive = true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder("User name: ").append(userName)
				.append("\nActive: ").append(isActive)
				.append("\nCreation time: ").append(creationTime)
				.append("\nLast access time: ").append(lastAccessedTime)
				.toString();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getCreationTime()
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getHttpSessionId()
	 */
	public String getHttpSessionId() {
		return sessionId;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getIdleTime()
	 */
	public Date getIdleTime() {
		return idleTime;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getLastAccessedTime()
	 */
	public Date getLastAccessedTime() {
		return lastAccessedTime;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getTTL()
	 */
	public Date getTTL() {
		return ttl;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getUsedServerTime()
	 */
	public Date getUsedServerTime() {
		return usedServerTime;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getUserName()
	 */
	public String getUserName() {
		return userName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#isActive()
	 */
	public boolean isActive() {
		return isActive;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getHits()
	 */
	public int getHits() {
		return hits;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getLastRequestSize()
	 */
	public long getLastRequestSize() {
		return lastRequestSize;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getLastResponseSize()
	 */
	public long getLastResponseSize() {
		return lastResponseSize;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getLastUsedTime()
	 */
	public int getLastUsedTime() {
		return lastUsedTime;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getMaxUsedTime()
	 */
	public int getMaxUsedTime() {
		return maxUsedTime;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getMinUsedTime()
	 */
	public int getMinUsedTime() {
		return minUsedTime;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getRemoteHost()
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getSize()
	 */
	public long getSize() {
		return size;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getTotalRequestSize()
	 */
	public long getTotalRequestSize() {
		return totalRequestSize;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#getTotalResponseSize()
	 */
	public long getTotalResponseSize() {
		return totalResponseSize;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserSessionInfo#isCurrentPrincipal()
	 */
	public boolean isCurrentPrincipal() {
		return isCurrentPrincipal;
	}

}
