package gov.iti.jets.dto;

public class OrderDetailsTableDto {

    String product;

    String category;

    String size;

    Integer quantity;

    Double price;

    public OrderDetailsTableDto() {
    }

    public OrderDetailsTableDto(String product, String category, String size, Integer quantity, Double price) {
        this.product = product;
        this.category = category;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
