
::Passing parameters for longer Mvn command
@SET plat=%1

@SET package=%2

ECHO %plat% and %package%

::Passing parameters for setupArgs

@SET setupARGS=-Dplat="%plat%" -Dpackage="%package%"

ECHO OFF

:while
IF NOT [%3] EQU [] (
@Setlocal EnableDelayedExpansion
@SET val=%3
@SET result=!val:"=!
@SET result=!result:\=!
@SET setupARGS=%setupARGS% !result!
@SHIFT
@goto :while
)

ECHO %setupARGS%

@SET runSetup=mvn exec:java -Dexec.mainClass="Test_Setup.DriverTest" %setupARGS%
ECHO ON
CALL %runSetup%
ECHO "Running Tests..."
@SET runTest=mvn test %setupARGS%
ECHO %runTest%
CALL %runTest%