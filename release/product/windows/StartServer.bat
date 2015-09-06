@echo off
cd $INSTALL_PATH\appserver\bin
call run.bat -c merp -b$HOST_NAME %*
