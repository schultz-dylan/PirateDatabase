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

public class PiratesInBattlePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;
    private JTable pirateTable;
    private JTable battleTable;
    DefaultTableCellRenderer centerRender;
    private ArrayList<Long> bids;
    
    
    public PiratesInBattlePage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Pirates In Battle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JButton loadPiratesButton = new JButton("Load Pirates");
        loadPiratesButton.setBounds(554, 545, 156, 43);
        startUpPane.add(loadPiratesButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(652, 162, 575, 258);
        startUpPane.add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(32, 162, 594, 258);
        startUpPane.add(scrollPane_1);
        
        JTextArea battleNameText = new JTextArea();
        battleNameText.setBounds(241, 431, 166, 22);
        startUpPane.add(battleNameText);
        
        // Create cell renderer that centers rows
        centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create the pirateTable
        pirateTable = new JTable();
        pirateTable.setAutoCreateRowSorter(true);
        pirateTable.setShowVerticalLines(false);
        pirateTable.setRowHeight(30);
        pirateTable.setRowSelectionAllowed(true);
        
        // Create the battleTable
        battleTable = new JTable();
        battleTable.setAutoCreateRowSorter(true);
        battleTable.setShowVerticalLines(false);
        battleTable.setRowHeight(30);
        battleTable.setRowSelectionAllowed(true);
        
        scrollPane.setViewportView(pirateTable);
        scrollPane_1.setViewportView(battleTable);
        
        JButton battleSearchButton = new JButton("Search");
        battleSearchButton.setBounds(241, 464, 166, 22);
        startUpPane.add(battleSearchButton);
        
        JLabel lblSelectAnIsland = new JLabel("Select a Battle");
        lblSelectAnIsland.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectAnIsland.setForeground(Color.WHITE);
        lblSelectAnIsland.setBounds(32, 137, 594, 14);
        startUpPane.add(lblSelectAnIsland);
        
        loadBattles("");
        
        loadPiratesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
                int bRow = battleTable.getSelectedRow();
                
                if(bRow < 0) {
                	JOptionPane.showMessageDialog(null, "Select a Battle!");
                	return;
                } 
                
                loadPirates(bids.get(bRow));
            }
        });
        
        battleSearchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
                loadBattles(battleNameText.getText());
            }
        });
        
        setVisible(true);
    }
    

    
    private void loadPirates(long bid) {
        PirateDatabase db = new PirateDatabase();
        
        try {
        	db.connect();
            
        	ResultSet rs = db.getPiratesInBattle(bid);
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Alias");
            model.addColumn("Role");
            model.addColumn("Ship");
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[4];

                for(int i = 0; i < 4; i++) {
                	rowData[i] = rs.getObject(i + 1);
                }

                model.addRow(rowData);
            }
            
            rs.close();
            pirateTable.setModel(model);
            for(int i = 0; i < 4; i++) {
            	pirateTable.getColumnModel().getColumn(i).setCellRenderer(centerRender);
            }
            
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