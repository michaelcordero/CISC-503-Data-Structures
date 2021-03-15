import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class BinarySearchTree<K,V> implements BinaryTree<K,V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////
    private BinarySearchTreeNode<K,V> root;

    ///////////////////////////////////////////////
    // constructors
    //////////////////////////////////////////////
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

        ////////////////////////////////////////////
        // public api
        ////////////////////////////////////////////
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

    private void innerSwap(BinarySearchTreeNode<K,V> node) {
        if (!node.isLeaf()) {
            BinarySearchTreeNode<K,V> temp = node.left;
            node.left = node.right;
            node.right = temp;
            innerSwap(node.left);
            innerSwap(node.right);
        }
    }

    private int innerHeight(BinaryTreeNode<K,V> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(innerHeight(node.left()), innerHeight(node.right())) + 1;
        }
    }

    private <R> void breadth(BinaryTreeNode<K,V> node, Function<BinaryTreeNode<K,V>,R> function) {
        Queue<BinaryTreeNode<K,V>> queue = new LinkedBlockingQueue<>();
        if (node != null) {
            queue.add(node);
            while (!queue.isEmpty()) {
                node = queue.poll();
                // visit the node, i.e. perform the operation
                if (function != null) {
                    function.apply(node);
                }
                // enqueue the children from left to right
                if (node.left() != null) {
                    queue.add(node.left());
                }
                if (node.right() != null) {
                    queue.add(node.right());
                }
            }
        }
    }

    private <R> void depth(BinaryTreeNode<K,V> node, Function<BinaryTreeNode<K,V>,R> function) {
        Stack<BinaryTreeNode<K,V>> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                // visit the node, i.e. perform the operation
                if (function != null) {
                    function.apply(node);
                }
                // push the children from right to left
                stack.push(node.right());
                stack.push(node.left());
            }
        }
    }

    private <R> void preorder(BinaryTreeNode<K,V> node, Function<BinaryTreeNode<K,V>,R> function) {
        // Root. Left. Right.
        if (node != null) {
            if (function != null) {
                function.apply(node);
            }
            preorder(node.left(), function);
            preorder(node.right(), function);
        }
    }

    private <R> void inorder(BinaryTreeNode<K,V> node, Function<BinaryTreeNode<K,V>,R> function) {
        // Left. Root. Right.
        if (node != null) {
            inorder(node.left(),function);
            if (function != null) {
                function.apply(node);
            }
            inorder(node.right(),function);
        }
    }

    private <R> void postorder(BinaryTreeNode<K,V> node, Function<BinaryTreeNode<K,V>,R> function) {
        // Left. Right. Root.
        if (node != null) {
            postorder(node.left(),function);
            postorder(node.right(),function);
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
        AtomicBoolean found = new AtomicBoolean(false);
        traverser(TraversalType.PREORDER, (node) -> {
            if (node.getValue() == value) found.set(true);
            return null;
        });
        return found.get();
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
        traverser(TraversalType.PREORDER, (node) -> keys.add(node.getKey()));
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        traverser(TraversalType.PREORDER, (node) -> values.add(node.getValue()) );
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> entries = new HashSet<>();
        traverser(TraversalType.PREORDER, (node) -> entries.add(new Map.Entry<K, V>() {
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
        }));
        return entries;
    }

    ///////////////////////////////////////////////
    // End Map contract
    //////////////////////////////////////////////

    ///////////////////////////////////////////////
    // Public API
    //////////////////////////////////////////////

    @Override
    public void balance() {
        List<BinarySearchTreeNode<K,V>> list =  new ArrayList<>();
        traverser(TraversalType.INORDER, (node) -> {
            list.add((BinarySearchTreeNode<K, V>) node);
            return null;
        });
        this.root = null;
        internalBalance(0, list.size() - 1, list);
    }

    /**
     * This method computes the height of the tree.
     * Definition: The height of a node is the number of nodes (excluding the node) on the longest path from the node
     * to a leaf.
     * @return - an int representing the height of the tree.
     */
    @Override
    public int height() {
        return innerHeight(root);
    }

    /**
     * This solution basically makes use of the fact that since we are traversing the Tree anyway, we might as well
     * perform the operation at that time. The textbook's solution wastes time & space. When creating a reusable
     * iterator, it has to traverse the elements, then store the elements in a data structure. Effectively doubling the
     * memory footprint.
     * @param traversalType - Consumer's choice
     * @param function - The function to be executed when a node is visited with respect to order.
     * @param <R> - Function's return type.
     */
    @Override
    public <R> void traverser(TraversalType traversalType, Function<BinaryTreeNode<K, V>,R> function ) {
        // placeholder
        switch (traversalType) {
            case BREADTH: {
                breadth(root,function);
                break;
            }
            case DEPTH:
                depth(root,function);
                break;
            case PREORDER: {
                preorder(root, function);
                break;
            }
            case INORDER:
                inorder(root,function);
                break;
            case POSTORDER:
                postorder(root,function);
                break;
        }
    }

    @Override
    public int nodeCount() {
        AtomicInteger counter = new AtomicInteger();
        Function<BinaryTreeNode<K,V>,Void> node_counter = (node) -> {
            counter.getAndIncrement();
            return null;
        };
        traverser(TraversalType.PREORDER, node_counter);
        return counter.get();
    }

    @Override
    public int leavesCount() {
        AtomicInteger counter = new AtomicInteger();
        traverser(TraversalType.PREORDER, (node) -> {
         if (node.isLeaf()) {
             counter.getAndIncrement();
         }
         return null;
        });
        return counter.get();
    }

    @Override
    public void swapTrees() {
        innerSwap(root);
    }

    @Override
    public int singleParent() {
        AtomicInteger single_parent_count = new AtomicInteger();
        traverser(TraversalType.PREORDER, (node) -> {
            if (node.isSingleParent()) single_parent_count.getAndIncrement();
            return null;
        });
        return single_parent_count.get();
    }

    /**
     * This method computes and prints each element and it's height at each level.
     * If you need proof, uncomment the parent selection and you can draw out the tree and see for yourself.
     */
    @Override
    public void nodeHeight() {
        traverser(TraversalType.BREADTH, (node) -> {
            V element = node.getValue();
//            Optional<BinaryTreeNode<K,V>> parent = Optional.ofNullable(node.parent());
            int height = innerHeight(node);
            System.out.print("Element: " + element + " Height: " + height);
//            parent.ifPresent(kvBinaryTreeNode -> System.out.print(" Parent " + kvBinaryTreeNode.getValue().toString()));
            System.out.println();
            return null;
        });
    }
}
