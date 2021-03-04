package com.arithmetic.Multithreading;

public enum ThreadConstant {
	//最大线程数
	MAX_SIZE(10), 
	//最小线程数
	MIN_SIZE(1), 
	//默认线程数
	Default_SIZE(5);
	private final int size;

	ThreadConstant(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
