public class Labyrinth {
	public static void main(String[] args) {

		// Deklaration
		final char BB8_DIRECTION_RIGHT = '>';
		final char BB8_DIRECTION_LEFT = '<';
		final char BB8_DIRECTION_UP = '^';
		final char BB8_DIRECTION_DOWN = 'v';
		final char SIGN_WALL = '#';
		final char SIGN_PATH = ' ';
		final char SIGN_EXIT = 'E';

		char[][] labyrinthOne = buildLabyrinthOne(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);
		char[][] labyrinthTwo = buildLabyrinthTwo(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_LEFT, SIGN_EXIT);
		char[][] labyrinthThree = buildLabyrinthThree(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);

		drawLabyrinth(labyrinthThree);

	}

	public static char[][] buildLabyrinthThree(char signWall, char signPath, char signBb8, char signExit) {
		char wall = signWall;
		char path = signPath;

		char[][] labyrinth = {
				{ wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall}, // 0. Zeile
				{ wall, wall, path, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall}, // 1. Zeile
				{ wall, wall, path, wall, wall, wall, path, path, path, path, path, path, path, path, path, path, wall}, // 2. Zeile
				{ wall, wall, path, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, path, wall}, // 3. Zeile
				{ wall, path, path, path, path, path, path, path, path, path, path, path, path, path, path, path, wall}, // 4. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall}, // 5. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, path, path, wall, wall, wall, wall}, // 6. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall, wall}, // 7. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, path, path, path, wall}, // 8. Zeile
				{ wall, path, path, path, path, path, path, path, path, wall, path, wall, path, wall, wall, wall, wall}, // 9. Zeile
				{ wall, path, wall, path, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall, wall}, // 10. Zeile
				{ wall, path, wall, path, path, path, path, wall, path, wall, path, wall, path, wall, wall, wall, wall}, // 11. Zeile
				{ wall, path, wall, path, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall, wall}, // 12. Zeile
				{ wall, wall, wall, path, wall, path, path, path, path, path, path, wall, path, wall, wall, wall, wall}, // 13. Zeile
				{ path, path, path, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, wall, wall, wall}, // 14. Zeile
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall}, // 15. Zeile


		};

		labyrinth[14][0] = signBb8;
		labyrinth[0][2] = signExit;

		return labyrinth;
	}

	public static char[][] buildLabyrinthTwo(char signWall, char signPath, char signBb8, char signExit) {
		char wall = signWall;
		char path = signPath;

		char[][] labyrinth = {
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall }, // 0. Zeile
				{ wall, path, path, path, wall, path, path, path, path, path, wall }, // 1. Zeile
				{ wall, path, wall, wall, wall, wall, wall, path, wall, path, wall }, // 2. Zeile
				{ wall, path, path, path, path, path, wall, path, wall, path, wall }, // 3. Zeile
				{ wall, path, wall, path, wall, path, wall, wall, wall, path, wall }, // 4. Zeile
				{ wall, path, wall, path, wall, path, path, path, wall, path, wall }, // 5. Zeile
				{ wall, path, wall, path, wall, wall, wall, wall, wall, path, wall }, // 6. Zeile
				{ wall, path, wall, path, path, path, path, path, path, path, wall }, // 7. Zeile
				{ wall, path, wall, wall, wall, path, wall, wall, wall, path, wall }, // 8. Zeile
				{ wall, path, wall, path, path, path, wall, path, path, path, wall }, // 9. Zeile
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall } // 10. Zeile
		};

		//BB-8 Startposition
		labyrinth[9][10] = signBb8;
		//Exit Position
		labyrinth[1][0] = signExit;

		return labyrinth;
	}

	public static char[][] buildLabyrinthOne(char signWall, char signPath, char signBb8, char signExit) {
		char wall = signWall;
		char path = signPath;

		char[][] labyrinth = {
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall }, // 0 Zeile
				{ path, path, path, path, path, path, wall, path, path, path, path, path }, // 1 Zeile
				{ wall, wall, wall, wall, wall, path, wall, path, wall, wall, wall, wall }, // 2 Zeile
				{ wall, path, path, path, path, path, wall, path, path, path, path, wall }, // 3 Zeile
				{ wall, path, wall, wall, wall, path, wall, wall, wall, wall, path, wall }, // 4 Zeile
				{ wall, path, wall, path, path, path, wall, path, path, path, path, wall }, // 5 Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, wall, wall, path, wall }, // 6 Zeile
				{ wall, path, path, path, path, path, path, wall, path, wall, path, wall }, // 7 Zeile
				{ wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, wall }, // 8 Zeile
				{ wall, path, path, path, path, path, path, path, path, path, path, wall }, // 9 Zeile
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall } // 10 Zeile
		};

		// Setzen von BB8 Startposition
		labyrinth[1][0] = signBb8;

		// Setzen von Exit Position
		labyrinth[1][11] = signExit;

		return labyrinth;
	}

	public static void drawLabyrinth(char[][] labyrinth) {
		for (int i = 0; i < labyrinth.length; i++) {
			for (int j = 0; j < labyrinth[i].length; j++) {
				System.out.print(labyrinth[i][j]);
			}
			System.out.println();
		}
	}
}
