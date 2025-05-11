package entity.base;

public class Crop extends Ingredient {
	private int buyPrice;
	private int sellPrice;
	private int duration;

	public Crop(String name, int buyPrice, int sellPrice, int duration) {
		super(name);
		this.setBuyPrice(buyPrice);
		this.sellPrice = sellPrice;
		this.setDuration(duration);
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
}
