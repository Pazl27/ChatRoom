package common;

public class Colors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    private static final String BLACK = "\033[0;30m";   // BLACK
    private static final String RED = "\033[0;31m";     // RED
    private static final String GREEN = "\033[0;32m";   // GREEN
    private static final String YELLOW = "\033[0;33m";  // YELLOW
    private static final String BLUE = "\033[0;34m";    // BLUE
    private static final String PURPLE = "\033[0;35m";  // PURPLE
    private static final String CYAN = "\033[0;36m";    // CYAN


    private static final String[] colorsToChoose = {BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN};

    public static String getRandomColor(){
        return colorsToChoose[(int)(Math.random() * colorsToChoose.length)];
    }
}
