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

public class CreatePiratePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable table;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> cids;
    private ArrayList<Long> sids;
    
    public CreatePiratePage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Create a Pirate");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton createPirateButton = new JButton("Create Pirate");
        createPirateButton.setBounds(554, 545, 156, 43);
        startUpPane.add(createPirateButton);
        
        JTextArea fNameText = new JTextArea();
        fNameText.setBounds(151, 165, 166, 22);
        startUpPane.add(fNameText);
        
        JTextArea mNameText = new JTextArea();
        mNameText.setBounds(366, 165, 166, 22);
        startUpPane.add(mNameText);
        
        JTextArea lNameText = new JTextArea();
        lNameText.setBounds(151, 234, 166, 22);
        startUpPane.add(lNameText);
        
        JTextArea ageText = new JTextArea();
        ageText.setBounds(366, 234, 166, 22);
        startUpPane.add(ageText);
        
        JTextArea aliasText = new JTextArea();
        aliasText.setBounds(151, 301, 166, 22);
        startUpPane.add(aliasText);
        
        JTextArea netWorthText = new JTextArea();
        netWorthText.setBounds(366, 301, 166, 22);
        startUpPane.add(netWorthText);
        
        JTextArea roleText = new JTextArea();
        roleText.setBounds(258, 374, 166, 22);
        startUpPane.add(roleText);
        
        JLabel lblNewLabel = new JLabel("First Name");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(151, 140, 166, 14);
        startUpPane.add(lblNewLabel);
        
        JLabel lblMiddleName = new JLabel("Middle Name");
        lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
        lblMiddleName.setForeground(Color.WHITE);
        lblMiddleName.setBounds(366, 140, 166, 14);
        startUpPane.add(lblMiddleName);
        
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setForeground(Color.WHITE);
        lblLastName.setBounds(204, 209, 66, 14);
        startUpPane.add(lblLastName);
        
        JLabel lblAlias = new JLabel("Alias");
        lblAlias.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlias.setForeground(Color.WHITE);
        lblAlias.setBounds(151, 276, 166, 14);
        startUpPane.add(lblAlias);
        
        JLabel lblAge = new JLabel("Age");
        lblAge.setHorizontalAlignment(SwingConstants.CENTER);
        lblAge.setForeground(Color.WHITE);
        lblAge.setBounds(366, 209, 166, 14);
        startUpPane.add(lblAge);
        
        JLabel lblNetWorth = new JLabel("Net Worth");
        lblNetWorth.setHorizontalAlignment(SwingConstants.CENTER);
        lblNetWorth.setForeground(Color.WHITE);
        lblNetWorth.setBounds(366, 276, 166, 14);
        startUpPane.add(lblNetWorth);
        
        JLabel lblRole = new JLabel("Role");
        lblRole.setHorizontalAlignment(SwingConstants.CENTER);
        lblRole.setForeground(Color.WHITE);
        lblRole.setBounds(258, 349, 166, 14);
        startUpPane.add(lblRole);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(597, 162, 448, 258);
        startUpPane.add(scrollPane);
        
        JLabel lblSelectACrew = new JLabel("Select a Ship");
        lblSelectACrew.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectACrew.setForeground(Color.WHITE);
        lblSelectACrew.setBounds(597, 137, 448, 14);
        startUpPane.add(lblSelectACrew);
        
        JTextArea crewNameText = new JTextArea();
        crewNameText.setBounds(879, 453, 166, 22);
        startUpPane.add(crewNameText);
        
        JTextArea shipNameText = new JTextArea();
        shipNameText.setBounds(597, 453, 166, 22);
        startUpPane.add(shipNameText);
        
        JLabel lblShip = new JLabel("Ship");
        lblShip.setHorizontalAlignment(SwingConstants.CENTER);
        lblShip.setForeground(Color.WHITE);
        lblShip.setBounds(597, 428, 166, 14);
        startUpPane.add(lblShip);
        
        JLabel lblRole_1_1 = new JLabel("Crew");
        lblRole_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblRole_1_1.setForeground(Color.WHITE);
        lblRole_1_1.setBounds(879, 431, 166, 14);
        startUpPane.add(lblRole_1_1);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(597, 486, 448, 22);
        startUpPane.add(btnSearch);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the table
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.setShowVerticalLines(false);
        table.setRowHeight(30);
        table.setRowSelectionAllowed(true);
        
        scrollPane.setViewportView(table);
        
        loadShips("", "");
        
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadShips(shipNameText.getText(), crewNameText.getText());
            }
        });
        
        createPirateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String fName = fNameText.getText();
                String mName = mNameText.getText();
                String lName = lNameText.getText();
                String age = ageText.getText();
                String alias = aliasText.getText();
                String netWorth = netWorthText.getText();
                String role = roleText.getText();
                int row = table.getSelectedRow();
                
                if(fName.length() < 1) {
                	JOptionPane.showMessageDialog(null, "A pirate must have a first name!");
                	return;
                }
                
                if(fName.length() < 1) {
                	JOptionPane.showMessageDialog(null, "A pirate must have a last name!");
                	return;
                }
                
                if(netWorth.length() < 1) {
                	JOptionPane.showMessageDialog(null, "A pirate must have a net worth!");
                	return;
                }
                
                if(row < 0) {
                	JOptionPane.showMessageDialog(null, "Select a ship, ye ruffian!");
                	return;
                }
                
                PirateDatabase db = new PirateDatabase();
                
                try {
                	ResultSet c;
                	
                    db.connect();
                    
                    db.insertPirate(fName, mName, lName, age, alias, netWorth, role, cids.get(row), sids.get(row));
                    
                    JOptionPane.showMessageDialog(null, "Pirate Added Successfully");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    
                } finally {
                    db.disconnect();
                }
            }
        });
        
        setVisible(true);
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
            table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRender);
            table.getColumnModel().getColumn(1).setCellRenderer(centerRender);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}
