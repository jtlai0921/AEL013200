package com.freejavaman.projects;

import com.freejavaman.projects.Pets.Pet;
import android.content.Context;
import android.database.sqlite.*;

public class MyDBHelper extends SQLiteOpenHelper {
 
  private static final String DATABASE_NAME = "Pets.db";
  public static final String DATABASE_TABLE_NAME = "pet";
  
  private static final int DATABASE_VERSION = 1;
  
  //建構者函數
  public MyDBHelper(Context context) {
   super(context, DATABASE_NAME, null, 1);
  }

  //建立資料庫表格
  public void onCreate(SQLiteDatabase db) {
   db.execSQL("CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
		      Pet._ID     + " INTEGER PRIMARY KEY," +
		      Pet.SPECIE  + " TEXT," + 
		      Pet.HABITAT + " TEXT" +
   		      ")");	  
  }

  //刪除資料庫表格
  public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
   db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
   this.onCreate(db);
  }
}
