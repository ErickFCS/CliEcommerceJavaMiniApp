@echo off
setlocal
call ej > nul
mvnw package -D skipTests -D maven.test.skip-true
endlocal