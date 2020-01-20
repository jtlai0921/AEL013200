package com.freejavaman;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class MonteCarlo {
 
 //�üƭn���ͪ��y�м�
 public static int totalPoint = 1000000;	
	
 //��@Map�\�઺����	
 public static class MyMap extends MapReduceBase 
                         implements Mapper<LongWritable, Text, Text, DoubleWritable> {
  
  public void map(LongWritable key, 
		          Text value, 
		          OutputCollector<Text, DoubleWritable> output, 
		          Reporter reporter) throws IOException {	  
   
   double x = Math.random(); //���o0 ~ 1���ü�
   double y = Math.random(); //���o0 ~ 1���ü�
   
   x -= 0.5; //�ϱo x ���� -0.5 ~ 0.5����
   y -= 0.5; //�ϱo y ���� -0.5 ~ 0.5����
   
   //�P�_�O�_���b�ꤺ, �h�O���@�����b�ꤺ���O��
   if ((x * x) + (y * y) <= 0.25) {
	output.collect(new Text("InCircle"),  new DoubleWritable(1.0));   
   }   
  }
 }

 //��@Reduce�\�઺����
 public static class MyReduce extends MapReduceBase 
                            implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {
  //���o�Ҧ��D��ȬۦP�����
  public void reduce(Text key, 
		             Iterator<DoubleWritable> values, 
		             OutputCollector<Text, DoubleWritable> output, 
		             Reporter reporter) throws IOException {
   
   //�P�_map�B���A�@���h�ֵ����b�ꤺ
   double inCircle = 0.0;
   while (values.hasNext()) {
    values.next();
    inCircle++;
   }
   
   //��P�v = 4 * (n/m)
   double pi = 4 * (inCircle/MonteCarlo.totalPoint);
   
   //�x�s�b�t�d�]�q��X��ƪ�����   
   output.collect(new Text("PI"),  new DoubleWritable(pi));
  }
 }

 public static void main(String[] args) throws Exception {
  
  boolean isLocalFolderOk = false;
  boolean isHDFSFolderOk = false;
	 
  String folderName = "localFolder";
  
  //�ھڭn���ͪ��üƮy�мơA�ʺA�إ߸����
  try {
	//�b�����ɮרt�Τ��A�إ߼Ȧs����ɪ��ؿ�
	File folder = new File(folderName);  
	folder.mkdir();
	
    //�b�����ɮרt�Τ��A�إ߷s�������	 
    DataOutputStream fsOut = new DataOutputStream(new FileOutputStream("./" + folderName + "/dataFile"));
    
    //�ھڮy�мơA�إ߸�Ƥ��q
    for (int i = 0; i < MonteCarlo.totalPoint; i++) {
     fsOut.writeBytes("" + i + "\n");
     fsOut.flush();
    }
    
    //�������ݪ��ɮ�
    fsOut.close();
    fsOut = null;    
    
    System.out.println("create local folder:" + folderName + " and data file done.");
    isLocalFolderOk = true;
  } catch (Exception e) {
	System.out.println("create local folder, error:" + e);  
  }
  
  //�w�b���ݫإ߸���ɡA�ǳƽƻs��HDFS
  if (isLocalFolderOk) {
   try {
    //���o�w�]���պA�]�w	 
    Configuration conf = new Configuration();
	  
    //�]�w�ɮ׸�ƨӷ��P�ت���m
    Path srcPath = new Path("./" + folderName); //���ݥؿ�
    Path dstPath = new Path("/"); //HDFS�ڥؿ�
	
	//���o�ʦs�ɮרt�θ�T������
	FileSystem hdfs = dstPath.getFileSystem(conf);
	   
	//�N���ݥؿ������ɮ�, �ƻs��HDFS
	hdfs.copyFromLocalFile(false, srcPath, dstPath);
	   
	System.out.println("copy local folder to HDFS done.");
	isHDFSFolderOk = true;
   } catch (Exception e) {
	System.out.println("copy local folder to HDFS error:" + e);	  
   }   
  }
  
  //����ɮ׷ǳƧ����A�}�l�i��B��
  if (isHDFSFolderOk) { 
   JobConf conf = new JobConf(MonteCarlo.class);
   conf.setJobName("MonteCarlo");
  
   //�]�w��X�J��Ʈ榡
   conf.setInputFormat(TextInputFormat.class);
   conf.setOutputFormat(TextOutputFormat.class);
  
   //�]�w��@Map�PReduce�\�઺���O
   conf.setMapperClass(MyMap.class);   
   conf.setReducerClass(MyReduce.class);
  
   //�]�w��X��ƥD������O
   conf.setOutputKeyClass(Text.class);
  
   //�]�w��X��Ƹ�����O
   conf.setOutputValueClass(DoubleWritable.class);
  
   //�]�w��J�ɮ׸��|
   FileInputFormat.setInputPaths(conf, new Path("/" + folderName));
  
   //�]�w��X�ɮ׸��|
   FileOutputFormat.setOutputPath(conf, new Path("monteCarloOutput"));
  
   //����p��u�@
   JobClient.runJob(conf);
   
   System.out.println("operation done.");
  }
 }
}

