import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	public static int dt2 = 150;
	private static Timer timer;
	
	public Board() { //Creation of the information board given to the players, next to the map.
		Color coolGray = new Color(215,215,215);
		this.setBackground(coolGray);
		timer = new Timer(dt2, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				repaint();	
			}
		});
		timer.start();
		setFocusable(true);
		requestFocus();
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font1 = new Font("Cambria Math", Font.BOLD, 30); //Ajouter du texte
		Font font2 = new Font("Cambria Math", Font.BOLD, 20);
		Font font3 = new Font("Cambria Math", Font.BOLD, 15);
		Font font4 = new Font("Cambria Math", Font.BOLD, 17);
		g.setFont(new Font("Cambria Math", Font.BOLD, 15));
		g.setColor(Color.RED);
		g.drawString("Press ESC to pause",10,30);
		g.setFont(font1);
		
		Color greeen = new Color(0,145,0);
		g.setColor(Color.black);
		g.drawString("Board",225,40);
		
		

		//Player 1
		if (Panel_game.player1.getFreeze()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Cambria Math", Font.BOLD, 190));
			g.drawString("STOP!",5,220);
		}
		if (Panel_game.player1.getStarMode()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Cambria Math", Font.BOLD, 90));
			g.drawString("STARMODE!",5,220);
		}
		g.setColor(Color.black);
		g.setFont(font2);
		g.drawString("-----------------------------------------------", 85, 70);
		g.drawString("Steve", 20, 90);
		g.setFont(font4);
		g.drawString("Controls : ", 20, 110);
		g.setFont(font3);
		g.drawString("Left = Left Arrow | Right = Right Arrow", 118, 135);
		g.drawString("Up = Up Arrow | Down = Down Arrow", 136, 155);
		g.drawString("Change Weapon = 'L'", 90, 175);
		g.drawString("Use Weapon = 'M'", 270, 175);
		g.drawString("Life = ", 20, 205);
			if (Panel_game.player1.getLife() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player1.getLife() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player1.getLife(), 83, 205);
		g.setColor(Color.black);
		if (Panel_game.player1.getIndex() == 0) 
			g.drawString("Bombs Selected", 200, 215);
		if (Panel_game.player1.getIndex() == 1) 
			g.drawString("Spikes Selected", 200, 215);
		if (Panel_game.player1.getIndex() == 2) 
			g.drawString("Rockets Selected", 200, 215);
		if (Panel_game.player1.getIndex() == 3) 
			g.drawString("Blocks Selected", 200, 215);
		g.drawString("Range = " + Panel_game.player1.getRange(), 415, 205);
		g.setFont(font4);
		g.drawString("Ammo : ", 20, 230);
		g.setFont(font3);
		g.drawString("Spike = ", 20, 255);
			if (Panel_game.player1.getAmmoSpike() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player1.getAmmoSpike() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player1.getAmmoSpike(), 90, 255);
		g.setColor(Color.black);
		g.drawString("Rocket = ", 215, 255);
			if (Panel_game.player1.getAmmoRocket() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player1.getAmmoRocket() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player1.getAmmoRocket(), 300, 255);
		g.setColor(Color.black);
		g.drawString("Block = ", 416, 255);
			if (Panel_game.player1.getAmmoBlock() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player1.getAmmoBlock() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player1.getAmmoBlock(), 487, 255);
		g.setColor(Color.black);
		g.drawString("Position = (" + Panel_game.player1.getX() + ", " + Panel_game.player1.getY() + ")", 20, 275);
		
		
		
		//Player 2
		if (Panel_game.player2.getFreeze()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Cambria Math", Font.BOLD, 190));
			g.drawString("STOP!",5,480);
		}
		if (Panel_game.player1.getStarMode()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Cambria Math", Font.BOLD, 90));
			g.drawString("STARMODE!",5,440);
		}
		g.setColor(Color.BLACK);
		g.setFont(font2);
		g.drawString("-----------------------------------------------", 85, 305);
		g.drawString("Zombie", 20, 325);
		g.setFont(font4);
		g.drawString("Controls : ", 20, 345);
		g.setFont(font3);
		g.drawString("Left = 'Q' | Right = 'D'", 174, 365);
		g.drawString("Up = 'Z' | Down = 'S'", 185, 385);
		g.drawString("Change Weapon = 'A'", 90, 405);
		g.drawString("Use Weapon = 'F'", 270, 405);
		g.drawString("Life = ", 20, 435);
			if (Panel_game.player2.getLife() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player2.getLife() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player2.getLife(), 83, 435);
		g.setColor(Color.black);
		if (Panel_game.player2.getIndex() == 0) 
			g.drawString("Bombs Selected",200, 445);
		if (Panel_game.player2.getIndex() == 1) 
			g.drawString("Spikes Selected", 200, 445);
		if (Panel_game.player2.getIndex() == 2) 
			g.drawString("Rockets Selected", 200, 445);
		if (Panel_game.player2.getIndex() == 3) 
			g.drawString("Blocks Selected", 200, 445);
		g.drawString("Range = " + Panel_game.player2.getRange(), 415, 435);
		g.setFont(font4);
		g.drawString("Ammo : ", 20, 460);
		g.setFont(font3);
		g.drawString("Spike = ", 20, 485);
			if (Panel_game.player2.getAmmoSpike() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player2.getAmmoSpike() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player2.getAmmoSpike(), 90, 485);
		g.setColor(Color.black);
		g.drawString("Rocket = ", 215, 485);
			if (Panel_game.player2.getAmmoRocket() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player2.getAmmoRocket() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player2.getAmmoRocket(), 300, 485);
		g.setColor(Color.black);
		g.drawString("Block = ", 416, 485);
			if (Panel_game.player2.getAmmoBlock() != 0) {
				g.setColor(greeen);
			}
			if (Panel_game.player2.getAmmoBlock() == 0) {
				g.setColor(Color.red);
			}
		g.drawString("" + Panel_game.player2.getAmmoBlock(), 487, 485);
		g.setColor(Color.black);
		g.drawString("Position = (" + Panel_game.player2.getX() + ", " + Panel_game.player2.getY() + ")", 20, 505);	
	}	
}