# Unix/Linux commands — Windows vs Mac

| Purpose | Unix / Mac Terminal | Windows CMD | Windows PowerShell |
|---------|---------------------|-------------|-------------------|
| List files | ls | dir | Get-ChildItem / ls |
| List all files including hidden | ls -a | dir /a | Get-ChildItem -Force |
| List files with details | ls -l | dir | Get-ChildItem |
| List all + details | ls -la | dir /a | Get-ChildItem -Force |
| List sorted by time | ls -lt | dir /o-d | Get-ChildItem \| Sort-Object LastWriteTime -Descending |
| Current directory | pwd | cd | Get-Location / pwd |
| Change directory | cd folder | cd folder | cd folder |
| Go one level up | cd .. | cd .. | cd .. |
| Go home | cd ~ | cd %USERPROFILE% | cd ~ |
| Clear terminal | clear | cls | Clear-Host / cls |
| Create directory | mkdir test | mkdir test | mkdir test |
| Create empty file | touch test.txt | type nul > test.txt | New-Item test.txt |
| Copy file | cp a.txt b.txt | copy a.txt b.txt | Copy-Item a.txt b.txt |
| Copy directory | cp -r folder1 folder2 | xcopy folder1 folder2 /E | Copy-Item folder1 folder2 -Recurse |
| Move file | mv a.txt folder/ | move a.txt folder | Move-Item a.txt folder |
| Rename file | mv old.txt new.txt | ren old.txt new.txt | Rename-Item old.txt new.txt |
| Delete file | rm file.txt | del file.txt | Remove-Item file.txt |
| Delete directory | rm -r folder | rmdir /s folder | Remove-Item folder -Recurse |
| Force delete | rm -rf folder | rmdir /s /q folder | Remove-Item folder -Recurse -Force |
| Read file | cat file.txt | type file.txt | Get-Content file.txt / cat |
| Search text in file | grep "hello" file.txt | findstr "hello" file.txt | Select-String "hello" file.txt |
| Find files | find . -name "*.java" | dir /s *.java | Get-ChildItem -Recurse -Filter *.java |
| Show running processes | ps | tasklist | Get-Process |
| Kill process | kill PID | taskkill /PID PID /F | Stop-Process -Id PID -Force |
| Show environment variable | echo $JAVA_HOME | echo %JAVA_HOME% | $env:JAVA_HOME |
| Set environment variable | export JAVA_HOME=... | set JAVA_HOME=... | $env:JAVA_HOME="..." |
| Show PATH | echo $PATH | echo %PATH% | $env:PATH |
| Check command location | which java | where java | Get-Command java |
| Check Java version | java -version | java -version | java -version |
| Check Git version | git --version | git --version | git --version |
| Print text | echo "Hello" | echo Hello | Write-Output "Hello" |
| Compare files | diff a.txt b.txt | fc a.txt b.txt | Compare-Object |
| Display first lines | head file.txt | more file.txt | Get-Content file.txt -Head 10 |
| Display last lines | tail file.txt | powershell Get-Content file.txt -Tail 10 | Get-Content file.txt -Tail 10 |
| Monitor file changes | tail -f app.log | PowerShell alternative | Get-Content app.log -Wait |
| Check network connectivity | ping google.com | ping google.com | ping google.com |
| Show network config | ifconfig / ipconfig | ipconfig | ipconfig |
| Check port | lsof -i :8080 | netstat -ano \| findstr :8080 | Get-NetTCPConnection -LocalPort 8080 |
| Make HTTP request | curl URL | curl URL | curl URL |
| Download file | curl -O URL | curl -O URL | curl -O URL |
| Show disk space | df -h | wmic logicaldisk get size,freespace,caption | Get-PSDrive |
| Show directory size | du -sh folder | dir /s folder | (Get-ChildItem folder -Recurse \| Measure-Object Length -Sum).Sum |
| Change permissions | chmod +x script.sh | Different mechanism | icacls |
| Run shell script | ./script.sh | Not directly | .\script.ps1 for PowerShell |
| Create symbolic link | ln -s target link | mklink link target | New-Item -ItemType SymbolicLink |
