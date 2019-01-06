import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class KeyboardListener implements KeyListener {
	private static boolean gauche1, gauche2;
	private static boolean droite1, droite2;
	private static boolean haut1, haut2;
	private static boolean bas1, bas2;
	private static boolean pOn, bOn;
	private static boolean pOff = true, wOff = true;
	static boolean stop = false;
	Frame f;
	
	public KeyboardListener(Frame f){
		this.f=f;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			KeyboardListener.gauche1 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			KeyboardListener.droite1 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			KeyboardListener.haut1 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			KeyboardListener.bas1 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			KeyboardListener.gauche2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			KeyboardListener.droite2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			KeyboardListener.haut2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			KeyboardListener.bas2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_M) {
			KeyboardListener.pOn = false;
			KeyboardListener.pOff = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F) {
			KeyboardListener.bOn = false;
			KeyboardListener.wOff = true;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE && stop == false){
			Panel_game.getTimer().stop();
			JOptionPane.showMessageDialog(f, "The game is on Pause. \n Click OK to continue playing !", "Pause", JOptionPane.INFORMATION_MESSAGE);
			Panel_game.getTimer().start();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			KeyboardListener.gauche1 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			KeyboardListener.droite1 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			KeyboardListener.haut1 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			KeyboardListener.bas1 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			KeyboardListener.gauche2 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			KeyboardListener.droite2 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			KeyboardListener.haut2 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			KeyboardListener.bas2 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_M) {
			pOn = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F) {
			bOn = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_L) {
			Panel_game.player1.addIndex();
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Panel_game.player2.addIndex();
		}
	}

	public static boolean getGauche1() {
		return KeyboardListener.gauche1;
	}
	public static boolean getDroite1() {
		return KeyboardListener.droite1;
	}
	public static boolean getHaut1() {
		return KeyboardListener.haut1;
	}
	public static boolean getBas1() {
		return KeyboardListener.bas1;
	}
	public static boolean getGauche2() {
		return KeyboardListener.gauche2;
	}
	public static boolean getDroite2() {
		return KeyboardListener.droite2;
	}
	public static boolean getHaut2() {
		return KeyboardListener.haut2;
	}
	public static boolean getBas2() {
		return KeyboardListener.bas2;
	}
	public static boolean getpOn() {
		return KeyboardListener.pOn;
	}
	public static boolean getpOff() {
		return KeyboardListener.pOff;
	}
	public static void setpOff(boolean p) {
		KeyboardListener.pOff = p;
	}
	public static void setwOff(boolean b) {
		KeyboardListener.wOff = b;
	}
	public static boolean getbOn() {
		return KeyboardListener.bOn;
	}
	public static boolean getwOff() {
		return KeyboardListener.wOff;
	}
}