package com.arithmetic.Multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public  class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
	//工作列表
	public final LinkedList<Job> jobs=new LinkedList<Job>();
	//工作者列表
	public final List<Worker> workers=Collections.synchronizedList(new ArrayList<Worker>());
	//工作者线程的数量
	private int workNum=ThreadConstant.Default_SIZE.getSize();
	//线程编号生成
	private AtomicLong  threadNum=new AtomicLong();
	
	public  DefaultThreadPool() {
		initializeWorkers(ThreadConstant.Default_SIZE.getSize());
	}
    public  DefaultThreadPool(int num) {
    	workNum= num<ThreadConstant.MAX_SIZE.getSize()&& num>ThreadConstant.MIN_SIZE.getSize()?num:workNum;
    	initializeWorkers(workNum);
	}
	@Override
	public void execute(Job job) {
		job.run();
		
	}
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addWorker(int num) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeWorker(int num) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getJobSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void initializeWorkers(int num) {
		
	}


}
