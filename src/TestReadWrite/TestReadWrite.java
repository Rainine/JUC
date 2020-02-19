package TestReadWrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          ReadWriteDemo re=new ReadWriteDemo();
          new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				re.set((int)(Math.random()*101));
			}
		},"write").start();
          for(int i=0;i<100;i++){
        	  new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					re.get();
				}
			},"read").start();
			
        	  
          }
	}

}
class ReadWriteDemo {
	private int number=0;
	private ReadWriteLock lock=new ReentrantReadWriteLock();
	public void get(){
		lock.readLock().lock();;
		try{
			System.out.println(Thread.currentThread().getName()+":"+number);
			
		}finally{
			lock.readLock().unlock();
		}
		//return number;
	}
	public void set(int number){
		lock.writeLock().lock();;
		try{
			System.out.println(Thread.currentThread().getName());
			this.number=number;
		}finally{
			lock.writeLock().unlock();
		}
	
	}
	
}