public class SortedArrayBufferWithDups extends ArrayBufferWithDups{
    public SortedArrayBufferWithDups(int size) {
        super(size);
    }

    /**
     * This method inserts an integer value into it's ordered position. Duplicates are inserted consecutively.
     * The guarding clause is there to protect against insertions when the buffer is full.
     * This problem of inserting a new element into the correct position can be broken down into 3 simple steps.
     * 1. Find the destination index.
     * 2. Shift the elements down (or right) to make room for the new incoming element.
     * 3. Finally, insert the new element.
     * @param value - value to be inserted
     * @return true or false if operation occurred.
     */
    @Override
    public Boolean insert(int value) {
        // Guard clause against insertions when buffer is full
        if (numberOfElements == intArray.length) {
            return false;
        }
        // Find destination index
        int destination = 0;
        for (; destination <= numberOfElements - 1; destination++) {
            if (value < intArray[destination]) {
                break;
            }
        }
        // Shift elements down
        for (int i = numberOfElements -1; i >= destination ; i--) {
            intArray[i+1] = intArray[i];
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
