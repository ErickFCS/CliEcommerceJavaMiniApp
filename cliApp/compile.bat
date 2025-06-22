@echo off
setlocal
call ej > nul
mvn package -D skipTests -D maven.test.skip=true
endlocal