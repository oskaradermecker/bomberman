import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player {
	
	private String name;
	private int life;
	private Coord spawn;
	private int x;
	private int y;
	public Weapon weapon;
	private int index = 0;
	private int ammoBomb = 1;
	private int ammoRocket = 0;
	private int ammoSpike = 0;
	private int ammoBlock = 0;
	private int range = 1; 
	private boolean looking_left = true; //indicate the image that must be chosen
	private boolean looking_right = false;
	private boolean looking_up = false;
	private boolean looking_down = false;
	private boolean looking_dead = false;
	private boolean starMode = false;
	private int delay = 2000;
	private Image img;
	private boolean isFreeze = false;
	private int countdown = 3500;
		
	public Player(String name, int x, int y) {
		this.name = name;
		this.spawn = new Coord(x,y);
		this.x = x;
		this.y = y;
		this.life = 5;
		try { 
			this.img = ImageIO.read(new File(this.name + "right.png"));
		} catch (IOException e1) {
			this.img = null;
			System.out.println("Impossible to read file "+ this.name + "left.png");
		}
		Panel_game.playerList.add(this);
	}
	
	public void changeLife(int life) {
		this.life += life;
	}

	public int getLife() {
		return this.life;
	}
	
	public void changeRange(int range) {
		this.range += range;
	}
	
	public int getRange() {
		return this.range;
	}
	
	public void changeDelay(int delay){
		this.delay += delay;
	}
	
	public int getDelay(){
		return this.delay;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getAmmoRocket() {
		return this.ammoRocket;
	}	
	
	public int getAmmoSpike() {
		return this.ammoSpike;
	}	
	
	public int getAmmoBlock() {
		return this.ammoBlock;
	}
	
	public int getAmmoBomb(){
		return this.ammoBomb;
	}
	
	public void addAmmoRocket(int newAmmo) {
		this.ammoRocket += newAmmo;
	}
	
	public void addAmmoSpike(int newAmmo) {
		this.ammoSpike += newAmmo;
	}
	
	public void addAmmoBlock(int newAmmo) {
		this.ammoBlock += newAmmo;
	}
	
	public void addAmmoBomb(int newAmmo){
		this.ammoBomb += newAmmo;
	}
	
	public void addIndex(){
		this.index += 1;
	}
	
	public boolean getFreeze(){
		return this.isFreeze;
	}
	
	public void setFreeze(boolean b){
		this.isFreeze = b;
	}
	
	public boolean getStarMode(){
		return this.starMode;
	}

	public void setStarMode(boolean b) {
		this.starMode = b;
	}

	public int getCountdown(){
		return this.countdown;
	}
	
	public void setCountdown(int i){
		this.countdown = i;
	}
	
	public boolean getDead(){
		return this.looking_dead;
	}

	public void Move(boolean left, boolean right, boolean up, boolean down) {

		int xc = this.x;
		int yc = this.y;
		if(left) {
			this.looking_left = true; this.looking_right = false; 
			this.looking_up = false; this.looking_down = false;
			if (Panel_game.grid.get(xc-1).get(yc).Crossable && !Panel_game.grid.get(xc-1).get(yc).WithBomb ) {
				this.x -= 1;
			}
			else {
				this.x = xc;
			}
		}
		if(right) {
			this.looking_left = false; this.looking_right = true; 
			this.looking_up = false; this.looking_down = false;
			if (Panel_game.grid.get(xc+1).get(yc).Crossable && !Panel_game.grid.get(xc+1).get(yc).WithBomb) {
				this.x += 1;
			}
			else {
				this.x = xc;
			}
		}
		if(up) {
			this.looking_left = false; this.looking_right = false; 
			this.looking_up = true; this.looking_down = false; 
			if (Panel_game.grid.get(xc).get(yc-1).Crossable && !Panel_game.grid.get(xc).get(yc-1).WithBomb) {
				this.y -= 1;
			}
			else {
				this.y = yc;
			}
		}
		if(down) {
			this.looking_left = false; this.looking_right = false; 
			this.looking_up = false; this.looking_down = true; 
			if (Panel_game.grid.get(xc).get(yc+1).Crossable  && !Panel_game.grid.get(xc).get(yc+1).WithBomb) {
				this.y += 1;
			}
			else {
				this.y = yc;
			}
		}
		this.changeImage();
	}
	
	public void die(){
		if(Panel_game.grid.get(this.x).get(this.y).Hurt){
			this.life -= 1;
			if (this == Panel_game.player1){
				Sound.playTempSound("sounds/hurt_Steve.wav");
			}
			if (this == Panel_game.player2){
				Sound.playTempSound("sounds/death_zombie.wav");
			}
			Panel_game.grid.get(this.x).get(this.y).setHurt(false);
			this.looking_dead = true;
			this.looking_left = false;
			this.looking_right = false;
			this.looking_up = false;
			this.looking_down = false;
			this.setFreeze(true);
			this.changeImage();
		}
	}
	
	public void life(){
		if (!getFreeze()){
			this.x = this.spawn.getX();
			this.y= this.spawn.getY();
			this.looking_right = true;
			this.looking_dead = false;
		}
		
	}
	
	public boolean selectWeapon(boolean drop,boolean dropoff){
		if (this.index >= 4) 
				this.index = 0;
		if(drop && dropoff){
			if(this.index == 0){
				this.weapon = new Bomb(this);
			}
			else if(this.index == 1){
				this.weapon =new Spike(this);
			}
			else if(this.index == 2){
				this.weapon = new RocketLauncher(this);
			}
			else if(this.index == 3){
				this.weapon = new Block(this);
			} else if (this.index == 4){
				
			}
			dropoff = false;
		}
		return dropoff;
	}
	
	private void changeImage() {
		if (this.looking_left) {
			try {
				if (this.starMode){
					this.img = ImageIO.read(new File("starmode" + this.name + "left.png"));
				} else {
					this.img = ImageIO.read(new File(this.name + "left.png"));
				}
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file " + this.name + "left.png");
			}
		}
		if (this.looking_right) {
			try {
				if (this.starMode){
					this.img = ImageIO.read(new File("starmode" + this.name + "right.png"));
				} else {
					this.img = ImageIO.read(new File(this.name + "right.png"));
				}
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file " + this.name + "right.png");
			}
		}
		if (this.looking_up) {
			try {
				if (this.starMode){
					this.img = ImageIO.read(new File("starmode" + this.name + "up.png"));
				} else {
					this.img = ImageIO.read(new File(this.name + "up.png"));
				}
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file " + this.name + "up.png");
			}	
		}
		if (this.looking_down) {
			try {
				if (this.starMode){
					this.img = ImageIO.read(new File("starmode" + this.name + "down.png"));
				} else {
					this.img = ImageIO.read(new File(this.name + "down.png"));
				}
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file " + this.name + "down.png");
			}	
		}
		if(this.looking_dead){
			try {
				this.img = ImageIO.read(new File(this.name +"dead.png"));
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file " + this.name + "dead.png");
			}
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.img, 32*this.x, 32*this.y, null);
	}
		
	public Coord getFrontCase(int x, int y, String direction) {
		Coord FrontCase = null;
		if (direction == "left") {
			FrontCase = new Coord(x-1,y); 
		}
		else if (direction == "right") {
			FrontCase = new Coord(x+1,y);
		}
		else if (direction == "up") {
			FrontCase = new Coord(x,y-1);
		}
		else if (direction == "down") {
			FrontCase = new Coord(x,y+1);
		}
		else {
			System.out.println("Wrong direction");
		}
		return FrontCase;
	}
	
	public String getDirection() {
		String direction = "";
		if (looking_left) {
			direction = "left";
		}
		if (looking_right) {
			direction = "right";
		}
		if (looking_up) {
			direction = "up";
		}
		if (looking_down) {
			direction = "down";
		}
		return direction;
	}

	public void countdown(){
		this.countdown -= Panel_game.dt;
	}
	
	public void winsBonus(){ // called only if the player stands on a cell containing bonus
		Bonus thisCase = Panel_game.grid.get(this.x).get(this.y).bonusOnCase;
		thisCase.actionBonus(this);
		Sound.playTempSound("sounds/bonus.wav");
	}
		
	public void star(boolean b){ // destroys the blocks in front of the player (which is why the direction of the player is important)
		if (b){
			if (this.getDirection() == "left" && Panel_game.grid.get(this.x-1).get(this.y).isDestroyable() && !Panel_game.grid.get(this.x-1).get(this.y).isCrossable()){
				Panel_game.grid.get(this.x-1).set(this.y, new Stone(this.x-1,this.y));
				Panel_game.grid.get(this.x-1).get(this.y).addBonus(this.x-1,this.y);
			}
			if (this.getDirection() == "right" && Panel_game.grid.get(this.x+1).get(this.y).isDestroyable() && !Panel_game.grid.get(this.x+1).get(this.y).isCrossable()){
				Panel_game.grid.get(this.x+1).set(this.y, new Stone(this.x+1,this.y));
				Panel_game.grid.get(this.x+1).get(this.y).addBonus(this.x+1,this.y);
			}
			if (this.getDirection() == "down" && Panel_game.grid.get(this.x).get(this.y+1).isDestroyable() && !Panel_game.grid.get(this.x).get(this.y+1).isCrossable()){
				Panel_game.grid.get(this.x).set(this.y+1, new Stone(this.x,this.y+1));
				Panel_game.grid.get(this.x).get(this.y+1).addBonus(this.x,this.y+1);
			}
			if (this.getDirection() == "up" && Panel_game.grid.get(this.x).get(this.y-1).isDestroyable() && !Panel_game.grid.get(this.x).get(this.y-1).isCrossable()){
				Panel_game.grid.get(this.x).set(this.y-1, new Stone(this.x,this.y-1));
				Panel_game.grid.get(this.x).get(this.y-1).addBonus(this.x,this.y-1);
			}
		}
	}
}
	

