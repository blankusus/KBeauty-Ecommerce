package com.example.mvcproducts.domain;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class OrderLineItem {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private Product product;

  private int qty;

  public OrderLineItem() {}

  public OrderLineItem(Long id, Product product, int qty) {
    this.id = id;
    this.product = product;
    this.qty = qty;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQty() {
    return qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }
}
