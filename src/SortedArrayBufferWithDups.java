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
            // let's check for duplicates
            int location = locationOf(value);
            if (location != -1) {
                // we have a duplicate, shift next element up, to make room
                intArray[location + 2] = intArray[location + 1];
                // now store value
                intArray[location + 1] = value;
                numberOfElements++;
                return true;
            } else {
                // no duplicates so follow normal logic
                if (value > intArray[numberOfElements - 1 ]) {
                    intArray[numberOfElements] = value;
                    numberOfElements++;
                    return true;
                } else if (value < intArray[numberOfElements - 1]) {
                    // shift up
                    intArray[numberOfElements] = intArray[numberOfElements - 1];
                    intArray[numberOfElements - 1] = value;
                    numberOfElements++;
                    return true;
                }
            }
        }
        // Array Buffer is full
        return false;
    }
}
