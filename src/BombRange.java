import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BombRange extends Bonus{
	
	private int increments;
	
	public BombRange(int x,int y,int increment){
		super(x,y); // calls the constructor of Bonus(x,y)
		Panel_game.grid.get(x).get(y).Bonus = true;
		this.increments = increment;
		try {
			this.img = ImageIO.read(new File("bonus_bombrange.png"));
		} catch (IOException e) {
			this.img = null;
			System.out.println("Impossible to read file bonus_bombrange.png");
		}
		Panel_game.bonusList.add(this); // displays the bonus
	}
	
	public void actionBonus(Player player){ // called when a player wins the Bombrange bonus
		if (this.increments != -player.getRange() && this.increments <= -player.getRange() + 3){ // forces the range to stay between between 0 and 3 blocks
			player.changeRange(this.increments); 
		}
		Panel_game.bonusList.remove(this);
	}
}