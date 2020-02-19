package 闭锁;

import java.util.concurrent.CountDownLatch;

public class Text {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	final	CountDownLatch latch=new CountDownLatch(5);//标记五个线程结束
            Demo a=new Demo(latch);
           long start=System.currentTimeMillis();
    for(int i=0;i<5;i++){
    	new Thread(a).start();
    }
    try {
		latch.await();//闭锁操作
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    long end =System.currentTimeMillis();
    System.out.println("操作时间"+(end-start));
	}

}
class Demo implements Runnable{
	  private CountDownLatch latch;
	  
	public Demo(CountDownLatch latch) {
		
		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			synchronized(this){
			    for(int i=0;i<4000;i++){
			    	if(i%2==0){
			    		System.out.println(i);
			    	}
			      }
		       }
		
	    }finally{
	    	latch.countDown();//自己减一
	    }
	}
  
	
	
}