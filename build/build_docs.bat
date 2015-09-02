REM  ======================================================================
REM
REM  This is the main entry point for the build docs.
REM
REM  Users should be sure to execute this file rather than 'ant' to ensure
REM  the correct version is being used with the correct configuration.
REM
REM  ======================================================================
REM
REM $Id: build_docs.bat,v 1.1 2008/03/04 13:14:55 safonov Exp $

set ANT_OPTS=-Xms128m -Xmx512m -XX:MaxPermSize=128m
ant -logfile build.log -propertyfile build.properties -buildfile ../docs/build.xml
