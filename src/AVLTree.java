import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class AVLTree<K,V> extends BinarySearchTree<K,V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////

    ///////////////////////////////////////////////
    // constructors
    //////////////////////////////////////////////
    public AVLTree() {
    }

    ///////////////////////////////////////////////
    // inner node class
    //////////////////////////////////////////////
    static class AVLTreeNode<K,V> implements BinaryTreeNode<K,V> {
        /////////////////////////////////////////////
        // Properties
        ////////////////////////////////////////////
        K key;
        V value;
        protected AVLTreeNode<K,V> parent;
        protected AVLTreeNode<K,V> left;
        protected AVLTreeNode<K,V> right;
        int size;
        ////////////////////////////////////////////
        // constructors
        ////////////////////////////////////////////
        AVLTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 0;
        }

        ////////////////////////////////////////////
        // Methods
        ///////////////////////////////////////////
        @Override
        public AVLTreeNode<K, V> parent() {
            return parent;
        }

        @Override
        public AVLTreeNode<K, V> left() {
            return left;
        }

        @Override
        public AVLTreeNode<K, V> right() {
            return right;
        }

        @Override
        public void setLeft(BinaryTreeNode<K, V> left) {
            this.left = (AVLTreeNode<K, V>) left;
        }

        @Override
        public void setRight(BinaryTreeNode<K, V> right) {
            this.right = (AVLTreeNode<K, V>) right;
        }

        @Override
        public void setParent(BinaryTreeNode<K, V> parent) {
            this.parent = (AVLTreeNode<K, V>) parent;
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

        /**
         * The size method computes the size of a node, which is one plus the sum of it's left and right
         * child nodes. The text suggests modifying the insert and deletes to update the size, but this
         * method proves that is unnecessary.
         * @return - the number of nodes in the subtree rooted at the node plus 1.
         */
        public int size() {
            int leftSize = left != null ? left().size() : 0;
            int rightSize = right != null ? right().size() : 0;
            return 1 + leftSize + rightSize;
        }
    }
    ///////////////////////////////////////////////
    // Private
    //////////////////////////////////////////////
    // O(n) solution = bad! commenting out for demolition.
//    public void innerSmallest(BinaryTreeNode<K,V> node, AtomicInteger counter, AtomicReference<K> keySlot) {
//        if (node != null && keySlot.get() == null ) {
//            // check left
//            innerSmallest(node.left(), counter, keySlot);
//            // check root
//            if (keySlot.get() == null && counter.get() == 0) {
//                keySlot.set(node.getKey());
//            } else {
//                counter.getAndDecrement();
//            }
//            // check right
//            innerSmallest(node.right(), counter, keySlot);
//        }
//    }

    ///////////////////////////////////////////////
    // Map contract
    //////////////////////////////////////////////

    @Override
    public V put(K key, V value) {
        V to_return = super.put(key,value);
        if (size() != 1) {
            balance();
        }
        return to_return;
    }

    @Override
    public V remove(Object key) {
        V to_return = super.remove(key);
        if (size() != 1) {
            balance();
        }
        return to_return;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        super.putAll(m);
        balance();
    }

    ///////////////////////////////////////////////
    // Public API
    //////////////////////////////////////////////
    public K smallest(int kth) {
        AtomicReference<K> keySlot = new AtomicReference<>();
//        AtomicInteger counter = new AtomicInteger(kth - 1);
//        innerSmallest(root, counter, keySlot);
//        System.out.println("AtomicInteger: " +counter.get());
        return keySlot.get();
    }

}
