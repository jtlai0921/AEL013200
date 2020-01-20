package com.freejavaman;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

public class CanvasDrawer extends Activity {

 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  //���n�ϥΪ����t�m�A�ӬO�ĥΦۭq��View����
  MyCanvasView view = new MyCanvasView(this);
  setContentView(view);
 }
 
 //�ۭq��View����
 private class MyCanvasView extends View {
  
  //�ۭqView���󪺫غc�̨��	 
  public MyCanvasView(Context context) {
   super(context);  
  }

  //�мg����"�e�ù�"���\��
  protected void onDraw(Canvas canvas) {
   super.onDraw(canvas);
   
   //�]�w�I���զ�
   canvas.drawColor(Color.WHITE);
   
   Paint paint = new Paint();
   paint.setAntiAlias(true); //��������
   
   //�]�w�Ť�
   paint.setStyle(Paint.Style.STROKE); 
   paint.setColor(Color.RED); //�]�w�C��
   paint.setStrokeWidth(8); //�Ťߥ~�ؼe��   
   canvas.drawCircle(80, 88, 80, paint);
   
   //�]�w ���
   paint.setStyle(Paint.Style.FILL); 
   paint.setColor(Color.YELLOW); //�]�w�C��
   canvas.drawCircle(240 + 4, 88, 80, paint);
   
   //�]�w���h
   Shader shader = new LinearGradient(0,0,168,168,
		               new int[]{Color.BLUE,Color.WHITE},
		               null,Shader.TileMode.REPEAT);
   paint.setShader(shader);
   canvas.drawCircle(400 + 4, 88, 80, paint);
   
   //�b�e���W��ܤ�r
   paint.setShader(null);
   paint.setColor(Color.GREEN);
   paint.setTextSize(68);
   canvas.drawText("�ڦb�e���W�g�r", 20, 300, paint);
  }
 }
 
 
}