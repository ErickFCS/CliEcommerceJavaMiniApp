@echo off
setlocal
call ej > nul
mvnw compile -D skipTests -D maven.test.skip=true
endlocal