public class Coord {
	
    private int line;
    private int column;
   
    public Coord(int x, int y) { 
        this.line = x;
        this.column = y;
    }
    
    public int getX() {
    	return this.line;
    }
    
    public int getY() {
    	return this.column;
    }
}
