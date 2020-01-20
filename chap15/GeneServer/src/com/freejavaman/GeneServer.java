package com.freejavaman;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GeneServer {
 public static void main(String args[]){
  try{
   //建立ServerSocket物件, 傾聽於16888埠
   ServerSocket srvSocket = new ServerSocket(16888);
		    
   System.out.println("Gene Server start at port 16888");
		     
   //等待使用者輸入
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