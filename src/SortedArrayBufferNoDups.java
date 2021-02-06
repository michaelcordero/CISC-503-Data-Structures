public class SortedArrayBufferNoDups extends ArrayBufferNoDups {
    public SortedArrayBufferNoDups(int size) {
        super(size);
    }

    /**
     * This method inserts an integer value into it's appropriate position. Duplicates are not allowed.
     * The guarding clause is there to protect against duplicates and for when the buffer is already full.
     * This problem of inserting into the correct position can be solved in 3 simple steps.
     * 1. Find the destination index.
     * 2. Shift the existing elements down (or right) to make room for the new incoming element.
     * 3. Finally, insert the new element.
     * @param value - value to be inserted
     * @return true or false if operation occurred.
     */
    @Override
    public Boolean insert(int value) {
        // Guard clause against insertions when buffer is full or attempted duplicate entry.
        if (numberOfElements == intArray.length || find(value)) {
            return false;
        }
        // Find destination index
        int destination = 0;
        for ( ; destination <= numberOfElements - 1; destination++) {
            if (value < intArray[destination]) {
                break;
            }
        }
        // Shift elements down
        for (int i = numberOfElements - 1; i >= destination; i--) {
            intArray[i + 1] = intArray[i];
        }
        // Finally insert element
        intArray[destination] = value;
        numberOfElements++;
        return true;
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
            } else if (intArray[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
