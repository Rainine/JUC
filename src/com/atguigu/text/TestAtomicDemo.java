package com.atguigu.text;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicDemo {
///原子变量：jdk1.5之后Java.util.concurrent.atomic包下提供了常用的原子变量
//1.volatile保证了内存的可见性
	//2.CAS(Compare-And-Swap)算法保证数据原子性
	    //CAS算法是硬件对于并发操作共享数据的支持
	//CAS包含了三个操作符  内存值V 预估值A 更新值 B 当V==A时，V=B
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 AtomicDemo arr=new AtomicDemo();
 for(int i=0;i<10;i++){
	new Thread(arr).start();
 }
	}

}
class AtomicDemo implements Runnable{
//private int serialNumber=0;
	private AtomicInteger serialNumber=new AtomicInteger();//相当于包装类
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(":"+getSerialNumber());
	}
	public int getSerialNumber(){
		return serialNumber.getAndIncrement();
	}
}
