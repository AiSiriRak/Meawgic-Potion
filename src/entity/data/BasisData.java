package entity.data;

import entity.base.Basis;

public enum BasisData {

	NETHER_WART("NetherWart", 0, 5, 8, 60),
	WATERMELON("Watermelon", 0, 10, 15, 90),
	CARROT("Carrot", 0, 10, 15, 60),
	SUGAR("Sugar",0,10,15,90),
	RABBIT_FOOT("RabbitFoot",0,20,30,120),
	PUFFERFISH("Pufferfish",0,20,30,120),
	SPIDER_EYE("SpiderEye",0,20,30,120),
	MAGMA_CREAM("MagmaCream",0,25,40,180),
	GHAST_TEAR("GhastTear",0,25,40,180),
	BLAZE_POWDER("BlazePowder", 0, 25, 40, 180);
	
	private final Basis item;

	BasisData(String name, int capacity, int buyPrice, int sellPrice, int duration) {
		this.item = new Basis(name, capacity, buyPrice, sellPrice, duration);
	}

	public Basis getItem() {
		return item;
	}

}
