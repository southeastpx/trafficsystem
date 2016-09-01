package com.pauu.trafficsystem;

public enum Lamp {
	S2N("N2S","S2W",false),S2W("W2S","E2W",false),E2W("W2E","E2S",false),E2S("S2E","S2N",false),
	N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),
	S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);
	private boolean lighted;//�Ƶ�״̬
	private String opposite;//����ĵ�
	private String next;//��һ����
	private Lamp(String opposite,String next,boolean lighted) {
		this.opposite = opposite;
		this.next = next;
		this.lighted = lighted;
	}
	//���صƵ�״̬
	public boolean isLighted(){
		return this.lighted;
	}
	//������
	public void light(){
		this.lighted = true;//�����Լ�
		if(opposite!=null){
			Lamp lamp = Lamp.valueOf(opposite);
			lamp.light();//��������ĵ�
		}
	}
	//Ϩ���
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