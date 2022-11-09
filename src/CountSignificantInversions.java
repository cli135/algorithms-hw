// hw4, problem 1
// 11-2-2022
// intro algos fall 2022

public class CountSignificantInversions {

    public static int countSignificantInversions(int[] arr) {
        return sortAndMerge(arr, 0, arr.length);
    }

    /**
     * Runs mergesort on arr from start (inclusive)
     * to end (exclusive). Returns the number of significant
     * inversions detected in the left half, the right half, and the
     * overall merge operation of both halves.
     *
     * @param arr   the array to be sorted
     * @param start the start index (inclusive) of the portion
     * @param end   the end index (exclusive) of the portion
     * @return the total number of significant inversions detected in array
     *         from [start, end), including significant inversions
     *         detected at deeper levels of recursion
     */
    private static int sortAndMerge(int[] arr, int start, int end) {
        // base case, already sorted, no significant inversions
        if (end - start <= 1) {
            return 0;
        }
        // Recurrence relation for recursive case:
        // T(N) <= 2T(N/2) + O(N)
        // counting significant inversions in each half
        int mid = start + (end - start) / 2;
        int left = sortAndMerge(arr, start, mid);
        int right = sortAndMerge(arr, mid, end);
        // and counting significant inversions and merging in O(2n) == O(n) time
        int inversionsWhenMerging = countAndMerge(arr, start, end);
        // total significant inversions
        return left + right + inversionsWhenMerging;
    }

    /**
     * Counts the number of significant inversions between
     * both halves of this array (only at this level), and
     * merges the two sorted halves of the array from
     * [start, mid) and [mid, end).
     * Returns the number of significant inversions detected
     * between both sorted halves of the array before the
     * merge operation of both halves (only at this level).
     *
     * @param arr   the array to be sorted
     * @param start the start index (inclusive) of the portion
     * @param end   the end index (exclusive) of the portion
     * @return the number of significant inversions detected in array
     *         from [start, end) when merging two halves in [start, end)
     */
    private static int countAndMerge(int[] arr, int start, int end) {
        int mid = start + (end - start) / 2;
        // 1. counting significant inversions
        int l = start;
        int r = mid;
        // significant inversions
        int countSignificantInversions = 0;
        // 1st pass significant inversion check
        while (l < mid && r < end) {
            if (arr[l] > 2 * arr[r]) {
                // significant inversion detected
                // counting multiple significant inversions
                // at a time by transitivity:
                // mid - l is the number of transitive inversions
                // we can deduce, i.e. all the elements in left half
                // even greater than right element we just compared
                countSignificantInversions += mid - l;
                r++; // move right pointer
            }
            else {
                // no significant inversion yet
                l++; // move left pointer
            }
        }
        // 2. mergesort merge operation
        l = start;
        r = mid;
        int[] merged = new int[end - start];
        // 2nd pass merge
        while (l < mid && r < end) {
            if (arr[l] > arr[r]) {
                // normal inversion detected - for sorting
                // copying right value to merged array
                merged[(l - start) + (r - mid)] = arr[r];
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
        return countSignificantInversions;
    }
}