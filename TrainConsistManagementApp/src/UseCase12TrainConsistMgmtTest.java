import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UseCase12TrainConsistMgmtTest {

    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    // Helper method
    private boolean isSafe(List<GoodsBogie> list) {
        return list.stream()
                .allMatch(b ->
                        !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum")
                );
    }

    @Test
    void testSafety_AllBogiesValid() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Open", "Coal"),
                new GoodsBogie("Box", "Grain")
        );

        assertTrue(isSafe(list));
    }

    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Cylindrical", "Coal")
        );

        assertFalse(isSafe(list));
    }

    @Test
    void testSafety_NonCylindricalBogiesAllowed() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Open", "Coal"),
                new GoodsBogie("Box", "Grain")
        );

        assertTrue(isSafe(list));
    }

    @Test
    void testSafety_MixedBogiesWithViolation() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Cylindrical", "Coal") // invalid
        );

        assertFalse(isSafe(list));
    }

    @Test
    void testSafety_EmptyBogieList() {
        List<GoodsBogie> list = new ArrayList<>();

        assertTrue(isSafe(list)); // no violation → safe
    }
}