package com.freejavaman;
import java.util.*;

//�ΨӳB�z�Ȧ���P�����D
public class TSPService {
 
 //�@�ӱڸs���A�]�t6�ӬV����
 private TSPChromosome worm[] = new TSPChromosome[6];
 
 //�x�s���L�k���A�C�@�ӬV����Ҧ�����Ұ϶�
 private double rwheelRange[] = new double[6];
 
 //��m���N�V���骺��t��
 private TSPChromosome matingPool[] = new TSPChromosome[6];
 
 //��t���v(probability of performing crossover�FPC)
 private double pc = 0.8;
 
 //���ܾ��v(probability of mutation�FPM)
 private double pm  = (double)0.001;
 
 private Vector chromosomeList;
 
 //�غc�̨�ơA�ǤJ�ҭn�Ȧ檺�������V������
 public TSPService(Vector chromosomeList) {
  this.chromosomeList = chromosomeList;
 }
 
 //�q�ڸs���A�D��A���ȳ̤p���զX�A���̨θ� 
 public String getBestSolution() {
  
  int minValue = 100000000;
  String geneStr = "";
  
  //���ߩҦ��V����
  for (int i = 0; i < worm.length; i++) {
   //�P�_�O�_�����p���A����	  
   if (worm[i].getFitnessValue() < minValue) {
	minValue = worm[i].getFitnessValue();
	geneStr = worm[i].getGeneString();
   }
  }
  return geneStr;
 }
 
 public void start() {	 
  //�إߪ�N�ڸs
  for (int i = 0; i < worm.length; i++) {
   worm[i] = new TSPChromosome();
   worm[i].setGeneArray((String)chromosomeList.elementAt(i));
  }
  
  //�إߥ�t�Ѥ�������
  for (int i = 0; i < matingPool.length; i++) {
   matingPool[i] = new TSPChromosome();
  }
  
  int roop = 0;
  int roopMax = 10;
  
  do {
   System.out.println("��" + (roop + 1) + "���t��");
	  
   //�p����L�϶�
   setRange();  
   System.out.println("�������L�]�w");
   
   //��ܽ��L�϶�
   /*for (int i = 0; i < rwheelRange.length; i++) {
   System.out.println("rwheelRange[" + i + "]:" + rwheelRange[i]);
   }*/
  
   //�ھڽ��L�k�A��ܥ��N�A�øm���t�Ѥ���
   doSelect();   
   System.out.println("�������N���");
   
   //��ܥ�t�Ѥ������V����
   /*for (int i = 0; i < matingPool.length; i++) {
    System.out.println("matingPool" + i + ":" + matingPool[i].getGeneString() + ",fitnessValue:" + matingPool[i].getFitnessValue());
   }*/
  
   //�i��V�����t
   doCrossover();
   System.out.println("�����V�����t");
   
   //�i����ܹB��
   doMutation();
   System.out.println("�������ܹB��");
  
   //�i��l�N���N���N���u�@
   doReplace();
   System.out.println("�������N");
   
   /*//��ܩҦ��V���骺�A����
   for (int i = 0; i < worm.length; i++) {
    System.out.println("roop:" + (roop + 1) + ",worm" + i + ":" + worm[i].getGeneString() + ",fitnessValue:" + worm[i].getFitnessValue());
   }*/
   
   //�i�J�U�@���i�ƹL�{
   roop++;
  } while (roop < roopMax); 
 }
 
 //�Ĩ���s���N������
 private void doReplace() {
  for (int i = 0; i < worm.length; i++) {
   worm[i].setGeneArray(matingPool[i].getGeneString());
  }	 
 }
 
 //�i����ܹB��
 private void doMutation() {
  //�ھڬV����ơA�i����ܹB��
  for (int i = 0; i < matingPool.length; i++) {
   //���X��t�Ѥ����V����	 
   TSPChromosome worm = matingPool[i];
	  
   //���ͤj�󵥩�0, �B�p��1���ü�
   double decide = java.lang.Math.random();
   
   //�Y�üƤp����ܾ��v�A�~�ݶi����ܹB��
   if (decide <= pm) {
	matingPool[i].mutationACT();   
   }
  }
 }
 
