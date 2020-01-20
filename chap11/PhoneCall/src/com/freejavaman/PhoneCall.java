package com.freejavaman;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PhoneCall extends Activity {

 EditText pNum, sms;
 Button cBtn, sBtn;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //��J�q�ܸ��X
  pNum = (EditText)this.findViewById(R.id.pNum);
  
  //��J²�T���e
  sms = (EditText)this.findViewById(R.id.sms);
  
  //�����P�o�e²�T�s
  cBtn = (Button)this.findViewById(R.id.cBtn);
  sBtn = (Button)this.findViewById(R.id.sBtn); 
  
  //�i�漷���s���ƥ�e��
  cBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	//�P�_�O�_����J�q�ܸ��X
	String pNumStr = pNum.getText().toString();
	
	if (pNumStr != null && !pNumStr.equals("")) {
	 //�إ�Intent����A�ð��漷���\��
	 //Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pNumStr));
	 Intent callIntent = new Intent();
	 callIntent.setAction(Intent.ACTION_CALL);
	 callIntent.setData(Uri.parse("tel:" + pNumStr));
	 
	 PhoneCall.this.startActivity(callIntent);
	 
	 //�M�ſ�J���e
	 pNum.setText("");
	}
   }  
  });
  
  //�i��²�T�s���ƥ�e��
  sBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	//�P�_�O�_����J�q�ܸ��X, �H��²�T���e
	String pNumStr = pNum.getText().toString();
	String smsStr = sms.getText().toString();
	
	if (pNumStr != null && !pNumStr.equals("") &&
		smsStr != null && !smsStr.equals("") ) {
	 //���o²�T�o�e�޲z����	
	 SmsManager smsMgr = SmsManager.getDefault();
	 
	 //�إߥ]�q�����T����Intent����
	 PendingIntent pIntent = PendingIntent.getBroadcast(PhoneCall.this, 0, new Intent(), 0);
	 
	 //�]�w���ܤ�q�ܸ��X�A�P²�T���e
	 smsMgr.sendTextMessage(pNumStr, null, smsStr, pIntent, null);
	 
	 //�M�ſ�J���e
	 pNum.setText("");
	 sms.setText("");
	}
   }  
  });
  
 }
}