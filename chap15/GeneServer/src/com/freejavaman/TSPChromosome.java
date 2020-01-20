package com.freejavaman;

import java.util.Enumeration;
import java.util.Hashtable;

//�@�ӬV����A�Y�N��@�ո�
public class TSPChromosome {
	 
 //�Ȥᶡ�Z����Ӫ�A�@10�ӫȤ�(A ~ J)
 private static int[][] distance = {
  {0,20,25,30,60,10,15,22,6,16},
  {20,0,2,10,50,5,10,20,8,20},
  {25,2,0,3,40,7,13,25,11,19},
  {30,10,3,0,55,9,11,19,7,18},
  {60,50,40,55,0,12,15,16,10,21},
  {10,5,7,9,12,0,20,7,13,20},
  {15,10,13,11,15,20,0,30,5,17},
  {22,20,25,19,16,7,30,0,6,8},
  {6,8,11,7,10,13,5,6,0,13},
  {16,20,19,18,21,20,17,8,13,0}};
		 
 private char[] gene;
 
 public TSPChromosome(){}
	 
 public TSPChromosome(Hashtable cityHash){  
  //�N�ϥΪ̩ҿ�ܪ������A��s��}�C����
  int inx = 0;	 
  gene = new char[cityHash.size()];  
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
 }
	 
 //������ܹB��A�N�}�C�����N���]�i��洫
 public void mutationACT() {
  //Math.random()�^�Ǥj�󵥩�0, ��p��1���ƭ�   	  
  int sInx = (int)(Math.random() * gene.length);
  int eInx = (int)(Math.random() * gene.length);
		   
  //�N�üƩҫ��V�����޸�Ƥ��e�A�i��洫���u�@
  char tmp = gene[sInx];
  gene[sInx] = gene[eInx];
  gene[eInx] = tmp;	 
 }
	 
 //�p�⦹�V���骺�A����
 public int getFitnessValue() {
  int fitnessValue = 0;
  for (int i = 0; i < gene.length; i++) {
   //�g�Ѭd��A���o�ثe���ޭȫ����P�U�@�ӫ����������Z��   
   if (i == (gene.length - 1)) {
	 //�}�C���̫�@�ӫ�����, ���Ӹ�}�C�Ĥ@�ӫ������
	 int rowInx = ((int)gene[i]) - 65;
	 int colInx = ((int)gene[0]) - 65;
	 fitnessValue += distance[rowInx][colInx];		 
   } else {
	 //���ޭȫ��V�ثe����, �P�U�@�ӫ��� 
	 //�r��A��ASCII�Ȭ�65, �]��, �K���V��0�Ӥ���   
	 int rowInx = ((int)gene[i]) - 65;
	 int colInx = ((int)gene[i + 1]) - 65;
	 fitnessValue += distance[rowInx][colInx];
   }  
  }
  return fitnessValue;
 }
	 
 //�p��ҶǤJ���V���骺�A����
 public synchronized static int caculateFitnessValue(String geneStr) {
  int fitnessValue = 0;
  char[] localGene = geneStr.toCharArray();
	  
  for (int i = 0; i < localGene.length; i++) {
   //�g�Ѭd��A���o�ثe���ޭȫ����P�U�@�ӫ����������Z��   
   if (i == (localGene.length - 1)) {
	 //�}�C���̫�@�ӫ�����, ���Ӹ�}�C�Ĥ@�ӫ������
	 int rowInx = ((int)localGene[i]) - 65;
	 int colInx = ((int)localGene[0]) - 65;
	 fitnessValue += distance[rowInx][colInx];		 
   } else {
	 //���ޭȫ��V�ثe����, �P�U�@�ӫ��� 
	 //�r��A��ASCII�Ȭ�65, �]��, �K���V��0�Ӥ���   
	 int rowInx = ((int)localGene[i]) - 65;
	 int colInx = ((int)localGene[i + 1]) - 65;
	 fitnessValue += distance[rowInx][colInx];
   }  
  }
  return fitnessValue;
 }
	 
 //���o�V�������
 public int getChromosomeLength() {
  if (gene != null)	 
   return gene.length;
  else
   return 0;	  
 }
 
 //�ھڰ�]��ơA�]�w��]�����N�X
 public void setGeneArray(String geneStr) {
  gene = geneStr.toCharArray(); 
 }
	 
 //�C�L�X�V���骺���e
 public String getGeneString() {
  StringBuffer sBuf = new StringBuffer("");	 
  for (int i = 0; i < gene.length; i++) {
   sBuf.append(gene[i]);
  }
  return sBuf.toString();
 }
}
