package com.smoothstack.utopia_spring.services;

import com.smoothstack.utopia_spring.dao.BookingGuestDAO;
import com.smoothstack.utopia_spring.model.BookingGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingGuestService {

    @Autowired
    private BookingGuestDAO bookingGuestDao;

    public BookingGuest getById(int booking_id) {
        try {
            return bookingGuestDao.findById(booking_id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BookingGuest();
    }

    public List<BookingGuest> getAllBookingGuests() {
        return bookingGuestDao.findAll();
    }

    public boolean insertBookingGuest(BookingGuest booking_guest) {
        try {
            bookingGuestDao.save(booking_guest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBookingGuest(BookingGuest booking_guest) {
        try {
            bookingGuestDao.delete(booking_guest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBookingGuest(int booking_id) {
        try {
            bookingGuestDao.deleteById(booking_id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
