public class SortedArrayBufferWithDups extends ArrayBufferWithDups{
    public SortedArrayBufferWithDups(int size) {
        super(size);
    }

    @Override
    public Boolean insert(int value) {
        // Empty Array Buffer Case. Early Insert, no sorted insert required.
        if (numberOfElements == 0) {
            intArray[0] = value;
            numberOfElements++;
            return true;
        }
        // Array Buffer not full case
        if (numberOfElements != intArray.length) {
            if (value > intArray[numberOfElements - 1 ]) {
                intArray[numberOfElements] = value;
                numberOfElements++;
                return true;
            } else if (value < intArray[numberOfElements - 1]) {
                // find destination index
                int destination = 0;
                for (int i = 0; i < numberOfElements - 1; i++) {
                    if (value > intArray[i]) {
                        destination = i + 1;
                    }
                }
                // shift elements down
                for (int i = numberOfElements - 1; i >= destination; i--) {
                    intArray[i+1] = intArray[i];
                }
                // finally insert element
                intArray[destination] = value;
                numberOfElements++;
                return true;
            }
        }
        // Array Buffer is full
        return false;
    }
}
