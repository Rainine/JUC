package 线程调度;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledThread {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
        ScheduledExecutorService pool=Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 5; i++) {
			Future<Integer> result=pool.schedule(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
				  int number=new Random().nextInt(100);
				  System.out.println(Thread.currentThread().getName()+" : "+number);
					return number;
				}
			}, 2, TimeUnit.SECONDS);
			System.out.println(result.get());
		}
        pool.shutdown();
	}

}
