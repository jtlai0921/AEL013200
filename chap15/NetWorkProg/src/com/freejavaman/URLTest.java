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
  
  //download HTML����
  doHTMLDownload();
 }
 
 //����U��HTML�����e
 private void doHTMLDownload(){
  try{ 
    URL myURL = new URL("http://www.android.com");
    
    //�z�LURL����}�ҿ�J��Ƭy
    DataInputStream in = new DataInputStream(myURL.openStream());
    
    //���^�P��� HTML���e
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