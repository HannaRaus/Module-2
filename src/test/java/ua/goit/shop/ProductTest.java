package ua.goit.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product testProduct;

    @BeforeEach
    public void init() {
        testProduct = new Product("A", 1.25, 3, 3);
    }

    @Test
    public void testShouldGetPrice_happyPath() {
        double expected = 1.25;
        double actual = testProduct.getPrice(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testShouldGetPrice_whenAmountIsZero() {
        assertThrows(BucketException.class, () -> testProduct.getPrice(0),
                "Amount can't be less than 1");
    }

    @Test
    public void testShouldGetPrice_whenAmountIsLessThanZero() {
        assertThrows(BucketException.class, () -> testProduct.getPrice(-2),
                "Amount can't be less than 1");
    }

    @Test
    public void testShouldCompareProducts_happyPath() {
        Product similar = new Product("A", 1.25, 3, 3);
        assertEquals(similar, testProduct);
    }

    @Test
    public void testShouldCompareProducts_differentProduct() {
        Product another = new Product("B", 4.25);
        assertNotEquals(another, testProduct);

        Product sameWithAnotherPrice = new Product("A", 1.5, 3, 3);
        assertNotEquals(sameWithAnotherPrice, testProduct);

        Product sameWithAnotherPromotionalAmount = new Product("A", 1.25, 4, 3);
        assertNotEquals(sameWithAnotherPromotionalAmount, testProduct);

        Product sameWithAnotherPromotionalPrice = new Product("A", 1.25, 3, 4);
        assertNotEquals(sameWithAnotherPromotionalPrice, testProduct);
    }

    @Test
    public void testShouldCompareHashCode_happyPath() {
        Product linkToTestProduct = testProduct;
        assertEquals(linkToTestProduct.hashCode(), testProduct.hashCode());

        Product sameToTestProduct = new Product("A", 1.25, 3, 3);
        assertEquals(sameToTestProduct.hashCode(), testProduct.hashCode());
    }

    @Test
    public void testShouldCompareHashCode_differentProduct() {
        Product sameWithAnotherPrice = new Product("A", 1.24, 3, 3);
        assertNotEquals(sameWithAnotherPrice.hashCode(), testProduct.hashCode());
    }

}