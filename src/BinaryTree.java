import java.util.Map;
import java.util.function.Function;

public interface BinaryTree<K,V> extends Map<K, V> {
    ///////////////////////////////////////////////
    // inner node class
    //////////////////////////////////////////////
    interface BinaryTreeNode<K,V> extends Map.Entry<K, V> {
        // dutiful methods
        BinaryTreeNode<K,V> parent();
        BinaryTreeNode<K,V> left();
        BinaryTreeNode<K,V> right();
        // quality of life methods
        default boolean isSingleParent() { return left() != null ^ right() != null; }
        default boolean isParent() {
            return left() != null || right() != null;
        }
        default boolean isChild() {
            return parent() != null;
        }
        default boolean isRoot() {
            return parent() == null;
        }
        default boolean isLeaf() {
            return left() == null && right() == null;
        }
    }
    ///////////////////////////////////////////////
    // traversal types enumeration
    //////////////////////////////////////////////
    enum TraversalType {
        BREADTH,DEPTH,PREORDER,INORDER,POSTORDER
    }
    ///////////////////////////////////////////////
    // definition methods
    ///////////////////////////////////////////////

    // data queries
    void balance();
//    int level(BinaryTreeNode<K,V> node);
    int height();
//    V min();
//    V max();
    // Traversals
<R> void traverser(TraversalType traversalType, Function<BinaryTreeNode<K, V>,R> function);


    //////////////////////////////////////////////
    // CISC-503 methods
    //////////////////////////////////////////////
    int nodeCount();
    int leavesCount();
    void swapTrees();
    int singleParent();
    void nodeHeight();
}
