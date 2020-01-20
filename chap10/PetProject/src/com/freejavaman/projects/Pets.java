package com.freejavaman.projects;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Pets {
 
 public static final String AUTHORITY = "com.freejavaman.provider.pets";
 
 private Pets(){}
 
 //靜態內部類別
 public static final class Pet implements BaseColumns {
	 
  private Pet(){}
  
  //設定URI
  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/pet");
  
  //定義MIME型態
  //public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.freejavaman.pets";  
  //public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.freejavaman.pets";
  
  //預設要排序的欄位
  public static final String DEFAULT_SORT_ORDER = "specie DESC";
  
  //資料物件的屬性，即資料欄位
  public static final String SPECIE = "specie";
  public static final String HABITAT = "habitat";
 }
 
}
