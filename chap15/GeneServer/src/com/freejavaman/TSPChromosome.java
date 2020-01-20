package com.freejavaman;

import java.util.Enumeration;
import java.util.Hashtable;

//一個染色體，即代表一組解
public class TSPChromosome {
	 
 //客戶間距離對照表，共10個客戶(A ~ J)
 private static int[][] distance = {
  {0,20,25,30,60,10,15,22,6,16},
  {20,0,2,10,50,5,10,20,8,20},
  {25,2,0,3,40,7,13,25,11,19},
  {30,10,3,0,55,9,11,19,7,18},
  {60,50,40,55,0,12,15,16,10,21},
  {10,5,7,9,12,0,20,7,13,20},
  {15,10,13,11,15,20,0,30,5,17},
  {22,20,25,19,16,7,30,0,6,8},
  {6,8,11,7,10,13,5,6,0,13},
  {16,20,19,18,21,20,17,8,13,0}};
		 
 private char[] gene;
 
 public TSPChromosome(){}
	 
 public TSPChromosome(Hashtable cityHash){  
  //將使用者所選擇的城市，轉存於陣列之中
  int inx = 0;	 
  gene = new char[cityHash.size()];  
  Enumeration enums = cityHash.keys();
  while (enums.hasMoreElements()) {
   gene[inx] = (Character)enums.nextElement();
   inx++;
  }
	  
  //將城市代碼，亂數排序
  for (int i = 0; i < 10; i++) {
   //Math.random()回傳大於等於0, 到小於1的數值   	  
   int sInx = (int)(Math.random() * cityHash.size());
   int eInx = (int)(Math.random() * cityHash.size());
	   
   //將亂數所指向的索引資料內容，進行交換的工作
   char tmp = gene[sInx];
   gene[sInx] = gene[eInx];
   gene[eInx] = tmp;
  }  
 }
	 
 //執行突變運算，將陣列中任意兩基因進行交換
 public void mutationACT() {
  //Math.random()回傳大於等於0, 到小於1的數值   	  
  int sInx = (int)(Math.random() * gene.length);
  int eInx = (int)(Math.random() * gene.length);
		   
  //將亂數所指向的索引資料內容，進行交換的工作
  char tmp = gene[sInx];
  gene[sInx] = gene[eInx];
  gene[eInx] = tmp;	 
 }
	 
 //計算此染色體的適應值
 public int getFitnessValue() {
  int fitnessValue = 0;
  for (int i = 0; i < gene.length; i++) {
   //經由查表，取得目前索引值城市與下一個城市之間的距離   
   if (i == (gene.length - 1)) {
	 //陣列中最後一個城市時, 應該跟陣列第一個城市比對
	 int rowInx = ((int)gene[i]) - 65;
	 int colInx = ((int)gene[0]) - 65;
	 fitnessValue += distance[rowInx][colInx];		 
   } else {
	 //索引值指向目前城市, 與下一個城市 
	 //字元A的ASCII值為65, 因此, 便指向第0個元素   
	 int rowInx = ((int)gene[i]) - 65;
	 int colInx = ((int)gene[i + 1]) - 65;
	 fitnessValue += distance[rowInx][colInx];
   }  
  }
  return fitnessValue;
 }
	 
 //計算所傳入的染色體的適應值
 public synchronized static int caculateFitnessValue(String geneStr) {
  int fitnessValue = 0;
  char[] localGene = geneStr.toCharArray();
	  
  for (int i = 0; i < localGene.length; i++) {
   //經由查表，取得目前索引值城市與下一個城市之間的距離   
   if (i == (localGene.length - 1)) {
	 //陣列中最後一個城市時, 應該跟陣列第一個城市比對
	 int rowInx = ((int)localGene[i]) - 65;
	 int colInx = ((int)localGene[0]) - 65;
	 fitnessValue += distance[rowInx][colInx];		 
   } else {
	 //索引值指向目前城市, 與下一個城市 
	 //字元A的ASCII值為65, 因此, 便指向第0個元素   
	 int rowInx = ((int)localGene[i]) - 65;
	 int colInx = ((int)localGene[i + 1]) - 65;
	 fitnessValue += distance[rowInx][colInx];
   }  
  }
  return fitnessValue;
 }
	 
 //取得染色體長度
 public int getChromosomeLength() {
  if (gene != null)	 
   return gene.length;
  else
   return 0;	  
 }
 
 //根據基因資料，設定基因城市代碼
 public void setGeneArray(String geneStr) {
  gene = geneStr.toCharArray(); 
 }
	 
 //列印出染色體的內容
 public String getGeneString() {
  StringBuffer sBuf = new StringBuffer("");	 
  for (int i = 0; i < gene.length; i++) {
   sBuf.append(gene[i]);
  }
  return sBuf.toString();
 }
}
