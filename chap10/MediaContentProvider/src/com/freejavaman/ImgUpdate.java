package com.freejavaman;

import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.widget.ImageView;

public class ImgUpdate extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  ImageView imageView = (ImageView)this.findViewById(R.id.img1);
  
   try {
	//取得ContentResolver工具以URI   
    ContentResolver cResolver = this.getContentResolver();
    Uri uri = Media.EXTERNAL_CONTENT_URI;
    
    //查詢之類別欄位
    String[] projection = {Media._ID, Media.DISPLAY_NAME};
    
    //設定查詢條件
    String selection = Media.DISPLAY_NAME + "=?";
    
    //設定查詢條件的資料值
    String[] selectionArgs = {"view"};
    
    //執行查詢的工作
    Cursor cursor = cResolver.query(uri, projection, selection, selectionArgs, null);
   
    if (cursor.moveToFirst()) {
     
     //取得ID欄位的索引值
     int idInx = cursor.getColumnIndex(Media._ID);
     
     //取得圖檔的ID
     String id = cursor.getString(idInx);
    	 
     //結合圖檔的ID, 取得資料所在的URI	
     Uri imgUri = Uri.withAppendedPath(uri, id);
     
     Log.v("content", "id:" + cursor.getString(cursor.getColumnIndex(Media._ID)));
     
     //讀入新的圖檔內容
     Bitmap sourceImg = null;
     try {
      sourceImg = BitmapFactory.decodeFile("/sdcard/tmp/trip2.jpg");
      Log.v("content", "read img done");
     }catch (Exception e) {
      Log.e("content", "read img err:" + e);	  
     }
     
     try {
      if (sourceImg != null) {
       //開啟輸出資料流，指向圖檔的URI
       OutputStream out = cResolver.openOutputStream(imgUri);
       
       //更新圖檔內容
       sourceImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
       
       //關閉資料輸入資料流
       out.close();
       out = null;
       Log.v("content", "update image done");
      } 
     } catch (Exception e) {
      Log.e("content", "update image err:" + e);	  
     }
     
    } else {
     Log.v("content", "no data");
    } 
  } catch (Exception e) {
   Log.e("content", "err when update:" + e);	  
  }
 }
}