@echo off

cd /d %~dp0

echo ==================================
echo Running API Automation Tests
echo ==================================

mvn clean test

echo ==================================
echo Execution Completed
echo ==================================

pause