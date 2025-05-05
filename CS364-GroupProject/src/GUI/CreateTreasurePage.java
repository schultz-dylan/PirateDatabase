package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.PirateDatabase;

import javax.swing.JTextArea;
import javax.swing.JTable;


public class CreateTreasurePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable islandTable;
    private JTable pirateTable;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> iids;
    private ArrayList<Long> pids;
    
    private String treasure = "<html>                            _.--.\r\n"
    		+ "                        _.-'_:-'||\r\n"
    		+ "                    _.-'_.-::::'||\r\n"
    		+ "               _.-:'_.-::::::'  ||\r\n"
    		+ "             .'`-.-:::::::'     ||\r\n"
    		+ "            /.'`;|:::::::'      ||_\r\n"
    		+ "           ||   ||::::::'     _.;._'-._\r\n"
    		+ "           ||   ||:::::'  _.-!oo @.!-._'-.\r\n"
    		+ "           \\'.  ||:::::.-!()oo @!()@.-'_.|\r\n"
    		+ "            '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\r\n"
    		+ "              `>'-.!@%()@'@_%-'_.-o _.|'||\r\n"
    		+ "               ||-._'-.@.-'_.-' _.-o  |'||\r\n"
    		+ "               ||=[ '-._.-\\U/.-'    o |'||\r\n"
    		+ "               || '-.]=|| |'|      o  |'||\r\n"
    		+ "               ||      || |'|        _| ';\r\n"
    		+ "               ||      || |'|    _.-'_.-'\r\n"
    		+ "               |'-._   || |'|_.-'_.-'\r\n"
    		+ "                '-._'-.|| |' `_.-'\r\n"
    		+ "                    '-.||_/.-'<html>";
    
    public CreateTreasurePage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create a Treasure");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Create Treasure");
        btnNewButton.setBounds(528, 566, 156, 43);
        startUpPane.add(btnNewButton);
        
        JTextArea treasureText = new JTextArea();
        treasureText.setBounds(172, 242, 166, 22);
        startUpPane.add(treasureText);
        
        JTextArea locationText = new JTextArea();
        locationText.setBounds(172, 336, 166, 22);
        startUpPane.add(locationText);
        
        
        JLabel lblNewLabel = new JLabel("Treasure Worth");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(172, 217, 166, 14);
        startUpPane.add(lblNewLabel);
        
        JLabel lblMiddleName = new JLabel("Location");
        lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
        lblMiddleName.setForeground(Color.WHITE);
        lblMiddleName.setBounds(172, 311, 166, 14);
        startUpPane.add(lblMiddleName);
        
        JLabel lblANewCrew = new JLabel("A NEW TREASURE IS BURIED");
        lblANewCrew.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblANewCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblANewCrew.setForeground(Color.WHITE);
        lblANewCrew.setBounds(338, 55, 587, 98);
        startUpPane.add(lblANewCrew);
        
        // Create scroll panes for the tables
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(374, 209, 221, 251);
        startUpPane.add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(652, 209, 545, 251);
        startUpPane.add(scrollPane_1);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the table
        islandTable = new JTable();
        islandTable.setAutoCreateRowSorter(true);
        islandTable.setShowVerticalLines(false);
        islandTable.setRowHeight(30);
        islandTable.setRowSelectionAllowed(true);
        
        // Create the table
        pirateTable = new JTable();
        pirateTable.setAutoCreateRowSorter(true);
        pirateTable.setShowVerticalLines(false);
        pirateTable.setRowHeight(30);
        pirateTable.setRowSelectionAllowed(true);
        
        // Make the tables scrollable
        scrollPane.setViewportView(islandTable);
        scrollPane_1.setViewportView(pirateTable);
        
        JTextArea islandSearchText = new JTextArea();
        islandSearchText.setBounds(405, 471, 166, 22);
        startUpPane.add(islandSearchText);
        
        JLabel lblCrew = new JLabel("Where be this treasure?");
        lblCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrew.setForeground(Color.WHITE);
        lblCrew.setBounds(374, 187, 221, 14);
        startUpPane.add(lblCrew);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(405, 504, 166, 32);
        startUpPane.add(btnSearch);
        
        JLabel lblSelectAPirate = new JLabel("Which pirate buried it?");
        lblSelectAPirate.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectAPirate.setForeground(Color.WHITE);
        lblSelectAPirate.setBounds(652, 187, 545, 14);
        startUpPane.add(lblSelectAPirate);
        
        JTextArea pirateFNameText = new JTextArea();
        pirateFNameText.setBounds(652, 471, 125, 22);
        startUpPane.add(pirateFNameText);
        
        JButton btnSearch_1 = new JButton("Search");
        btnSearch_1.setBounds(842, 544, 166, 32);
        startUpPane.add(btnSearch_1);
        
        JTextArea pirateLNameText = new JTextArea();
        pirateLNameText.setBounds(787, 471, 132, 22);
        startUpPane.add(pirateLNameText);
        
        JTextArea pirateAliasText = new JTextArea();
        pirateAliasText.setBounds(929, 471, 125, 22);
        startUpPane.add(pirateAliasText);
        
        JTextArea pirateCrewText = new JTextArea();
        pirateCrewText.setBounds(1064, 471, 133, 22);
        startUpPane.add(pirateCrewText);
        
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        lblFirstName.setForeground(Color.WHITE);
        lblFirstName.setBounds(662, 504, 102, 14);
        startUpPane.add(lblFirstName);
        
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
        lblLastName.setForeground(Color.WHITE);
        lblLastName.setBounds(787, 504, 132, 14);
        startUpPane.add(lblLastName);
        
        JLabel lblAlias = new JLabel("Alias");
        lblAlias.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlias.setForeground(Color.WHITE);
        lblAlias.setBounds(929, 504, 125, 14);
        startUpPane.add(lblAlias);
        
        JLabel lblCrew_1 = new JLabel("Crew");
        lblCrew_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrew_1.setForeground(Color.WHITE);
        lblCrew_1.setBounds(1064, 504, 133, 14);
        startUpPane.add(lblCrew_1);
        
        loadIslands("");
        loadPirates("", "", "", "");
        
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadIslands(islandSearchText.getText());
            }
        });
        
        btnSearch_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadPirates(pirateFNameText.getText(), pirateLNameText.getText(), pirateAliasText.getText(), pirateCrewText.getText());
            }
        });
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String worthStr = treasureText.getText();
                String location = locationText.getText();
                int iRow = islandTable.getSelectedRow();
                int pRow = pirateTable.getSelectedRow();
                
                if(worthStr.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter in a worth, ye scallywag!");
                	return;
                }
                
                if(iRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select an island, ye ruffian!");
                	return;
                }
                
                if(pRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select a pirate, ye rapscallion!");
                	return;
                }
                
                double value = Double.parseDouble(worthStr);
                
                if(value < 1) {
                	JOptionPane.showMessageDialog(null, "Enter in a positive treasure value!");
                	return;
                }
                
                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet c;
                	
                    db.connect();
                    
                    db.insertTreasure(value, location, iids.get(iRow), pids.get(pRow));
                    
                    JOptionPane.showMessageDialog(null, "Treasure Added Successfully");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    
                } finally {
                    db.disconnect();
                }
            }
        });
        
        setVisible(true);
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
    
    private void loadPirates(String fName, String lName, String alias, String crewName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getPirateByNameAndCrew(fName, lName, alias, crewName);
            DefaultTableModel model = new DefaultTableModel();
            pids = new ArrayList<Long>();
            
            model.addColumn("First Name");
            model.addColumn("Middle Name");
            model.addColumn("Last Name");
            model.addColumn("Alias");
            model.addColumn("Crew");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[5];
                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                rowData[2] = rs.getObject(3);
                rowData[3] = rs.getObject(4);
                rowData[4] = rs.getObject(5);

                model.addRow(rowData);
                pids.add(rs.getLong(6));
            }
            
            rs.close();
            pirateTable.setModel(model);
            pirateTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            pirateTable.getColumnModel().getColumn(1).setCellRenderer(centerRender);
            pirateTable.getColumnModel().getColumn(2).setCellRenderer(centerRender);
            pirateTable.getColumnModel().getColumn(3).setCellRenderer(centerRender);
            pirateTable.getColumnModel().getColumn(4).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}