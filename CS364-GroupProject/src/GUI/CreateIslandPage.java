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

public class CreateIslandPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    
    private String island = "<html>⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠻⢿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⠀⣀⣤⣶⣶⣦⣄⠙⣿⣿⣿⣇⣠⣶⣾⣿⣷⣶⣶⠄⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣷⣼⣿⣿⣿⣿⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠘⠛⠉⠉⠉⠁⠉⠉⠛⢿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣤⣀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⠿⠛⢿⣿⣿⣿⣿⣟⠛⠻⢿⣷⣦⡀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡿⠁⠀⠀⢸⣿⣿⡿⠻⣿⣷⡀⠀⠉⠻⢷⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡿⠁⠀⠀⠀⠸⣿⡿⠁⠀⠈⢿⣇⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⠁⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠏⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⠀⢠⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⢀⣾⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠀⣼⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⢠⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⣼⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
    		+ "⠀⠀⠀⠀⠈⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀<html>";
    
    public CreateIslandPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create an Island");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Create Island");
        btnNewButton.setBounds(546, 448, 156, 43);
        startUpPane.add(btnNewButton);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(404, 283, 166, 22);
        startUpPane.add(textArea);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(682, 283, 166, 22);
        startUpPane.add(textArea_1);
        
        JLabel lblNewLabel = new JLabel("Island's Name");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(404, 258, 166, 14);
        startUpPane.add(lblNewLabel);
        
        JLabel lblMiddleName = new JLabel("Island's Location");
        lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
        lblMiddleName.setForeground(Color.WHITE);
        lblMiddleName.setBounds(682, 258, 166, 14);
        startUpPane.add(lblMiddleName);
        
        JLabel lblANewCrew = new JLabel("A NEW ISLAND HAS BEEN DISCOVERED");
        lblANewCrew.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblANewCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblANewCrew.setForeground(Color.WHITE);
        lblANewCrew.setBounds(10, 55, 1244, 98);
        startUpPane.add(lblANewCrew);
        
        JLabel lblNewLabel_1 = new JLabel(island);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(20, 0, 287, 357);
        startUpPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel(island);
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setBounds(977, 11, 287, 357);
        startUpPane.add(lblNewLabel_1_1);
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
                
                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet c;
                	
                    db.connect();
                    c = db.getCrewByName(name);
                    
                    if(c.next()) {
                    	JOptionPane.showMessageDialog(null, "A island already bears that name!");
                    	return;
                    }
                    
                    db.insertIsland(name, location);
                    
                    JOptionPane.showMessageDialog(null, "Island Added Successfully");

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
