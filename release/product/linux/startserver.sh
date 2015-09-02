#!/bin/sh
# -------------------------------------------------------------------------
# MBSA start script for Linux
# -------------------------------------------------------------------------
# $Id: startserver.sh,v 1.2 2007/10/24 12:19:51 safonov Exp $

cd $INSTALL_PATH/appserver/bin
./run.sh -c merp -b$HOST_NAME "$@"
