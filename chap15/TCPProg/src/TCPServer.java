import java.net.*;
import java.io.*;

public class TCPServer extends Object{

 public static void main(String args[]){
  try{
   //建立ServerSocket物件, 傾聽於16888埠
   ServerSocket srvSocket = new ServerSocket(16888);
    
   System.out.println("Server start");
     
   //等待使用者輸入
   while (true) {
    Socket socket = srvSocket.accept(); 
    ServiceObject myService = new ServiceObject(socket);
   }
  } catch(IOException e){
    System.err.println(e);
  }
 }
}

class ServiceObject extends Thread {
	 
 //與客戶端建立的資料流
 DataInputStream dis;
 DataOutputStream dos;
	    
 public ServiceObject(Socket socket){	  
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
    //客戶端所傳遞的運算資料
    String datas = dis.readLine();
    System.out.println("datas from client:" + datas);
	
    //拆解資料內容
    String[] dataSplit = datas.split("_");    
    int xValue, yValue;
    float result;
    
    //取得運算資料
    try {
     xValue = Integer.parseInt(dataSplit[1]);	
     yValue = Integer.parseInt(dataSplit[2]);
    } catch (Exception e) {
     xValue = 0;	
     yValue = 0;
    }
    
    //執行對映服務
    if (dataSplit[0] != null && dataSplit[0].equals("add")) {
 	  //加法  
      result = (xValue + yValue);
    } else if (dataSplit[0] != null && dataSplit[0].equals("dec")) {
 	  //減法  
      result = (xValue - yValue);
    } else if (dataSplit[0] != null && dataSplit[0].equals("multiple")) {
 	  //乘法  
      result = (xValue * yValue);
    } else if (dataSplit[0] != null && dataSplit[0].equals("div")) {
 	  //除法  
      try {	
       result = (xValue / yValue);
      } catch (Exception e) {
    	//避免除零問題  
    	result = 0;
      }
    } else {
 	 //無法判斷請求內容  
     result = 0;
    }
    
    //回傳執行結果
    dos.writeBytes("" + result + "\n");    
   }
  } catch (Exception e){
   System.out.println(e);	
  } 
 }
}