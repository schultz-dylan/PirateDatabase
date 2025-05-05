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
import javax.swing.JCheckBox;

public class DigTreasure extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable treasureTable;
    private JTable pirateTable;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> pids;
    private ArrayList<Long> tids;
    
    
    public DigTreasure() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Dig For Treasure");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton digTreasureButton = new JButton("Dig For Treasure!");
        digTreasureButton.setBounds(554, 545, 156, 43);
        startUpPane.add(digTreasureButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(134, 162, 397, 258);
        startUpPane.add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(541, 162, 584, 258);
        startUpPane.add(scrollPane_1);
        
        JLabel lblSelectACrew = new JLabel("Who collected this treasure?");
        lblSelectACrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectACrew.setForeground(Color.WHITE);
        lblSelectACrew.setBounds(134, 137, 397, 14);
        startUpPane.add(lblSelectACrew);
        
        JTextArea islandNameText = new JTextArea();
        islandNameText.setBounds(732, 431, 166, 22);
        startUpPane.add(islandNameText);
        
        JTextArea pirateNameText = new JTextArea();
        pirateNameText.setBounds(134, 431, 166, 22);
        startUpPane.add(pirateNameText);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(250, 489, 166, 22);
        startUpPane.add(btnSearch);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the pirateTable
        pirateTable = new JTable();
        pirateTable.setAutoCreateRowSorter(true);
        pirateTable.setShowVerticalLines(false);
        pirateTable.setRowHeight(30);
        pirateTable.setRowSelectionAllowed(true);
        
        // Create the treasureTable
        treasureTable = new JTable();
        treasureTable.setAutoCreateRowSorter(true);
        treasureTable.setShowVerticalLines(false);
        treasureTable.setRowHeight(30);
        treasureTable.setRowSelectionAllowed(true);
        
        scrollPane.setViewportView(pirateTable);
        scrollPane_1.setViewportView(treasureTable);
        
        JButton btnSearch_1 = new JButton("Search by Island Name");
        btnSearch_1.setBounds(732, 464, 166, 22);
        startUpPane.add(btnSearch_1);
        
        JLabel lblSelectAnIsland = new JLabel("Which Treasure was dug up?");
        lblSelectAnIsland.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectAnIsland.setForeground(Color.WHITE);
        lblSelectAnIsland.setBounds(531, 137, 594, 14);
        startUpPane.add(lblSelectAnIsland);
        
        JTextArea crewNameText = new JTextArea();
        crewNameText.setBounds(365, 431, 166, 22);
        startUpPane.add(crewNameText);
        
        JLabel lblPiratesName = new JLabel("Pirate's Name");
        lblPiratesName.setHorizontalAlignment(SwingConstants.CENTER);
        lblPiratesName.setForeground(Color.WHITE);
        lblPiratesName.setBounds(134, 464, 166, 14);
        startUpPane.add(lblPiratesName);
        
        JLabel lblCrewsName = new JLabel("Crew's Name");
        lblCrewsName.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrewsName.setForeground(Color.WHITE);
        lblCrewsName.setBounds(365, 464, 166, 14);
        startUpPane.add(lblCrewsName);
        
        loadPirates("", "");
        loadTreasures("");
        
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadPirates(pirateNameText.getText(), crewNameText.getText());
            }
        });
        
        btnSearch_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadTreasures(islandNameText.getText());
            }
        });
        
        digTreasureButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
                int pRow = pirateTable.getSelectedRow();
                int tRow = treasureTable.getSelectedRow();
                
                if(pRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select a Pirate!");
                	return;
                }
                
                if(tRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select a Treasure!");
                	return;
                } 
                
                long tid = tids.get(tRow);
                long pid = pids.get(pRow);
                
                PirateDatabase db = new PirateDatabase();
                
                try {
                    db.connect();
                    
                    double value = (double) treasureTable.getValueAt(tRow, 3);
                    
                    db.updatePirateNetWorth(pid, value);
                    db.deleteTreasure(tid);
                    
                    JOptionPane.showMessageDialog(null, "Treasure dig logged succesfully!");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Error");
                } finally {
                    db.disconnect();
                }
            }
        });
        
        setVisible(true);
    }
    

    
    private void loadPirates(String pirateName, String crewName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getPirateAndCrewByNameAndCrew(pirateName, crewName);
            DefaultTableModel model = new DefaultTableModel();
            pids = new ArrayList<Long>();
            
            model.addColumn("Name");
            model.addColumn("Alias");
            model.addColumn("Crew");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[3];
                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                rowData[2] = rs.getObject(3);

                model.addRow(rowData);
                pids.add(rs.getLong(4));
            }
            
            rs.close();
            pirateTable.setModel(model);
            pirateTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            pirateTable.getColumnModel().getColumn(1).setCellRenderer(centerRender);
            pirateTable.getColumnModel().getColumn(2).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
    
    private void loadTreasures(String islandName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getTreasureByIsland(islandName);
            DefaultTableModel model = new DefaultTableModel();
            tids = new ArrayList<Long>();
            
            model.addColumn("Island");
            model.addColumn("Location");
            model.addColumn("Value");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[4];
                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                rowData[2] = rs.getObject(3);

                model.addRow(rowData);
                tids.add(rs.getLong(4));
            }
            
            rs.close();
            treasureTable.setModel(model);
            treasureTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            treasureTable.getColumnModel().getColumn(1).setCellRenderer(centerRender);
            treasureTable.getColumnModel().getColumn(2).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}