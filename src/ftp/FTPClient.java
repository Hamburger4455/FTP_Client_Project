package ftp;

import commands.DirectoryCommand;
import commands.DownloadCommand;
import commands.ListCommand;
import commands.UploadCommand;

public class FTPClient {
    private FTPConnection connection;

    public FTPClient() {
        connection = new FTPConnection();
    }

    public void connect(String host, int port) throws Exception {
        connection.connect(host, port);
    }

    public String login(String username, String password) throws Exception {
        return connection.login(username, password);
    }

    public String listFiles() throws Exception {
        return ListCommand.listFiles(connection);
    }

    public void downloadFile(String remoteFile, String localPath) throws Exception {
        DownloadCommand.downloadFile(connection, remoteFile, localPath);
    }

    public void uploadFile(String localFile, String remoteFile) throws Exception {
        UploadCommand.uploadFile(connection, localFile, remoteFile);
    }

    //pwd
    public String printWorkingDirectory() throws Exception {
        return connection.printWorkingDir();
    }

    //cwd
    public String changeDirectory(String path) throws Exception {
        return connection.changeDir(path);
    }

    //create folder
    public String makeDirectory(String folder) throws Exception {
        return DirectoryCommand.makeDirectory(connection, folder);
    }

    //remove folder
    public String removeDirectory(String folder) throws Exception {
        return DirectoryCommand.removeDirectory(connection, folder);
    }

    public String deleteFile(String file) throws Exception {
        return DirectoryCommand.deleteFile(connection, file);
    }

    public void disconnect() throws Exception {
        connection.disconnect();
    }
}