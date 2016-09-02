package com.pauu.trafficsystem;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LampController {
	private Lamp currentLamp;
	public LampController(){
		this.currentLamp = Lamp.S2N;
		currentLamp.light();
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(
				new Runnable() {
					@Override
					public void run() {
						currentLamp = currentLamp.blackOut();
					}
				}, 
				10,
				10, 
				TimeUnit.SECONDS);
	}
}
