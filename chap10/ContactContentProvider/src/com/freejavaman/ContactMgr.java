package com.freejavaman;

import java.util.ArrayList;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.GroupMembership;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContactMgr extends Activity {
 
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
  Uri rawURI = ContactsContract.RawContacts.CONTENT_URI;
  Uri dataURI = ContactsContract.Data.CONTENT_URI;
  
  Log.v("content", "insert Root Raw URI:" + rawURI.toString());
  Log.v("content", "insert Root Data URI:" + dataURI.toString());
  
  ContentValues values = new ContentValues();  
  
  values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, "tester@test.com");
  values.put(ContactsContract.RawContacts.ACCOUNT_NAME, "com.test");  
  Uri dataUri = cResolver.insert(rawURI, values);
  Log.v("content", "insert Raw URI:" + dataUri.toString());
  
  long id = ContentUris.parseId(dataUri);  
  Log.v("content", "insert id:" + id);
  
  //設定群組
  values.clear(); 
  values.put(ContactsContract.Data.RAW_CONTACT_ID, id);
  values.put(ContactsContract.Data.MIMETYPE, GroupMembership.CONTENT_ITEM_TYPE);
  values.put(GroupMembership.GROUP_ROW_ID, 11);  
  dataUri = cResolver.insert(dataURI, values);  
  Log.v("content", "insert Data(0) URI:" + dataUri.toString());
  
  //設定聯絡資料顯示名稱
  values.clear(); 
  values.put(ContactsContract.Data.RAW_CONTACT_ID, id);  
  values.put(ContactsContract.Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
  values.put(CommonDataKinds.StructuredName.DISPLAY_NAME, nameEdit.getText().toString());  
  dataUri = cResolver.insert(dataURI, values);
  Log.v("content", "insert Data(1) URI:" + dataUri.toString());
  
  //設定聯絡人電話
  values.clear(); 
  values.put(ContactsContract.Data.RAW_CONTACT_ID, id);  
  values.put(ContactsContract.Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
  values.put(CommonDataKinds.Phone.NUMBER, telEdit.getText().toString());
  values.put(CommonDataKinds.Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE);
  dataUri = cResolver.insert(dataURI, values);
  Log.v("content", "insert Data(2) URI:" + dataUri.toString());
    
  //清除輸入框
  nameEdit.setText("");
  telEdit.setText("");
  
  Log.v("content", "insert done");
 }
 
 //刪除聯絡人資料
 private void doDelete() {
  //取得提供聯絡人資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  
  //Uri contactURI = ContactsContract.Contacts.CONTENT_URI;
  Uri rawURI = ContactsContract.RawContacts.CONTENT_URI;
  
  String name = nameEdit.getText().toString();
  
  if (name != null && !name.equals("")) {   
    //根據指定的名字執行刪除
    String whereClause = ContactsContract.Data.DISPLAY_NAME + "=?";
    String[] whereArgs = {nameEdit.getText().toString()};
    int rows = cResolver.delete(rawURI, whereClause, whereArgs);
  
    //清除輸入框
    nameEdit.setText("");
    telEdit.setText("");
    
    Log.v("content", "delete done(by name):" + rows);    
  } else {
	//刪除全部的資料
	int rows = cResolver.delete(rawURI, null, null);  
	Log.v("content", "delete done(rawURI):" + rows);
	
	
	//刪除所有群組
	Uri groupURI = android.provider.ContactsContract.Groups.CONTENT_URI;
	rows = cResolver.delete(groupURI, null, null);
	Log.v("content", "delete done(groupURI):" + rows);		  
  }
 }
  
 //更新聯絡人資料
 private void doUpdate(){
  ContentResolver cResolver = this.getContentResolver();
  Uri dataURI = ContactsContract.Data.CONTENT_URI;
  
  Log.v("content", "query contactURI:" + dataURI.toString());
  
  //取得輸入的姓名
  String name = nameEdit.getText().toString();
  
  //設定查詢條件
  String where = ContactsContract.Data.DISPLAY_NAME + " = ? AND " + 
	             ContactsContract.Data.MIMETYPE + " = ? AND " +
	             CommonDataKinds.Phone.TYPE + " = ? ";
  
  //設定查詢條件之資料值
  String[] selectionArgs = new String[] {name, 
		                                 CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
		                                 "" + CommonDataKinds.Phone.TYPE_MOBILE};
  
  //進行資料查詢
  Cursor phoneCur = managedQuery(dataURI, null, where, selectionArgs, null);
  
  //判斷是否有符合條件的資料存在
  if (phoneCur != null) {
	Log.v("content", "update data exist");
	
    //如果存在，就更新電話
    ContentValues values = new ContentValues();
    values.put(ContactsContract.CommonDataKinds.Phone.DATA, telEdit.getText().toString());
    cResolver.update(dataURI, values, where, selectionArgs);
   
    //清除輸入框
    nameEdit.setText("");
    telEdit.setText("");   
    
    Log.v("content", "update done.");
    
  } else {
	Log.v("content", "update no data match"); 
  }
 }
 
 //查詢聯絡人資料Zfer
 private void doQuery(){
  //查詢所有的Account	 
  /*Account[] accounts = AccountManager.get(this).getAccounts(); 
  for (Account acc : accounts){
   Log.d("content", "account name = " + acc.name + ", type = " + acc.type);
  }*/ 
  
  //查詢所有的群組
  /*Uri groupURI = android.provider.ContactsContract.Groups.CONTENT_URI;  
  String[] groupFields = new String[] {android.provider.ContactsContract.Groups._ID, android.provider.ContactsContract.Groups.TITLE};
  Cursor groupCur = this.managedQuery(groupURI, groupFields, null, null, null);  
  if (groupCur.moveToFirst()) {
	do { 
	  int idInx = groupCur.getColumnIndex(android.provider.ContactsContract.Groups._ID);
	  int titleInx = groupCur.getColumnIndex(android.provider.ContactsContract.Groups.TITLE);
	  
	  String id = groupCur.getString(idInx);	 
	  String title = groupCur.getString(titleInx);	  
	  Log.v("content", "group, id:" + id + ", title:" + title);
	} while (groupCur.moveToNext()); 
  } else {
   Log.v("content", "no group data");	  
  }*/
   
  //取得提供聯絡人資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri contactURI = ContactsContract.Contacts.CONTENT_URI;
  
  Log.v("content", "query contactURI:" + contactURI.toString());
  
  //執行查詢的工作
  Cursor cursor = null;
  String name = nameEdit.getText().toString();  
  if (name != null && !name.equals("")) {
	  
	String[] fields = {ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};	
	String whereClause = ContactsContract.Data.DISPLAY_NAME + "=?";
	String[] whereArgs = {name};
	cursor = cResolver.query(contactURI, fields, whereClause, whereArgs, ContactsContract.Data.DISPLAY_NAME);
  } else {
    //查詢所有的聯絡資料
	cursor = cResolver.query(contactURI, null, null, null, null);
  }
  
  //輪詢所有查詢結果
  if (cursor.moveToFirst()) {
   do {	 
	 int idInx = cursor.getColumnIndex(ContactsContract.Contacts._ID);
     int nameInx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
     
     String id = cursor.getString(idInx);
     String displayName = cursor.getString(nameInx);
     Log.v("content", "query result: id:" + id + ",name:" + displayName);
     
     //取得欄位索引
     int hasNumInx = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
     
     //根據欄位索引，取得號碼筆數
     String hasNumStr = cursor.getString(hasNumInx);
     
     //轉換成數值型態
     int hasNum = Integer.parseInt(hasNumStr);
     
     if (hasNum > 0) {
       
       //查詢所有欄位	 
       String[] fields = null;
       
       //設定查詢條件
       String whereClause = ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?";
       
       //設定查詢條件的資料內容, 即聯絡資料的ID
       String[] whereArgs = {id};
       
       //進行號碼查詢
  	   Cursor pCur = cResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, fields, whereClause, whereArgs, null);
  	   
  	   //輪詢所有結果集合
       while (pCur.moveToNext()) {    	   
    	//號碼欄位的索引值
    	int phoneNoInx = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    	
    	//取得資料內容
    	String phoneNo = pCur.getString(phoneNoInx);
        Log.v("content", "query result: phoneNo:" + phoneNo);
        
        telEdit.setText(phoneNo);
       }
       
       //關閉索引
       pCur.close();
     } else {
      Log.v("content", "query result: no phoneNo");	 
     }
   } while (cursor.moveToNext());
  } else {
   Log.v("content", "no data for query");
  }  
  
 }
}