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

public class CreateShipPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable table;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> cids;
    
    public CreateShipPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create a Ship");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Create Ship");
        btnNewButton.setBounds(550, 523, 156, 43);
        startUpPane.add(btnNewButton);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(410, 242, 166, 22);
        startUpPane.add(textArea);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(410, 339, 166, 22);
        startUpPane.add(textArea_1);
        
        JLabel lblNewLabel = new JLabel("Ship's Name");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(410, 217, 166, 14);
        startUpPane.add(lblNewLabel);
        
        JLabel lblMiddleName = new JLabel("Ship's Type");
        lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
        lblMiddleName.setForeground(Color.WHITE);
        lblMiddleName.setBounds(410, 314, 166, 14);
        startUpPane.add(lblMiddleName);
        
        JLabel lblANewCrew = new JLabel("A NEW SHIP SAILS THE HIGH SEAS");
        lblANewCrew.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblANewCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblANewCrew.setForeground(Color.WHITE);
        lblANewCrew.setBounds(338, 55, 587, 98);
        startUpPane.add(lblANewCrew);
        
        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(651, 217, 221, 251);
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
        
        JTextArea textArea_1_1 = new JTextArea();
        textArea_1_1.setBounds(903, 217, 166, 22);
        startUpPane.add(textArea_1_1);
        
        JLabel lblCrew = new JLabel("Select a Crew");
        lblCrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrew.setForeground(Color.WHITE);
        lblCrew.setBounds(651, 187, 221, 14);
        startUpPane.add(lblCrew);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(903, 250, 166, 32);
        startUpPane.add(btnSearch);
        
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

                String name = textArea.getText();
                String type = textArea_1.getText();
                int row = table.getSelectedRow();
                
                if(name.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter in a name, ye scallywag!");
                	return;
                }
                
                if(type.length() < 1) {
                	JOptionPane.showMessageDialog(null, "Enter in a type, ye rapscallion!");
                	return;
                }
                
                if(row < 0) {
                	JOptionPane.showMessageDialog(null, "Select a crew, ye ruffian!");
                	return;
                }
                
                
                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet c;
                	
                    db.connect();
                    c = db.getShipByName(name);
                    
                    if(c.next()) {
                    	JOptionPane.showMessageDialog(null, "A ship already sails with that name!");
                    	return;
                    }
                    
                    db.insertShip(name, type, cids.get(row));
                    
                    JOptionPane.showMessageDialog(null, "Ship Added Successfully");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    
                } finally {
                    db.disconnect();
                }
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
            table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}