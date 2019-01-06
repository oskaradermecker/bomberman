import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Freeze extends Bonus {

	public Freeze(int x,int y){
		super(x,y); // calls the constructor of Bonus(x,y)
		Panel_game.grid.get(x).get(y).Bonus = true;
		try {
			this.img = ImageIO.read(new File("bonus_freeze.png"));
		} catch (IOException e) {
			this.img = null;
			System.out.println("Impossible to read file bonus_freeze.png");
		}
		Panel_game.bonusList.add(this);
	}
	
	public void actionBonus(Player player){
		for (Player p : Panel_game.playerList){
			if (p != player){ // to avoid freezing the player who took the bonus
				p.setFreeze(true);
				if (p.getStarMode()){
					p.setStarMode(false); // stops the starmode if the player is frozen
					p.setCountdown(5000);
				}
			}
		}
		Panel_game.bonusList.remove(this);
	}
}
