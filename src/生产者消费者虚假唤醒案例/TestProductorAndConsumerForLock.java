package ��������������ٻ��Ѱ���;
/**
public class TestProductorAndConsumerForLock {
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
	//����
	public synchronized void get(){
		while(product>=1){//Ϊ�˱�����ٻ���Ӧ��ʹ��whileһֱѭ��
			System.out.println("��Ʒ�Ѿ�����");
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
	//����
	public synchronized void seal(){
		while(product<=0){
			
			System.out.println("ȱ��");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//else{//ȥ��else ��ִ��"--"�����ϱ��������򲻻�ȴ�
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