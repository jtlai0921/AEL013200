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
	//���o�u�㤸�󪺹���   
    ContentResolver cResolver = this.getContentResolver();
    
    //�]�w�n�d�ߪ����
    String[] projection = {Media._ID, Media.DISPLAY_NAME, Media.DESCRIPTION};
    
    //�]�w�d�߱���
    String selection = Media.DISPLAY_NAME + "=?";
    
    //�]�w�d�߱����ƭ�
    String[] selectionArgs = {"view"};
    
    //����d��
    Cursor cursor = cResolver.query(Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, null);
   
    if (cursor.moveToFirst()) {
     //���X�Ϥ���ID, �إ߷s��URI	
     
     //���o���ɪ�ID������
     int idInx = cursor.getColumnIndex(Media._ID);
     
     //���o���ɪ�ID
     String id = cursor.getString(idInx);
     
     //���o��ID������URI
     Uri imgUri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, id);
     
     Log.v("content", "imgUri:" + imgUri.toString());
     Log.v("content", "id:" + cursor.getString(cursor.getColumnIndex(Media._ID)));
     Log.v("content", "name:" + cursor.getString(cursor.getColumnIndex(Media.DISPLAY_NAME)));
     Log.v("content", "desc:" + cursor.getString(cursor.getColumnIndex(Media.DESCRIPTION)));
     
     //�ھڧ��㪺����URI�A���^���ɤ��e
     Bitmap bitmap = Media.getBitmap(cResolver, imgUri);
     
     //��ܦb�ù��W
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