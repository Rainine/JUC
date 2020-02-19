package ����;

import java.util.concurrent.CountDownLatch;

public class Text {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	final	CountDownLatch latch=new CountDownLatch(5);//�������߳̽���
            Demo a=new Demo(latch);
           long start=System.currentTimeMillis();
    for(int i=0;i<5;i++){
    	new Thread(a).start();
    }
    try {
		latch.await();//��������
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    long end =System.currentTimeMillis();
    System.out.println("����ʱ��"+(end-start));
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
	    	latch.countDown();//�Լ���һ
	    }
	}
  
	
	
}