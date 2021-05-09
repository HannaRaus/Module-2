package ua.goit.shop;

import java.util.Objects;

public class Product {
    private final String code;
    private final double price;
    private final int promotionalAmount;
    private final double promotionalPrice;

    public Product(String code, double price, int promotionalAmount, double promotionalPrice) {
        this.code = code;
        this.price = price;
        this.promotionalAmount = promotionalAmount;
        this.promotionalPrice = promotionalPrice;
    }

    public Product(String code, double price) {
        this(code, price, 1, price);
    }

    public String getCode() {
        return code;
    }

    public double getPrice(long amount) {
        if (amount <= 0) {
            throw new BucketException("Amount can't be less than 1");
        }
        if (amount < promotionalAmount) {
            return amount * price;
        } else {
            return (double) (amount / promotionalAmount) * promotionalPrice + (amount % promotionalAmount) * price;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                promotionalAmount == product.promotionalAmount &&
                Double.compare(product.promotionalPrice, promotionalPrice) == 0 &&
                Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, price, promotionalAmount, promotionalPrice);
    }

    @Override
    public String toString() {
        return "Product" +
                " '" + code + '\'' +
                ", price is " + price;
    }
}
