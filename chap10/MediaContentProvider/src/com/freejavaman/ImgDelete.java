package com.freejavaman;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;

public class ImgDelete extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
   try {
    ContentResolver cResolver = this.getContentResolver();
    
    //�ھګ��w����ܦW�r����R��
    String where = Media.DISPLAY_NAME + "=?";
    String[] selectionArgs = {"view"};
    
    //����R��
    cResolver.delete(Media.EXTERNAL_CONTENT_URI, where, selectionArgs);
   
    Log.v("content", "delete done");
  } catch (Exception e) {
   Log.e("content", "err when delete:" + e);	  
  }
 }
}