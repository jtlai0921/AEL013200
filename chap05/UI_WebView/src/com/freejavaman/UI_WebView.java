package com.freejavaman;

import java.net.URLEncoder;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class UI_WebView extends Activity {
	
	Button btn;
	WebView myWeb;
    
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
          
     //取得WebView物件實體
     btn = (Button)this.findViewById(R.id.btn);
     myWeb = (WebView)this.findViewById(R.id.myWeb);
     
     //取得WebView的設定值
     WebSettings setting = myWeb.getSettings();
     
     //設定使用預設的縮放功能
     //setting.setBuiltInZoomControls(true);
     
     //啟動JavaScript
     setting.setJavaScriptEnabled(true);
     
     //啟動plugin
     setting.setPluginsEnabled(true);
     
     //根據別名, 指向提供服務的物件
     //myWeb.addJavascriptInterface(this, "myActivity");
     
     //開啟具有JavaScript的測試網頁
     myWeb.loadUrl("file:///android_asset/javascriptTest2.html");
     
     //補捉網頁中的alert事件
     myWeb.setWebChromeClient(new WebChromeClient(){
   	   public boolean onJsAlert(WebView view, String url, String message, JsResult result) {   		   
	    return super.onJsAlert(view, url, message, result);
	   } 
     });
     
     //點選按鈕時，呼叫JavScript
     btn.setOnClickListener(new View.OnClickListener() {		
	   public void onClick(View view) {
	    myWeb.loadUrl("javascript:htmlAlert()");		
	   }
	 });
     
     //測試WebViewClient
     /*myWeb.setWebViewClient(new WebViewClient() {    	 
		public void onPageFinished(WebView view, String url) {		 
		 super.onPageFinished(view, url);
		}		
		public void onPageStarted(WebView view, String url, Bitmap favicon) {		 
		 Toast.makeText(UI_WebView.this, "開始下載網頁", Toast.LENGTH_SHORT).show();
		 super.onPageStarted(view, url, favicon);
		}
     });
     //開啟網站
     myWeb.loadUrl("http://www.android.com");*/
          
     //判斷是否有上一次的狀態，如果有，則取得之前的狀態
     //反之，則重新下載網頁
     /*if (savedInstanceState != null) {
       myWeb.restoreState(savedInstanceState);
     } else {
       myWeb.loadUrl("http://www.android.com");
     }*/
     
     //開啟本端檔案
     //myWeb.loadUrl("file:///android_asset/android.html");
     
     //動態組合網頁內容
     /*String str = "<html>";
     str += "<head><title>PageTest</title></head>";
     str += "<body>";
     str += "<a href=\"http://android.com\">Android Offical Site</a>";     
     str += "</body>";
     str += "</html>";
     myWeb.loadData(str, "text/html", "UTF8");*/
    }
    
    //將WebView的狀態，儲存在Bundle物件之中
    /*protected void onSaveInstanceState(Bundle outState) {
     myWeb.saveState(outState);
    }*/
    
    /*public void onConfigurationChanged(Configuration newConfig){
     super.onConfigurationChanged(newConfig);
    }*/
  
  //要讓JavaScript呼叫的成員函數  
  public void showToast() {
   Toast.makeText(UI_WebView.this, "來自JavaScript的呼叫", Toast.LENGTH_SHORT).show();  
  }
    
}