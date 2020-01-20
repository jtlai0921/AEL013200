package com.freejavaman;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MyPlayer2 extends Activity implements 
                                       MediaPlayer.OnCompletionListener, 
                                       MediaPlayer.OnErrorListener,
                                       MediaPlayer.OnPreparedListener
 {
 
 //�h�C�鼽�񤸥�
 private MediaPlayer mediaPlayer;
 private AudioManager audioMgr;
 
 private Button loadBtn, startBtn, pauseBtn, stopBtn;
 private Button upBtn, downBtn;
 
 private ProgressBar myProgressBar;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main2);
  
  //���o���s�������
  loadBtn = (Button)this.findViewById(R.id.loadBtn);
  startBtn = (Button)this.findViewById(R.id.startBtn);
  pauseBtn = (Button)this.findViewById(R.id.pauseBtn);
  stopBtn = (Button)this.findViewById(R.id.stopBtn);
  
  upBtn = (Button)this.findViewById(R.id.upBtn);
  downBtn = (Button)this.findViewById(R.id.downBtn);
  
  //�T����s
  startBtn.setEnabled(false);
  pauseBtn.setEnabled(false);
  stopBtn.setEnabled(false);
  
  //�վ㭵�q������
  audioMgr = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
  
  //��ܶi��
  myProgressBar = (ProgressBar)this.findViewById(R.id.myProgressBar);
  
  int volume = audioMgr.getStreamVolume(AudioManager.STREAM_MUSIC);
  
  //����n���Ҧ�
  //checkAudioMode();  
  //displayVolumn();
  
  Log.v("mediaTest", "volume:" + volume);
  myProgressBar.setProgress(volume);
	
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
	  mediaPlayer.setOnCompletionListener(MyPlayer2.this);
	  mediaPlayer.setOnErrorListener(MyPlayer2.this);
	  mediaPlayer.setOnPreparedListener(MyPlayer2.this);
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
  
  //�e�����q�դj���s
  upBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	//���q�դj
	audioMgr.adjustVolume(AudioManager.ADJUST_RAISE, 0);
	
	//��ܭ��q�j�p
	int volume = audioMgr.getStreamVolume(AudioManager.STREAM_MUSIC);
	Log.v("mediaTest", "increase volume:" + volume);
	//displayVolumn();
	myProgressBar.setProgress(volume);
   }  
  });
  
  //�e�����q�դp���s
  downBtn.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	//���q�դp   
	audioMgr.adjustVolume(AudioManager.ADJUST_LOWER, 0);
	
	//��ܭ��q�j�p
	int volume = audioMgr.getStreamVolume(AudioManager.STREAM_MUSIC);
	Log.v("mediaTest", "decrease volume:" + volume);
	//displayVolumn();
	myProgressBar.setProgress(volume);
   }  
  });  
 }
 
 private void displayVolumn() {	 
  Log.v("mediaTest", "sound MAX ALARM:" + audioMgr.getStreamMaxVolume(AudioManager.STREAM_ALARM));
  Log.v("mediaTest", "sound MAX DTMF:" + audioMgr.getStreamMaxVolume(AudioManager.STREAM_DTMF));
  Log.v("mediaTest", "sound MAX MUSIC:" + audioMgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
  Log.v("mediaTest", "sound MAX NOTIFICATION:" + audioMgr.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
  Log.v("mediaTest", "sound MAX RING:" + audioMgr.getStreamMaxVolume(AudioManager.STREAM_RING));
  Log.v("mediaTest", "sound MAX SYSTEM:" + audioMgr.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
  Log.v("mediaTest", "sound MAX VOICE:" + audioMgr.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
  
  Log.v("mediaTest", "sound ALARM:" + audioMgr.getStreamVolume(AudioManager.STREAM_ALARM));
  Log.v("mediaTest", "sound DTMF:" + audioMgr.getStreamVolume(AudioManager.STREAM_DTMF));
  Log.v("mediaTest", "sound MUSIC:" + audioMgr.getStreamVolume(AudioManager.STREAM_MUSIC));
  Log.v("mediaTest", "sound NOTIFICATION:" + audioMgr.getStreamVolume(AudioManager.STREAM_NOTIFICATION));
  Log.v("mediaTest", "sound RING:" + audioMgr.getStreamVolume(AudioManager.STREAM_RING));
  Log.v("mediaTest", "sound SYSTEM:" + audioMgr.getStreamVolume(AudioManager.STREAM_SYSTEM));
  Log.v("mediaTest", "sound VOICE:" + audioMgr.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
 }
 
 private void checkAudioMode() {
  if (audioMgr != null) {
    int mode = audioMgr.getMode();
    switch(mode) {
     case AudioManager.MODE_NORMAL:
    	  Log.v("mediaTest", "MODE_NORMAL");
   	      break;
     case AudioManager.MODE_RINGTONE:
    	  Log.v("mediaTest", "MODE_RINGTONE");
   	      break;
     case AudioManager.MODE_IN_CALL:
   	      Log.v("mediaTest", "MODE_IN_CALL");
   	      break;
     /*case AudioManager.MODE_IN_COMMUNICATION:
   	      Log.v("mediaTest", "MODE_IN_COMMUNICATION");
   	      break; */  	   
     default:break;
    };
  }
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