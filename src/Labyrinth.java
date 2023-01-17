import de.hsrm.mi.prog.util.StaticScanner;

/* Zweites Stegreifprojekt: "BB-8 lost in Labyrinth"
   Implementiert von Mykhailo Fakliier, Fouad Ahsayni und Artur Konkel	
*/

public class Labyrinth {

	// Global variables
	static final char BB8_DIRECTION_RIGHT = '>';
	static final char BB8_DIRECTION_LEFT = '<';
	static final char BB8_DIRECTION_UP = '^';
	static final char BB8_DIRECTION_DOWN = 'v';
	static final char SIGN_WALL = '.';
	static final char SIGN_PATH = ' ';
	static final char SIGN_EXIT = 'E';
	static final char SIGN_DARK_FORCE = 'D';

	public static void main(String[] args) throws InterruptedException {

		// Declaration of variables

		int[] bb8Position; // [0] = Column [1] = Row
		char currentDirection = 0;

		char[][] labyrinth = null;
		char[][] labyrinthMap = null;

		boolean userInputCorrect = false;

		// Beginning the adventure with a story
	//	playStartStory();

		// Asking the user to choose a labyrinth
		while (userInputCorrect == false) {

			System.out.println(
					"How hard should it be, to get through the spacecrafts labyrinth for BB-8 ('e'asy/'m'edium/'h'ard)?");

			char userInput = StaticScanner.nextChar();

			switch (userInput) {
			case 'e': {
				currentDirection = BB8_DIRECTION_RIGHT;
				labyrinth = buildLabyrinthOne(currentDirection);
				setDarkForce(labyrinth, 1);
				userInputCorrect = true;
				break;
			}
			case 'm': {
				currentDirection = BB8_DIRECTION_LEFT;
				labyrinth = buildLabyrinthTwo(currentDirection);
				setDarkForce(labyrinth, 2);
				userInputCorrect = true;
				break;
			}
			case 'h': {
				currentDirection = BB8_DIRECTION_RIGHT;
				labyrinth = buildLabyrinthThree(currentDirection);
				setDarkForce(labyrinth, 4);
				userInputCorrect = true;
				break;
			}
			default:
				System.out.println("Input is not valid. Please choose again.");
			}
		}

		// Setting BB-8's starting position in the labyrinth chosen by the user
		bb8Position = determineBB8startPosition(labyrinth);

		// Map of BB-8's steps through the labyrinth
		labyrinthMap = buildLabyrinthMap(labyrinth);

		// Sending BB8 through the labyrinth chosen by the user
		findWayThroughLabyrinth(labyrinth, labyrinthMap, currentDirection, bb8Position);
	}

	public static void playStartStory() throws InterruptedException {
		int sleepingTime = 5000;

		System.out.println("A long time ago in a galaxy far, far away...");
		Thread.sleep(sleepingTime / 2);
		System.out
				.println("R2-D2 was kidnapped by an enemies spaceship and brought deep into there occupied territory.");
		Thread.sleep(sleepingTime);
		System.out
				.println("Because R2-D2 was an important team member and one of our best friends we had to free him.");
		Thread.sleep(sleepingTime);
		System.out.println(
				"But the mission was dangerous. It would be a suicide mission to just fly into the enemies territory.");
		Thread.sleep(sleepingTime);
		System.out
				.println("So we decide to carry out a stealth mission. And our only hope was R2-D2s best friend BB-8.");
		Thread.sleep(sleepingTime);
		System.out.println("He knows how to get into the enemies spaceship without getting detected by the radar.");
		Thread.sleep(sleepingTime);
		System.out.println(
				"We gave him the coordinates where R2-D2 is held and a special programm to find his way through the spacecrafts labyrinths.");
		Thread.sleep(sleepingTime);
		System.out.println("With a little luck, he can free our friend and return safely to our carrier.");
		Thread.sleep(sleepingTime);
		System.out.println("But it's uppon you to decide, how hard it will be for BB-8 to find his friend... ");
		Thread.sleep(sleepingTime);
	}

