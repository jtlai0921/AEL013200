package com.freejavaman;
import java.util.*;

//用來處理旅行推銷員問題
public class TSPService {
 
 //一個族群中，包含6個染色體
 private TSPChromosome worm[] = new TSPChromosome[6];
 
 //儲存輪盤法中，每一個染色體所佔的比例區間
 private double rwheelRange[] = new double[6];
 
 //放置母代染色體的交配槽
 private TSPChromosome matingPool[] = new TSPChromosome[6];
 
 //交配機率(probability of performing crossover；PC)
 private double pc = 0.8;
 
 //突變機率(probability of mutation；PM)
 private double pm  = (double)0.001;
 
 private Vector chromosomeList;
 
 //建構者函數，傳入所要旅行的城市的染色體資料
 public TSPService(Vector chromosomeList) {
  this.chromosomeList = chromosomeList;
 }
 
 //從族群中，挑選適應值最小的組合，當成最佳解 
 public String getBestSolution() {
  
  int minValue = 100000000;
  String geneStr = "";
  
  //輪詢所有染色體
  for (int i = 0; i < worm.length; i++) {
   //判斷是否為較小的適應值	  
   if (worm[i].getFitnessValue() < minValue) {
	minValue = worm[i].getFitnessValue();
	geneStr = worm[i].getGeneString();
   }
  }
  return geneStr;
 }
 
 public void start() {	 
  //建立初代族群
  for (int i = 0; i < worm.length; i++) {
   worm[i] = new TSPChromosome();
   worm[i].setGeneArray((String)chromosomeList.elementAt(i));
  }
  
  //建立交配槽中的物件
  for (int i = 0; i < matingPool.length; i++) {
   matingPool[i] = new TSPChromosome();
  }
  
  int roop = 0;
  int roopMax = 10;
  
  do {
   System.out.println("第" + (roop + 1) + "次演化");
	  
   //計算輪盤區間
   setRange();  
   System.out.println("完成輪盤設定");
   
   //顯示輪盤區間
   /*for (int i = 0; i < rwheelRange.length; i++) {
   System.out.println("rwheelRange[" + i + "]:" + rwheelRange[i]);
   }*/
  
   //根據輪盤法，選擇母代，並置於交配槽之中
   doSelect();   
   System.out.println("完成母代選擇");
   
   //顯示交配槽之中的染色體
   /*for (int i = 0; i < matingPool.length; i++) {
    System.out.println("matingPool" + i + ":" + matingPool[i].getGeneString() + ",fitnessValue:" + matingPool[i].getFitnessValue());
   }*/
  
   //進行染色體交配
   doCrossover();
   System.out.println("完成染色體交配");
   
   //進行突變運算
   doMutation();
   System.out.println("完成突變運算");
  
   //進行子代替代母代的工作
   doReplace();
   System.out.println("完成替代");
   
   /*//顯示所有染色體的適應值
   for (int i = 0; i < worm.length; i++) {
    System.out.println("roop:" + (roop + 1) + ",worm" + i + ":" + worm[i].getGeneString() + ",fitnessValue:" + worm[i].getFitnessValue());
   }*/
   
   //進入下一次進化過程
   roop++;
  } while (roop < roopMax); 
 }
 
 //採取整群取代的策略
 private void doReplace() {
  for (int i = 0; i < worm.length; i++) {
   worm[i].setGeneArray(matingPool[i].getGeneString());
  }	 
 }
 
 //進行突變運算
 private void doMutation() {
  //根據染色體數，進行突變運算
  for (int i = 0; i < matingPool.length; i++) {
   //取出交配槽中的染色體	 
   TSPChromosome worm = matingPool[i];
	  
   //產生大於等於0, 且小於1的亂數
   double decide = java.lang.Math.random();
   
   //若亂數小於突變機率，才需進行突變運算
   if (decide <= pm) {
	matingPool[i].mutationACT();   
   }
  }
 }
 
