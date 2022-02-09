@echo off

echo.
echo This tries to start 'Git Bash' (if you have it installed) from current dir.
echo You can then run .sh scripts in the newly opened window, like for example:
echo   ./setup.sh
echo   ./setup.sh clean-all
echo   exit
echo Note: for cleanup operations, it's strongly recommended to first close ALL IntelliJ windows, before continuing with the sh scripts!

start "" "%PROGRAMFILES%\Git\bin\sh.exe" --login