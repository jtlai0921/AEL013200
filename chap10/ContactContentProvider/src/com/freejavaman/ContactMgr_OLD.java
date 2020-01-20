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
  
  //連絡人的姓名與電話欄位
  nameEdit = (EditText)this.findViewById(R.id.nameEdit);
  telEdit = (EditText)this.findViewById(R.id.telEdit);
  
  //取得新增、刪除、修改、查詢按鈕之物件實體
  insertBtn = (Button)this.findViewById(R.id.btn1);
  delBtn = (Button)this.findViewById(R.id.btn2);
  updateBtn = (Button)this.findViewById(R.id.btn3);
  queryBtn = (Button)this.findViewById(R.id.btn4);
  
  
  //委任點選按鈕的事件
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
 
 //新增聯絡人資料
 private void doInsert() {
  //取得提供聯絡人資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
  
  //設定要新增的資料
  ContentValues personalValues = new ContentValues();  
  personalValues.put(People.NAME, nameEdit.getText().toString());
  
  //執行新增聯絡人
  Uri newPersonUri = cResolver.insert(uri, personalValues);
  Uri mUri = Uri.withAppendedPath(newPersonUri, Contacts.People.Phones.CONTENT_DIRECTORY);
  
  //新增聯絡人的手機號碼
  ContentValues mobileValues = new ContentValues();
  mobileValues.put(Contacts.Phones.NUMBER, new Integer(telEdit.getText().toString())); 
  mobileValues.put(Contacts.Phones.TYPE, Contacts.Phones.TYPE_MOBILE); 
  
  //執行新增聯絡人行動電話
  Uri phoneUpdate = cResolver.insert(mUri, mobileValues); 
  
  Log.v("content", "insert person:" + uri.toString());
  Log.v("content", "insert new person:" + newPersonUri.toString());
  Log.v("content", "insert mobile:" + mUri.toString());
  Log.v("content", "insert new mobile:" + phoneUpdate.toString());
  
  //清除輸入框
  nameEdit.setText("");
  telEdit.setText("");
  
  Log.v("content", "insert done");
 }
 
 //刪除聯絡人資料
 private void doDelete() {
  //取得提供聯絡人資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
  	  
  //根據指定的名字執行刪除
  String whereClause = Contacts.PeopleColumns.NAME + "=?";
  String[] whereArgs = {nameEdit.getText().toString()};
  cResolver.delete(uri, whereClause, whereArgs);
  
  //清除輸入框
  nameEdit.setText("");
  telEdit.setText("");
  
  Log.v("content", "delete done");
 }
 
 //更新聯絡人資料
 private void doUpdate(){
  //取得提供聯絡人資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
		
  //查詢之類別欄位
  String[] projection = {Contacts.People._ID, Contacts.PeopleColumns.NAME};
	  
  //根據聯絡人名字查詢
  String selection = Contacts.PeopleColumns.NAME + "=?";
  String[] selectionArgs = {nameEdit.getText().toString()};
	  
  //排序的依據
  String sortOrder = Contacts.PeopleColumns.NAME;
	  
  //執行查詢的工作
  Cursor cursor = cResolver.query(uri, projection, selection, selectionArgs, sortOrder);
  
  //輪詢所有查詢結果	  
  if (cursor.moveToFirst()) {
    for (int i = 0; i < cursor.getCount(); i++) {
     
     //取得指向聯絡人的URI	
     String pID =cursor.getString(cursor.getColumnIndex(Contacts.People._ID));
     Uri pUri = Uri.withAppendedPath(Contacts.People.CONTENT_URI, pID);
	 
     //再取得手機號碼的URI      
     Uri mobileUri = Uri.withAppendedPath(pUri, Contacts.People.Phones.CONTENT_DIRECTORY);
     
     Log.v("content", "update person:" + pUri.toString());
     Log.v("content", "update mobile:" + mobileUri.toString());
     
     
     //查詢所有的手機號碼資料
     //String[] mProjection = {Contacts.Phones._ID, Contacts.Phones.NUMBER, Contacts.Phones.NUMBER_KEY};
     String[] mProjection = {Contacts.Phones._ID, Contacts.Phones.NUMBER};
     
     //根據聯絡人名字查詢
     //String mSelection = Contacts.PeopleColumns.NAME + "=?";
     //String[] mSelectionArgs = {nameEdit.getText().toString()};
     
     //執行查詢的工作
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
      
     //執行資料更新     
     int row = cResolver.update(mobileUri, mobileValues, null, null);*/
     //Log.v("content", "update new row:" + row);
     
     Log.v("content", "update ok");
    }
  } else {
   Log.v("content", "no data for update");
  } 
  
  //清除輸入框
  nameEdit.setText("");
  telEdit.setText("");
 }
 
 //查詢聯絡人資料
 private void doQuery(){
  //取得提供聯絡人資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri uri = Contacts.People.CONTENT_URI;
	
  //查詢之類別欄位
  String[] projection = {Contacts.People._ID, Contacts.PeopleColumns.NAME};
  
  //根據聯絡人名字查詢
  String selection = Contacts.PeopleColumns.NAME + "=?";
  String[] selectionArgs = {nameEdit.getText().toString()};
  
  //排序的依據
  String sortOrder = Contacts.PeopleColumns.NAME;
  
  //執行查詢的工作
  Cursor cursor = cResolver.query(uri, projection, selection, selectionArgs, sortOrder);
  
  //輪詢所有查詢結果
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