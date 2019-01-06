import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Spike extends Weapon{
	
	private String direction;

	public Spike(Player spike_owner){
		super(spike_owner); //calls constructor Weapon(player)
		this.type = "Spike";
		if((this.weapon_owner.getAmmoSpike()>0)){
			this.countdown = 500; // effect time of the spike
			this.direction = this.weapon_owner.getDirection(); //finds the direction of the player
			try {
				this.img = ImageIO.read(new File("spike.png"));
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file spike.png");
			}
			this.x = this.weapon_owner.getFrontCase(this.x,this.y,this.direction).getX(); 
			this.y = this.weapon_owner.getFrontCase(this.x,this.y,this.direction).getY();
			if(Panel_game.grid.get(this.x).get(this.y).isCrossable()){ 
				Panel_game.grid.get(this.x).get(this.y).setHurt(!this.finish);
				Panel_game.weaponList.add(this); //Displays the weapon
				this.weapon_owner.addAmmoSpike(-1);
			}
		}
	}
	
	public void useWeapon(){
			this.finish = true; // the action is finished after the use of the spike
			Panel_game.grid.get(this.x).get(this.y).setHurt(!this.finish);
		}
	}