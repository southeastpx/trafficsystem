package com.pauu.trafficsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
						Thread.sleep(new Random().nextInt(1)*1000);
						//生成某个方向上的第几辆车
						vechicles.add(Road.this.name+"_"+i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
