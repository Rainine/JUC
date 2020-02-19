package ��������������ٻ��Ѱ���;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumer {
//�����ߺ������߰���
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  Clerk clerk=new Clerk();
  Productor p=new Productor(clerk);
  Cousumer c=new Cousumer(clerk);
  new Thread(p,"������A").start();
  new Thread(c,"������B").start();
  new Thread(p,"������C").start();
  new Thread(c,"������D").start();
  
	}

}
//��Ա
class Clerk{
	private int product=0;
	private Lock lock=new ReentrantLock();
	private Condition con=lock.newCondition();
	//����
	public  void get(){
		lock.lock();
		try{
			while(product>=1){//Ϊ�˱�����ٻ���Ӧ��ʹ��whileһֱѭ��
				System.out.println("��Ʒ�Ѿ�����");
			     try {
					//this.wait();��await()�ȼ�
			    	 con.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//else{
				System.out.println(Thread.currentThread().getName()+":"+ ++product);
			   //  this.notifyAll();��signalAll()�ȼ�
				con.signalAll();
		//	}
		}finally{
			lock.unlock();
		}
		
		
	}
	//����
	public  void seal(){
		lock.lock();
		try{
			while(product<=0){
				
				System.out.println("ȱ��");
				try {
					//this.wait();
					 con.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//else{//ȥ��else ��ִ��"--"�����ϱ��������򲻻�ȴ�
				System.out.println(Thread.currentThread().getName()+":"+ --product);
			    // this.notifyAll();
				con.signalAll();
			//}
		}finally{
			lock.unlock();
		}
	}
}
class Productor implements Runnable{
	private Clerk clerk;

	public Productor(Clerk clerk) {		
		this.clerk = clerk;
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<20;i++){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.get();
		}
	}
	
}
class Cousumer implements Runnable{
 private Clerk clerk;
 
	public Cousumer(Clerk clerk) {	
	this.clerk = clerk;
}
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<20;i++){
			clerk.seal();
		}
	}
	}