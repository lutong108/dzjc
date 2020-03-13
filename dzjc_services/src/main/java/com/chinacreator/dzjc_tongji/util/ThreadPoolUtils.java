package com.chinacreator.dzjc_tongji.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池工具类
 * @author zyz818
 *
 */
public class ThreadPoolUtils {

	//CPU参数
	private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

	//核心线程数
	private static int CORE_POOL_SIZE = CPU_COUNT + 1;

	//最大线程数
	private static int MAX_POOL_SIZE = CPU_COUNT + 1;

	//线程空闲时间
	private static int KEEP_ALIVE_TIME = 60;
	
	//任务队列
	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

	//线程工厂
	private static ThreadFactory threadFactory = new ThreadFactory() {
		private final AtomicInteger integer = new AtomicInteger(1);
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "ThreadPool thread:" + integer.getAndIncrement());
		}
	};

	private static ThreadPoolExecutor threadPool;

	static {
		threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, threadFactory);
	}

	/**
	 * 从线程池中抽取线程，执行指定的Runnable对象
	 * @param runnable
	 */
	public static void execute(Runnable runnable) {
		threadPool.execute(runnable);
	}
	
	/**
	 * 从线程池中抽取线程，执行指定的Callable对象
	 * @param <V>
	 * @param runnable
	 * @return 
	 */
	public static <V> Future<V> execute(Callable<V> callable) {
		return threadPool.submit(callable);
	}
	
	public static int getQueue(){
		return threadPool.getQueue().size();
	}
	
	public static int getActiveCount(){
		return threadPool.getActiveCount();
	}
	
	public static boolean isActive() {
		if (getQueue() == 0 && getActiveCount() == 0) {
			return false;
		}
		return true;
	}
	
}

