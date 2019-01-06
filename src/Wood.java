import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Wood extends Cell {
	
	public Wood(int x, int y) {
		
		super(x,y); // calls the constructor of Cell(x,y)
		this.Bonus = false;
		this.Crossable = false;
		this.Destroyable = true;
		this.Hurt = false;
		try {
			this.img = ImageIO.read(new File("Wood.png"));
		} catch (IOException e1) {
			this.img = null;
			System.out.println("Impossible to read file Wood.png");
		}
	}
}