import java.awt.EventQueue;
import javax.swing.JFrame;

import Database.PirateDatabase;
import GUI.MainPage;

public class Driver {
    public static void main(String[] args) {
    	/*
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new MainPage();
                frame.setSize(1280, 720);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        */
    	
    	PirateDatabase pdb = new PirateDatabase();
    	pdb.connect();
    	pdb.disconnect();
    }
}
