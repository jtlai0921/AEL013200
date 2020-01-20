package com.freejavaman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceClient2 extends Activity {

 //���ѿ�X�B��Ѽ�	
 EditText xValue, yValue;
 TextView reslut;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
  
  //���o��ƿ�J�P��ܤ�����
  xValue = (EditText)this.findViewById(R.id.xValue);
  yValue = (EditText)this.findViewById(R.id.yValue);
  reslut = (TextView)this.findViewById(R.id.reslut);
  
  //����B�⤧���s
  Button btn1 = (Button)this.findViewById(R.id.btn1);
  Button btn2 = (Button)this.findViewById(R.id.btn2);
  Button btn3 = (Button)this.findViewById(R.id.btn3);
  Button btn4 = (Button)this.findViewById(R.id.btn4);
  
  //����[�k�B��
  btn1.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("add");
   }
  });
  
  //�����k�B��
  btn2.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("dec");
   }
  });
  
  //���歼�k�B��
  btn3.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("multiple");
   }
  });  
  
  //���氣�k�B��
  btn4.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doTCPService("div");
   }
  });
 }
 
 //����U��HTML�����e
 private void doTCPService(String actType){
  try{
   //�إ߻P���A�ݪ��s�u
   Socket socket = new Socket("192.168.1.100", 16888);
	    
   //���o����A�ݪ���Ƭy
   DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
   DataInputStream dis = new DataInputStream(socket.getInputStream());
	    
   //����B����    
   dos.writeBytes(actType + "_" + xValue.getText() + "_" + yValue.getText() + "\n");
	    
   //��������ܰT��
   String reslutStr = dis.readLine();
   Log.v("network", "doTCPService reslutStr:" + reslutStr);
   reslut.setText(reslutStr);
  } catch(IOException e){
   Log.e("network", "doTCPService error:" + e);
  }  
 }
 
}