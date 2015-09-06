@echo off
cd $INSTALL_PATH\appserver\bin
call shutdown.bat -S -s$HOST_NAME %*
