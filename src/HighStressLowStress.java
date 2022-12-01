public class HighStressLowStress {

    public static void main(String[] args) {
        int[] lowStressValues = {10, 1 , 10, 10};
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
            }
            else if (i == 1) {
                // now we can speak of i - 1, but not i - 2 yet
                OPT[i] = Math.max(lowStressValues[i] + OPT[i - 1], highStressValues[i]);
            }
            else {
                // general case, all indices valid
                // this is the full-fledged optimization function
                OPT[i] = Math.max(lowStressValues[i] + OPT[i - 1], highStressValues[i] + OPT[i - 2]);
            }

        }
        // the last value, meaning
        // maximum value for the optimal plan
        return OPT[n - 1];
    }

        public static String[] findPlanForHighStressLowStressJobsProbablyNotRight(int[] lowStressValues, int[] highStressValues) {
        int n = lowStressValues.length; // same as highStressValues.length
        int[] dp = new int[n + 2];
        dp[0] = 0;
        dp[1] = 0;
        int[] path = new int[n + 2];
        for (int i = 2; i < n + 2; i++) {
            dp[i] = Math.max(lowStressValues[i] + dp[i - 1], highStressValues[i] + dp[i - 2]);
            if (dp[i] == lowStressValues[i] + dp[i - 1]) {
                path[i] = i - 1;
            }
            else {
                path[i] = i - 2;
            }
        }
        int[] optimalPath = new int[n];
        for (int i = n + 2 - 1; i >= 2; i += 0) {
            optimalPath[i - 2] = path[i];
            i = path[i];
        }
        return null; // stub
    }
}
