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
  //取得工具物件之實體	 
  ContentResolver cResolver = this.getContentResolver();
  
  //設定更新的資料
  ContentValues values = new ContentValues();  
  values.put(Pet.SPECIE, "Dog");
  values.put(Pet.HABITAT, "home");
  
  //設定更新條件
  String whereClause = Pet._ID + "=?";
  String[] whereArgs = {"3"};
  
  //執行更新的工作
  cResolver.update(Pet.CONTENT_URI, values, whereClause, whereArgs);
 }
 
 
 private void doDeleteAll() {
  //取得提供資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();  
  cResolver.delete(Pet.CONTENT_URI, null, null);
 }
 
 private void doDeleteByID() {
  //取得提供資料的ContentProvider
  ContentResolver cResolver = this.getContentResolver();
  
  //根據資料的ID, 進行刪除
  String whereClause = Pet._ID + "=?";
  String[] whereArgs = {"2"};
  
  cResolver.delete(Pet.CONTENT_URI, whereClause, whereArgs);
 }
 
 //新增資料
 private void doInsert() {
  try {	 
   //取得ContentResolver工具物件實體, 及Provider之URI
   ContentResolver cResolver = this.getContentResolver();
   Uri uri = Pet.CONTENT_URI;
		  
   //設定資料內容, 並進行新增
   ContentValues values = new ContentValues();  
   values.put(Pet.SPECIE, "Dog");
   values.put(Pet.HABITAT, "wild");
   cResolver.insert(uri, values);
   
   //設定資料內容, 並進行新增
   values.clear();  
   values.put(Pet.SPECIE, "Cat");
   values.put(Pet.HABITAT, "city");
   cResolver.insert(uri, values);
   
   //設定資料內容, 並進行新增
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