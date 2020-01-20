package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UI_Toast extends Activity {
	
	EditText edit;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     //建立EditText與Button物件實體
     edit = (EditText)this.findViewById(R.id.edit);        
     Button btn = (Button)this.findViewById(R.id.btn);
     
     //委任按鈕點選事件
     btn.setOnClickListener(new View.OnClickListener(){
	   public void onClick(View arg0) {
	    //取得EditText所輸入的文字
		//並且顯示在Toast元件之中
		Toast.makeText(UI_Toast.this, edit.getText(), Toast.LENGTH_LONG).show();
	   }
     });
     
    }
}