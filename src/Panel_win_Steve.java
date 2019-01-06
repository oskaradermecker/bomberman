import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel_win_Steve extends JPanel {
	
	private Image img;
	
	//Steve won
	public Panel_win_Steve() {
		try {
			img = ImageIO.read(new File("Steve_won.png"));
		} catch (IOException e) {
			img = null;
			System.out.println("Impossible to read file Steve_won.png");
		}
	}
	
	public void paintComponent(Graphics g){
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);    
	}
}
