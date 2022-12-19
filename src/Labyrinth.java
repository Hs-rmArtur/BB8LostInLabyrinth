public class Labyrinth {
	public static void main (String[] args) {
		
		// Deklaration
		char bb8 = '>'; 		// Anfangsposition von BB-8
		char dot = '#'; 		// Wand
		char weg = ' '; 		// Weg = null
		char e = 'E';			// E steht für Exit
		
		// Deklaration und Instanziierung von Labyrinth
		int[][] labyrinth = 
			{
					{dot,dot,dot,dot,dot,dot,dot,dot,dot,dot,dot,dot}, 	// 0 Zeile
					{bb8,weg,weg,weg,weg,weg,dot,weg,weg,weg,weg,e}, 	// 1 Zeile
					{dot,dot,dot,dot,dot,weg,dot,weg,dot,dot,dot,dot}, 	// 2 Zeile
					{dot,weg,weg,weg,weg,weg,dot,weg,weg,weg,weg,dot}, 	// 3 Zeile
					{dot,weg,dot,dot,dot,weg,dot,dot,dot,dot,weg,dot},	// 4 Zeile
					{dot,weg,dot,weg,weg,weg,dot,weg,weg,weg,weg,dot},	// 5 Zeile
					{dot,weg,dot,dot,dot,dot,dot,dot,dot,dot,weg,dot},	// 6 Zeile
					{dot,weg,weg,weg,weg,weg,weg,dot,weg,dot,weg,dot},	// 7 Zeile
					{dot,dot,dot,dot,dot,dot,weg,dot,weg,dot,weg,dot},	// 8 Zeile
					{dot,weg,weg,weg,weg,weg,weg,weg,weg,weg,weg,dot},	// 9 Zeile
					{dot,dot,dot,dot,dot,dot,dot,dot,dot,dot,dot,dot}	// 10 Zeile
			};
		
		System.out.println(labyrinth[1][0]);
	}
}
