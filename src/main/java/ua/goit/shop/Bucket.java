package ua.goit.shop;

import java.util.*;
import java.util.stream.Collectors;

public class Bucket {
    private static final Map<String, Product> PRODUCTS = new HashMap<>();

    static {
        PRODUCTS.put("A", new Product("A", 1.25, 3, 3));
        PRODUCTS.put("B", new Product("B", 4.25));
        PRODUCTS.put("C", new Product("C", 1.00, 6, 5));
        PRODUCTS.put("D", new Product("D", 0.75));
    }

    public static double calculateTotalCost(String bucket) {
        String filteredBucket = filterBucketFromUnknownProducts(bucket);
        if (checkIfBucketIsNotEmpty(filteredBucket)) {
            Map<String, Long> productsAmount = Arrays.stream(filteredBucket.split(""))
                    .map(PRODUCTS::get)
                    .collect(Collectors.groupingBy(Product::getCode,
                            Collectors.mapping(Product::getCode, Collectors.counting())));
            return productsAmount.entrySet().stream()
                    .mapToDouble(p -> PRODUCTS.get(p.getKey()).getPrice(p.getValue()))
                    .sum();
        } else {
            return 0;
        }
    }


    private static String filterBucketFromUnknownProducts(String bucket) {
        if (checkIfBucketIsNotEmpty(bucket)) {
            return Arrays.stream(bucket.toUpperCase(Locale.ROOT)
                    .split(""))
                    .filter(PRODUCTS::containsKey)
                    .collect(Collectors.joining());
        } else {
            throw new BucketException("Please, enter product's codes");
        }
    }

    private static boolean checkIfBucketIsNotEmpty(String bucket) {
        return bucket != null && !bucket.isEmpty();
    }

}
