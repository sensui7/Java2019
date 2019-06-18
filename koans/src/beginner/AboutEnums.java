package beginner;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;


public class AboutEnums {


    enum Colors {
        Red(), Blue, Green, Yellow; // what happens if you add a ; here? Only needed if you're adding code to the enum
        // What happens if you type Red() instead? Does nothing, it's apart of the enum constructor syntax
    }

    @Koan
    public void basicEnums() {
        Colors blue = Colors.Blue;
        assertEquals(blue == Colors.Blue, true);
        assertEquals(blue == Colors.Red, false);
        assertEquals(blue instanceof Colors, true);
    }

    @Koan
    public void basicEnumsAccess() {
        Colors[] colorArray = Colors.values();
        assertEquals(colorArray[2], Colors.Green);
    }

    enum SkatSuits {
        Clubs(12), Spades(11), Hearts(10), Diamonds(9);

        SkatSuits(int v) {
            value = v;
        }

        private int value;
    }

    @Koan
    public void enumsWithAttributes() {
        // value is private but we still can access it. Why?
        // In Java, you can always access private members of a nested type from the containing type "AboutEnums"
        // Try moving the enum outside the AboutEnum class... What do you expect? Error because value has private access in SkatSuits
        // What happens?
        assertEquals(SkatSuits.Clubs.value > SkatSuits.Spades.value, true);
    }

    enum OpticalMedia {
        CD(650), DVD(4300), BluRay(50000);

        OpticalMedia(int c) {
            capacityInMegaBytes = c;
        }

        int capacityInMegaBytes;

        int getCoolnessFactor() {
            return (capacityInMegaBytes - 1000) * 10;
        }
    }

    @Koan
    public void enumsWithMethods() {
        assertEquals(OpticalMedia.CD.getCoolnessFactor(), -3500);
        assertEquals(OpticalMedia.BluRay.getCoolnessFactor(), 490000);
    }
}
