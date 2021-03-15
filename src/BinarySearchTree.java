import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class BinarySearchTree<K,V> implements BinaryTree<K,V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////
    BinarySearchTreeNode<K,V> root;

    public BinarySearchTree() {
        this.root = null;
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
        return 1 + amount(node.left()) + amount(node.right());
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
        return values().contains(value);
    }

    @Override
    public V get(Object key) {
        BinarySearchTreeNode<K,V> itr = root;
//        int visits = 1;
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        while (itr != null) {
            int comparison = k.compareTo(itr.getKey());
            if (comparison < 0) {
                itr = itr.left;
//                visits++;
            } else if (comparison > 0) {
                itr = itr.right;
//                visits++;
            } else {
//                System.out.println("# of visits: " + visits);
                return itr.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        BinarySearchTreeNode<K,V> node = new BinarySearchTreeNode<>(key,value);
        if (root == null) {
            root = node;
            return root.getValue();
        }
        boolean inserted = false;
        BinarySearchTreeNode<K,V> itr = root;
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        while (!inserted && itr != null) {
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
    private void breadth(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque, Function<BinaryTreeNode<K,V>,Void> function) {
        Queue<BinaryTreeNode<K,V>> queue = new LinkedBlockingQueue<>();
        if (node != null) {
            queue.add(node);
            while (!queue.isEmpty()) {
                node = queue.poll();
                // visit the node, i.e. perform the operation
                deque.add(node);
                if (function != null) {
                    function.apply(node);
                }
                // enqueue the children from left to right
                queue.add(node.left());
                queue.add(node.right());
            }
        }
    }

    private void depth(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque, Function<BinaryTreeNode<K,V>,Void> function) {
        Stack<BinaryTreeNode<K,V>> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                // visit the node, i.e. perform the operation
                deque.push(node);
                if (function != null) {
                    function.apply(node);
                }
                // push the children from right to left
                stack.push(node.right());
                stack.push(node.left());
            }
        }
    }

    private void preorder(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque, Function<BinaryTreeNode<K,V>,Void> function) {
        // Root. Left. Right.
        if (node != null) {
            deque.add(node);
            if (function != null) {
                function.apply(node);
            }
            preorder(node.left(), deque, function);
            preorder(node.right(), deque, function);
        }
    }

    private void inorder(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque, Function<BinaryTreeNode<K,V>,Void> function) {
        // Left. Root. Right.
        if (node != null) {
            inorder(node.left(),deque, function);
            deque.add(node);
            if (function != null) {
                function.apply(node);
            }
            inorder(node.right(),deque, function);
        }
    }

    private void postorder(BinaryTreeNode<K,V> node, ArrayDeque<BinaryTreeNode<K,V>> deque, Function<BinaryTreeNode<K,V>,Void> function) {
        // Left. Right. Root.
        if (node != null) {
            postorder(node.left(),deque, function);
            postorder(node.right(),deque, function);
            deque.add(node);
            if (function != null) {
                function.apply(node);
            }
        }
    }

    /**
     * Inspired by OO Data Structures using Java 4th edition Dale, Joyce, & Weems. Page 470.
     * @param low - starting point index
     * @param high - end point index
     * @param list - structure to be indexed
     */
    private void internalBalance(int low, int high, List<BinarySearchTreeNode<K,V>> list ) {
        if (low == high) {
            BinarySearchTreeNode<K,V> low_node = list.get(low);
            this.put(low_node.getKey(),low_node.getValue());
        }
        else if ((low + 1) == high) {
            BinarySearchTreeNode<K,V> low_node = list.get(low);
            BinarySearchTreeNode<K,V> high_node = list.get(high);
            this.put(low_node.getKey(),low_node.getValue());
            this.put(high_node.getKey(), high_node.getValue());
        }
        else {
            int mid = (low + high) / 2;
            BinarySearchTreeNode<K,V> mid_node = list.get(mid);
            this.put(mid_node.getKey(), mid_node.getValue());
            internalBalance(low, mid - 1, list);
            internalBalance(mid + 1, high, list);
        }
    }

    @Override
    public void balance() {
        Iterator<BinaryTreeNode<K,V>> itr = traverser(TraversalType.INORDER, null);
        List<BinarySearchTreeNode<K,V>> list =  new ArrayList<>();
        while (itr.hasNext()) {
            list.add((BinarySearchTreeNode<K, V>) itr.next());
        }
        this.root = null;
        internalBalance(0, list.size() - 1, list);
    }

    @Override
    public Iterator<BinaryTreeNode<K,V>> iterator() {
        return traverser(TraversalType.INORDER, null); // natural order is default
    }

    @Override
    public Iterator<BinaryTreeNode<K,V>> traverser(TraversalType traversalType, Function<BinaryTreeNode<K,V>,Void> function ) {
        // placeholder
        ArrayDeque<BinaryTreeNode<K,V>> results = new ArrayDeque<>();
        switch (traversalType) {
            case BREADTH: {
                breadth(root,results, function);
                break;
            }
            case DEPTH:
                depth(root,results, function);
                break;
            case PREORDER: {
                preorder(root, results, function);
                break;
            }
            case INORDER:
                inorder(root,results, function);
                break;
            case POSTORDER:
                postorder(root,results, function);
                break;
        }
        return new BinarySearchTreeIterator<>(results, traversalType);
    }

    @Override
    public int nodeCount(BinaryTreeNode<K, V> root) {
        AtomicInteger counter = new AtomicInteger();
        Function<BinaryTreeNode<K,V>,Void> node_counter = (node) -> {
            counter.getAndIncrement();
            return null;
        };
        preorder(root,new ArrayDeque<>(),node_counter);
        return counter.get();
    }

    @Override
    public int leavesCount(BinaryTreeNode<K, V> root) {
        AtomicInteger counter = new AtomicInteger();
        Function<BinaryTreeNode<K,V>,Void> leaves_counter = (node) -> {
          if (node.isLeaf()) {
              counter.getAndIncrement();
          }
          return null;
        };
        inorder(root, new ArrayDeque<>(), leaves_counter);
        return counter.get();
    }

    @Override
    public void swapTrees(BinaryTreeNode<K, V> node) {
        BinarySearchTreeNode<K,V> itr = (BinarySearchTreeNode<K, V>) node;
        if (!node.isLeaf()) {
            BinarySearchTreeNode<K,V> temp = itr.left;
            itr.left = itr.right;
            itr.right = temp;
            swapTrees(itr.left);
            swapTrees(itr.right);
        }
    }

    @Override
    public int singleParent() {
        Iterator<BinaryTreeNode<K,V>> traverser = traverser(TraversalType.PREORDER, null);
        int single_parent_count = 0;
        while (traverser.hasNext()) {
            BinaryTreeNode<K,V> current = traverser.next();
            if (current.isSingleParent()) {
                single_parent_count++;
            }
        }
        return single_parent_count;
    }
}
