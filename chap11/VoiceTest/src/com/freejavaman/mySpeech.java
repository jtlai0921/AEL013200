package com.freejavaman;

import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class mySpeech extends Activity {
 
 //語音元件	
 private TextToSpeech ttSpeech;
 
 /*private TextToSpeech.OnUtteranceCompletedListener ucListener = new  TextToSpeech.OnUtteranceCompletedListener() { 
  public void onUtteranceCompleted(String utteranceId) {
  } 	
 };*/
 
 //TTS引擎的初始化物件
 private TextToSpeech.OnInitListener initListener = new TextToSpeech.OnInitListener() {
   public void onInit(int status) {
	   
    //en - USA
    //zh - TWN
    Locale locale = new Locale("en" , "USA", "");
    ttSpeech.setLanguage(locale);
    
    //根據時區，判斷是否有支援語言
    if (ttSpeech.isLanguageAvailable(locale) == TextToSpeech.LANG_AVAILABLE) {
      ttSpeech.setLanguage(locale);
      Log.v("mySpeech", "language support");
    } else {
      Log.v("mySpeech", "language NOT support");
    }
    
    ttSpeech.setOnUtteranceCompletedListener(ucListener);
   }	
 };
 
 EditText myText;
 Button btn;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //建立語音元件之物件實體
  ttSpeech = new TextToSpeech(this, initListener);
    
  myText = (EditText)this.findViewById(R.id.myText);
  btn = (Button)this.findViewById(R.id.btn);
  
  btn.setOnClickListener(new View.OnClickListener() {	
   public void onClick(View v) {
	String txt = myText.getText().toString();
	
	if (!txt.equals("")) {
	 ttSpeech.speak(txt, TextToSpeech.QUEUE_FLUSH, null);	
	}
   }
  });
 }

 protected void onDestroy() {
  super.onDestroy();
  //釋放語音資源
  ttSpeech.shutdown();
 } 
}

