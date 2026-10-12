package gui;

import java.awt.*;
import javax.swing.*;
import ftp.FTPClient;

public class FTPClientGUI extends JFrame {

    private FTPClient client;
    private JTextField hostField;
    private JTextField portField;
    private JTextField userField;
    private JTextField passField;
    private JTextArea outputArea;

    public FTPClientGUI() {

        client = new FTPClient();

        setTitle("FTP Client");
        setSize(700, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        //top
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 5));

        hostField = new JTextField("ftp.gnu.org");
        portField = new JTextField("21");
        userField = new JTextField("anonymous");
        passField = new JTextField("anonymous");

        JButton connectButton = new JButton("Connect");
        JButton loginButton = new JButton("Login");
        topPanel.add(new JLabel("Host"));
        topPanel.add(new JLabel("Port"));
        topPanel.add(new JLabel("Username"));
        topPanel.add(new JLabel("Password"));
        topPanel.add(new JLabel(""));

        topPanel.add(hostField);
        topPanel.add(portField);
        topPanel.add(userField);
        topPanel.add(passField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connectButton);
        buttonPanel.add(loginButton);
        topPanel.add(buttonPanel);
        add(topPanel, BorderLayout.NORTH);

        //center
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(scrollPane, BorderLayout.CENTER);

        //btom
        JPanel bottomPanel = new JPanel();
        JButton listButton = new JButton("LIST");
        JButton pwdButton = new JButton("PWD");
        JButton cwdButton = new JButton("CWD");
        JButton downloadButton = new JButton("DOWNLOAD");
        JButton uploadButton = new JButton("UPLOAD");
        JButton mkdirButton = new JButton("MKDIR");
        JButton deleteButton = new JButton("DELETE");
        JButton rmdirButton = new JButton("RMD");
        JButton clearButton = new JButton("CLEAR");
        JButton quitButton = new JButton("QUIT");

        bottomPanel.add(listButton);
        bottomPanel.add(pwdButton);
        bottomPanel.add(cwdButton);
        bottomPanel.add(downloadButton);
        bottomPanel.add(uploadButton);
        bottomPanel.add(mkdirButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(rmdirButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(quitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        connectButton.addActionListener(e -> {
            try {
                client.connect(hostField.getText(), Integer.parseInt(portField.getText()));
                outputArea.append("Connected\n");
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        loginButton.addActionListener(e -> {
            try {
                String reply = client.login(userField.getText(), passField.getText());

                outputArea.append(reply);
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        listButton.addActionListener(e -> {
            try {
                outputArea.append(client.listFiles());
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        pwdButton.addActionListener(e -> {
            try {
                outputArea.append(client.printWorkingDirectory());
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        cwdButton.addActionListener(e -> {
            try {
                String path = JOptionPane.showInputDialog("Enter directory:");

                outputArea.append(client.changeDirectory(path));
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        downloadButton.addActionListener(e -> {
            try {
                String remoteFile = JOptionPane.showInputDialog("Remote file name:");

                client.downloadFile(remoteFile, "download/" + remoteFile);
                outputArea.append("Download completed\n");
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        uploadButton.addActionListener(e -> {
            try {
                String localFile = JOptionPane.showInputDialog("Local file path:");
                String remoteFile = JOptionPane.showInputDialog("Remote file name:");

                client.uploadFile(localFile, remoteFile);
                outputArea.append("Upload completed\n");
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        mkdirButton.addActionListener(e -> {
            try {
                String folder = JOptionPane.showInputDialog("Folder name:");

                if(folder == null || folder.isEmpty()) {
                    return;
                }
                outputArea.append(client.makeDirectory(folder));
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                String file = JOptionPane.showInputDialog("File name:");
                if(file == null || file.isEmpty()) {
                    return;
                }

                outputArea.append(client.deleteFile(file));
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        rmdirButton.addActionListener(e -> {
            try {
                String folder = JOptionPane.showInputDialog("Folder name:");
                if(folder == null || folder.isEmpty()) {
                    return;
                }

                outputArea.append(client.removeDirectory(folder));
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });

        clearButton.addActionListener(e -> {
            outputArea.setText("");
        });

        quitButton.addActionListener(e -> {
            try {
                client.disconnect();
                outputArea.append("Disconnected\n");
            } catch (Exception ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        });
        setVisible(true);
    }
}