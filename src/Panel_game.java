import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings({ "serial" })	
public class Panel_game extends JPanel {
	
	public static ArrayList<ArrayList<Cell>> grid = initialList();
	public static ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
	public static ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	public static ArrayList<Bonus> bonusList = new ArrayList<Bonus>();
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	private Map map = new Map();
	public static Player player1 = new Player("player1",1,1);
	public static Player player2 = new Player("player2",15,15);
	public static int dt = 100;
	private static Timer timer;
	Frame f;
	
	public Panel_game(Frame f) {		
		this.f = f;
		addKeyListener(new KeyboardListener(f));
		timer = new Timer(dt, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (player1.getLife() > 0 && player2.getLife() > 0) {
					if (!player1.getFreeze()){
						player1.Move(KeyboardListener.getGauche1(), KeyboardListener.getDroite1(), KeyboardListener.getHaut1(), KeyboardListener.getBas1());
						player1.star(player1.getStarMode()); // gives its new powers to the player
						player1.die(); // checks if player1 is hurt
						KeyboardListener.setpOff(player1.selectWeapon(KeyboardListener.getpOn(), KeyboardListener.getpOff()));
					}
					if (!player2.getFreeze()){
						player2.Move(KeyboardListener.getGauche2(), KeyboardListener.getDroite2(), KeyboardListener.getHaut2(), KeyboardListener.getBas2());
						player2.star(player2.getStarMode()); // gives its new powers to the player
						player2.die(); // checks if player2 is hurt
						KeyboardListener.setpOff(player2.selectWeapon(KeyboardListener.getbOn(), KeyboardListener.getwOff()));
					}
					for(Weapon weapon : weaponList){
						weapon.countdown();
					}
					for(Explosion explosion : explosionList){
						explosion.countdown();
					}
					for (Player player : playerList){
						if (grid.get(player.getX()).get(player.getY()).Bonus){ // if the player stands on a bonus cell
							player.winsBonus(); // the bonus is applied to the player
							grid.get(player.getX()).get(player.getY()).Bonus = false; // the bonus disappears from the cell
						}
						if (player.getFreeze() || player.getStarMode()){ // if the player isn't in "normal" mode
							player.countdown(); // the player's countdown decreases
							if (player.getCountdown() <= 0){ // when the countdown is finished
								player.setFreeze(false); // it defreezes the player
								player.setStarMode(false); // it sets de StarMode to false
								player.setCountdown(5000); // the player's countdown is reset
								if (player.getDead()){
									player.life();
								}
							}
						}
					}
					repaint();	
				}
				else {
					f.setVisible(false);
					timer.stop();
					if (player2.getLife() <= 0) { //Zombie is dead. Steve won.
						new Frame(3);
					}
					if (player1.getLife() <= 0) { //Steve is dead. Zombie won.
						new Frame(4);
					}
				}
			}
		});
		timer.start();
		setFocusable(true);
		requestFocus();
	}
	
	private static ArrayList<ArrayList<Cell>> initialList() {
		ArrayList<ArrayList<Cell>> iniList = new ArrayList<ArrayList<Cell>>();
		for (int x=0; x < 17; x++) {
			iniList.add(new ArrayList<Cell>());
			for (int y=0; y < 17; y++) {
				iniList.get(x).add(null);
			}
		}
		return iniList;
	}
	
	static public Timer getTimer(){
		return timer;
	}
				
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		map.draw(g);
		player1.draw(g);
		player2.draw(g);
		for(Weapon weapon : weaponList){
			weapon.draw(g);
		}
		for(Explosion explosion : explosionList){
			explosion.draw(g);
		}
		for(Bonus bonus : bonusList){ // all the bonuses are drawn
			bonus.draw(g);
		}	
	}
}