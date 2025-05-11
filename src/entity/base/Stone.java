package entity.base;

public class Stone extends Ingredient {
	private int buyPrice;

	public Stone(String name) {
		super(name);
		this.setBuyPrice(5);
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

}
