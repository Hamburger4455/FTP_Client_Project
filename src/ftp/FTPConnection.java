package ftp;
import java.io.*;
import java.net.*;

public class FTPConnection {
    private Socket controlSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public void connect(String host, int port)throws IOException {
        controlSocket = new Socket(host, port);

        reader = new BufferedReader(new InputStreamReader(controlSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(controlSocket.getOutputStream()));
        System.out.println(readReply());
    }

    public void sendCommand(String command)throws IOException {
        writer.write(command + "\r\n");
        writer.flush();

        System.out.println("CLIENT: " + command);
    }
//reade sever replies
    public String readReply() throws IOException {
        String line = reader.readLine();

        if (line == null){
            return "";
        }

        StringBuilder reply = new StringBuilder();
        reply.append(line).append("\n");
        System.out.println("SERVER: " + line);

        if (line.length() >= 4 && line.charAt(3) == '-'){
            String code = line.substring(0, 3);

            while (true){
                line = reader.readLine();
                reply.append(line).append("\n");
                System.out.println("SERVER: " + line);

                if (line.startsWith(code + " ")){
                    break;
                }
            }
        }
        return reply.toString();
    }

    public String login(String username, String password)throws IOException{

        sendCommand("USER " + username);
        String userReply = readReply();

        sendCommand("PASS " + password);
        String passReply = readReply();

        return userReply + passReply;
    }

    public String printWorkingDir() throws IOException {
        sendCommand("PWD");
        return readReply();
    }

    public String changeDir(String path) throws IOException {
        sendCommand("CWD " + path);
        return readReply();
    }

    public void disconnect() throws IOException {
        sendCommand("QUIT");

        readReply();

        if (reader != null){
            reader.close();
        }

        if (writer != null){
            writer.close();
        }

        if (controlSocket != null){
            controlSocket.close();
        }
    }
}




