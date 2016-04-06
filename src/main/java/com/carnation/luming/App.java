package com.carnation.luming;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

/**
 * <p>Description: </p>
 *
 * @author 宿舍楼顶
 * @date 2016年4月1日
 */
public class App 
{
	/**
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1000);
		final BlockingQueue<String> queue2 = new LinkedBlockingQueue<String>();
		//四条生产线程
		for(int i=0;i<7;i++) {
			Thread product = new Thread(new Runnable() {
				public void run() {
					while(true) {
						try {
							Thread.sleep((long) (Math.random()*(1000-10)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}//10秒生产一个产品
						
						queue2.add(" 生产线程 " + Thread.currentThread().getName() + " @ " + System.currentTimeMillis());
						System.out.println(" 生产线程 " + Thread.currentThread().getName() + " 生产了@ " + System.currentTimeMillis());
					}
				}
			});
			product.start();
		}
		//消费线程，每隔5秒消费一个产品
		for(int j=0;j<5;j++) {
			Thread custom = new Thread(new Runnable() {
				public void run() {
					while(true) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							if(queue2.isEmpty()) {
								System.out.println(" 消费线程在等待生产线程生产东西。。");
							}
							System.out.println("消费线程： "+ Thread.currentThread().getName() 
									+ " 消费了 ： " + queue2.take());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			custom.start();
			
		}
 		
		//队列的使用
//		System.out.println(queue.isEmpty());
//    	for(int j=0;j<1000;j++) {
//    		queue.put("hello : " + j + " ");
//    	}
//    	
//    	for(int i=0;i<30;i++) {
//    		Thread t = new Thread(new Runnable() {
//    			
//    			public void run() {
//    				while(!queue.isEmpty()) {
//    					try {
//    						Thread.sleep(100);
//    					} catch (InterruptedException e) {
//    						// TODO Auto-generated catch block
//    						e.printStackTrace();
//    					}
//    					try {
//							System.out.println(Thread.currentThread().getName()
//									+" 取得数据 ： " +queue.remove());
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//    				}
//    			}
//    		});
//    		t.start();
//    	}
//    	
//    	
//    	Thread.currentThread().sleep(15000);
//    	System.out.println("程序停止了15秒，之后开始GC");
//    	System.gc();
	}
  
    
    @Test
    public void testQueue() throws InterruptedException
    {
    	
    }
}
