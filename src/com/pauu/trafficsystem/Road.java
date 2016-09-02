package com.pauu.trafficsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {
	private List<String> vechicles = new ArrayList<String>();//·���г�
	private String name;//·������
	public Road(String name){
		this.name = name;
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(new Runnable() {
			@Override
			public void run() {
				//����999����
				for(int i=1;i<1000;i++){
					try {
						Thread.sleep((new Random().nextInt(10)+1)*100);
						//����ĳ�������ϵĵڼ�����
						vechicles.add(Road.this.name+"_"+i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		//ÿ��1�����ǲ����̵ƣ����̵���Ӧ·���Ͼͼ���һ����
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				boolean lighted = Lamp.valueOf(Road.this.name).isLighted();//�̵�״̬
				if(vechicles.size()>0){//ȷ��·�����г�
					if(lighted){
						System.out.println(vechicles.remove(0)+" is tranfering!");
					}
				}
			}
		}, 
			1, 
			1, 
			TimeUnit.SECONDS);
	}
}
