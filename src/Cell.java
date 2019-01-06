import java.awt.Graphics;
import java.awt.Image;



public class Cell {
	protected int x;
	protected int y;
	protected boolean Crossable;
	protected boolean Destroyable;
	protected boolean WithBomb;
	protected boolean Bonus;
	protected boolean Hurt;
	public Bomb bombOnCase;
	public Bonus bonusOnCase;
	protected Image img;
	
	public void setBomb(Bomb bomb,boolean answer) {
		this.WithBomb = answer;
		this.bombOnCase = bomb;	
		this.Crossable = !answer;
	}
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		Panel_game.grid.get(this.x).set(this.y,this);	
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean isCrossable() {
		return this.Crossable;
	}
	
	public boolean isDestroyable() {
		return this.Destroyable;
	}
	
	public void setHurt(boolean answer) {
		this.Hurt = answer;
	}

	public void draw(Graphics g) {
		g.drawImage(this.img, 32*this.x, 32*this.y, null);
	}
	
	public void addBonus(int x, int y){ // adds (or not) a new bonus to the grid ; called after a wood explosion
		
		/* GIVES A RANDOM INTEGER BETWEEN 0 AND 100 */
		double ran = Math.random()*100;
		int randomBonus = (int) ran;

		/* IF...ELSE STRUCTURE TO CHOOSE A RANDOM BONUS AFTER THE WOOD DESTRUCTION */
		if (randomBonus < 3){
			this.bonusOnCase = new Life(x, y, 1);
		} if (3<= randomBonus && randomBonus < 6){
			this.bonusOnCase = new Freeze(x, y);
		} if (6<= randomBonus && randomBonus < 9){
			this.bonusOnCase = new MunitionBlock(x, y);
		} if (9<= randomBonus && randomBonus < 12){
			this.bonusOnCase = new BombDelay(x, y, 1000);
		} if (12<= randomBonus && randomBonus < 15){
			this.bonusOnCase = new BombRange(x, y, 1);
		} if (15<= randomBonus && randomBonus < 18){
			this.bonusOnCase = new BombRange(x, y, -1);
		} if (18<= randomBonus && randomBonus < 21){
			this.bonusOnCase = new MunitionRocketLauncher(x, y);
		} if (21<= randomBonus && randomBonus < 24){
			this.bonusOnCase = new MunitionSpike(x, y);
		} if (24<= randomBonus && randomBonus < 27){
			this.bonusOnCase = new Life(x, y, -1);
		} if (27<= randomBonus && randomBonus < 30){
			this.bonusOnCase = new BombDelay(x, y, -1000);
		} if (30<= randomBonus && randomBonus < 31){
			this.bonusOnCase = new StarMode(x, y);
		}
	}
}
