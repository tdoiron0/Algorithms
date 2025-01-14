package Tree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of a BST.
 *
 * @author Trent Doiron
 * @version 1.0
 * @userid tdoiron6
 * @GTID 903833296
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class BST<T extends Comparable<? super T>> {
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     */
    public BST() {}

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
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
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
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
     * Recursive helper method for add().
     * 
     * @param curr current node while traversing the tree 
     * @param data data to add 
     * @return the new subtree after adding the new node 
     */
    private BSTNode<T> rAdd(BSTNode<T> curr, T data) {
        if (curr == null) {
            ++size; 
            return new BSTNode<T>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rAdd(curr.getLeft(), data)); 
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        if (size == 0) {
            throw new NoSuchElementException("No elements in the list to remove.");
        }

        BSTNode<T> dummy = new BSTNode<T>(null);
        root = rRemove(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Recursive helper method for remove().
     * 
     * @param curr current node while traversing the tree 
     * @param data the data to remove
     * @param dummy dummy node to hold the data that was removed 
     * @return new subree after reordering 
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        //searching for data to remove 
        if (curr == null) {
            throw new NoSuchElementException("Data " + data + " is not in the tree");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        } else {
            //when data found, put it in dummy node
            dummy.setData(curr.getData());
            --size;
            //if no children, return null and node will be removed from tree via pointer reinforcement. 
            //if only one child, that node becomes new root for subtree. 
            //if two children find successor node and set as new subtree root 
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getRight() != null && curr.getLeft() == null) {
                return curr.getRight();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null); 
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        return curr;
    }

    /**
     * Recursive helper method for rRemove(). Finds the successor node of the subtree and removes it 
     * while reordering the subtree to maintain order. Stores the value of the successor node in the dummy node.
     * 
     * @param curr current node while traversing the tree 
     * @param dummy dummy node to temporarily hold the data of the new successor node 
     * @return new subree after reordering 
     */
    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr == null) {
            return null;
        } else if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            return curr;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        if (size == 0) {
            throw new NoSuchElementException("No elements in the list to find.");
        }
        
        return rGet(root, data);
    }

    /**
     * Recursive helper method for get().
     * 
     * @param curr current node while traversing the tree 
     * @param data the data to search for
     * @return the data found in the tree
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private T rGet(BSTNode<T> curr, T data) {
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
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
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
     * Recursive helper method for contains().
     * 
     * @param curr current node while traversing the tree 
     * @param data the data to search for
     * @return whether the data was found or not
     */
    private boolean rContains(BSTNode<T> curr, T data) {
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
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        LinkedList<T> result = new LinkedList<>();
        rPreorder(root, result);
        return result;
    }

    /**
     * Recursive helper method for preorder().
     * 
     * @param curr current node while traversing the tree 
     * @return the preorder traversal of the tree
     */
    private void rPreorder(BSTNode<T> curr, LinkedList<T> list) {
        list.addLast(curr.getData());
        if (curr.getLeft() != null) {
            rPreorder(curr.getLeft(), list);
        }
        if (curr.getRight() != null) {
            rPreorder(curr.getRight(), list);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        LinkedList<T> result = new LinkedList<>();
        rInorder(root, result);
        return result;
    }

    /**
     * Recurisve helper method for inorder().
     * 
     * @param curr current node while traversing the tree 
     * @return the inorder traversal of the tree
     */
    private void rInorder(BSTNode<T> curr, LinkedList<T> list) {
        if (curr.getLeft() != null) {
            rInorder(curr.getLeft(), list);
        }
        list.addLast(curr.getData());
        if (curr.getRight() != null) {
            rInorder(curr.getRight(), list);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        LinkedList<T> result = new LinkedList<>();
        rPostorder(root, result);
        return result;
    }

    /**
     * Recursive helper method for postorder().
     * 
     * @param curr current node while traversing the tree 
     * @return the postorder traversal of the tree
     */
    private void rPostorder(BSTNode<T> curr, LinkedList<T> list) {
        if (curr.getLeft() != null) {
            rPostorder(curr.getLeft(), list);
        }
        if (curr.getRight() != null) {
            rPostorder(curr.getRight(), list);
        }
        list.addLast(curr.getData());
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        LinkedList<T> result = new LinkedList<>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> curr = queue.remove(); 
            if (curr != null) {
                result.add(curr.getData());
                queue.add(curr.getLeft());
                queue.add(curr.getRight()); 
            }
        }
        return result;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return rHeight(root);
    }

    /**
     * Recursive helper method for height().
     * 
     * @param curr current node while traversing the tree 
     * @return the height of the node passed in
     */
    private int rHeight(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        } else {
            return 1 + Math.max(rHeight(curr.getLeft()), rHeight(curr.getRight()));
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null; 
        size = 0;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     *
     * This must be done recursively.
     * 
     * A good way to start is by finding the deepest common ancestor (DCA) of both data
     * and add it to the list. You will most likely have to split off and
     * traverse the tree for each piece of data adding to the list in such a
     * way that it will return the path in the correct order without requiring any
     * list manipulation later. One way to accomplish this (after adding the DCA
     * to the list) is to then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list. 
     *
     * Please note that there is no relationship between the data parameters 
     * in that they may not belong to the same branch. 
     * 
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use considering the Big-O efficiency of the list
     * operations.
     *
     * This method only needs to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     * 
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     *
     * Ex:
     * Given the following BST composed of Integers
     *              50
     *          /        \
     *        25         75
     *      /    \
     *     12    37
     *    /  \    \
     *   11   15   40
     *  /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data cannot be equal to null.");
        }
        if (size == 0) {
            throw new NoSuchElementException("No elements in the list to remove.");
        }

        LinkedList<T> result = new LinkedList<>();
        BSTNode<T> ancesstor = findDCA(root, data1, data2);
        rGetPathPost(ancesstor, result, data1);
        result.removeLast();
        rGetPathPre(ancesstor, result, data2);
        return result;
    }

    /**
     * Recursive helper method for findPathBetween(). Finds the deepest common ancestor between two nodes.
     * 
     * @param curr current node while traversing the tree 
     * @param data1 first child of ancestor 
     * @param data2 second child of ancestor 
     * @return deepest common ancestor of the two nodes provided 
     */
    private BSTNode<T> findDCA(BSTNode<T> curr, T data1, T data2) {
        if (data1.compareTo(curr.getData()) < 0 && data2.compareTo(curr.getData()) < 0) {
            return findDCA(curr.getLeft(), data1, data2);
        } else if (data1.compareTo(curr.getData()) > 0 && data2.compareTo(curr.getData()) > 0) {
            return findDCA(curr.getRight(), data1, data2);
        } else {
            return curr;
        }
    }

    /**
     * Recursive helper method for findPathBetween(). Finds the path between the root of the subtree 
     * and the provided data in preorder. 
     * 
     * @param curr current node while traversing the tree 
     * @param list list path is to be added to 
     * @param data end point of path 
     */
    private void rGetPathPre(BSTNode<T> curr, LinkedList<T> list, T data) {
        if (curr == null) {
            return;
        } else {
            list.add(curr.getData());
            if (data.compareTo(curr.getData()) < 0) {
                rGetPathPre(curr.getLeft(), list, data);
            } else if (data.compareTo(curr.getData()) > 0) {
                rGetPathPre(curr.getRight(), list, data);
            }
        }
    }

    /**
     * Recursive helper method for findPathBetween(). Finds the path between the root of the subtree 
     * and the provided data in postorder. 
     * 
     * @param curr current node while traversing the tree 
     * @param list list path is to be added to 
     * @param data end point of path 
     */
    private void rGetPathPost(BSTNode<T> curr, LinkedList<T> list, T data) {
        if (curr == null) {
            return;
        } else {
            if (data.compareTo(curr.getData()) < 0) {
                rGetPathPost(curr.getLeft(), list, data);
            } else if (data.compareTo(curr.getData()) > 0) {
                rGetPathPost(curr.getRight(), list, data);
            }
            list.add(curr.getData());
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
    public BSTNode<T> getRoot() {
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
}
