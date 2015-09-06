<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- ======================================================================= -->
<!-- Copyright (c) 1998 - 2009 BusinessTechnology, Ltd. -->
<!-- All rights reserved -->
<!-- -->
<!-- This program is the proprietary and confidential information -->
<!-- of BusinessTechnology, Ltd. and may be used and disclosed only -->
<!-- as authorized in a license agreement authorizing and -->
<!-- controlling such use and disclosure -->
<!-- -->
<!-- Millennium ERP system. -->
<!-- ======================================================================= -->

<!-- $Id: merpclient.jsp,v 1.7 2009/02/09 15:53:25 safonov Exp $ -->

<%!
    private boolean needsAppletTag(String userAgent) {
        return userAgent.indexOf("Mac") != -1 && (userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Safari") != -1);
    }
%>

<HTML>
<HEAD>
    <TITLE>Millennium Business Suite</TITLE>
</HEAD>
<BODY>

<%
    String applicationUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/merp-applet";
    if (!needsAppletTag(request.getHeader("User-Agent"))) {
%>

<jsp:plugin
        type="applet"
        code="com.mg.merp.core.client.applet.ClientAppletLauncher.class"
        codebase="."
        archive="lib/merp-applet-client.jar,lib/ulc-all5-client.jar,lib/merp-ulccomponent-client.jar,lib/jgraph.jar,lib/looks.jar"
        height="100%"
        width="100%"
        jreversion="1.5">
    <jsp:params>
        <jsp:param name="url-string" value="<%= applicationUrl %>"/>
        <jsp:param name="keep-alive-interval" value="900"/>
        <jsp:param name="log-level" value="WARNING"/>
        <jsp:param name="client-coder-registry-provider"
                   value="com.mg.merp.core.support.ui.ClientCoderRegistryProvider"/>
        <jsp:param name="look-and-feel" value="com.jgoodies.looks.plastic.PlasticLookAndFeel"/>
    </jsp:params>

    <jsp:fallback>Your browser does not support JDK 1.5 or higher for applets.</jsp:fallback>
</jsp:plugin>

<%
} else {
%>

<APPLET WIDTH="100%" HEIGHT="100%"
        CODEBASE="."
        CODE="com.mg.merp.core.client.applet.ClientAppletLauncher.class"
        ARCHIVE="lib/merp-applet-client.jar,lib/ulc-all5-client.jar,lib/merp-ulccomponent-client.jar,lib/jgraph.jar,lib/looks.jar">
    <PARAM NAME="url-string" VALUE="<%= applicationUrl %>">
    <PARAM NAME="keep-alive-interval" VALUE="900">
    <PARAM NAME="log-level" VALUE="WARNING">
    <PARAM NAME="client-coder-registry-provider"
           VALUE="com.mg.merp.core.support.ui.ClientCoderRegistryProvider">
    <PARAM NAME="look-and-feel" VALUE="com.jgoodies.looks.plastic.PlasticLookAndFeel">
    Your browser does not support JDK 1.5 or higher for applets.
</APPLET>

<%
    }
%>

</BODY>
</HTML>
