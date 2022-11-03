// hw4, problem 1
// 11-2-2022
// intro algos fall 2022

public class CountInversionsReformattedCode {

    public static int countInversions(int[] arr) {
        return sortAndMerge(arr, 0, arr.length);
    }
    /**
     * Runs mergesort on arr from start (inclusive)
     * to end (exclusive). Returns the number of inversions
     * detected in the left half, the right half, and the
     * overall merge operation of both halves.
     * @param arr the array to be sorted
     * @param start the start index (inclusive) of the portion
     * @param end the end index (exclusive) of the portion
     * @return the total number of inversions detected in array
     *      from [start, end), including inversions detected at
     *      deeper levels of recursion
     */
    private static int sortAndMerge(int[] arr, int start, int end) {
        // base case, already sorted, no inversions
        if (end - start <= 1) {
            return 0;
        }
        // Recurrence relation for recursive case:
        // T(N) <= 2T(N/2) + O(N)
        // counting inversions in each half
        int mid = start + (end - start) / 2;
        int left = sortAndMerge(arr, start, mid);
        int right = sortAndMerge(arr, mid, end);
        // and counting inversions when merging in O(n) time
        int inversionsWhenMerging = countAndMerge(arr, start, end);
        return left + right + inversionsWhenMerging; // total inversions
    }

    /**
     * Merges the two sorted halves of the array from
     * [start, mid) and [mid, end).
     * Returns the number of inversions detected during
     * the merge operation of both halves only at this level.
     * @param arr the array to be sorted
     * @param start the start index (inclusive) of the portion
     * @param end the end index (exclusive) of the portion
     * @return the number of inversions detected in array
     *      from [start, end) when merging two halves in [start, end)
     */
    private static int countAndMerge(int[] arr, int start, int end) {
        // mergesort merge operation, with just one caveat
        // an additional if-statement check
        // to check for multiple inversions at a time (by transitivity)
        int mid = start + (end - start) / 2;
        int l = start;
        int r = mid;
        int countInversionsWhenMerging = 0;
        int[] merged = new int[end - start];
        while (l < mid && r < end) {
            //------------------------------
            // The only change between counting inversions and significant inversions
            // is the line of code below
            // Normal inversions would be if (arr[l] > arr[r])
            //------------------------------
            if (arr[l] > 2 * arr[r]) { // significant inversions
                // inversion detected
                // copying right value to merged array
                merged[(l - start) + (r - mid)] = arr[r];
                // mid - l is the number of transitive inversions
                // we can deduce, i.e. all the elements in left half
                // even greater than right element we just compared
                countInversionsWhenMerging += mid - l;
                r++; // moving r pointer along
            } else {
                // copying left value to merged array
                merged[(l - start) + (r - mid)] = arr[l];
                l++; // moving l pointer along
            }
        }
        // appending the remaining elements in left half
        while (l < mid) {
            merged[(l - start) + (r - mid)] = arr[l];
            l++;
        }
        // appending the remaining elements in right half
        while (r < end) {
            // (l - start) is how much we've progressed in the l half
            // (r - mid) is how much we've progressed in the r half
            merged[(l - start) + (r - mid)] = arr[r];
            r++;
        }
        // copy back to original arr
        for (int i = 0; i < merged.length; i++) {
            arr[start + i] = merged[i];
        }
        return countInversionsWhenMerging;
    }
}