import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

public class RocketLauncher extends Weapon{
	
	private int x_target; //last case before the rocket explodes
	private int y_target;
	private String direction;
	private boolean targeted = false; //when the rocket is not at the targeted case, targeted = false
	
	public RocketLauncher(Player rocket_owner){
		super(rocket_owner); //calls the constructor Weapon(player)
		this.type = "Rocket Launcher";
		if(this.weapon_owner.getAmmoRocket()>0){
			this.countdown = 100; 
			this.direction = this.weapon_owner.getDirection(); // gets the direction of the player
			setImg(this.direction);
			Panel_game.weaponList.add(this); //displays the rocket
			this.weapon_owner.addAmmoRocket(-1);
			this.target();
		}
	}
	
	public void move(RocketLauncher rocket){ //animation movement of the rocket before it explodes
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){ //the rocket move case by case each 100 ms
			@Override
			public void run(){
				if(rocket.direction == "right" && rocket.x < rocket.x_target){
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(false);
					rocket.x += 1;
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(true);
				}
				else if(rocket.direction =="left" && rocket.x > rocket.x_target ){
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(false);
					rocket.x -= 1;
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(true);
				}
				else if(rocket.direction == "up" && rocket.y > rocket.y_target ){
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(false);
					rocket.y -= 1;
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(true);
				}
				else if(rocket.direction =="down"&& rocket.y < rocket.y_target){
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(false);
					rocket.y += 1;
					Panel_game.grid.get(rocket.x).get(rocket.y).setHurt(true);
				}
				else{
					rocket.targeted = true;
				}
			}
		}, 0, 100);	
	}
	
	public void target(){ //gets the last case before the rocket explodes
		int i =0;int j =0;
		while(this.direction == "right" && Panel_game.grid.get(this.x +i).get(this.y).isCrossable()){
			this.x_target = Panel_game.grid.get(this.x +i).get(this.y).getX();
			this.y_target = Panel_game.grid.get(this.x +i).get(this.y).getY();
			i++;
		}
		i =0;
		while(this.direction == "left" && Panel_game.grid.get(this.x -i).get(this.y).isCrossable()){
			this.x_target = Panel_game.grid.get(this.x -i).get(this.y).getX();
			this.y_target = Panel_game.grid.get(this.x -i).get(this.y).getY();
			i++;
		}
		while(this.direction == "down" && Panel_game.grid.get(this.x).get(this.y +j).isCrossable()){
			this.x_target = Panel_game.grid.get(this.x).get(this.y +j).getX();
			this.y_target = Panel_game.grid.get(this.x).get(this.y +j).getY();
			j++;
		}
		j =0;
		while(this.direction == "up" && Panel_game.grid.get(this.x).get(this.y -j).isCrossable()){
			this.x_target = Panel_game.grid.get(this.x).get(this.y -j).getX();
			this.y_target = Panel_game.grid.get(this.x).get(this.y -j).getY();
			j++;
		}	
	}
	
	public void useWeapon(){
		move(this);
		Sound.playTempSound("sounds/launch_rocket.wav");
		if(this.targeted){
			new Explosion(this); // when the last case is reached, an explosion is created
			this.finish =true;
		}	
	}
	
	
	
	public void setImg (String direction){ // Gets the picture of the rocket according to the player direction
		if (direction == "up"){
			try {
				this.img = ImageIO.read(new File("Rocket_up.png"));
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file Rocket_up.png");
			}
		}
		else if(direction == "down"){
			try {
				this.img = ImageIO.read(new File("Rocket_down.png"));
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file Rocket_down.png");
			}
		}
		else if(direction =="right"){
			try {
				this.img = ImageIO.read(new File("Rocket_right.png"));
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file Rocket_right.png");
			}
		}
		else{
			try {
				this.img = ImageIO.read(new File("Rocket_left.png"));
			} catch (IOException e1) {
				this.img = null;
				System.out.println("Impossible to read file Rocket_left.png");
			}
		}	
	}
}
