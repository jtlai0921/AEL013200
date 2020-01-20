package com.freejavaman;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyPlayer extends Activity implements 
                                       MediaPlayer.OnCompletionListener, 
                                       MediaPlayer.OnErrorListener,
                                       MediaPlayer.OnPreparedListener
 {
 
 //多媒體播放元件
 private MediaPlayer mediaPlayer;
 
 Button loadBtn, startBtn, pauseBtn, stopBtn;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //取得按鈕物件實體
  loadBtn = (Button)this.findViewById(R.id.loadBtn);
  startBtn = (Button)this.findViewById(R.id.startBtn);
  pauseBtn = (Button)this.findViewById(R.id.pauseBtn);
  stopBtn = (Button)this.findViewById(R.id.stopBtn);
  
  //禁能按鈕
  startBtn.setEnabled(false);
  pauseBtn.setEnabled(false);
  stopBtn.setEnabled(false);
  
  //委任載入按鈕
  loadBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	try {   
	  //建立多媒體播放元件實體	   
	  mediaPlayer = new MediaPlayer();
	  Log.v("mediaTest", "media player instanced");
	
	  //設定音檔資料來源
	  mediaPlayer.setDataSource("/sdcard/onepiece.mp3");
	  Log.v("mediaTest", "load file");
	
	  //委任事件
	  mediaPlayer.setOnCompletionListener(MyPlayer.this);
	  mediaPlayer.setOnErrorListener(MyPlayer.this);
	  mediaPlayer.setOnPreparedListener(MyPlayer.this);
	  Log.v("mediaTest", "delegate to listener");
	  
	  //進行非同步的載入
	  mediaPlayer.prepareAsync();
	  Log.v("mediaTest", "prepare async");
	} catch (Exception e) {
	 Log.e("mediaTest", "load error:" + e);
	}
   }  
  });
  
  //委任播放按鈕
  startBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	if (mediaPlayer != null && !mediaPlayer.isPlaying()) {   
	 mediaPlayer.start();
	 startBtn.setEnabled(false);
	 Log.v("mediaTest", "start play");
	} 
   }  
  });
  
  //委任暫停按鈕
  pauseBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	if (mediaPlayer != null && mediaPlayer.isPlaying()) {
	  mediaPlayer.pause();
	  startBtn.setEnabled(true);
	  Log.v("mediaTest", "pause play");
	}
   }  
  });
  
  //委任停止按鈕
  stopBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	if (mediaPlayer != null) {
      mediaPlayer.stop();      
      try {
	   mediaPlayer.prepare();
      } catch (Exception e) {
       Log.v("mediaTest", "stop play (call prepare)");
      }
	  Log.v("mediaTest", "stop play");
	}  
   }  
  });
 }
 
 //繼承自Activity
 protected void onPause() {
  super.onPause();
  //釋放MediaPlayer
  if (mediaPlayer != null)
   mediaPlayer.release();
 }
 
 //實作MediaPlayer.OnPreparedListener
 public void onPrepared(MediaPlayer mp) {
  //完成檔案載入之後, 致能所有按鈕
  startBtn.setEnabled(true);
  pauseBtn.setEnabled(true);
  stopBtn.setEnabled(true);
  Log.v("mediaTest", "enable all button");
 }
 
 //實作MediaPlayer.OnErrorListener
 public boolean onError(MediaPlayer mp, int arg1, int arg2) {
  Log.v("mediaTest", "onError");
  return false;
 }
 
 //實作MediaPlayer.OnCompletionListener
 public void onCompletion(MediaPlayer mp) { 
  Log.v("mediaTest", "play completed");
 }
 
}