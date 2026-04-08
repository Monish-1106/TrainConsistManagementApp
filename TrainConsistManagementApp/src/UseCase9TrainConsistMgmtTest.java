import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UseCase9TrainConsistMgmtTest {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // Helper method
    private Map<String, List<Bogie>> groupBogies(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));
    }

    @Test
    void testGrouping_BogiesGroupedByType() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("Sleeper", 70)
        );

        Map<String, List<Bogie>> result = groupBogies(list);

        assertEquals(1, result.size());
        assertTrue(result.containsKey("Sleeper"));
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        List<Bogie> list = Arrays.asList(
                new Bogie("AC Chair", 56),
                new Bogie("AC Chair", 60)
        );

        Map<String, List<Bogie>> result = groupBogies(list);

        assertEquals(2, result.get("AC Chair").size());
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        );

        Map<String, List<Bogie>> result = groupBogies(list);

        assertEquals(3, result.size());
    }

    @Test
    void testGrouping_EmptyBogieList() {
        List<Bogie> list = new ArrayList<>();

        Map<String, List<Bogie>> result = groupBogies(list);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72)
        );

        Map<String, List<Bogie>> result = groupBogies(list);

        assertEquals(1, result.size());
    }

    @Test
    void testGrouping_MapContainsCorrectKeys() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        );

        Map<String, List<Bogie>> result = groupBogies(list);

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    void testGrouping_GroupSizeValidation() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("Sleeper", 70)
        );

        Map<String, List<Bogie>> result = groupBogies(list);

        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));

        List<Bogie> copy = new ArrayList<>(list);

        groupBogies(list);

        assertEquals(copy.size(), list.size());
    }
}