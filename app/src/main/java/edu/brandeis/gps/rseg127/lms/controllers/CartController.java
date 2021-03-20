package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.brandeis.gps.rseg127.lms.entities.Cart;
import edu.brandeis.gps.rseg127.lms.services.CartService;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping(path="/api/cart", produces="application/json")
    public ResponseEntity<List<Cart>> getAllCartItems() {
        return new ResponseEntity<>(cartService.getAllCartItems(), HttpStatus.OK);
    }

    @PostMapping(consumes="application/json", produces="application/json", path="/api/cart")
    public ResponseEntity<Cart> createCartItem(@RequestBody Cart cartItem) {        
        return new ResponseEntity<>(cartService.createCartItem(cartItem), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/api/cart/{id}", produces = "application/json")
    public ResponseEntity<String> deleteCartItem(@PathVariable(value = "id") Integer id) {
        cartService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
