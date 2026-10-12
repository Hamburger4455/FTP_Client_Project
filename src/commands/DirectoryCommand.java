package commands;

import ftp.FTPConnection;
import java.io.*;

public class DirectoryCommand {
    public static String makeDirectory(FTPConnection connection, String folder) throws IOException{
        connection.sendCommand("MKD " + folder);
        return connection.readReply();
    }

    public static String removeDirectory(FTPConnection connection, String folder)throws IOException{
        connection.sendCommand("RMD " + folder);
        return connection.readReply();
    }

    public static String deleteFile(FTPConnection connection, String file)throws IOException{
        connection.sendCommand("DELE " + file);
        return connection.readReply();
    }
}
