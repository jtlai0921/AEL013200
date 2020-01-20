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
    
    //根據指定的顯示名字執行刪除
    String where = Media.DISPLAY_NAME + "=?";
    String[] selectionArgs = {"view"};
    
    //執行刪除
    cResolver.delete(Media.EXTERNAL_CONTENT_URI, where, selectionArgs);
   
    Log.v("content", "delete done");
  } catch (Exception e) {
   Log.e("content", "err when delete:" + e);	  
  }
 }
}