@echo off
setlocal
call ej > nul
java -cp target/classes src/main/java/com/menu/Main.java
endlocal