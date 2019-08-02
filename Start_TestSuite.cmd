
::Passing parameters for longer Mvn command
@SET plat=%1

@SET package=%2

ECHO %plat% and %package%


::Passing parameters for -Dexec.args and setupArgs
@SET dExecARGs=-Dplat=%plat% -Dpackage=%package%

@SET setupARGS=-Dplat="%plat%" -Dpackage="%package%"

ECHO OFF


:while
IF NOT [%3] EQU [] (
@Setlocal EnableDelayedExpansion
@SET val=%3
@SET result=!val:"=!
@SET dExecARGs=%dExecARGs% !result!
@SET setupARGS=%setupARGS% %~3
@SHIFT
@goto :while
)



@SET runTests=mvn exec:java -Dexec.mainClass="BaseTest.DriverTest" -Dexec.args="%dExecARGs%" %setupARGS%
ECHO ON
CALL %runTests%