package Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author Trent Doiron
 * @version 1.0
 * @userid tdoiron6
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class AVLTree<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVLTree() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVLTree(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        for (T it : data) {
            if (it == null) {
                throw new IllegalArgumentException("Data cannot be equal to null.");
            }
            add(it);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        root = rAdd(root, data);
    }

    /**
     * Helper method for add() to traverse the tree which also
     * utilizes pointer reinforcement to update and rebalance
     * upper nodes afer adding node.
     *
     * @param curr current node travsersing through the tree
     * @param data data to add
     * @return new subree after adding and rebalancing
     */
    private AVLNode<T> rAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            ++size;
            return new AVLNode<T>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        update(curr);
        curr = rebalance(curr);
        return curr;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        if (size == 0) {
            throw new NoSuchElementException("No elements in the list to remove.");
        }

        AVLNode<T> dummy = new AVLNode<T>(null);
        root = rRemove(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Helper method for remove which traverses the tree and rebalances nodes.
     *
     * @param curr current node travsersing through the tree
     * @param data data to remove
     * @param dummy dummy node used to store value of deleted data
     * @return new subree after removing and rebalancing
     * @throws java.util.NoSuchElementException if the data is not found
     */
    private AVLNode<T> rRemove(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data " + data + " is not in the tree");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            --size;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getRight() != null && curr.getLeft() == null) {
                return curr.getRight();
            } else {
                AVLNode<T> dummy2 = new AVLNode<>(null);
                curr.setLeft(removePredecessor(curr.getLeft(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        update(curr);
        curr = rebalance(curr);
        return curr;
    }

    /**
     * Finds and removes the predicessor node from the tree provided the starting node, curr.
     *
     * @param curr current node travsersing through the tree
     * @param dummy stores data removed from the tree
     * @return new subree after removing and rebalancing
     */
    private AVLNode<T> removePredecessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr == null) {
            return null;
        } else if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy));
            update(curr);
            return rebalance(curr);
        }
    }

    /**
     * Rebalnces the tree, performing all the neccessary rotations.
     * Assumes that the node has already had its height and balance factor updated.
     *
     * @param node node to be rebalnced
     * @return new balanced tree
     */
    private AVLNode<T> rebalance(AVLNode<T> node) {
        if (node.getBalanceFactor() > 1) { // left heavy 
            if (node.getLeft().getBalanceFactor() >= 0) { // if left node is also heavy then line 
                return rightRotate(node);
            } else { // otherwise double rotation 
                node.setLeft(leftRotate(node.getLeft()));
                return rightRotate(node);
            }
        }
        if (node.getBalanceFactor() < -1) { // right heavy 
            if (node.getRight().getBalanceFactor() <= 0) {
                return leftRotate(node);
            } else {
                node.setRight(rightRotate(node.getRight()));
                return leftRotate(node);
            }
        }

        return node;
    }

    /**
     * Performs a right rotation on the specified node.
     *
     * @param b root of subtree to perform right rotation on
     * @return new subtree after rotation
     */
    private AVLNode<T> rightRotate(AVLNode<T> b) {
        AVLNode<T> a = b.getLeft();

        b.setLeft(a.getRight());
        a.setRight(b);

        update(b); // order important 
        update(a);

        return a;
    }

    /**
     * Performs a left rotation on the specified node.
     *
     * @param a root of subtree to perform left rotation on
     * @return new subtree after rotation
     */
    private AVLNode<T> leftRotate(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();

        a.setRight(b.getLeft());
        b.setLeft(a);

        update(a); // order important 
        update(b);

        return b;
    }

    /**
     * Updates the height and balance factor of specified node.
     *
     * @param node node to update
     */
    private void update(AVLNode<T> node) {
        int leftHeight = (node.getLeft() != null) ? node.getLeft().getHeight() : -1;
        int rightHeight = (node.getRight() != null) ? node.getRight().getHeight() : -1;
        int maxHeight = (leftHeight > rightHeight) ? leftHeight : rightHeight;
        node.setHeight(1 + maxHeight);
        node.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Returns the height of the specified node. If the node specified is null, then returns -1.
     *
     * @param node node to retrieve the height of
     * @return height of specified node
     */
    private int height(AVLNode<T> node) {
        return (node == null) ? -1 : node.getHeight();
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        return rGet(root, data);
    }

    /**
     * Helper method for get() which recursively traverses the tree.
     *
     * @param curr current node travsersing through the tree
     * @param data data to search for in the tree
     * @return data found in the tree
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private T rGet(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data " + data + " is not in the tree");
        } else if (data.compareTo(curr.getData()) < 0) {
            return rGet(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return rGet(curr.getRight(), data);
        } else {
            return curr.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        return rContains(root, data);
    }

    /**
     * Helper method for contains() which recursively traverses the tree.
     *
     * @param curr current node travsersing through the tree
     * @param data data to search for in the tree
     * @return whether or not the data was found in the tree
     */
    private boolean rContains(AVLNode<T> curr, T data) {
        if (curr == null) {
            return false;
        } else if (data.compareTo(curr.getData()) < 0) {
            return rContains(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return rContains(curr.getRight(), data);
        } else {
            return true;
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return height(root);
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * The predecessor is the largest node that is smaller than the current data.
     *
     * Should be recursive.
     *
     * This method should retrieve (but not remove) the predecessor of the data
     * passed in. There are 2 cases to consider:
     * 1: The left subtree is non-empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 2: The left subtree is empty. In this case, the predecessor is the lowest
     * ancestor of the node containing data whose right child is also
     * an ancestor of data.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *     76
     *   /    \
     * 34      90
     *  \    /
     *  40  81
     * predecessor(76) should return 40
     * predecessor(81) should return 76
     *
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }

        return rPredecessor(root, null, data);
    }

    /**
     * Helper function for the predecessor() method which traverses the tree and
     * keeps track of the ancestor with the lowest value.
     *
     * @param curr current node travsersing through the tree
     * @param minAncestor smallest data found in one of the ancestors of curr. Is not needed at start so set to null.
     * @param data data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private T rPredecessor(AVLNode<T> curr, T minAncestor, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data " + data + " is not in the tree.");
        } else if (data.compareTo(curr.getData()) < 0) {
            return rPredecessor(curr.getLeft(), minAncestor, data);
        } else if (data.compareTo(curr.getData()) > 0) {
            minAncestor = curr.getData();
            return rPredecessor(curr.getRight(), minAncestor, data);
        } else {
            if (curr.getLeft() != null) {
                curr = curr.getLeft();
                while (curr != null && curr.getRight() != null) {
                    curr = curr.getRight();
                }
                return curr.getData();
            } else {
                return minAncestor;
            }
        }
    }

    /**
     * Returns the data in the deepest node. If there is more than one node
     * with the same deepest depth, return the rightmost (i.e. largest) node with
     * the deepest depth.
     *
     * Should be recursive.
     *
     * Must run in O(log n) for all cases.
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        return rMaxDeepestNode(root);
    }

    /**
     * Recursive helper function of maxDeepestNode() which traverses the tree.
     *
     * @param curr current node travsersing through the tree
     * @return data of deepest node in the tree
     */
    private T rMaxDeepestNode(AVLNode<T> curr) {
        if (curr == null) {
            return null;
        } else if (curr.getLeft() == null && curr.getRight() == null) {
            return curr.getData();
        } else if (height(curr.getLeft()) > height(curr.getRight())) {
            return rMaxDeepestNode(curr.getLeft());
        } else {
            return rMaxDeepestNode(curr.getRight());
        }
    }

    public List<T> listOfLeftHeavyNodes() {
        ArrayList<T> result = new ArrayList<>();
        rListOfLeftHeavyNodes(root, result);
        return result;
    }

    private void rListOfLeftHeavyNodes(AVLNode<T> curr, List<T> list) {
        if (curr != null) {
            rListOfLeftHeavyNodes(curr.getLeft(), list);
            if (curr.getBalanceFactor() > 0) {
                list.add(curr.getData());
            }
            rListOfLeftHeavyNodes(curr.getRight(), list);
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, "", "", root);
        return sb.toString();
    }
    
    private void print(StringBuilder buffer, String prefix, String childrenPrefix, AVLNode<T> curr) {
        if (curr != null) {
            buffer.append(prefix);
            buffer.append(curr.getData() + "(" + curr.getHeight() + "," + curr.getBalanceFactor() + ")");
            buffer.append('\n');

            if (curr.getLeft() != null) {
                print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", curr.getRight());
            }
            else {
                print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", curr.getRight());
            }
            print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", curr.getLeft());
        }
    }
}
