/**
 * MimeTypeFilter.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.help.viewer;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Фильтр принудительной установки mime типа, т.к. при обращении к некоторым разделам помощи из
 * приложения JNLP на клиентской стороне генерируется NPE (<a href="https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4886">запрос
 * 4886</a>).
 *
 * @author Oleg V. Safonov
 * @version $Id: MimeTypeFilter.java,v 1.1 2008/10/31 06:38:43 safonov Exp $
 */
public class MimeTypeFilter implements Filter {
  private Logger log = ServerUtils.getLogger(MimeTypeFilter.class);

  /* (non-Javadoc)
   * @see javax.servlet.Filter#destroy()
   */
  public void destroy() {
  }

  /* (non-Javadoc)
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
   */
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    chain.doFilter(request, response);
    if (response.getContentType() == null) {
      if (log.isDebugEnabled())
        log.debug("force a value of response content type to text/html");
      response.setContentType("text/html");
    }
  }

  /* (non-Javadoc)
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  public void init(FilterConfig filterConfig) throws ServletException {
  }

}
