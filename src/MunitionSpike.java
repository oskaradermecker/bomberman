import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MunitionSpike extends Bonus{
	
	public MunitionSpike(int x,int y){
		super(x,y); // calls the constructor of Bonus(x,y)
		Panel_game.grid.get(x).get(y).Bonus = true;
		try {
			this.img = ImageIO.read(new File("bonus_munitionspike.png"));
		} catch (IOException e) {
			this.img = null;
			System.out.println("Impossible to read file bonus_munitionspike.png");
		}
		Panel_game.bonusList.add(this); // displays the bonus
	}
	
	public void actionBonus(Player player){ // called when a player wins the MunitionSpike bonus
		player.addAmmoSpike(2);
		Panel_game.bonusList.remove(this);
	}
}