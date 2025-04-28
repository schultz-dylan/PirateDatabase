package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MainPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel startUpPane;

    public MainPage() {
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        startUpPane = new JPanel();
        startUpPane.setForeground(UIManager.getColor("ToolBar.light"));
        startUpPane.setBackground(new Color(49, 49, 49));
        startUpPane.setLayout(null);
        setContentPane(startUpPane);
        
        JLabel lblNewLabel = new JLabel("AHOY YE SCURVY DOGS");
        lblNewLabel.setForeground(UIManager.getColor("text"));
        lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 29));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(379, 26, 498, 52);
        startUpPane.add(lblNewLabel);
        
        JButton newPirateButton = new JButton("Log a new Pirate");
        newPirateButton.setBounds(543, 102, 169, 23);
        startUpPane.add(newPirateButton);
        
        JButton newCrewButton = new JButton("Log a new Crew");
        newCrewButton.setBounds(543, 136, 169, 23);
        startUpPane.add(newCrewButton);
        
        JButton newShipButton = new JButton("Log a new Ship");
        newShipButton.setBounds(543, 170, 169, 23);
        startUpPane.add(newShipButton);
        
        JButton newIslandButton = new JButton("Log a new Island");
        newIslandButton.setBounds(543, 204, 169, 23);
        startUpPane.add(newIslandButton);
        
        JButton newVisitButton = new JButton("Log a new Visit");
        newVisitButton.setBounds(543, 238, 169, 23);
        startUpPane.add(newVisitButton);
        
        JButton newTreasureButton = new JButton("Log a new Treasure");
        newTreasureButton.setBounds(543, 272, 169, 23);
        startUpPane.add(newTreasureButton);
        
        JButton newBattleButton = new JButton("Log a new Battle");
        newBattleButton.setBounds(543, 306, 169, 23);
        startUpPane.add(newBattleButton);
        
        JButton newParticipateButton = new JButton("Log a new Participation");
        newParticipateButton.setBounds(543, 340, 169, 23);
        startUpPane.add(newParticipateButton);

        newPirateButton.addActionListener(e -> {
            new CreatePiratePage();
        });
        
        newCrewButton.addActionListener(e -> {
            new CreateCrewPage();
        });
        
        newShipButton.addActionListener(e -> {
            new CreateShipPage();
        });
        
        newIslandButton.addActionListener(e -> {
            new CreateIslandPage();
        });
        
        newVisitButton.addActionListener(e -> {
            new CreateVisitPage();
        });
        
        newTreasureButton.addActionListener(e -> {
            new CreateTreasurePage();
        });
        
        newBattleButton.addActionListener(e -> {
            new CreateBattlePage();
        });
        
        newParticipateButton.addActionListener(e -> {
            new CreateParticipatePage();
        });

        setVisible(true);
    }
}