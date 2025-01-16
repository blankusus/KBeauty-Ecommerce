package com.example.mvcproducts.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ProductOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "order_id") // Specifies the foreign key column in OrderLineItem
  private Set<OrderLineItem> orderLineItems = new HashSet<>();

  @Column(nullable = false)
  private LocalDateTime createdDate;

  // Customer details
  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String phoneNumber;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String postalCode;

  @Column(nullable = false)
  private String paymentMethod;

  @Column
  private String cardNumber; // Nullable for cash payments

  public ProductOrder() {
    this.createdDate = LocalDateTime.now();
  }

  public ProductOrder(Long id, Set<OrderLineItem> orderLineItems, String firstName, String lastName,
                      String phoneNumber, String address, String postalCode, String paymentMethod, String cardNumber) {
    this.id = id;
    this.orderLineItems = orderLineItems;
    this.createdDate = LocalDateTime.now();
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.postalCode = postalCode;
    this.paymentMethod = paymentMethod;
    this.cardNumber = cardNumber;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<OrderLineItem> getOrderLineItems() {
    return orderLineItems;
  }

  public void setOrderLineItems(Set<OrderLineItem> items) {
    this.orderLineItems = items;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

}
