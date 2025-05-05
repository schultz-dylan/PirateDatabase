package Database;

import Database.PirateDatabase;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class DataInsert extends PirateDatabase{

    public static void main(String[] args) {
        PirateDatabase db = new PirateDatabase();
        db.connect();

        File[] files = {
            new File("battle.txt"),
            new File("crew.txt"),
            new File("island.txt"),
            new File("participate.txt"),
            new File("pirate.txt"),
            new File("ship.txt"),
            new File("treasure.txt"),
            new File("visit.txt")
        };

        for (File f : files) {
            parseData(f, db);
        }

        db.disconnect();
    }

    public static void parseData(File curFile, PirateDatabase db) {
        try {
            Scanner scanner = new Scanner(curFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                String fileName = curFile.getName().toLowerCase();

                try {
                    switch (fileName) {
                        case "battle.txt":
                            db.insertBattle(
                                tokens[0], // name
                                tokens[1], // location
                                Integer.parseInt(tokens[2]), // casualties
                                tokens[3], // winner
                                Double.parseDouble(tokens[4]), // lootAmount
                                LocalDate.parse(tokens[5]) // date (format: yyyy-MM-dd)
                            );
                            break;

                        case "crew.txt":
                            db.insertCrew(tokens[0], tokens[1]);
                            break;

                        case "island.txt":
                            db.insertIsland(tokens[0], tokens[1]);
                            break;

                        case "participate.txt":
                            db.insertParticipate(
                                Long.parseLong(tokens[0]), // bid
                                Long.parseLong(tokens[1]), // sid
                                Boolean.parseBoolean(tokens[2]) // wasSunk
                            );
                            break;

                        case "pirate.txt":
                            db.insertPirate(
                                tokens[0], // firstName
                                tokens[1], // middleName
                                tokens[2], // lastName
                                tokens[3], // age (stored as String)
                                tokens[4], // alias
                                tokens[5], // netWorth (stored as String)
                                tokens[6], // role
                                Long.parseLong(tokens[7]), // cid
                                Long.parseLong(tokens[8])  // sid
                            );
                            break;

                        case "ship.txt":
                            db.insertShip(
                                tokens[0], // name
                                tokens[1], // type
                                Long.parseLong(tokens[2]) // cid
                            );
                            break;

                        case "treasure.txt":
                            db.insertTreasure(
                                Double.parseDouble(tokens[0]), // value
                                tokens[1], // location
                                Long.parseLong(tokens[2]), // iid
                                Long.parseLong(tokens[3])  // pid
                            );
                            break;

                        case "visit.txt":
                            db.insertVisit(
                                Long.parseLong(tokens[0]), // iid
                                Long.parseLong(tokens[1]), // sid
                                LocalDate.parse(tokens[2]) // date
                            );
                            break;

                        default:
                            System.out.println("Unknown file type: " + fileName);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Failed to insert line from " + fileName + ": " + line);
                    e.printStackTrace();
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + curFile.getName());
        }
    }
}