 //進行染色體交配
 private void doCrossover() {
  //在交配槽中的染色體，兩兩進行交配
  //因為有6個染色體，因此，交配運算要執行3次
  for (int i = 0; i < 6; i+=2) {
   TSPChromosome worm1 = matingPool[i];  
   TSPChromosome worm2 = matingPool[i + 1];
   
   //取得染色體的資料內容
   String wormStr1 = worm1.getGeneString();  
   String wormStr2 = worm2.getGeneString();
   
   //產生大於等於0, 且小於1的亂數
   double decide = java.lang.Math.random();
   
   //若亂數小於交配機率，且染色體內容也不同時，才需進行交配
   if (decide <= pc && !wormStr1.equals(wormStr2)) {
    //if (true && !wormStr1.equals(wormStr2)) {
	
	int point1 = 0;
	int point2 = 0;
		
	do {   
	 //產生兩個交配點
	 point1 =  (int)(java.lang.Math.random() * worm1.getChromosomeLength());
	 point2 =  (int)(java.lang.Math.random() * worm1.getChromosomeLength());
	
	 //point1為左交配點，point2為右交配點
	 //若point2大於point1，則位置進行交換	
	 if (point2 < point1) {
	  int tmp = point1;
	  point1 = point2;
	  point2 = tmp;	
	 }
	 //若左右交配點，同時指向資料的最兩端，也沒有交配的意義
	} while(point1 == 0 && point2 == worm1.getChromosomeLength() - 1); 
	
	//point1 = 0;
	//point2 = 3;
	
	 //System.out.println("point1:" + point1);
	 //System.out.println("point2:" + point2);		
	 
	 //取得染色體的資料內容，並且根據交換點，切割成3個子區段
	 //第二個區段，即為要進行資料交換的區段
	 String wormStr1_sec1 = wormStr1.substring(0, point1);
	 String wormStr1_sec2 = wormStr1.substring(point1, point2 + 1);
	 String wormStr1_sec3 = wormStr1.substring(point2 + 1, wormStr1.length());
	 
	 String wormStr2_sec1 = wormStr2.substring(0, point1);
	 String wormStr2_sec2 = wormStr2.substring(point1, point2 + 1);
	 String wormStr2_sec3 = wormStr2.substring(point2 + 1, wormStr2.length());
	 
	 //System.out.println("wormStr1 split:" + wormStr1_sec1 + " " + wormStr1_sec2 + " " + wormStr1_sec3);
	 //System.out.println("wormStr2 split:" + wormStr2_sec1 + " " + wormStr2_sec2 + " " + wormStr2_sec3);
	 
	 //判斷第一個染色體新的第二個區段(第二個染色體的第二區段)
     //所有資料內容與舊資料之間的關係
	 for (int secInx = 0; secInx < wormStr2_sec2.length(); secInx++) {
	  //新區段的字元	 
	  String newChar= "" + wormStr2_sec2.charAt(secInx);
	  
	  //舊區段的字元
	  String oldChar= "" + wormStr1_sec2.charAt(secInx);
	  
	  //System.out.println("secInx:" + secInx + ", newChar:" + newChar + ",oldChar:" + oldChar);
	  
	  //判斷新字元是否存在於其它兩區段之中
	  if (wormStr1_sec1.indexOf(newChar) != -1 ||
		  wormStr1_sec3.indexOf(newChar) != -1) {
	   //新字元存重覆於其它兩區段，必須要剔除重覆項
	   //System.out.println("新字元存重覆於其它兩區段,newChar:" + newChar);
		  
	   //判斷舊字元是否存在於新區段之中
	   int oldInx = wormStr2_sec2.indexOf(oldChar);
	   if (oldInx != -1) {
		//舊字元存在於新區段之中
		//必須要找到適當的替代字元
		//System.out.println("舊字元存在於新區段,oldChar:" + oldChar + ",新區段:" + wormStr2_sec2);
		   
	    //oldInx: 舊字元在新區段中的位置		
		do {
		 //取得對映舊區段中, 相同位置的字元
		 oldChar = "" + wormStr1_sec2.charAt(oldInx);
		
		 //判斷該字元是否存在於新區段
		 oldInx = wormStr2_sec2.indexOf(oldChar);
		 
		 //System.out.println("找取代字元:" + oldChar + ",oldInx:" + oldInx);
		 
		} while (oldInx != -1); 
		
		//將舊字元取代其它區段中的重覆項
		wormStr1_sec1 = wormStr1_sec1.replace(newChar, oldChar);
		wormStr1_sec3 = wormStr1_sec3.replace(newChar, oldChar);		
	   } else {
		//舊字元不存在於新區段之中
		//System.out.println("舊字元不存在於新區段,oldChar:" + oldChar);
		//將舊字元取代其它區段中的重覆項
		wormStr1_sec1 = wormStr1_sec1.replace(newChar, oldChar);
		wormStr1_sec3 = wormStr1_sec3.replace(newChar, oldChar);
	   }  
	  } else {
	   //新字元不重覆於其它兩區段，因此，可以忽略不計
	   //System.out.println("新字元不重覆於其它兩區段");
	  }
	 }
	 
	 //判斷第一個染色體新的第二個區段(第二個染色體的第二區段)
     //所有資料內容與舊資料之間的關係
	 for (int secInx = 0; secInx < wormStr1_sec2.length(); secInx++) {
	  //新區段的字元	 
	  String newChar= "" + wormStr1_sec2.charAt(secInx);
	  
	  //舊區段的字元
	  String oldChar= "" + wormStr2_sec2.charAt(secInx);
	  
	  //System.out.println("secInx:" + secInx + ", newChar:" + newChar + ",oldChar:" + oldChar);
	  
	  //判斷新字元是否存在於其它兩區段之中
	  if (wormStr2_sec1.indexOf(newChar) != -1 ||
		  wormStr2_sec3.indexOf(newChar) != -1) {
	   //新字元存重覆於其它兩區段，必須要剔除重覆項
	   //System.out.println("新字元存重覆於其它兩區段,newChar:" + newChar);
		  
	   //判斷舊字元是否存在於新區段之中
	   int oldInx = wormStr1_sec2.indexOf(oldChar);
	   if (oldInx != -1) {
		//舊字元存在於新區段之中
		//必須要找到適當的替代字元
		//System.out.println("舊字元存在於新區段,oldChar:" + oldChar);
		   
	    //oldInx: 舊字元在新區段中的位置		
		do {
		 //取得對映舊區段中, 相同位置的字元
		 oldChar = "" + wormStr2_sec2.charAt(oldInx);
		
		 //判斷該字元是否存在於新區段
		 oldInx = wormStr1_sec2.indexOf(oldChar);
		} while (oldInx != -1); 
		
		//將舊字元取代其它區段中的重覆項
		wormStr2_sec1 = wormStr2_sec1.replace(newChar, oldChar);
		wormStr2_sec3 = wormStr2_sec3.replace(newChar, oldChar);		
	   } else {
		//舊字元不存在於新區段之中
		//System.out.println("舊字元不存在於新區段,oldChar:" + oldChar);
		//將舊字元取代其它區段中的重覆項
		wormStr2_sec1 = wormStr2_sec1.replace(newChar, oldChar);
		wormStr2_sec3 = wormStr2_sec3.replace(newChar, oldChar);
	   }  
	  } else {
	   //新字元不重覆於其它兩區段，因此，可以忽略不計
	   //System.out.println("新字元不重覆於其它兩區段");
	  }
	 }
	 
	 wormStr1 = wormStr1_sec1 + wormStr2_sec2 + wormStr1_sec3;
	 wormStr2 = wormStr2_sec1 + wormStr1_sec2 + wormStr2_sec3;
	 
	 //System.out.println("new wormStr1:" + wormStr1);
	 //System.out.println("new wormStr2:" + wormStr2);
	 
	 worm1.setGeneArray(wormStr1);  
	 worm2.setGeneArray(wormStr2);
   }   
  }
 } 
 
