package ArduinoToDatabase;

import com.fazecast.jSerialComm.*;

import java.sql.*;

public class ArduinoToDatabase {

    public static void main(String[] args) {
    	
        SerialPort[] ports = SerialPort.getCommPorts();
        
        System.out.println("Available ports:");
        
        for (int i = 0; i < ports.length; i++) {
        	
            System.out.println(i + ": " + ports[i].getSystemPortName() + " (" + ports[i].getDescriptivePortName() + ")");
        }

        
        
        if (ports.length > 0) {
        	
            SerialPort activePort = ports[0];
            
            if (activePort.openPort()) {
            	
                System.out.println("Opened port: " + activePort.getDescriptivePortName());

                activePort.addDataListener(new SerialPortDataListener() {
                	
                    StringBuilder dataBuffer = new StringBuilder();

                    @Override
                    public void serialEvent(SerialPortEvent event) {
                    	
                        int size = event.getSerialPort().bytesAvailable();
                        
                        if (size > 0) {
                        	
                            byte[] buffer = new byte[size];
                            
                            int bytesRead = event.getSerialPort().readBytes(buffer, size);
                            
                            String string = new String(buffer, 0, bytesRead).trim();
                            
                            dataBuffer.append(string);

                            // Process complete data set
                            int start = dataBuffer.indexOf("<");
                            
                            int end = dataBuffer.indexOf(">");
                            
                            if (start != -1 && end != -1 && end > start) {
                            	
                                String completeData = dataBuffer.substring(start + 1, end);
                                
                                System.out.println("Data received: [" + completeData + "]");
                                
                                String[] data = completeData.split(":");
                                
                                System.out.println("Data length: " + data.length);
                                
                                for (int i = 0; i < data.length; i++) {
                                	
                                    System.out.println("data[" + i + "] = " + data[i]);
                                    
                                }
                                if (data.length == 5) {
                                	
                                    insertDataToDatabase(data[0], data[1], data[2], data[3], data[4]);
                                    
                                } else {
                                	
                                    System.out.println("Invalid data received.");
                                    
                                }
                                
                                dataBuffer.delete(0, end + 1);
                                
                            }
                        }
                    }

                    @Override
                    public int getListeningEvents() {
                    	
                        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                        
                    }
                });
                
            } else {
            	
                System.out.println("Failed to open port: " + activePort.getSystemPortName());
                
            }
            
        } else {
        	
            System.out.println("No available ports.");
            
        }
    }

    private static void insertDataToDatabase(String humidity, String tempretuure, String pression, String lumniosite, String pluie) {
    	
        String url = "jdbc:mysql://localhost:3306/db";
        
        String user = "root";
        
        String password = "";

        String query = "INSERT INTO db (humidity, tempretuure, pression, lumniosite, pluie) VALUES (?, ?, ?, ?, ?)";
        

        try (Connection connection = DriverManager.getConnection(url, user, password);
        		
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	

            preparedStatement.setString(1, humidity);
            
            preparedStatement.setString(2, tempretuure);
            
            preparedStatement.setString(3, pression);
            
            preparedStatement.setString(4, lumniosite);
            
            preparedStatement.setString(5, pluie);

            int rowsInserted = preparedStatement.executeUpdate();
            
            if (rowsInserted > 0) {
            	
                System.out.println("Data inserted successfully.");
                
            }
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
    }
}
