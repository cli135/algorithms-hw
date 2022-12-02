public class PCContractSchedule {
    public static void main(String[] args) {

    }

    /**
     * Returns an array of characters like ['A', 'B', 'B, 'B', 'B', 'A']
     * representing the optimal schedule of who was contracted in which week
     * which achieves the global minimum cost possible.
     * @param supplyWeights the s array, telling us the weight
     *      of the shipment in week i. Only relevant for company A's
     *      pricing model.
     * @return A length-n array of the form ['A', 'B', 'B, 'B', 'B', 'A'] etc.
     *      where the character at index i represents which company (A or B)
     *      was contracted for week i.
     */
    public static char[] findContractScheduleOfMinimumCost(int[] supplyWeights) {
        int n = supplyWeights.length;
        int[] OPT = new int[n];
        int[] tracePath = new int[n]; // pointers to remember our path to this point
        for (int i = 0; i < n ; i++) {

        }
    }
}
