import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTree<K,V> implements BinaryTree<K,V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////
    BinarySearchTreeNode<K,V> root;
    Comparator<? super K> comparator;

    public BinarySearchTree() {
        this.root = null;
        this.comparator = null;
    }

    public BinarySearchTree(K key, V value) {
        this.root = new BinarySearchTreeNode<>(key, value);
        this.comparator = null;
    }

    public BinarySearchTree(K key, V value, Comparator<? super K> comparator) {
        this.root = new BinarySearchTreeNode<>(key, value);
        this.comparator = comparator;
    }

    ///////////////////////////////////////////////
    // inner node class
    //////////////////////////////////////////////
    static class BinarySearchTreeNode<K,V> implements BinaryTreeNode<K,V> {
        /////////////////////////////////////////////
        // Properties
        ////////////////////////////////////////////
        K key;
        V value;
        // ain't nobody got time for that setter() ish! haha
        protected BinarySearchTreeNode<K,V> parent;
        protected BinarySearchTreeNode<K,V> left;
        protected BinarySearchTreeNode<K,V> right;

        ////////////////////////////////////////////
        // constructors
        ////////////////////////////////////////////
        BinarySearchTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            // outer class's put() takes care of linking the new node.
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        @Override
        public BinaryTreeNode<K, V> parent() {
            return parent;
        }

        @Override
        public BinaryTreeNode<K, V> left() {
            return left;
        }

        @Override
        public BinaryTreeNode<K, V> right() {
            return right;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return value;
        }
    }

    ///////////////////////////////////////////////
    // Private
    //////////////////////////////////////////////
    private int amount(BinaryTreeNode<K,V> node) {
        if (node == null) {
            return 0;
        }
        return amount(node.left()) + amount(node.right()) + 1;
    }

    ///////////////////////////////////////////////
    // Map contract
    //////////////////////////////////////////////
    @Override
    public int size() {
        return amount(root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean found = false;
        BinarySearchTreeNode<K,V> itr = root;
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        while (!found && itr != null) {
            int comparison = k.compareTo(itr.getKey());
            if (comparison == 0) {
                found = true;
            }
            if (comparison < 0) {
                itr = itr.left;
            } else if (comparison > 0) {
                itr = itr.right;
            }
        }
        return found;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean found = false;
        BinarySearchTreeNode<K,V> itr = root;
        @SuppressWarnings("unchecked")
        Comparable<? super V> v = (Comparable<? super V>) value;
        while (!found && itr != null) {
            int comparison = v.compareTo(itr.getValue());
            if (comparison == 0) {
                found = true;
            }
            if (comparison < 0) {
                itr = itr.left;
            } else if (comparison > 0) {
                itr = itr.right;
            }
        }
        return found;
    }

    @Override
    public V get(Object key) {
        BinarySearchTreeNode<K,V> itr = root;
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        while (itr != null) {
            int comparison = k.compareTo(itr.getKey());
            if (comparison < 0) {
                itr = itr.left;
            } else if (comparison > 0) {
                itr = itr.right;
            } else {
                return itr.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        BinarySearchTreeNode<K,V> node = new BinarySearchTreeNode<>(key,value);
        boolean inserted = false;
        BinarySearchTreeNode<K,V> itr = root;
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        while (!inserted) {
            int comparison = k.compareTo(itr.getKey());
            if (comparison < 0) {
                if (itr.left() == null) {
                    itr.left = node;
                    node.parent = itr;
                    inserted = true;
                } else {
                    itr = itr.left;
                }
            } else if( comparison > 0) {
                if (itr.right() == null) {
                    itr.right = node;
                    node.parent = itr;
                    inserted = true;
                } else {
                    itr = itr.right;
                }
            } else {
                // duplicate value. the text says enter it to the left, but not sure if want that.
                // have to change inserted to true to avoid infinite loop.
                inserted = true;
            }
        }
        return node.getValue();
    }

    @Override
    public V remove(Object key) {
        boolean found = false;
        BinarySearchTreeNode<K,V> itr = root;
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        while (!found && itr != null) {
            int comparison = k.compareTo(itr.getKey());
            if (comparison < 0) {
                itr = itr.left;
            } else if (comparison > 0) {
                itr = itr.right;
            }
            // key object found
            else {
                found = true;
                BinarySearchTreeNode<K,V> parent = itr.parent;
                BinarySearchTreeNode<K,V> left = itr.left;
                BinarySearchTreeNode<K,V> right = itr.right;
                // re-attaching the child nodes to the node to be removed's parent.
                if (right != null) {
                    if (itr == parent.left) {
                        parent.left = right;
                    } else {
                        parent.right = right;
                    }
                } else {
                    if (itr == parent.right) {
                        parent.right = left;
                    } else {
                        parent.left = left;
                    }
                }
            }
        }
        return itr != null ? itr.getValue() : null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry: m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (BinaryTreeNode<K, V> node : this) {
            keys.add(node.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (BinaryTreeNode<K, V> node : this) {
            values.add(node.getValue());
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> entries = new HashSet<>();
        for (BinaryTreeNode<K,V> node: this) {
            entries.add(new Entry<K, V>() {
                @Override
                public K getKey() {
                    return node.getKey();
                }
                @Override
                public V getValue() {
                    return node.getValue();
                }
                @Override
                public V setValue(V value) {
                    return node.setValue(value);
                }
            });
        }
        return entries;
    }

    ///////////////////////////////////////////////
    // iterator contract
    //////////////////////////////////////////////

    static class BinarySearchTreeIterator<E> implements Iterator<E> {
        //////////////////////////////////////
        // properties
        ////////////////////////////////////
        ArrayDeque<E> items;    // array deque can act as a queue or stack, depending on how you add the elements?
        TraversalType type;
        //////////////////////////////////////
        // constructor
        ////////////////////////////////////
        BinarySearchTreeIterator(ArrayDeque<E> items, TraversalType type) {
            this.items = items;
            this.type = type;
        }
        //////////////////////////////////////
        // contract methods
        ////////////////////////////////////
        @Override
        public boolean hasNext() {
            return !items.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("Collection is empty!");
            }
            E to_return;
            switch (type) {
                case BREADTH: case PREORDER:
                case INORDER: case POSTORDER:
                    to_return = items.poll();
                    break;
                case DEPTH:
                    to_return = items.pop();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            return to_return;
        }
    }

    /**
     * These methods follows suit of the textbook pg. 454-455 and adds the values to the deque in the traversal type.
     * Adding the whole node --key & value-- to the deque for re-usability.
     * @param node - current node to be processed. invoker of this method should start at the root node.
     * @param deque - container structure to store the pre-iterated elements.
     */
    private void breadth(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque) {
        Queue<BinaryTreeNode<K,V>> queue = new LinkedBlockingQueue<>();
        if (node != null) {
            queue.add(node);
            while (!queue.isEmpty()) {
                node = queue.poll();
                // visit the node, i.e. perform the operation
                deque.add(node);
                // enqueue the children from left to right
                queue.add(node.left());
                queue.add(node.right());
            }
        }
    }

    private void depth(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque) {
        Stack<BinaryTreeNode<K,V>> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                // visit the node, i.e. perform the operation
                deque.push(node);
                // push the children from right to left
                stack.push(node.right());
                stack.push(node.left());
            }
        }
    }

    private void preorder(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque) {
        // Root. Left. Right.
        if (node != null) {
            deque.add(node);
            preorder(node.left(), deque);
            preorder(node.right(), deque);
        }
    }

    private void inorder(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque) {
        // Left. Root. Right.
        if (node != null) {
            inorder(node.left(),deque);
            deque.add(node);
            inorder(node.right(),deque);
        }
    }

    private void postorder(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque) {
        // Left. Right. Root.
        if (node != null) {
            postorder(node.left(),deque);
            postorder(node.right(),deque);
            deque.add(node);
        }
    }

    @Override
    public Iterator<BinaryTreeNode<K,V>> iterator() {
        return traverser(TraversalType.INORDER); // natural order is default
    }


    @Override
    public Iterator<BinaryTreeNode<K,V>> traverser(TraversalType traversalType) {
        // placeholder
        ArrayDeque<BinaryTreeNode<K,V>> results = new ArrayDeque<>();
        switch (traversalType) {
            case BREADTH: {
                breadth(root,results);
                break;
            }
            case DEPTH:
                depth(root,results);
                break;
            case PREORDER: {
                preorder(root, results);
                break;
            }
            case INORDER:
                inorder(root,results);
                break;
            case POSTORDER:
                postorder(root,results);
                break;
        }
        return new BinarySearchTreeIterator<>(results, traversalType);
    }
}