 //根據適應值，計算輪盤法中，每一段的區間比例
 private void setRange() {
  //加總適應值, 為求輪盤法的分母
  int totalFitnessValue = 0;
  for (int i = 0; i < worm.length; i++) {
   totalFitnessValue += worm[i].getFitnessValue();
  }
	  
  //計算在輪盤法中，每一個染色體所佔的比例
  //由於在TSP問題中，適應值越低越好，因此，在計算輪盤比例時，必須先用100去減，以求反轉  
  double rangeSubsum = 0.0;
  for (int i = 0; i < worm.length; i++) {
   //暫存反轉之後的值
   rwheelRange[i] = ((double)100 - ((double)worm[i].getFitnessValue()/totalFitnessValue) * 100);
   //小計總比例
   rangeSubsum += rwheelRange[i];
  }
	  
  //將進行輪盤內容累加，以形成區間
  double rangeSum = 0.0;
  for (int i = 0; i < rwheelRange.length; i++) {
   rangeSum += (rwheelRange[i]/rangeSubsum) * 100;	
   rwheelRange[i] = rangeSum;
  } 
 }
 
 //根據亂數與輪盤比例，選擇母代，並置於交配槽
 private void doSelect() {  
  //總共要選出數量相當的染色體	 
  for (int i = 0; i < worm.length; i++){
   //產生0 ~ 100之間的亂數，以決定區間	  
   int randomForSelect = (int)(java.lang.Math.random() * 101);
   
   //判斷那一個染色體應該置於交配槽之中(複製資料內容)
   if ((randomForSelect >= 0) && randomForSelect < rwheelRange[0]) {
	//區間一
	matingPool[i].setGeneArray(worm[0].getGeneString());   
   } else if ((randomForSelect >= rwheelRange[0]) && randomForSelect < rwheelRange[1]) {
	//區間二
	matingPool[i].setGeneArray(worm[1].getGeneString());
   } else if ((randomForSelect >= rwheelRange[1]) && randomForSelect < rwheelRange[2]) {
	//區間三
	matingPool[i].setGeneArray(worm[2].getGeneString());
   } else if ((randomForSelect >= rwheelRange[2]) && randomForSelect < rwheelRange[3]) {
	//區間四
	matingPool[i].setGeneArray(worm[3].getGeneString());
   } else if ((randomForSelect >= rwheelRange[3]) && randomForSelect < rwheelRange[4]) {
	//區間五
	matingPool[i].setGeneArray(worm[4].getGeneString());
   } else if ((randomForSelect >= rwheelRange[4]) && randomForSelect < rwheelRange[5]) {
	//區間六   
	matingPool[i].setGeneArray(worm[5].getGeneString());
   }   
  }  
 }
}


