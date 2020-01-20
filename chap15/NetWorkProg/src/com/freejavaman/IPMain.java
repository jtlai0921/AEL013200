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
  
  //訊息顯示用的元件
  TextView ipView = (TextView)this.findViewById(R.id.ipView);
  TextView macView = (TextView)this.findViewById(R.id.macView);
  
  //顯示手機IP
  ipView.setText("本地IP:" + getLocalIP());
  macView.setText("網卡MAC:" + getLocalMacAddress());
 }
 
 //取得本地IP位址
 private String getLocalIP(){
  String ip = "";
  
  try {
   //取得所有網路介面元件	  
   Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
   while (en.hasMoreElements()) {     
    
	//取得其中之一的介面   
	NetworkInterface intf = en.nextElement();
	/*
	 String macAdd = "";

     //從網路介面中，取得MAC位址
     byte[] mac = intf.getHardwareAddress();
     if (mac != null) {
      //將MAC位址，轉換成 xx-xx-xx 的格式
      for (int i = 0; i < mac.length; i++) {
       System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
      }
     } else {
      //無法取得MAC位址
     }
    */
	
	//取得該網卡上所有綁定的IP位址
    Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
    
    while (enumIpAddr.hasMoreElements()) {
     //取得其中之一的IP資料，並且顯示之	
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
 
 //取得Wi-Fi無線網卡之MAC位址
 public String getLocalMacAddress() {     
  WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);     
  WifiInfo info = wifi.getConnectionInfo();  
  return info.getMacAddress();     
 }     
}