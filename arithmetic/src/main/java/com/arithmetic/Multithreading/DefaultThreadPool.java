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
	
	//默认构造函数，线程池活跃线程默认
	public  DefaultThreadPool() {
		initializeWorkers(ThreadConstant.Default_SIZE.getSize());
	}
	//带参构造函数，线程池活跃线程指定
    public  DefaultThreadPool(int num) {
    	workNum= num<ThreadConstant.MAX_SIZE.getSize()&& num>ThreadConstant.MIN_SIZE.getSize()?num:workNum;
    	initializeWorkers(workNum);
	}
	//线程池执行，向工作池添加任务
	public void execute(Job job) {
		if(job!=null){
			synchronized(jobs){
				jobs.addLast(job);
				jobs.notify();
			}
		}
		
	}
	//关闭线程池，关闭线程池中所有线程
	public void shutDown() {
		workers.stream().forEach(a-> a.shutDown());
	}
	//向线程池添加工作线程
	public void addWorker(int num) {
		synchronized (jobs) {
	    num=num+this.workNum>ThreadConstant.MAX_SIZE.getSize()?(ThreadConstant.MAX_SIZE.getSize()-this.workNum):num;
	    initializeWorkers(num);
	    this.workNum+=num;
		}
		
	}
	//从线程池中移除工作线程
	public void removeWorker(int num) {
		synchronized (jobs) {
		    if(num>this.workNum){
		    	throw new IllegalArgumentException("beyond workNum");
		    }
		    int i=0;
		    while(i>num){
		    Worker worker=workers.get(i);
		    if(workers.remove(worker)){
		    	worker.shutDown();
		    };
		    i++;
		    }
		 this.workNum-=i;   
		}
		
	}
	//获取任务列表大小
	public int getJobSize() {
		return jobs.size();
	}
	//初始化线程池
	private void initializeWorkers(int num) {
	 for(int i=1;i<=num;i++){
		Worker worker=new Worker();
		workers.add(worker);
		Thread thread=new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
		thread.start();
	 }
	}
    //工作线程
	class Worker implements Runnable {

		//是否工作
		private volatile boolean runing =true;
		
		public void run() {
	    while(runing){
	    	Job job=null;
	    	synchronized (jobs) {
	    	while(jobs.isEmpty()){
	    		try {
					jobs.wait();
				} catch (InterruptedException e) {
					//响应外部线程中断
                  Thread.currentThread().interrupt();
					return;
				}
	    	}
	    	//获取工作列表中的第一个job
	    	job=jobs.removeFirst();
	    	if(job!=null){
	    		try{
	    		job.run();
	    		}catch(Exception ex){
	    		}
	    	}
			}
	    }
			
		}
		//关闭线程
		public void shutDown(){
			runing =false;	
		}

	}


}
