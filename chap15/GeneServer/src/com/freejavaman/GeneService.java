package com.freejavaman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Hashtable;

public class GeneService extends Thread {

 //�P�Ȥ�ݫإߪ���Ƭy
 DataInputStream dis;
 DataOutputStream dos;
			    
 public GeneService(Socket socket){	  
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
    //�Ȥ�ݩҶǻ���������ơA�p�GA_B_D_E_G_I
    String datas = dis.readLine();
	    
    System.out.println("datas from client:" + datas);
		
    //�N���������s��Hashtable��Ƶ��c����
    Hashtable cityHash = new Hashtable();
    
    if (datas != null && !datas.equals("")) {
     //�Ȥ�Ҷǻ��������N�X�����A�ѩ��u�j�}
     String[] cities = datas.split("_");
	     
     //�N�����N�X�x�s��Hashtable
     for (int i = 0; i < cities.length; i++) {    	 
      //�קK���Ъ������N�X
  	  if (!cityHash.containsKey(cities[i])) {
  	   cityHash.put(cities[i].charAt(0), cities[i].charAt(0));
  	  }
     }
    } 
	    
    //�إ߶��ݹB��A�P�ɡA���o���浲�G
    if (cityHash.size() > 0) {
     String result = new TSPCloud(cityHash).doCloudOP();     
     //�^�ǰ��浲�G
 	 dos.writeBytes(result + "\n\r");
    } else {
     //�ǻ������T���������	
     dos.writeBytes("ERR:data not correct\n\r");
    }    
   }
  } catch (Exception e){
   System.out.println(e);	
  } 
 }
}
