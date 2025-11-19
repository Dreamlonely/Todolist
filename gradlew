#!/usr/bin/env sh
APP_HOME=dirname ""
CLASSPATH=/gradle/wrapper/gradle-wrapper.jar
exec "/bin/java" -classpath "" org.gradle.wrapper.GradleWrapperMain "$@"
