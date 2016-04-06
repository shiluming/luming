package com.carnation.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Runnable_Task_2 implements Runnable{

	private CloseableHttpClient httpClient;
	
	private String url;
	
	public Runnable_Task_2(String url,CloseableHttpClient httpClient) {
		this.url = url;
		this.httpClient = httpClient;
	}
	
	public void run() {
		
		FileOutputStream output = null;
		HttpResponse response = null;
		try {
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("a.img > img");
			Elements names = doc.select("div.text > p");
			String name = names.get(0).text();
			for(Element e : links ) {
				String imgurl = e.attr("src");
				System.out.println(name + " 待下载的图片： " + imgurl);
				HttpGet downimage = new HttpGet(imgurl);
				long image = new Date().getTime();
				response = httpClient.execute(downimage);
				File dir = new File("e://girl//"+name+"//");
				dir.mkdir();
				File file = new File("e://girl//"+name+"//"+image+".jpg");
				output = new FileOutputStream(file);
				HttpEntity entity = response.getEntity();
				entity.writeTo(output);
				output.flush();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
