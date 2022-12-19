package bb8LostInLabyrinth;

import labyrinths.LabyrinthLostInSpace;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LabyrinthLostInSpace LLostInSpace = new LabyrinthLostInSpace();

		int[] newPosition = new int[2];

		LLostInSpace.buildLabyrinth();

		LLostInSpace.drawLabyrinth();

		newPosition[0] = 15;
		newPosition[1] = 1;
		LLostInSpace.setNewPosition(newPosition, '>');
		LLostInSpace.drawLabyrinth();

		newPosition[0] = 15;
		newPosition[1] = 2;
		LLostInSpace.setNewPosition(newPosition, '>');
		LLostInSpace.drawLabyrinth();
		
		newPosition[0] = 15;
		newPosition[1] = 3;
		LLostInSpace.setNewPosition(newPosition, '^');
		LLostInSpace.drawLabyrinth();
	}

}
