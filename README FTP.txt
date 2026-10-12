FTP Client Application

Project Structure:
src
├── app
│   └── Main.java
│
├── gui
│   └── FTPClientGUI.java
│
├── ftp
│   ├── FTPClient.java
│   ├── FTPConnection.java
│   └── FTPDataConnection.java
│
└── commands
    ├── ListCommand.java
    ├── DownloadCommand.java
    ├── UploadCommand.java
    └── DirectoryCommand.java


Requirements
Java JDK 17 or higher
IntelliJ IDEA (recommended)
FTP Server (optional for testing uploads and directory operations)


Running the Application
Open the project in IntelliJ IDEA.
Build the project.
Run Main.java.
The FTP Client GUI will appear.



========= Testing:

Public FTP Server

Host: ftp.gnu.org

Port: 21

Username: anonymous

Password: anonymous

Note: This server is read-only. Upload, delete, and directory modification operations are not permitted.


Local FileZilla Server

Host: 127.0.0.1

Port: 21

Use the username and password configured in FileZilla Server.





