/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sudoku_server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CLIENTConnection implements Runnable {

    private Socket clientSocket;
    private BufferedReader in = null;

    public CLIENTConnection(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));

                        receiveFile();                   
                in.close();

        } catch (IOException ex) {
            Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void receiveFile() {
        try {
            int bytesRead;

            DataInputStream clientData = new DataInputStream(clientSocket.getInputStream());
           // String fileName1 = clientData.readUTF();
            String fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream(("received_from_client_" + fileName));
           // OutputStream output1 = new FileOutputStream(("received_from_client_" + fileName1));
            //BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\score.txt"));
            BufferedReader in = new BufferedReader(new FileReader(fileName));
           // BufferedReader in1 = new BufferedReader(new FileReader(fileName1));
                    String str;
                    str = in.readLine();
                    //str1=in1.readLine();
                    //  String str1;
                  // str1 = in.readLine();
                    System.out.println(str);
                   // System.out.println(str1);
                   // System.out.println(fileName);
                
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
                
            }

            output.close();
            clientData.close();

            //System.out.println("File "+fileName+" received from client.");
        } catch (IOException ex) {
            System.err.println("Client error. Connection closed.");
        }
    }
      
      }
   
//    public void sendFile(String fileName) {
//        try {
//            //handle file read
//            File myFile = new File(fileName);
//            byte[] mybytearray = new byte[(int) myFile.length()];
//
//            FileInputStream fis = new FileInputStream(myFile);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            //bis.read(mybytearray, 0, mybytearray.length);
//
//            DataInputStream dis = new DataInputStream(bis);
//            dis.readFully(mybytearray, 0, mybytearray.length);
//
//            //handle file send over socket
//            OutputStream os = clientSocket.getOutputStream();
//
//            //Sending file name and file size to the server
//            DataOutputStream dos = new DataOutputStream(os);
//            dos.writeUTF(myFile.getName());
//            dos.writeLong(mybytearray.length);
//            dos.write(mybytearray, 0, mybytearray.length);
//            dos.flush();
//            System.out.println("File "+fileName+" sent to client.");
//        } catch (Exception e) {
//            System.err.println("File does not exist!");
//        } 
//    }
//}
