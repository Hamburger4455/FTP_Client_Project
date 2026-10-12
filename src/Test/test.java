package Test;

import ftp.FTPClient;
import ftp.FTPConnection;
import commands.ListCommand;
import commands.DownloadCommand;

public class test {
    public static void main(String[] args) {
        try {
            FTPClient client = new FTPClient();

            client.connect("ftp.gnu.org", 21);
            System.out.println(client.login("anonymous", "anonymous"));
            System.out.println(client.listFiles());

            client.downloadFile("README","C:\\Coding Place RameSay\\JET BRAINS\\FTPClientProjectCN2\\download\\README.txt");
            client.uploadFile("upload_test.txt", "server_test.txt");

            System.out.println(client.makeDirectory("testFolder"));
            client.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}