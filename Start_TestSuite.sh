#!/bin/bash

plat=("$1")

package=("$2")

echo ""$plat" and "$package""

# Passing parameters for setupArgs
setupArgs=("-Dplat=\"$plat\"" "-Dpackage=\"$package\"")

# adding addtional parameters for setup and log capture
while [ $3 ];
do
	val=("$3")
	result=${val//[\"]/}
	setupArgs+=( "$result" )
	shift
done

cleaning_input () {
	local runcmd
	declare runcmd="mvn exec:java -Dexec.mainClass=\"Test_Setup.DriverTest\" "${setupArgs[@]}" -Dexec.cleanupDaemonThreads=false"
	printf '%s\n' "${runcmd[*]}"
	return $rundcmd
}

run_tests () {
	local testLine
	declare testLine="mvn test ${setupArgs[@]}"
	printf '%s\n' "${testLine[*]}"
	return $testLine
}

declare runTestSetup=$(cleaning_input)
echo $runTestSetup
eval $runTestSetup

echo "Running Tests...."
declare testCmd=$(run_tests)
echo $testCmd
eval $testCmd

