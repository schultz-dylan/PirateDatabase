package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Database.PirateDatabase;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateBattlePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTextField yearField;
    private Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    
    public CreateBattlePage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create a Battle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Create Battle");
        btnNewButton.setBounds(552, 520, 156, 43);
        startUpPane.add(btnNewButton);
        
        JTextArea nameText = new JTextArea();
        nameText.setBounds(404, 246, 166, 22);
        startUpPane.add(nameText);
        
        JTextArea locationText = new JTextArea();
        locationText.setBounds(682, 246, 166, 22);
        startUpPane.add(locationText);
        
        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(404, 221, 166, 14);
        startUpPane.add(lblNewLabel);
        
        JLabel lblMiddleName = new JLabel("Location");
        lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
        lblMiddleName.setForeground(Color.WHITE);
        lblMiddleName.setBounds(682, 221, 166, 14);
        startUpPane.add(lblMiddleName);
        
        JLabel lblANewCrew = new JLabel("A NEW BATTLE HAS BEEN FOUGHT");
        lblANewCrew.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblANewCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblANewCrew.setForeground(Color.WHITE);
        lblANewCrew.setBounds(338, 55, 587, 98);
        startUpPane.add(lblANewCrew);
        
        JTextArea winnerText = new JTextArea();
        winnerText.setBounds(404, 332, 166, 22);
        startUpPane.add(winnerText);
        
        JTextArea casualtiesText = new JTextArea();
        casualtiesText.setBounds(682, 332, 166, 22);
        startUpPane.add(casualtiesText);
        
        JLabel lblWinner = new JLabel("Winner");
        lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
        lblWinner.setForeground(Color.WHITE);
        lblWinner.setBounds(404, 307, 166, 14);
        startUpPane.add(lblWinner);
        
        JLabel lblCasualties = new JLabel("Casualties");
        lblCasualties.setHorizontalAlignment(SwingConstants.CENTER);
        lblCasualties.setForeground(Color.WHITE);
        lblCasualties.setBounds(682, 307, 166, 14);
        startUpPane.add(lblCasualties);
        
        JTextArea lootValueText = new JTextArea();
        lootValueText.setBounds(404, 416, 166, 22);
        startUpPane.add(lootValueText);
        
        JLabel lblLootValue = new JLabel("Loot Value");
        lblLootValue.setHorizontalAlignment(SwingConstants.CENTER);
        lblLootValue.setForeground(Color.WHITE);
        lblLootValue.setBounds(404, 391, 166, 14);
        startUpPane.add(lblLootValue);
        
        JComboBox<?> monthComboBox = new JComboBox(months);
        monthComboBox.setBounds(682, 415, 60, 27);
        startUpPane.add(monthComboBox);
        
        JComboBox<?> dayComboBox = new JComboBox(days);
        dayComboBox.setBounds(766, 415, 60, 27);
        startUpPane.add(dayComboBox);
        
        yearField = new JTextField();
        yearField.setBounds(855, 413, 110, 30);
        startUpPane.add(yearField);
        yearField.setColumns(10);
        
        JLabel lblMonth = new JLabel("Month");
        lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonth.setForeground(Color.WHITE);
        lblMonth.setBounds(682, 391, 60, 14);
        startUpPane.add(lblMonth);
        
        JLabel lblDay = new JLabel("Day");
        lblDay.setHorizontalAlignment(SwingConstants.CENTER);
        lblDay.setForeground(Color.WHITE);
        lblDay.setBounds(766, 391, 60, 14);
        startUpPane.add(lblDay);
        
        JLabel lblYear = new JLabel("Year");
        lblYear.setHorizontalAlignment(SwingConstants.CENTER);
        lblYear.setForeground(Color.WHITE);
        lblYear.setBounds(855, 391, 110, 14);
        startUpPane.add(lblYear);
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
                int month = (int) monthComboBox.getSelectedItem();
                int day = (int) dayComboBox.getSelectedItem();
                String strYear = yearField.getText();
                String name = nameText.getText();
                String location = locationText.getText();
                String casualtiesStr = casualtiesText.getText();
                String winner = winnerText.getText();
                String lootValueStr = lootValueText.getText();
                
                if(strYear.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter a year!");
                	return;
                }
                
                int year = Integer.parseInt(strYear);
                
                if(year < 1650 || year > 1740) {
                	JOptionPane.showMessageDialog(null, "Enter a year between 1650 and 1740!");
                	return;
                }
                
                int casualties = Integer.parseInt(casualtiesStr);
                
                if(casualties < 0) {
                	JOptionPane.showMessageDialog(null, "Cannot have negative casualties!");
                	return;
                }
                
                double lootAmount = Double.parseDouble(lootValueStr);
                
                if(lootAmount < 0) {
                	JOptionPane.showMessageDialog(null, "Cannot have negative loot value!");
                	return;
                }
                
                LocalDate date;
                
                try {
                	date = LocalDate.of(year, month, day);
                } catch (DateTimeException dte) {
                	JOptionPane.showMessageDialog(null, "Improper day and month!");
                	return;
                }

                PirateDatabase db = new PirateDatabase();
                
                try {
                	
                    db.connect();
                    
                    db.insertBattle(name, location, casualties, winner, lootAmount, date);
                    
                    JOptionPane.showMessageDialog(null, "Battle Added Successfully");

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
