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
	
 //��@Map�\�઺����	
 public static class MyMap extends MapReduceBase 
                         implements Mapper<LongWritable, Text, Text, Text> {
  
  public void map(LongWritable key, 
		          Text value, 
		          OutputCollector<Text, Text> output, 
		          Reporter reporter) throws IOException {	  
   
   //�q����ɤ�Ū�J�@�����	  
   String line = value.toString();
   
   //�C�@����ơA�N�O�@�ӱڸs(�Ĥ@�欰�ڸs�Ǹ�)
   String[] datas = line.split(" ");
   
   //�x�s�V�����Ƥ��C��
   Vector chromosomeList = new Vector();
   for (int i = 1; i < 7; i++)   
    chromosomeList.addElement(datas[i]);
      
   //�إ�TSP��]�t��k����
   TSPService tService = new TSPService(chromosomeList);
   
   //�}�l�i���]�t��k
   tService.start();
   
   //�x�s�ӱڸs���A�̾A��
   //���F�e���P�@��reduce�B��A�ϥΦP�@�ӥD��
   output.collect(new Text("TSPSolution"),  new Text(tService.getBestSolution()));
  }
 }

 //��@Reduce�\�઺����
 public static class MyReduce extends MapReduceBase 
                            implements Reducer<Text, Text, Text, Text> {
  //���o�Ҧ��D��ȬۦP�����
  public void reduce(Text key, 
		             Iterator<Text> values, 
		             OutputCollector<Text, Text> output, 
		             Reporter reporter) throws IOException {
    
	int minValue = 100000000;
	String minGeneStr = "";
	  
    //���o�Ҧ�map�B�⪺���G�A�p��̤p�A���ȡA���̨θ�
	while (values.hasNext()) {
	 //���o�V����r��	
     String geneStr = values.next().toString();
     
     //�p��A����
     //�P�_�O�_�����p���A����	  
     if (TSPChromosome.caculateFitnessValue(geneStr) < minValue) {
  	  minValue = TSPChromosome.caculateFitnessValue(geneStr);
  	  minGeneStr = geneStr;
     }
    }
	
   //�x�s�b�t�d�]�q��X��ƪ�����   
   output.collect(new Text("TSPSolution"),  new Text("result:" + minGeneStr + "_" + minValue));
  }
 }
 
 //�x�sTSP���D�������N�X
 private Hashtable cityHash;
 
 //���ݹB�⪺�غc�̨��
 public TSPCloud(Hashtable cityHash) {
  this.cityHash = cityHash;
 }
 
 //�}�l���涳�ݰ�]�t��
 public String doCloudOP() {
  System.out.println("start cloud OP");
	 
  boolean isLocalFolderOk = false;
  boolean isHDFSFolderOk = false;
  
  //�����ͪ��ڸs�ơA�C�@�ӱڸs�|�ᵹ�@��map�B��
  int group = 10;
  
  //�x�s���浲�G���r��
  String resultStr = "";
  
  //����ɮ׼Ȧs�����ݥؿ�
  String folderName = "localFolder";
  
  //����ɮסA�H����ɶ����ɦW���@����
  String fileName = "TSP_" + this.getNowDate() + "_" + this.getNowTime();
  
  try {
	//�b�����ɮרt�Τ��A�إ߼Ȧs����ɪ��ؿ�
  	File folder = new File(folderName);  
  	folder.mkdir();
  		
    //�b�����ɮרt�Τ��A�إ߷s�������	 
    DataOutputStream fsOut = new DataOutputStream(new FileOutputStream("./" + folderName + "/" + fileName));
    
    //���ͫ��w���ڸs��
	for (int i = 0; i < group; i++) {
		
	 //�C�ӱڸs, ��6���V����
	 StringBuffer chromosomeStr	= new StringBuffer("");
	 
	 //�[�J�ڸs�Ǹ�
	 chromosomeStr.append(i + " ");
	 
	 for (int j = 0; j < 6; j++) {
	  //�üƲ��ͬV���餺�e	 
	  chromosomeStr.append(createChromosome() + " ");	 
	 }
	 
	 //�N�V�����Ƽg���ɮפ���
	 fsOut.writeBytes("" + chromosomeStr.toString() + "\n");
	 fsOut.flush();
	}
	
    //�������ݪ��ɮ�
	fsOut.close();
	fsOut = null;    
	    
	System.out.println("create local folder:" + folderName + " and file:" + fileName + " done.");
	isLocalFolderOk = true;
  } catch (Exception e) {
  }
  
  //�w�b���ݫإ߸���ɡA�ǳƽƻs��HDFS
  if (isLocalFolderOk) {
   System.out.println("start to copy local folder to HDFS...");
   
   try {
    //���o�w�]���պA�]�w	 
    Configuration conf = new Configuration();
	  
    //�]�w�ɮ׸�ƨӷ��P�ت���m
    Path srcPath = new Path("./" + folderName + "/" + fileName);
    
    //HDFS�ؿ������ɮ�
    Path dstPath = new Path("/TSPFolder/" + fileName);
	
	//���o�ʦs�ɮרt�θ�T������
	FileSystem hdfs = dstPath.getFileSystem(conf);
	   
	//�N���ݥؿ������ɮ�, �ƻs��HDFS
	hdfs.copyFromLocalFile(false, srcPath, dstPath);
	   
	System.out.println("copy local folder to HDFS done:" + "/TSPFolder/" + fileName);
	
	isHDFSFolderOk = true;
   } catch (Exception e) {
	System.out.println("copy local folder to HDFS error:" + e);	  
   }   
  }
  
  //����ɮ׷ǳƧ����A�}�l�i��B��
  try {
   if (isHDFSFolderOk) {
	//���ͥ��ȦW��(��� + �ɶ�)
	String jobName = "TSPJob_" + this.getNowDate() + "_" + this.getNowTime();
	
    JobConf conf = new JobConf(TSPCloud.class);
    
    conf.setJobName(jobName);
  
    //�]�w��X�J��Ʈ榡
    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);
  
    //�]�w��@Map�PReduce�\�઺���O
    conf.setMapperClass(MyMap.class);   
    conf.setReducerClass(MyReduce.class);
  
    //�]�w��X��ƥD������O
    conf.setOutputKeyClass(Text.class);
  
    //�]�w��X��Ƹ�����O
    conf.setOutputValueClass(Text.class);
  
    //�]�w��J�ɮ׸��|
    FileInputFormat.setInputPaths(conf, new Path("/TSPFolder/" + fileName));
  
    //�]�w��X�ɮ׸��|(���ȦW�٬��ؿ����@����)
    String outputFolder = "/TSPOutput/" + jobName;
    FileOutputFormat.setOutputPath(conf, new Path(outputFolder));
    
    System.out.println("HDFS Output folder:" + outputFolder);
    
    //���涳�ݰ�]�p��u�@
    JobClient.runJob(conf);   
    
    //==========================================================
    try {
     //���o�w�]���պA�]�w	 
     Configuration hdfsConf = new Configuration();
	
	 //���o�ʦs�ɮרt�θ�T������
	 FileSystem hdfs = FileSystem.get(hdfsConf);
	
	 //��]�t��k�����G��
     Path outputPath = new Path("/TSPOutput/" + jobName + "/part-00000");
    
     //�}�ҿ�J��Ƭy
	 FSDataInputStream dis = hdfs.open(outputPath);
	
	 FileStatus stat = hdfs.getFileStatus(outputPath);
	
	 //Ū�J���㪺���e
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
 
 //�ھڶǤJ�������N�X�A���ͬV������
 private String createChromosome() {
  int inx = 0;
  
  //�V������סA�ѫe�ݨϥΪ̩ҿ�w�������ӼƩҨM�w
  char[] gene = new char[cityHash.size()];  
  Enumeration enums = cityHash.keys();
  while (enums.hasMoreElements()) {
   gene[inx] = (Character)enums.nextElement();
   inx++;
  }
			  
  //�N�����N�X�A�üƱƧ�
  for (int i = 0; i < 10; i++) {
   //Math.random()�^�Ǥj�󵥩�0, ��p��1���ƭ�   	  
   int sInx = (int)(Math.random() * cityHash.size());
   int eInx = (int)(Math.random() * cityHash.size());
			   
   //�N�üƩҫ��V�����޸�Ƥ��e�A�i��洫���u�@
   char tmp = gene[sInx];
   gene[sInx] = gene[eInx];
   gene[eInx] = tmp;
  }
	
  //�N��]��Ʋզ��r��A�^�ǤW�h
  StringBuffer sBuf = new StringBuffer("");	 
  for (int i = 0; i < gene.length; i++) {
   sBuf.append(gene[i]);
  }
  return sBuf.toString();	  
 }
 
 //���o�ثe�t�Τ��,�榡�� "�~���".
 public String getNowDate() {
  java.util.Date now = new java.util.Date();

  //���o"�~"
  StringBuffer str = new StringBuffer("" + (now.getYear() + 1900));

  //���o"��"
  int x = now.getMonth() + 1;
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  //���o"��"
  x = now.getDate();
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  return str.toString();	
 }

 //���o�ثe�t�ήɶ�,�榡�� "�ɤ���".
 public String getNowTime() {
  java.util.Date now = new java.util.Date();
  StringBuffer str = new StringBuffer("");

  //���o"��"
  int x = now.getHours();
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  //���o"��" 
  x = now.getMinutes(); 
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  //���o"��"
  x = now.getSeconds(); 
  if (x < 10)
   str.append("0" + x);
  else
   str.append("" + x);

  return str.toString();	
 } 
}

