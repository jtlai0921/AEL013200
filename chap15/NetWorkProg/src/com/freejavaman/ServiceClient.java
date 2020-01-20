package com.freejavaman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceClient extends Activity {

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
	doWebService("add");
   }
  });
  
  //�����k�B��
  btn2.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doWebService("dec");
   }
  });
  
  //���歼�k�B��
  btn3.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doWebService("multiple");
   }
  });  
  
  //���氣�k�B��
  btn4.setOnClickListener(new View.OnClickListener() {		
   public void onClick(View view) {
	doWebService("div");
   }
  });
 }
 
 //����U��HTML�����e
 private void doWebService(String actType){
   try {	 
	//���V���ѪA�Ȫ�URL
	URL url = new URL("http://192.168.1.100:8080/AndroidWeb/AndroidServlet");
	URLConnection urlCONN = url.openConnection();
	urlCONN.setDoOutput(true);
	
    //�զX�ұ��ǻ����Ѽ�
    String data = "actType=" + actType + "&" + 
	              "xValue=" + xValue.getText() + "&" +
                  "yValue=" + yValue.getText();
	
    //�إ߿�X��Ƭy�A�ô���Ѽ�
    OutputStreamWriter wr = new OutputStreamWriter(urlCONN.getOutputStream(), "Big5");
	wr.write(data);
	wr.flush();
			    
	//���o���A�ݪ����浲�G
	BufferedReader rd = new BufferedReader(new InputStreamReader(urlCONN.getInputStream()));
	String returnMsg = "";
	String line;

	//�զX�Ҧ����浲�G
	while ((line = rd.readLine()) != null) {
	 returnMsg += line;
	}
	
	//������X�J��Ƭy
    wr.close();
	rd.close();
	wr = null;
	rd = null;
	
	Log.v("network", "returnMsg:" + returnMsg);
	
	//��ܰ��浲�G
	if (returnMsg.indexOf("<Error>") != -1) {
	 //���浲�G�o�Ͱ��D
	 reslut.setText("�B��L�{�o�Ͱ��D");
	} else if (returnMsg.indexOf("<Result>") != -1) {
	 //���o�B�⵲�G���r����ޭ�
	 int sInx = returnMsg.indexOf("<Result>");
	 sInx += "<Result>".length();	 
	 int eInx = returnMsg.indexOf("</Result>");
	 
	 Log.v("network", "sInx:" + sInx);
	 Log.v("network", "eInx:" + eInx);
	 
	 reslut.setText(returnMsg.substring(sInx, eInx));
	 
	} else {
	 //�L�k�P�_���A�ݰ��浲�G	
     reslut.setText("�L�k�P�_���浲�G");
	}
	
   } catch (Exception e) {
	reslut.setText("������~�G" + e);
   }
 }
 
}