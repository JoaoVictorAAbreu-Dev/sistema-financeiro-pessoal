#!/bin/sh
BASEDIR=$(cd "$(dirname "$0")"; pwd)
exec "${JAVA_HOME:-java}" -Dmaven.multiModuleProjectDirectory="$BASEDIR" -classpath "$BASEDIR/.mvn/wrapper/maven-wrapper.jar" org.apache.maven.wrapper.MavenWrapperMain "$@"
