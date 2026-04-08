import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementApp {
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // Helper method for filtering
    private List<Bogie> filterBogies(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 80),
                new Bogie("B", 60)
        );

        List<Bogie> result = filterBogies(list, 70);

        assertEquals(1, result.size());
        assertEquals(80, result.get(0).capacity);
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 70)
        );

        List<Bogie> result = filterBogies(list, 70);

        assertTrue(result.isEmpty()); // Equal should NOT be included
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 50)
        );

        List<Bogie> result = filterBogies(list, 70);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 80),
                new Bogie("B", 90),
                new Bogie("C", 40)
        );

        List<Bogie> result = filterBogies(list, 70);

        assertEquals(2, result.size());
    }

    @Test
    void testFilter_NoBogiesMatching() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 30),
                new Bogie("B", 40)
        );

        List<Bogie> result = filterBogies(list, 70);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 80),
                new Bogie("B", 90)
        );

        List<Bogie> result = filterBogies(list, 70);

        assertEquals(list.size(), result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        List<Bogie> list = new ArrayList<>();

        List<Bogie> result = filterBogies(list, 70);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("A", 80));

        List<Bogie> copy = new ArrayList<>(list);

        filterBogies(list, 70);

        assertEquals(copy.size(), list.size()); // original unchanged
    }
}