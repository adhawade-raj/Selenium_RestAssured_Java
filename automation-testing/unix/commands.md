# Unix/Linux commands — Windows vs Mac

## 📌 Note
**Linux and Mac use the same terminal commands!** Both Linux PuTTY (SSH terminal) and Mac Terminal run on POSIX-based shells (bash/zsh), so the commands are virtually identical. The main difference is between Unix/Linux/Mac vs Windows environments.

| Purpose | Unix / Linux / Mac | Windows CMD |
|---------|-------------------|-------------|
| List files | ls | dir |
| List all files including hidden | ls -a | dir /a |
| List files with details | ls -l | dir |
| List all + details | ls -la | dir /a |
| List sorted by time | ls -lt | dir /o-d |
| Current directory | pwd | cd |
| Change directory | cd folder | cd folder |
| Go one level up | cd .. | cd .. |
| Go home | cd ~ | cd %USERPROFILE% |
| Clear terminal | clear | cls |
| Create directory | mkdir test | mkdir test |
| Create empty file | touch test.txt | type nul > test.txt |
| Copy file | cp a.txt b.txt | copy a.txt b.txt |
| Copy directory | cp -r folder1 folder2 | xcopy folder1 folder2 /E |
| Move file | mv a.txt folder/ | move a.txt folder |
| Rename file | mv old.txt new.txt | ren old.txt new.txt |
| Delete file | rm file.txt | del file.txt |
| Delete directory | rm -r folder | rmdir /s folder |
| Force delete | rm -rf folder | rmdir /s /q folder |
| Read file | cat file.txt | type file.txt |
| Search text in file | grep "hello" file.txt | findstr "hello" file.txt |
| Find files | find . -name "*.java" | dir /s *.java |
| Show running processes | ps | tasklist |
| Kill process | kill PID | taskkill /PID PID /F |
| Show environment variable | echo $JAVA_HOME | echo %JAVA_HOME% |
| Set environment variable | export JAVA_HOME=... | set JAVA_HOME=... |
| Show PATH | echo $PATH | echo %PATH% |
| Check command location | which java | where java |
| Check Java version | java -version | java -version |
| Check Git version | git --version | git --version |
| Print text | echo "Hello" | echo Hello |
| Compare files | diff a.txt b.txt | fc a.txt b.txt |
| Display first lines | head file.txt | more file.txt |
| Display last lines | tail file.txt | powershell Get-Content file.txt -Tail 10 |
| Monitor file changes | tail -f app.log | PowerShell: Get-Content app.log -Wait |
| Check network connectivity | ping google.com | ping google.com |
| Show network config | ifconfig / ipconfig | ipconfig |
| Check port | lsof -i :8080 | netstat -ano \| findstr :8080 |
| Make HTTP request | curl URL | curl URL |
| Download file | curl -O URL | curl -O URL |
| Show disk space | df -h | wmic logicaldisk get size,freespace,caption |
| Show directory size | du -sh folder | dir /s folder |
| Change permissions | chmod +x script.sh | icacls (different mechanism) |
| Run shell script | ./script.sh | Not directly supported |
| Create symbolic link | ln -s target link | mklink link target |
