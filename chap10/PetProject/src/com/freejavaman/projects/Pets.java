package com.freejavaman.projects;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Pets {
 
 public static final String AUTHORITY = "com.freejavaman.provider.pets";
 
 private Pets(){}
 
 //�R�A�������O
 public static final class Pet implements BaseColumns {
	 
  private Pet(){}
  
  //�]�wURI
  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/pet");
  
  //�w�qMIME���A
  //public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.freejavaman.pets";  
  //public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.freejavaman.pets";
  
  //�w�]�n�ƧǪ����
  public static final String DEFAULT_SORT_ORDER = "specie DESC";
  
  //��ƪ����ݩʡA�Y������
  public static final String SPECIE = "specie";
  public static final String HABITAT = "habitat";
 }
 
}
