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
  
  //���o���������T�޲z��
  TelephonyManager tMgr = (TelephonyManager)this.getSystemService(this.TELEPHONY_SERVICE);
  
  //��ܬ�����T
  if (tMgr != null) {
   //SMI�d����API	  
   Log.v("tMgr", "SIM�d�G");
   Log.v("tMgr", "  ���A�G" + convertSIMStat(tMgr.getSimState()));	  
   Log.v("tMgr", "  �Ǹ��G" + tMgr. getSimSerialNumber());
   Log.v("tMgr", "  ��O�G" + tMgr.getSimCountryIso());
   Log.v("tMgr", "  �����ӥN�X�G" + tMgr.getSimOperator());
   Log.v("tMgr", "  �����ӦW�١G" + tMgr.getSimOperatorName());
   		 	 
   Log.v("tMgr", "�q�H�����~�̡G");
   Log.v("tMgr", "  ��O�G" + tMgr.getNetworkCountryIso());
   Log.v("tMgr", "  �N�X�G" + tMgr.getNetworkOperator());
   Log.v("tMgr", "  �W�١G" + tMgr.getNetworkOperatorName());   
   Log.v("tMgr", "  �����G" + convertNetworkType(tMgr.getNetworkType()));
   Log.v("tMgr", "  ��ں��C�G" + tMgr.isNetworkRoaming());
   
   //Returns a constant indicating the device phone type.
   Log.v("tMgr", "��ʳq�T�����G" + convertPhoneType(tMgr.getPhoneType()));
   
   //Returns the unique device ID, for example, the IMEI for GSM and the MEID or ESN for CDMA phones.
   Log.v("tMgr", "���ID�G" + tMgr.getDeviceId());
   
   //Returns the phone number string for line 1, for example, the MSISDN for a GSM phone.
   Log.v("tMgr", "������X�G" + tMgr.getLine1Number());
   
   //Returns the software version number for the device, for example, the IMEI/SV for GSM phones.
   Log.v("tMgr", "����n�骩���G" + tMgr.getDeviceSoftwareVersion());
   
   //Returns the unique subscriber ID, for example, the IMSI for a GSM phone.
   Log.v("tMgr", "�q�\��ID�G" + tMgr.getSubscriberId());
   
   //Returns a constant indicating the call state (cellular) on the device.
   Log.v("tMgr", "�ϥΪ��A�G" + convertCallState(tMgr.getCallState()));  
   
   
  }
 }
  
 //�P�_����ϥΪ��A
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
	
 //�P�_��ʳq�T����
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
 
 //�P�_��������
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
 
 
 //�P�_SIM�d�����A
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