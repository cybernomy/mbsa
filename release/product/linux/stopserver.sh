#!/bin/sh
# -------------------------------------------------------------------------
# MBSA stop script for Linux
# -------------------------------------------------------------------------
# $Id: stopserver.sh,v 1.2 2008/05/23 15:28:15 safonov Exp $

cd $INSTALL_PATH/appserver/bin
./shutdown.sh -S -s$HOST_NAME "$@"
