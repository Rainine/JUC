package 线程八锁;
//题目：判断打印的是One，还是two
public class TestThread {
  public static void main(String[] args) {
	Number n=new Number();
	Number n2=new Number();
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			n.getOne();
		}
	}).start();
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			n2.getTwo();
		}
	}).start();
//    new Thread(new Runnable() {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			n.getThree();;
//		}
//	}).start();
}
}
class Number{
	public static synchronized void getOne(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  System.out.println("one");
	}
	public  synchronized void getTwo(){
		  System.out.println("two");
		}
	public  void getThree(){
		  System.out.println("Three");
		}
}