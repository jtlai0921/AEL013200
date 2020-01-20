package com.freejavaman;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class MyHDFS {
 
 public static void main(String[] args) throws Exception {
  //���o�w�]���պA�]�w	 
  Configuration conf = new Configuration();
  
  //�]�w�ɮ׸�ƨӷ��P�ت���m
  Path srcPath = new Path(args[0]);
  Path dstPath = new Path(args[1]);
  
  try {
   //���o�ʦs�ɮרt�θ�T������
   FileSystem hdfs = dstPath.getFileSystem(conf);
   
   //�b�������ɮרt�Τ��A�إߥؿ�
   hdfs.mkdirs(dstPath);
   
   //�N���ݥؿ������ɮ�, �ƻs��HDFS
   hdfs.copyFromLocalFile(false, srcPath, dstPath);
   
   System.out.println("MyHDFS, copy file ok");
  } catch (Exception e) {
   System.out.println("MyHDFS, copy file error:" + e);	  
  }    
 }
}

