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

public class PirateLookupPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable table;
    DefaultTableCellRenderer centerRender;
    private JTextField aliasText;
    private JTextField lastNameText;
    private JTextField middleNameText;
    private JTextField firstNameText;
    private JLabel lblMiddleName;
    private JLabel lblLastName;
    private JLabel lblAlias;
    
    public PirateLookupPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Pirate Lookup");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton btnNewButton = new JButton("Search");
        btnNewButton.setBounds(532, 526, 156, 43);
        startUpPane.add(btnNewButton);
        
        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 1244, 347);
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
        
        aliasText = new JTextField();
        aliasText.setColumns(10);
        aliasText.setBounds(782, 407, 156, 20);
        startUpPane.add(aliasText);
        
        lastNameText = new JTextField();
        lastNameText.setColumns(10);
        lastNameText.setBounds(616, 407, 156, 20);
        startUpPane.add(lastNameText);
        
        middleNameText = new JTextField();
        middleNameText.setColumns(10);
        middleNameText.setBounds(450, 407, 156, 20);
        startUpPane.add(middleNameText);
        
        firstNameText = new JTextField();
        firstNameText.setColumns(10);
        firstNameText.setBounds(284, 407, 156, 20);
        startUpPane.add(firstNameText);
        
        JLabel lblNewLabel = new JLabel("First Name");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(284, 438, 156, 14);
        startUpPane.add(lblNewLabel);
        
        lblMiddleName = new JLabel("Middle Name");
        lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
        lblMiddleName.setForeground(Color.WHITE);
        lblMiddleName.setBounds(450, 438, 156, 14);
        startUpPane.add(lblMiddleName);
        
        lblLastName = new JLabel("Last Name");
        lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
        lblLastName.setForeground(Color.WHITE);
        lblLastName.setBounds(616, 438, 156, 14);
        startUpPane.add(lblLastName);
        
        lblAlias = new JLabel("Alias");
        lblAlias.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlias.setForeground(Color.WHITE);
        lblAlias.setBounds(782, 438, 156, 14);
        startUpPane.add(lblAlias);
        
        loadPirates("", "", "", "");
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadPirates(firstNameText.getText(), middleNameText.getText(), lastNameText.getText(), aliasText.getText());
            }
        });
        
        setVisible(true);
        
    }
    
    private void loadPirates(String fname, String mname, String lname, String alias) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getPirateFullInfo(fname, mname, lname, alias);
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("First Name");
            model.addColumn("Middle Name");
            model.addColumn("Last Name");
            model.addColumn("Alias");
            model.addColumn("Age");
            model.addColumn("Net Worth");
            model.addColumn("Role");
            model.addColumn("Crew");
            model.addColumn("Ship");
            
            while (rs.next()) {
                Object[] rowData = new Object[9];

                for(int i = 0; i < 9; i++) {
                	rowData[i] = rs.getObject(i + 1);
                }

                model.addRow(rowData);
            }
            
            rs.close();
            table.setModel(model);

            for(int i = 0; i < 9; i++) {
            	table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}
