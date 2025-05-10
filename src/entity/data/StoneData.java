package entity.data;

import entity.base.Stone;

public enum StoneData {
	REDSTONE("RedStone"),
	GROWSTONE("GrowStone");

	private final Stone stone;

	StoneData(String name) {
		this.stone = new Stone(name);
	}

	public Stone getItem() {
		return stone;
	}

}
