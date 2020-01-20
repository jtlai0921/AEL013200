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

public class Summa {
	
 //��@Map�\�઺����	
 public static class MyMap extends MapReduceBase 
                         implements Mapper<LongWritable, Text, Text, DoubleWritable> {
  
  public void map(LongWritable key, 
		          Text value, 
		          OutputCollector<Text, DoubleWritable> output, 
		          Reporter reporter) throws IOException {	  
   
   //�q����ɤ�Ū�J�@�����	  
   String line = value.toString();
   
   //���o�x�Ϊ���
   double height = Double.parseDouble(line.substring(0, line.indexOf(" ")));
   
   //���o�x�Ϊ��e
   double width = Double.parseDouble(line.substring(line.indexOf(" ") + 1, line.length()));
   
   //�x�s�x�έ��n
   output.collect(new Text("square"),  new DoubleWritable(height * width));
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
   
   //�N�Ҧ�map�B�⪺���G�[�`�A�����n���B��
   double totalArea = 0.0;
   while (values.hasNext()) {
	totalArea += values.next().get();
   }
   
   //�x�s�b�t�d�]�q��X��ƪ�����   
   output.collect(new Text("TotalArea"),  new DoubleWritable(totalArea));
  }
 }

 public static void main(String[] args) throws Exception {
  
  boolean isLocalFolderOk = false;
  boolean isHDFSFolderOk = false;
	 
  String folderName = "localFolder";
  
  //�ҭn�p�⪺�n���϶��U��
  int rangeA = Integer.parseInt(args[0]);
  
  //�ҭn�p�⪺�n���϶��W��
  int rangeB = Integer.parseInt(args[1]);
  
  //�϶��ҭn���j�����q��
  int splits = Integer.parseInt(args[2]);
  
  //���q�Ƥ��o��0
  if (splits != 0) {
    //�p��C�@�ӯx�Ϊ��e��
    double width = (double)(rangeB - rangeA)/(double)splits;
    if (width != 0) {
      //�p��C�@�ӯx�Ϊ���,�Q�Τ��� y = x ^ 2  
      try {
    	//�b�����ɮרt�Τ��A�إ߼Ȧs����ɪ��ؿ�
    	File folder = new File(folderName);  
    	folder.mkdir();
    		
        //�b�����ɮרt�Τ��A�إ߷s�������	 
        DataOutputStream fsOut = new DataOutputStream(new FileOutputStream("./" + folderName + "/dataFile"));
    	    
        //�ھڤ��q�ơA�إ߸�Ƥ��q
        double x = rangeA; //�϶��U�����_�l�I
        double y = 0; //�x�Ϊ���
        
        //�إ߸���ɤ��e
        //�x�Ϊ��� + �ť� + �x�Ϊ��e
    	for (int i = 0; i < splits; i++) {
    	  
    	  //�ھڱ��i��n���������A�d�o�x�Ϊ���	
    	  y = x * x; //����: y = x ^ 2
    	  
    	  //System.out.print("" + y + " " + width + "\n");
    	  
    	  //�g�X�x�θ��	
    	  fsOut.writeBytes("" + y + " " + width + "\n");
    	  fsOut.flush();
    	  
    	  //����U�@�Ӥ��q
    	  x += width;
    	}
    	    
    	//�������ݪ��ɮ�
    	fsOut.close();
    	fsOut = null;    
    	    
    	System.out.println("create local folder:" + folderName + " and data file done.");
    	isLocalFolderOk = true;
      } catch (Exception e) {
    	System.out.println("create local folder, error:" + e);  
      }
    } else {
      System.out.println("width could not to be 0");
    }
  } else {
	System.out.println("splits could not to be 0");  
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
   JobConf conf = new JobConf(Summa.class);
   conf.setJobName("Summa");
  
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
   FileOutputFormat.setOutputPath(conf, new Path("summaOutput"));
  
   //����p��u�@
   JobClient.runJob(conf);   
   System.out.println("operation done.");
  }
 }
}

