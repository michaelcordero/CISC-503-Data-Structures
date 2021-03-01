import java.util.Iterator;

/**
 * The purpose of this class is to act as a container for any type of expressions that need to be evaluated in the LIFO
 * order. Under the hood it is using a singly linked-list implementation of the stack ADT.
 * @param <T>
 */
public class ExpressionStack<T> implements Iterable<T> {

    /////////////////////////////////////////////////////////
    // Properties
    /////////////////////////////////////////////////////////
    private ExpressionNode<T> top;

    @Override
    public Iterator<T> iterator() {
        return new ExpressionStackIterator<>(this);
    }

    /**
     * Because this is a linked-list implementation, I wanted a quick solution to be able to print out all the values
     * contained in the stack.
     * @param <T>
     */
    static class ExpressionStackIterator<T> implements Iterator<T> {
        ExpressionNode<T> current;

        public ExpressionStackIterator(ExpressionStack<T> stack) {
            current = stack.top;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.value;
            current = current.predecessor;
            return data;
        }
    }


    /**
     * The purpose of this class is to serve the ExpressionStack<T> as it's inner node class. This achieves
     * encapsulation, so that consumers of the ExpressionStack<T> do not have to worry about internal operations.
     * @param <E>
     */
    protected static class ExpressionNode<E> {
        /////////////////////////////////////////////////
        // Properties
        ////////////////////////////////////////////////
        private final E value;
        private ExpressionNode<E> predecessor;

        /////////////////////////////////////////////////
        // Constructors
        ////////////////////////////////////////////////
        private ExpressionNode(E info) {
            value = info;
        }

        private ExpressionNode(E info, ExpressionNode<E> predecessor) {
            value = info;
            this.predecessor = predecessor;
        }
    }

    //////////////////////////////////////////////////////////////
    // Constructors
    /////////////////////////////////////////////////////////////
    public ExpressionStack() {
        top = null;
    }

    public ExpressionStack(T value) {
        top = new ExpressionNode<>(value);
    }

    ////////////////////////////////////////////////////////////
    // Public API
    ///////////////////////////////////////////////////////////

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ExpressionStackIterator<T> itr = (ExpressionStackIterator<T>) this.iterator();
        while (itr.hasNext()) {
            builder.append(itr.next());
            if (itr.hasNext()) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public void push(T value) {
        ExpressionNode<T> element = new ExpressionNode<>(value);
        element.predecessor = top;
        top = element;
    }

    public T pop() {
        ExpressionNode<T> to_return = top;
        top = to_return.predecessor;
        return to_return.value;
    }

    public T peek() {
        ExpressionNode<T> to_return = top;
        return to_return.value;
    }

    public boolean empty() {
        return top == null;
    }

}
