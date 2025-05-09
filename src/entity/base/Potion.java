package entity.base;

public class Potion extends Item {
	private int sellPrice;
	private int duration;
	private boolean isCraft;

	public Potion(String name, int capacity, int sellPrice, int duration) {
		super(name, capacity);
		this.setSellPrice(sellPrice);
		this.setDuration(duration);
		this.setCraft(false);
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

	public boolean isCraft() {
		return isCraft;
	}

	public void setCraft(boolean isCraft) {
		this.isCraft = isCraft;
	}

}
