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
 
 //�y������	
 private TextToSpeech ttSpeech;
 
 /*private TextToSpeech.OnUtteranceCompletedListener ucListener = new  TextToSpeech.OnUtteranceCompletedListener() { 
  public void onUtteranceCompleted(String utteranceId) {
  } 	
 };*/
 
 //TTS��������l�ƪ���
 private TextToSpeech.OnInitListener initListener = new TextToSpeech.OnInitListener() {
   public void onInit(int status) {
	   
    //en - USA
    //zh - TWN
    Locale locale = new Locale("en" , "USA", "");
    ttSpeech.setLanguage(locale);
    
    //�ھڮɰϡA�P�_�O�_���䴩�y��
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
  
  //�إ߻y�����󤧪������
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
  //����y���귽
  ttSpeech.shutdown();
 } 
}

