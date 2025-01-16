package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductType;
import com.example.mvcproducts.domain.Role;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.ProductService;
import com.example.mvcproducts.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataLoader implements CommandLineRunner {
  private final ProductService productService;
  private final UserService userService;

  public DataLoader(ProductService productService, UserService userService) {
    this.productService = productService;
    this.userService = userService;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Product> productList = List.of(
            new Product("Lipozom Oil Ampoule", ProductType.SKINCARE, "Nice car",60, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\sk3.jpg" ),
            new Product("Centella Asiatica Face Mask", ProductType.SKINCARE, "Lenovo",12, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\sk4.jpg" ),
            new Product("Green Tangerine C Dark Spot Care Serum", ProductType.SKINCARE, "Office",40, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\sk5.jpg" ),
            new Product("A'PIEU Icing Sweet Bar Sheet Mask", ProductType.SKINCARE, "win",10, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\sk6.jpg" ),
            new Product("Anua Birch 70% Moisture Boosting Serum", ProductType.SKINCARE, "Sweet",80, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\sk7.jpg" ),
            new Product("Vegan Rice Milk Toner", ProductType.SKINCARE, "Sweet",50, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\sk8.jpg" ),
            new Product("UNLEASHIA Don't Touch Glass Pink Cushion", ProductType.MAKEUP, "Sweet",50, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\m1.jpg" ),
            new Product("LANEIGE Glowy Makeup Serum", ProductType.MAKEUP, "Sweet",110, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\m5.jpg" ),
            new Product("Glacier Vegan Lip Balm", ProductType.MAKEUP, "Sweet",70, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\m2.jpg" ),
            new Product("CLIO Pro Eye Palette", ProductType.MAKEUP, "Sweet",130, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\m6.jpg" ),
            new Product("LANEIGE Ultimistic Whipping Tint", ProductType.MAKEUP, "Sweet",100, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\m7.jpg" ),
            new Product("CLIO Kill Cover Mesh Glow Cushion Set", ProductType.MAKEUP, "Sweet",65, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\m9.jpg" ),
            new Product("Glow Replenishing Rice Milk", ProductType.SKINCARE, "Nice car",81, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\rice.jpg" ),
            new Product("Skin1004 Madagascar Centella Light Cleansing Oil", ProductType.SKINCARE, "Lenovo",73, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs1.jpg" ),
            new Product("Anua 7 Rice Ceramide Hydrating Barrier Serum", ProductType.SKINCARE, "Office",119, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs2.jpg" ),
            new Product("Beauty of Joseon Dynasty Cream", ProductType.SKINCARE, "win",159, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs3.jpg" ),
            new Product("Anua Rice 70 Intensive Moisturizing Milk", ProductType.SKINCARE, "Sweet",110, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs4.jpg" ),
            new Product("Anua BHA 2% Gentle Exfoliating Toner", ProductType.SKINCARE, "Sweet",119, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs5.jpg" ),
            new Product("A’pieu Milk and Honey Lip Balm", ProductType.MAKEUP, "Sweet",36, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs6.jpg" ),
            new Product("Romand Better Than Palette", ProductType.MAKEUP, "Sweet",119, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs7.jpg" ),
            new Product("Peripera Ink Mood Glowy Tint Peritage Collection", ProductType.MAKEUP, "Sweet",56, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs8.jpg" ),
            new Product("Flortte Limited Edition Lip Gloss Wackky Collection", ProductType.MAKEUP, "Sweet",55, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs9.jpg" ),
            new Product("Romand Bare Water Cushion", ProductType.MAKEUP, "Sweet",99, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs10.jpg" ),
            new Product("TirTir Mask Fit Red Cushion", ProductType.MAKEUP, "Sweet",99, "C:\\Users\\Ruxandra\\Desktop\\kbeauty\\src\\main\\resources\\static\\images\\produs11.jpg" ));
    productService.saveAll(productList);

    PasswordEncoder bcrypt = new BCryptPasswordEncoder();

    User user1 = new User("user1@yahoo.com", "user1", bcrypt.encode("user1"));
    user1.getRoles().add(Role.ROLE_USER);

    User user2 = new User("admin@outlook.com", "admin", bcrypt.encode("admin"));
    user2.getRoles().add(Role.ROLE_ADMIN);

    User user3 = new User("user3@gmail.com", "user3", bcrypt.encode("user3"));
    user3.getRoles().add(Role.ROLE_USER);

    userService.save(user1);
    userService.save(user2);
    userService.save(user3);
  }
}
