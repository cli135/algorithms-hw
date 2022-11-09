import java.util.Arrays;

class EquivalenceTester {
    public static void main(String[] args) {
        EquivalenceTester et = new EquivalenceTester();
        int[][] testCardLists = {
                {1, 2, 1, 1, 2, 1, 4, 1},
                {1, 2, 5, 1, 2, 1, 4, 1},
                {1, 2, 4, 1, 4, 1, 4, 1},
                {4, 2, 4, 4, 2, 4, 4, 1},
                {3, 3, 3, 3, 2, 1, 2, 1},

                {3, 3, 3, 3, 2, 1, 2, 1, 3},
                {3, 3, 3, 3, 2, 1, 2, 1, 3, 2},
                {3, 3, 3, 3, 2, 1, 2, 1, 3, 2, 3},
                {3, 3, 3, 3, 2, 1, 2, 1, 3, 2, 3, 4},
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1}


        };
        boolean[] expectedResults = {
                true,
                false,
                false,
                true,
                false,

                true,
                false,
                true,
                false,
                true
        };
        boolean allTestsPassed = true; // assume true until counterexample found
        for (int i = 0; i < testCardLists.length; i++) {
            if (i == 4) {
                int x = 5; // breakpoint here
            }
            boolean result = et.checkMoreThanHalfSame(testCardLists[i]);
            System.out.println("Test case " + i + ": ");
            System.out.println("Original array: Length is " + testCardLists[i].length);
            System.out.println(Arrays.toString(testCardLists[i]));

            System.out.println("Result (are there more than half same?):");
            System.out.println(result);
            System.out.println("Expected: " + expectedResults[i]);
            if (expectedResults[i] != result) {
                allTestsPassed = false; // found one counterexample
                System.out.println("This expected result does not match actual result");
            }
            System.out.println();
//            System.out.println();
        }
        if (allTestsPassed) {
            System.out.println("All tests passed!");
        }
        else {
            System.out.println("At least one test did not pass.");
        }

    }
    public boolean checkMoreThanHalfSame(int[] cards) {
        // -1 is a flag for no majority element
        // otherwise, the majority element/card will be returned
        return -1 != checkMoreThanHalfSame(cards, 0, cards.length);
    }
    private int checkMoreThanHalfSame(int[] cards, int start, int end) {
        // base case - only one element means it is majority element
        if (end - start == 1) {
            return cards[start];
        }
        // base case - two elements
        else if (end - start == 2) {
            if (cards[start] == cards[end - 1]) {
                // same values
                // return one of them to represent
                // the majority element
                return cards[start];
            }
            else {
                // not the same, so
                // no majority element to return
                return -1; // -1 flag for no majority
            }
        }
        // recursive case - dividing problem size two subproblems
        // each of size n/2
        int mid = start + (end - start) / 2;
        int leftMajorityCard = checkMoreThanHalfSame(cards, start, mid);
        int rightMajorityCard = checkMoreThanHalfSame(cards, mid, end);
        // if neither half has a majority card,
        if (leftMajorityCard == -1 && rightMajorityCard == -1) {
            // then no majority possible at this level
            return -1;
        }
        else {
            // have to do a linear time check using
            // the majority elements, if there are any
            // to confirm if they are actually majorities
            // at the merged level
            if (leftMajorityCard != -1) {
                // counting occurrences of left majority card
                int count = 0;
                for (int i = start; i < end; i++) {
                    if (leftMajorityCard == cards[i]) {
                        count++;
                    }
                }
                // if it's a majority at this level, we return it
                if (count > (end - start) / 2) {
                    return leftMajorityCard;
                }
            }
            if (rightMajorityCard != -1) {
                // counting occurrences of left majority card
                int count = 0;
                for (int i = start; i < end; i++) {
                    if (rightMajorityCard == cards[i]) {
                        count++;
                    }
                }
                // if it's a majority at this level, we return it
                if (count > (end - start) / 2) {
                    return rightMajorityCard;
                }
            }
            // otherwise, neither majority of the n/2 size
            // subproblems is a majority at the higher merged level
            // and we do not have an overall majority at this higher level

            return -1; // no majority at this level
        }
    }
}