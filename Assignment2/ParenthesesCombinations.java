// Author: Juan Velasquez 
// NID: ju460863
// Course: CS2
// Semester: Spring 2025

import java.util.ArrayList;
import java.util.List;

public class ParenthesesCombinations
{

    // This is the function that the driver file will execute for each test case
    public List<String> generateParentheses(int n)
    {
        // Create List of Strings to hold the valid combinations of strings to return
        List<String> output = new ArrayList<>();

        // Create empty string of size n * 2 as a char array
        char[] str = new char[n * 2];

        // Execute backtracking function to compute all combinations of valid strings and add it to output
        backtrack(str, n, 0, 0, 0, output);

        // Return the result
        return output;
    }

    /**
     * This is the backtracking function that will generate every possible combination of n pairs of parentheses.
     * The function acheives this recursively with an implementation of backtracking that builds the solution 
     * in the char array by either adding '(' or ')' at each recursive call.
     * */
    private void backtrack(char[] s, int n, int index, int openParen, int closedParen, List<String> out)
    {
        // Case 1: add a '(' if you have less than n '('s in the string.
        if(openParen < n)
        {
            s[index] = '(';

            //Once the parenthesis has been added, recursively call this function and add 1 to index and openParen.
            backtrack(s, n, index + 1, openParen + 1, closedParen, out);
        }

        // Case 2: add a ')' if you have more '(' than ')'s in the string. In other words, balance the string.
        if(openParen > closedParen)
        {
            s[index] = ')';

            //Once the parenthesis has been added, recursively call this function and add 1 to index and closedParen.
            backtrack(s, n, index + 1, openParen, closedParen + 1, out);
        }
        
        // Case 3: you have completed the string so, add it to the list of string.
        if(index == n * 2)
        {
            out.add(new String(s));

            // You do not have to reset the char array because the backtracking naturally fixes the array anyways.
            return;
        }

    }

}