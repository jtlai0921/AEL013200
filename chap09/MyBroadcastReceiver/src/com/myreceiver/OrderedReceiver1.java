package com.myreceiver;

import android.content.*;
import android.os.Bundle;
import android.util.Log;

//�S��GUI�A��ª��s��������
public class OrderedReceiver1 extends BroadcastReceiver {
  
  //������s�����T��	
  public void onReceive(Context context, Intent intent) {
   //���o���쪺�s��	  
   String msg = intent.getStringExtra("myOrderedMsg");
   Log.v("broadcast", "receive(1):" + msg);
   
   //���o�ѤW�@�Ӽs���줤���{���A�Ҷǻ��L�Ӫ��T��
   Bundle bundle = getResultExtras(false);
   
   if (bundle == null) {
	 Log.v("broadcast", "receive(1):�L���浲�G");
   } else {
	 Log.v("broadcast", "receive(1):���浲�G:" + bundle.getString("myResult"));
   }   
  }
}