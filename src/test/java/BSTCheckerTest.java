import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BSTCheckerTest {
    @Test
    void checkValidBST() {
        BSTChecker bst = new BSTChecker();
        String[] arr = new String[]{"ab", "ac", "bf", "be", "cn"};
        assertTrue(bst.isPreOrderValidForBST(arr, arr.length));
    }

    @Test
    void checkInvalidBST_whenInputIsNotPreOrdered() {
        BSTChecker bst = new BSTChecker();
        String[] arr = new String[]{"ac", "cn", "ab"};
        assertFalse(bst.isPreOrderValidForBST(arr, arr.length));

        String[] arr2 = new String[]{"da", "ca", "cb", "ba", "ga", "ha"};
        assertFalse(bst.isPreOrderValidForBST(arr2, arr2.length));
    }

    //empty input should be considered as a valid list of nodes
    @Test
    void checkValidBST_whenInputIsEmpty() {
        BSTChecker bst = new BSTChecker();
        String[] arr = new String[]{};
        assertTrue(bst.isPreOrderValidForBST(arr, arr.length));
    }
}
