import java.util.*;
import java.util.stream.*;
import java.util.Scanner;

public class FactoryAssigner {
    //matrix for keeping dp distances
    private boolean[][] dp;

    void printAssignmentList(List<Integer> v) {
        System.out.println(v.toString().replaceAll("[,\\[\\]]", ""));
    }

    void iterateSubsetsRecursive(int[] arr, int i, int sum,
                                 ArrayList<Integer> p, ArrayList<ArrayList<Integer>> solutions) {
        if (i == 0 && sum != 0 && dp[0][sum]) {
            p.add(arr[i]);
            solutions.add(new ArrayList<>(p));
            p.clear();
            return;
        }

        if (i == 0 && sum == 0) {
            solutions.add(new ArrayList<>(p));
            p.clear();
            return;
        }

        if (dp[i - 1][sum]) {
            ArrayList<Integer> b = new ArrayList<>(p);
            iterateSubsetsRecursive(arr, i - 1, sum, b, solutions);
        }

        if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
            p.add(arr[i]);
            iterateSubsetsRecursive(arr, i - 1, sum - arr[i], p, solutions);
        }
    }

    ArrayList<ArrayList<Integer>> getAllSubsetsWithGivenSum(int[] arr, int n, int sum) {
        //if either n=0 or sum is less than 0, there is no solution available
        if (n == 0 || sum < 0) {
            System.out.println("Nr solutions=0");
            return new ArrayList<>();
        }
        //get total sum of the given array
        int total = Arrays.stream(arr).sum();
        //if there's no leg room to create sub-lists anyway, then there's only one solution = original array
        if (total <= sum) {
            ArrayList<Integer> arrAsList = Arrays.stream(arr).boxed().collect(Collectors.toCollection(ArrayList::new));
            System.out.println("Nr solutions=1");
            printAssignmentList(arrAsList);
            System.out.println("Waste=" + (sum - total));
            return new ArrayList<>(List.of(arrAsList));
        }

        //init matrix for dynamic programming
        dp = new boolean[n][sum + 1];
        //init 0 row to true, because solution for 0 sum is trivial (empty sublist)
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }

        //sum equivalent of a given single element in the array is also trivial (sublist containing only 1 element)
        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

        //fill the rest of dp matrix
        for (int i = 1; i < n; ++i)
            for (int j = 0; j < sum + 1; ++j)
                dp[i][j] = (arr[i] <= j) ? (dp[i - 1][j] ||
                        dp[i - 1][j - arr[i]])
                        : dp[i - 1][j];
        //if we cannot reach the expected sum, then there's no solution without a waste
        if (!dp[n - 1][sum]) {
            //System.out.println("There are solutions with sum"+ sum);
            System.out.println("Nr solutions=0");
            return new ArrayList<>();
        }

        //current subset (payload) that will be manipulated throughout the recursive function
        ArrayList<Integer> p = new ArrayList<>();
        //array which successful solutions will be added into
        ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
        //call recursive function to iterate over solutions and add them to 'solutions' list
        iterateSubsetsRecursive(arr, n - 1, sum, p, solutions);
        //print out the result
        System.out.println("Nr solutions=" + solutions.size());
        solutions.forEach(this::printAssignmentList);
        System.out.println("Waste=0");
        return solutions;
    }

    //to read input from console/stdin, run this method and provide input in the following format:
    // number of workers
    // production amount per unit time for each worker
    // intended sum of production
    public static void main(String[] args) {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        int sum = scan.nextInt();
        factoryAssigner.getAllSubsetsWithGivenSum(arr, n, sum);
    }
} 