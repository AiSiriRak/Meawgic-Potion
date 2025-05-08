package entity.base;

public class Stone extends Ingredient{
	private int buyPrice;

	public Stone(String name, int capacity) {
		super(name, capacity);
		this.setBuyPrice(5);
		// TODO Auto-generated constructor stub
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

}
