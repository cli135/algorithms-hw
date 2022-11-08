public class Main {
    public static void main(String[] args) {
        runCountInversions();
    }
    public static void runCountInversions() {
        // System.out.println("Hello world!");
        int[] arr = { 5, 3, 4, 2, 6, 7, 1, 0 }; // ??? inversions

        int[] arr2 = { 3, 2, 5, 7, 4, 1, 6, 0 }; // 16? inversions
        int[] arr3 = { 7, 6, 5, 4, 3, 2, 1, 0 }; // 16? inversions
        int[] arr4 = { 0, 1, 2, 3, 4, 5, 6, 7 }; // 0
        int a = CountInversionsReformattedCode.countInversions(arr2);
        int b = CountInversionsReformattedCode.countInversions(arr3);
        int c = CountInversionsReformattedCode.countInversions(arr4);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
