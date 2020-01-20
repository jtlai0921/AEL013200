package com.freejavaman;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GeneServer {
 public static void main(String args[]){
  try{
   //�إ�ServerSocket����, ��ť��16888��
   ServerSocket srvSocket = new ServerSocket(16888);
		    
   System.out.println("Gene Server start at port 16888");
		     
   //���ݨϥΪ̿�J
   while (true) {
    Socket socket = srvSocket.accept();
    
    System.out.println("client connected");
    GeneService myService = new GeneService(socket);
   }
  } catch(IOException e){
    System.err.println("Gene Server, error:" + e);
  }
 }
}