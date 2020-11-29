import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryTreeCheckerTest {
    @Test
    void checkValidBinaryTree() {
        String[] pairs = new String[]{
                "ab",
                "bf",
                "ac",
                "be",
                "cn"
        };
        BinaryTreeChecker bt = new BinaryTreeChecker(pairs);
        assertTrue(bt.isBinaryTree());
    }

    @Test
    void checkValidBinaryTree_whenItHasSingleEdge() {
        String[] pairs = new String[]{
                "ab",
        };
        BinaryTreeChecker bt = new BinaryTreeChecker(pairs);
        assertTrue(bt.isBinaryTree());
    }

    @Test
    void checkInvalidValidBinaryTree_whenItContainsACycle() {
        String[] pairs = new String[]{
                "ab",
                "ba"
        };
        BinaryTreeChecker bt = new BinaryTreeChecker(pairs);
        assertFalse(bt.isBinaryTree());

        pairs = new String[]{
                "aa"
        };
        bt = new BinaryTreeChecker(pairs);
        assertFalse(bt.isBinaryTree());
    }

    @Test
    void checkInvalidValidBinaryTree_whenANodeContainsFanOutLargerThanTwo() {
        String[] pairs = new String[]{
                "ab",
                "ac",
                "ad"
        };
        BinaryTreeChecker bt = new BinaryTreeChecker(pairs);
        assertFalse(bt.isBinaryTree());

        pairs = new String[]{
                "ab",
                "bc",
                "bd",
                "be"
        };
        bt = new BinaryTreeChecker(pairs);
        assertFalse(bt.isBinaryTree());
    }

    @Test
    void checkInvalidValidBinaryTree_whenANodeContainsFanInLargerThanOne() {
        String[] pairs = new String[]{
                "ab",
                "ac",
                "cd",
                "bd"
        };
        BinaryTreeChecker bt = new BinaryTreeChecker(pairs);
        assertFalse(bt.isBinaryTree());
    }
}
