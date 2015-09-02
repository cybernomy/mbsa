#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  This is the main entry point for the build installer.                   ##
##                                                                          ##
##  Users should be sure to execute this file rather than 'ant' to ensure   ##
##  the correct version is being used with the correct configuration.       ##
##                                                                          ##
### ====================================================================== ###

# $Id: build_installer.sh,v 1.1 2008/03/04 13:14:55 safonov Exp $

ANT_OPTS='-Xms128m -Xmx512m -XX:MaxPermSize=128m'
export ANT_OPTS

ant -logfile build.log -propertyfile build.properties -buildfile ../release/product/build.xml release.community
