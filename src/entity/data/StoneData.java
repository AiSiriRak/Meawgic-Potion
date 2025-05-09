package entity.data;

import entity.base.Stone;

public enum StoneData {
	REDSTONE("RedStone", 0),
	GROWSTONE("GrowStone", 0);

	private final Stone item;

	StoneData(String name, int capacity) {
		this.item = new Stone(name, capacity);
	}

	public Stone getItem() {
		return item;
	}

}
