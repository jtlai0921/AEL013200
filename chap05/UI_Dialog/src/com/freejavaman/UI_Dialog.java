package com.freejavaman;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UI_Dialog extends Activity {
	
  TextView txt1;
  String[] stations = {"台北", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "高雄", "屏東"};
  boolean[] isChk = {false,false,false,false,false,false,false,false,false,false,false,};
  
  AlertDialog.Builder builder;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
        
    //建立TextView與Button物件實體
    txt1 = (TextView)this.findViewById(R.id.txt1);        
    Button btn1 = (Button)this.findViewById(R.id.btn1);
      
    //點選按鈕顯示AlertDialog
    btn1.setOnClickListener(new View.OnClickListener() {
     //點選按鈕
     public void onClick(View view) {
      AlertDialog.Builder builder = new AlertDialog.Builder(UI_Dialog.this);
      
      /*builder.setTitle("原始AlertDialog");
      builder.setMessage("沒有任何修飾");
      builder.show();  */
       
    	 
      //精簡語法 	 
      //new AlertDialog.Builder(UI_Dialog.this).setTitle("原始AlertDialog").setMessage("沒有任何修飾").show();
      
      //具有三個按鈕的對話框
      /*AlertDialog.Builder builder = new AlertDialog.Builder(UI_Dialog.this);
      builder.setTitle("具按鈕功能");
      builder.setMessage("三個功能鈕");      
      builder.setPositiveButton("左邊鈕", null);
      builder.setNeutralButton("中間鈕", null);
      builder.setNegativeButton("右邊鈕", null);
      builder.show();*/
            
      //builder = new AlertDialog.Builder(UI_Dialog.this);
      //builder.setTitle("具列表的對話框");
      /*builder.setMessage("出發車站：");      
      builder.setPositiveButton("左邊鈕", null);
      builder.setNeutralButton("中間鈕", null);
      builder.setNegativeButton("右邊鈕", null);*/
      
      //建立列表選項      
      /*builder.setItems(stations, new DialogInterface.OnClickListener(){
      public void onClick(DialogInterface dialog, int inx) {
 		 txt1.setText("您選擇的是" + stations[inx]);
 		} 
       });*/
      
      //顯示具單選列表的對話框
      /*builder.setSingleChoiceItems(stations, 0, new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog, int inx) {		 
		 txt1.setText("您選擇的是" + stations[inx]);
		} 
      });
      builder.show();*/
      
      //顯示具多選列表的對話框
      builder.setMultiChoiceItems(stations, isChk, new DialogInterface.OnMultiChoiceClickListener(){
		public void onClick(DialogInterface dialog, int inx, boolean isChked) {		 
		 //txt1.setText("您選擇的是" + stations[inx]);
		 Log.v("dialogTest", "do here");
		 
		 if (isChked) {
		   Log.v("dialogTest", "您選擇的是" + stations[inx]);	 
		 } else {
		   Log.v("dialogTest", "您取消的是" + stations[inx]);
		 }
		} 
      });
      builder.show();
      
 	 }
 	});      
  }
}