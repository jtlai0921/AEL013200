package com.freejavaman;

import android.os.RemoteException;

public class BMIImplement extends BMIInterface.Stub {

 private float weight, height;
 
 //�]�w�魫��T
 public void setWeight(float w) throws RemoteException {
  this.weight = w;	
 }

 //�]�w������T
 public void setHeight(float h) throws RemoteException {
  this.height = h; 
 }
 
 //���o�p�⵲�G
 public float getBMI() throws RemoteException {
  return weight/(height * height);
 }
 
}
