package com.freejavaman.projects;

import java.util.HashMap;
import com.freejavaman.projects.Pets.Pet;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class PetProvider extends ContentProvider {
 	
 private MyDBHelper helper;	
 
 //用來進行URI比對之工具物件
 private static final UriMatcher uriMatcher;
 
 //進行查詢時, 所要查詢的欄位
 private static HashMap<String, String> columnMap;
 
 //在類別載入週期執行
 static {
  //設定URI比對的參數值	 
  uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
  uriMatcher.addURI(Pets.AUTHORITY, "pet", 1);
  uriMatcher.addURI(Pets.AUTHORITY, "pet/#", 2);
  
  columnMap = new HashMap<String, String>();
  columnMap.put(Pet._ID, Pet._ID);
  columnMap.put(Pet.SPECIE, Pet.SPECIE);
  columnMap.put(Pet.HABITAT, Pet.HABITAT);
 }
 
 //建立資料庫工具物件的實體
 public boolean onCreate() {
  helper = new MyDBHelper(this.getContext()); 
  return true;
 }
	
 //必須實作, 執行Provider新增資料的功能
 public Uri insert(Uri uri, ContentValues values) {
  SQLiteDatabase db = helper.getWritableDatabase();
  long rowID = db.insert(MyDBHelper.DATABASE_TABLE_NAME , Pet.SPECIE, values);
  
  if (rowID > 0) {
   Uri petURI = ContentUris.withAppendedId(Pet.CONTENT_URI, rowID);
   this.getContext().getContentResolver().notifyChange(petURI, null);
   return petURI;
  }  
  return null;
 }
	
 //必須實作, 執行Provider查詢資料的功能
 public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
  SQLiteQueryBuilder dbBuilder = new SQLiteQueryBuilder();
  
  //比對使用者所傳入的URI
  switch (uriMatcher.match(uri)) {
   case 1:
	    //查詢所有資料
	    Log.v("content", "provider query all");
	    
	    //設定資料庫表格
	    dbBuilder.setTables(helper.DATABASE_TABLE_NAME);
	    
	    //設定要查詢的資料欄位
	    dbBuilder.setProjectionMap(columnMap);
	    break;
   case 2:
	    //根據ID查詢資料
	    Log.v("content", "provider query args");
	    
	    //設定資料庫表格
	    dbBuilder.setTables(helper.DATABASE_TABLE_NAME);
	    
	    //設定要查詢的資料欄位
	    dbBuilder.setProjectionMap(columnMap);
	    
	    //根據使用者所傳入的條件，進行查詢
	    dbBuilder.appendWhere(Pet._ID + "=" + uri.getPathSegments().get(1));
	    Log.v("content", "provider where args:" + Pet._ID + "=" + uri.getPathSegments().get(1));
	    break;	   
   default: throw new IllegalArgumentException("URI錯誤:" + uri);
  }
  
  //設定排序欄位
  String orderByStr;
  if (TextUtils.isEmpty(sort)) {
	orderByStr = Pet.DEFAULT_SORT_ORDER;
  } else {
	orderByStr = sort;	  
  }
  
  //根據上述邏輯，進行資料查詢
  SQLiteDatabase db = helper.getReadableDatabase();
  Cursor cursor = dbBuilder.query(db, projection, selection, selectionArgs, null, null, orderByStr);
  cursor.setNotificationUri(this.getContext().getContentResolver(), uri);
  return cursor;
 }
 
 //必須實作, 執行Provider刪除資料的功能
 public int delete(Uri uri, String selection, String[] selectionArgs) {
  //取得資料庫物件
  SQLiteDatabase db = helper.getWritableDatabase();
  
  //根據使用者所設定的條件，進行刪除
  int rows = db.delete(helper.DATABASE_TABLE_NAME, selection, selectionArgs);  
  this.getContext().getContentResolver().notifyChange(uri, null);
  
  return rows;
 }

 //必須實作, 執行Provider更新資料的功能
 public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
  //取得資料庫物件
  SQLiteDatabase db = helper.getWritableDatabase();
  
  //根據使用者所設定的條件，進行更新
  int rows = db.update(helper.DATABASE_TABLE_NAME, values, selection, selectionArgs);
  this.getContext().getContentResolver().notifyChange(uri, null);
  
  return rows;
 }

 //必須實作
 public String getType(Uri uri) {
  return null;
 }
}
