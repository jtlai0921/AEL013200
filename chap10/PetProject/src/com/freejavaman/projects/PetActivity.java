package com.freejavaman.projects;

import com.freejavaman.projects.Pets.Pet;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class PetActivity extends Activity {
  
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  doDeleteAll();
  doInsert();
  doQuery();
  doDeleteByID();
  //doDeleteAll();
  doQuery();
  doUpdate();
  doQuery();
 }

 
 private void doUpdate() {
  //���o�u�㪫�󤧹���	 
  ContentResolver cResolver = this.getContentResolver();
  
  //�]�w��s�����
  ContentValues values = new ContentValues();  
  values.put(Pet.SPECIE, "Dog");
  values.put(Pet.HABITAT, "home");
  
  //�]�w��s����
  String whereClause = Pet._ID + "=?";
  String[] whereArgs = {"3"};
  
  //�����s���u�@
  cResolver.update(Pet.CONTENT_URI, values, whereClause, whereArgs);
 }
 
 
 private void doDeleteAll() {
  //���o���Ѹ�ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();  
  cResolver.delete(Pet.CONTENT_URI, null, null);
 }
 
 private void doDeleteByID() {
  //���o���Ѹ�ƪ�ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  
  //�ھڸ�ƪ�ID, �i��R��
  String whereClause = Pet._ID + "=?";
  String[] whereArgs = {"2"};
  
  cResolver.delete(Pet.CONTENT_URI, whereClause, whereArgs);
 }
 
 //�s�W���
 private void doInsert() {
  try {	 
   //���oContentResolver�u�㪫�����, ��Provider��URI
   ContentResolver cResolver = this.getContentResolver();
   Uri uri = Pet.CONTENT_URI;
		  
   //�]�w��Ƥ��e, �öi��s�W
   ContentValues values = new ContentValues();  
   values.put(Pet.SPECIE, "Dog");
   values.put(Pet.HABITAT, "wild");
   cResolver.insert(uri, values);
   
   //�]�w��Ƥ��e, �öi��s�W
   values.clear();  
   values.put(Pet.SPECIE, "Cat");
   values.put(Pet.HABITAT, "city");
   cResolver.insert(uri, values);
   
   //�]�w��Ƥ��e, �öi��s�W
   values.clear();  
   values.put(Pet.SPECIE, "Panda");
   values.put(Pet.HABITAT, "bamboo forest");
   cResolver.insert(uri, values);
   
   	  
   Log.v("content", "insert done");
  } catch (Exception e) {
   Log.e("content", "insert error:" + e);	  
  }
 }
		 
 private void doQuery() {
  String[] projection = new String[]{Pet._ID, Pet.SPECIE, Pet.HABITAT}; 	 
  Cursor cursor = this.managedQuery(Pet.CONTENT_URI, projection, null, null, Pet.DEFAULT_SORT_ORDER);
  
  if (cursor != null && cursor.moveToFirst()) {
   do {	
	String id = cursor.getString(0);
	String specie = cursor.getString(1);
	String habitat = cursor.getString(2);
	Log.v("content", "query:id:" + id + ",specie=" + specie + ",habitat=" + habitat);
   } while (cursor.moveToNext());
  } else {
    Log.e("content", "query cursor is NULL");	  
  }
 }
}