public class Block extends Weapon {
	
	private String direction;
	private int x_target;
	private int y_target;
	
	public Block(Player block_owner){
		super(block_owner); //calls the constructor Weapon(player)
		this.type = "Block";
		if(this.weapon_owner.getAmmoBlock()>0){
			this.countdown = 100;
			this.direction = this.weapon_owner.getDirection(); // gets the player direction
			this.x_target = this.weapon_owner.getFrontCase(this.x,this.y,this.direction).getX(); //give the case position in front of the player according to his direction
			this.y_target = this.weapon_owner.getFrontCase(this.x,this.y,this.direction).getY();
			if(Panel_game.grid.get(this.x_target).get(this.y_target).isCrossable()){
				Panel_game.weaponList.add(this); //displays the block
				this.weapon_owner.addAmmoBlock(-1);
			}
		}
	}
	
	public void useWeapon(){
			this.finish = true;
			Panel_game.grid.get(this.x_target).set(this.y_target,new Wood(this.x_target,this.y_target)); //replace the targeted case by a block (= wood case)
			Sound.playTempSound("sounds/wood.wav");
		}
}
	
	

