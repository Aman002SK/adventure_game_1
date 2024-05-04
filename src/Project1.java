// Project2.java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Project1 {

    public static void main(String[] args) {
        try {
            // Read input from the file
            BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"));
            int num_Clauses = Integer.parseInt(reader.readLine().trim());

            int[][] clauses = new int[num_Clauses][2];

            for (int i = 0; i < num_Clauses; i++) {
                String[] clausTokens = reader.readLine().trim().split("\\s+");
                clauses[i][0] = Integer.parseInt(clausTokens[0]);
                clauses[i][1] = Integer.parseInt(clausTokens[1]);
            }

            reader.close();

            // Implementation of Max2SAT Approximation algorithm
            int[] assignment = max2SATApproximation(clauses);

            // number of satisfied clauses
            int satisfiedClauses = countSatClauses(clauses, assignment);


            System.out.println(satisfiedClauses);

            for (int value : assignment) {
                System.out.print((value > 0) ? 'T' : 'F');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] max2SATApproximation(int[][] clauses) {
        int numVar = calcNumVariables(clauses);
        int[] assign = new int[numVar];

        for (int i = 0; i < numVar; i++) {
            // Check if the variable appears more often negated or non-negated
            int negatedCnt = cntNegatedOccurrences(i + 1, clauses);
            int nonNegatedCnt = cntNonNegatedOccurrences(i + 1, clauses);

            // Set the variable to F if it appears negated more often, otherwise set to T
            assign[i] = (negatedCnt > nonNegatedCnt) ? -1 : 1;
        }

        return assign;
    }

    private static int cntNegatedOccurrences(int variable, int[][] clauses) {
        int count = 0;

        for (int[] clause : clauses) {
            if (clause[0] == -variable || clause[1] == -variable) {
                count++;
            }
        }

        return count;
    }

    private static int calcNumVariables(int[][] clauses) {
        int maxVar = 0;

        for (int[] clause : clauses) {
            maxVar = Math.max(maxVar, Math.abs(clause[0]));
            maxVar = Math.max(maxVar, Math.abs(clause[1]));
        }

        return maxVar;
    }
    private static int cntNonNegatedOccurrences(int variable, int[][] clauses) {
        int count = 0;

        for (int[] clause : clauses) {
            if (clause[0] == variable || clause[1] == variable) {
                count++;
            }
        }

        return count;
    }




    private static int countSatClauses(int[][] clauses, int[] assignment) {
        int count = 0;

        for (int[] clause : clauses) {
            int literal1 = clause[0];
            int literal2 = clause[1];

            // Check if either literal1 or literal2 is assigned the correct truth value
            boolean isSatisfied = (literal1 > 0 && assignment[Math.abs(literal1) - 1] > 0) ||
                    (literal1 < 0 && assignment[Math.abs(literal1) - 1] < 0) ||
                    (literal2 > 0 && assignment[Math.abs(literal2) - 1] > 0) ||
                    (literal2 < 0 && assignment[Math.abs(literal2) - 1] < 0);

            if (isSatisfied) {
                count++;
            }
        }

        return count;
    }

}
