import java.net.*;
import java.io.*;

public class TCPServer extends Object{

 public static void main(String args[]){
  try{
   //�إ�ServerSocket����, ��ť��16888��
   ServerSocket srvSocket = new ServerSocket(16888);
    
   System.out.println("Server start");
     
   //���ݨϥΪ̿�J
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
    System.out.println("datas from client:" + datas);
	
    //��Ѹ�Ƥ��e
    String[] dataSplit = datas.split("_");    
    int xValue, yValue;
    float result;
    
    //���o�B����
    try {
     xValue = Integer.parseInt(dataSplit[1]);	
     yValue = Integer.parseInt(dataSplit[2]);
    } catch (Exception e) {
     xValue = 0;	
     yValue = 0;
    }
    
    //�����M�A��
    if (dataSplit[0] != null && dataSplit[0].equals("add")) {
 	  //�[�k  
      result = (xValue + yValue);
    } else if (dataSplit[0] != null && dataSplit[0].equals("dec")) {
 	  //��k  
      result = (xValue - yValue);
    } else if (dataSplit[0] != null && dataSplit[0].equals("multiple")) {
 	  //���k  
      result = (xValue * yValue);
    } else if (dataSplit[0] != null && dataSplit[0].equals("div")) {
 	  //���k  
      try {	
       result = (xValue / yValue);
      } catch (Exception e) {
    	//�קK���s���D  
    	result = 0;
      }
    } else {
 	 //�L�k�P�_�ШD���e  
     result = 0;
    }
    
    //�^�ǰ��浲�G
    dos.writeBytes("" + result + "\n");    
   }
  } catch (Exception e){
   System.out.println(e);	
  } 
 }
}