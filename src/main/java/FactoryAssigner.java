import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FactoryAssigner {
    //matrix for keeping dp distances
    private boolean[][] dp;

    void printAssignmentList(List<Integer> v) {
        System.out.println(v.toString().replaceAll("[,\\[\\]]", ""));
    }

    void iterateSubsetsRecursive(int[] arr, int i, int sum,
                                 List<Integer> p, List<List<Integer>> solutions) {
        //success case, we found an entry with requested sum, add as solution
        if (i == 0 && sum != 0 && dp[0][sum]) {
            p.add(arr[i]);
            solutions.add(List.copyOf(p));
            p.clear();
            return;
        }

        //success case, if requested sum is reduced to zero, add as solution
        if (i == 0 && sum == 0) {
            solutions.add(List.copyOf(p));
            p.clear();
            return;
        }

        //recursive case a) ignore the current element
        if (dp[i - 1][sum]) {
            List<Integer> b = new ArrayList<>(p);
            iterateSubsetsRecursive(arr, i - 1, sum, b, solutions);
        }

        //recursive case b) include current element in the sublist, continue search with (sum - currentelement)
        if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
            p.add(arr[i]);
            iterateSubsetsRecursive(arr, i - 1, sum - arr[i], p, solutions);
        }
    }

    List<List<Integer>> getAllSubsetsWithGivenSum(int[] arr, int n, int sum) {
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
            //System.out.println("There no are solutions with sum "+ sum);
            return List.of();
        }

        //current subset (payload) that will be manipulated throughout the recursive function
        List<Integer> p = new ArrayList<>();
        //array which successful solutions will be added into
        List<List<Integer>> solutions = new ArrayList<>();
        //call recursive function to iterate over solutions and add them to 'solutions' list
        iterateSubsetsRecursive(arr, n - 1, sum, p, solutions);
        return solutions;
    }

    List<List<Integer>> getAllSubsetsEqualOrGreaterThanGivenSum(int[] arr, int n, int sum) {
        int initialSum = sum;
        boolean solutionExists = false;
        //if either n=0 or sum is less than 0, there is no solution available
        if (n == 0 || sum < 0) {
            System.out.println("Nr solutions=0");
            return List.of();
        }
        //get total sum of the given array
        int total = Arrays.stream(arr).sum();

        List<List<Integer>> solutions = List.of();
        //if we can't find the perfect solution, keep increasing 'sum' until we find a solution with waste
        while (!solutionExists && sum <= total) {
            solutions = getAllSubsetsWithGivenSum(arr, n, sum);
            solutionExists = solutions.size() > 0;
            sum++;
        }

        if (solutions.size() > 0) {
            //print out the result
            System.out.println("Nr solutions=" + solutions.size());
            solutions.forEach(this::printAssignmentList);
            System.out.println("Waste=" + (sum - initialSum - 1));
        } else {
            System.out.println("Nr solutions=0");
            return List.of();
        }

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
        factoryAssigner.getAllSubsetsEqualOrGreaterThanGivenSum(arr, n, sum);
    }
} 