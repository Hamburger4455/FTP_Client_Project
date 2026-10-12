package commands;

import ftp.FTPConnection;
import ftp.FTPDataConnection;

import java.io.*;
import java.net.*;

public class ListCommand {
    public static String listFiles(FTPConnection connection) throws IOException{
        //enter passive mode
        FTPDataConnection passive = new FTPDataConnection();

        Socket dataSocket = passive.enterPassiveMode(connection);

        connection.sendCommand("LIST");
        connection.readReply();

        BufferedReader dataReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));

        StringBuilder result = new StringBuilder();

        String line;

        while ((line = dataReader.readLine()) != null){
            result.append(line).append("\n");
        }

        dataReader.close();
        dataSocket.close();
        connection.readReply();

        return result.toString();
    }
}
