package com.freejavaman;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseTest {
 
 public HBaseTest() {
  //�]�wZooKeeper���`�I�A�s����	 
  HBaseConfiguration config = new HBaseConfiguration();
  config.clear();
  config.set("hbase.zookeeper.quorum", "linux1.freejavaman");
  config.set("hbase.zookeeper.property.clientPort","2181");
	 
  //doScan(config);
  //doPut(config);
  //doUpdate(config);
  //doDeleteRow(config);
  //doDeleteFamily(config);
  //doDeleteColumn(config);
  //doCreateTable(config);
  //doDropColumn(config);
  doModifyColumn(config);
 }
 
 //�ק�P�s�W��a��
 private void doModifyColumn(HBaseConfiguration config) {
  try {
   //HBase�޲z����
   HBaseAdmin admin = new HBaseAdmin(config); 
   
   //���W��
   String tableName = "ProductTable";
   
   //�P�_���O�_�w�g�s�b
   if (admin.tableExists(tableName)) {
	//�T��Ӫ��   
	admin.disableTable(tableName);
	
	//�W�[�s����a��
	HColumnDescriptor familyDesc = new HColumnDescriptor("memo");
	admin.addColumn(tableName, familyDesc);
	
	//�ק��a��
	HColumnDescriptor familyDesc2 = new HColumnDescriptor("spec");	
	familyDesc2.setTimeToLive(60 * 3);
	admin.modifyColumn(tableName, familyDesc2);
	
	//�P��Ӫ��
	admin.enableTable(tableName);
   }   
  } catch (Exception e) {
   System.out.println("doDropColumn error:" + e);	  
  }
 }
 
 //�R����a��
 private void doDropColumn(HBaseConfiguration config) {
  try {
   //HBase�޲z����
   HBaseAdmin admin = new HBaseAdmin(config); 
   
   //���W��
   String tableName = "ProductTable";
   
   //�P�_���O�_�w�g�s�b
   if (admin.tableExists(tableName)) {
	//�T��Ӫ��   
	admin.disableTable(tableName);
	 
	//�p�G�s�b�A�~�i�H�ק���n	
	admin.deleteColumn(tableName, "price");
	
	//�P��Ӫ��
	admin.enableTable(tableName);
   }   
  } catch (Exception e) {
   System.out.println("doDropColumn error:" + e);	  
  }
 }
 
 //�إ߸�ƪ��
 private void doCreateTable(HBaseConfiguration config) {
  try {
   //HBase�޲z����
   HBaseAdmin admin = new HBaseAdmin(config); 
   
   //���W��
   String tableName = "ProductTable";
   
   //�P�_���O�_�w�g�s�b
   if (!admin.tableExists(tableName)) {
	//�p�G���s�b�A�K�}�l�]�w�����n
	
	//�إߴy�z��檺����   
	HTableDescriptor tableDesc = new HTableDescriptor(tableName);
	
	//�W�[���a�ڪ��y�z
	HColumnDescriptor familyDesc = new HColumnDescriptor("spec");  
	
	//�W�[���a�ڪ��y�z
	HColumnDescriptor familyDesc2 = new HColumnDescriptor("price");
	
	//�N��a�ڪ��y�z�[����y�z����
	tableDesc.addFamily(familyDesc);
	tableDesc.addFamily(familyDesc2);
	
	//�ھڪ��y�z�A�إߪ��
	admin.createTable(tableDesc);
   }   
  } catch (Exception e) {
   System.out.println("doCreateTable error:" + e);	  
  }
 }
 
 //����R���@���ƪ��\��
 private void doDeleteColumn(HBaseConfiguration config) {
  try {
   //�P��@HBase��淾�q������	  
   HTable htable = new HTable(config, "MovieTable");
   
   //�]�q�R���\�઺����
   Delete del = new Delete("X-Men".getBytes());
   
   //�]�w�ҭn�R������a�ڻP�׹���
   del = del.deleteColumn("cast".getBytes(), "Directed".getBytes());
   
   //����R�����u�@
   htable.delete(del);
  } catch (Exception e) {
   System.out.println("doDeleteColumn error:" + e);	  
  }
 }
 
 //����R����a�ڪ��\��
 private void doDeleteFamily(HBaseConfiguration config) {
  try {
   //�P��@HBase��淾�q������	  
   HTable htable = new HTable(config, "MovieTable");
   
   //�]�q�R���\�઺����
   Delete del = new Delete("X-Men".getBytes());
   
   //�]�w�ҭn�R������a��
   del = del.deleteFamily("info".getBytes());
   
   //����R�����u�@
   htable.delete(del);
  } catch (Exception e) {
   System.out.println("doDeleteFamily error:" + e);	  
  }
 }
 
 
 //����R���@�C��ƪ��\��
 private void doDeleteRow(HBaseConfiguration config) {
  try {
   //�P��@HBase��淾�q������	  
   HTable htable = new HTable(config, "MovieTable");
   
   //�]�q�R���\�઺����
   Delete del = new Delete("Harry Potter 7".getBytes());
   
   //����R�����u�@
   htable.delete(del);
  } catch (Exception e) {
   System.out.println("doDeleteRow error:" + e);	  
  }
 }
 
 
 //����ק��ƪ��\��
 private void doUpdate(HBaseConfiguration config) {
  try {
   //�P��@HBase��淾�q������	  
   HTable htable = new HTable(config, "MovieTable");
   
   //�]�q�ק�\�઺����
   Put put = new Put("Harry Potter 7".getBytes());
   
   //�]�w�ҭn�ק諸��a�ڪ��e�m���A�׹����A�θ�Ƥ��e
   put.add("cast".getBytes(),"Starring".getBytes(), "Emma Watson".getBytes());
   
   //����ק諸�u�@
   htable.put(put);
  } catch (Exception e) {
   System.out.println("doPut error:" + e);	  
  }
 }
 
 
 //����s�W��ƪ��\��
 private void doPut(HBaseConfiguration config) {
  try {
   //�P��@HBase��淾�q������	  
   HTable htable = new HTable(config, "MovieTable");
   
   //�]�q�s�W�\�઺����
   Put put = new Put("Harry Potter 7".getBytes());
   
   //�]�w�ҭn�s�W����a�ڪ��e�m���A�׹����A�θ�Ƥ��e   
   put.add("info".getBytes(),"RunningTime".getBytes(), "2 hrs. 10 min.".getBytes());
   put.add("info".getBytes(),"ReleaseDate".getBytes(), "July 15th, 2011".getBytes());
   put.add("cast".getBytes(),"Starring".getBytes(), "Daniel Radcliffe".getBytes());
   put.add("cast".getBytes(),"Directed".getBytes(), "David Yates".getBytes());
   
   //����s�W���u�@
   htable.put(put);
  } catch (Exception e) {
   System.out.println("doPut error:" + e);	  
  }
 }
 
 //����d�ߪ��\��
 private void doScan(HBaseConfiguration config) {
  try {
   //�P��@HBase��淾�q������	  
   HTable htable = new HTable(config, "MovieTable");
   
   //�]�q�d�ߥ\�઺����
   Scan scan = new Scan();
   
   //�]�w�ҭn�d�ߪ���a�ڪ��e�m���A�Ψ�׹���
   scan.addColumn("info".getBytes(),"RunningTime".getBytes());
   scan.addColumn("info".getBytes(),"ReleaseDate".getBytes());
   scan.addColumn("cast".getBytes(),"Starring".getBytes());
   scan.addColumn("cast".getBytes(),"Directed".getBytes());
      
   //�]�w�d�ߪ��_�l�C�D��
   scan.setStartRow(Bytes.toBytes("X-Men"));
   
   //�]�w�d�ߪ��פ�C�D��
   scan.setStopRow(Bytes.toBytes("X-Men"));
   
   //���߬d�ߵ��G
   for(Result result : htable.getScanner(scan)) {
	//���X�C�@���d�ߵ��G	   
	String rTime = new String(result.getValue("info".getBytes(),"RunningTime".getBytes()));
	String rDate = new String(result.getValue("info".getBytes(),"ReleaseDate".getBytes()));
	String actor = new String(result.getValue("cast".getBytes(),"Starring".getBytes()));
	String director = new String(result.getValue("cast".getBytes(),"Directed".getBytes()));
	
	System.out.println("rTime:" + rTime + ",rDate:" + rDate + ",actor:" + actor + ",director:" + director);
   }
  } catch (Exception e) {
   System.out.println("doScan error:" + e);	  
  }
 }
 
 public static void main(String[] args) {  
  new HBaseTest();
 }
}
