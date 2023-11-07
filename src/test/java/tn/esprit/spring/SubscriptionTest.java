package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionTest {

    private Subscription subscription;

    @BeforeEach
    public void setUp() {
        // Initialize test data before each test
        subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.of(2023, 1, 1));
        subscription.setEndDate(LocalDate.of(2024, 1, 1));
        subscription.setPrice(100.0f);
        subscription.setTypeSub(TypeSubscription.ANNUAL);
    }

    @Test
    public void testGetNumSub() {
        assertEquals(1L, subscription.getNumSub());
    }

    @Test
    public void testGetStartDate() {
        assertEquals(LocalDate.of(2023, 1, 1), subscription.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        assertEquals(LocalDate.of(2024, 1, 1), subscription.getEndDate());
    }

    @Test
    public void testGetPrice() {
        assertEquals(100.0f, subscription.getPrice(), 0.01); // Utilize a tolerance margin
    }

    @Test
    public void testGetTypeSub() {
        assertEquals(TypeSubscription.ANNUAL, subscription.getTypeSub());
    }
}

