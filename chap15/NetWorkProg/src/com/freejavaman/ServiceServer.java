package com.freejavaman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ServiceServer extends Activity {
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  try{
   //�إ�ServerSocket����, ��ť��16888��
   ServerSocket srvSocket = new ServerSocket(16888);
	
   Log.v("network", "android server start");
   
   //���ݨϥΪ̿�J
   while (true) {
    Socket socket = srvSocket.accept(); 
    ServiceObject myService = new ServiceObject(socket);
   }
  } catch(IOException e){
   Log.e("network", "android server error:" + e);
  }
 }
}

class ServiceObject extends Thread {
	 
 //�P�Ȥ�ݫإߪ���Ƭy
 DataInputStream dis;
 DataOutputStream dos;
		    
 public ServiceObject(Socket socket){	  
  try {	
   //���o�P�Ȥ�ݤ�������Ƭy
   dis = new DataInputStream(socket.getInputStream());
   dos = new DataOutputStream(socket.getOutputStream());    	
		  
   //�}�l�����
   start();
  } catch (Exception e){
   System.out.println(e);	
  } 
 }
		 
 public void run(){  
  try {   
   while (true) {
    //�Ȥ�ݩҶǻ����B����
    String datas = dis.readLine();
    Log.v("network", "android server data:" + datas);
	    
    //�^�ǰ��浲�G
    dos.writeBytes("return by server" + datas + "\n");    
   }
  } catch (Exception e){
	  Log.e("network", "android server(run) error:" + e);	
  } 
 }
}