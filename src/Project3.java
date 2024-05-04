import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Project3 {

    public static void main(String[] args) {
        try {
            // Read input from the file
            BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"));
            int numClauses = Integer.parseInt(reader.readLine().trim());

            int[][] clauses = new int[numClauses][2];

            for (int i = 0; i < numClauses; i++) {
                String[] clauseTokens = reader.readLine().trim().split("\\s+");
                clauses[i][0] = Integer.parseInt(clauseTokens[0]);
                clauses[i][1] = Integer.parseInt(clauseTokens[1]);
            }

            reader.close();


            int[] assignment = localSearch(clauses);

            // Number of satisfied clauses
            int satisfiedClauses = countSatClauses(clauses, assignment);

            System.out.println(satisfiedClauses);

            for (int value : assignment) {
                System.out.print((value > 0) ? 'T' : 'F');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] localSearch(int[][] clauses) {
        int numVar = calcNumVariables(clauses);
        int[] assignment = getFixedAssignment(numVar);


        int iterations = 1000;


        for (int iter = 0; iter < iterations; iter++) {
            int[] candidateAssignment = Arrays.copyOf(assignment, assignment.length);

            for (int i = 1; i <= numVar; i++) {
                flipVariable(i, candidateAssignment);


                if (countSatClauses(clauses, candidateAssignment) > countSatClauses(clauses, assignment)) {
                    assignment = Arrays.copyOf(candidateAssignment, candidateAssignment.length);
                } else {
                    // If not better, revert the flip
                    flipVariable(i, candidateAssignment);
                }
            }
        }

        return assignment;
    }


    private static int[] getFixedAssignment(int numVar) {

        int[] assignment = new int[numVar];
        for (int i = 0; i < numVar; i++) {
            assignment[i] = 1; // Set all variables to true
        }
        return assignment;
    }


    private static void flipVariable(int variable, int[] assignment) {
        int index = Math.abs(variable) - 1;
        assignment[index] *= -1; // Flip the value of the variable
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

    private static int calcNumVariables(int[][] clauses) {
        int maxVar = 0;

        for (int[] clause : clauses) {
            maxVar = Math.max(maxVar, Math.abs(clause[0]));
            maxVar = Math.max(maxVar, Math.abs(clause[1]));
        }

        return maxVar;
    }
}
