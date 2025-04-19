package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Database.PirateDatabase;

import javax.swing.JTextArea;

public class CreateCrewPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    
    public CreateCrewPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create a Crew");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Create Crew");
        btnNewButton.setBounds(548, 448, 156, 43);
        startUpPane.add(btnNewButton);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(404, 283, 166, 22);
        startUpPane.add(textArea);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(682, 283, 166, 22);
        startUpPane.add(textArea_1);
        
        JLabel lblNewLabel = new JLabel("Crew's Name");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(451, 258, 91, 14);
        startUpPane.add(lblNewLabel);
        
        JLabel lblMiddleName = new JLabel("Crew's Location");
        lblMiddleName.setForeground(Color.WHITE);
        lblMiddleName.setBounds(723, 258, 104, 14);
        startUpPane.add(lblMiddleName);
        
        JLabel lblANewCrew = new JLabel("A NEW CREW SAILS THE HIGH SEAS");
        lblANewCrew.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblANewCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblANewCrew.setForeground(Color.WHITE);
        lblANewCrew.setBounds(338, 55, 587, 98);
        startUpPane.add(lblANewCrew);
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Retrieve the username and password from the form
                String name = textArea.getText();
                String location = textArea_1.getText();
                
                if(name.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter in a name, ye scallywag!");
                	return;
                }
                
                if(location.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter in a location, ye rapscallion!");
                	return;
                }
                
                // Create an instance of your DatabaseYMCA
                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet c;
                	
                    db.connect();
                    c = db.getCrewByName(name);
                    
                    if(c.next()) {
                    	JOptionPane.showMessageDialog(null, "A crew already sails with that name!");
                    	return;
                    }
                    
                    db.insertCrew(name, location);
                    
                    JOptionPane.showMessageDialog(null, "Crew Added Successfully");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    
                } finally {
                    db.disconnect();
                }
            }
        });
        
        setVisible(true);
    }
    
    
}
