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
	//���oContentResolver�u��HURI   
    ContentResolver cResolver = this.getContentResolver();
    Uri uri = Media.EXTERNAL_CONTENT_URI;
    
    //�d�ߤ����O���
    String[] projection = {Media._ID, Media.DISPLAY_NAME};
    
    //�]�w�d�߱���
    String selection = Media.DISPLAY_NAME + "=?";
    
    //�]�w�d�߱��󪺸�ƭ�
    String[] selectionArgs = {"view"};
    
    //����d�ߪ��u�@
    Cursor cursor = cResolver.query(uri, projection, selection, selectionArgs, null);
   
    if (cursor.moveToFirst()) {
     
     //���oID��쪺���ޭ�
     int idInx = cursor.getColumnIndex(Media._ID);
     
     //���o���ɪ�ID
     String id = cursor.getString(idInx);
    	 
     //���X���ɪ�ID, ���o��ƩҦb��URI	
     Uri imgUri = Uri.withAppendedPath(uri, id);
     
     Log.v("content", "id:" + cursor.getString(cursor.getColumnIndex(Media._ID)));
     
     //Ū�J�s�����ɤ��e
     Bitmap sourceImg = null;
     try {
      sourceImg = BitmapFactory.decodeFile("/sdcard/tmp/trip2.jpg");
      Log.v("content", "read img done");
     }catch (Exception e) {
      Log.e("content", "read img err:" + e);	  
     }
     
     try {
      if (sourceImg != null) {
       //�}�ҿ�X��Ƭy�A���V���ɪ�URI
       OutputStream out = cResolver.openOutputStream(imgUri);
       
       //��s���ɤ��e
       sourceImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
       
       //������ƿ�J��Ƭy
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