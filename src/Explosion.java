import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Explosion {
	
	private int x;
	private int y;
	private int range; 
	private Player weapon_owner;
	private int countdown = 2000; // explosion effect time 
	private Image img;
	private ArrayList<Coord> targetList = new ArrayList<Coord>(); //List of all the targeted cases
	private boolean isfire = true; //when the case is on fire or not
	
	public Explosion(Bomb bomb){ //the explosion take all the informations from the bomb
		this.x = bomb.x;
		this.y = bomb.y;
		this.weapon_owner = bomb.getOwner();
		this.range = this.weapon_owner.getRange();
		try {
			this.img = ImageIO.read(new File("explosion.png"));
		} catch (IOException e1) {
			this.img = null;
			System.out.println("Impossible to read file explosion.png");
		}
		this.target();
		this.chainExplosion();
		Panel_game.explosionList.add(this); //displays the explosion
		this.setHurtCase(this.isfire);
	}
	
	public Explosion(RocketLauncher rocket){ //Explosion used for the rocket launcher
		this.x = rocket.x;
		this.y = rocket.y;
		this.weapon_owner = rocket.weapon_owner;
		this.range = this.weapon_owner.getRange();
		try {
			this.img = ImageIO.read(new File("explosion.png"));
		} catch (IOException e1) {
			this.img = null;
			System.out.println("Impossible to read file explosion.png");
		}
		this.target();
		this.chainExplosion();
		Panel_game.explosionList.add(this);
		this.setHurtCase(this.isfire);
	}
	
	public void setHurtCase(boolean isfire){ //put all the targeted cases in a hurting state and destroy the wood cases
		for(Coord elem: targetList){
			int x_target = elem.getX();
			int y_target = elem.getY();
			Panel_game.grid.get(x_target).get(y_target).setHurt(isfire);
			if((!Panel_game.grid.get(x_target).get(y_target).isCrossable())&&isfire){
				Panel_game.grid.get(x_target).set(y_target,new Stone(x_target,y_target)); // replace the wood cases by stone cases
				Sound.playTempSound("sounds/explosion.wav"); // plays the sound of an explosion when a bomb explodes
				Panel_game.grid.get(x_target).get(y_target).addBonus(x_target, y_target); // calls the method which adds (or not) a bonus  		
			}
		}
	}
	
	public void countdown(){
		if(this.isfire){
			this.countdown -= Panel_game.dt;
			if(this.countdown <= 0){
				this.isfire = false;
				this.setHurtCase(this.isfire);// after the countdown, the targeted cases go back to an unwound state 
			}
		}
	}

	
	public void chainExplosion(){ // explodes the other bomb on the targeted cases
		int i =1;
		if(this.isfire){
			while((Panel_game.grid.get(this.x +i).get(this.y).isCrossable() || Panel_game.grid.get(this.x +i).get(this.y).WithBomb) && i<= this.range){	
				if(Panel_game.grid.get(this.x +i).get(this.y).WithBomb){
					Panel_game.grid.get(this.x +i).get(this.y).bombOnCase.useWeapon();
				}
				i++;
			}
			i =1;
			while((Panel_game.grid.get(this.x -i).get(this.y).isCrossable() || Panel_game.grid.get(this.x -i).get(this.y).WithBomb) && i<= this.range){			
				if(Panel_game.grid.get(this.x -i).get(this.y).WithBomb){
					Panel_game.grid.get(this.x -i).get(this.y).bombOnCase.useWeapon();
				}
				i++;
			}
			i = 1;
			while((Panel_game.grid.get(this.x).get(this.y +i).isCrossable() || Panel_game.grid.get(this.x).get(this.y +i).WithBomb) && i<= this.range){
				if(Panel_game.grid.get(this.x).get(this.y +i).WithBomb){
					Panel_game.grid.get(this.x).get(this.y +i).bombOnCase.useWeapon();
				}
				i++;
			}
			i=1;
			while((Panel_game.grid.get(this.x).get(this.y -i).isCrossable() || Panel_game.grid.get(this.x).get(this.y -i).WithBomb) && i<= this.range){
				if(Panel_game.grid.get(this.x).get(this.y -i).WithBomb){
					Panel_game.grid.get(this.x).get(this.y -i).bombOnCase.useWeapon();
				}
				i++;
			}
		}
	}
	
	public void draw(Graphics g){
		if(this.isfire){
			for(Coord elem :targetList){
				int x_target = elem.getX();
				int y_target =elem.getY();
				g.drawImage(this.img,x_target *32,y_target*32,null); // picture are 32 pixel
			}
		}
	}
	
	public void target(){ // add the targeted cases to the list 
		int i=0;int j =1;
		boolean finish= false;
		while(i<=this.range && Panel_game.grid.get(this.x +i).get(this.y).isDestroyable() &&(finish == false)){
			if (Panel_game.grid.get(this.x +i).get(this.y).isCrossable()){
				this.targetList.add(new Coord(this.x +i,this.y));
			}
			if((!Panel_game.grid.get(this.x +i).get(this.y).isCrossable()) && isfire){
				this.targetList.add(new Coord(this.x +i,this.y));
				finish = true;
			}
			i++;
		}
		finish = false;
		i =1;
		while(i<=this.range && Panel_game.grid.get(this.x -i).get(this.y).isDestroyable() &&(finish == false)){ 
			if (Panel_game.grid.get(this.x -i).get(this.y).isCrossable()){
				this.targetList.add(new Coord(this.x -i,this.y));
			}
			if((!Panel_game.grid.get(this.x -i).get(this.y).isCrossable())&& isfire){
				this.targetList.add(new Coord(this.x -i,this.y));
				finish= true;
			}
			i++;
		}
		finish = false;
		while(j<=this.range && Panel_game.grid.get(this.x).get(this.y +j).isDestroyable() && (finish == false)){
			if (Panel_game.grid.get(this.x).get(this.y +j).isCrossable()){
				this.targetList.add(new Coord(this.x,this.y +j));
			}
			if((!Panel_game.grid.get(this.x).get(this.y +j).isCrossable()) && isfire){
				this.targetList.add(new Coord(this.x,this.y +j));
				finish = true;
			}
			j++;
		}
		finish = false;
		j=1;
		while(j<=this.range && Panel_game.grid.get(this.x).get(this.y -j).isDestroyable() && (finish == false)){
			if (Panel_game.grid.get(this.x).get(this.y -j).isCrossable()){
				this.targetList.add(new Coord(this.x,this.y -j));
			}
			if((!Panel_game.grid.get(this.x).get(this.y -j).isCrossable()) && isfire){
				this.targetList.add(new Coord(this.x,this.y -j));
				finish = true;
			}
			j++;
		}
	}	
}