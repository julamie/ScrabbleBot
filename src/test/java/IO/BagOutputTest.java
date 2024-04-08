package IO;

import BoardStructure.Bag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagOutputTest {

    @Test
    void printBagContents() {
        BagOutput.printBagContents(new Bag());
    }
}