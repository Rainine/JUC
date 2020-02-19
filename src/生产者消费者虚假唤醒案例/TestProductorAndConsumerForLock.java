package 生产者消费者虚假唤醒案例;
/**
public class TestProductorAndConsumerForLock {
//生产者和消费者案例
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  Clerk clerk=new Clerk();
  Productor p=new Productor(clerk);
  Cousumer c=new Cousumer(clerk);
  new Thread(p,"生产者A").start();
  new Thread(c,"消费者B").start();
  new Thread(p,"生产者C").start();
  new Thread(c,"消费者D").start();
  
	}

}
//店员
class Clerk{
	private int product=0;
	//卖货
	public synchronized void get(){
		while(product>=1){//为了避免虚假唤醒应该使用while一直循环
			System.out.println("产品已经满了");
		     try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//else{
			System.out.println(Thread.currentThread().getName()+":"+ ++product);
		     this.notifyAll();
	//	}
		
	}
	//进货
	public synchronized void seal(){
		while(product<=0){
			
			System.out.println("缺货");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//else{//去掉else 会执行"--"操作上边生产者则不会等待
			System.out.println(Thread.currentThread().getName()+":"+ --product);
		     this.notifyAll();
		//}
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
	}*/