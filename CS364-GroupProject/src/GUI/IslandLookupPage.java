package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.PirateDatabase;

import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class IslandLookupPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable table;
    DefaultTableCellRenderer centerRender;
    private JTextField locationText;
    
    public IslandLookupPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Island Lookup");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Search");
        btnNewButton.setBounds(532, 586, 156, 43);
        startUpPane.add(btnNewButton);
        
        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(341, 137, 536, 347);
        startUpPane.add(scrollPane);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the table
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.setShowVerticalLines(false);
        table.setRowHeight(30);
        table.setRowSelectionAllowed(true);
        
        // Make the table scrollable
        scrollPane.setViewportView(table);
        
        locationText = new JTextField();
        locationText.setColumns(10);
        locationText.setBounds(430, 495, 156, 20);
        startUpPane.add(locationText);
        
        JLabel lblNewLabel = new JLabel("Location");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(430, 526, 156, 14);
        startUpPane.add(lblNewLabel);
        
        JLabel lblWhatBeThe = new JLabel("WHAT BE THE MOST VALUABLE ISLANDS?");
        lblWhatBeThe.setFont(new Font("Tahoma", Font.PLAIN, 45));
        lblWhatBeThe.setHorizontalAlignment(SwingConstants.CENTER);
        lblWhatBeThe.setForeground(Color.WHITE);
        lblWhatBeThe.setBounds(10, 11, 1244, 115);
        startUpPane.add(lblWhatBeThe);
        
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(1, 1, 50, 1));
        spinner.setBounds(646, 495, 96, 20);
        startUpPane.add(spinner);
        
        JLabel lblNumberDiplayed = new JLabel("Number Diplayed");
        lblNumberDiplayed.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumberDiplayed.setForeground(Color.WHITE);
        lblNumberDiplayed.setBounds(646, 523, 98, 20);
        startUpPane.add(lblNumberDiplayed);
        
        
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadLocations(locationText.getText(), (int)spinner.getValue());
            }
        });
        
        setVisible(true);
        
    }
    
    private void loadLocations(String location, int limit) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getIslandByLocation(location, limit);
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Island");
            model.addColumn("Value");
            model.addColumn("Location");

            while (rs.next()) {
                Object[] rowData = new Object[9];

                for(int i = 0; i < 3; i++) {
                	rowData[i] = rs.getObject(i + 1);
                }

                model.addRow(rowData);
            }
            
            rs.close();
            table.setModel(model);
            for(int i = 0; i < 3; i++) {
            	table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}