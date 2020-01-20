package com.freejavaman;

import java.io.OutputStream;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;

public class ImgInsert extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //取得檔案來源
  Bitmap sourceImg = null;
  try {
   sourceImg = BitmapFactory.decodeFile("/sdcard/tmp/trip.jpg");
   Log.v("content", "read img done");
  }catch (Exception e) {
   Log.e("content", "read img err:" + e);	  
  }
  
  //取得ContentResolver工具物件實體
  ContentResolver cResolver = this.getContentResolver();
  
  //設定圖檔的基本資料
  ContentValues values = new ContentValues(3);  
  values.put(Media.DISPLAY_NAME, "view");
  values.put(Media.DESCRIPTION, "my trip");
  values.put(Media.MIME_TYPE, "image/jpeg");
  
  //新增圖檔的基本資料
  Uri uri = cResolver.insert(Media.EXTERNAL_CONTENT_URI, values);
  
  //進行圖檔的更新
  try {
   if (sourceImg != null) {	  
    OutputStream out = cResolver.openOutputStream(uri);
    sourceImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
    out.close();
    out = null;
    Log.v("content", "insert image done");
   } 
  } catch (Exception e) {
   Log.e("content", "err:" + e);	  
  } 
 }
}