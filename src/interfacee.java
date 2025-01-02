import java.awt.Color;

import java.awt.EventQueue;

import java.awt.Font;

import java.awt.Image;

import java.awt.Toolkit;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.time.LocalDate;

import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.SwingConstants;

import javax.swing.Timer;

import BD.BD;

import dao.Humidite;

import dao.Luminosite;

import dao.Precipitation;

import dao.Pression;

import dao.Temperature;

public class interfacee {
	
    JFrame frame;

    public static void main(String[] args) {
    	
        EventQueue.invokeLater(() -> {
        	
            try {
            	
                interfacee window = new interfacee();
                
                window.frame.setVisible(true);
                
            } catch (Exception e) {
            	
                e.printStackTrace();
                
            }
            
        });
        
    }

    public interfacee() {
    	
        initialize();
        
    }

    private void initialize() {
    	
        Temperature T = new Temperature();
        
        Humidite H = new Humidite();
        
        Precipitation P = new Precipitation();
        
        Pression Pr = new Pression();
        
        Luminosite L = new Luminosite();
        
        BD bd = new BD();
        
        Connection con = bd.connection();
        
        ResultSet rs = bd.selection(con);
        

        try {
        	
            if (rs != null && rs.next()) {
            	
                H.setValeur(rs.getString("humidity"));
                
                T.setValeur(rs.getString("tempretuure"));
                
                Pr.setValeur(rs.getString("pression"));
                
                P.setValeur(rs.getString("pluie"));
                
                L.setValeur(rs.getString("lumniosite"));
                
            }
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }

        frame = new JFrame();
        
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(interfacee.class.getResource("/images/icon.png")));
        
        frame.getContentPane().setBackground(Color.WHITE);
        
        frame.getContentPane().setLayout(null);
        

        LocalDate currentDate = LocalDate.now();
        
        LocalTime currentTime = LocalTime.now();
        
        String location = "Kenitra";

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        // Time label
        JLabel lblTime = new JLabel(currentTime.format(timeFormatter));
        
        lblTime.setFont(new Font("Calibri", Font.BOLD, 18));
        
        lblTime.setForeground(Color.DARK_GRAY);
        
        lblTime.setHorizontalAlignment(SwingConstants.LEFT);
        
        lblTime.setBounds(10, 200, 200, 25); // Left aligned
        
        frame.getContentPane().add(lblTime);

        // Location label
        JLabel lblLocation = new JLabel("Location: " + location);
        
        lblLocation.setFont(new Font("Calibri", Font.ITALIC, 14));
        
        lblLocation.setForeground(Color.DARK_GRAY);
        
        lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
        
        lblLocation.setBounds(10, 230, 200, 15); // Left aligned
        
        
        frame.getContentPane().add(lblLocation);
        

        // Date label
        JLabel lblDate = new JLabel(currentDate.format(dateFormatter));
        
        lblDate.setFont(new Font("Calibri", Font.BOLD, 16));
        
        lblDate.setForeground(Color.DARK_GRAY);
        
        lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
        
        lblDate.setBounds(220, 200, 200, 25); // Right aligned
        
        frame.getContentPane().add(lblDate);
        

        Timer timer = new Timer(1000, e -> lblTime.setText(LocalTime.now().format(timeFormatter)));
        
        timer.start();
        

        JPanel panel = new JPanel();
        
        panel.setBackground(new Color(135, 206, 235));
        
        panel.setBounds(0, 260, 434, 183); // Blue zone
        
        frame.getContentPane().add(panel);
        
        panel.setLayout(null);

        JLabel lblHumidity = new JLabel();
        
        lblHumidity.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/humidity.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        
        lblHumidity.setBounds(20, 49, 80, 80);
        
        panel.add(lblHumidity);

        JLabel valeurHumidite = new JLabel(H.getValeur() + "%");
        
        valeurHumidite.setFont(new Font("Calibri", Font.BOLD, 20));
        
        valeurHumidite.setForeground(Color.DARK_GRAY);
        
        valeurHumidite.setHorizontalAlignment(SwingConstants.CENTER);
        
        valeurHumidite.setBounds(32, 120, 46, 39);
        
        panel.add(valeurHumidite);
        
        

        JLabel lblTemperature = new JLabel();
        
        lblTemperature.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/temperature.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        
        lblTemperature.setBounds(122, 63, 46, 60);
        
        panel.add(lblTemperature);
        

        JLabel valeurTemperature = new JLabel(T.getValeur() + "°C");
        
        valeurTemperature.setFont(new Font("Calibri", Font.BOLD, 20));
        
        valeurTemperature.setHorizontalAlignment(SwingConstants.CENTER);
        
        valeurTemperature.setForeground(Color.DARK_GRAY);
        
        valeurTemperature.setBounds(122, 127, 46, 25);
        
        panel.add(valeurTemperature);
        

        JLabel lblPressure = new JLabel();
        
        lblPressure.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/pression.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        
        lblPressure.setBounds(224, 49, 80, 80);
        
        panel.add(lblPressure);
        

        JLabel valeurPression = new JLabel(Pr.getValeur() + "hpa");
        
        valeurPression.setFont(new Font("Calibri", Font.BOLD, 20));
        
        valeurPression.setForeground(Color.DARK_GRAY);
        
        valeurPression.setHorizontalAlignment(SwingConstants.CENTER);
        
        valeurPression.setBounds(224, 126, 80, 33);
        
        panel.add(valeurPression);
        

        JLabel lblPrecipitation = new JLabel();
        
        lblPrecipitation.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/precipitation.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        
        lblPrecipitation.setBounds(330, 49, 80, 80);
        
        panel.add(lblPrecipitation);

        JLabel valeurPrecipitation = new JLabel(P.getValeur() + "%");
        
        valeurPrecipitation.setFont(new Font("Calibri", Font.BOLD, 20));
        
        valeurPrecipitation.setHorizontalAlignment(SwingConstants.CENTER);
        
        valeurPrecipitation.setForeground(Color.DARK_GRAY);
        
        valeurPrecipitation.setBounds(342, 129, 46, 20);
        
        panel.add(valeurPrecipitation);

        JLabel lblWeatherIcon = new JLabel();
        
        lblWeatherIcon.setBounds(164, 50, 116, 119);
        
        frame.getContentPane().add(lblWeatherIcon);

        String weatherCondition = "Unknown";
        
        if (!L.getValeur().isEmpty() && !P.getValeur().isEmpty()) {
        	
            int luminosity = Integer.parseInt(L.getValeur());
            
            int precipitation = Integer.parseInt(P.getValeur());
            
            if (luminosity < 6 || precipitation > 0) {
            	
                weatherCondition = "Rainy";
                
                lblWeatherIcon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/rainy.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                
            } else if (luminosity > 6 && luminosity < 80) {
            	
                weatherCondition = "Cloudy";
                
                lblWeatherIcon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/weather.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                
            } else if (luminosity >= 80) {
            	
                weatherCondition = "Sunny";
                
                lblWeatherIcon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/sunny.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                
            }
            
        }

        JLabel lblWeatherText = new JLabel(weatherCondition);
        
        lblWeatherText.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblWeatherText.setForeground(Color.DARK_GRAY);
        
        lblWeatherText.setFont(new Font("Calibri Light", Font.BOLD, 16));
        
        lblWeatherText.setBounds(164, 168, 106, 26);
        
        frame.getContentPane().add(lblWeatherText);
        

        frame.setBounds(100, 100, 442, 515);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
