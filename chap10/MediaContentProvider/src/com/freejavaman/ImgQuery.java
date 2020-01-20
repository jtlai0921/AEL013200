package com.freejavaman;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.widget.ImageView;

public class ImgQuery extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  ImageView imageView = (ImageView)this.findViewById(R.id.img1);
  
   try {
	//取得工具元件的實體   
    ContentResolver cResolver = this.getContentResolver();
    
    //設定要查詢的欄位
    String[] projection = {Media._ID, Media.DISPLAY_NAME, Media.DESCRIPTION};
    
    //設定查詢條件
    String selection = Media.DISPLAY_NAME + "=?";
    
    //設定查詢條件資料值
    String[] selectionArgs = {"view"};
    
    //執行查詢
    Cursor cursor = cResolver.query(Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, null);
   
    if (cursor.moveToFirst()) {
     //結合圖片的ID, 建立新的URI	
     
     //取得圖檔的ID欄位索引
     int idInx = cursor.getColumnIndex(Media._ID);
     
     //取得圖檔的ID
     String id = cursor.getString(idInx);
     
     //取得具ID的完整URI
     Uri imgUri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, id);
     
     Log.v("content", "imgUri:" + imgUri.toString());
     Log.v("content", "id:" + cursor.getString(cursor.getColumnIndex(Media._ID)));
     Log.v("content", "name:" + cursor.getString(cursor.getColumnIndex(Media.DISPLAY_NAME)));
     Log.v("content", "desc:" + cursor.getString(cursor.getColumnIndex(Media.DESCRIPTION)));
     
     //根據完整的圖檔URI，取回圖檔內容
     Bitmap bitmap = Media.getBitmap(cResolver, imgUri);
     
     //顯示在螢幕上
     imageView.setImageBitmap(bitmap);
     
     Log.v("content", "query done");
    } else {
     Log.v("content", "no data");
    } 
  } catch (Exception e) {
   Log.e("content", "err when query:" + e);	  
  }
 }
}