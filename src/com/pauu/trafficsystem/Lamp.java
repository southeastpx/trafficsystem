package com.pauu.trafficsystem;

public enum Lamp {
	S2N("N2S","S2W",false),S2W("W2S","E2W",false),E2W("W2E","E2S",false),E2S("S2E","S2N",false),
	N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),
	S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);
	private boolean lighted;//灯的状态
	private String opposite;//对面的灯
	private String next;//下一个灯
	private Lamp(String opposite,String next,boolean lighted) {
		this.opposite = opposite;
		this.next = next;
		this.lighted = lighted;
	}
	//返回灯的状态
	public boolean isLighted(){
		return this.lighted;
	}
	//点亮灯
	public void light(){
		this.lighted = true;//点亮自己
		if(opposite!=null){
			Lamp lamp = Lamp.valueOf(opposite);
			lamp.light();//点亮对面的灯
		}
	}
	//熄灭灯
	public void blackOut(){
		this.lighted = false;
		if(opposite!=null){
			Lamp lamp = Lamp.valueOf(opposite);
			lamp.blackOut();
		}
		if(next!=null){
			Lamp.valueOf(Lamp.this.next).light();
		}
	}
}
