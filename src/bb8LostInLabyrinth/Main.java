package bb8LostInLabyrinth;

import labyrinths.LabyrinthLostInSpace;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		LabyrinthLostInSpace LLostInSpace = new LabyrinthLostInSpace();

		int newPosY = 0;
		int newPosX = 0;

		LLostInSpace.buildLabyrinth();

		LLostInSpace.drawLabyrinth();

		newPosY = 15;
		newPosX = 1;
		LLostInSpace.setNewPosition(newPosY, newPosX, '>');
		LLostInSpace.drawLabyrinth();
		Thread.sleep(1000);

		newPosY = 15;
		newPosX = 2;
		LLostInSpace.setNewPosition(newPosY, newPosX, '>');
		LLostInSpace.drawLabyrinth();
		Thread.sleep(1000);

		newPosY = 15;
		newPosX = 3;
		LLostInSpace.setNewPosition(newPosY, newPosX, '^');
		LLostInSpace.drawLabyrinth();
		Thread.sleep(1000);
		
		newPosY = 14;
		newPosX = 3;
		LLostInSpace.setNewPosition(newPosY, newPosX, '^');
		LLostInSpace.drawLabyrinth();
		Thread.sleep(1000);
		
		newPosY = 13;
		newPosX = 3;
		LLostInSpace.setNewPosition(newPosY, newPosX, '^');
		LLostInSpace.drawLabyrinth();
		Thread.sleep(1000);
		
		newPosY = 12;
		newPosX = 3;
		LLostInSpace.setNewPosition(newPosY, newPosX, '^');
		LLostInSpace.drawLabyrinth();
		Thread.sleep(1000);
		
		newPosY = 11;
		newPosX = 3;
		LLostInSpace.setNewPosition(newPosY, newPosX, '^');
		LLostInSpace.drawLabyrinth();
	}

}
