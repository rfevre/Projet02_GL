public class Main{
     /**
     * Fonction principal qui construit l'arbre, et lance le jeu
     */
    public static void main(String[] args) {
		Arbre arbre;
		String definir = "";
		char reponse = 'n';

		do {
			System.out.println();
			System.out.println("------------------------------------------------");
			System.out.println("-        Projet 2 : Les Arbres Binaires        -");
			System.out.println("------------------------------------------------");
			System.out.println();

			//Si pas d'arguments
			if (args.length == 0) {
				//On créer un abre par défaut
				
				String[] values = new String[]{"Est-ce un mammifère ?", "un crocodile", "Est-ce qu’il aboie ?", "Un cheval", "Un chien"};
				arbre = new Arbre(values, 0);
				
				//On lance la recherche d'un animal
				arbre.rechercherAnimal(); 
			}
			else {
				//Si l'argument d'indice 0 est égal à "--definir" 
				if (args[0].equals("--definir")){                           
					arbre = new Arbre(args, 2);         
					//On lance la recherche automatique d'un animal                     
					definir = arbre.definir(args[1]);            
					
					//défini par l'argument d'indice 1
					if (!definir.equals(""))                                     
						System.out.println(definir);
					else
						System.out.println("Animal non trouvé");
				}
				// Si pas d'argument spécial à l'indice 0
				else {                                                     
					arbre = new Arbre(args, 0);
					System.out.println(arbre);
					//On lance la recherche d'un animal
					arbre.rechercherAnimal();                               
				}
			}
			
			System.out.println("\n------------------------------------------------");
			System.out.println("-                  Arbre Final                 -");
			System.out.println("------------------------------------------------ \n");
			System.out.println(arbre);
			System.out.println("\n Voulez vous quitter ? (o/n)");
			reponse=Clavier.readChar();
		} while(reponse !='o');
	}
}
