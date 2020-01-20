
//基因演算法的程式
public class ga{
  
 int        counter,counter2;
 int        SumFitness;
 float      pCrossOver = (float)0.8;  //交配機率
 float      pMutation  = (float)0.001;//突變機率
 byte       x1,x2;
 //存放舊族群的染色體
 chromosome worm[] = new chromosome[4];
 //存放新族群的染色體 
 chromosome new_worm[] = new chromosome[4];

 public static void main(String argv[]){
  new ga();
 }

 public ga(){

 //產生四條染色體 (舊族群和新族群各四條)
 for (counter=0;counter<=3;counter++)
 {
   worm[counter] = new chromosome();
   new_worm[counter] = new chromosome();
 }

 //亂數產生四條染色體中的內容 (0到255之間)
 for (counter=0;counter<=3;counter++)
     worm[counter].individual =(byte)(java.lang.Math.random()*256);

 counter2 = 0;
 do{ 
   System.out.println("這是第"+counter2+"交配");
   counter2++; 
   setFit();      //取得每一個染色體的適應能力
   allFitness();  //取得全部染色體的適應能力
   setRate();     //取得輪盤法的百分比
   sortGa();      //將所有的染色體按其適應能力排列 (由大到小)
   pIndividual(); //顯示染色體中的內容

   selectGa();    //選擇要放到交配槽的染色體
   crossover();   //進行交配
   mutation();    //決定會不會突變

   replace();     //新物種取代舊物種

   System.out.println("");

  }while (worm[0].fitness < 290);//終止條件
 
 }//method ga end there


 //將染色體拆解為x1,x2兩個變數.並求除其適應能力
 //適應函數為 f(x1,x2)=x1^2 + 5 * x2
 public void setFit(){
   for (counter=0;counter<=3;counter++)
   { 
    x1 = (byte)((worm[counter].individual & 0xf0)>>4);
    x2 = (byte) (worm[counter].individual & 0x0f);
    //求每一個染色體的適應能力
    worm[counter].fitness = fit(x1,x2);
   }
 }

 //適應函數
 public int fit(byte x1,byte x2){
  int fitness;

  fitness = (x1 * x1) + (5 * x2);
  return fitness; 
 }//method fit end there

 //求全部染色體的適應能力
 public void allFitness(){ 
   SumFitness = 0;
   for (counter=0;counter<=3;counter++)
       SumFitness += worm[counter].fitness;
 }

 //求每一隻染色體用輪盤法篩選時的百分比
 public void setRate(){
  for (counter=0;counter<=3;counter++)
  {
   worm[counter].rate =(((float)worm[counter].fitness/(float)SumFitness)*100);
  }
 }

 //列印出每一隻染色體的內容
 public void pIndividual(){
  for (counter=0;counter<=3;counter++)
  {
   System.out.print(worm[counter].individual+"  ");
   System.out.print(worm[counter].fitness+"  ");
   System.out.print(worm[counter].rate+"  ");
   System.out.println();
  }
 }

 //將全部的基因按其適應能力的高低排列
 //本排序是使用泡泡排序法(bouble sort) 
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

 //依照輪盤法選擇要放到交配槽的染色體
 public void selectGa(){
  
  int range = (int)(java.lang.Math.random()*101);
  int limit1,limit2,limit3;
  
  //產生輪盤法的四個區間
  limit1 = (int)worm[0].rate;
  limit2 = limit1 + (int)worm[1].rate;
  limit3 = limit2 + (int)worm[2].rate;

  for (counter=0;counter<=3;counter++){
    //亂數選擇要進行交配的染色體
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

 //進行交配
 public void crossover(){
 
  float decide;     //決定要不要進行交配
  int   changP;     //決定交換點
  int   antiP;      //反向移動的交換點
  byte  tmp1,tmp2,tmp3; 

  //染色體0和1交配, 染色體2和3交配
  for (counter=0;counter<=3;counter+=2)
  { 
   decide = (float)java.lang.Math.random();
   //如果機率值小於交配機率時,則進行交配
   if (decide < pCrossOver)
   {
    //交換點用亂數產生
    changP = (int)((java.lang.Math.random()*8)+1);
    antiP  = 8 - changP;

    tmp1 = (byte)new_worm[counter].individual;
    tmp2 = (byte)new_worm[counter+1].individual;
    tmp1 = (byte)(tmp1>>changP);
    tmp1 = (byte)(tmp1<<changP);
    tmp2 = (byte)(tmp2<<antiP);
    tmp2 = (byte)(tmp2>>antiP);
    tmp1 = (byte)(tmp1 | tmp2); //tmp1現在存放交換後的值

    tmp2 = (byte)new_worm[counter].individual;
    tmp3 = (byte)new_worm[counter+1].individual;
    tmp3 = (byte)(tmp3>>changP);
    tmp3 = (byte)(tmp3<<changP);
    tmp2 = (byte)(tmp2<<antiP);
    tmp2 = (byte)(tmp2>>antiP);
    tmp3 = (byte)(tmp3 | tmp2); //tmp3現在存放交換後的值

    new_worm[counter].individual   = (byte)tmp1;
    new_worm[counter+1].individual = (byte)tmp3;
   }
  }
 }//method crossover end there

 //決定是否突變的函數
 public void mutation(){

  float decide;
  
  for (counter=0;counter<=3;counter++)
  {
   decide = (float)(java.lang.Math.random());

   //若小於突變機率,則進行突變, lsb進行反向
   if (decide < pMutation)
   {
    new_worm[counter].individual = (byte)(new_worm[counter].individual ^ 0x01);
   }
  }
 }//method mutation end there

 //本程式所採用的取代方式為全部取代
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
