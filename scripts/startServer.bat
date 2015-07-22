@echo off

call setEnv.bat

echo Starting Locator and Server...
call gfsh run --file=serverStart.gf

REM Run the Loader client (LoadRegions) to load the data


set REPO=%COURSE_HOME%\repository

set CLASSPATH=target\classes;%REPO%\org\springframework\data\spring-data-gemfire\1.5.0.RELEASE\spring-data-gemfire-1.5.0.RELEASE.jar;%REPO%\antlr\antlr\2.7.7\antlr-2.7.7.jar;%REPO%\org\slf4j\jcl-over-slf4j\1.7.6\jcl-over-slf4j-1.7.6.jar;%REPO%\org\aspectj\aspectjweaver\1.8.2\aspectjweaver-1.8.2.jar;%REPO%\org\slf4j\slf4j-api\1.7.6\slf4j-api-1.7.6.jar;%REPO%\org\springframework\data\spring-data-commons\1.9.0.RELEASE\spring-data-commons-1.9.0.RELEASE.jar;%REPO%\com\gemstone\gemfire\gemfire\8.0.0\gemfire-8.0.0.jar;%REPO%\org\codehaus\jackson\jackson-mapper-asl\1.9.12\jackson-mapper-asl-1.9.12.jar;%REPO%\org\springframework\spring-aop\3.2.11.RELEASE\spring-aop-3.2.11.RELEASE.jar;%REPO%\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;%REPO%\org\springframework\spring-context-support\3.1.2.RELEASE\spring-context-support-3.1.2.RELEASE.jar;%REPO%\org\springframework\spring-core\3.2.11.RELEASE\spring-core-3.2.11.RELEASE.jar;%REPO%\org\codehaus\jackson\jackson-core-asl\1.9.12\jackson-core-asl-1.9.12.jar;%REPO%\junit\junit\4.10\junit-4.10.jar;%REPO%\org\hamcrest\hamcrest-core\1.1\hamcrest-core-1.1.jar;%REPO%\org\springframework\spring-test\3.2.11.RELEASE\spring-test-3.2.11.RELEASE.jar;%REPO%\org\springframework\spring-context\3.2.11.RELEASE\spring-context-3.2.11.RELEASE.jar;%REPO%\org\springframework\spring-beans\3.2.11.RELEASE\spring-beans-3.2.11.RELEASE.jar;%REPO%\org\springframework\spring-expression\3.2.11.RELEASE\spring-expression-3.2.11.RELEASE.jar;%REPO%\org\springframework\spring-tx\3.2.11.RELEASE\spring-tx-3.2.11.RELEASE.jar;%REPO%\log4j\log4j\1.2.17\log4j-1.2.17.jar;%REPO%\cglib\cglib\2.2.2\cglib-2.2.2.jar;%REPO%\asm\asm\3.3.1\asm-3.3.1.jar;%REPO%\org\slf4j\slf4j-log4j12\1.7.5\slf4j-log4j12-1.7.5.jar;%REPO%\com\gopivotal\training\server-bootstrap\1.0.0.CI-SNAPSHOT\server-bootstrap-1.0.0.CI-SNAPSHOT.jar


echo Running loader...
java com.gopivotal.training.LoadRegions


