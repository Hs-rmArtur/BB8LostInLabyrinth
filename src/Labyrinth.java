public class Labyrinth {
	public static void main(String[] args) throws InterruptedException {

		// Deklaration
		final char BB8_DIRECTION_RIGHT = '>';
		final char BB8_DIRECTION_LEFT = '<';
		final char BB8_DIRECTION_UP = '^';
		final char BB8_DIRECTION_DOWN = 'v';
		final char SIGN_WALL = '#';
		final char SIGN_PATH = ' ';
		final char SIGN_EXIT = 'E';

		int[] bb8Position; // [0] = Column [1] = Row
		char currentDirection;

//		char[][] labyrinthOne = buildLabyrinthOne(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);
//		char[][] labyrinthTwo = buildLabyrinthTwo(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_LEFT, SIGN_EXIT);
		char[][] labyrinthThree = buildLabyrinthThree(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);

		currentDirection = BB8_DIRECTION_RIGHT;
		bb8Position = determineBB8startPosition(labyrinthThree, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
				BB8_DIRECTION_DOWN);

		findWayThroughLabyrinth(labyrinthThree, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
				BB8_DIRECTION_DOWN, SIGN_PATH, SIGN_WALL, SIGN_EXIT, currentDirection, bb8Position);

	}

	// 0.CheckIfExit
	// 1. CheckIfRightWall --> false --> turn right --> step
	// 2. CheckIfFrontWall --> false --> step
	// 3. 1 + 2 true --> turnLeft --> repeat 0, 1, 2, 3....

	public static void findWayThroughLabyrinth(char[][] labyrinth, final char BB8_DIRECTION_RIGHT,
			final char BB8_DIRECTION_LEFT, final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN,
			final char SIGN_PATH, final char SIGN_WALL, final char SIGN_EXIT, char currentDirection,
			int[] currentPosition) throws InterruptedException {

		boolean isExit = false;
		boolean isExitRight = false;
		boolean isExitFront = false;

		boolean isWallRight = false;
		boolean isWallFront = false;

		while (isExit == false) {

			if (!isExit) {

				isWallRight = checkIfWallRight(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
						BB8_DIRECTION_DOWN, currentPosition, SIGN_WALL, currentDirection);

				isWallFront = checkIfWallFront(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
						BB8_DIRECTION_DOWN, currentPosition, SIGN_WALL, currentDirection);

				isExitRight = checkIfExit(labyrinth, SIGN_EXIT, currentPosition, currentDirection, BB8_DIRECTION_RIGHT,
						BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN, 'r');

				isExitFront = checkIfExit(labyrinth, SIGN_EXIT, currentPosition, currentDirection, BB8_DIRECTION_RIGHT,
						BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN, 'f');

				if (isExitRight) {

					turnRight(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
							currentPosition, currentDirection);
					
					makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
							SIGN_PATH, currentPosition, currentDirection);

					drawLabyrinth(labyrinth);

					Thread.sleep(500);

					isExit = true;

				}

				if (isExitFront) {

					makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
							SIGN_PATH, currentPosition, currentDirection);

					drawLabyrinth(labyrinth);

					Thread.sleep(500);

					isExit = true;

				}

				if (!isExitRight && !isExitFront) {

					if (isWallRight && isWallFront) {

						currentDirection = turnLeft(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT,
								BB8_DIRECTION_UP, BB8_DIRECTION_DOWN, currentPosition, currentDirection);

						drawLabyrinth(labyrinth);

						Thread.sleep(500);

					}

					if (!isWallRight) {

						currentDirection = turnRight(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT,
								BB8_DIRECTION_UP, BB8_DIRECTION_DOWN, currentPosition, currentDirection);
						
						drawLabyrinth(labyrinth);
						Thread.sleep(500);
						
						makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
								BB8_DIRECTION_DOWN, SIGN_PATH, currentPosition, currentDirection);

						drawLabyrinth(labyrinth);

						Thread.sleep(500);

					}

					if (isWallRight && !isWallFront) {

						makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
								BB8_DIRECTION_DOWN, SIGN_PATH, currentPosition, currentDirection);

						drawLabyrinth(labyrinth);

						Thread.sleep(500);

					}

				}

			}

		}

	}

	public static void makeStep(char[][] labyrinth, final char BB8_DIRECTION_RIGHT, final char BB8_DIRECTION_LEFT,
			final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN, final char SIGN_PATH, int[] bb8Position,
			char currentDirection) {

		int bb8PositionColumn = bb8Position[0];
		int bb8PositionRow = bb8Position[1];

		if (currentDirection == BB8_DIRECTION_RIGHT) {

			labyrinth[bb8PositionColumn][bb8PositionRow + 1] = BB8_DIRECTION_RIGHT;
			labyrinth[bb8PositionColumn][bb8PositionRow] = SIGN_PATH;

			bb8Position[1] = bb8PositionRow + 1;

		} else if (currentDirection == BB8_DIRECTION_LEFT) {

			labyrinth[bb8PositionColumn][bb8PositionRow - 1] = BB8_DIRECTION_LEFT;
			labyrinth[bb8PositionColumn][bb8PositionRow] = SIGN_PATH;

			bb8Position[1] = bb8PositionRow - 1;

		} else if (currentDirection == BB8_DIRECTION_UP) {

			labyrinth[bb8PositionColumn - 1][bb8PositionRow] = BB8_DIRECTION_UP;
			labyrinth[bb8PositionColumn][bb8PositionRow] = SIGN_PATH;

			bb8Position[0] = bb8PositionColumn - 1;

		} else if (currentDirection == BB8_DIRECTION_DOWN) {

			labyrinth[bb8PositionColumn + 1][bb8PositionRow] = BB8_DIRECTION_DOWN;
			labyrinth[bb8PositionColumn][bb8PositionRow] = SIGN_PATH;

			bb8Position[0] = bb8PositionColumn + 1;
		}

	}

	public static char turnLeft(char[][] labyrinth, final char BB8_DIRECTION_RIGHT, final char BB8_DIRECTION_LEFT,
			final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN, int[] bb8Position, char currentDirection) {

		int bb8PositionColumn = bb8Position[0];
		int bb8PositionRow = bb8Position[1];
		char newDirection = ' ';

		if (currentDirection == BB8_DIRECTION_RIGHT) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_UP;

			newDirection = BB8_DIRECTION_UP;

		} else if (currentDirection == BB8_DIRECTION_LEFT) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_DOWN;
			newDirection = BB8_DIRECTION_DOWN;

		} else if (currentDirection == BB8_DIRECTION_UP) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_LEFT;
			newDirection = BB8_DIRECTION_LEFT;

		} else if (currentDirection == BB8_DIRECTION_DOWN) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_RIGHT;
			newDirection = BB8_DIRECTION_RIGHT;
		}

		return newDirection;
	}

	public static char turnRight(char[][] labyrinth, final char BB8_DIRECTION_RIGHT, final char BB8_DIRECTION_LEFT,
			final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN, int[] bb8Position, char currentDirection) {

		int bb8PositionColumn = bb8Position[0];
		int bb8PositionRow = bb8Position[1];
		char newDirection = ' ';

		if (currentDirection == BB8_DIRECTION_RIGHT) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_DOWN;

			newDirection = BB8_DIRECTION_DOWN;

		} else if (currentDirection == BB8_DIRECTION_LEFT) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_UP;
			newDirection = BB8_DIRECTION_UP;

		} else if (currentDirection == BB8_DIRECTION_UP) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_RIGHT;
			newDirection = BB8_DIRECTION_RIGHT;

		} else if (currentDirection == BB8_DIRECTION_DOWN) {

			labyrinth[bb8PositionColumn][bb8PositionRow] = BB8_DIRECTION_LEFT;
			newDirection = BB8_DIRECTION_LEFT;
		}

		return newDirection;
	}

	public static boolean checkIfWallFront(char[][] labyrinth, final char BB8_DIRECTION_RIGHT,
			final char BB8_DIRECTION_LEFT, final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN,
			int[] bb8Position, final char SIGN_WALL, char currentDirection) {

		int bb8PositionColumn = bb8Position[0];
		int bb8PositionRow = bb8Position[1];
		boolean isWall = false;

		if (currentDirection == BB8_DIRECTION_RIGHT) {
			if (labyrinth[bb8PositionColumn][bb8PositionRow + 1] == SIGN_WALL) {
				isWall = true;
			}
		} else if (currentDirection == BB8_DIRECTION_LEFT) {
			if (labyrinth[bb8PositionColumn][bb8PositionRow - 1] == SIGN_WALL) {
				isWall = true;
			}
		} else if (currentDirection == BB8_DIRECTION_UP) {
			if (labyrinth[bb8PositionColumn - 1][bb8PositionRow] == SIGN_WALL) {
				isWall = true;
			}
		} else if (currentDirection == BB8_DIRECTION_DOWN) {
			if (labyrinth[bb8PositionColumn + 1][bb8PositionRow] == SIGN_WALL) {
				isWall = true;
			}
		}

		return isWall;
	}

	public static boolean checkIfWallRight(char[][] labyrinth, final char BB8_DIRECTION_RIGHT,
			final char BB8_DIRECTION_LEFT, final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN,
			int[] bb8Position, final char SIGN_WALL, char currentDirection) {

		int bb8PositionColumn = bb8Position[0];
		int bb8PositionRow = bb8Position[1];

		boolean isWall = false;

		if (currentDirection == BB8_DIRECTION_RIGHT) {
			if (labyrinth[bb8PositionColumn + 1][bb8PositionRow] == SIGN_WALL) {
				isWall = true;
			}
		} else if (currentDirection == BB8_DIRECTION_LEFT) {
			if (labyrinth[bb8PositionColumn - 1][bb8PositionRow] == SIGN_WALL) {
				isWall = true;
			}
		} else if (currentDirection == BB8_DIRECTION_UP) {
			if (labyrinth[bb8PositionColumn][bb8PositionRow + 1] == SIGN_WALL) {
				isWall = true;
			}
		} else if (currentDirection == BB8_DIRECTION_DOWN) {
			if (labyrinth[bb8PositionColumn][bb8PositionRow - 1] == SIGN_WALL) {
				isWall = true;
			}
		}

		return isWall;
	}

	public static boolean checkIfExit(char[][] labyrinth, final char SIGN_EXIT, int[] currentPosition,
			char currentDirection, final char BB8_DIRECTION_RIGHT, final char BB8_DIRECTION_LEFT,
			final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN, char rightOrFront) {

		int bb8PositionColumn = currentPosition[0];
		int bb8PositionRow = currentPosition[1];

		boolean isExit = false;

		// Right

		if (rightOrFront == 'r') {

			if (currentDirection == BB8_DIRECTION_RIGHT) {
				if (labyrinth[bb8PositionColumn + 1][bb8PositionRow] == SIGN_EXIT) {
					isExit = true;
				}
			} else if (currentDirection == BB8_DIRECTION_LEFT) {
				if (labyrinth[bb8PositionColumn - 1][bb8PositionRow] == SIGN_EXIT) {
					isExit = true;
				}
			} else if (currentDirection == BB8_DIRECTION_UP) {
				if (labyrinth[bb8PositionColumn][bb8PositionRow + 1] == SIGN_EXIT) {
					isExit = true;
				}
			} else if (currentDirection == BB8_DIRECTION_DOWN) {
				if (labyrinth[bb8PositionColumn][bb8PositionRow - 1] == SIGN_EXIT) {
					isExit = true;
				}
			}

		} else {

			// Front

			if (currentDirection == BB8_DIRECTION_RIGHT) {
				if (labyrinth[bb8PositionColumn][bb8PositionRow + 1] == SIGN_EXIT) {
					isExit = true;
				}
			} else if (currentDirection == BB8_DIRECTION_LEFT) {
				if (labyrinth[bb8PositionColumn][bb8PositionRow - 1] == SIGN_EXIT) {
					isExit = true;
				}
			} else if (currentDirection == BB8_DIRECTION_UP) {
				if (labyrinth[bb8PositionColumn - 1][bb8PositionRow] == SIGN_EXIT) {
					isExit = true;
				}
			} else if (currentDirection == BB8_DIRECTION_DOWN) {
				if (labyrinth[bb8PositionColumn + 1][bb8PositionRow] == SIGN_EXIT) {
					isExit = true;
				}
			}

		}

		return isExit;

	}

	// Position von BB8 (X-Achse)
	public static int[] determineBB8startPosition(char[][] labyrinth, char BB8_DIRECTION_RIGHT, char BB8_DIRECTION_LEFT,
			char BB8_DIRECTION_UP, char BB8_DIRECTION_DOWN) {

		int[] BB8Position = new int[2];

		int posColumn = -1;
		int posRow = -1;

		// First row
		for (int i = 0; i < labyrinth[0].length; i++) {
			if (labyrinth[0][i] == BB8_DIRECTION_RIGHT || labyrinth[0][i] == BB8_DIRECTION_LEFT
					|| labyrinth[0][i] == BB8_DIRECTION_UP || labyrinth[0][i] == BB8_DIRECTION_DOWN) {

				posColumn = 0;
				posRow = i;
			}
		}

		// First column
		if (posColumn == -1) {
			for (int i = 0; i < labyrinth.length; i++) {
				if (labyrinth[i][0] == BB8_DIRECTION_RIGHT || labyrinth[i][0] == BB8_DIRECTION_LEFT
						|| labyrinth[i][0] == BB8_DIRECTION_UP || labyrinth[i][0] == BB8_DIRECTION_DOWN) {

					posColumn = i;
					posRow = 0;

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
					posRow = lastCurrentRowIndex;

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
					posRow = i;

				}
			}
		}

		BB8Position[0] = posColumn;
		BB8Position[1] = posRow;

		return BB8Position;

	}

	/*
	 * // Position von Exit (X-Achse) public static int
	 * determineExitPositionColumn(char[][] labyrinth, char SIGN_EXIT) {
	 * 
	 * int posColumn = -1;
	 * 
	 * // First row for (int i = 0; i < labyrinth[0].length; i++) { if
	 * (labyrinth[0][i] == SIGN_EXIT) {
	 * 
	 * posColumn = 0; } }
	 * 
	 * // First column if (posColumn == -1) { for (int i = 0; i < labyrinth.length;
	 * i++) { if (labyrinth[i][0] == SIGN_EXIT) {
	 * 
	 * posColumn = i;
	 * 
	 * } } }
	 * 
	 * // Last column if (posColumn == -1) { for (int i = 0; i < labyrinth.length;
	 * i++) {
	 * 
	 * int lastCurrentRowIndex = labyrinth[i].length - 1;
	 * 
	 * if (labyrinth[i][lastCurrentRowIndex] == SIGN_EXIT) {
	 * 
	 * posColumn = i;
	 * 
	 * } } }
	 * 
	 * // Last row if (posColumn == -1) { for (int i = 0; i <
	 * labyrinth[labyrinth.length - 1].length; i++) {
	 * 
	 * int lastRowIndex = labyrinth.length - 1;
	 * 
	 * if (labyrinth[lastRowIndex][i] == SIGN_EXIT) {
	 * 
	 * posColumn = lastRowIndex;
	 * 
	 * } } }
	 * 
	 * return posColumn;
	 * 
	 * }
	 * 
	 * // Position von Exit (Y-Achse) public static int
	 * determineExitPositionRow(char[][] labyrinth, char SIGN_EXIT) {
	 * 
	 * int posRow = -1;
	 * 
	 * // First row for (int i = 0; i < labyrinth[0].length; i++) { if
	 * (labyrinth[0][i] == SIGN_EXIT) {
	 * 
	 * posRow = i; } }
	 * 
	 * // First column if (posRow == -1) { for (int i = 0; i < labyrinth.length;
	 * i++) { if (labyrinth[i][0] == SIGN_EXIT) {
	 * 
	 * posRow = 0;
	 * 
	 * } } }
	 * 
	 * // Last column
	 * 
	 * if (posRow == -1) { for (int i = 0; i < labyrinth.length; i++) {
	 * 
	 * int lastCurrentRowIndex = labyrinth[i].length - 1;
	 * 
	 * if (labyrinth[i][lastCurrentRowIndex] == SIGN_EXIT) {
	 * 
	 * posRow = lastCurrentRowIndex;
	 * 
	 * } } }
	 * 
	 * // Last row if (posRow == -1) { for (int i = 0; i <
	 * labyrinth[labyrinth.length - 1].length; i++) {
	 * 
	 * int lastRowIndex = labyrinth.length - 1;
	 * 
	 * if (labyrinth[lastRowIndex][i] == SIGN_EXIT) {
	 * 
	 * posRow = i;
	 * 
	 * } } }
	 * 
	 * return posRow;
	 * 
	 * }
	 * 
	 */

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
