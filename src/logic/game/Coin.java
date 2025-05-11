package logic.game;

public class Coin {

    private int coin;
    private boolean isEnoughCoin;
    private boolean isWarning;

    public Coin() {
        this.coin = 100;
        this.isWarning = false;
        this.isEnoughCoin = true;
    }

    public void increaseCoin(int amount) {
        coin += amount;
        GameController.updateCoinDisplay();
    }

    public boolean decreaseCoin(int price) {
        if (coin >= price) {
            coin -= price;
            GameController.updateCoinDisplay();
            return true;
        }
        else {
			System.out.println("Not Enough Money");
			this.isEnoughCoin = false;
			this.isWarning = true;
			Thread warningCountdown = new Thread(() -> {
				while (this.isWarning) {
					try {
						System.out.println(1);
						GameController.warningCoinPane.setVisible(true);
						Thread.sleep(1000);
						System.out.println(2);
						GameController.warningCoinPane.setVisible(false);
						this.isWarning = false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			warningCountdown.start();
        }
        return false;
    }

	public int getCoin() {
		return coin;
	}

	public boolean isEnoughCoin() {
		return isEnoughCoin;
	}

	public void setEnoughCoin(boolean isEnoughCoin) {
		this.isEnoughCoin = isEnoughCoin;
	}

	public boolean isWarning() {
		return isWarning;
	}

	public void setWarning(boolean isWarning) {
		this.isWarning = isWarning;
	}
	
    @Override
    public String toString() {
    	return String.valueOf(coin);
    }

}