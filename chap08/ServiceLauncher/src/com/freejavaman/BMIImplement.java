package com.freejavaman;

import android.os.RemoteException;

public class BMIImplement extends BMIInterface.Stub {

 private float weight, height;
 
 //設定體重資訊
 public void setWeight(float w) throws RemoteException {
  this.weight = w;	
 }

 //設定身高資訊
 public void setHeight(float h) throws RemoteException {
  this.height = h; 
 }
 
 //取得計算結果
 public float getBMI() throws RemoteException {
  return weight/(height * height);
 }
 
}
