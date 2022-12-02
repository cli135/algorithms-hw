public class NYToSanFrancisco {
    public static void main(String[] args) {
        int[] newYorkCosts = {1, 3, 20, 30};
        int[] sanFranciscoCosts = {50, 20, 2, 4};
        int movingCost = 10;
        System.out.println(minCostValueToTravelBetweenCities(newYorkCosts, sanFranciscoCosts, movingCost));

    }
    public static int minCostValueToTravelBetweenCities(int[] newYorkCosts, int[] sanFranciscoCosts, int movingCost) {
        int n = newYorkCosts.length;
        int[][] OPT = new int[n + 2][2];
        // int[] OPT = new int[n + 1];
        // 0 means New York
        // 1 means San Francisco
        // 1 offset for 1-indexing
        // and moving backwards
        for (int i = n; i >= 1; i--) {
            // change i-1 because that code is not meaningful and it is really just confusing
            int idx = i - 1;
            OPT[i][0] = Math.min(newYorkCosts[idx] + OPT[i + 1][0], movingCost + sanFranciscoCosts[idx] + OPT[i + 1][1]);
            OPT[i][1] = Math.min(movingCost + newYorkCosts[idx] + OPT[i + 1][0], sanFranciscoCosts[idx] + OPT[i + 1][1]);
        }
        // should we end in NY or SF
        return Math.min(OPT[1][0], OPT[1][1]);
    }

}
