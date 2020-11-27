import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactoryAssignerTest {
    @Test
    void checkCorrectAssignments() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{1, 2, 4, 10, 5, 6};
        int sum = 11;
        ArrayList<ArrayList<Integer>> expectedSolutions = new ArrayList<>(List.of(
                new ArrayList<>(List.of(10, 1)),
                new ArrayList<>(List.of(5, 4, 2)),
                new ArrayList<>(List.of(6, 4, 1)),
                new ArrayList<>(List.of(6, 5))
        ));

        ArrayList<ArrayList<Integer>> solutions = factoryAssigner.getAllSubsetsWithGivenSum(arr, arr.length, 11);
        assertEquals(4, solutions.size());
        assertEquals(expectedSolutions, solutions);
    }

    //if sum is too high, we should get the trivial solution, which is the original array with workers
    @Test
    void checkTrivialAssignment_whenSumIsTooHigh() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{1, 2, 4, 3};
        int sum = 13;
        ArrayList<ArrayList<Integer>> expectedSolutions = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 2, 4, 3))
        ));

        ArrayList<ArrayList<Integer>> solutions = factoryAssigner.getAllSubsetsWithGivenSum(arr, arr.length, sum);
        assertEquals(1, solutions.size());
        assertEquals(expectedSolutions, solutions);
    }

    //expecting a negative sum to be gathered from workers should return no solution
    @Test
    void checkUnsuccessfulAssignment_whenSumIsInvalid() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{1, 2, 4, 3};
        int sum = -1;

        ArrayList<ArrayList<Integer>> solutions = factoryAssigner.getAllSubsetsWithGivenSum(arr, arr.length, sum);
        assertEquals(0, solutions.size());
    }
}
