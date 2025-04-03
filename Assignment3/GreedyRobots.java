package Assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GreedyRobots {

    int robots = 0;
    int buildings = 0;
    String robotsFile = "";
    String buildingsFile = "";
    int[] robotravelRange;
    int[] buildingDistances;
    int successfulDelivery = 0;
    int unservedBuildings = 0;

    public GreedyRobots(int robots, int buildings, String robotsFile, String buildingsFile) {

        this.robots = robots;
        this.buildings = buildings;
        this.robotsFile = robotsFile;
        this.buildingsFile = buildingsFile;
        this.robotravelRange = new int[robots];
        this.buildingDistances = new int[buildings];

    }

    public void readFile() {

        File robotsFile = new File(this.robotsFile);
        File buildingsFile = new File(this.buildingsFile);
        int i = 0;
        int j = 0;

        try {

            Scanner robotReader = new Scanner(robotsFile);
            Scanner buildingReader = new Scanner(buildingsFile);

            while (robotReader.hasNextInt()) {
                this.robotravelRange[i] = robotReader.nextInt();
                i++;
            }

            while (buildingReader.hasNextInt()) {
                this.buildingDistances[j] = buildingReader.nextInt();
                j++;
            }

            robotReader.close();
            buildingReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            File directory = new File(".");
            System.out.println("Contents of directory: " + directory.getAbsolutePath());
            e.printStackTrace();
        }

    }

    public void assignDeliveries() {

        this.readFile();

        Arrays.sort(this.robotravelRange);
        Arrays.sort(this.buildingDistances);

        System.out.println(Arrays.toString(this.robotravelRange));
        System.out.println(Arrays.toString(this.buildingDistances));

        boolean[] assigned = new boolean[this.buildings];
        int j = 0;
        for (int i = 0; i < this.robotravelRange.length; i++) {
            int remainingDistance = this.robotravelRange[i];
            while (j < this.buildingDistances.length && this.buildingDistances[j] <= remainingDistance) {
                if (!assigned[j]) {
                    this.successfulDelivery++;
                    remainingDistance -= (this.buildingDistances[j] * 2);
                    assigned[j] = true;
                    j++;
                }
            }

        }

        this.unservedBuildings = this.buildings - this.successfulDelivery;
    }

    public void displayResults() {
        System.out.println("Successful Deliveries: " + this.successfulDelivery + "\nUnserved Buildings: "
                + this.unservedBuildings);
    }

}