package com.arithmetic.arithmetic;

import java.util.Arrays;

public class DailyArithmetic {
	/**
	 * 双赛道，每次只能容纳两匹马比赛，现有12匹马，采用最优算法获取前三名
	 * 时间复杂度O(n^2)
	 */
	public static void horseRacing(){
		int[] a={96,93,30,60,19,95,19,83,24,5,88,100};
		for(int i=0;i<a.length;i++){
			for(int i1=0;i1<a.length;i1++){
			if(a[i]<a[i1]){
			int	b=a[i1];
				a[i1]=a[i];
				a[i]=b;
			}
			}
		}
		System.out.print(Arrays.toString(a)); 
		
	}
    public static void main(String args[]){
    	DailyArithmetic.horseRacing();
    }
}
