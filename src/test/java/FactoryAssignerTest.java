import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactoryAssignerTest {
    @Test
    void checkCorrectAssignments() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{1, 2, 4, 10, 5, 6};
        int sum = 11;
        List<List<Integer>> expectedSolutions = List.of(
                List.of(10, 1),
                List.of(5, 4, 2),
                List.of(6, 4, 1),
                List.of(6, 5)
        );

        List<List<Integer>> solutions = factoryAssigner.getAllSubsetsEqualOrGreaterThanGivenSum(arr, arr.length, sum);
        assertEquals(4, solutions.size());
        assertEquals(expectedSolutions, solutions);
    }

    @Test
    void checkSuccessfulAssignmentWithWaste_whenNoPerfectAssignmentExists() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{6, 7, 15};
        int sum = 8;
        List<List<Integer>> expectedSolutions = List.of(
                List.of(7, 6)
        );

        List<List<Integer>> solutions = factoryAssigner.getAllSubsetsEqualOrGreaterThanGivenSum(arr, arr.length, sum);
        assertEquals(1, solutions.size());
        assertEquals(expectedSolutions, solutions);
    }

    @Test
    void checkMultipleSuccessfulAssignmentsWithWaste_whenNoPerfectAssignmentExists() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{2, 5, 7, 8, 11};
        int sum = 6;
        List<List<Integer>> expectedSolutions = List.of(
                List.of(5, 2),
                List.of(7)
        );

        List<List<Integer>> solutions = factoryAssigner.getAllSubsetsEqualOrGreaterThanGivenSum(arr, arr.length, sum);
        assertEquals(2, solutions.size());
        assertEquals(expectedSolutions, solutions);
    }

    //if sum is too high, there's no way to satisfy the expected production
    @Test
    void checkUnsuccessfulAssignment_whenSumIsTooHigh() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{1, 2, 4, 3};
        int sum = 13;

        List<List<Integer>> solutions = factoryAssigner.getAllSubsetsEqualOrGreaterThanGivenSum(arr, arr.length, sum);
        assertEquals(0, solutions.size());
    }

    //expecting a negative sum to be gathered from workers should return no solution
    @Test
    void checkUnsuccessfulAssignment_whenSumIsInvalid() {
        FactoryAssigner factoryAssigner = new FactoryAssigner();
        int[] arr = new int[]{1, 2, 4, 3};
        int sum = -1;

        List<List<Integer>> solutions = factoryAssigner.getAllSubsetsEqualOrGreaterThanGivenSum(arr, arr.length, sum);
        assertEquals(0, solutions.size());
    }
}
