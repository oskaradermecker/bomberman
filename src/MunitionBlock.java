import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MunitionBlock extends Bonus {
	
	public MunitionBlock(int x,int y){
		super(x,y); // calls the constructor of Bonus(x,y)
		Panel_game.grid.get(x).get(y).Bonus = true;
		try {
			this.img = ImageIO.read(new File("bonus_munitionbreakableblock.png"));
		} catch (IOException e) {
			this.img = null;
			System.out.println("Impossible to read file bonus_munitionbreakableblock.png");
		}
		Panel_game.bonusList.add(this); // displays the bonus
	}
	
	public void actionBonus(Player player){ // called when a player wins the MunitionBlock bonus
		player.addAmmoBlock(3);
		Panel_game.bonusList.remove(this);
	}	
}