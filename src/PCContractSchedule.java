public class PCContractSchedule {
    public static void main(String[] args) {
        int[] supplyWeights = {11, 9, 9, 12, 12, 12, 12, 9, 9, 11};
        int r = 1;
        int c = 10;
        System.out.println(findContractScheduleOfMinimumCost(supplyWeights, r, c));
    }

    /**
     * Returns an array of characters like ['A', 'B', 'B, 'B', 'B', 'A']
     * representing the optimal schedule of who was contracted in which week
     * which achieves the global minimum cost possible.
     * Precondition: supplyWeight.length >= 1
     * @param supplyWeights the s array, telling us the weight
     *      of the shipment in week i. Only relevant for company A's
     *      pricing model.
     *      Precondition: supplyWeight.length >= 1
     * @return A length-n array of the form ['A', 'B', 'B, 'B', 'B', 'A'] etc.
     *      where the character at index i represents which company (A or B)
     *      was contracted for week i.
     */
    public static char[] findContractScheduleOfMinimumCost(int[] supplyWeights, int r, int c) {
        int n = supplyWeights.length;
        int[] OPT = new int[n];
        int[] tracePath = new int[n]; // pointers to remember our path to this point
        // I prefer explicit handling of edge cases
        // through if-elseif-else casework in the for loop
        // rather than assuming the zeroes on the fringe of the array
        // are the proper base cases to make the dynamic programming work.
        // This way, it's easier to hardcode base cases when they are 1, etc.
        // And just to explicitly see the base cases reduces errors / bugs
        // instead of accessing indices that we can't immediately see
        // that are outside the normal length of the array.
        for (int i = n - 1; i >= 0; i--) {
            // index checking of edge cases
            if (i >= n - 1) {
                // most stringent case, both i + 1 and i + 4 indices
                // are out of bounds / invalid
                // optimization function to calculate minimum cost
                OPT[i] = Math.min(r * supplyWeights[i], 4 * c);

                // path tracing to output the actual schedule
                // of minimum cost
                if (r * supplyWeights[i] < 4 * c) {
                    tracePath[i] = 1;
                }
                else {
                    tracePath[i] = 4;
                }
            }
            else if (i >= n - 4) {
                // kind of stringent case, only i + 4 is out of bounds
                // optimization function to calculate minimum cost
                OPT[i] = Math.min(r * supplyWeights[i] + OPT[i + 1], 4 * c);

                // path tracing to output the actual schedule
                // of minimum cost
                if (r * supplyWeights[i] + OPT[i + 1] < 4 * c) {
                    tracePath[i] = 1;
                }
                else {
                    tracePath[i] = 4;
                }
            }
            else {
                // general case, no index guards needed
                // all indices valid in this context
                // this is the full statement of the optimization function
                // to calculate minimum cost
                OPT[i] = Math.min(r * supplyWeights[i] + OPT[i + 1], 4 * c + OPT[i + 4]);

                // path tracing to output the actual schedule
                // of minimum cost
                if (r * supplyWeights[i] + OPT[i + 1] < 4 * c + OPT[i + 4]) {
                    tracePath[i] = 1;
                }
                else {
                    tracePath[i] = 4;
                }
            }
        }
        // time to trace the path
        // and for good riddance we'll also check
        // that the optimal path's total cost is indeed
        // that calculated by the OPT function at OPT[0]
        // since we were progressing backwards through
        // the OPT array this time (I think this is purely a convention)

        // assuming that n >= 1
        // explicit base (edge) case to handle n == 0 can be made later
        char[] schedule = new char[n];
        int i = 0;
        while (i < n) {
            if (tracePath[i] == 1) {
                // we picked A just for this week
                schedule[i] = 'A';
                // continuing the path trace
                i += tracePath[i]; // += 1
            }
            else { // implied / assuming that tracePath[i] == 4
                // we picked B for a time period of 4 weeks,
                // starting with this week.
                // we make the next 4 entries in the schedule
                // array 'B', or we just go to the end of the array
                // whichever comes first
                for (int j = 0; j < 4 && i + j < n; j++) {
                    schedule[i + j] = 'B';
                }
                // continuing the path trace
                i += tracePath[i]; // should be += 4
            }
        }
        return schedule; // method stub
    }
}
