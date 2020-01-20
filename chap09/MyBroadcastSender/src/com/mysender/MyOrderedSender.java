package com.mysender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyOrderedSender extends Activity {

 private static final String MyAction = "com.mybroadcast.action.OrderedAction";	
	
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
   
   //���o�o�e�s���t�Ϊ����s
   Button btn = (Button)this.findViewById(R.id.btn);
   
   //�e���I����s���ƥ�
   btn.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {
	 //�إߥ]�q�s����T������	
	 Intent intent = new Intent();
	 
	 //�]�w�����ѧO�X
	 intent.setAction(MyAction);
	 
	 //�]�w�n�s������T
	 intent.putExtra("myOrderedMsg", "�s���}�l�I");
	 
	 //�i��s��
	 //MyOrderedSender.this.sendOrderedBroadcast(intent, "android.permission.myBroadcastPermission");
	 MyOrderedSender.this.sendOrderedBroadcast(intent, null);
	}  
   });   
 }
}