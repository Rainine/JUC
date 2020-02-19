package com.atguigu.text;

public class TestAndCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
final CompareAndSwap cas=new CompareAndSwap();
for(int i=0;i<10;i++){
	new Thread(new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int expectedValue=cas.get();
			boolean b=cas.compareAnd(expectedValue, (int)(Math.random()*101));
		System.out.println(b);
		}
		
	}).start();
}
	}

}
class CompareAndSwap{
	private int value;
	public synchronized int get(){
		return value;
	}
	public synchronized int compareAndSwap(int expectedValue,int newValue){
		int oldValue=value;
		if(oldValue==expectedValue){
			this.value= newValue;
		}
		return oldValue;
	}
	public synchronized boolean compareAnd(int expectedValue,int newValue){
		return expectedValue==compareAndSwap(expectedValue,newValue);
	}
}