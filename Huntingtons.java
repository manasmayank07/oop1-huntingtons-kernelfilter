public class Huntingtons {

    // Returns the longest consecutive run of CAG in the DNA string
    public static int maxRepeats(String dna) {
        int max = 0;
        int count = 0;
        for (int i = 0; i <= dna.length() - 3; ) {
            if (dna.substring(i, i + 3).equals("CAG")) {
                count++;
                i += 3;
                if (count > max) {
                    max = count;
                }
            } else {
                count = 0;
                i++;
            }
        }
        return max;
    }

    // Classifies the risk based on the number of repeats
    public static String diagnose(int maxRepeats) {
        if (maxRepeats <= 9) return "not human";
        else if (maxRepeats <= 35) return "normal";
        else if (maxRepeats <= 39) return "high risk";
        else return "Huntington's";
    }

    // Main method: reads a DNA string from standard input, analyzes it
    public static void main(String[] args) {
        String dna = StdIn.readAll().replaceAll("\\s+", "").toUpperCase();
        int max = maxRepeats(dna);
        String result = diagnose(max);
        System.out.println("max repeats = " + max);
        System.out.println(result);
    }
}
