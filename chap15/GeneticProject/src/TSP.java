import java.util.*;

//�ΨӳB�z�Ȧ���P�����D
public class TSP {

 //�x�s�ҭn�Ȧ檺�����N�X
 private Hashtable cityHash;
 
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
 
 //�غc�̨�ơA�ǤJ�ҭn�Ȧ檺����
 public TSP(char[] cities) {
  //�N�}�C��s��Hashtable��Ƶ��c����	 
  cityHash = new Hashtable();
  if (cities != null) {
   for (int i = 0; i < cities.length; i++) {
	if (cities[i] != -1 && !cityHash.containsKey(cities[i])) {
	 cityHash.put(cities[i], cities[i]);
	}//if (cities[i] != null && !cityHash.containsKey(cities[i])) ends
   }//for (int i = 0; i < cities.length; i++) ends
  }//if (cities != null) ends
  
  if (cityHash.size() == 0)
   return;
  
  //�إߪ�N�ڸs
  for (int i = 0; i < worm.length; i++) {
   worm[i] = new TSPChromosome(cityHash);
   //System.out.println("worm" + i + ":" + worm[i].getGeneString() + ",fitnessValue:" + worm[i].getFitnessValue());
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
   
   //��ܩҦ��V���骺�A����
   for (int i = 0; i < worm.length; i++) {
    System.out.println("roop:" + (roop + 1) + ",worm" + i + ":" + worm[i].getGeneString() + ",fitnessValue:" + worm[i].getFitnessValue());
   }
   
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
	 point1 =  (int)(java.lang.Math.random() * cityHash.size());
	 point2 =  (int)(java.lang.Math.random() * cityHash.size());
	
	 //point1������t�I�Apoint2���k��t�I
	 //�Ypoint2�j��point1�A�h��m�i��洫	
	 if (point2 < point1) {
	  int tmp = point1;
	  point1 = point2;
	  point2 = tmp;	
	 }
	 //�Y���k��t�I�A�P�ɫ��V��ƪ��̨�ݡA�]�S����t���N�q
	} while(point1 == 0 && point2 == cityHash.size() - 1); 
	
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
 
 //�D���
 public static void main(String[] args) {
  //�ǤJ�ҭn�i��TSP���D�������N��
  char[] cities = {'A', 'B', 'D', 'E', 'G', 'I'};	 
  new TSP(cities);
 }
}

//�@�ӬV����A�Y�N��@�ո�
class TSPChromosome {
 
 //�Ȥᶡ�Z����Ӫ�A�@10�ӫȤ�(A ~ J)
 private int[][] distance = {
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
