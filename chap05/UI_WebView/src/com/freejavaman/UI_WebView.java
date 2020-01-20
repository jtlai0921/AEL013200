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
          
     //���oWebView�������
     btn = (Button)this.findViewById(R.id.btn);
     myWeb = (WebView)this.findViewById(R.id.myWeb);
     
     //���oWebView���]�w��
     WebSettings setting = myWeb.getSettings();
     
     //�]�w�ϥιw�]���Y��\��
     //setting.setBuiltInZoomControls(true);
     
     //�Ұ�JavaScript
     setting.setJavaScriptEnabled(true);
     
     //�Ұ�plugin
     setting.setPluginsEnabled(true);
     
     //�ھڧO�W, ���V���ѪA�Ȫ�����
     //myWeb.addJavascriptInterface(this, "myActivity");
     
     //�}�Ҩ㦳JavaScript�����պ���
     myWeb.loadUrl("file:///android_asset/javascriptTest2.html");
     
     //�ɮ���������alert�ƥ�
     myWeb.setWebChromeClient(new WebChromeClient(){
   	   public boolean onJsAlert(WebView view, String url, String message, JsResult result) {   		   
	    return super.onJsAlert(view, url, message, result);
	   } 
     });
     
     //�I����s�ɡA�I�sJavScript
     btn.setOnClickListener(new View.OnClickListener() {		
	   public void onClick(View view) {
	    myWeb.loadUrl("javascript:htmlAlert()");		
	   }
	 });
     
     //����WebViewClient
     /*myWeb.setWebViewClient(new WebViewClient() {    	 
		public void onPageFinished(WebView view, String url) {		 
		 super.onPageFinished(view, url);
		}		
		public void onPageStarted(WebView view, String url, Bitmap favicon) {		 
		 Toast.makeText(UI_WebView.this, "�}�l�U������", Toast.LENGTH_SHORT).show();
		 super.onPageStarted(view, url, favicon);
		}
     });
     //�}�Һ���
     myWeb.loadUrl("http://www.android.com");*/
          
     //�P�_�O�_���W�@�������A�A�p�G���A�h���o���e�����A
     //�Ϥ��A�h���s�U������
     /*if (savedInstanceState != null) {
       myWeb.restoreState(savedInstanceState);
     } else {
       myWeb.loadUrl("http://www.android.com");
     }*/
     
     //�}�ҥ����ɮ�
     //myWeb.loadUrl("file:///android_asset/android.html");
     
     //�ʺA�զX�������e
     /*String str = "<html>";
     str += "<head><title>PageTest</title></head>";
     str += "<body>";
     str += "<a href=\"http://android.com\">Android Offical Site</a>";     
     str += "</body>";
     str += "</html>";
     myWeb.loadData(str, "text/html", "UTF8");*/
    }
    
    //�NWebView�����A�A�x�s�bBundle���󤧤�
    /*protected void onSaveInstanceState(Bundle outState) {
     myWeb.saveState(outState);
    }*/
    
    /*public void onConfigurationChanged(Configuration newConfig){
     super.onConfigurationChanged(newConfig);
    }*/
  
  //�n��JavaScript�I�s���������  
  public void showToast() {
   Toast.makeText(UI_WebView.this, "�Ӧ�JavaScript���I�s", Toast.LENGTH_SHORT).show();  
  }
    
}