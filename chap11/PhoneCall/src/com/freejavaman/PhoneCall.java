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
  
  //輸入電話號碼
  pNum = (EditText)this.findViewById(R.id.pNum);
  
  //輸入簡訊內容
  sms = (EditText)this.findViewById(R.id.sms);
  
  //撥號與發送簡訊鈕
  cBtn = (Button)this.findViewById(R.id.cBtn);
  sBtn = (Button)this.findViewById(R.id.sBtn); 
  
  //進行撥號鈕之事件委任
  cBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	//判斷是否有輸入電話號碼
	String pNumStr = pNum.getText().toString();
	
	if (pNumStr != null && !pNumStr.equals("")) {
	 //建立Intent物件，並執行撥號功能
	 //Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pNumStr));
	 Intent callIntent = new Intent();
	 callIntent.setAction(Intent.ACTION_CALL);
	 callIntent.setData(Uri.parse("tel:" + pNumStr));
	 
	 PhoneCall.this.startActivity(callIntent);
	 
	 //清空輸入內容
	 pNum.setText("");
	}
   }  
  });
  
  //進行簡訊鈕之事件委任
  sBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	//判斷是否有輸入電話號碼, 以及簡訊內容
	String pNumStr = pNum.getText().toString();
	String smsStr = sms.getText().toString();
	
	if (pNumStr != null && !pNumStr.equals("") &&
		smsStr != null && !smsStr.equals("") ) {
	 //取得簡訊發送管理元件	
	 SmsManager smsMgr = SmsManager.getDefault();
	 
	 //建立包裹相關訊息的Intent物件
	 PendingIntent pIntent = PendingIntent.getBroadcast(PhoneCall.this, 0, new Intent(), 0);
	 
	 //設定收話方電話號碼，與簡訊內容
	 smsMgr.sendTextMessage(pNumStr, null, smsStr, pIntent, null);
	 
	 //清空輸入內容
	 pNum.setText("");
	 sms.setText("");
	}
   }  
  });
  
 }
}