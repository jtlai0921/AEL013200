package com.freejavaman;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class URLTest extends Activity {
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  //download HTML內文
  doHTMLDownload();
 }
 
 //執行下載HTML的內容
 private void doHTMLDownload(){
  try{ 
    URL myURL = new URL("http://www.android.com");
    
    //透過URL物件開啟輸入資料流
    DataInputStream in = new DataInputStream(myURL.openStream());
    
    //取回與顯示 HTML內容
    String str1 = in.readLine();
    while (str1 != null) {
     Log.v("network", "data:" + str1);
     str1 = in.readLine();     
    }
    
    in.close();
    in = null;
  } catch(MalformedURLException e){
    Log.e("nerwork", "MalformedURLException:" + e);
  } catch(IOException e){
	Log.e("nerwork", "IOException:" + e);
  } catch(Exception e){
	Log.e("nerwork", "Exception:" + e);  
  }
 }
 
}