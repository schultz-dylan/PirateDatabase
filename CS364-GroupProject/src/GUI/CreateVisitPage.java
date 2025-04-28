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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.PirateDatabase;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CreateVisitPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable shipTable;
    private JTable islandTable;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> iids;
    private ArrayList<Long> sids;
    private Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private JTextField yearField;
    
    
    public CreateVisitPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create a Visit");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton createPirateButton = new JButton("Log Visit");
        createPirateButton.setBounds(554, 545, 156, 43);
        startUpPane.add(createPirateButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(597, 162, 211, 258);
        startUpPane.add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(835, 162, 211, 258);
        startUpPane.add(scrollPane_1);
        
        JLabel lblSelectACrew = new JLabel("Select a Ship");
        lblSelectACrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectACrew.setForeground(Color.WHITE);
        lblSelectACrew.setBounds(597, 137, 211, 14);
        startUpPane.add(lblSelectACrew);
        
        JTextArea islandNameText = new JTextArea();
        islandNameText.setBounds(855, 431, 166, 22);
        startUpPane.add(islandNameText);
        
        JTextArea shipNameText = new JTextArea();
        shipNameText.setBounds(622, 431, 166, 22);
        startUpPane.add(shipNameText);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(622, 464, 166, 22);
        startUpPane.add(btnSearch);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the shipTable
        shipTable = new JTable();
        shipTable.setAutoCreateRowSorter(true);
        shipTable.setShowVerticalLines(false);
        shipTable.setRowHeight(30);
        shipTable.setRowSelectionAllowed(true);
        
        // Create the islandTable
        islandTable = new JTable();
        islandTable.setAutoCreateRowSorter(true);
        islandTable.setShowVerticalLines(false);
        islandTable.setRowHeight(30);
        islandTable.setRowSelectionAllowed(true);
        
        scrollPane.setViewportView(shipTable);
        scrollPane_1.setViewportView(islandTable);
        
        JButton btnSearch_1 = new JButton("Search");
        btnSearch_1.setBounds(855, 464, 166, 22);
        startUpPane.add(btnSearch_1);
        
        JLabel lblSelectAnIsland = new JLabel("Select an Island");
        lblSelectAnIsland.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectAnIsland.setForeground(Color.WHITE);
        lblSelectAnIsland.setBounds(835, 137, 211, 14);
        startUpPane.add(lblSelectAnIsland);
        
        JComboBox<?> monthComboBox = new JComboBox(months);
        monthComboBox.setBounds(262, 294, 60, 27);
        startUpPane.add(monthComboBox);
        
        JComboBox<?> dayComboBox = new JComboBox(days);
        dayComboBox.setBounds(332, 294, 60, 27);
        startUpPane.add(dayComboBox);
        
        yearField = new JTextField();
        yearField.setBounds(402, 292, 110, 30);
        startUpPane.add(yearField);
        yearField.setColumns(10);
        
        JLabel lblMonth = new JLabel("Month");
        lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonth.setForeground(Color.WHITE);
        lblMonth.setBounds(262, 269, 60, 14);
        startUpPane.add(lblMonth);
        
        JLabel lblDay = new JLabel("Day");
        lblDay.setHorizontalAlignment(SwingConstants.CENTER);
        lblDay.setForeground(Color.WHITE);
        lblDay.setBounds(332, 269, 60, 14);
        startUpPane.add(lblDay);
        
        JLabel lblYear = new JLabel("Year");
        lblYear.setHorizontalAlignment(SwingConstants.CENTER);
        lblYear.setForeground(Color.WHITE);
        lblYear.setBounds(402, 269, 110, 14);
        startUpPane.add(lblYear);
        
        loadShips("");
        loadIslands("");
        
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadShips(shipNameText.getText());
            }
        });
        
        btnSearch_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadIslands(islandNameText.getText());
            }
        });
        
        createPirateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
                int sRow = shipTable.getSelectedRow();
                int iRow = islandTable.getSelectedRow();
                int month = (int) monthComboBox.getSelectedItem();
                int day = (int) dayComboBox.getSelectedItem();
                String strYear = yearField.getText();
                
                if(sRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select a Ship!");
                	return;
                }
                
                if(iRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select an Island!");
                	return;
                }
                
                if(strYear.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter a year!");
                	return;
                }
                
                int year = Integer.parseInt(strYear);
                
                if(year < 1650 || year > 1740) {
                	JOptionPane.showMessageDialog(null, "Enter a year between 1650 and 1740!");
                	return;
                }
                
                
                LocalDate date;
                
                try {
                	date = LocalDate.of(year, month, day);
                } catch (DateTimeException dte) {
                	JOptionPane.showMessageDialog(null, "Improper day and month!");
                	return;
                }
                
                long iid = iids.get(iRow);
                long sid = sids.get(sRow);
                

                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet rs;
                	
                    db.connect();
                    
                    rs = db.getVisitByDateAndID(iid, sid, date);
                    
                    if(rs.next()) {
                    	JOptionPane.showMessageDialog(null, "Visit already recorded!");
                    	return;
                    }
                    
                    db.insertVisit(iid, sid, date);
                    
                    JOptionPane.showMessageDialog(null, "Visit Added Successfully");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    
                } finally {
                    db.disconnect();
                }
            }
        });
        
        setVisible(true);
    }
    

    
    private void loadShips(String shipName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getShipByLikeName(shipName);
            DefaultTableModel model = new DefaultTableModel();
            sids = new ArrayList<Long>();
            
            model.addColumn("Ship");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[1];
                rowData[0] = rs.getObject(1);

                model.addRow(rowData);
                sids.add(rs.getLong(2));
            }
            
            rs.close();
            shipTable.setModel(model);
            shipTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
    
    private void loadIslands(String islandName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getIslandByLikeName(islandName);
            DefaultTableModel model = new DefaultTableModel();
            iids = new ArrayList<Long>();
            
            model.addColumn("Island");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[1];
                rowData[0] = rs.getObject(1);

                model.addRow(rowData);
                iids.add(rs.getLong(2));
            }
            
            rs.close();
            islandTable.setModel(model);
            islandTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}