	public static void playEndStory(char[][] labyrinthMap, int countedSteps, boolean hasFoundExit)
			throws InterruptedException {
		int sleepingTime = 3500;

		if (hasFoundExit) {
			System.out.println("Finally... BB-8 fought he would never get out this labyrinth.");
			Thread.sleep(sleepingTime);
			System.out.println("Now just a few corners and he gets where R2-D2 should be located...");
			Thread.sleep(sleepingTime);
			System.out.println("No signal...");
			Thread.sleep(sleepingTime);
			System.out.println("No signal...");
			Thread.sleep(sleepingTime);
			System.out.println("No signal...");
			Thread.sleep(sleepingTime * 1, 5);

			System.out.println("Our beamer is getting a request! Two robots want to be beamed on our ship!");
			Thread.sleep(sleepingTime);
			System.out.println("Cheering...");
			Thread.sleep(sleepingTime);
			System.out.println("R2-D2 are safely back, saved by his friend BB-8.");
			Thread.sleep(sleepingTime);
			System.out.println("It only took him " + countedSteps + " steps to find through this labyrinth.");
			Thread.sleep(sleepingTime);
			System.out.println("And also, to everyone's surprise, he got a map with his steps.");
			Thread.sleep(sleepingTime);
			drawLabyrinthMap(labyrinthMap);
			Thread.sleep(sleepingTime);
			System.out.println("BB-8 is happy.");
		} else {
			System.out.println("We are getting no Signal from BB-8...");
			Thread.sleep(sleepingTime);
			System.out.println("I think we lost him.");
		}

	}

	public static void setDarkForce(char[][] labyrinth, int amountOfDarkForces) {
		int ranPosColumn;
		int ranPosRow;
		char currentSign;
		int counter = 0;

		while (counter < amountOfDarkForces) {
			ranPosColumn = getRandomPos(0, labyrinth.length - 1);
			ranPosRow = getRandomPos(0, labyrinth[0].length - 1);

			currentSign = labyrinth[ranPosColumn][ranPosRow];

			if (currentSign == SIGN_PATH) {
				labyrinth[ranPosColumn][ranPosRow] = SIGN_DARK_FORCE;

				counter++;
			}
		}

	}

	public static int getRandomPos(int min, int max) {
		return (int) Math.round(Math.random() * (max - min) + min);
	}

	// Building the map of BB-8's steps through the labyrinth
	public static char[][] buildLabyrinthMap(char[][] labyrinth) {

		int columnLength = labyrinth.length;
		int rowLength = labyrinth[0].length;
		char[][] labyrinthMap = new char[columnLength][rowLength];

		// Setting all elements of the array to an empty char so that the starting map
		// is empty
		for (int i = 0; i < labyrinthMap.length; i++) {
			for (int j = 0; j < labyrinthMap[i].length; j++) {
				(labyrinthMap[i][j]) = ' ';
			}
		}

		return labyrinthMap;

	}

	// Drawing and printing the map of BB-8's steps through the labyrinth to the
	// console
	public static void drawLabyrinthMap(char[][] labyrinthMap) {

		System.out.println();
		for (int i = 0; i < labyrinthMap.length; i++) {
			for (int j = 0; j < labyrinthMap[i].length; j++) {
				System.out.print(labyrinthMap[i][j]);
			}
			System.out.println();
		}
	}

	// Drawing the direction signs of BB-8 into the map based on his way through the
	// labyrinth
	public static void drawStepIntoMap(char[][] labyrinthMap, char currentDirection, int[] currentPosition) {
		int columnPosition = currentPosition[0];
		int rowPosition = currentPosition[1];
		labyrinthMap[columnPosition][rowPosition] = currentDirection;
	}

	// Sending BB8 through the labyrinth chosen by the user
	public static void findWayThroughLabyrinth(char[][] labyrinth, char[][] labyrinthMap, char currentDirection,
			int[] currentPosition) throws InterruptedException {

		boolean isExit = false;
		boolean isExitRight = false;
		boolean isExitFront = false;

		boolean isWallRight = false;
		boolean isWallFront = false;

		boolean isDarkForce = false;

		// Setting the interval between the printing of each of BB-8's steps to the
		// console
		// The interval is used as input for the Thread.sleep()-method later on
		int sleepingTime = 100;

		// Number of steps BB-8 takes in the labyrinth
		int countedSteps = 0;

		// Drawing and printing the labyrinth chosen by the user along with BB-8's
		// current position
		drawLabyrinth(labyrinth);

		// BB8 making his way through the labyrinth by checking various conditions such
		// as the existence of walls
		// or free paths.
		while (isExit == false && isDarkForce == false) {

			isWallRight = checkIfWallRight(labyrinth, currentPosition, currentDirection);

			if (isWallRight) {
				isWallFront = checkIfWallFront(labyrinth, currentPosition, currentDirection);
			}

			isExitRight = checkIfExit(labyrinth, currentPosition, currentDirection, 'r');

			if (!isExitRight) {
				isExitFront = checkIfExit(labyrinth, currentPosition, currentDirection, 'f');
			}

			if (isExitRight) {

				turnRight(labyrinth, currentPosition, currentDirection);

				makeStep(labyrinth, currentPosition, currentDirection);

				drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);

				// Adding a new step to the variable countedSteps
				countedSteps++;

				drawLabyrinth(labyrinth);

				Thread.sleep(sleepingTime);

				isExit = true;

			} else if (isExitFront) {

				makeStep(labyrinth, currentPosition, currentDirection);

				drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);

				countedSteps++;

				drawLabyrinth(labyrinth);

				Thread.sleep(sleepingTime);

				isExit = true;

			}

