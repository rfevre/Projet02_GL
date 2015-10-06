public class NoeudArbre {
    /** le contenu du noeud */
    private String contenu;
    
    /** les noeuds gauche et droit */
    private NoeudArbre gauche, droit;

    /**
     * Constructeur qui initaliase le contenu du noeud
     * et initalise a null, le noeud gauche et droit
     * (c'est donc une réponse, car c'est une feuille)
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
     * Affiche l'ensemble de l'arbre en parcours Préfixe
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
     * Fonction qui pose les questions adéquat, jusqu'a ce que l'ordi gagne ou perde
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
	    System.out.println("Est-ce "+this.contenu);
	    reponse=Clavier.readString();
	    if (reponse.equals("oui"))
		System.out.println("j'ai gagné");
	    if (reponse.equals("non")) {
		System.out.println("j'ai perdu");
		this.apprendre();
	    }	    
	}	
    }

    /**
     * méthode apprendre permettant d’apprendre un nouvel animal pour le nœud courant.
     */
    public void apprendre() {
	String reponse="";
	
	System.out.println("Quel est l'animal choisi ?");
	reponse=Clavier.readString();
	this.droit=new NoeudArbre(reponse);//on met le nouveau animal a droite
	this.gauche=new NoeudArbre(this.contenu);//on met l'ancien animal a gauche
	
	System.out.println("Quel est la nouvelle question pour definir l'animal ?");
	reponse=Clavier.readString();
	this.contenu=reponse;//en remplace l'ancien animal par la nouvelle question
    }

    /**
     * fonction qui donne l’enchaînement questions-réponses permettant de définir l’animal spécifié (s’il existe)
     */
    public String definir(String animal) {
	String retour="";
	
	if (this.gauche!=null && this.droit!=null) { //si c'est une question
	    retour = this.gauche.definir(animal);
	    if (!retour.equals("")) {                        // on test si dans le sous arbre de gauche on trouve "animal"
		retour = this.contenu+ " -> non; " + retour; //si il le trouve, on rajoute "non" a la chaine de caractére
	    }
	    else {
		retour = this.droit.definir(animal);
		if (!retour.equals(""))                          // on test si dans le sous arbre de droite on trouve "animal"
		    retour = this.contenu+ " -> oui; " + retour; //si il le trouve, on rejoute "oui" a la chaine de caractére
		else
		    retour = "";
	    }	  
	}
	
	else { //sinon si ce n'est pas une question
	    if(this.contenu.equals(animal)) //si "animal" est trouvé dans le contenu du noeud
		retour = "=> "+animal;      //on renvoie son nom
	    else                            //sinon
		retour = "";                //on indentique qu'il n'est pas présent dans ce noeud ci
	}
	return retour;
    }

    /**
     *
     */
    public static NoeudArbre creerArbre(String args[]) {
        int i = 2;
        NoeudArbre tmp = null;
        if (i<args.length) {
            tmp = creerArbre(args, i);
        }
        return tmp;
    }
    
    public static NoeudArbre creerArbre(String args[], int cursor) {
        NoeudArbre tmp = null;
        if (cursor < args.length) {
            if (!isQuestion(args[cursor])) {    //Si c'est une feuille
                tmp = new NoeudArbre(args[cursor]);
	    }
            else {                                //Si c'est un noeud
                tmp = new NoeudArbre(args[cursor],
				     creerArbre(args,++cursor),
				     creerArbre(args,++cursor));
            }
        }
        return tmp;
    }

    public static boolean isQuestion(String str) {
	return str.charAt(str.length()-1)=='?';
    }
    
    /**
     * Fonction qui construit l'arbre, et lance le jeu
     */
    public static void main(String[] args) {
	NoeudArbre aboie,noeud;
	NoeudArbre test;
	if (args.length == 0) {
	    aboie = new NoeudArbre("Est-ce qu’il aboie ?",
		    new NoeudArbre("un cheval"),
		    new NoeudArbre("un chien"));
	    noeud = new NoeudArbre("Est-ce un mammifère ?",
		    new NoeudArbre("un crocodile"),
		    aboie);
	}
	else {
	    test = this.creerArbre(args);
	    /* noeud = new NoeudArbre(args[0],
		    new NoeudArbre(args[1]),
		    new NoeudArbre(args[2]));*/
	}
	
	noeud.rechercherAnimal();
	//System.out.println(noeud.definir("un chien"));
	System.out.println("\n"+noeud.toString());
    }
    
}
