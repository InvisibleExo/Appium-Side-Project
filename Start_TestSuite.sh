#!/bin/bash

plat=("$1")

package=("$2")

echo ""$plat" and "$package""

# Passing parameters for -Dexec.args and setupArgs
execArgs=("-Dplat=$plat" "-Dpackage=$package")

setupArgs=("-Dplat=\"$plat\"" "-Dpackage=\"$package\"")

# adding addtional parameters for setup and log capture
while [ $3 ];
do
	val=("$3")
	result=${val//[\"]/}
	execArgs+=( "$result" )
	setupArgs+=( "$3" )
	shift
done

cleaning_input () {
	local runcmd
	declare runcmd="mvn exec:java -Dexec.mainClass=\"Test_Setup.DriverTest\" -Dexec.args=\""${execArgs[@]}"\" "${setupArgs[@]}""
	printf '%s\n' "${runcmd[*]}"
	return $rundcmd
}

declare runTest=$(cleaning_input)
echo $runTest
eval $runTest
