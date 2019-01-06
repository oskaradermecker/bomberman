import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel_accueil extends JPanel implements MouseListener{

	private Image img;
	private Frame f;
	
	public Panel_accueil(Frame f) {
		Sound.playTempSound("sounds/intro.wav");
		this.f = f;
		try {
			img = ImageIO.read(new File("Background.png"));
		} catch (IOException e) {
			img = null;
			System.out.println("Impossible to read file Background.png");
		}
	this.addMouseListener(this);
	}
		
	public void paintComponent(Graphics g){
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);    
	}
	
	public void mouseClicked(MouseEvent event) {
		this.f.setVisible(false);
		new Frame(2);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
}