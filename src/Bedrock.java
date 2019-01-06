import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bedrock extends Cell {
	
	public Bedrock(int x, int y) {
		super(x,y); // calls the constructor of Cell(x,y)
		this.Bonus = false;
		this.Crossable = false;
		this.Destroyable = false;
		this.Hurt = false;
		try {
			this.img = ImageIO.read(new File("Bedrock.png"));
		} catch (IOException e1) {
			this.img = null;
			System.out.println("Impossible to read file Bedrock.png");
		}	
	}
}
