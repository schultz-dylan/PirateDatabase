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
        lblNewLabel.setBounds(32, 26, 1222, 52);
        startUpPane.add(lblNewLabel);
        
        JButton newPirateButton = new JButton("Log a new Pirate");
        newPirateButton.setBounds(10, 284, 420, 56);
        startUpPane.add(newPirateButton);
        
        JButton newCrewButton = new JButton("Log a new Crew");
        newCrewButton.setBounds(10, 176, 420, 56);
        startUpPane.add(newCrewButton);
        
        JButton newShipButton = new JButton("Log a new Ship");
        newShipButton.setBounds(10, 232, 420, 52);
        startUpPane.add(newShipButton);
        
        JButton newIslandButton = new JButton("Log a new Island");
        newIslandButton.setBounds(10, 340, 420, 69);
        startUpPane.add(newIslandButton);
        
        JButton newVisitButton = new JButton("Log a new Visit");
        newVisitButton.setBounds(10, 407, 420, 62);
        startUpPane.add(newVisitButton);
        
        JButton newTreasureButton = new JButton("Log a new Treasure");
        newTreasureButton.setBounds(10, 469, 420, 69);
        startUpPane.add(newTreasureButton);
        
        JButton newBattleButton = new JButton("Log a new Battle");
        newBattleButton.setBounds(10, 538, 420, 62);
        startUpPane.add(newBattleButton);
        
        JButton newParticipateButton = new JButton("Log a new Participation");
        newParticipateButton.setBounds(10, 601, 420, 69);
        startUpPane.add(newParticipateButton);
        
        JLabel lblLogNewEntries = new JLabel("Log New Entries");
        lblLogNewEntries.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogNewEntries.setForeground(Color.WHITE);
        lblLogNewEntries.setFont(new Font("Yu Gothic", Font.BOLD, 25));
        lblLogNewEntries.setBounds(10, 113, 420, 52);
        startUpPane.add(lblLogNewEntries);
        
        JLabel lblChangeExistingEntries = new JLabel("Change Existing Entries");
        lblChangeExistingEntries.setHorizontalAlignment(SwingConstants.CENTER);
        lblChangeExistingEntries.setForeground(Color.WHITE);
        lblChangeExistingEntries.setFont(new Font("Yu Gothic", Font.BOLD, 25));
        lblChangeExistingEntries.setBounds(457, 113, 394, 52);
        startUpPane.add(lblChangeExistingEntries);
        
        JLabel lblMakeSpecialQueries = new JLabel("Make Special Queries");
        lblMakeSpecialQueries.setHorizontalAlignment(SwingConstants.CENTER);
        lblMakeSpecialQueries.setForeground(Color.WHITE);
        lblMakeSpecialQueries.setFont(new Font("Yu Gothic", Font.BOLD, 25));
        lblMakeSpecialQueries.setBounds(891, 113, 327, 52);
        startUpPane.add(lblMakeSpecialQueries);
        
        JButton transferPirateButton = new JButton("Transfer Pirate to new Crew");
        transferPirateButton.setBounds(458, 176, 393, 56);
        startUpPane.add(transferPirateButton);
        
        JButton digForTreasureButton = new JButton("Dig for Treasure");
        digForTreasureButton.setBounds(458, 232, 393, 52);
        startUpPane.add(digForTreasureButton);
        
        JButton btnPirateLookup = new JButton("Pirate Lookup");
        btnPirateLookup.setBounds(891, 176, 327, 56);
        startUpPane.add(btnPirateLookup);
        
        JButton btnIslandLookup = new JButton("Island Lookup");
        btnIslandLookup.setBounds(891, 230, 327, 56);
        startUpPane.add(btnIslandLookup);
        
        JButton btnCrewNet = new JButton("See Crew Net Worths");
        btnCrewNet.setBounds(891, 286, 327, 52);
        startUpPane.add(btnCrewNet);
        
        JButton btnPiratesInvolvedIn = new JButton("View All Pirates In a Battle");
        btnPiratesInvolvedIn.setBounds(891, 340, 327, 52);
        startUpPane.add(btnPiratesInvolvedIn);
        
        JButton btnRichestCrewIslands = new JButton("Richest Crew's Islands");
        btnRichestCrewIslands.setBounds(891, 392, 327, 52);
        startUpPane.add(btnRichestCrewIslands);
        
        JButton btnExperiencedShips = new JButton("Experienced Ships by Crew");
        btnExperiencedShips.setBounds(891, 445, 327, 58);
        startUpPane.add(btnExperiencedShips);

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
        
        digForTreasureButton.addActionListener(e -> {
            new DigTreasure();
        });
        
        btnPirateLookup.addActionListener(e -> {
            new PirateLookupPage();
        });
        
        btnIslandLookup.addActionListener(e -> {
            new IslandLookupPage();
        });
        
        btnCrewNet.addActionListener(e -> {
            new CrewNetWorthPage();
        });
        
        btnPiratesInvolvedIn.addActionListener(e -> {
            new PiratesInBattlePage();
        });
        
        btnRichestCrewIslands.addActionListener(e -> {
            new RichestCrewIslandVisitsPage();
        });
        
        btnExperiencedShips.addActionListener(e -> {
            new ExperiencedShipsPage();
        });
        
        setVisible(true);
    }
}