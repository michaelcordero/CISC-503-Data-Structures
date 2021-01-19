// The ArrayBufferNoDups class should look like the ArrayBuffer class, only the insert operation has to change.
public class ArrayBufferNoDups extends ArrayBuffer {

    //////////////////////////////////////
    // Constructors
    //////////////////////////////////////
    public ArrayBufferNoDups(int size) {
        super(size);
    }

    /**
     * This method first checks if the value to be inserted is a duplicate, if not then the value will be inserted,
     * and true will be returned if not, then the value will not be inserted and false will be returned.
     * @param value - value to be inserted
     * @return - whether or not the operation succeeded successfully.
     */
    @Override
    public Boolean insert(int value) {
        if (!find(value)) {
            super.insert(value);
            return true;
        } else {
            return false;
        }
    }
}
