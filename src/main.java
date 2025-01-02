import ArduinoToDatabase.ArduinoToDatabase;
public class main {

    public static void main(String[] args) {
    	
        // Start Arduino communication in a separate thread
    	
        Thread arduinoThread = new Thread(() -> {
        	
            ArduinoToDatabase.main(args); // Initialize Arduino communication
            
        });
        
        arduinoThread.start();
        

        // Start the GUI on the Event Dispatch Thread
        
        javax.swing.SwingUtilities.invokeLater(() -> {
        	
            try {
            	
                interfacee.main(args); // Launch the GUI
                
            } catch (Exception e) {
            	
                e.printStackTrace();
            }
            
        });
    }
}
