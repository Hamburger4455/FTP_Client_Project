package commands;

import ftp.FTPConnection;
import ftp.FTPDataConnection;

import java.io.*;
import java.net.*;

public class UploadCommand {
    public static void uploadFile(FTPConnection connection, String localFile, String remoteFile) throws IOException {
        //binary
        connection.sendCommand("TYPE I");
        connection.readReply();

        //pasv mode
        FTPDataConnection passive = new FTPDataConnection();
        Socket dataSocket = passive.enterPassiveMode(connection);

        connection.sendCommand("STOR " + remoteFile);
        connection.readReply();

        //read local file
        FileInputStream input = new FileInputStream(localFile);

        //giv file to sever
        OutputStream output = dataSocket.getOutputStream();

        byte[] buffer = new byte[4096];

        int bytesRead;

        while ((bytesRead = input.read(buffer)) != -1){
            output.write(buffer, 0, bytesRead);
        }

        input.close();
        output.close();
        dataSocket.close();

        connection.readReply();
        System.out.println("Upload completed");
    }
}
