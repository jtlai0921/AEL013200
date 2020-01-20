package com.freejavaman;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class MyHDFS {
 
 public static void main(String[] args) throws Exception {
  //取得預設的組態設定	 
  Configuration conf = new Configuration();
  
  //設定檔案資料來源與目的位置
  Path srcPath = new Path(args[0]);
  Path dstPath = new Path(args[1]);
  
  try {
   //取得封存檔案系統資訊的物件
   FileSystem hdfs = dstPath.getFileSystem(conf);
   
   //在分散式檔案系統中，建立目錄
   hdfs.mkdirs(dstPath);
   
   //將本端目錄中的檔案, 複製至HDFS
   hdfs.copyFromLocalFile(false, srcPath, dstPath);
   
   System.out.println("MyHDFS, copy file ok");
  } catch (Exception e) {
   System.out.println("MyHDFS, copy file error:" + e);	  
  }    
 }
}

