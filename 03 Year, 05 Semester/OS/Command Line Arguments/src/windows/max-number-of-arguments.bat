@echo off
setlocal enabledelayedexpansion

set "arg=test"

set "num_repeats=5000"

set "args="

for /l %%i in (1,1,%num_repeats%) do (
  set "args=!args!!arg! "
)

cd ..
a.exe %args%

endlocal
