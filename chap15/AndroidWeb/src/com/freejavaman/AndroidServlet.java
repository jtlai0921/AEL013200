package com.freejavaman;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �����Ȥ�ݤ��ШD�A�æ^�ǳB�z���G
 */
public class AndroidServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  //�w�]���غc�̨��
  public AndroidServlet() {}

  protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   //�P�_�ШD���e
   String actType = req.getParameter("actType");
   int xValue = 0;
   int yValue = 0;
   try {
	//���o��J�Ѽ�   
	xValue = Integer.parseInt(req.getParameter("xValue"));
	yValue = Integer.parseInt(req.getParameter("yValue"));
   } catch (Exception e) {	   
   }
   
    //�զ����浲�G
   StringBuffer xml = new StringBuffer("");   
   xml.append("<?xml version=\"1.0\" encoding=\"BIG5\" ?>");
   xml.append("<Service>\n");
   
   //�����M�A��
   if (actType != null && actType.equals("add")) {
	 //�[�k  
     xml.append("<Result>" + (xValue + yValue) + "</Result>\n");
   } else if (actType != null && actType.equals("dec")) {
	 //��k  
	 xml.append("<Result>" + (xValue - yValue) + "</Result>\n");
   } else if (actType != null && actType.equals("multiple")) {
	 //���k  
	 xml.append("<Result>" + (xValue * yValue) + "</Result>\n");
   } else if (actType != null && actType.equals("div")) {
	 //���k  
	 xml.append("<Result>" + (xValue / yValue) + "</Result>\n");
   } else {
	 //�L�k�P�_�ШD���e  
	 xml.append("<Error>type error</Error>\n");
   }
   
   //�^�ǵ��G����
   xml.append("</Service>\n");
	   
   //�^�ǳB�z���G���Ȥ��
   res.setContentType("text/html");
   PrintStream ps = new PrintStream(res.getOutputStream());
   ps.print(xml);  
   ps.flush();
   ps.close();
   ps = null;

  }
}
