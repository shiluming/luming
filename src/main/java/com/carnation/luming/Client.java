package com.carnation.luming;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.carnation.spider.Runnable_Task_1;
import com.carnation.spider.Runnable_Task_2;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		
		new Thread(new Runnable_Task_1(queue)).start();
		//new Thread(new Runnable_Task_2(queue.take(), httpClient)).start();
		
		Thread.sleep(10000);
		
		Executor executors = Executors.newFixedThreadPool(10);
		
		while(!queue.isEmpty()) {
			
			executors.execute(new Runnable_Task_2(queue.take(), httpClient));
			
		}
		
	}

}