			if (!isExitRight && !isExitFront) {

				if (!isWallRight) {

					currentDirection = turnRight(labyrinth, currentPosition, currentDirection);

					drawLabyrinth(labyrinth);

					Thread.sleep(sleepingTime);

					isDarkForce = checkIfDarkForce(labyrinth, currentPosition, currentDirection);

					makeStep(labyrinth, currentPosition, currentDirection);

					drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);

					countedSteps++;

					drawLabyrinth(labyrinth);

					Thread.sleep(sleepingTime);

				} else if (isWallRight && !isWallFront) {

					isDarkForce = checkIfDarkForce(labyrinth, currentPosition, currentDirection);

					makeStep(labyrinth, currentPosition, currentDirection);

					drawStepIntoMap(labyrinthMap, currentDirection, currentPosition);

					countedSteps++;

					drawLabyrinth(labyrinth);

					Thread.sleep(sleepingTime);

				} else if (isWallRight && isWallFront) {

					currentDirection = turnLeft(labyrinth, currentPosition, currentDirection);

					drawLabyrinth(labyrinth);

					Thread.sleep(sleepingTime);
				}

			} else {
				// playEndStory(labyrinthMap, countedSteps);
			}

		}

		if (isDarkForce) {
			playEndStory(labyrinthMap, countedSteps, false);

		}

		if (isExit) {
			playEndStory(labyrinthMap, countedSteps, true);
		}

	}

	public static boolean checkIfDarkForce(char[][] labyrinth, int[] currentPosition, char currentDirection) {
		int bb8PositionColumn = currentPosition[0];
		int bb8PositionRow = currentPosition[1];

		boolean isDarkForce = false;

		if (currentDirection == BB8_DIRECTION_RIGHT) {
			if (labyrinth[bb8PositionColumn][bb8PositionRow + 1] == SIGN_DARK_FORCE) {
				isDarkForce = true;
			}
		} else if (currentDirection == BB8_DIRECTION_LEFT) {
			if (labyrinth[bb8PositionColumn][bb8PositionRow - 1] == SIGN_DARK_FORCE) {
				isDarkForce = true;
			}
		} else if (currentDirection == BB8_DIRECTION_UP) {
			if (labyrinth[bb8PositionColumn - 1][bb8PositionRow] == SIGN_DARK_FORCE) {
				isDarkForce = true;
			}
		} else if (currentDirection == BB8_DIRECTION_DOWN) {
			if (labyrinth[bb8PositionColumn + 1][bb8PositionRow] == SIGN_DARK_FORCE) {
				isDarkForce = true;
			}
		}

		return isDarkForce;

	}

	// BB-8 making steps based on his current position (for example first turning
	// right or left and then taking his step)
	public static void makeStep(char[][] labyrinth, int[] bb8Position, char currentDirection) {

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

	// BB-8 turning left based on his current direction and the conditions given in
	// the method findWayThroughLabyrinth
	public static char turnLeft(char[][] labyrinth, int[] bb8Position, char currentDirection) {

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

	// BB-8 turning right based on his current direction and the conditions given in
	// the method findWayThroughLabyrinth
	public static char turnRight(char[][] labyrinth, int[] bb8Position, char currentDirection) {

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

	// BB-8 checking if there is a wall in front of him (based on his current
	// position)
	public static boolean checkIfWallFront(char[][] labyrinth, int[] bb8Position, char currentDirection) {

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

	// BB-8 checking if there is a wall right of him (based on his current position)
	public static boolean checkIfWallRight(char[][] labyrinth, int[] bb8Position, char currentDirection) {

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

	// BB-8 checking if the exit is right or in front of him
	public static boolean checkIfExit(char[][] labyrinth, int[] currentPosition, char currentDirection,
			char checkingExitRightOrFront) {

		// rightOrFront = 'r' for checking if the exit is right of BB8, else checking if
		// the exit is in front of BB-8

		int bb8PositionColumn = currentPosition[0];
		int bb8PositionRow = currentPosition[1];

		boolean isExit = false;

		// Checking if the exit is right of BB-8

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

			// Checking if the exit is in front of BB-8

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

	/*
	 * Determining the starting position of BB-8. We know that the starting position
	 * of BB-8 has to be at the outer lines of the labyrinth. So we only search
	 * there so we don't have to go through the whole labyrinth line by line.
	 */
	public static int[] determineBB8startPosition(char[][] labyrinth) {

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

	// Building the first of the labyrinths the user can choose from
	public static char[][] buildLabyrinthOne(final char BB8_START_DIRECTION) {

		char wall = SIGN_WALL;
		char path = SIGN_PATH;

		char[][] labyrinth = { { wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall }, // 0. Row
				{ path, path, path, path, path, path, wall, path, path, path, path, path }, // 1. Row
				{ wall, wall, wall, wall, wall, path, wall, path, wall, wall, wall, wall }, // 2. Row
				{ wall, path, path, path, path, path, wall, path, path, path, path, wall }, // 3. Row
				{ wall, path, wall, wall, wall, path, wall, wall, wall, wall, path, wall }, // 4. Row
				{ wall, path, wall, path, path, path, wall, path, path, path, path, wall }, // 5. Row
				{ wall, path, wall, wall, wall, wall, wall, wall, wall, wall, path, wall }, // 6. Row
				{ wall, path, path, path, path, path, path, wall, path, wall, path, wall }, // 7. Row
				{ wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, wall }, // 8. Row
				{ wall, path, path, path, path, path, path, path, path, path, path, wall }, // 9. Row
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall } // 10. Row
		};

		// Setting BB-8's starting position
		labyrinth[1][0] = BB8_START_DIRECTION;

		// Setting the position of the exit
		labyrinth[1][11] = SIGN_EXIT;

		return labyrinth;
	}

	// Building the second of the labyrinths the user can choose from
	public static char[][] buildLabyrinthTwo(final char BB8_START_DIRECTION) {

		char wall = SIGN_WALL;
		char path = SIGN_PATH;

		char[][] labyrinth = { { wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall }, // 0. Row
				{ path, path, path, path, wall, path, path, path, path, path, wall }, // 1. Row
				{ wall, path, wall, wall, wall, wall, wall, path, wall, path, wall }, // 2. Row
				{ wall, path, path, path, path, path, wall, path, wall, path, wall }, // 3. Row
				{ wall, path, wall, path, wall, path, wall, wall, wall, path, wall }, // 4. Row
				{ wall, path, wall, path, wall, path, path, path, wall, path, wall }, // 5. Row
				{ wall, path, wall, path, wall, wall, wall, wall, wall, path, wall }, // 6. Row
				{ wall, path, wall, path, path, path, path, path, path, path, wall }, // 7. Row
				{ wall, path, wall, wall, wall, path, wall, wall, wall, path, wall }, // 8. Row
				{ wall, path, wall, path, path, path, wall, path, path, path, path }, // 9. Row
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall } // 10. Row
		};

		// Setting BB-8's starting position
		labyrinth[9][10] = BB8_START_DIRECTION;

		// Setting the position of the exit
		labyrinth[1][0] = SIGN_EXIT;

		return labyrinth;
	}

	// Building the third of the labyrinths the user can choose from
	public static char[][] buildLabyrinthThree(final char BB8_START_DIRECTION) {
		char wall = SIGN_WALL;
		char path = SIGN_PATH;

		char[][] labyrinth = {
				{ wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 0. Row
				{ wall, wall, path, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 1. Row
				{ wall, wall, path, wall, wall, wall, path, path, path, path, path, path, path, path, path, path,
						wall }, // 2. Row
				{ wall, wall, path, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall, wall, path,
						wall }, // 3. Row
				{ wall, path, path, path, path, path, path, path, path, path, path, path, path, path, path, path,
						wall }, // 4. Row
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 5. Row
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, path, path, wall, wall, wall,
						wall }, // 6. Row
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 7. Row
				{ wall, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, path, path, path, path,
						wall }, // 8. Row
				{ wall, path, path, path, path, path, path, path, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 9. Row
				{ wall, path, wall, path, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 10. Row
				{ wall, path, wall, path, path, path, path, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 11. Row
				{ wall, path, wall, path, wall, wall, wall, wall, path, wall, path, wall, path, wall, wall, wall,
						wall }, // 12. Row
				{ wall, wall, wall, path, wall, path, path, path, path, path, path, wall, path, wall, wall, wall,
						wall }, // 13. Row
				{ path, path, path, path, wall, wall, wall, wall, wall, wall, path, wall, path, wall, wall, wall,
						wall }, // 14. Row
				{ wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall, wall,
						wall }, // 15. Row

		};

		// Setting BB-8's starting position
		labyrinth[14][0] = BB8_START_DIRECTION;

		// Setting the position of the exit
		labyrinth[0][2] = SIGN_EXIT;

		return labyrinth;
	}

	// Drawing and printing the labyrinth to the console
	public static void drawLabyrinth(char[][] labyrinth) {
		for (int i = 0; i < labyrinth.length; i++) {
			for (int j = 0; j < labyrinth[i].length; j++) {
				System.out.print(labyrinth[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
