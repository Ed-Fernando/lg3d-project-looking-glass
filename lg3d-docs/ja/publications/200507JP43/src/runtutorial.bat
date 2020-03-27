@echo off
set LG3DHOME=.
  
if "%LGX11HOME%" == "" set LGX11HOME=%LG3DHOME%\ext\lg3d-x11

set CLASSPATH=%CLASSPATH%;.
set CLASSPATH=%CLASSPATH%;%LG3DHOME%\lib\ext\lg3d-core.jar
set CLASSPATH=%CLASSPATH%;%LG3DHOME%\ext\escher-0.2.2.lg.jar
set CLASSPATH=%CLASSPATH%;%LG3DHOME%\ext\jaimlib.jar
set CLASSPATH=%CLASSPATH%;%LG3DHOME%\ext\nwn-0.7.jar
set CLASSPATH=%CLASSPATH%;%LG3DHOME%\ext\odejava.jar
set CLASSPATH=%CLASSPATH%;%LG3DHOME%\ext\satin-v2.3.jar
 
set CONFIG=lgconfig_1p_nox.xml
set LGCONFIG=file:%LG3DHOME%\etc\lg3d\%CONFIG%
 
set DISP_CONFIG=-Dlg.displayconfigurl=file:%LG3DHOME%/etc/lg3d/displayconfig/j3d1x1
 
java -Xmx512m -cp %CLASSPATH% -Dj3d.sortShape3DBounds="true" -Dlg.configurl=%LGCONFIG% %DISP_CONFIG% -Dlg.etcdir=%LG3DHOME%\etc\ %1