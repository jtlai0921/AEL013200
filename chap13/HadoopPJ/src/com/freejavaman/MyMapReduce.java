package com.freejavaman;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class MyMapReduce {
 
 //實作Map功能的物件	
 public static class MyMap extends MapReduceBase 
                         implements Mapper<LongWritable, Text, Text, IntWritable> {
  
  private Text txt = new Text();

  public void map(LongWritable key, 
		          Text value, 
		          OutputCollector<Text, IntWritable> output, 
		          Reporter reporter) throws IOException {
   //從文字檔讀入一行資料	  
   String line = value.toString();
   
   //進行字串剖析的工作
   StringTokenizer tokenizer = new StringTokenizer(line);
   
   //輪詢, 取得所有的字串
   while (tokenizer.hasMoreTokens()) {
	txt.set(tokenizer.nextToken());
	//儲存在負責包裹輸出資料的物件中
    output.collect(txt, new IntWritable(1));
   }
  }
 }

 //實作Combiner與Reduce功能的物件
 public static class MyReduce extends MapReduceBase 
                            implements Reducer<Text, IntWritable, Text, IntWritable> {
  //取得所有主鍵值相同的資料
  public void reduce(Text key, 
		             Iterator<IntWritable> values, 
		             OutputCollector<Text, IntWritable> output, 
		             Reporter reporter) throws IOException {
   //計算主鍵值相同的資料筆數	  
   int sum = 0;
   while (values.hasNext()) {
    sum += values.next().get();    
   }
   
   //儲存在負責包裹輸出資料的物件中
   output.collect(key, new IntWritable(sum));
  }
 }

 public static void main(String[] args) throws Exception {  	 
  JobConf conf = new JobConf(MyMapReduce.class);
  conf.setJobName("MyMapReduce");
  
  //設定輸出入資料格式
  conf.setInputFormat(TextInputFormat.class);
  conf.setOutputFormat(TextOutputFormat.class);
  
  //設定實作Map與Reduce功能的類別
  conf.setMapperClass(MyMap.class);
  conf.setCombinerClass(MyReduce.class);
  conf.setReducerClass(MyReduce.class);
  
  //設定輸出資料主鍵值類別
  conf.setOutputKeyClass(Text.class);
  
  //設定輸出資料資料類別
  conf.setOutputValueClass(IntWritable.class);
  
  //設定輸入檔案路徑
  FileInputFormat.setInputPaths(conf, new Path(args[0]));
  
  //設定輸出檔案路徑
  FileOutputFormat.setOutputPath(conf, new Path(args[1]));
  
  //執行計算工作
  JobClient.runJob(conf);  
 }
}

