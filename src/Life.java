import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Life extends Bonus {
	
	private int increments;
	
	public Life(int x,int y, int increment){
		super(x,y); // calls the constructor of Bonus(x,y)
		Panel_game.grid.get(x).get(y).Bonus = true;
		this.increments = increment;
		try {
			this.img = ImageIO.read(new File("bonus_life.png"));
		} catch (IOException e) {
			this.img = null;
			System.out.println("Impossible to read file bonus_life.png");
		}
		Panel_game.bonusList.add(this); // displays the bonus
	}
	
	public void actionBonus(Player player){ // called when a player wins the Life bonus
		if (this.increments == -1){
			Panel_game.grid.get(player.getX()).get(player.getY()).setHurt(true); // the player goes back to his spawn when he is hurt
		} else {
			player.changeLife(this.increments); // if the player wins a life, he stays in place
		}
		Panel_game.bonusList.remove(this);
	}
}
