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
 
 //�ΨӶi��URI��蠟�u�㪫��
 private static final UriMatcher uriMatcher;
 
 //�i��d�߮�, �ҭn�d�ߪ����
 private static HashMap<String, String> columnMap;
 
 //�b���O���J�g������
 static {
  //�]�wURI��諸�Ѽƭ�	 
  uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
  uriMatcher.addURI(Pets.AUTHORITY, "pet", 1);
  uriMatcher.addURI(Pets.AUTHORITY, "pet/#", 2);
  
  columnMap = new HashMap<String, String>();
  columnMap.put(Pet._ID, Pet._ID);
  columnMap.put(Pet.SPECIE, Pet.SPECIE);
  columnMap.put(Pet.HABITAT, Pet.HABITAT);
 }
 
 //�إ߸�Ʈw�u�㪫�󪺹���
 public boolean onCreate() {
  helper = new MyDBHelper(this.getContext()); 
  return true;
 }
	
 //������@, ����Provider�s�W��ƪ��\��
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
	
 //������@, ����Provider�d�߸�ƪ��\��
 public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
  SQLiteQueryBuilder dbBuilder = new SQLiteQueryBuilder();
  
  //���ϥΪ̩ҶǤJ��URI
  switch (uriMatcher.match(uri)) {
   case 1:
	    //�d�ߩҦ����
	    Log.v("content", "provider query all");
	    
	    //�]�w��Ʈw���
	    dbBuilder.setTables(helper.DATABASE_TABLE_NAME);
	    
	    //�]�w�n�d�ߪ�������
	    dbBuilder.setProjectionMap(columnMap);
	    break;
   case 2:
	    //�ھ�ID�d�߸��
	    Log.v("content", "provider query args");
	    
	    //�]�w��Ʈw���
	    dbBuilder.setTables(helper.DATABASE_TABLE_NAME);
	    
	    //�]�w�n�d�ߪ�������
	    dbBuilder.setProjectionMap(columnMap);
	    
	    //�ھڨϥΪ̩ҶǤJ������A�i��d��
	    dbBuilder.appendWhere(Pet._ID + "=" + uri.getPathSegments().get(1));
	    Log.v("content", "provider where args:" + Pet._ID + "=" + uri.getPathSegments().get(1));
	    break;	   
   default: throw new IllegalArgumentException("URI���~:" + uri);
  }
  
  //�]�w�Ƨ����
  String orderByStr;
  if (TextUtils.isEmpty(sort)) {
	orderByStr = Pet.DEFAULT_SORT_ORDER;
  } else {
	orderByStr = sort;	  
  }
  
  //�ھڤW�z�޿�A�i���Ƭd��
  SQLiteDatabase db = helper.getReadableDatabase();
  Cursor cursor = dbBuilder.query(db, projection, selection, selectionArgs, null, null, orderByStr);
  cursor.setNotificationUri(this.getContext().getContentResolver(), uri);
  return cursor;
 }
 
 //������@, ����Provider�R����ƪ��\��
 public int delete(Uri uri, String selection, String[] selectionArgs) {
  //���o��Ʈw����
  SQLiteDatabase db = helper.getWritableDatabase();
  
  //�ھڨϥΪ̩ҳ]�w������A�i��R��
  int rows = db.delete(helper.DATABASE_TABLE_NAME, selection, selectionArgs);  
  this.getContext().getContentResolver().notifyChange(uri, null);
  
  return rows;
 }

 //������@, ����Provider��s��ƪ��\��
 public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
  //���o��Ʈw����
  SQLiteDatabase db = helper.getWritableDatabase();
  
  //�ھڨϥΪ̩ҳ]�w������A�i���s
  int rows = db.update(helper.DATABASE_TABLE_NAME, values, selection, selectionArgs);
  this.getContext().getContentResolver().notifyChange(uri, null);
  
  return rows;
 }

 //������@
 public String getType(Uri uri) {
  return null;
 }
}
