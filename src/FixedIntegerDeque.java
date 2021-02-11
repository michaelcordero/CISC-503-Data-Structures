/**
 * FixedIntegerDeque uses one shared array to implement a double-ended queue with special properties.
 * The number of elements at max is equal to 20. Only integer type elements may be pushed and popped from it.
 * A deque data structure is used because elements < 100 will be pushed into the back end and elements >= 100, will
 * be pushed into the front end. Two separate methods pop1 (pop_small) & pop2 (pop_large) should be implemented.
 * However, only one push or insert method is required because the inner logic will dictate which end of the array the
 * new element should go into. We can keep track of indices via the pointers, front and back. This code was inspired by
 * Java's ArrayDeque. Because pointers and bit arithmetic is used, indices can be calculated. This means that the
 * performance for this implementation is O(k), or constant time.
 */
public class FixedIntegerDeque {
    ///////////////////////////////////
    // properties
    //////////////////////////////////
    Integer[] deque;
    int back;
    int front;
    int numberOfElements;
    int MAX = 20;

    /////////////////////////////////
    // constructor
    ////////////////////////////////
    public FixedIntegerDeque() {
        deque = new Integer[32];
        numberOfElements = 0;
    }
    ////////////////////////////////
    // private api
    ////////////////////////////////

    /**
     * Push back pushes elements to the right most end of the stack. You & the bits to only take the maximum allowed
     * highest order bit, so as to prevent overflow. In the case of 20 elements, the next power of 2 is 2^5 = 32.
     * 2^4 = 16, would not be enough capacity for 20 elements.
     *
     * @param x - the element to be inserted
     */
    private void push_back(int x) {
        back = (back - 1) & (deque.length - 1);
        deque[back] = x;
        numberOfElements++;
    }

    /**
     * Push front pushes elements to the left, so the left most end is the bottom of the stack.
     *
     * @param x - the element to be inserted
     */
    private void push_front(int x) {
        deque[front] = x;
        front = (front + 1) & (deque.length - 1);
        numberOfElements++;
    }

    ///////////////////////////////////////
    // public operations
    ///////////////////////////////////////

    /**
     * Here the decision is made into what end the element should go into.
     * @param x - the element to be inserted
     */
    public void push(int x) {
        // array full case
        if (numberOfElements == MAX) {
            return;
        }
        if (x >= 100) {
            push_front(x);
        } else {
            push_back(x);
        }
    }

    /**
     * This function pops whatever "small" element is at the top of the stack, or the last inserted element. LIFO.
     * @return Integer element at top of small stack.
     */
    public Integer pop_small() {
        if (numberOfElements == 0) {
            return null;
        }
        Integer to_return = deque[back];
        deque[back] = null;
        back = (back + 1) & (deque.length - 1);
        numberOfElements--;
        return to_return;
    }

    /**
     * This function pops whatever "large" element is at the top of the large stack. LIFO.
     * @return Integer element at the top of the large stack.
     */
    public Integer pop_large() {
        if (numberOfElements == 0) {
            return null;
        }
        int index = (front - 1) & (deque.length - 1);
        Integer to_return = deque[index];
        deque[index] = null;
        front = index;
        numberOfElements--;
        return to_return;
    }
}
