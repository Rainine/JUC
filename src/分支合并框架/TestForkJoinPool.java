package 分支合并框架;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class TestForkJoinPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ForkJoinPool pool =new ForkJoinPool() ;
    	Instant start=Instant.now();
	  ForkJoinTask<Long> task=new ForkJoinPooll(0L, 100000000L);
	  long sum =pool.invoke(task);
	  System.out.println(sum);
	  Instant end=Instant.now();
		System.out.println("耗费时间："+(Duration.between(start, end).toMillis()));
	
	}
	public void test(){//传统算法
		Instant start=Instant.now();
		long sum=0L;
		for (long i = 0; i <  100000000L; i++) {
			sum+=i;
		}
		System.out.println(sum);
		Instant end=Instant.now();
		System.out.println("耗费时间："+(Duration.between(start, end).toMillis()));
	}
	public void test1(){//jdk8新特性
		Instant start=Instant.now();
		Long sum=LongStream.rangeClosed(0L, 100000000)
				.parallel().reduce(0L, Long::sum);
		System.out.println(sum);
		Instant end=Instant.now();
		System.out.println("耗费时间："+(Duration.between(start, end).toMillis()));
	
	}

}
class ForkJoinPooll extends RecursiveTask<Long> {//我们自己的拆分
       private static final long serialVersionUID=-12324343432424L;
	private long start;
	private long end;
	private static final long THURSHOLD=10000L;//临界值
	public  ForkJoinPooll(long start,long end) {
	// TODO Auto-generated constructor stub
	        this.start=start;
	        this.end=end;
	}
       @Override
	protected Long compute() {
		// TODO Auto-generated method stub
		 long length=end-start;
		 if(length<=THURSHOLD){
			 long sum=0L;
			 for (long i = start; i < end; i++) {
				sum+=i;
			}
			 return sum;
		 }else{
				long middle=(start+end)/2;
				
				ForkJoinPooll left=new ForkJoinPooll(start, middle);
			    left.fork();//进行拆分
			    ForkJoinPooll right=new ForkJoinPooll(middle+1, end);
			    right.fork();//进行拆分
			    return left.join()+right.join();
			}
		
    	  
	}
	
}