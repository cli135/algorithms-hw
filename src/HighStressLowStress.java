// Algorithms HW5
// Fall 2022
// Instructor: Professor Garg
// Student Submitting: Christopher Li

// algo hw5 q2b

public class HighStressLowStress {

    public static void main(String[] args) {
        int[] lowStressValues = {10, 1, 10, 10};
        int[] highStressValues = {5, 50, 5, 1};
        System.out.println(findMaxValueOfOptimalPlanForHighStressLowStressJobs(lowStressValues, highStressValues));

        int[] lowStressValues1 = {30, 40, 50};
        int[] highStressValues1 = {1000, 71, 1000};
        System.out.println(findMaxValueOfOptimalPlanForHighStressLowStressJobs(lowStressValues1, highStressValues1));

    }

    public static int findMaxValueOfOptimalPlanForHighStressLowStressJobs(int[] lowStressValues, int[] highStressValues) {
        int n = lowStressValues.length; // same as highStressValues.length
        int[] OPT = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // guards for indexing out of bounds errors
            if (i == 0) {
                // beginning, only compare values
                OPT[i] = Math.max(lowStressValues[i], highStressValues[i]);
            } else if (i == 1) {
                // now we can speak of i - 1, but not i - 2 yet
                OPT[i] = Math.max(lowStressValues[i] + OPT[i - 1], highStressValues[i]);
            } else {
                // general case, all indices valid
                // this is the full-fledged optimization function
                OPT[i] = Math.max(lowStressValues[i] + OPT[i - 1], highStressValues[i] + OPT[i - 2]);
            }

        }
        // the last value, meaning
        // maximum value for the optimal plan
        return OPT[n - 1];
    }
}