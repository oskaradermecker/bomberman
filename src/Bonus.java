import java.awt.Graphics;
import java.awt.Image;

public class Bonus{

	protected int x;
	protected int y;
	protected Image img;
	
	public Bonus(int x,int y){
		this.x = x;
		this.y = y;
		Panel_game.grid.get(this.x).get(this.y).Bonus = true;
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.img, 32*this.x, 32*this.y, null);
	}
	
	public void actionBonus(Player player){ // polymorphic method
	}
}