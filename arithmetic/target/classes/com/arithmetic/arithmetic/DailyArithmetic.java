package com.arithmetic.arithmetic;

import java.util.Arrays;

public class DailyArithmetic {
	//循环次数
	public static 		int count = 0;

	/**
	 * 双赛道，每次只能容纳两匹马比赛，现有12匹马，采用最优算法获取前三名
	 * 冒泡排序：时间复杂度O(n^2)
	 */
	public static void maopaoSort(){
		int[] a={96,93,30,60,19,95,19,83,24,5,88,100};
		int	b;
		int count=0;
		for(int i=0;i<a.length;i++){
			for(int i1=0;i1<a.length;i1++){
			if(a[i]<a[i1]){
				b=a[i1];
				a[i1]=a[i];
				a[i]=b;
		
			}
			count++;
			}
		}
		System.out.println("冒泡交换次数"+count);
		System.out.println(Arrays.toString(a)); 
		
	}
	//快速排序
	public static int[] fastSort(int[] a,int start,int end){
		//int[] a={96,93,30,60,19,95,19,83,24,5,88,100};
		int i=start;
		int j=end;
		int pointValue=a[i];
		while(i<j){
			
	     while(i<j&&a[j]>pointValue){
	    	 count++;
	    	 j--;
	     }
	     while(i<j&&a[i]<pointValue){
	    	 i++;
	    	 count++;
	     }
	     count++;
	     if(a[i]==a[j]&&i<j){
	    	 i++;
	     }else{
	    	 int temp=a[i];
	    	 a[i]=a[j];
	    	 a[j]=temp;
	     }
	    
		}
		 if(i-1>start) a=fastSort(a,start,i-1);
	     if(j+1<end) a=fastSort(a,j+1,end);
		//System.out.println("快速排序循环次数"+count);
		//System.out.println(Arrays.toString(a));
        return a;

		/*for(int i=0;i<a.length;i++){
			for(int i1=0;i1<a.length;i1++){
				if(a[i1]<a[i]&&i1>i){
				int x=a[i];
				a[i]=a[i1];
				a[i1]=x;
				count++;
				}
				if(a[i1]>a[i]&&i1<i){
				int x=a[i];
				a[i]=a[i1];
				a[i1]=x;
				count++;
				}
			}
			
		}*/
 

	}
    public static void main(String args[]){
     new DailyArithmetic().maopaoSort();
     System.out.println("快速排序循环次数"+count);
   System.out.println(Arrays.toString(new DailyArithmetic().fastSort(new int[]{96,93,30,60,19,95,19,83,24,5,88,100},0,11)));
    }
}
