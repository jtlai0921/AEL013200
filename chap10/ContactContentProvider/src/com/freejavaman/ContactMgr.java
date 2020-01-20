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
  
  //�]�w�s��
  values.clear(); 
  values.put(ContactsContract.Data.RAW_CONTACT_ID, id);
  values.put(ContactsContract.Data.MIMETYPE, GroupMembership.CONTENT_ITEM_TYPE);
  values.put(GroupMembership.GROUP_ROW_ID, 11);  
  dataUri = cResolver.insert(dataURI, values);  
  Log.v("content", "insert Data(0) URI:" + dataUri.toString());
  
  //�]�w�p�������ܦW��
  values.clear(); 
  values.put(ContactsContract.Data.RAW_CONTACT_ID, id);  
  values.put(ContactsContract.Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
  values.put(CommonDataKinds.StructuredName.DISPLAY_NAME, nameEdit.getText().toString());  
  dataUri = cResolver.insert(dataURI, values);
  Log.v("content", "insert Data(1) URI:" + dataUri.toString());
  
  //�]�w�p���H�q��
  values.clear(); 
  values.put(ContactsContract.Data.RAW_CONTACT_ID, id);  
  values.put(ContactsContract.Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
  values.put(CommonDataKinds.Phone.NUMBER, telEdit.getText().toString());
  values.put(CommonDataKinds.Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE);
  dataUri = cResolver.insert(dataURI, values);
  Log.v("content", "insert Data(2) URI:" + dataUri.toString());
    
  //�M����J��
  nameEdit.setText("");
  telEdit.setText("");
  
  Log.v("content", "insert done");
 }
 
 //�R���p���H���
 private void doDelete() {
  //���o�����p���H��ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  
  //Uri contactURI = ContactsContract.Contacts.CONTENT_URI;
  Uri rawURI = ContactsContract.RawContacts.CONTENT_URI;
  
  String name = nameEdit.getText().toString();
  
  if (name != null && !name.equals("")) {   
    //�ھګ��w���W�r����R��
    String whereClause = ContactsContract.Data.DISPLAY_NAME + "=?";
    String[] whereArgs = {nameEdit.getText().toString()};
    int rows = cResolver.delete(rawURI, whereClause, whereArgs);
  
    //�M����J��
    nameEdit.setText("");
    telEdit.setText("");
    
    Log.v("content", "delete done(by name):" + rows);    
  } else {
	//�R�����������
	int rows = cResolver.delete(rawURI, null, null);  
	Log.v("content", "delete done(rawURI):" + rows);
	
	
	//�R���Ҧ��s��
	Uri groupURI = android.provider.ContactsContract.Groups.CONTENT_URI;
	rows = cResolver.delete(groupURI, null, null);
	Log.v("content", "delete done(groupURI):" + rows);		  
  }
 }
  
 //��s�p���H���
 private void doUpdate(){
  ContentResolver cResolver = this.getContentResolver();
  Uri dataURI = ContactsContract.Data.CONTENT_URI;
  
  Log.v("content", "query contactURI:" + dataURI.toString());
  
  //���o��J���m�W
  String name = nameEdit.getText().toString();
  
  //�]�w�d�߱���
  String where = ContactsContract.Data.DISPLAY_NAME + " = ? AND " + 
	             ContactsContract.Data.MIMETYPE + " = ? AND " +
	             CommonDataKinds.Phone.TYPE + " = ? ";
  
  //�]�w�d�߱��󤧸�ƭ�
  String[] selectionArgs = new String[] {name, 
		                                 CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
		                                 "" + CommonDataKinds.Phone.TYPE_MOBILE};
  
  //�i���Ƭd��
  Cursor phoneCur = managedQuery(dataURI, null, where, selectionArgs, null);
  
  //�P�_�O�_���ŦX���󪺸�Ʀs�b
  if (phoneCur != null) {
	Log.v("content", "update data exist");
	
    //�p�G�s�b�A�N��s�q��
    ContentValues values = new ContentValues();
    values.put(ContactsContract.CommonDataKinds.Phone.DATA, telEdit.getText().toString());
    cResolver.update(dataURI, values, where, selectionArgs);
   
    //�M����J��
    nameEdit.setText("");
    telEdit.setText("");   
    
    Log.v("content", "update done.");
    
  } else {
	Log.v("content", "update no data match"); 
  }
 }
 
 //�d���p���H���Zfer
 private void doQuery(){
  //�d�ߩҦ���Account	 
  /*Account[] accounts = AccountManager.get(this).getAccounts(); 
  for (Account acc : accounts){
   Log.d("content", "account name = " + acc.name + ", type = " + acc.type);
  }*/ 
  
  //�d�ߩҦ����s��
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
   
  //���o�����p���H��ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  Uri contactURI = ContactsContract.Contacts.CONTENT_URI;
  
  Log.v("content", "query contactURI:" + contactURI.toString());
  
  //����d�ߪ��u�@
  Cursor cursor = null;
  String name = nameEdit.getText().toString();  
  if (name != null && !name.equals("")) {
	  
	String[] fields = {ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};	
	String whereClause = ContactsContract.Data.DISPLAY_NAME + "=?";
	String[] whereArgs = {name};
	cursor = cResolver.query(contactURI, fields, whereClause, whereArgs, ContactsContract.Data.DISPLAY_NAME);
  } else {
    //�d�ߩҦ����p�����
	cursor = cResolver.query(contactURI, null, null, null, null);
  }
  
  //���ߩҦ��d�ߵ��G
  if (cursor.moveToFirst()) {
   do {	 
	 int idInx = cursor.getColumnIndex(ContactsContract.Contacts._ID);
     int nameInx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
     
     String id = cursor.getString(idInx);
     String displayName = cursor.getString(nameInx);
     Log.v("content", "query result: id:" + id + ",name:" + displayName);
     
     //���o������
     int hasNumInx = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
     
     //�ھ������ޡA���o���X����
     String hasNumStr = cursor.getString(hasNumInx);
     
     //�ഫ���ƭȫ��A
     int hasNum = Integer.parseInt(hasNumStr);
     
     if (hasNum > 0) {
       
       //�d�ߩҦ����	 
       String[] fields = null;
       
       //�]�w�d�߱���
       String whereClause = ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?";
       
       //�]�w�d�߱��󪺸�Ƥ��e, �Y�p����ƪ�ID
       String[] whereArgs = {id};
       
       //�i�渹�X�d��
  	   Cursor pCur = cResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, fields, whereClause, whereArgs, null);
  	   
  	   //���ߩҦ����G���X
       while (pCur.moveToNext()) {    	   
    	//���X��쪺���ޭ�
    	int phoneNoInx = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    	
    	//���o��Ƥ��e
    	String phoneNo = pCur.getString(phoneNoInx);
        Log.v("content", "query result: phoneNo:" + phoneNo);
        
        telEdit.setText(phoneNo);
       }
       
       //��������
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