// Author: Juan Velasquez 
// NID: ju460863
// Course: CS2
// Semester: Spring 2025

import java.util.HashMap;
import java.util.Scanner;

public class Assignment1 {

    // This hashmap will store the unique pair of students
    private static HashMap<String, Boolean> pairs = new HashMap<>();

    /**
     * This hashmap will store the list of students in a specific class
     * in the format (key = classname, value = list of students)
     */
    private static HashMap<String, String> classes = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // variable used to store the number of unique pairs of students
        int result = 0;

        // number of students to be entered
        int n = sc.nextInt();

        // clear input buffer
        sc.nextLine();

        // temp variable for retrieving the next line
        String line = "";

        // loop once per each student
        for (int i = 0; i < n; i++) {
            // get the new student
            line = sc.nextLine();

            // get the list of classes the student is taking and make it an array
            String[] schedule = line.split(" ");

            // student's name is the first index of the list
            String name = schedule[0];

            // loop over the list of classes the student is taking
            for (int j = 1; j < schedule.length; j++) {

                // If the class is empty, just add the student to it
                if (classes.get(schedule[j]) == null) {
                    classes.put(schedule[j], name);
                }

                // Check if the current student and each student in the list has been registered
                // as a valid pair
                else {
                    // create a list of students in the class, excluding the newest one
                    String[] studentList = classes.get(schedule[j]).split("&");

                    // loop over the list of students
                    for (int k = 0; k < studentList.length; k++) {
                        // alphabetically sort the name of the new student and current student in the
                        // loop
                        String sortedNames = alphabetSort(name, studentList[k]);

                        // if their pair hasnt been added to the hashmap, add it and add 1 to the num of
                        // pairs
                        if (pairs.get(sortedNames) == null) {
                            pairs.put(sortedNames, true);
                            result++;
                        }
                    }

                    // update the list of students in the class
                    String newValue = classes.get(schedule[j]) + "&" + name;
                    classes.put(schedule[j], newValue);
                }
            }
        }

        // output result
        System.out.println(result);
        sc.close();
    }

    // This function will output the pair of names, but alphabetically sorted by
    // lexicographical value
    public static String alphabetSort(String name1, String name2) {
        if (name1.compareTo(name2) < 0) {
            return name1 + " and " + name2;
        } else {
            return name2 + " and " + name1;
        }
    }
}