public class NoeudArbre {
    /** le contenu du noeud */
    private String contenu;
    
    /** les noeuds gauche et droit */
    private NoeudArbre gauche, droit;

    /**
     * Constructeur qui initaliase le contenu du noeud
     * et initalise a null, le noeud gauche et droit
     * (c'est donc une réponse, car c'est une feuille)
     *
     * @param contenu Contenu du noeud
     */
    public NoeudArbre(String contenu) {
		this.contenu=contenu;
		this.gauche=null;
		this.droit=null;
    }

    /**
    * Constructeur qui initaliase le contenu du noeud
    * et initalise aussi le noeud gauche et droit
    * (c'est donc une question, car ce n'est pas une feuille)
    */
    public NoeudArbre(String contenu, NoeudArbre gauche, NoeudArbre droit) {
		this.contenu=contenu;
		this.gauche= gauche;
		this.droit= droit;
    }

    /**
     * Fonction qui affiche l'ensemble de l'arbre en parcours Préfixe
     *
     * @return L'arbre sous forme de chaine de caractere
     */
    public String toString() {
		String retour = this.contenu+"\n";
		if (this.gauche!=null)
			retour += this.gauche.toString();
		if (this.droit!=null)
			retour += this.droit.toString();
		return retour;
    }

    /**
     * Fonction qui pose les questions adéquates, jusqu'a ce que l'ordi gagne ou perde
     */
    public void rechercherAnimal() {
		String reponse="";
	
		//si c'est une question (car ce n'est pas une feuille)
		if (this.gauche!=null && this.droit!=null) {
			System.out.println(this.contenu);
			reponse=Clavier.readString();
			if (reponse.equals("oui"))
				this.droit.rechercherAnimal();
			if (reponse.equals("non"))
				this.gauche.rechercherAnimal();
		}
	
		//si ce n'est pas une question (car c'est une feuille)
		else{
			System.out.println("Est-ce " + contenu + " ?" );
			reponse=Clavier.readString();
			
			if (reponse.equals("oui"))
				System.out.println("J'ai gagné !");
			else if (reponse.equals("non")) {
				System.out.println("J'ai perdu :(");
				this.apprendre();
			}	    
		}	
    }

    /**
     * méthode apprendre permettant d’apprendre un nouvel animal pour le nœud courant.
     */
    public void apprendre() {
		String reponse="";
	
		System.out.println("Mais quel est l'animal que tu as choisi ?");
		reponse=Clavier.readString();
		droit=new NoeudArbre(reponse);//on met le nouveau animal a droite
		gauche=new NoeudArbre(this.contenu);//on met l'ancien animal a gauche
	
		System.out.println("Quel est la nouvelle question pour definir cet animal ?");
		reponse=Clavier.readString();
		contenu=reponse;//en remplace l'ancien animal par la nouvelle question
    }

    /**
     * fonction qui donne l’enchaînement questions-réponses permettant de définir l’animal spécifié (s’il existe)
     */
    public String definir(String animal) {
		String retour="";
	
		//si c'est une question
		if (gauche!=null && droit!=null) {           
			retour = gauche.definir(animal);
			
			//on teste si dans le sous arbre de gauche on trouve "animal"
			if (!retour.equals(""))		                     
				retour = contenu+ " -> non; " + retour;
			//si il le trouve, on rajoute "non" a la chaine de caractére
			else {                                               
				retour = droit.definir(animal);
				
				//on test si dans le sous arbre de droite on trouve "animal"
				if (!retour.equals(""))                     
					//si il le trouve, on rejoute "oui" a la chaine de caractére     
					retour = contenu+ " -> oui; " + retour; 
				else
					retour = "";
			}	  
		}
	
		 //sinon si ce n'est pas une question
		else {                                                  
			//si "animal" est trouvé dans le contenu du noeud
			if(this.contenu.equals(animal))                      
				//on renvoie son nom
				retour = "=> "+animal;                           
			else                                                 
				//on indentique qu'il n'est pas présent dans ce noeud ci
				retour = "";                                     
		}
		return retour;
    }

    /**
     * Fonction qui permet de créer un arbre automatiquement, à partir d'un tableau qu'on lui envoie
     */
    public static NoeudArbre creerArbre(String args[]) {
        int i = 2;
        NoeudArbre tmp = null;
        if (i<args.length)
            tmp = creerArbre(args, i);
        return tmp;
    }
    
    public static NoeudArbre creerArbre(String args[], int cursor) {
        NoeudArbre tmp = null;
        
        if (cursor < args.length) {
            if (!isQuestion(args[cursor])) {            //Si c'est une feuille
                tmp = new NoeudArbre(args[cursor]);
	    }
           
        else {                                      //Si c'est un noeud
        	tmp = new NoeudArbre(args[cursor],
			     creerArbre(args,++cursor),
			     creerArbre(args,++cursor));
            }
        }
        return tmp;
    }

    /**
     * Fonction qui test si la phrase envoyé en paramètre est une question
     */
    public static boolean isQuestion(String str) {      
		return str.charAt(str.length()-1)=='?';
    }
}
