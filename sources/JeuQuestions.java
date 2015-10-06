

class JeuQuestions {
    public static void main(String[] args) {
	NoeudArbre aboie = new NoeudArbre("Est-ce qu’il aboie ?",new NoeudArbre("un cheval"),new NoeudArbre("un chien"));
					  NoeudArbre noeud = new NoeudArbre("Est-ce un mammifère ?",new NoeudArbre("un crocodile"),aboie);
	System.out.println(noeud.toString());
    }	
}
