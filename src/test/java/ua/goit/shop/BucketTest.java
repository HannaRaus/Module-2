package ua.goit.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucketTest {

    @Test
    public void testShouldCalculateTotalCost_happyPath() {
        String bucket = "ABCDABA";
        double expected = 13.25;
        double actual = Bucket.calculateTotalCost(bucket);
        assertEquals(expected, actual);

        String bucketWithLowerCaseLetters = "abdCAb";
        double expectedWithLowerCaseLetters = 12.75;
        double actualWithLowerCaseLetters = Bucket.calculateTotalCost(bucketWithLowerCaseLetters);
        assertEquals(expectedWithLowerCaseLetters, actualWithLowerCaseLetters);
    }

    @Test
    public void testShouldCalculateTotalCost_withEmptyBucket() {
        assertThrows(BucketException.class, () -> Bucket.calculateTotalCost(null),
                "Please, enter product's codes");
        assertThrows(BucketException.class, () -> Bucket.calculateTotalCost(""),
                "Please, enter product's codes");
    }

    @Test
    public void testShouldCalculateTotalCost_withUnknownProducts() {
        String bucketWithUnknownCodes = "fknnf";
        double expectedWithUnknownCodes = 0;
        double actualWithUnknownCodes = Bucket.calculateTotalCost(bucketWithUnknownCodes);
        assertEquals(expectedWithUnknownCodes, actualWithUnknownCodes);

        String bucketWithSpace = " ABC DA BA";
        double expectedWithSpace = 13.25;
        double actualWithSpace = Bucket.calculateTotalCost(bucketWithSpace);
        assertEquals(expectedWithSpace, actualWithSpace);

        String bucketWithNumbers = "ABC58DABA25";
        double expectedWithNumbers = 13.25;
        double actualWithNumbers = Bucket.calculateTotalCost(bucketWithNumbers);
        assertEquals(expectedWithNumbers, actualWithNumbers);

        String bucketWithCorrectAndUnknown = "ABCDABAGHN";
        double expectedWithCorrectAndUnknown = 13.25;
        double actualWithCorrectAndUnknown = Bucket.calculateTotalCost(bucketWithCorrectAndUnknown);
        assertEquals(expectedWithCorrectAndUnknown, actualWithCorrectAndUnknown);
    }
}