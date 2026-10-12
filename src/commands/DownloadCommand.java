package commands;

import ftp.FTPConnection;
import ftp.FTPDataConnection;

import java.io.*;
import java.net.*;

public class DownloadCommand {
    public static void downloadFile(FTPConnection connection, String remoteFile, String localPath) throws IOException {
        //bin mode
        connection.sendCommand("TYPE I");
        connection.readReply();

        //pasv mode
        FTPDataConnection passive = new FTPDataConnection();

        Socket dataSocket = passive.enterPassiveMode(connection);

        connection.sendCommand("RETR " + remoteFile);

        String firstReply = connection.readReply();
        System.out.println(firstReply);

        InputStream input = dataSocket.getInputStream();

        FileOutputStream output = new FileOutputStream(localPath);
        byte[] buffer = new byte[4096];

        int bytesRead;

        while ((bytesRead = input.read(buffer)) != -1){
            output.write(buffer, 0, bytesRead);
        }
        output.close();
        input.close();
        dataSocket.close();

        String finalReply = connection.readReply();

        System.out.println(finalReply);
        System.out.println("download complete");
    }
}
