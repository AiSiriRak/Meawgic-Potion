package entity.data;

import entity.base.Crop;

public enum CropData {

	NETHER_WART("NetherWart", 5, 8, 60), 
	WATERMELON("Watermelon", 10, 15, 90), 
	CARROT("Carrot", 10, 15, 60),
	SUGAR("Sugar", 10, 15, 90), 
	RABBIT_FOOT("RabbitFoot", 20, 30, 120), 
	PUFFERFISH("Pufferfish", 20, 30, 120),
	SPIDER_EYE("SpiderEye", 20, 30, 120), 
	MAGMA_CREAM("MagmaCream", 25, 40, 180), 
	GHAST_TEAR("GhastTear", 25, 40, 180),
	BLAZE_POWDER("BlazePowder", 25, 40, 180);

	private final Crop crop;

	CropData(String name, int buyPrice, int sellPrice, int duration) {
		this.crop = new Crop(name, buyPrice, sellPrice, duration);
	}

	public Crop getItem() {
		return crop;
	}

}