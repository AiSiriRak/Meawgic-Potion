package logic.game;

public class Coin {

    private int coins;
    private boolean isWarning;

    public Coin() {
        this.coins = 100;
        this.isWarning = false;
    }

    public void increaseCoin(int amount) {
        coins += amount;
        GameController.updateCoinDisplay();
    }

    public boolean decreaseCoin(int price) {
        if (coins >= price) {
            coins -= price;
            GameController.updateCoinDisplay();
            return true;
        }
        else {
			System.out.println("Not Enough Money");
			this.isWarning = true;
			Thread warningCountdown = new Thread(() -> {
				while (this.isWarning) {
					try {
						GameController.getWarningCoinPane().setVisible(true);
						Thread.sleep(1000);
						GameController.getWarningCoinPane().setVisible(false);
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

	public int getCoins() {
		return coins;
	}

}