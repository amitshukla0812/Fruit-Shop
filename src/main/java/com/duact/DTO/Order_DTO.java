package com.duact.DTO;

public class Order_DTO {

    private Long id;

    private String email;
    private double totalPrice;
    private String status;
	public Order_DTO(Long id, String email, double  totalPrice, String status) {
		super();
		this.id = id;
		this.email = email;
		this. totalPrice = totalPrice;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double  totalPrice) {
		this. totalPrice =  totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
    
   
}
