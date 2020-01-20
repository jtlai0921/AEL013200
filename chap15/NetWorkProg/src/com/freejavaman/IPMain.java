package com.freejavaman;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class IPMain extends Activity {
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //�T����ܥΪ�����
  TextView ipView = (TextView)this.findViewById(R.id.ipView);
  TextView macView = (TextView)this.findViewById(R.id.macView);
  
  //��ܤ��IP
  ipView.setText("���aIP:" + getLocalIP());
  macView.setText("���dMAC:" + getLocalMacAddress());
 }
 
 //���o���aIP��}
 private String getLocalIP(){
  String ip = "";
  
  try {
   //���o�Ҧ�������������	  
   Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
   while (en.hasMoreElements()) {     
    
	//���o�䤤���@������   
	NetworkInterface intf = en.nextElement();
	/*
	 String macAdd = "";

     //�q�����������A���oMAC��}
     byte[] mac = intf.getHardwareAddress();
     if (mac != null) {
      //�NMAC��}�A�ഫ�� xx-xx-xx ���榡
      for (int i = 0; i < mac.length; i++) {
       System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
      }
     } else {
      //�L�k���oMAC��}
     }
    */
	
	//���o�Ӻ��d�W�Ҧ��j�w��IP��}
    Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
    
    while (enumIpAddr.hasMoreElements()) {
     //���o�䤤���@��IP��ơA�åB��ܤ�	
     InetAddress inetAddress = enumIpAddr.nextElement();     
     if (!inetAddress.isLoopbackAddress()) {     
      ip = inetAddress.getHostAddress().toString();
      Log.v("network", "ip:" + ip);
     }     
    }     
   }     
  } catch (Exception e) {
   ip = "check IP error:" + e;
  }
  
  return ip;
 }
 
 //���oWi-Fi�L�u���d��MAC��}
 public String getLocalMacAddress() {     
  WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);     
  WifiInfo info = wifi.getConnectionInfo();  
  return info.getMacAddress();     
 }     
}