package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingPaymentDAO extends JpaRepository<BookingPayment, Integer> {

}
