package com.myreceiver;

import android.content.*;
import android.os.Bundle;
import android.util.Log;

//�S��GUI�A��ª��s��������
public class OrderedReceiver2 extends BroadcastReceiver {
  
  //������s�����T��	
  public void onReceive(Context context, Intent intent) {
   //���o���쪺�s��	  
   String msg = intent.getStringExtra("myOrderedMsg");
   Log.v("broadcast", "receive(2):" + msg);
   
   //�ǳƶǻ����U�@�ӱ����{�����T��
   Bundle bundle = new Bundle();
   bundle.putString("myResult", "11 x 8 = 88");
   setResultExtras(bundle);
  }
}