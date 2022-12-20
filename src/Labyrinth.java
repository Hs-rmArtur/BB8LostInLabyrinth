public class Labyrinth {
	public static void main (String[] args) {

		// Deklaration
		char bb8 = '>'; 		// Anfangsposition von BB-8
		char wand = '#'; 		// Wand
		char weg = ' '; 		// Weg = null
		char exit = 'E';		// E steht für Exit


		char[][] labyrinth = buildLabyrinthEins(wand, weg, bb8, exit);


		// Deklaration und Instanziierung von Labyrinth
		drawLabyrinth(labyrinth);

	}


	public static char[][] buildLabyrinthEins(char wand, char weg, char bb8, char exit) {

		char[][] labyrinth =
			{
					{wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand}, 	// 0 Zeile
					{weg,weg,weg,weg,weg,weg,wand,weg,weg,weg,weg,weg}, 			// 1 Zeile
					{wand,wand,wand,wand,wand,weg,wand,weg,wand,wand,wand,wand}, 	// 2 Zeile
					{wand,weg,weg,weg,weg,weg,wand,weg,weg,weg,weg,wand}, 			// 3 Zeile
					{wand,weg,wand,wand,wand,weg,wand,wand,wand,wand,weg,wand},		// 4 Zeile
					{wand,weg,wand,weg,weg,weg,wand,weg,weg,weg,weg,wand},			// 5 Zeile
					{wand,weg,wand,wand,wand,wand,wand,wand,wand,wand,weg,wand},		// 6 Zeile
					{wand,weg,weg,weg,weg,weg,weg,wand,weg,wand,weg,wand},			// 7 Zeile
					{wand,wand,wand,wand,wand,wand,weg,wand,weg,wand,weg,wand},		// 8 Zeile
					{wand,weg,weg,weg,weg,weg,weg,weg,weg,weg,weg,wand},			// 9 Zeile
					{wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand}		// 10 Zeile
			};

		// Setzen von BB8 Startposition
		labyrinth[1][0] = bb8;

		// Setzen von Exit Position
		labyrinth[1][11] = exit;

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
