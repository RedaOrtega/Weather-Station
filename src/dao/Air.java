package dao;

public class Air

{   
	protected String valeur ;

	public String getValeur() {
		
		return valeur;
	}

	public void setValeur(String valeur) {
		
		this.valeur = valeur;
		
	}

	public Air(String valeur) {
		
		super();
		
		this.valeur = valeur;
		
	} 
	
	public Air() {
		
		super();
		
		this.valeur = "";
		
	}

	@Override
	public String toString() {
		
		return "Air [valeur=" + valeur + "]";
		
	} 
	
	public void afficher() {
		
		System.out.println("Air [valeur=" + valeur + "]");
		
		
	}

	}
	
	
	
	
	
	

