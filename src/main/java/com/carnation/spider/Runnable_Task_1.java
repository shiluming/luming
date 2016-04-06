package com.carnation.spider;

import java.io.IOException;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Runnable_Task_1 implements Runnable{

	private Queue<String> queue_url;
	
	public Runnable_Task_1(Queue<String> queue_url) {
		this.queue_url = queue_url;
	}
	
	public void run() {
		for(int i=1;i<70;i++) {
			String url = "http://sexy.faceks.com/?page="+i;
			try {
				Document document = Jsoup.connect(url).timeout(50000).get();
				Elements links = document.select("a.img");
				for(Element link : links) {
					String img = link.attr("href");
					System.out.println(" 在队列添加了要下载图片的url: " + img + " 现在队列大小： " + queue_url.size());
					queue_url.add(img);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
