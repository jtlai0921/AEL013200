package com.freejavaman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class TelMgr extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //取得手機相關資訊管理員
  TelephonyManager tMgr = (TelephonyManager)this.getSystemService(this.TELEPHONY_SERVICE);
  
  //顯示相關資訊
  if (tMgr != null) {
   //SMI卡相關API	  
   Log.v("tMgr", "SIM卡：");
   Log.v("tMgr", "  狀態：" + convertSIMStat(tMgr.getSimState()));	  
   Log.v("tMgr", "  序號：" + tMgr. getSimSerialNumber());
   Log.v("tMgr", "  國別：" + tMgr.getSimCountryIso());
   Log.v("tMgr", "  供應商代碼：" + tMgr.getSimOperator());
   Log.v("tMgr", "  供應商名稱：" + tMgr.getSimOperatorName());
   		 	 
   Log.v("tMgr", "電信網路業者：");
   Log.v("tMgr", "  國別：" + tMgr.getNetworkCountryIso());
   Log.v("tMgr", "  代碼：" + tMgr.getNetworkOperator());
   Log.v("tMgr", "  名稱：" + tMgr.getNetworkOperatorName());   
   Log.v("tMgr", "  種類：" + convertNetworkType(tMgr.getNetworkType()));
   Log.v("tMgr", "  國際漫遊：" + tMgr.isNetworkRoaming());
   
   //Returns a constant indicating the device phone type.
   Log.v("tMgr", "行動通訊種類：" + convertPhoneType(tMgr.getPhoneType()));
   
   //Returns the unique device ID, for example, the IMEI for GSM and the MEID or ESN for CDMA phones.
   Log.v("tMgr", "手機ID：" + tMgr.getDeviceId());
   
   //Returns the phone number string for line 1, for example, the MSISDN for a GSM phone.
   Log.v("tMgr", "手機號碼：" + tMgr.getLine1Number());
   
   //Returns the software version number for the device, for example, the IMEI/SV for GSM phones.
   Log.v("tMgr", "手機軟體版本：" + tMgr.getDeviceSoftwareVersion());
   
   //Returns the unique subscriber ID, for example, the IMSI for a GSM phone.
   Log.v("tMgr", "訂閱者ID：" + tMgr.getSubscriberId());
   
   //Returns a constant indicating the call state (cellular) on the device.
   Log.v("tMgr", "使用狀態：" + convertCallState(tMgr.getCallState()));  
   
   
  }
 }
  
 //判斷手機使用狀態
 private String convertCallState(int statInx) {
  String stat = "";
  
  switch(statInx) {	 
   case TelephonyManager.CALL_STATE_IDLE:
	    stat = "IDLE";
	    break;
   case TelephonyManager.CALL_STATE_OFFHOOK:
	    stat = "OFFHOOK";
	    break;
   case TelephonyManager.CALL_STATE_RINGING:
	    stat = "RINGING";
	    break;
   default:
	   stat = "null";
        break;
  }
  
  return stat;
 }	
	
 //判斷行動通訊種類
 private String convertPhoneType(int typeInx) {
  String type = "";
	  
  switch(typeInx) {
   case TelephonyManager.PHONE_TYPE_NONE:
	    type = "NONE";
	    break;
   case TelephonyManager.PHONE_TYPE_GSM:
	    type = "GSM";
	    break;
   case TelephonyManager.PHONE_TYPE_CDMA:
	    type = "CDMA";
	    break;   
   default:
	    type = "NONE";
        break;	    
  }
  
  return type;
 } 
 
 //判斷網路種類
 private String convertNetworkType(int typeInx) {
  String type = "";
  
  switch(typeInx) {
   case TelephonyManager.NETWORK_TYPE_UNKNOWN:
	    type = "UNKNOWN";
	    break;
   case TelephonyManager.NETWORK_TYPE_GPRS:
	    type = "GPRS";
	    break;
   case TelephonyManager.NETWORK_TYPE_EDGE:
	    type = "EDGE";
	    break;
   case TelephonyManager.NETWORK_TYPE_UMTS:
	    type = "UMTS";
	    break;
   case TelephonyManager.NETWORK_TYPE_HSDPA:
	    type = "HSDPA";
	    break;
   case TelephonyManager.NETWORK_TYPE_HSUPA:
	    type = "HSUPA";
	    break;
   case TelephonyManager.NETWORK_TYPE_HSPA:
	    type = "HSPA";
	    break;
   case TelephonyManager.NETWORK_TYPE_CDMA:
	    type = "CDMA";
	    break;
   case TelephonyManager.NETWORK_TYPE_EVDO_0:
	    type = "EVDO_0";
	    break;
   case TelephonyManager.NETWORK_TYPE_EVDO_A:
	    type = "EVDO_A";
	    break;   
   case TelephonyManager.NETWORK_TYPE_1xRTT:
	    type = "1xRTT";
	    break;
   case TelephonyManager.NETWORK_TYPE_IDEN:
	    type = "IDEN";
	    break;   
   default:
	    type = "UNKNOWN";
	    break;
  }
  
  return type;
 }
 
 
 //判斷SIM卡的狀態
 private String convertSIMStat(int statInx) {
  String stat = "";
  
  switch (statInx) {
   case TelephonyManager.SIM_STATE_UNKNOWN:
	    stat = "UNKNOWN";
	    break;
   case TelephonyManager.SIM_STATE_ABSENT:
	    stat = "ABSENT";
	    break;
   case TelephonyManager.SIM_STATE_PIN_REQUIRED:
	    stat = "PIN_REQUIRED";
	    break;
   case TelephonyManager.SIM_STATE_PUK_REQUIRED:
	    stat = "PUK_REQUIRED";
	    break;
   case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
	    stat = "NETWORK_LOCKED";
	    break;
   case TelephonyManager.SIM_STATE_READY:
	    stat = "STATE_READY";
	    break;
   default:
	   stat = "UNKNOWN";
       break;  
  }
  
  return stat;
 }
}