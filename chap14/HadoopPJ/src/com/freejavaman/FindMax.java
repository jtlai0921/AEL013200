package com.freejavaman;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class FindMax {
	
 //��@Map�\�઺����	
 public static class MyMap extends MapReduceBase 
                         implements Mapper<LongWritable, Text, Text, Text> {
  
  public void map(LongWritable key, 
		          Text value, 
		          OutputCollector<Text, Text> output, 
		          Reporter reporter) throws IOException {	  
   //�q��r��Ū�J�@����	  
   String line = value.toString();
   
   if (line != null && !line.equals("")) {
    //�i��r���R���u�@
    StringTokenizer tokenizer = new StringTokenizer(line);
   
    //���o�a�ϦW�١A�ΥH���D��
    String city = tokenizer.nextToken();
   
    //���o�q�����
    String date = tokenizer.nextToken();
   
    //���o�Ů�ìV����(PSI)
    String psi = tokenizer.nextToken();
   
    //�x�s�b�t�d�]�q��X��ƪ�����
    output.collect(new Text(city), new Text(date + " " + psi));
   }
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
   
   String tmpData = ""; //�Ȧs�̤j�����Ȫ����
   int tmpPSI = 0;      //�Ȧs�̤j�����Ȫ�PSI��
	  
   //���ߩҦ��D��ȬۦP�����, �D��Ȭ�city	  
   while (values.hasNext()) {
    try {
     //�P�_�O�_���̤j��PSI, �p�G�O�h�x�s��     
     StringTokenizer tokenizer = new StringTokenizer(values.next().toString());
     
     //���o�������
     String date = tokenizer.nextToken();
     
     //���oPSI��
     int psi = Integer.parseInt(tokenizer.nextToken());
     
     //�P�_�O�_���̤j������
     if (psi > tmpPSI) {
      tmpPSI  = psi;
      tmpData = date;
     }
    } catch (Exception e) {    	
    }
   }
   
   //�x�s�b�t�d�]�q��X��ƪ�����   
   output.collect(key,  new Text(tmpData + " " + tmpPSI));
  }
 }

 public static void main(String[] args) throws Exception {  	 
  JobConf conf = new JobConf(FindMax.class);
  conf.setJobName("FindMax");
  
  //�]�w��X�J��Ʈ榡
  conf.setInputFormat(TextInputFormat.class);
  conf.setOutputFormat(TextOutputFormat.class);
  
  //�]�w��@Map�PReduce�\�઺���O
  conf.setMapperClass(MyMap.class);
  //conf.setCombinerClass(MyReduce.class);
  conf.setReducerClass(MyReduce.class);
  
  //�]�w��X��ƥD������O
  conf.setOutputKeyClass(Text.class);
  
  //�]�w��X��Ƹ�����O
  conf.setOutputValueClass(Text.class);
  
  //�]�w��J�ɮ׸��|
  FileInputFormat.setInputPaths(conf, new Path(args[0]));
  
  //�]�w��X�ɮ׸��|
  FileOutputFormat.setOutputPath(conf, new Path(args[1]));
  
  //����p��u�@
  JobClient.runJob(conf);  
 }
}

