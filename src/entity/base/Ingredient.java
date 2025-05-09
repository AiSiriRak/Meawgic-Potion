package entity.base;

public class Ingredient extends Item {
	private boolean growState;

	public Ingredient(String name, int capacity) {
		super(name, 10);
		this.setGrowState(false);
	}

	public boolean isGrowState() {
		return growState;
	}

	public void setGrowState(boolean growState) {
		this.growState = growState;
	}

}
