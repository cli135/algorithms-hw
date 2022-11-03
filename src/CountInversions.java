// hw4, problem 1
// 11-2-2022
// intro algos fall 2022

public class CountInversions {

    public static int countInversions(int[] arr) {
        return sortAndMerge(arr, 0, arr.length);
    }

    // [start, end)
    // start inclusive, end exclusive
    private static int sortAndMerge(int[] arr, int start, int end) {
        // base case
        if (end - start <= 1) {
            return 0;
        }
        // [0, 1, 2, 3]
        int mid = start + (end - start) / 2;

        // oh i never sorted the arrays


        // recursive case of counting cross-half inversions, i.e.
        // left-right inversions
        // and merging in O(n) time, to eventually sort it
        // recurrence relation
        // T(N) <= 2T(N/2) + O(N)
        int left = sortAndMerge(arr, start, mid);
        int right = sortAndMerge(arr, mid, end);
        int inversionsWhenMerging = countAndMerge(arr, start, end);
        return left + right + inversionsWhenMerging;

    }

    // [start, end)
    // start inclusive, end exclusive
    private static int countAndMerge(int[] arr, int start, int end) {
        // mergesort merge operation, with just one caveat
        // an additional if-statement check
        // to use sorted invariant of lists to be merged
        // to deduce multiple inversions at a time via transitivity of < (less than
        // operation)
        int l = start;
        int mid = start + (end - start) / 2;
        int r = mid;
        int countInversionsWhenMerging = 0;
        int[] temp = new int[end - start]; // mergesort not in place

        while (l < mid && r < end) {
            // arr[l] > arr[r] is for counting normal inversions
            // arr[l] > 2 * arr[r] is for counting 'significant' inversions

            //------------------------------
            // The only change between counting inversions and significant inversions
            // is the line of code below
            //------------------------------
            if (arr[l] > 2 * arr[r]) { // significant inversions
            //------------------------------
            // if (arr[l] > arr[r]) { // normal inversions
            //------------------------------

                // copying to temp merged array
                temp[(l - start) + (r - mid)] = arr[r];
                // inversion detected
                // here it would be < 2 * arr[r] or something like that
                // mid - l is the number of transitive inversions
                // we can deduce, by all the things even greater
                // than the single comparison that we just made
                countInversionsWhenMerging += mid - l;
                r++; // don't forget to increment in while loops

            } else { // <= but really just < since all assumed unique

                // copying to temp merged array
                temp[(l - start) + (r - mid)] = arr[l];
                l++; // don't forget to increment in while loops
                continue; // all is well
            }
        }
        // merging is one huge indices problem
        // how to get indices just right

        // finding out why we ended
        // and filling in the rest accordingly
        while (l < mid) {
            // append the rest of the l half
            temp[(l - start) + (r - mid)] = arr[l];
            l++;
        }
        while (r < end) {
            // append the rest of the r half
            // (l - start) is how much we've progressed in the l half
            // (r - mid) is how much we've progressed in the r half
            // the key problem here is not realizing that mergesort
            // works on INNER intervals, not always rooted at start == 0
            // or end == arr.length
            // you need to generalize to all ranges, making
            // the definitions of start, mid, and end general
            temp[(l - start) + (r - mid)] = arr[r];
            r++;
        }
        for (int i = 0; i < temp.length; i++) {
            arr[start + i] = temp[i];
        }
        // copying back over to arr
        return countInversionsWhenMerging;
    }
}