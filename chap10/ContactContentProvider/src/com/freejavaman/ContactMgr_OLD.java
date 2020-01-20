package com.freejavaman;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContactMgr_OLD extends Activity {
 
 EditText nameEdit, telEdit;
 Button insertBtn, delBtn, updateBtn, queryBtn;
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //�s���H���m�W�P�q�����
  nameEdit = (EditText)this.findViewById(R.id.nameEdit);
  telEdit = (EditText)this.findViewById(R.id.telEdit);
  
  //���o�s�W�B�R���B�ק�B�d�߫��s���������
  insertBtn = (Button)this.findViewById(R.id.btn1);
  delBtn = (Button)this.findViewById(R.id.btn2);
  updateBtn = (Button)this.findViewById(R.id.btn3);
  queryBtn = (Button)this.findViewById(R.id.btn4);
  
  
  //�e���I����s���ƥ�
  insertBtn.setOnClickListener(new OnClickListener(){
   public void onClick(View view) {
	doInsert();   
   }  
  });
  
  delBtn.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {
     doDelete();	   
	}  
  });
  
  updateBtn.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {
     doUpdate();		   
	}  
  });
  
  queryBtn.setOnClickListener(new OnClickListener(){
	public void onClick(View view) {
	 doQuery();			   
	}  
  });  
 }
 
 //�s�W�p���H���
 private void doInsert() {
  //���o�����p���H��ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
  
  //�]�w�n�s�W�����
  ContentValues personalValues = new ContentValues();  
  personalValues.put(People.NAME, nameEdit.getText().toString());
  
  //����s�W�p���H
  Uri newPersonUri = cResolver.insert(uri, personalValues);
  Uri mUri = Uri.withAppendedPath(newPersonUri, Contacts.People.Phones.CONTENT_DIRECTORY);
  
  //�s�W�p���H��������X
  ContentValues mobileValues = new ContentValues();
  mobileValues.put(Contacts.Phones.NUMBER, new Integer(telEdit.getText().toString())); 
  mobileValues.put(Contacts.Phones.TYPE, Contacts.Phones.TYPE_MOBILE); 
  
  //����s�W�p���H��ʹq��
  Uri phoneUpdate = cResolver.insert(mUri, mobileValues); 
  
  Log.v("content", "insert person:" + uri.toString());
  Log.v("content", "insert new person:" + newPersonUri.toString());
  Log.v("content", "insert mobile:" + mUri.toString());
  Log.v("content", "insert new mobile:" + phoneUpdate.toString());
  
  //�M����J��
  nameEdit.setText("");
  telEdit.setText("");
  
  Log.v("content", "insert done");
 }
 
 //�R���p���H���
 private void doDelete() {
  //���o�����p���H��ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
  	  
  //�ھګ��w���W�r����R��
  String whereClause = Contacts.PeopleColumns.NAME + "=?";
  String[] whereArgs = {nameEdit.getText().toString()};
  cResolver.delete(uri, whereClause, whereArgs);
  
  //�M����J��
  nameEdit.setText("");
  telEdit.setText("");
  
  Log.v("content", "delete done");
 }
 
 //��s�p���H���
 private void doUpdate(){
  //���o�����p���H��ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
		
  //�d�ߤ����O���
  String[] projection = {Contacts.People._ID, Contacts.PeopleColumns.NAME};
	  
  //�ھ��p���H�W�r�d��
  String selection = Contacts.PeopleColumns.NAME + "=?";
  String[] selectionArgs = {nameEdit.getText().toString()};
	  
  //�ƧǪ��̾�
  String sortOrder = Contacts.PeopleColumns.NAME;
	  
  //����d�ߪ��u�@
  Cursor cursor = cResolver.query(uri, projection, selection, selectionArgs, sortOrder);
  
  //���ߩҦ��d�ߵ��G	  
  if (cursor.moveToFirst()) {
    for (int i = 0; i < cursor.getCount(); i++) {
     
     //���o���V�p���H��URI	
     String pID =cursor.getString(cursor.getColumnIndex(Contacts.People._ID));
     Uri pUri = Uri.withAppendedPath(Contacts.People.CONTENT_URI, pID);
	 
     //�A���o������X��URI      
     Uri mobileUri = Uri.withAppendedPath(pUri, Contacts.People.Phones.CONTENT_DIRECTORY);
     
     Log.v("content", "update person:" + pUri.toString());
     Log.v("content", "update mobile:" + mobileUri.toString());
     
     
     //�d�ߩҦ���������X���
     //String[] mProjection = {Contacts.Phones._ID, Contacts.Phones.NUMBER, Contacts.Phones.NUMBER_KEY};
     String[] mProjection = {Contacts.Phones._ID, Contacts.Phones.NUMBER};
     
     //�ھ��p���H�W�r�d��
     //String mSelection = Contacts.PeopleColumns.NAME + "=?";
     //String[] mSelectionArgs = {nameEdit.getText().toString()};
     
     //����d�ߪ��u�@
     Cursor mCursor = cResolver.query(mobileUri, mProjection, null, null, null);
     
     if (mCursor.moveToFirst()) {
  	   for (int j = 0; j < mCursor.getCount(); j++) {
    	 int idInx = mCursor.getColumnIndex(Contacts.Phones._ID);
    	 int numInx = mCursor.getColumnIndex(Contacts.Phones.NUMBER);
    	 //int numKInx = mCursor.getColumnIndex(Contacts.Phones.NUMBER_KEY);
    	 
    	 String id = cursor.getString(idInx);
    	 String num = cursor.getString(numInx);
    	 //String numK = cursor.getString(numKInx);
    	 
    	 Log.v("content", "update query id:" + id);
    	 Log.v("content", "update query num:" + num);
    	 //Log.v("content", "update query numK:" + numK);
  	   }
     }  
    	     
     /*ContentValues mobileValues = new ContentValues();
     mobileValues.put(Contacts.Phones.NUMBER, new Integer(telEdit.getText().toString())); 
     mobileValues.put(Contacts.Phones.TYPE, Contacts.Phones.TYPE_MOBILE); 
      
     //�����Ƨ�s     
     int row = cResolver.update(mobileUri, mobileValues, null, null);*/
     //Log.v("content", "update new row:" + row);
     
     Log.v("content", "update ok");
    }
  } else {
   Log.v("content", "no data for update");
  } 
  
  //�M����J��
  nameEdit.setText("");
  telEdit.setText("");
 }
 
 //�d���p���H���
 private void doQuery(){
  //���o�����p���H��ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
	
  //�d�ߤ����O���
  String[] projection = {Contacts.People._ID, Contacts.PeopleColumns.NAME};
  
  //�ھ��p���H�W�r�d��
  String selection = Contacts.PeopleColumns.NAME + "=?";
  String[] selectionArgs = {nameEdit.getText().toString()};
  
  //�ƧǪ��̾�
  String sortOrder = Contacts.PeopleColumns.NAME;
  
  //����d�ߪ��u�@
  Cursor cursor = cResolver.query(uri, projection, selection, selectionArgs, sortOrder);
  
  //���ߩҦ��d�ߵ��G
  if (cursor.moveToFirst()) {
    for (int i = 0; i < cursor.getCount(); i++) {
     int nameInx = cursor.getColumnIndex(Contacts.PeopleColumns.NAME);
     int idInx = cursor.getColumnIndex(Contacts.People._ID);
     
     String name = cursor.getString(nameInx);
     String id = cursor.getString(idInx);
     
     Log.v("content", "query result: id:" + id + ",name:" + name);
    }
  } else {
   Log.v("content", "no data for query");
  }  
 }
}