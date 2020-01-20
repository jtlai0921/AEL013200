package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UI_EditText extends Activity {
	
	EditText edit;
	TextView txt;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
      txt = (TextView)this.findViewById(R.id.txt1);
      edit = (EditText)this.findViewById(R.id.edit1);      
      edit.setOnKeyListener(new View.OnKeyListener() {		
		public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
		 txt.setText(edit.getText()); 
		 return false;
		}
	  });
      
      //Editable editAble = edit.getText();
      //Log.v("UI_EditText", editAble.toString());
      
      //edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
      //edit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }
}