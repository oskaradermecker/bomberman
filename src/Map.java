import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Map {

	public boolean isInCoordList(int i, int j, ArrayList<Coord> List) { //sends back 'true' if (i,j) is in CoordList. 
		boolean res = false;
		for (int a = 0; a < List.size(); a++) {
			if (List.get(a).getX() == i && List.get(a).getY() == j ) {
				res = true;
			}
		}	
		return res;
	}
	
	private ArrayList<Coord> PossiblePositions() {
		ArrayList<Coord> PossiblePositions = new ArrayList<Coord>();
		int l;
		int c;
		for (l = 1; l < 16; l++) {
			if (l == 1 || l == 15) {
				for (c = 3; c < 14; c++) {
					PossiblePositions.add(new Coord(l,c));
				}
			}
			else if (l == 2 || l == 14) {
				for (c = 3; c < 14; c=c+2) {
					PossiblePositions.add(new Coord(l,c));
				}				
			}
			else {
				if (l%2 == 0) {
					for (c = 1; c < 16; c=c+2) {
						PossiblePositions.add(new Coord(l,c));	
					}
				}	
				else {
					for (c = 1; c < 16; c++) {
						PossiblePositions.add(new Coord(l,c));
					}
				}
			}
		}
		return PossiblePositions;
	}
		
	private ArrayList<Coord> WoodList() {
		ArrayList<Coord> PossiblePositions = PossiblePositions();
		Random rd = new Random();
		ArrayList<Coord> WoodList = new ArrayList<Coord>();
		int index = 0;	
		while (WoodList.size() < 100) {
			index = rd.nextInt(PossiblePositions.size());
			WoodList.add(PossiblePositions.get(index));
			PossiblePositions.remove(index);
		}
		return WoodList;	
	}
	
	public Map() {
		ArrayList<Coord> WoodList = WoodList();
		//Adds elements to the map
		int i;
		int j;
		for (i = 0; i < 17; i++){
			Panel_game.grid.add(new ArrayList<Cell>());
			for (j = 0; j < 17; j++) {
				if (i == 0 || i == 16) {
					new Bedrock(i,j);
				}
				else {
					if (j == 0 || j == 16) {
						new Bedrock(i,j);
					}
					else if (i > 1 && j > 1 && i < 15 && j < 15 && i%2 == 0 && j%2 == 0){
						new Bedrock(i,j);
					}
					else {
						if ( isInCoordList(i,j,WoodList) ) {
							new Wood(i,j);
						}
						else {
							new Stone(i,j);
						}
					}
				}	
			}
		}	
	}
	
	public void draw(Graphics g) {
		for (ArrayList<Cell> column: Panel_game.grid) {
			for (Cell cell: column) {
				cell.draw(g);
			}
		}	
	}
}