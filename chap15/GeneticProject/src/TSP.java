import java.util.*;

//用來處理旅行推銷員問題
public class TSP {

 //儲存所要旅行的城市代碼
 private Hashtable cityHash;
 
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
 
 //建構者函數，傳入所要旅行的城市
 public TSP(char[] cities) {
  //將陣列轉存到Hashtable資料結構之中	 
  cityHash = new Hashtable();
  if (cities != null) {
   for (int i = 0; i < cities.length; i++) {
	if (cities[i] != -1 && !cityHash.containsKey(cities[i])) {
	 cityHash.put(cities[i], cities[i]);
	}//if (cities[i] != null && !cityHash.containsKey(cities[i])) ends
   }//for (int i = 0; i < cities.length; i++) ends
  }//if (cities != null) ends
  
  if (cityHash.size() == 0)
   return;
  
  //建立初代族群
  for (int i = 0; i < worm.length; i++) {
   worm[i] = new TSPChromosome(cityHash);
   //System.out.println("worm" + i + ":" + worm[i].getGeneString() + ",fitnessValue:" + worm[i].getFitnessValue());
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
   
   //顯示所有染色體的適應值
   for (int i = 0; i < worm.length; i++) {
    System.out.println("roop:" + (roop + 1) + ",worm" + i + ":" + worm[i].getGeneString() + ",fitnessValue:" + worm[i].getFitnessValue());
   }
   
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
	 point1 =  (int)(java.lang.Math.random() * cityHash.size());
	 point2 =  (int)(java.lang.Math.random() * cityHash.size());
	
	 //point1為左交配點，point2為右交配點
	 //若point2大於point1，則位置進行交換	
	 if (point2 < point1) {
	  int tmp = point1;
	  point1 = point2;
	  point2 = tmp;	
	 }
	 //若左右交配點，同時指向資料的最兩端，也沒有交配的意義
	} while(point1 == 0 && point2 == cityHash.size() - 1); 
	
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
 
 //主函數
 public static void main(String[] args) {
  //傳入所要進行TSP問題的城市代號
  char[] cities = {'A', 'B', 'D', 'E', 'G', 'I'};	 
  new TSP(cities);
 }
}

//一個染色體，即代表一組解
class TSPChromosome {
 
 //客戶間距離對照表，共10個客戶(A ~ J)
 private int[][] distance = {
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
