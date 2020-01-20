
//��]�t��k���{��
public class ga{
  
 int        counter,counter2;
 int        SumFitness;
 float      pCrossOver = (float)0.8;  //��t���v
 float      pMutation  = (float)0.001;//���ܾ��v
 byte       x1,x2;
 //�s���±ڸs���V����
 chromosome worm[] = new chromosome[4];
 //�s��s�ڸs���V���� 
 chromosome new_worm[] = new chromosome[4];

 public static void main(String argv[]){
  new ga();
 }

 public ga(){

 //���ͥ|���V���� (�±ڸs�M�s�ڸs�U�|��)
 for (counter=0;counter<=3;counter++)
 {
   worm[counter] = new chromosome();
   new_worm[counter] = new chromosome();
 }

 //�üƲ��ͥ|���V���餤�����e (0��255����)
 for (counter=0;counter<=3;counter++)
     worm[counter].individual =(byte)(java.lang.Math.random()*256);

 counter2 = 0;
 do{ 
   System.out.println("�o�O��"+counter2+"��t");
   counter2++; 
   setFit();      //���o�C�@�ӬV���骺�A����O
   allFitness();  //���o�����V���骺�A����O
   setRate();     //���o���L�k���ʤ���
   sortGa();      //�N�Ҧ����V�������A����O�ƦC (�Ѥj��p)
   pIndividual(); //��ܬV���餤�����e

   selectGa();    //��ܭn����t�Ѫ��V����
   crossover();   //�i���t
   mutation();    //�M�w�|���|����

   replace();     //�s���ب��N�ª���

   System.out.println("");

  }while (worm[0].fitness < 290);//�פ����
 
 }//method ga end there


 //�N�V�����Ѭ�x1,x2����ܼ�.�èD����A����O
 //�A����Ƭ� f(x1,x2)=x1^2 + 5 * x2
 public void setFit(){
   for (counter=0;counter<=3;counter++)
   { 
    x1 = (byte)((worm[counter].individual & 0xf0)>>4);
    x2 = (byte) (worm[counter].individual & 0x0f);
    //�D�C�@�ӬV���骺�A����O
    worm[counter].fitness = fit(x1,x2);
   }
 }

 //�A�����
 public int fit(byte x1,byte x2){
  int fitness;

  fitness = (x1 * x1) + (5 * x2);
  return fitness; 
 }//method fit end there

 //�D�����V���骺�A����O
 public void allFitness(){ 
   SumFitness = 0;
   for (counter=0;counter<=3;counter++)
       SumFitness += worm[counter].fitness;
 }

 //�D�C�@���V����ν��L�k�z��ɪ��ʤ���
 public void setRate(){
  for (counter=0;counter<=3;counter++)
  {
   worm[counter].rate =(((float)worm[counter].fitness/(float)SumFitness)*100);
  }
 }

 //�C�L�X�C�@���V���骺���e
 public void pIndividual(){
  for (counter=0;counter<=3;counter++)
  {
   System.out.print(worm[counter].individual+"  ");
   System.out.print(worm[counter].fitness+"  ");
   System.out.print(worm[counter].rate+"  ");
   System.out.println();
  }
 }

 //�N��������]����A����O�����C�ƦC
 //���ƧǬO�ϥΪw�w�ƧǪk(bouble sort) 
 public void sortGa(){
  int   i,j;
  byte  tmp_individual;
  int   tmp_fitness;
  float tmp_rate;
  
   for (i=0;i<=3;i++)
     for (j=0;j<=3;j++)
      {
       if (worm[i].fitness >= worm[j].fitness)
        {
         tmp_individual = worm[i].individual;
         tmp_fitness    = worm[i].fitness;
         tmp_rate       = worm[i].rate;

         worm[i].individual = worm[j].individual;
         worm[i].fitness    = worm[j].fitness;
         worm[i].rate       = worm[j].rate;
          
         worm[j].individual = tmp_individual;
         worm[j].fitness    = tmp_fitness;
         worm[j].rate       = tmp_rate;         
        }
      }
 }//method sortGa end there

 //�̷ӽ��L�k��ܭn����t�Ѫ��V����
 public void selectGa(){
  
  int range = (int)(java.lang.Math.random()*101);
  int limit1,limit2,limit3;
  
  //���ͽ��L�k���|�Ӱ϶�
  limit1 = (int)worm[0].rate;
  limit2 = limit1 + (int)worm[1].rate;
  limit3 = limit2 + (int)worm[2].rate;

  for (counter=0;counter<=3;counter++){
    //�üƿ�ܭn�i���t���V����
    range = (int)(java.lang.Math.random()*101);
    if (range >= limit3)
    {
     new_worm[counter].individual = worm[3].individual;
    }
    else
    {
     if (range >= limit2)
     {
      new_worm[counter].individual = worm[2].individual;
     }
     else
     {
      if (range >= limit1)
      {
       new_worm[counter].individual = worm[1].individual;
      }
      else
      {
       new_worm[counter].individual = worm[0].individual;
      }
     }
    }
  }
 }//method selectGa end there 

 //�i���t
 public void crossover(){
 
  float decide;     //�M�w�n���n�i���t
  int   changP;     //�M�w�洫�I
  int   antiP;      //�ϦV���ʪ��洫�I
  byte  tmp1,tmp2,tmp3; 

  //�V����0�M1��t, �V����2�M3��t
  for (counter=0;counter<=3;counter+=2)
  { 
   decide = (float)java.lang.Math.random();
   //�p�G���v�Ȥp���t���v��,�h�i���t
   if (decide < pCrossOver)
   {
    //�洫�I�ζüƲ���
    changP = (int)((java.lang.Math.random()*8)+1);
    antiP  = 8 - changP;

    tmp1 = (byte)new_worm[counter].individual;
    tmp2 = (byte)new_worm[counter+1].individual;
    tmp1 = (byte)(tmp1>>changP);
    tmp1 = (byte)(tmp1<<changP);
    tmp2 = (byte)(tmp2<<antiP);
    tmp2 = (byte)(tmp2>>antiP);
    tmp1 = (byte)(tmp1 | tmp2); //tmp1�{�b�s��洫�᪺��

    tmp2 = (byte)new_worm[counter].individual;
    tmp3 = (byte)new_worm[counter+1].individual;
    tmp3 = (byte)(tmp3>>changP);
    tmp3 = (byte)(tmp3<<changP);
    tmp2 = (byte)(tmp2<<antiP);
    tmp2 = (byte)(tmp2>>antiP);
    tmp3 = (byte)(tmp3 | tmp2); //tmp3�{�b�s��洫�᪺��

    new_worm[counter].individual   = (byte)tmp1;
    new_worm[counter+1].individual = (byte)tmp3;
   }
  }
 }//method crossover end there

 //�M�w�O�_���ܪ����
 public void mutation(){

  float decide;
  
  for (counter=0;counter<=3;counter++)
  {
   decide = (float)(java.lang.Math.random());

   //�Y�p����ܾ��v,�h�i�����, lsb�i��ϦV
   if (decide < pMutation)
   {
    new_worm[counter].individual = (byte)(new_worm[counter].individual ^ 0x01);
   }
  }
 }//method mutation end there

 //���{���ұĥΪ����N�覡���������N
 public void replace(){

  worm[0].individual = new_worm[0].individual;
  worm[1].individual = new_worm[1].individual;
  worm[2].individual = new_worm[2].individual;
  worm[3].individual = new_worm[3].individual;

 }//method replace end there

}//class ga end there

class chromosome{

 public byte  individual;
 public int   fitness;
 public float rate;

}//class chromosome end there
