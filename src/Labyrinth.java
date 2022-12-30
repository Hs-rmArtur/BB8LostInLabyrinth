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

		char[][] labyrinthOne = buildLabyrinthOne(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);
//		char[][] labyrinthTwo = buildLabyrinthTwo(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_LEFT, SIGN_EXIT);
//		char[][] labyrinthThree = buildLabyrinthThree(SIGN_WALL, SIGN_PATH, BB8_DIRECTION_RIGHT, SIGN_EXIT);

		currentDirection = BB8_DIRECTION_RIGHT;
		bb8Position = determineBB8startPosition(labyrinthOne, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
				BB8_DIRECTION_DOWN);
		
		char[][] labyrinthMap = buildLabyrinthMap(labyrinthOne);

		findWayThroughLabyrinth(labyrinthOne, labyrinthMap, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
				BB8_DIRECTION_DOWN, SIGN_PATH, SIGN_WALL, SIGN_EXIT, currentDirection, bb8Position);
		
	}
	
	
	public static char[][] buildLabyrinthMap(char[][] labyrinth) {
		
		int columnLength = labyrinth.length;
		int rowLength = labyrinth[0].length;
		char[][] labyrinthMap = new char[columnLength][rowLength];
		
		return labyrinthMap;
		
	}
	
	public static void drawLabyrinthMap(char[][] labyrinthMap) {
		
		for (int i = 0; i < labyrinthMap.length; i++) {
			for (int j = 0; j < labyrinthMap[i].length; j++) {
				System.out.print(labyrinthMap[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void drawStepIntoMap(char[][] labyrinthMap, char currentDirection, int[] currentPosition) {
		int columnPosition = currentPosition[0];
		int rowPosition = currentPosition[1];
		labyrinthMap[columnPosition][rowPosition] = currentDirection;
	}

	public static void findWayThroughLabyrinth(char[][] labyrinth, char[][] labyrinthMap, final char BB8_DIRECTION_RIGHT,
			final char BB8_DIRECTION_LEFT, final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN,
			final char SIGN_PATH, final char SIGN_WALL, final char SIGN_EXIT, char currentDirection,
			int[] currentPosition) throws InterruptedException {

		boolean isExit = false;
		boolean isExitRight = false;
		boolean isExitFront = false;

		boolean isWallRight = false;
		boolean isWallFront = false;

		int sleepingTime = 500;
		
		// Counted steps of BB8 in the labyrinth
		int countedSteps = 0;
		
		
		drawLabyrinth(labyrinth);
		
		while (isExit == false) {

			isWallRight = checkIfWallRight(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
					BB8_DIRECTION_DOWN, currentPosition, SIGN_WALL, currentDirection);

			if (isWallRight) {
				isWallFront = checkIfWallFront(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
						BB8_DIRECTION_DOWN, currentPosition, SIGN_WALL, currentDirection);
			}

			isExitRight = checkIfExit(labyrinth, SIGN_EXIT, currentPosition, currentDirection, BB8_DIRECTION_RIGHT,
					BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN, 'r');

			if (!isExitRight) {
				isExitFront = checkIfExit(labyrinth, SIGN_EXIT, currentPosition, currentDirection, BB8_DIRECTION_RIGHT,
						BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN, 'f');
			}

			if (isExitRight) {

				turnRight(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
						currentPosition, currentDirection);

				makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
						SIGN_PATH, currentPosition, currentDirection);
				
				drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);
				
				countedSteps++;
				

				drawLabyrinth(labyrinth);

				Thread.sleep(sleepingTime);

				isExit = true;

			} else if (isExitFront) {

				makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
						SIGN_PATH, currentPosition, currentDirection);
				
				drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);
				
				countedSteps++;

				drawLabyrinth(labyrinth);

				Thread.sleep(sleepingTime);

				isExit = true;

			}

			if (!isExitRight && !isExitFront) {

				if (!isWallRight) {

					currentDirection = turnRight(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
							BB8_DIRECTION_DOWN, currentPosition, currentDirection);

					drawLabyrinth(labyrinth);
					Thread.sleep(sleepingTime);

					makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
							SIGN_PATH, currentPosition, currentDirection);
					
					drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);
					
					countedSteps++;

					drawLabyrinth(labyrinth);

					Thread.sleep(sleepingTime);

				} else if (isWallRight && !isWallFront) {

					makeStep(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP, BB8_DIRECTION_DOWN,
							SIGN_PATH, currentPosition, currentDirection);
					
					drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);
					
					countedSteps++;

					drawLabyrinth(labyrinth);

					Thread.sleep(sleepingTime);

				} else if (isWallRight && isWallFront) {

					currentDirection = turnLeft(labyrinth, BB8_DIRECTION_RIGHT, BB8_DIRECTION_LEFT, BB8_DIRECTION_UP,
							BB8_DIRECTION_DOWN, currentPosition, currentDirection);

					drawLabyrinth(labyrinth);

					Thread.sleep(sleepingTime);
				}

			} else {
				System.out.println("BB-8 found his way out of the labyrinth!");
				System.out.println("BB-8 made " + countedSteps + " Steps");
				System.out.println("The way of BB-8 in the labyrinth");
				drawLabyrinthMap(labyrinthMap);
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
			final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN, char checkingExitRightOrFront) {

		// rightOrFront = 'r' for checking if the Exit is right of BB8, else checking if
		// the Exit is in front of BB8

		int bb8PositionColumn = currentPosition[0];
		int bb8PositionRow = currentPosition[1];

		boolean isExit = false;

		// checking Exit right of BB8

		if (checkingExitRightOrFront == 'r') {

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

			// checking Exit in front of BB8

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

	// Determine Position of BB8. We know, that the startposition of BB8 has to be
	// at the outer lines of the labyrinth. So we just search there, so we don't
	// have to go through the whole labyrinth line by line

	public static int[] determineBB8startPosition(char[][] labyrinth, final char BB8_DIRECTION_RIGHT,
			final char BB8_DIRECTION_LEFT, final char BB8_DIRECTION_UP, final char BB8_DIRECTION_DOWN) {

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

	public static char[][] buildLabyrinthThree(final char SIGN_WALL, final char SIGN_PATH,
			final char BB8_START_DIRECTION, final char SIGN_EXIT) {
		char wall = SIGN_WALL;
		char path = SIGN_PATH;

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

		labyrinth[14][0] = BB8_START_DIRECTION;
		labyrinth[0][2] = SIGN_EXIT;

		return labyrinth;
	}

	public static char[][] buildLabyrinthTwo(final char SIGN_WALL, final char SIGN_PATH, final char BB8_START_DIRECTION,
			final char SIGN_EXIT) {
		char wall = SIGN_WALL;
		char path = SIGN_PATH;

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
		labyrinth[9][10] = BB8_START_DIRECTION;
		// Exit Position
		labyrinth[1][0] = SIGN_EXIT;

		return labyrinth;
	}

	public static char[][] buildLabyrinthOne(final char SIGN_WALL, final char SIGN_PATH, final char BB8_START_DIRECTION,
			final char SIGN_EXIT) {
		char wall = SIGN_WALL;
		char path = SIGN_PATH;

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
		labyrinth[1][0] = BB8_START_DIRECTION;

		// Setzen von Exit Position
		labyrinth[1][11] = SIGN_EXIT;

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
