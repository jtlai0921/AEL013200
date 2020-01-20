package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UI_ListView extends Activity {
 
 String[] stations = {"�x�_", "���", "�s��", "�]��", "�x��", "����", "���L", "�Ÿq", "�x�n", "����", "�̪F"};	
	
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  ListView myListView = (ListView)this.findViewById(R.id.myListView);
  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);
  myListView.setAdapter(adapter);
 }
}