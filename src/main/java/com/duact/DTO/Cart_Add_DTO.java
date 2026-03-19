package com.duact.DTO;

public class Cart_Add_DTO {

    private Long id;
    private String name;
    private String imageFile;
    private int quantity;
    private double price;
    private double totalPrice;

    public Cart_Add_DTO() {}

    public Cart_Add_DTO(Long id, String name, String imageFile,
                        int quantity, double price) {
        this.id = id;
        this.name = name;
        this.imageFile = imageFile;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price * quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.price * quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.totalPrice = this.price * this.quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
