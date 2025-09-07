package ap.exercises.Finalexam;

import java.util.Objects;

public class Product {
    private String name;
    private String fee;
    public Product(String name,String fee){
        this.fee = fee.trim();
        this.name=name;

    }

    public String getFee() {
        return fee;
    }
    public int getFeeAsInt() {
        try {
            return Integer.parseInt(fee.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public String getName() { return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return fee == product.fee &&
                Objects.equals(name, product.name) &&
                this.getClass() == product.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fee, getClass());
    }
}
