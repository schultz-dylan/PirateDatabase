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
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class CrewNetWorthPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable table;
    DefaultTableCellRenderer centerRender;
    private JLabel lblAlias;
    
    public CrewNetWorthPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Crew Net Worth");
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
        
        lblAlias = new JLabel("Value");
        lblAlias.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlias.setForeground(Color.WHITE);
        lblAlias.setBounds(532, 435, 156, 14);
        startUpPane.add(lblAlias);
        
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
        spinner.setBounds(532, 404, 156, 20);
        startUpPane.add(spinner);
        
        loadCrews(0);
        
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	loadCrews((double)spinner.getValue());
            }
        });
        
        setVisible(true);
        
    }
    
    private void loadCrews(double value) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
        	
        	ResultSet rs = db.getCrewNetWorths(value);
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Crew");
            model.addColumn("Total Net Worth");

            
            while (rs.next()) {
                Object[] rowData = new Object[2];

                for(int i = 0; i < 2; i++) {
                	rowData[i] = rs.getObject(i + 1);
                }

                model.addRow(rowData);
            }
            
            rs.close();
            table.setModel(model);
            for(int i = 0; i < 2; i++) {
            	table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
}
