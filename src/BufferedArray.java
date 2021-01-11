public class BufferedArray {
    /////////////////////////////
    // Properties
    ////////////////////////////
    private int numberOfElements = 0;
    private static final int BUFFER_SIZE = 20;
    private final int[] intArray = new int[BUFFER_SIZE];

    //////////////////////////////////////
    // PRIVATE API
    /////////////////////////////////////

    /**
     * The locationOf method visits every value in the array. If it finds a value that matches the target argument,
     * it should return it's location in the array, i.e. index.
     *
     * @param target - the value that is to be searched for.
     * @return -1 if not values found that match otherwise, return the index of the target.
     */
    private int locationOf(int target) {
        for (int i = 0; i < numberOfElements; i++) {
            if (intArray[i] == target) {
                return i;
            }
        }
        return -1;
    }

    ////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////

    /**
     * The insert method dds the value argument to the next available space at the end of the array, and increments
     * the numberOfElements variable. If the buffered array is full, it should just return false without doing anything.
     *
     * @param value - value to be inserted
     * @return true or false if operation was successful
     */
    public Boolean insert(int value) {
        if (numberOfElements == BUFFER_SIZE) {
            return false;
        } else {
            intArray[numberOfElements] = value;
            numberOfElements++;
            return true;
        }
    }

    /**
     * The remove method replaces the target value with the last value in the array, and then decrements the buffer's
     * numberOfElements variable.
     *
     * @param value - the value to be removed
     * @return - true or false if the operation was successful
     */
    public Boolean remove(int value) {
        if (find(value)) {
            int lastElement = intArray[numberOfElements - 1];
            intArray[locationOf(value)] = lastElement;
            numberOfElements--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * The stable remove method removes the target value, shifts the remaining elements up, and decrements the
     * numberOfElements variable.
     *
     * @param target - the value to be removed
     * @return - true or false if the operation was successful
     */
    public Boolean stableRemove(int target) {
        if (find(target)) {
            int location = locationOf(target);
            for (int i = location; i < numberOfElements - 1; i++) {
                int next_location = i + 1;
                intArray[i] = intArray[next_location];
            }
            numberOfElements--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * The find method searches the intArray for the target value.
     *
     * @param target - the value to be searched for.
     * @return - true if the target value was found, otherwise false if not found.
     */
    public Boolean find(int target) {
        return locationOf(target) != -1;
    }

    /**
     * The display method prints the elements in the intArray (buffer), ensuring that commas are placed between each
     * element and returning to the next line.
     */
    public void display() {
        if (numberOfElements == 0) {
            System.out.println();
            return;
        }
        for (int i = 0; i < numberOfElements; i++) {
            String output = i != numberOfElements - 1 ? intArray[i] + "," : intArray[i] + "\n";
            System.out.print(output);
        }
    }
}
