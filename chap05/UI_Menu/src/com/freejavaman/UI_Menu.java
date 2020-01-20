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
 
 //�]�w�C�@�ӿﶵ�W�@�L�G��ID
 private static final int Item_file = Menu.FIRST; 
 private static final int Item_save = Menu.FIRST + 1;
 private static final int Item_saveAs = Menu.FIRST + 2;
 private static final int Item_edit = Menu.FIRST + 3;
 private static final int Item_copy = Menu.FIRST + 4;
 private static final int Item_delete = Menu.FIRST + 5;
 
 //�إ߿�檫�󪺿ﶵ
 public boolean onCreateOptionsMenu(Menu menu) {  
  //�إ߲Ĥ@��Menu
  SubMenu sub1 = menu.addSubMenu(Menu.NONE, Item_file, 0, "�ɮ�");
  sub1.add(Menu.NONE, Item_save, 0, "�x�s");
  sub1.add(Menu.NONE, Item_saveAs, 1, "�t�s�s��");
  
  //�إ߲ĤG��Menu
  SubMenu sub2 = menu.addSubMenu(Menu.NONE, Item_edit, 1, "�s��");
  sub2.add(Menu.NONE, Item_copy, 0, "�ƻs");
  sub2.add(Menu.NONE, Item_delete, 1, "�R��");
  
  return super.onCreateOptionsMenu(menu);
 }
 
 //�ھڿﶵ��ID, �i���M���ʧ@
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