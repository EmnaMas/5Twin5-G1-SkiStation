package tn.esprit.spring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SubscriptionMockTest {

    @Mock
    private Subscription mockSubscription;

    @BeforeEach
    public void setUp() {
        // Initialize mock objects before each test
        MockitoAnnotations.openMocks(this);

        // Define mock behavior
        when(mockSubscription.getNumSub()).thenReturn(2L);
        when(mockSubscription.getStartDate()).thenReturn(LocalDate.of(2023, 2, 1));
        when(mockSubscription.getEndDate()).thenReturn(LocalDate.of(2024, 2, 1));
        when(mockSubscription.getPrice()).thenReturn(150.0f);
        when(mockSubscription.getTypeSub()).thenReturn(TypeSubscription.MONTHLY);
    }

    @Test
    void testGetNumSubWithMock() {
        assertEquals(2L, mockSubscription.getNumSub());
    }

    @Test
    void testGetStartDateWithMock() {
        assertEquals(LocalDate.of(2023, 2, 1), mockSubscription.getStartDate());
    }

    @Test
    void testGetEndDateWithMock() {
        assertEquals(LocalDate.of(2024, 2, 1), mockSubscription.getEndDate());
    }

    @Test
    void testGetPriceWithMock() {
        assertEquals(150.0f, mockSubscription.getPrice(), 0.01); // Utilize a tolerance margin
    }

    @Test
    void testGetTypeSubWithMock() {
        assertEquals(TypeSubscription.MONTHLY, mockSubscription.getTypeSub());
    }
}

