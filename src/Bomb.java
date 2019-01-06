import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

public class Bomb  extends Weapon{
	
	public Bomb(Player bomb_owner){ //Constructor of "Bomb"
		super(bomb_owner); //calls the constructor Weapon(player)
		this.type = "Bomb";
		if(this.weapon_owner.getAmmoBomb()>0){
			this.countdown = 4000; //countdown of the bomb = 4 sec
			this.setImg(this);				
			Panel_game.grid.get(this.x).get(this.y).setBomb(this,true); //drop the bomb on the targeted case
			Panel_game.weaponList.add(this); // display the bomb
			this.weapon_owner.addAmmoBomb(-1);
		}
	}
	
	public void setImg(Bomb bomb){ //the bomb picture switches during the countdown 
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			boolean next = true;
			@Override
			public void run(){		
				if(next){
					change_img1(bomb);
					next = false;
				}
				else{
					change_img2(bomb);
					next = true;
				}
			}			
		}, 0,500 );
	}
			
	public void change_img1(Bomb bomb){
		try {
			bomb.img = ImageIO.read(new File("Tnt.png"));
		} catch (IOException e1) {
			bomb.img = null;
			System.out.println("Impossible to read file Tnt.png");} 
	}
		
	public void change_img2(Bomb bomb){
		try {
			bomb.img = ImageIO.read(new File("Tnt2.png"));
		} catch (IOException e1) {
			bomb.img = null;
			System.out.println("Impossible to read file Tnt2.png"); 	}
	}
				
	public void useWeapon(){
		this.finish = true;
		Panel_game.grid.get(this.x).get(this.y).setBomb(null,false); // drop the bomb off the case
		new Explosion (this); // create an explosion where the bomb explodes
		this.weapon_owner.addAmmoBomb(1); //the player get it's ammo back
	}		
}