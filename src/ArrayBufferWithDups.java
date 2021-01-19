public class ArrayBufferWithDups extends ArrayBuffer {

    //////////////////////////////////////
    // Constructors
    //////////////////////////////////////
    public ArrayBufferWithDups(int size) {
        super(size);
    }

    /**
     * The findAll function should return an int with the number of elements that have the same value as the target
     * value.
     * @param target - the value to be searched for
     * @return - number of target value occurrences within the ArrayBuffer
     */
    public int findAll(int target) {
        // naive solution O(n), it would probably be better to make this ArrayBuffer be backed by a heap, but that is
        // not within the scope of this assignment.
        int occurrences = 0;
        int itr = 0;
        while (itr < numberOfElements) {
            if (intArray[itr] == target) {
                occurrences++;
            }
            itr++;
        }
        return occurrences;
    }

    /**
     * The fastRemoveAll function should remove all copies of the target value. I assume in the same manner as it's
     * similarly named function.
     * @param target - target value to be removed
     * @return - an int with the number of values that were actually removed
     */
    public int fastRemoveAll(int target) {
        int occurrences = findAll(target);
        int itr = occurrences;
        while (itr > 0) {
            super.fastRemove(target);
            itr--;
        }
        return occurrences;
    }


    /**
     * The stableRemoveAll function should also remove all copies of the target value. I assume in the same manner as
     * it's similarly named function.
     * @param target - value to be removed
     * @return - number of occurrences of the target value to be removed.
     */
    public int stableRemoveAll(int target) {
        int occurrences = findAll(target);
        int itr = occurrences;
        while (itr > 0) {
            super.stableRemove(target);
            itr--;
        }
        return occurrences;
    }


}
