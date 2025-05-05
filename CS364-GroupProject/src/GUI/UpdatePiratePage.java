package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class UpdatePiratePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable shipTable;
    private JTable pirateTable;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> pids;
    private ArrayList<Long> cids;
    private ArrayList<Long> sids;
    
    public UpdatePiratePage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Update a Pirate");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton updatePirateButton = new JButton("Update Pirate");
        updatePirateButton.setBounds(554, 545, 156, 43);
        startUpPane.add(updatePirateButton);
        
        JTextArea aliasText = new JTextArea();
        aliasText.setBounds(544, 311, 166, 22);
        startUpPane.add(aliasText);
        
        JTextArea roleText = new JTextArea();
        roleText.setBounds(544, 369, 166, 22);
        startUpPane.add(roleText);
        
        JLabel lblAlias = new JLabel("Alias");
        lblAlias.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlias.setForeground(Color.WHITE);
        lblAlias.setBounds(544, 286, 166, 14);
        startUpPane.add(lblAlias);
        
        JLabel lblAge = new JLabel("Age");
        lblAge.setHorizontalAlignment(SwingConstants.CENTER);
        lblAge.setForeground(Color.WHITE);
        lblAge.setBounds(544, 170, 166, 14);
        startUpPane.add(lblAge);
        
        JLabel lblNetWorth = new JLabel("Net Worth");
        lblNetWorth.setHorizontalAlignment(SwingConstants.CENTER);
        lblNetWorth.setForeground(Color.WHITE);
        lblNetWorth.setBounds(544, 228, 166, 14);
        startUpPane.add(lblNetWorth);
        
        JLabel lblRole = new JLabel("Role");
        lblRole.setHorizontalAlignment(SwingConstants.CENTER);
        lblRole.setForeground(Color.WHITE);
        lblRole.setBounds(544, 344, 166, 14);
        startUpPane.add(lblRole);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(734, 162, 448, 258);
        startUpPane.add(scrollPane);
        
        JLabel lblSelectACrew = new JLabel("Select a Ship");
        lblSelectACrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectACrew.setForeground(Color.WHITE);
        lblSelectACrew.setBounds(734, 137, 448, 14);
        startUpPane.add(lblSelectACrew);
        
        JTextArea crewNameText = new JTextArea();
        crewNameText.setBounds(1016, 453, 166, 22);
        startUpPane.add(crewNameText);
        
        JTextArea shipNameText = new JTextArea();
        shipNameText.setBounds(734, 453, 166, 22);
        startUpPane.add(shipNameText);
        
        JLabel lblShip = new JLabel("Ship");
        lblShip.setHorizontalAlignment(SwingConstants.CENTER);
        lblShip.setForeground(Color.WHITE);
        lblShip.setBounds(734, 431, 166, 14);
        startUpPane.add(lblShip);
        
        JLabel lblRole_1_1 = new JLabel("Crew");
        lblRole_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblRole_1_1.setForeground(Color.WHITE);
        lblRole_1_1.setBounds(1016, 431, 166, 14);
        startUpPane.add(lblRole_1_1);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(734, 486, 448, 22);
        startUpPane.add(btnSearch);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the table
        pirateTable = new JTable();
        pirateTable.setAutoCreateRowSorter(true);
        pirateTable.setShowVerticalLines(false);
        pirateTable.setRowHeight(30);
        pirateTable.setRowSelectionAllowed(true);
        
        scrollPane.setViewportView(pirateTable);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(65, 162, 448, 258);
        startUpPane.add(scrollPane_1);
        
        JLabel lblSelectAPirate = new JLabel("Select a Pirate");
        lblSelectAPirate.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectAPirate.setForeground(Color.WHITE);
        lblSelectAPirate.setBounds(65, 137, 448, 14);
        startUpPane.add(lblSelectAPirate);
        
        JLabel lblPirate = new JLabel("Pirate");
        lblPirate.setHorizontalAlignment(SwingConstants.CENTER);
        lblPirate.setForeground(Color.WHITE);
        lblPirate.setBounds(65, 431, 166, 14);
        startUpPane.add(lblPirate);
        
        JTextArea pirateNameText = new JTextArea();
        pirateNameText.setBounds(65, 453, 166, 22);
        startUpPane.add(pirateNameText);
        
        JLabel lblRole_1_1_1 = new JLabel("Crew");
        lblRole_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblRole_1_1_1.setForeground(Color.WHITE);
        lblRole_1_1_1.setBounds(347, 431, 166, 14);
        startUpPane.add(lblRole_1_1_1);
        
        JTextArea crewNameText_1 = new JTextArea();
        crewNameText_1.setBounds(347, 453, 166, 22);
        startUpPane.add(crewNameText_1);
        
        JButton btnSearch_1 = new JButton("Search");
        btnSearch_1.setBounds(65, 486, 448, 22);
        startUpPane.add(btnSearch_1);
        
        JSpinner ageSpinner = new JSpinner();
        ageSpinner.setModel(new SpinnerNumberModel(16, 16, 120, 1));
        ageSpinner.setBounds(599, 195, 56, 22);
        startUpPane.add(ageSpinner);
        
        JSpinner netWorthSpinner = new JSpinner();
        netWorthSpinner.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
        netWorthSpinner.setBounds(544, 253, 166, 22);
        startUpPane.add(netWorthSpinner);
        
        loadPirates("", "");
        loadShips("", "");
        
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadShips(shipNameText.getText(), crewNameText.getText());
            }
        });
        
        
        
        updatePirateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int age = (int)ageSpinner.getValue();
                String alias = aliasText.getText();
                double netWorth = (double)netWorthSpinner.getValue();
                String role = roleText.getText();
                int row = table.getSelectedRow();
                
                if(role.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter in a Role");
                }
                
                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet c;
                	
                    db.connect();
                    
                    db.updatePirate(fName, mName, lName, age, alias, netWorth, role, cids.get(row), sids.get(row));
                    
                    JOptionPane.showMessageDialog(null, "Pirate Updated Successfully");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    
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
        	
        	ResultSet rs = db.getShipByNameAndCrew(pirateName, crewName);
            DefaultTableModel model = new DefaultTableModel();
            pids = new ArrayList<Long>();
            
            model.addColumn("Name");
            model.addColumn("Alias");
            model.addColumn("Crew");
            

            while (rs.next()) {
                Object[] rowData = new Object[2];

                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                
                pids.add(rs.getLong(3));

                model.addRow(rowData);
            }
            
            rs.close();
            pirateTable.setModel(model);
            pirateTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            pirateTable.getColumnModel().getColumn(1).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
    
    private void loadShips(String shipName, String crewName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getShipByNameAndCrew(shipName, crewName);
            DefaultTableModel model = new DefaultTableModel();
            cids = new ArrayList<Long>();
            sids = new ArrayList<Long>();
            
            model.addColumn("Ship");
            model.addColumn("Crew");
            

            while (rs.next()) {
                Object[] rowData = new Object[2];

                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                
                sids.add(rs.getLong(3));
                cids.add(rs.getLong(4));

                model.addRow(rowData);
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
}