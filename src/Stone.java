import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Stone extends Cell {
	
	public Stone(int x, int y) {
		
		super(x,y); // calls the constructor of Cell(x,y)
		this.Bonus = false;
		this.Crossable = true;
		this.Destroyable = true;
		this.WithBomb = false;
		this.Hurt = false;
		try {
			this.img = ImageIO.read(new File("Stone.png"));
		} catch (IOException e1) {
			this.img = null;
			System.out.println("Impossible to read file Stone.png");
		}
	}
}
