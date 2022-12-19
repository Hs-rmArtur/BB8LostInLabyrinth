package labyrinths;

public class LabyrinthLostInSpace {
	char[][] labyrinth = new char[17][17];
	int[] position = new int[2];

	
	public void drawLabyrinth() {
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < labyrinth[i].length; j++) {
				System.out.print(labyrinth[i][j]);
			}
			System.out.println();

		}
	}
	
	public void setNewPosition(int positionY, int positionX, char direction) {
		labyrinth[position[0]][position[1]] = ' ';
		labyrinth[positionY][positionX] = direction;
		position[0] = positionY;
		position[1] = positionX;
	}
	
	public int[] getPosition() {
		return position;
	}

	private void setStartPoint() {
		labyrinth[15][0] = '>';
		position[0] = 15;
		position[1] = 0;
	}

	private void setExit() {
		labyrinth[0][2] = 'E';
	}

	public void buildLabyrinth() {
		setStartPoint();
		setExit();

		// line labyrinth [0]
		labyrinth[0][1] = '#';
		labyrinth[0][2] = 'E';
		labyrinth[0][3] = '#';
		for (int i = 5; i < 8; i++) {
			labyrinth[0][i] = '#';
		}

		// line labyrinth [1]
		labyrinth[1][1] = '#';
		labyrinth[1][3] = '#';
		labyrinth[1][5] = '#';
		labyrinth[1][7] = '#';

		// line labyrinth [2]
		labyrinth[2][1] = '#';
		labyrinth[2][3] = '#';
		labyrinth[2][5] = '#';
		for (int i = 7; i < 17; i++) {
			labyrinth[2][i] = '#';
		}

		// line labyrinth [3]
		labyrinth[3][1] = '#';
		labyrinth[3][3] = '#';
		labyrinth[3][5] = '#';
		labyrinth[3][16] = '#';

		// line labyrinth [4]
		labyrinth[4][0] = '#';
		labyrinth[4][1] = '#';
		for (int i = 3; i < 6; i++) {
			labyrinth[4][i] = '#';
		}

		for (int i = 7; i < 15; i++) {
			labyrinth[4][i] = '#';
		}

		labyrinth[4][16] = '#';

		// line labyrinth [5]
		labyrinth[5][0] = '#';
		labyrinth[5][16] = '#';

		// line labyrinth [6]
		labyrinth[6][0] = '#';
		for (int i = 2; i < 8; i++) {
			labyrinth[6][i] = '#';
		}

		for (int i = 9; i < 17; i++) {
			labyrinth[6][i] = '#';
		}

		// line labyrinth [7]
		labyrinth[7][0] = '#';
		labyrinth[7][2] = '#';
		labyrinth[7][7] = '#';
		labyrinth[7][9] = '#';
		labyrinth[7][13] = '#';

		// line labyrinth [8]
		labyrinth[8][0] = '#';
		labyrinth[8][2] = '#';
		labyrinth[8][7] = '#';
		labyrinth[8][9] = '#';
		labyrinth[8][11] = '#';
		for (int i = 13; i < 17; i++) {
			labyrinth[8][i] = '#';
		}

		// line labyrinth [9]
		labyrinth[9][0] = '#';
		for (int i = 2; i < 8; i++) {
			labyrinth[9][i] = '#';
		}

		labyrinth[9][9] = '#';
		labyrinth[9][11] = '#';
		labyrinth[9][16] = '#';

		// line labyrinth [10]
		labyrinth[10][0] = '#';
		labyrinth[10][9] = '#';
		labyrinth[10][11] = '#';
		for (int i = 13; i < 17; i++) {
			labyrinth[10][i] = '#';

		}

		// line labyrinth [11]
		labyrinth[11][0] = '#';
		labyrinth[11][2] = '#';
		for (int i = 4; i < 8; i++) {
			labyrinth[11][i] = '#';

		}
		labyrinth[11][9] = '#';
		labyrinth[11][11] = '#';
		labyrinth[11][13] = '#';

		// line labyrinth [12]
		labyrinth[12][0] = '#';
		labyrinth[12][2] = '#';
		for (int i = 7; i < 14; i++) {
			if (i % 2 != 0) {
				labyrinth[12][i] = '#';
			}
		}

		// line labyrinth [13]
		labyrinth[13][0] = '#';
		labyrinth[13][2] = '#';
		for (int i = 4; i < 8; i++) {
			labyrinth[13][i] = '#';
		}

		for (int i = 9; i < 14; i++) {
			if (i % 2 != 0) {
				labyrinth[13][i] = '#';
			}
		}

		// line labyrinth [14]
		for (int i = 0; i < 3; i++) {
			labyrinth[14][i] = '#';
		}

		labyrinth[14][4] = '#';
		labyrinth[14][11] = '#';
		labyrinth[14][13] = '#';

		// line labyrinth [15]
		for (int i = 4; i < 10; i++) {
			labyrinth[15][i] = '#';
		}

		labyrinth[15][11] = '#';
		labyrinth[15][13] = '#';

		// line labyrinth [16]
		for (int i = 0; i < 5; i++) {
			labyrinth[16][i] = '#';
		}

		for (int i = 9; i < 14; i++) {
			labyrinth[16][i] = '#';
		}

	}
}
