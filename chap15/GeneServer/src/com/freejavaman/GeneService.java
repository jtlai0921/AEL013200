package com.freejavaman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Hashtable;

public class GeneService extends Thread {

 //與客戶端建立的資料流
 DataInputStream dis;
 DataOutputStream dos;
			    
 public GeneService(Socket socket){	  
  try {	
   //取得與客戶端之間的資料流
   dis = new DataInputStream(socket.getInputStream());
   dos = new DataOutputStream(socket.getOutputStream());    	
		  
   //開始執行緒
   start();
  } catch (Exception e){
   System.out.println(e);	
  } 
 }
			 
 public void run(){  
  try {   
   while (true) {
    //客戶端所傳遞的城市資料，如：A_B_D_E_G_I
    String datas = dis.readLine();
	    
    System.out.println("datas from client:" + datas);
		
    //將城市資料轉存到Hashtable資料結構之中
    Hashtable cityHash = new Hashtable();
    
    if (datas != null && !datas.equals("")) {
     //客戶所傳遞的城市代碼之間，由底線隔開
     String[] cities = datas.split("_");
	     
     //將城市代碼儲存至Hashtable
     for (int i = 0; i < cities.length; i++) {    	 
      //避免重覆的城市代碼
  	  if (!cityHash.containsKey(cities[i])) {
  	   cityHash.put(cities[i].charAt(0), cities[i].charAt(0));
  	  }
     }
    } 
	    
    //建立雲端運算，同時，取得執行結果
    if (cityHash.size() > 0) {
     String result = new TSPCloud(cityHash).doCloudOP();     
     //回傳執行結果
 	 dos.writeBytes(result + "\n\r");
    } else {
     //傳遞不正確的城市資料	
     dos.writeBytes("ERR:data not correct\n\r");
    }    
   }
  } catch (Exception e){
   System.out.println(e);	
  } 
 }
}
