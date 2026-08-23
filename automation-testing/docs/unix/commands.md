# Unix/Linux commands — Windows vs Mac

## 📌 Note
**Linux and Mac use the same terminal commands!** Both Linux PuTTY (SSH terminal) and Mac Terminal run on POSIX-based shells (bash/zsh), so the commands are virtually identical. The main difference is between Unix/Linux/Mac vs Windows environments.

---

## 1. Directory Navigation

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Current directory | pwd | cd |
| 2 | Change directory | cd folder | cd folder |
| 3 | Go one level up | cd .. | cd .. |
| 4 | Go home | cd ~ | cd %USERPROFILE% |
| 5 | Clear terminal | clear | cls |

---

## 2. Listing & Finding Files/Directories

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | List files | ls | dir |
| 2 | List all files including hidden | ls -a | dir /a |
| 3 | List files with details | ls -l | dir |
| 4 | List all + details | ls -la | dir /a |
| 5 | List sorted by time | ls -lt | dir /o-d |
| 6 | Find files | find . -name "*.java" | dir /s *.java |
| 7 | Show directory size | du -sh folder | dir /s folder |

---

## 3. File & Directory Operations

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Create directory | mkdir test | mkdir test |
| 2 | Create empty file | touch test.txt | type nul > test.txt |
| 3 | Copy file | cp a.txt b.txt | copy a.txt b.txt |
| 4 | Copy directory | cp -r folder1 folder2 | xcopy folder1 folder2 /E |
| 5 | Move file | mv a.txt folder/ | move a.txt folder |
| 6 | Rename file | mv old.txt new.txt | ren old.txt new.txt |
| 7 | Delete file | rm file.txt | del file.txt |
| 8 | Delete directory | rm -r folder | rmdir /s folder |
| 9 | Force delete | rm -rf folder | rmdir /s /q folder |
| 10 | Create symbolic link | ln -s target link | mklink link target |

---

## 4. File Content Operations

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Read file | cat file.txt | type file.txt |
| 2 | Display first lines | head file.txt | more file.txt |
| 3 | Display last lines | tail file.txt | powershell Get-Content file.txt -Tail 10 |
| 4 | Monitor file changes | tail -f app.log | PowerShell: Get-Content app.log -Wait |
| 5 | Search text in file | grep "hello" file.txt | findstr "hello" file.txt |
| 6 | Compare files | diff a.txt b.txt | fc a.txt b.txt |
| 7 | Print text | echo "Hello" | echo Hello |

---

## 5. Permissions (chmod)

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Make executable | chmod +x script.sh | icacls script.sh /grant Everyone:F |
| 2 | Read/write/execute (755) | chmod 755 file.txt | icacls file.txt /inheritance:r /grant Everyone:F |
| 3 | Remove all permissions | chmod 000 file.txt | icacls file.txt /deny Everyone:(F) |
| 4 | Add read permission | chmod +r file.txt | icacls file.txt /grant Everyone:R |
| 5 | Add write permission | chmod +w file.txt | icacls file.txt /grant Everyone:W |
| 6 | Run shell script | ./script.sh | Not directly supported |

---

## 6. Process Management

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Show running processes | ps | tasklist |
| 2 | Kill process | kill PID | taskkill /PID PID /F |

---

## 7. Environment Variables

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Show environment variable | echo $JAVA_HOME | echo %JAVA_HOME% |
| 2 | Set environment variable | export JAVA_HOME=... | set JAVA_HOME=... |
| 3 | Show PATH | echo $PATH | echo %PATH% |
| 4 | Check command location | which java | where java |

---

## 8. System Information

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Check Java version | java -version | java -version |
| 2 | Check Git version | git --version | git --version |
| 3 | Show disk space | df -h | wmic logicaldisk get size,freespace,caption |

---

## 9. Network & Connectivity

| Sr No | Purpose | Unix / Linux / Mac | Windows CMD |
|-------|---------|-------------------|-------------|
| 1 | Check network connectivity | ping google.com | ping google.com |
| 2 | Show network config | ifconfig / ipconfig | ipconfig |
| 3 | Check port | lsof -i :8080 | netstat -ano \| findstr :8080 |
| 4 | Make HTTP request | curl URL | curl URL |
| 5 | Download file | curl -O URL | curl -O URL |
