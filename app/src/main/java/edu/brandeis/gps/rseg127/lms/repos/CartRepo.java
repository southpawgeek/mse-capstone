package edu.brandeis.gps.rseg127.lms.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.brandeis.gps.rseg127.lms.entities.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c where c.userId = ?1")
    List<Cart> findByUserId(Integer userId);
}
