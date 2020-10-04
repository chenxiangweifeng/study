package com.example.study.common.pool;

import java.util.concurrent.*;

/**
 * @author 沉香微风
 * 自定义线程池
 * 线程池的数量和cpu的核心数密切相关，一般最大线程池数设置为2n即可，n为cpu核心数
 */
public class MyThreadExecutor {
   private static int corePoolSize = 640;    // 线程池的基本大小
   private static int maximumPoolSize = 640;  // 线程池最大大小
   private static int keepAliveTime = 10;  // 线程存活保持时间
   private static ThreadPoolExecutor executor =  new ThreadPoolExecutor(
                                                           corePoolSize,
                                                           maximumPoolSize,
                                                           keepAliveTime,
                                                           TimeUnit.SECONDS,
                                                           new LinkedBlockingQueue<>()
   );
    /**
     * 备用线程池2
     */
    private static ExecutorService executor2 = Executors.newFixedThreadPool(80);
    public void submit(Runnable task){
       executor.submit(task);
   }

    /**
     * 获取多线程的返回结果
     * @param task 需要运行的任务
     * @param <T> Future<T>用于取得任务的返回结果
     * @return 可以用 future.get() 方法读取
     */
   public static <T> Future<T> submit(Callable<T> task){
       return executor.submit(task);
   }
    public static void main(String[] args) throws Exception{
       Callable callable = new Callable() {
           @Override
           public Object call() throws Exception {
               return "string";
           }
       };
        Future<String> future = submit(() -> {
            int a = 3;
            int b = 4;
            return "hello" + "world";
        });
//        通过 future.get()得到线程的返回结果
        System.out.println(future.get());
    }

}
