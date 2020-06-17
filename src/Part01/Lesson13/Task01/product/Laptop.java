package Part01.Lesson13.Task01.product;

/**
 * Laptop.
 *
 * @author Roman Khokhlov
 */
public class Laptop {
    private Integer id;
    private String name;
    private Double price;
    private String productGroup;

    public Laptop() {
    }

    public Laptop(Integer id, String name, String productGroup, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productGroup='" + productGroup + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }
}
