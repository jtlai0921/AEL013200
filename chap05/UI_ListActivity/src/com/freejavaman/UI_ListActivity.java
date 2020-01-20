package com.freejavaman;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class UI_ListActivity extends ListActivity {
  
  String[] stations = {"�x�_", "���", "�s��", "�]��", "�x��", "����", "���L", "�Ÿq", "�x�n", "����", "�̪F"};
	
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);
    this.setListAdapter(adapter);    
  }
}