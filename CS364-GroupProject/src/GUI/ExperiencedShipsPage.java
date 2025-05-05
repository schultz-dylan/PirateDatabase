package GUI;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.PirateDatabase;

import javax.swing.JTextArea;
import javax.swing.JTable;

public class ExperiencedShipsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable crewTable;
    private JTable shipTable;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> cids;
    
    public ExperiencedShipsPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("View Experienced Ships");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Select Crew");
        btnNewButton.setBounds(546, 574, 156, 43);
        startUpPane.add(btnNewButton);
        
        JLabel lblANewCrew = new JLabel("WHAT BE THE MOST EXPERIENCED SHIPS?");
        lblANewCrew.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblANewCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblANewCrew.setForeground(Color.WHITE);
        lblANewCrew.setBounds(10, 55, 1244, 98);
        startUpPane.add(lblANewCrew);
        
        // Create scroll panes for the crewTable and shipTable
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(178, 214, 359, 251);
        startUpPane.add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(634, 214, 509, 251);
        startUpPane.add(scrollPane_1);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the crewTable
        crewTable = new JTable();
        crewTable.setAutoCreateRowSorter(true);
        crewTable.setShowVerticalLines(false);
        crewTable.setRowHeight(30);
        crewTable.setRowSelectionAllowed(true);
        
        // Make the crewTable scrollable
        scrollPane.setViewportView(crewTable);
        
        // Create the shipTable
        shipTable = new JTable();
        shipTable.setAutoCreateRowSorter(true);
        shipTable.setShowVerticalLines(false);
        shipTable.setRowHeight(30);
        shipTable.setRowSelectionAllowed(true);
        
        // Make the shipTable scrollable
        scrollPane_1.setViewportView(shipTable);
        
        JTextArea textArea_1_1 = new JTextArea();
        textArea_1_1.setBounds(288, 476, 166, 22);
        startUpPane.add(textArea_1_1);
        
        JLabel lblCrew = new JLabel("Select a Crew");
        lblCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrew.setForeground(Color.WHITE);
        lblCrew.setBounds(178, 189, 359, 14);
        startUpPane.add(lblCrew);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(288, 509, 166, 28);
        startUpPane.add(btnSearch);
        
        JLabel lblShipsThatHave = new JLabel("Ships With More Battles than Average");
        lblShipsThatHave.setHorizontalAlignment(SwingConstants.CENTER);
        lblShipsThatHave.setForeground(Color.WHITE);
        lblShipsThatHave.setBounds(634, 189, 509, 14);
        startUpPane.add(lblShipsThatHave);
        
        loadCrews("");
        
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadCrews(textArea_1_1.getText());
            }
        });
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = crewTable.getSelectedRow();
                
                if(row < 0) {
                	JOptionPane.showMessageDialog(null, "Select a crew, ye ruffian!");
                	return;
                }
                
                
                loadShips(cids.get(row));
                
            }
        });
        
        setVisible(true);
    }
    
    private void loadCrews(String crewName) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getCrewByLikeName(crewName);
            DefaultTableModel model = new DefaultTableModel();
            cids = new ArrayList<Long>();
            
            model.addColumn("Name");
            
            while (rs.next()) {
                Object[] rowData = new Object[1];

                rowData[0] = rs.getObject(1);
                
                cids.add(rs.getLong(2));

                model.addRow(rowData);
            }
            
            rs.close();
            crewTable.setModel(model);
            crewTable.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
    
    private void loadShips(long cid) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getExperiencedShips(cid);
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Ship");
            model.addColumn("Number of Battles");
            
            while (rs.next()) {
                Object[] rowData = new Object[2];

                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                
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