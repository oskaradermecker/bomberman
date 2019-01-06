import java.awt.Graphics;
import java.awt.Image;

public class Weapon{
	
	protected int x;
	protected int y;
	protected Player weapon_owner;
	protected boolean finish = false; // characterize if the action of the weapon is finish or not
	protected Image img;
	protected int countdown; //effect time of the weapon
	public String type;
	
	public Weapon(Player weapon_owner){
		this.weapon_owner = weapon_owner;
		this.x = this.weapon_owner.getX(); 
		this.y = this.weapon_owner.getY();
	}
	
	public void useWeapon(){
	}
	
	public Player getOwner(){
		return this.weapon_owner;
	}
	
	public void draw(Graphics g){ //draw the weapon
		if(!this.finish){
			g.drawImage(this.img,this.x *32,this.y *32,null); // because the img are 32 pixels
		}
	}
	public void countdown(){
		if (!this.finish){
			this.countdown -= Panel_game.dt; // we decrease the countdown
			if(this.countdown<=0){
				this.useWeapon(); //when the countdown is over, the weapon is used
			}
		}
	}
}
