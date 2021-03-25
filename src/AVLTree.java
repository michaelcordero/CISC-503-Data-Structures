import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AVLTree<K,V> extends BinarySearchTree<K,V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////

    //BinaryTreeNode<K,V> root;

    ///////////////////////////////////////////////
    // constructors
    //////////////////////////////////////////////
    public AVLTree() {
        //this.root = super.root;
    }

    ///////////////////////////////////////////////
    // inner node class
    //////////////////////////////////////////////
//    private static class AVLTreeNode<K,V> implements BinaryTreeNode<K,V> {
//        /////////////////////////////////////////////
//        // Properties
//        ////////////////////////////////////////////
//        K key;
//        V value;
//        protected AVLTreeNode<K,V> parent;
//        protected AVLTreeNode<K,V> left;
//        protected AVLTreeNode<K,V> right;
//        ////////////////////////////////////////////
//        // constructors
//        ////////////////////////////////////////////
//        AVLTreeNode(K key, V value) {
//            this.key = key;
//            this.value = value;
//        }
//
//        ////////////////////////////////////////////
//        // Methods
//        ///////////////////////////////////////////
//        @Override
//        public AVLTreeNode<K, V> parent() {
//            return parent;
//        }
//
//        @Override
//        public AVLTreeNode<K, V> left() {
//            return left;
//        }
//
//        @Override
//        public AVLTreeNode<K, V> right() {
//            return right;
//        }
//
//        @Override
//        public void setLeft(BinaryTreeNode<K, V> left) {
//            this.left = (AVLTreeNode<K, V>) left;
//        }
//
//        @Override
//        public void setRight(BinaryTreeNode<K, V> right) {
//            this.right = (AVLTreeNode<K, V>) right;
//        }
//
//        @Override
//        public void setParent(BinaryTreeNode<K, V> parent) {
//            this.parent = (AVLTreeNode<K, V>) parent;
//        }
//
//        @Override
//        public K getKey() {
//            return key;
//        }
//
//        @Override
//        public V getValue() {
//            return value;
//        }
//
//        @Override
//        public V setValue(V value) {
//            return value;
//        }
//    }
    ///////////////////////////////////////////////
    // Private
    //////////////////////////////////////////////
    public void innerSmallest(BinaryTreeNode<K,V> node, AtomicInteger counter, AtomicReference<K> keySlot) {
        if (node != null && keySlot.get() == null ) {
            // check left
            innerSmallest(node.left(), counter, keySlot);
            // check root
            if (keySlot.get() == null && counter.get() == 0) {
                keySlot.set(node.getKey());
            } else {
                counter.getAndDecrement();
            }
            // check right
            innerSmallest(node.right(), counter, keySlot);
        }
    }

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
        AtomicInteger counter = new AtomicInteger(kth - 1);
        innerSmallest(root, counter, keySlot);
        System.out.println("AtomicInteger: " +counter.get());
        return keySlot.get();
    }

}