 //�i��V�����t
 private void doCrossover() {
  //�b��t�Ѥ����V����A���i���t
  //�]����6�ӬV����A�]���A��t�B��n����3��
  for (int i = 0; i < 6; i+=2) {
   TSPChromosome worm1 = matingPool[i];  
   TSPChromosome worm2 = matingPool[i + 1];
   
   //���o�V���骺��Ƥ��e
   String wormStr1 = worm1.getGeneString();  
   String wormStr2 = worm2.getGeneString();
   
   //���ͤj�󵥩�0, �B�p��1���ü�
   double decide = java.lang.Math.random();
   
   //�Y�üƤp���t���v�A�B�V���餺�e�]���P�ɡA�~�ݶi���t
   if (decide <= pc && !wormStr1.equals(wormStr2)) {
    //if (true && !wormStr1.equals(wormStr2)) {
	
	int point1 = 0;
	int point2 = 0;
		
	do {   
	 //���ͨ�ӥ�t�I
	 point1 =  (int)(java.lang.Math.random() * worm1.getChromosomeLength());
	 point2 =  (int)(java.lang.Math.random() * worm1.getChromosomeLength());
	
	 //point1������t�I�Apoint2���k��t�I
	 //�Ypoint2�j��point1�A�h��m�i��洫	
	 if (point2 < point1) {
	  int tmp = point1;
	  point1 = point2;
	  point2 = tmp;	
	 }
	 //�Y���k��t�I�A�P�ɫ��V��ƪ��̨�ݡA�]�S����t���N�q
	} while(point1 == 0 && point2 == worm1.getChromosomeLength() - 1); 
	
	//point1 = 0;
	//point2 = 3;
	
	 //System.out.println("point1:" + point1);
	 //System.out.println("point2:" + point2);		
	 
	 //���o�V���骺��Ƥ��e�A�åB�ھڥ洫�I�A���Φ�3�Ӥl�Ϭq
	 //�ĤG�ӰϬq�A�Y���n�i���ƥ洫���Ϭq
	 String wormStr1_sec1 = wormStr1.substring(0, point1);
	 String wormStr1_sec2 = wormStr1.substring(point1, point2 + 1);
	 String wormStr1_sec3 = wormStr1.substring(point2 + 1, wormStr1.length());
	 
	 String wormStr2_sec1 = wormStr2.substring(0, point1);
	 String wormStr2_sec2 = wormStr2.substring(point1, point2 + 1);
	 String wormStr2_sec3 = wormStr2.substring(point2 + 1, wormStr2.length());
	 
	 //System.out.println("wormStr1 split:" + wormStr1_sec1 + " " + wormStr1_sec2 + " " + wormStr1_sec3);
	 //System.out.println("wormStr2 split:" + wormStr2_sec1 + " " + wormStr2_sec2 + " " + wormStr2_sec3);
	 
	 //�P�_�Ĥ@�ӬV����s���ĤG�ӰϬq(�ĤG�ӬV���骺�ĤG�Ϭq)
     //�Ҧ���Ƥ��e�P�¸�Ƥ��������Y
	 for (int secInx = 0; secInx < wormStr2_sec2.length(); secInx++) {
	  //�s�Ϭq���r��	 
	  String newChar= "" + wormStr2_sec2.charAt(secInx);
	  
	  //�°Ϭq���r��
	  String oldChar= "" + wormStr1_sec2.charAt(secInx);
	  
	  //System.out.println("secInx:" + secInx + ", newChar:" + newChar + ",oldChar:" + oldChar);
	  
	  //�P�_�s�r���O�_�s�b��䥦��Ϭq����
	  if (wormStr1_sec1.indexOf(newChar) != -1 ||
		  wormStr1_sec3.indexOf(newChar) != -1) {
	   //�s�r���s���Щ�䥦��Ϭq�A�����n�簣���ж�
	   //System.out.println("�s�r���s���Щ�䥦��Ϭq,newChar:" + newChar);
		  
	   //�P�_�¦r���O�_�s�b��s�Ϭq����
	   int oldInx = wormStr2_sec2.indexOf(oldChar);
	   if (oldInx != -1) {
		//�¦r���s�b��s�Ϭq����
		//�����n���A�����N�r��
		//System.out.println("�¦r���s�b��s�Ϭq,oldChar:" + oldChar + ",�s�Ϭq:" + wormStr2_sec2);
		   
	    //oldInx: �¦r���b�s�Ϭq������m		
		do {
		 //���o��M�°Ϭq��, �ۦP��m���r��
		 oldChar = "" + wormStr1_sec2.charAt(oldInx);
		
		 //�P�_�Ӧr���O�_�s�b��s�Ϭq
		 oldInx = wormStr2_sec2.indexOf(oldChar);
		 
		 //System.out.println("����N�r��:" + oldChar + ",oldInx:" + oldInx);
		 
		} while (oldInx != -1); 
		
		//�N�¦r�����N�䥦�Ϭq�������ж�
		wormStr1_sec1 = wormStr1_sec1.replace(newChar, oldChar);
		wormStr1_sec3 = wormStr1_sec3.replace(newChar, oldChar);		
	   } else {
		//�¦r�����s�b��s�Ϭq����
		//System.out.println("�¦r�����s�b��s�Ϭq,oldChar:" + oldChar);
		//�N�¦r�����N�䥦�Ϭq�������ж�
		wormStr1_sec1 = wormStr1_sec1.replace(newChar, oldChar);
		wormStr1_sec3 = wormStr1_sec3.replace(newChar, oldChar);
	   }  
	  } else {
	   //�s�r�������Щ�䥦��Ϭq�A�]���A�i�H�������p
	   //System.out.println("�s�r�������Щ�䥦��Ϭq");
	  }
	 }
	 
	 //�P�_�Ĥ@�ӬV����s���ĤG�ӰϬq(�ĤG�ӬV���骺�ĤG�Ϭq)
     //�Ҧ���Ƥ��e�P�¸�Ƥ��������Y
	 for (int secInx = 0; secInx < wormStr1_sec2.length(); secInx++) {
	  //�s�Ϭq���r��	 
	  String newChar= "" + wormStr1_sec2.charAt(secInx);
	  
	  //�°Ϭq���r��
	  String oldChar= "" + wormStr2_sec2.charAt(secInx);
	  
	  //System.out.println("secInx:" + secInx + ", newChar:" + newChar + ",oldChar:" + oldChar);
	  
	  //�P�_�s�r���O�_�s�b��䥦��Ϭq����
	  if (wormStr2_sec1.indexOf(newChar) != -1 ||
		  wormStr2_sec3.indexOf(newChar) != -1) {
	   //�s�r���s���Щ�䥦��Ϭq�A�����n�簣���ж�
	   //System.out.println("�s�r���s���Щ�䥦��Ϭq,newChar:" + newChar);
		  
	   //�P�_�¦r���O�_�s�b��s�Ϭq����
	   int oldInx = wormStr1_sec2.indexOf(oldChar);
	   if (oldInx != -1) {
		//�¦r���s�b��s�Ϭq����
		//�����n���A�����N�r��
		//System.out.println("�¦r���s�b��s�Ϭq,oldChar:" + oldChar);
		   
	    //oldInx: �¦r���b�s�Ϭq������m		
		do {
		 //���o��M�°Ϭq��, �ۦP��m���r��
		 oldChar = "" + wormStr2_sec2.charAt(oldInx);
		
		 //�P�_�Ӧr���O�_�s�b��s�Ϭq
		 oldInx = wormStr1_sec2.indexOf(oldChar);
		} while (oldInx != -1); 
		
		//�N�¦r�����N�䥦�Ϭq�������ж�
		wormStr2_sec1 = wormStr2_sec1.replace(newChar, oldChar);
		wormStr2_sec3 = wormStr2_sec3.replace(newChar, oldChar);		
	   } else {
		//�¦r�����s�b��s�Ϭq����
		//System.out.println("�¦r�����s�b��s�Ϭq,oldChar:" + oldChar);
		//�N�¦r�����N�䥦�Ϭq�������ж�
		wormStr2_sec1 = wormStr2_sec1.replace(newChar, oldChar);
		wormStr2_sec3 = wormStr2_sec3.replace(newChar, oldChar);
	   }  
	  } else {
	   //�s�r�������Щ�䥦��Ϭq�A�]���A�i�H�������p
	   //System.out.println("�s�r�������Щ�䥦��Ϭq");
	  }
	 }
	 
	 wormStr1 = wormStr1_sec1 + wormStr2_sec2 + wormStr1_sec3;
	 wormStr2 = wormStr2_sec1 + wormStr1_sec2 + wormStr2_sec3;
	 
	 //System.out.println("new wormStr1:" + wormStr1);
	 //System.out.println("new wormStr2:" + wormStr2);
	 
	 worm1.setGeneArray(wormStr1);  
	 worm2.setGeneArray(wormStr2);
   }   
  }
 } 
 
