package com.freejavaman;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class TSPCloud {
	
 //實作Map功能的物件	
 public static class MyMap extends MapReduceBase 
                         implements Mapper<LongWritable, Text, Text, Text> {
  
  public void map(LongWritable key, 
		          Text value, 
		          OutputCollector<Text, Text> output, 
		          Reporter reporter) throws IOException {	  
   
   //從資料檔中讀入一筆資料	  
   String line = value.toString();
   
   //每一筆資料，就是一個族群(第一欄為族群序號)
   String[] datas = line.split(" ");
   
   //儲存染色體資料之列表
   Vector chromosomeList = new Vector();
   for (int i = 1; i < 7; i++)   
    chromosomeList.addElement(datas[i]);
      
   //建立TSP基因演算法物件
   TSPService tService = new TSPService(chromosomeList);
   
   //開始進行基因演算法
   tService.start();
   
   //儲存該族群中，最適解
   //為了送往同一個reduce運算，使用同一個主鍵
   output.collect(new Text("TSPSolution"),  new Text(tService.getBestSolution()));
  }
 }

 //實作Reduce功能的物件
 public static class MyReduce extends MapReduceBase 
                            implements Reducer<Text, Text, Text, Text> {
  //取得所有主鍵值相同的資料
  public void reduce(Text key, 
		             Iterator<Text> values, 
		             OutputCollector<Text, Text> output, 
		             Reporter reporter) throws IOException {
    
	int minValue = 100000000;
	String minGeneStr = "";
	  
    //取得所有map運算的結果，計算最小適應值，當成最佳解
	while (values.hasNext()) {
	 //取得染色體字串	
     String geneStr = values.next().toString();
     
     //計算適應值
     //判斷是否為較小的適應值	  
     if (TSPChromosome.caculateFitnessValue(geneStr) < minValue) {
  	  minValue = TSPChromosome.caculateFitnessValue(geneStr);
  	  minGeneStr = geneStr;
     }
    }
	
   //儲存在負責包裹輸出資料的物件中   
   output.collect(new Text("TSPSolution"),  new Text("result:" + minGeneStr + "_" + minValue));
  }
 }
 
 //儲存TSP問題的城市代碼
 private Hashtable cityHash;
 
 //雲端運算的建構者函數
 public TSPCloud(Hashtable cityHash) {
  this.cityHash = cityHash;
 }
 
 //開始執行雲端基因演算
 public String doCloudOP() {
  System.out.println("start cloud OP");
	 
  boolean isLocalFolderOk = false;
  boolean isHDFSFolderOk = false;
  
  //欲產生的族群數，每一個族群會丟給一個map運算
  int group = 10;
  
  //儲存執行結果的字串
  String resultStr = "";
  
  //資料檔案暫存的本端目錄
  String folderName = "localFolder";
  
  //資料檔案，以日期時間為檔名的一部份
  String fileName = "TSP_" + this.getNowDate() + "_" + this.getNowTime();
  
  try {
	//在本端檔案系統中，建立暫存資料檔的目錄
  	File folder = new File(folderName);  
  	folder.mkdir();
  		
    //在本端檔案系統中，建立新的資料檔	 
    DataOutputStream fsOut = new DataOutputStream(new FileOutputStream("./" + folderName + "/" + fileName));
    
    //產生指定的族群數
	for (int i = 0; i < group; i++) {
		
	 //每個族群, 具6條染色體
	 StringBuffer chromosomeStr	= new StringBuffer("");
	 
	 //加入族群序號
	 chromosomeStr.append(i + " ");
	 
	 for (int j = 0; j < 6; j++) {
	  //亂數產生染色體內容	 
	  chromosomeStr.append(createChromosome() + " ");	 
	 }
	 
	 //將染色體資料寫到檔案之中
	 fsOut.writeBytes("" + chromosomeStr.toString() + "\n");
	 fsOut.flush();
	}
	
    //關閉本端的檔案
	fsOut.close();
	fsOut = null;    
	    
	System.out.println("create local folder:" + folderName + " and file:" + fileName + " done.");
	isLocalFolderOk = true;
  } catch (Exception e) {
  }
  
  //已在本端建立資料檔，準備複製至HDFS
  if (isLocalFolderOk) {
   System.out.println("start to copy local folder to HDFS...");
   
   try {
    //取得預設的組態設定	 
    Configuration conf = new Configuration();
	  
    //設定檔案資料來源與目的位置
    Path srcPath = new Path("./" + folderName + "/" + fileName);
    
    //HDFS目錄中的檔案
    Path dstPath = new Path("/TSPFolder/" + fileName);
	
	//取得封存檔案系統資訊的物件
	FileSystem hdfs = dstPath.getFileSystem(conf);
	   
	//將本端目錄中的檔案, 複製至HDFS
	hdfs.copyFromLocalFile(false, srcPath, dstPath);
	   
	System.out.println("copy local folder to HDFS done:" + "/TSPFolder/" + fileName);
	
	isHDFSFolderOk = true;
   } catch (Exception e) {
	System.out.println("copy local folder to HDFS error:" + e);	  
   }   
  }
  
  //資料檔案準備完成，開始進行運算
  try {
   if (isHDFSFolderOk) {
	//產生任務名稱(日期 + 時間)
	String jobName = "TSPJob_" + this.getNowDate() + "_" + this.getNowTime();
	
    JobConf conf = new JobConf(TSPCloud.class);
    
    conf.setJobName(jobName);
  
    //設定輸出入資料格式
    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);
  
    //設定實作Map與Reduce功能的類別
    conf.setMapperClass(MyMap.class);   
    conf.setReducerClass(MyReduce.class);
  
    //設定輸出資料主鍵值類別
    conf.setOutputKeyClass(Text.class);
  
    //設定輸出資料資料類別
    conf.setOutputValueClass(Text.class);
  
    //設定輸入檔案路徑
    FileInputFormat.setInputPaths(conf, new Path("/TSPFolder/" + fileName));
  
    //設定輸出檔案路徑(任務名稱為目錄的一部份)
    String outputFolder = "/TSPOutput/" + jobName;
    FileOutputFormat.setOutputPath(conf, new Path(outputFolder));
    
    System.out.println("HDFS Output folder:" + outputFolder);
    
    //執行雲端基因計算工作
    JobClient.runJob(conf);   
    
    //==========================================================
    try {
     //取得預設的組態設定	 
     Configuration hdfsConf = new Configuration();
	
	 //取得封存檔案系統資訊的物件
	 FileSystem hdfs = FileSystem.get(hdfsConf);
	
	 //基因演算法的結果檔
     Path outputPath = new Path("/TSPOutput/" + jobName + "/part-00000");
    
     //開啟輸入資料流
	 FSDataInputStream dis = hdfs.open(outputPath);
	
	 FileStatus stat = hdfs.getFileStatus(outputPath);
	
	 //讀入完整的內容
	 byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
	 dis.readFully(0, buffer);
	 resultStr = new String(buffer);
    } catch (Exception e2) {
     System.out.println("read from output file error:" + e2);
     resultStr = "ERR_Read_Output:" + e2;
    }
	//==========================================================
	System.out.println("operation done.");
   }
  }catch (Exception e) {
   System.out.println("do colud error:" + e);
   resultStr = "ERR_Colud_OP:" + e;
  }
  
  return resultStr;
 }
 
 //根據傳入的城市代碼，產生染色體資料
 private String createChromosome() {
  int inx = 0;
  
  //染色體長度，由前端使用者所選定的城市個數所決定
  char[] gene = new char[cityHash.size()];  
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
	
  //將基因資料組成字串，回傳上層
  StringBuffer sBuf = new StringBuffer("");	 
  for (int i = 0; i < gene.length; i++) {
   sBuf.append(gene[i]);
  }
  return sBuf.toString();	  
 }
 
 //取得目前系統日期,格式為 "年月日".
 public String getNowDate() {
  java.util.Date now = new java.util.Date();

  //取得"年"
  StringBuffer str = new StringBuffer("" + (now.getYear() + 1900));

  //取得"月"
  int x = now.getMonth() + 1;
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  //取得"日"
  x = now.getDate();
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  return str.toString();	
 }

 //取得目前系統時間,格式為 "時分秒".
 public String getNowTime() {
  java.util.Date now = new java.util.Date();
  StringBuffer str = new StringBuffer("");

  //取得"時"
  int x = now.getHours();
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  //取得"分" 
  x = now.getMinutes(); 
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  //取得"秒"
  x = now.getSeconds(); 
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  return str.toString();	
 } 
}

