// Algorithms HW5
// Fall 2022
// Instructor: Professor Garg
// Student Submitting: Christopher Li

// algo hw5 q1c

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Node {
    Integer val; // value
    Node(int v) {
        val = v;
    }
    @Override
    public String toString() {
        return val.toString();
    }
}
public class MaximumIndependentSetInPath {
    public static void main(String[] args) {
        System.out.println("Independent Set With Maximum Weight Sum in Path");
        System.out.println("Test cases:");
        System.out.println();
        int[][] testCases = {
                {10, 1, 1, 10},
                {10, 1, 1, 4, 9, 8, 2, 1, 9, 6},
                {9, 10, 8},
                {1, 2, 3, 4, 5},
                {1, 8, 6, 3, 6}
        };
        int[] expected = {
             20,
             31,
             17,
             9,
             14
        };
        boolean allTestCasesPassed = true;
        for (int j = 0; j < testCases.length; j++) {

            int[] arr = testCases[j];
            Node[] nodes = new Node[arr.length];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node(arr[i]);
            }
            List<Node> path = new ArrayList<>(Arrays.asList(nodes));
            System.out.println("Test case: " + j);
            System.out.println("Original path:");
            System.out.println(Arrays.toString(path.toArray()));
            List<Node> independentSet = independentSetWithMaximumWeightSum(path);
            System.out.println("Independent set:");
            System.out.println(Arrays.toString(independentSet.toArray()));
            int sum = sumOfWeightsIn(independentSet);
            System.out.println("Max sum of independent set: " + sum);
            if (sum != expected[j]) {
                System.out.println("Test case " + j + "failed: expected " + expected[j]);
                allTestCasesPassed = false;
            }
            else {
                System.out.println("Passed! Expected " + expected[j] + " and received correct answer");
            }
            System.out.println();

        }
        if (allTestCasesPassed) {
            System.out.println("All test cases passed!");

        }
    }

    // returns a list with the nodes
    // in the independent set
    public static List<Node> independentSetWithMaximumWeightSum(List<Node> path) {
//        return independentSetWithMaximumWeightSum(path, 0);
        // bottom up dp
        int n = path.size();
        int[] dp = new int[n + 2];
        int[] indicesTrace = new int[n];
        boolean[] take = new boolean[n];

        // focus on the value only for now
        for (int i = n - 1; i >= 0; i--) {
            if (path.get(i).val + dp[i + 2] > dp[i + 1]) {
                dp[i] = path.get(i).val + dp[i + 2]; // max sum here
                indicesTrace[i] = i + 2;
                take[i] = true;
            }
            else {
                dp[i] = dp[i + 1]; // max sum here
                indicesTrace[i] = i + 1;
                take[i] = false;
            }
        }
        int sumCheck = 0;
        int idx = 0; // idx of current node
        List<Node> independentSet = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (idx >= n) {
                break; // and we're done!
                // jumped through the whole array
            }
            if (take[idx]) {
                // originally i did path.get(i) instead of
                // path.get(idx), which was a mistake
                // i is just a loop variable
                // idx is the actual index into the n-length path
                // of where to go next
                independentSet.add(path.get(idx));
                idx += 2;
            }
            else {
                idx += 1;
            }
            //idx = indicesTrace[i]; // for the next place to
            // go to
        }
        // debugging output print statements
        //System.out.println(dp[0] == sumCheck);
        //System.out.println("dp[0]: " + dp[0]);
        //System.out.println("sumCheck: " + sumCheck);
        return independentSet;
    }
    public static List<Node> independentSetWithMaximumWeightSum(List<Node> path, int i) {
        List<Node> list = new ArrayList<>();
        // base case
        if (i >= path.size()) {
            return list; // empty list
        }
        List<Node> takeThisOne = new ArrayList<>();
        takeThisOne.add(path.get(i)); // taking this one
        takeThisOne.addAll(independentSetWithMaximumWeightSum(path, i + 2));
        int pickThisOne = path.get(i).val + sumOfWeightsIn(takeThisOne);

        List<Node> leaveThisOne = independentSetWithMaximumWeightSum(path, i + 1);
        int skipThisOne = sumOfWeightsIn(leaveThisOne);
        if (pickThisOne > skipThisOne) {
            return takeThisOne;
        }
        else { // == case included, since we just need to return any indep set
            return leaveThisOne;
        }
    }
    public static int sumOfWeightsIn(List<Node> listOfNodes) {
        int sum = 0;
        for (Node node : listOfNodes) {
            sum += node.val;
        }
        return sum;
    }
}
