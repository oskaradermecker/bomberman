import java.awt.GridLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	public Frame (int frame_number) {
		this.setTitle("BOMBERMAN");
		this.setSize(550,570);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		if (frame_number == 1) {
			Sound.playTempSound("sounds/intro.wav"); // plays this intro when the frame opens (stops when the frame is closed)
			this.setContentPane(new Panel_accueil(this));
		}
		if (frame_number == 2) {
			this.setSize(1100,570);
			this.setLocationRelativeTo(null);
			this.setLayout(new GridLayout(1,2));
			this.getContentPane().add(new Panel_game(this));
			this.getContentPane().add(new Board());
		}		
		if (frame_number == 3) 
			this.setContentPane(new Panel_win_Steve());
		if (frame_number == 4) 
			this.setContentPane(new Panel_win_Zombie());
		setVisible(true);
	}	
}
