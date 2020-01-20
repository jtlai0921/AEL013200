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
 
 //�h�C�鼽�񤸥�
 private MediaPlayer mediaPlayer;
 
 Button loadBtn, startBtn, pauseBtn, stopBtn;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //���o���s�������
  loadBtn = (Button)this.findViewById(R.id.loadBtn);
  startBtn = (Button)this.findViewById(R.id.startBtn);
  pauseBtn = (Button)this.findViewById(R.id.pauseBtn);
  stopBtn = (Button)this.findViewById(R.id.stopBtn);
  
  //�T����s
  startBtn.setEnabled(false);
  pauseBtn.setEnabled(false);
  stopBtn.setEnabled(false);
  
  //�e�����J���s
  loadBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	try {   
	  //�إߦh�C�鼽�񤸥����	   
	  mediaPlayer = new MediaPlayer();
	  Log.v("mediaTest", "media player instanced");
	
	  //�]�w���ɸ�ƨӷ�
	  mediaPlayer.setDataSource("/sdcard/onepiece.mp3");
	  Log.v("mediaTest", "load file");
	
	  //�e���ƥ�
	  mediaPlayer.setOnCompletionListener(MyPlayer.this);
	  mediaPlayer.setOnErrorListener(MyPlayer.this);
	  mediaPlayer.setOnPreparedListener(MyPlayer.this);
	  Log.v("mediaTest", "delegate to listener");
	  
	  //�i��D�P�B�����J
	  mediaPlayer.prepareAsync();
	  Log.v("mediaTest", "prepare async");
	} catch (Exception e) {
	 Log.e("mediaTest", "load error:" + e);
	}
   }  
  });
  
  //�e��������s
  startBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	if (mediaPlayer != null && !mediaPlayer.isPlaying()) {   
	 mediaPlayer.start();
	 startBtn.setEnabled(false);
	 Log.v("mediaTest", "start play");
	} 
   }  
  });
  
  //�e���Ȱ����s
  pauseBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	if (mediaPlayer != null && mediaPlayer.isPlaying()) {
	  mediaPlayer.pause();
	  startBtn.setEnabled(true);
	  Log.v("mediaTest", "pause play");
	}
   }  
  });
  
  //�e��������s
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
 
 //�~�Ӧ�Activity
 protected void onPause() {
  super.onPause();
  //����MediaPlayer
  if (mediaPlayer != null)
   mediaPlayer.release();
 }
 
 //��@MediaPlayer.OnPreparedListener
 public void onPrepared(MediaPlayer mp) {
  //�����ɮ׸��J����, �P��Ҧ����s
  startBtn.setEnabled(true);
  pauseBtn.setEnabled(true);
  stopBtn.setEnabled(true);
  Log.v("mediaTest", "enable all button");
 }
 
 //��@MediaPlayer.OnErrorListener
 public boolean onError(MediaPlayer mp, int arg1, int arg2) {
  Log.v("mediaTest", "onError");
  return false;
 }
 
 //��@MediaPlayer.OnCompletionListener
 public void onCompletion(MediaPlayer mp) { 
  Log.v("mediaTest", "play completed");
 }
 
}