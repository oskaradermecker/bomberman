import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BombDelay extends Bonus{
	
	private int increments;
	
	public BombDelay(int x,int y, int increment){
		super(x,y); // calls the constructor of Bonus(x,y)
		Panel_game.grid.get(x).get(y).Bonus = true;
		this.increments = increment;
		try {
			this.img = ImageIO.read(new File("bonus_bombdelay.png"));
		} catch (IOException e) {
			this.img = null;
			System.out.println("Impossible to read file bonus_bombdelay.png");
		}
		Panel_game.bonusList.add(this); // displays the bonus
	}
	
	public void actionBonus(Player player){ // called when the player wins the BombDelay bonus
		if (this.increments != -player.getDelay()){ // to avoid the situation where the delay becomes "null"
			player.changeDelay(this.increments);
		}
		Panel_game.bonusList.remove(this);
	}		
}