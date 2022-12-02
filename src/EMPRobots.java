public class EMPRobots {
    public static void main(String[] args) {
        int[] x = {1, 10, 10, 1};
        int[] f = {1 ,2, 4, 8};
        System.out.println(maximumNumberOfRobotsThatCanBeDestroyed(x, f));
    }
    /**
    Returns the maximum number of robots that can be destroyed.
     @param x the x_i values of the robot swarms at time i
     @param f the recharging function, where f(j) gives the
        amount that can be destroyed after charging for j seconds
     */
    public static int maximumNumberOfRobotsThatCanBeDestroyed(int[] x, int[] f) {
        int n = x.length;
        int[][] OPT = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {

                // One big mistake I originally made was this:
                // I wrote + OPT[i + 1][1] (incorrect)
                // instead of + OPT[i + 1][0] (correct)
                // This is because the prompt had 1-indexing
                // Whereas my actual Java OPT double array is 0-indexed
                // The first (1st) column is index 0 in Java
                // Drawing the 2-D grid in dynamic programming is very helpful
                // To see when my indices are off

                // two options at each step
                int dischargeEMP = Math.min(x[i], f[j]) + OPT[i + 1][0];
                int waitForAnotherTime = OPT[i + 1][j + 1];
                OPT[i][j] = Math.max(dischargeEMP, waitForAnotherTime);
            }
        }
        return OPT[0][0];
    }
}
