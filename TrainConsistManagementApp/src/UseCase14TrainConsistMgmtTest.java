import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UseCase14TrainConsistMgmtTest {

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    static class PassengerBogie {
        String type;
        int capacity;

        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.type = type;
            this.capacity = capacity;
        }
    }

    @Test
    void testException_ValidCapacityCreation() throws InvalidCapacityException {
        PassengerBogie b = new PassengerBogie("Sleeper", 72);
        assertEquals(72, b.capacity);
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        Exception ex = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("Sleeper", -10);
        });

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        Exception ex = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("AC", 0);
        });

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ExceptionMessageValidation() {
        Exception ex = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("AC", 0);
        });

        assertTrue(ex.getMessage().contains("greater than zero"));
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws InvalidCapacityException {
        PassengerBogie b = new PassengerBogie("First Class", 50);

        assertEquals("First Class", b.type);
        assertEquals(50, b.capacity);
    }

    @Test
    void testException_MultipleValidBogiesCreation() throws InvalidCapacityException {
        PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
        PassengerBogie b2 = new PassengerBogie("AC", 56);

        assertNotNull(b1);
        assertNotNull(b2);
    }
}