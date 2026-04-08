import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

class UseCase13TrainConsistMgmtTest {

    static class Bogie {
        String type;
        int capacity;

        Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }
    }

    // Loop method
    private List<Bogie> loopFilter(List<Bogie> list) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : list) {
            if (b.capacity > 60) {
                result.add(b);
            }
        }
        return result;
    }

    // Stream method
    private List<Bogie> streamFilter(List<Bogie> list) {
        return list.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
    }

    @Test
    void testLoopFilteringLogic() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 70),
                new Bogie("B", 50)
        );

        List<Bogie> result = loopFilter(list);

        assertEquals(1, result.size());
    }

    @Test
    void testStreamFilteringLogic() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 70),
                new Bogie("B", 50)
        );

        List<Bogie> result = streamFilter(list);

        assertEquals(1, result.size());
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 70),
                new Bogie("B", 80),
                new Bogie("C", 40)
        );

        List<Bogie> loopResult = loopFilter(list);
        List<Bogie> streamResult = streamFilter(list);

        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        List<Bogie> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Bogie("A", i));
        }

        long start = System.nanoTime();
        loopFilter(list);
        long end = System.nanoTime();

        long time = end - start;

        assertTrue(time > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<Bogie> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(new Bogie("A", i));
        }

        List<Bogie> result = streamFilter(list);

        assertNotNull(result);
    }
}