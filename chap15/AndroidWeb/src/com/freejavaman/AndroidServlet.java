package com.freejavaman;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接收客戶端之請求，並回傳處理結果
 */
public class AndroidServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  //預設之建構者函數
  public AndroidServlet() {}

  protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   //判斷請求內容
   String actType = req.getParameter("actType");
   int xValue = 0;
   int yValue = 0;
   try {
	//取得輸入參數   
	xValue = Integer.parseInt(req.getParameter("xValue"));
	yValue = Integer.parseInt(req.getParameter("yValue"));
   } catch (Exception e) {	   
   }
   
    //組成執行結果
   StringBuffer xml = new StringBuffer("");   
   xml.append("<?xml version=\"1.0\" encoding=\"BIG5\" ?>");
   xml.append("<Service>\n");
   
   //執行對映服務
   if (actType != null && actType.equals("add")) {
	 //加法  
     xml.append("<Result>" + (xValue + yValue) + "</Result>\n");
   } else if (actType != null && actType.equals("dec")) {
	 //減法  
	 xml.append("<Result>" + (xValue - yValue) + "</Result>\n");
   } else if (actType != null && actType.equals("multiple")) {
	 //乘法  
	 xml.append("<Result>" + (xValue * yValue) + "</Result>\n");
   } else if (actType != null && actType.equals("div")) {
	 //除法  
	 xml.append("<Result>" + (xValue / yValue) + "</Result>\n");
   } else {
	 //無法判斷請求內容  
	 xml.append("<Error>type error</Error>\n");
   }
   
   //回傳結果結尾
   xml.append("</Service>\n");
	   
   //回傳處理結果給客戶端
   res.setContentType("text/html");
   PrintStream ps = new PrintStream(res.getOutputStream());
   ps.print(xml);  
   ps.flush();
   ps.close();
   ps = null;

  }
}
