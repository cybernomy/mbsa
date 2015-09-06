@echo off
rem Usage example:
rem extract_fontmetric.bat cour.ttf ttf_cour.xml

java -cp ..\..\..\support\lib\fop.jar;..\..\..\support\lib\avalon-framework-cvs-20020806.jar; org.apache.fop.fonts.apps.TTFReader %1 %2
