package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.JCheckBox;

public class CreateParticipatePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable shipTable;
    private JTable battleTable;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> bids;
    private ArrayList<Long> sids;
    
    
    public CreateParticipatePage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create a Participation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton createPirateButton = new JButton("Log Participation");
        createPirateButton.setBounds(554, 545, 156, 43);
        startUpPane.add(createPirateButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(134, 162, 331, 258);
        startUpPane.add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(531, 162, 594, 258);
        startUpPane.add(scrollPane_1);
        
        JLabel lblSelectACrew = new JLabel("Select a Ship");
        lblSelectACrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectACrew.setForeground(Color.WHITE);
        lblSelectACrew.setBounds(134, 137, 331, 14);
        startUpPane.add(lblSelectACrew);
        
        JTextArea battleNameText = new JTextArea();
        battleNameText.setBounds(732, 431, 166, 22);
        startUpPane.add(battleNameText);
        
        JTextArea shipNameText = new JTextArea();
        shipNameText.setBounds(222, 431, 166, 22);
        startUpPane.add(shipNameText);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(222, 464, 166, 22);
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
        
        // Create the battleTable
        battleTable = new JTable();
        battleTable.setAutoCreateRowSorter(true);
        battleTable.setShowVerticalLines(false);
        battleTable.setRowHeight(30);
        battleTable.setRowSelectionAllowed(true);
        
        scrollPane.setViewportView(shipTable);
        scrollPane_1.setViewportView(battleTable);
        
        JButton btnSearch_1 = new JButton("Search");
        btnSearch_1.setBounds(732, 464, 166, 22);
        startUpPane.add(btnSearch_1);
        
        JLabel lblSelectAnIsland = new JLabel("Select a Battle");
        lblSelectAnIsland.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectAnIsland.setForeground(Color.WHITE);
        lblSelectAnIsland.setBounds(531, 137, 594, 14);
        startUpPane.add(lblSelectAnIsland);
        
        JCheckBox sunkCheckBox = new JCheckBox("Ship Was Sunk");
        sunkCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        sunkCheckBox.setBounds(554, 508, 156, 23);
        startUpPane.add(sunkCheckBox);
        
        loadShips("");
        loadBattles("");
        
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadShips(shipNameText.getText());
            }
        });
        
        btnSearch_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadBattles(battleNameText.getText());
            }
        });
        
        createPirateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
                int sRow = shipTable.getSelectedRow();
                int bRow = battleTable.getSelectedRow();
                boolean wasSunk = sunkCheckBox.isSelected();
                
                if(sRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select a Ship!");
                	return;
                }
                
                if(bRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select an Island!");
                	return;
                } 
                
                long bid = bids.get(bRow);
                long sid = sids.get(sRow);
                
                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet rs;
                	
                    db.connect();
                    
                    rs = db.getParticipateByIDs(bid, sid);
                    
                    if(rs.next()) {
                    	JOptionPane.showMessageDialog(null, "Participation already recorded!");
                    	return;
                    }
                    
                    db.insertParticipate(bid, sid, wasSunk);
                    
                    JOptionPane.showMessageDialog(null, "Participation Added Successfully");

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
        	
        	ResultSet rs = db.getShipAndCrewByShipName(shipName);
            DefaultTableModel model = new DefaultTableModel();
            sids = new ArrayList<Long>();
            
            model.addColumn("Ship");
            model.addColumn("Crew");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[2];
                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);

                model.addRow(rowData);
                sids.add(rs.getLong(3));
            }
            
            rs.close();
            shipTable.setModel(model);
            shipTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            shipTable.getColumnModel().getColumn(1).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
    
    private void loadBattles(String battleName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getBattleByLikeName(battleName);
            DefaultTableModel model = new DefaultTableModel();
            bids = new ArrayList<Long>();
            
            model.addColumn("Battle");
            model.addColumn("Winner");
            model.addColumn("Location");
            model.addColumn("Date");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[4];
                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                rowData[2] = rs.getObject(3);
                rowData[3] = rs.getObject(4);

                model.addRow(rowData);
                bids.add(rs.getLong(5));
            }
            
            rs.close();
            battleTable.setModel(model);
            battleTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            battleTable.getColumnModel().getColumn(1).setCellRenderer(centerRender);
            battleTable.getColumnModel().getColumn(2).setCellRenderer(centerRender);
            battleTable.getColumnModel().getColumn(3).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}