package com.pauu.trafficsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {
	private List<String> vechicles = new ArrayList<String>();//路上有车
	private String name;//路的名字
	public Road(String name){
		this.name = name;
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(new Runnable() {
			@Override
			public void run() {
				//产生999辆车
				for(int i=1;i<1000;i++){
					try {
						Thread.sleep((new Random().nextInt(10)+1)*100);
						//生成某个方向上的第几辆车
						vechicles.add(Road.this.name+"_"+i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		//每隔1秒检查是不是绿灯，是绿灯相应路线上就减少一辆车
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				boolean lighted = Lamp.valueOf(Road.this.name).isLighted();//绿灯状态
				if(vechicles.size()>0){//确保路线上有车
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
