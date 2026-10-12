package ftp;
import java.io.*;
import java.net.*;

public class FTPDataConnection {
    public Socket enterPassiveMode(FTPConnection connection)throws IOException{

        connection.sendCommand("PASV");

        String reply = connection.readReply();

        int start = reply.indexOf("(");

        int end = reply.indexOf(")");

        String data = reply.substring(start + 1, end);

        String[] parts = data.split(",");

        //build ip
        String ip = parts[0] + "." + parts[1] + "." + parts[2] + "." + parts[3];

        //caculate port
        int port = Integer.parseInt(parts[4]) * 256 + Integer.parseInt(parts[5]);

        System.out.println("DATA IP: " + ip);
        System.out.println("DATA PORT " + port);

        return new Socket(ip, port);
    }
}
