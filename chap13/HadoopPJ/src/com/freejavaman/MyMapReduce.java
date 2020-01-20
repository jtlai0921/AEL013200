package com.freejavaman;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class MyMapReduce {
 
 //��@Map�\�઺����	
 public static class MyMap extends MapReduceBase 
                         implements Mapper<LongWritable, Text, Text, IntWritable> {
  
  private Text txt = new Text();

  public void map(LongWritable key, 
		          Text value, 
		          OutputCollector<Text, IntWritable> output, 
		          Reporter reporter) throws IOException {
   //�q��r��Ū�J�@����	  
   String line = value.toString();
   
   //�i��r���R���u�@
   StringTokenizer tokenizer = new StringTokenizer(line);
   
   //����, ���o�Ҧ����r��
   while (tokenizer.hasMoreTokens()) {
	txt.set(tokenizer.nextToken());
	//�x�s�b�t�d�]�q��X��ƪ�����
    output.collect(txt, new IntWritable(1));
   }
  }
 }

 //��@Combiner�PReduce�\�઺����
 public static class MyReduce extends MapReduceBase 
                            implements Reducer<Text, IntWritable, Text, IntWritable> {
  //���o�Ҧ��D��ȬۦP�����
  public void reduce(Text key, 
		             Iterator<IntWritable> values, 
		             OutputCollector<Text, IntWritable> output, 
		             Reporter reporter) throws IOException {
   //�p��D��ȬۦP����Ƶ���	  
   int sum = 0;
   while (values.hasNext()) {
    sum += values.next().get();    
   }
   
   //�x�s�b�t�d�]�q��X��ƪ�����
   output.collect(key, new IntWritable(sum));
  }
 }

 public static void main(String[] args) throws Exception {  	 
  JobConf conf = new JobConf(MyMapReduce.class);
  conf.setJobName("MyMapReduce");
  
  //�]�w��X�J��Ʈ榡
  conf.setInputFormat(TextInputFormat.class);
  conf.setOutputFormat(TextOutputFormat.class);
  
  //�]�w��@Map�PReduce�\�઺���O
  conf.setMapperClass(MyMap.class);
  conf.setCombinerClass(MyReduce.class);
  conf.setReducerClass(MyReduce.class);
  
  //�]�w��X��ƥD������O
  conf.setOutputKeyClass(Text.class);
  
  //�]�w��X��Ƹ�����O
  conf.setOutputValueClass(IntWritable.class);
  
  //�]�w��J�ɮ׸��|
  FileInputFormat.setInputPaths(conf, new Path(args[0]));
  
  //�]�w��X�ɮ׸��|
  FileOutputFormat.setOutputPath(conf, new Path(args[1]));
  
  //����p��u�@
  JobClient.runJob(conf);  
 }
}

