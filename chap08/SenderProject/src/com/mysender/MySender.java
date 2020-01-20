package com.mysender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MySender extends Activity {
 
 private static final String action1 = "com.mysender.ACTION1";	
	
  public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
		  
   Button btn = (Button)this.findViewById(R.id.btn);
   btn.setOnClickListener(new OnClickListener(){
    //���U���s�|���檺�u�@
	public void onClick(View view) {
	 //�إ�intent����	
	 Intent intent = new Intent();
	 
	 //�]�w�T���Ψ䤺�e
	 intent.setAction(action1);
	 String msg = "�ѫB���ơA�p�ߦw��";
	 intent.putExtra("broadcastMsg", msg);
	 
	 Log.v("broadcastMsg", msg);
	 
	 //�i��s��
	 MySender.this.sendBroadcast(intent);
	} 
  });
 }
}