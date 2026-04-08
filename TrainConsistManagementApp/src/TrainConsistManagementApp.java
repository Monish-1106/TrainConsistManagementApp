import java.util.*;
import java.util.stream.*;

class UseCase12TrainConsistMgmt {

    // Goods Bogie class
    static class GoodsBogie {
        String type;   // Cylindrical, Open, Box
        String cargo;  // Petroleum, Coal, Grain

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return type + " -> " + cargo;
        }
    }

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("UC12 - Safety Compliance Check for Goods Bogies");
        System.out.println("=========================================\n");

        // Create goods bogie list
        List<GoodsBogie> goodsBogies = new ArrayList<>();

        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal")); // ❌ invalid

        // Display bogies
        System.out.println("Goods Bogies in Train:");
        goodsBogies.forEach(System.out::println);

        // 🔥 SAFETY RULE:
        // Cylindrical → ONLY Petroleum allowed
        boolean isSafe = goodsBogies.stream()
                .allMatch(b ->
                        !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum")
                );

        // Output
        System.out.println("\nSafety Compliance Status: " + isSafe);

        if (isSafe) {
            System.out.println("Train formation is SAFE.");
        } else {
            System.out.println("Train formation is NOT SAFE.");
        }

        System.out.println("\nUC12 safety validation completed...");
    }
}