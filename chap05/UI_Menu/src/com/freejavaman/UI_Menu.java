package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class UI_Menu extends Activity {
 
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
 }
 
 //設定每一個選項獨一無二的ID
 private static final int Item_file = Menu.FIRST; 
 private static final int Item_save = Menu.FIRST + 1;
 private static final int Item_saveAs = Menu.FIRST + 2;
 private static final int Item_edit = Menu.FIRST + 3;
 private static final int Item_copy = Menu.FIRST + 4;
 private static final int Item_delete = Menu.FIRST + 5;
 
 //建立選單物件的選項
 public boolean onCreateOptionsMenu(Menu menu) {  
  //建立第一個Menu
  SubMenu sub1 = menu.addSubMenu(Menu.NONE, Item_file, 0, "檔案");
  sub1.add(Menu.NONE, Item_save, 0, "儲存");
  sub1.add(Menu.NONE, Item_saveAs, 1, "另存新檔");
  
  //建立第二個Menu
  SubMenu sub2 = menu.addSubMenu(Menu.NONE, Item_edit, 1, "編輯");
  sub2.add(Menu.NONE, Item_copy, 0, "複製");
  sub2.add(Menu.NONE, Item_delete, 1, "刪除");
  
  return super.onCreateOptionsMenu(menu);
 }
 
 //根據選項的ID, 進行對映的動作
 public boolean onOptionsItemSelected(MenuItem item) {
  switch(item.getItemId()) {
    case Item_file:
    	 break;
    case Item_save:
   	     break;
    case Item_saveAs:
   	     break;
    case Item_edit:
   	     break;
    case Item_copy:
   	     break;
    case Item_delete:
   	     break;
    default:break;
  }
  return super.onOptionsItemSelected(item);
 }
 
}