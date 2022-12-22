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

		int startPositionRow;
		int startPositionColumn;
		
		int exitPositionRow;
		int exitPositionColumn;

		char[][] labyrinthOne = buildLabyrinthOne(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);
		char[][] labyrinthTwo = buildLabyrinthTwo(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_LEFT, SIGN_EXIT);
		char[][] labyrinthThree = buildLabyrinthThree(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);

		drawLabyrinth(labyrinthTwo);

		// BB8 Position
		startPositionColumn = determineBB8startPositionColumn(labyrinthTwo, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT,
				BB8_DIRECTION_UP, BB8_DIRECTION_DOWN);

		System.out.println(startPositionColumn);

		startPositionRow = determineBB8startPositionRow(labyrinthTwo, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT,
				BB8_DIRECTION_UP, BB8_DIRECTION_DOWN);

		System.out.println(startPositionRow);
		
		
		// Exit Position
		exitPositionColumn = determineexitPositionColumn(labyrinthTwo, SIGN_EXIT);

		System.out.println(exitPositionColumn);

		exitPositionRow = determineexitPositionRow(labyrinthTwo, SIGN_EXIT);

		System.out.println(exitPositionRow);

	}

	// Position von BB8 (X-Achse)
	public static int determineBB8startPositionColumn(char[][] labyrinth, char BB8_DIRECTION_RIGHT, char BB8_DIRECTION_LEFT,
			char BB8_DIRECTION_UP, char BB8_DIRECTION_DOWN) {

		int posColumn = -1;

		// First row
		for (int i = 0; i < labyrinth[0].length; i++) {
			if (labyrinth[0][i] == BB8_DIRECTION_RIGHT || labyrinth[0][i] == BB8_DIRECTION_LEFT
					|| labyrinth[0][i] == BB8_DIRECTION_UP || labyrinth[0][i] == BB8_DIRECTION_DOWN) {

				posColumn = 0;
			}
		}

		// First column
		if (posColumn == -1) {
			for (int i = 0; i < labyrinth.length; i++) {
				if (labyrinth[i][0] == BB8_DIRECTION_RIGHT || labyrinth[i][0] == BB8_DIRECTION_LEFT
						|| labyrinth[i][0] == BB8_DIRECTION_UP || labyrinth[i][0] == BB8_DIRECTION_DOWN) {

					posColumn = i;

				}
			}
		}

		// Last column
		if (posColumn == -1) {
			for (int i = 0; i < labyrinth.length; i++) {

				int lastCurrentRowIndex = labyrinth[i].length - 1;

				if (labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_RIGHT
						|| labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_LEFT
						|| labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_UP
						|| labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_DOWN) {

					posColumn = i;

				}
			}
		}

		// Last row
		if (posColumn == -1) {
			for (int i = 0; i < labyrinth[labyrinth.length - 1].length; i++) {

				int lastRowIndex = labyrinth.length - 1;

				if (labyrinth[lastRowIndex][i] == BB8_DIRECTION_RIGHT
						|| labyrinth[lastRowIndex][i] == BB8_DIRECTION_LEFT
						|| labyrinth[lastRowIndex][i] == BB8_DIRECTION_UP
						|| labyrinth[lastRowIndex][i] == BB8_DIRECTION_DOWN) {

					posColumn = lastRowIndex;

				}
			}
		}

		return posColumn;

	}

	// Position von BB8 (Y-Achse)
	public static int determineBB8startPositionRow(char[][] labyrinth, char BB8_DIRECTION_RIGHT, char BB8_DIRECTION_LEFT,
			char BB8_DIRECTION_UP, char BB8_DIRECTION_DOWN) {

		int posRow = -1;

		// First row
		for (int i = 0; i < labyrinth[0].length; i++) {
			if (labyrinth[0][i] == BB8_DIRECTION_RIGHT || labyrinth[0][i] == BB8_DIRECTION_LEFT
					|| labyrinth[0][i] == BB8_DIRECTION_UP || labyrinth[0][i] == BB8_DIRECTION_DOWN) {

				posRow = i;
			}
		}

		// First column
		if (posRow == -1) {
			for (int i = 0; i < labyrinth.length; i++) {
				if (labyrinth[i][0] == BB8_DIRECTION_RIGHT || labyrinth[i][0] == BB8_DIRECTION_LEFT
						|| labyrinth[i][0] == BB8_DIRECTION_UP || labyrinth[i][0] == BB8_DIRECTION_DOWN) {

					posRow = 0;

				}
			}
		}

		// Last column

		if (posRow == -1) {
			for (int i = 0; i < labyrinth.length; i++) {

				int lastCurrentRowIndex = labyrinth[i].length - 1;

				if (labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_RIGHT
						|| labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_LEFT
						|| labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_UP
						|| labyrinth[i][lastCurrentRowIndex] == BB8_DIRECTION_DOWN) {

					posRow = lastCurrentRowIndex;

				}
			}
		}

		// Last row
		if (posRow == -1) {
			for (int i = 0; i < labyrinth[labyrinth.length - 1].length; i++) {

				int lastRowIndex = labyrinth.length - 1;

				if (labyrinth[lastRowIndex][i] == BB8_DIRECTION_RIGHT
						|| labyrinth[lastRowIndex][i] == BB8_DIRECTION_LEFT
						|| labyrinth[lastRowIndex][i] == BB8_DIRECTION_UP
						|| labyrinth[lastRowIndex][i] == BB8_DIRECTION_DOWN) {

					posRow = i;

				}
			}
		}

		return posRow;

	}

	// Position von Exit (X-Achse)
	public static int determineexitPositionColumn(char[][] labyrinth, char SIGN_EXIT) {

		int posColumn = -1;

		// First row
		for (int i = 0; i < labyrinth[0].length; i++) {
			if (labyrinth[0][i] == SIGN_EXIT) {

				posColumn = 0;
			}
		}

		// First column
		if (posColumn == -1) {
			for (int i = 0; i < labyrinth.length; i++) {
				if (labyrinth[i][0] == SIGN_EXIT) {

					posColumn = i;

				}
			}
		}

		// Last column
		if (posColumn == -1) {
			for (int i = 0; i < labyrinth.length; i++) {

				int lastCurrentRowIndex = labyrinth[i].length - 1;

				if (labyrinth[i][lastCurrentRowIndex] == SIGN_EXIT) {

					posColumn = i;

				}
			}
		}

		// Last row
		if (posColumn == -1) {
			for (int i = 0; i < labyrinth[labyrinth.length - 1].length; i++) {

				int lastRowIndex = labyrinth.length - 1;

				if (labyrinth[lastRowIndex][i] == SIGN_EXIT) {

					posColumn = lastRowIndex;

				}
			}
		}

		return posColumn;

	}

	// Position von Exit (Y-Achse)
	public static int determineexitPositionRow(char[][] labyrinth, char SIGN_EXIT) {

		int posRow = -1;

		// First row
		for (int i = 0; i < labyrinth[0].length; i++) {
			if (labyrinth[0][i] == SIGN_EXIT) {

				posRow = i;
			}
		}

		// First column
		if (posRow == -1) {
			for (int i = 0; i < labyrinth.length; i++) {
				if (labyrinth[i][0] == SIGN_EXIT) {

					posRow = 0;

				}
			}
		}

		// Last column

		if (posRow == -1) {
			for (int i = 0; i < labyrinth.length; i++) {

				int lastCurrentRowIndex = labyrinth[i].length - 1;

				if (labyrinth[i][lastCurrentRowIndex] == SIGN_EXIT) {

					posRow = lastCurrentRowIndex;

				}
			}
		}

		// Last row
		if (posRow == -1) {
			for (int i = 0; i < labyrinth[labyrinth.length - 1].length; i++) {

				int lastRowIndex = labyrinth.length - 1;

				if (labyrinth[lastRowIndex][i] == SIGN_EXIT) {

					posRow = i;

				}
			}
		}

		return posRow;

	}

	public static char[][] buildLabyrinthThree(char signWall, char signPath, char signBb8, char signExit) {
		char wall = signWall;
		char path = signPath;

		char[][] labyrinth = {
				{ wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 0. Zeile
				{ wall, wall, path, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 1. Zeile
				{ wall, wall, path, wall, wall, wall, path, path, path, path, path, path, path, path, path, path,
						wall }, // 2. Zeile
				{ wall, wall, path, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, path,
						wall }, // 3. Zeile
				{ wall, path, path, path, path, path, path, path, path, path, path, path, path, path, path, path,
						wall }, // 4. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 5. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, path, path, wall, wall, wall,
						wall }, // 6. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 7. Zeile
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, path, path, path,
						wall }, // 8. Zeile
				{ wall, path, path, path, path, path, path, path, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 9. Zeile
				{ wall, path, wall, path, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 10. Zeile
				{ wall, path, wall, path, path, path, path, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 11. Zeile
				{ wall, path, wall, path, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 12. Zeile
				{ wall, wall, wall, path, wall, path, path, path, path, path, path, wall, path, wall, wall, wall,
						wall }, // 13. Zeile
				{ path, path, path, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, wall, wall,
						wall }, // 14. Zeile
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 15. Zeile

		};

		labyrinth[14][0] = signBb8;
		labyrinth[0][2] = signExit;

		return labyrinth;
	}

	public static char[][] buildLabyrinthTwo(char signWall, char signPath, char signBb8, char signExit) {
		char wall = signWall;
		char path = signPath;

		char[][] labyrinth = { { wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall }, // 0. Zeile
				{ path, path, path, path, wall, path, path, path, path, path, wall }, // 1. Zeile
				{ wall, path, wall, wall, wall, wall, wall, path, wall, path, wall }, // 2. Zeile
				{ wall, path, path, path, path, path, wall, path, wall, path, wall }, // 3. Zeile
				{ wall, path, wall, path, wall, path, wall, wall, wall, path, wall }, // 4. Zeile
				{ wall, path, wall, path, wall, path, path, path, wall, path, wall }, // 5. Zeile
				{ wall, path, wall, path, wall, wall, wall, wall, wall, path, wall }, // 6. Zeile
				{ wall, path, wall, path, path, path, path, path, path, path, wall }, // 7. Zeile
				{ wall, path, wall, wall, wall, path, wall, wall, wall, path, wall }, // 8. Zeile
				{ wall, path, wall, path, path, path, wall, path, path, path, path }, // 9. Zeile
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall } // 10. Zeile
		};

		// BB-8 Startposition
		labyrinth[9][10] = signBb8;
		// Exit Position
		labyrinth[1][0] = signExit;

		return labyrinth;
	}

	public static char[][] buildLabyrinthOne(char signWall, char signPath, char signBb8, char signExit) {
		char wall = signWall;
		char path = signPath;

		char[][] labyrinth = { { wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall }, // 0 Zeile
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
