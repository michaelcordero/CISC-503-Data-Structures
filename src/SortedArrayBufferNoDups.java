public class SortedArrayBufferNoDups extends ArrayBufferNoDups {
    public SortedArrayBufferNoDups(int size) {
        super(size);
    }

    /**
     * This method ensures that integer elements entered into the SortedArrayBuffer are inserted into their ordered
     * location. It does not allow duplicates.
     * @param value - value to be inserted
     * @return - true if insertion is successful, false if not.
     */
    @Override
    public Boolean insert(int value) {
        // No Duplicates case. Early termination.
        if (find(value)) {
            return false;
        }
        // Empty Array Buffer Case. Early Insert, no sorted insert required.
        if (numberOfElements == 0) {
            intArray[0] = value;
            numberOfElements++;
            return true;
        }
        // Array Buffer size case
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

    /**
     * This method overrides the locationOf() method which is called by the stableRemove() parent method, in order to
     * early terminate when the iteration knows its reached a value greater then the target value. This means that the
     * target value is not in the buffer.
     * @param target - the value that is to be searched for.
     * @return location of target value if found, if not, -1.
     */
    @Override
    protected int locationOf(int target) {
        for (int i = 0; i < numberOfElements; i++) {
            if (intArray[i] > target) {
                // early termination optimization
                return -1;
            }
            else if (intArray[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
