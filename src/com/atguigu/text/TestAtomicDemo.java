package com.atguigu.text;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicDemo {
///ԭ�ӱ�����jdk1.5֮��Java.util.concurrent.atomic�����ṩ�˳��õ�ԭ�ӱ���
//1.volatile��֤���ڴ�Ŀɼ���
	//2.CAS(Compare-And-Swap)�㷨��֤����ԭ����
	    //CAS�㷨��Ӳ�����ڲ��������������ݵ�֧��
	//CAS����������������  �ڴ�ֵV Ԥ��ֵA ����ֵ B ��V==Aʱ��V=B
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
	private AtomicInteger serialNumber=new AtomicInteger();//�൱�ڰ�װ��
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
