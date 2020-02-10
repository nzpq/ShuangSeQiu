package com.xxx.shuangseqiu;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 规则
 * 双色球分为蓝球号码区和红球号码区，红球号码区范围为01-33，蓝球号码区范围为：01-16，
 * 双色球每期从33个红球中开出6个号码，从16个蓝球中开出1个号码作为中奖号码
 * 玩家每期分别选择蓝球和红球号码作为竞猜
 *
 * @author 鹏
 *
 */
public class ShuangSeQiu {

	public static void main(String[] args) {

		//定义相关变量
		int[] userReadBall = new int[6];//用户选择的红球号码
		int userBlueBall = 0;//用户选择的蓝球号码
		int[] sysReadBall = new int[6];//系统随机生成的红球号码，本期的红球中奖号码
		int sysBlueBall = 0;//系统随机生成的蓝球号码，本期的蓝球中奖号码
		int readCount = 0;//记录用户选择正确的红球数
		int blueCount = 0;//记录用户选择正确的蓝球数

		int[] readBall = new int[33];//红球号码池
		//为红球号码池赋值
		for(int i=0;i<readBall.length;i++) {
			readBall[i] = i+1;
		}
		Random r = new Random();

		//编写主界面
		Scanner in = new Scanner(System.in);
		System.out.println("**********欢迎使用双色球**********");
		System.out.println("\t1、机选");
		System.out.println("\t2、手选");
		System.out.print("请输入您的选择：");

		boolean flag = true;
		while(flag) {

			String choice = in.nextLine();
			switch(choice) {
				case "1":
					//机选
					computerselection(readBall,userReadBall);//机选红球
					userBlueBall = r.nextInt(16)+1;
					flag = false;
					break;

				case "2":
					//手选
					System.out.print("请您选择六个红球号码（1-33）：");
					for(int i=0;i<userReadBall.length;i++) {
						userReadBall[i] = in.nextInt();
					}
					System.out.print("请您选择一个蓝球号码（1-16）：");
					userBlueBall = in.nextInt();

					flag = false;
					break;

				default:
					System.out.println("输入错误请重新选择");
			}
		}

		//生成中奖号码
		computerselection(readBall,sysReadBall);//生成红球中奖号码
		sysBlueBall = r.nextInt(16)+1;//生成蓝球中奖号码

		//排序
		//Arrays.sort(userReadBall);//对选择的号码进行排序
		//Arrays.sort(sysReadBall);//对系统产生的号码进行排序
		maoPao(userReadBall);
		maoPao(sysReadBall);

		//比较结果
		//比较红球结果
		for(int i=0;i<sysReadBall.length;i++) {
			for(int j=0;j<userReadBall.length;j++) {
				if(userReadBall[j] == sysReadBall[i]) {
					readCount++;
				}
			}
		}
		//比较蓝球结果
		if(sysBlueBall == userBlueBall) {
			blueCount++;
		}
		System.out.println("=====================================");
		System.out.println("=************开奖啦*****************=");
		System.out.println("=====================================");
		//验证是否中奖
		if(blueCount == 0 && readCount<=3) {
			System.out.println("很遗憾，您未中奖！");
		}else if(blueCount == 1 && readCount<3) {
			System.out.println("恭喜！您中了六等奖 奖金五元");
		}else if((blueCount == 1 && readCount ==3) || (blueCount == 0 && readCount == 4)){
			System.out.println("恭喜！您中了五等奖 奖金十元");
		}else if((blueCount == 1 && readCount == 4) || (blueCount == 0 && readCount == 5)){
			System.out.println("恭喜！您中了四等奖 奖金一百元");
		}else if(blueCount == 1 && readCount == 5){
			System.out.println("恭喜！您中了三等奖 奖金三千元");
		}else if(blueCount == 0 && readCount == 6){
			System.out.println("恭喜！您中了二等奖 奖金一万元");
		}else if(blueCount ==1 && readCount == 6){
			System.out.println("恭喜！您中了一等奖 奖金一百万");
		}else{
			System.out.println("对不起！系统异常！");
		}

		//输出号码
		System.out.print("本期中将红球号码为：");
		System.out.println(Arrays.toString(sysReadBall));
		System.out.println("本期中奖蓝球号码为："+sysBlueBall);
		System.out.print("本期您选择的红球号码为：");
		System.out.println(Arrays.toString(userReadBall));
		System.out.println("本期您选择的蓝球号码为："+userBlueBall);

	}


	//用于在指定数列中，随机生成多个不重复数的算法
	public static void computerselection(int[] readBall,int[] userReadBall) {

		Random r= new Random();
		int index = -1;
		for(int i=0;i<userReadBall.length;i++) {

			index = r.nextInt(readBall.length-i);
			userReadBall[i] = readBall[index];

			int temp = readBall[index];
			readBall[index] = readBall[readBall.length-1-i];
			readBall[readBall.length-1-i] = temp;

		}
	}

	//冒泡法排序
	public static int[] maoPao(int[] nums) {
		//外循环控制循环轮数=数组长度-1
		for(int i=0;i<nums.length;i++) {
			//内循环控制每轮比较的次数=数组长度-1-i
			for(int j=0;j<nums.length-1-i;j++) {
				if(nums[j] > nums[j+1]) {
					int temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
				}
			}
		}

		return nums;
	}

}
