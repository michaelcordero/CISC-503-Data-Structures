public class SortedArrayBufferWithDups extends ArrayBufferWithDups{
    public SortedArrayBufferWithDups(int size) {
        super(size);
    }

    /**
     * This method inserts an integer value, whether it's a duplicate or not, into it's appropriate position.
     * The duplicates are enabled by the <= or >= operators. Whenever an element to be inserted is less than what's
     * already in the list, special logic must take care to shift elements down to make room for the new element.
     * @param value - value to be inserted
     * @return true or false if operation occurred.
     */
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
            if (value >= intArray[numberOfElements - 1 ]) {
                intArray[numberOfElements] = value;
                numberOfElements++;
                return true;
            } else if (value <= intArray[numberOfElements - 1]) {
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

    /**
     * This method utilizes existing class methods to find the number of occurrences of the passed in target, and location
     * of the first occurrence. Then, it uses that information to know how much to offset, when shifting the elements up,
     * that are not duplicates of the target value to be removed, or in this case overwritten. The offset is represented
     * by the variable j.
     * @param target - value to be removed
     * @return occurrences - number of times the target value appears in the list, i.e. duplicates.
     */
    @Override
    public int stableRemoveAll(int target) {
        int occurrences = findAll(target);
        int start = locationOf(target);
        // shift elements up that are not an occurrence of the target
        if (start != -1) {
            for (int i = start, j = i+occurrences; i < numberOfElements -1; i++, j++) {
                if (j < numberOfElements ) {
                    intArray[i] = intArray[j];
                }
            }
            numberOfElements -= occurrences;
        }
        return occurrences;
    }
}
