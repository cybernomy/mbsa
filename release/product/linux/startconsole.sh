#!/bin/sh
# -------------------------------------------------------------------------
# MBSA console script for Linux
# -------------------------------------------------------------------------
# $Id: startconsole.sh,v 1.1 2007/04/17 07:08:44 safonov Exp $

#
# Helper to complain.
#
warn() {
    echo "${PROGNAME}: $*"
}

#JAVA_HOME=/usr/java/jdk1.5.0_10

if [ "x$JAVA_HOME" != "x" ]; then
	JAVA="$JAVA_HOME/bin/java"
else
  warn "JAVA_HOME is not set. Unexpected results may occur."
  warn "Set JAVA_HOME to the directory of your local JDK to avoid this message."
	JAVA="java"
fi

"$JAVA" -classpath appserverconsole.jar:../lib/jbossall-client.jar:../lib/log4j.jar:../lib/getopt.jar:. com.mg.framework.console.RemoteConsole "$@"