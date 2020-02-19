package ������14K;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestABC {
	/* ��дһ������
	 * �����̵߳�ID�ֱ�ΪA,B,C,
	 * ÿ���̶߳�����ӡ�Լ���ID����Ļ�ϴ�ӡ10�飬
	 * Ҫ�����������밴˳����ʾ,
	 * ��ABCABCABC,���εݹ� --==========
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   AlternateDemo ad=new AlternateDemo();
   new Thread(new Runnable() {	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++){
			ad.loopA(i);
		}
	}
},"A").start();
   new Thread(new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<5;i++){
				ad.loopB(i);
			}
		}
	},"B").start();
   new Thread(new Runnable() {		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<5;i++){
				ad.loopC(i);
				System.out.println("---------------------------");
			}
		}
	},"C").start();
	}

}
class AlternateDemo{
	private int number=1;
	private Lock lock=new ReentrantLock();
	private Condition con1=lock.newCondition();
	private Condition con2=lock.newCondition();
	private Condition con3=lock.newCondition();
	public void loopA(int totalloop){
		lock.lock();
		try{
			if(number!=1){				
				con1.await();			 
			
			}	for(int i=0;i<5;i++){
					System.out.println(Thread.currentThread().getName()+":"+i+"\t"+totalloop);
				}
				number=2;
				con2.signal();
				
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}finally{
			lock.unlock();
		}
	}

public void loopB(int totalloop){
	lock.lock();
	try{
		if(number!=2){
			
				con2.await();
			} 
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName()+":"+i+"\t"+totalloop);
			}
			number=3;
			con3.signal();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	}finally{
		lock.unlock();
	}
}

public void loopC(int totalloop){
	lock.lock();
	try{
		if(number!=3){
			
				con3.await();
			}
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName()+":"+i+"\t"+totalloop);
			}
			number=1;
			con1.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	}finally{
		lock.unlock();
	}
}
}