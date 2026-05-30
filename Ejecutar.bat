@echo off

start cmd /k java -cp target/classes es.etg.dam.server.Servidor

timeout /t 2 >nul

java -cp target/classes es.etg.dam.client.Cliente 10 + 5
java -cp target/classes es.etg.dam.client.Cliente 20 - 5

pause