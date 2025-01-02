package dao;

public class Temperature extends Air { 
	
	// Temperature inherits from Air

    public Temperature() {
    	
        super();  // Call the parent (Air) constructor
    }
    

    public Temperature(String valeur) {
    	
        super(valeur);  // Call the Air constructor with value
    }
    
}