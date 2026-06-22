@ECHO OFF
SETLOCAL
SET MAVEN_PROJECTBASEDIR=%~dp0
SET WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar
IF DEFINED JAVA_HOME (
  SET JAVA_EXEC=%JAVA_HOME%\bin\java.exe
) ELSE (
  SET JAVA_EXEC=java
)
"%JAVA_EXEC%" -Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR% -classpath %WRAPPER_JAR% org.apache.maven.wrapper.MavenWrapperMain %*
ENDLOCAL
