public class Labyrinth {
	public static void main (String[] args) {
		
		// Deklaration
		char bb8 = '>'; 		// Anfangsposition von BB-8
		char wand = '#'; 		// Wand
		char weg = ' '; 		// Weg = null
		char exit = 'E';		// E steht für Exit
		
		// Deklaration und Instanziierung von Labyrinth
		int[][] labyrinth = 
			{
					{wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand}, 	// 0 Zeile
					{bb8,weg,weg,weg,weg,weg,wand,weg,weg,weg,weg,exit}, 			// 1 Zeile
					{wand,wand,wand,wand,wand,weg,wand,weg,wand,wand,wand,wand}, 	// 2 Zeile
					{wand,weg,weg,weg,weg,weg,wand,weg,weg,weg,weg,wand}, 			// 3 Zeile
					{wand,weg,wand,wand,wand,weg,wand,wand,wand,wand,weg,wand},		// 4 Zeile
					{wand,weg,wand,weg,weg,weg,wand,weg,weg,weg,weg,wand},			// 5 Zeile
					{wand,weg,wand,wand,wand,wand,wand,wand,wand,wand,weg,wand},	// 6 Zeile
					{wand,weg,weg,weg,weg,weg,weg,wand,weg,wand,weg,wand},			// 7 Zeile
					{wand,wand,wand,wand,wand,wand,weg,wand,weg,wand,weg,wand},		// 8 Zeile
					{wand,weg,weg,weg,weg,weg,weg,weg,weg,weg,weg,wand},			// 9 Zeile
					{wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand,wand}	// 10 Zeile
			};
		
		System.out.println(labyrinth[1][0]);
	}
}