 //�ھھA���ȡA�p����L�k���A�C�@�q���϶����
 private void setRange() {
  //�[�`�A����, ���D���L�k������
  int totalFitnessValue = 0;
  for (int i = 0; i < worm.length; i++) {
   totalFitnessValue += worm[i].getFitnessValue();
  }
	  
  //�p��b���L�k���A�C�@�ӬV����Ҧ������
  //�ѩ�bTSP���D���A�A���ȶV�C�V�n�A�]���A�b�p����L��ҮɡA��������100�h��A�H�D����  
  double rangeSubsum = 0.0;
  for (int i = 0; i < worm.length; i++) {
   //�Ȧs���ध�᪺��
   rwheelRange[i] = ((double)100 - ((double)worm[i].getFitnessValue()/totalFitnessValue) * 100);
   //�p�p�`���
   rangeSubsum += rwheelRange[i];
  }
	  
  //�N�i����L���e�֥[�A�H�Φ��϶�
  double rangeSum = 0.0;
  for (int i = 0; i < rwheelRange.length; i++) {
   rangeSum += (rwheelRange[i]/rangeSubsum) * 100;	
   rwheelRange[i] = rangeSum;
  } 
 }
 
 //�ھڶüƻP���L��ҡA��ܥ��N�A�øm���t��
 private void doSelect() {  
  //�`�@�n��X�ƶq�۷��V����	 
  for (int i = 0; i < worm.length; i++){
   //����0 ~ 100�������üơA�H�M�w�϶�	  
   int randomForSelect = (int)(java.lang.Math.random() * 101);
   
   //�P�_���@�ӬV�������Ӹm���t�Ѥ���(�ƻs��Ƥ��e)
   if ((randomForSelect >= 0) && randomForSelect < rwheelRange[0]) {
	//�϶��@
	matingPool[i].setGeneArray(worm[0].getGeneString());   
   } else if ((randomForSelect >= rwheelRange[0]) && randomForSelect < rwheelRange[1]) {
	//�϶��G
	matingPool[i].setGeneArray(worm[1].getGeneString());
   } else if ((randomForSelect >= rwheelRange[1]) && randomForSelect < rwheelRange[2]) {
	//�϶��T
	matingPool[i].setGeneArray(worm[2].getGeneString());
   } else if ((randomForSelect >= rwheelRange[2]) && randomForSelect < rwheelRange[3]) {
	//�϶��|
	matingPool[i].setGeneArray(worm[3].getGeneString());
   } else if ((randomForSelect >= rwheelRange[3]) && randomForSelect < rwheelRange[4]) {
	//�϶���
	matingPool[i].setGeneArray(worm[4].getGeneString());
   } else if ((randomForSelect >= rwheelRange[4]) && randomForSelect < rwheelRange[5]) {
	//�϶���   
	matingPool[i].setGeneArray(worm[5].getGeneString());
   }   
  }  
 }
}


