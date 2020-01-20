package com.myreceiver;

import android.content.*;
import android.util.Log;
import android.widget.Toast;

//�S��GUI�A��ª��s��������
public class MyReceiver extends BroadcastReceiver {
  
  private static int cnt = 0;
  
  public MyReceiver() {
   super();	  
   MyReceiver.cnt++;
   Log.v("broadcast", "MyReceiver contruct, count:" + MyReceiver.cnt);
  }
  
  //������s�����T��	
  public void onReceive(Context context, Intent intent) {
   String msg = intent.getStringExtra("myMsg");
   Log.v("broadcast", "receive:" + msg);
   Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
   
   //���ռȰ�30��A�ΨӼ����u�@�W�L10����������
   try {	   
	new Thread().sleep(1000 * 30);   
	Log.v("broadcast", "wake up");
   } catch (Exception e) {
	Log.e("broadcast", "sleep err:" + e);   
   }
  }
  
  protected void finalize() throws Throwable {
   super.finalize();
   MyReceiver.cnt--;
   Log.v("broadcast", "MyReceiver finalize, count:" + MyReceiver.cnt);
  }
}