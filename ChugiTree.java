//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Chugi Tree
// Course:   CS 300 Fall 2022
//
// Author:   Chaitanya Sharma
// Email:    csharma4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
// Persons:         None
// Online Sources:  None
///////////////////////////////////////////////////////////////////////////////

import com.sun.source.tree.BinaryTree;
import java.util.NoSuchElementException;

/**
 * This class implements a ChugidexStorage as a Binary Search Tree.
 * 
 * Notes: 1) You may NOT use any arrays or Collections objects (ArrayLists, etc)
 * in this class. 2)
 * You may NOT use any loops (for, while, etc) in this class. Recursive
 * strategies only.
 */
public class ChugiTree implements ChugidexStorage {

  /**
   * The root of this ChugiTree. Set to null when tree is empty.
   */
  private BSTNode<Chugimon> root;

  /**
   * The size of this ChugiTree (total number of Chugimon stored in this BST)
   */
  private int size; // size of the ChugiTree
  private static BSTNode<Chugimon> otherNode = null;

  /**
   * Constructor for ChugiTree. Should set size to 0 and root to null.
   */
  public ChugiTree() {
    size = 0;
    root = null;
  }

  /**
   * Getter method for the Chugimon at the root of this BST.
   *
   * @return the root of the BST.
   */
  public Chugimon getRoot() {
    if (root != null) {
      return root.getData();
    }
    return null;
  }

  /**
   * A method for determining whether this ChugiTree is a valid BST. In
   * order to be a valid BST, the following must be true: For every internal
   * (non-leaf) node X of a binary tree, all the values in the node's left subtree
   * are less than the value in X, and all the values in the node's right subtree
   * are greater than the value in X.
   *
   * @return true if this ChugiTree is a valid BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(root);
  }

  /**
   * A helper method for determining whether this ChugiTree is a valid BST. In
   * order to be a valid BST, the following must be true: For every internal
   * (non-leaf) node X of a binary tree, all the values in the node's left subtree
   * are less than the value in X, and all the values in the node's right subtree
   * are greater than the value in X.
   *
   * @param node The root of the BST.
   * @return true if the binary tree rooted at node is a BST, false otherwise
   */
  public static boolean isValidBSTHelper(BSTNode<Chugimon> node) {
    if(node == null){ // return true if node null
      return true;
    }
    if(isValidBSTHelper(node.getLeft()) == false){ // false if left tree is not valid
      return false;
    }
    if(otherNode!=null && otherNode.getData().compareTo(node.getData())>=0){
      return false;
    }
    otherNode = node;
    return isValidBSTHelper(node.getRight());
  }

  /**
   * Checks whether this ChugiTree is empty or not
   *
   * @return true if this tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    // TODO implement this method
    if (getRoot() == null) { // checks if there is a root
      return true;
    }
    if(size == 0){
      return false;
    }
    return false; // default return statement
  }

  /**
   * Gets the size of this ChugiTree
   *
   * @return the total number of Chugimons stored in this tree
   */
  @Override
  public int size() {
    // TODO implement this method
    return size(root);
  }

  /**
   * Private helper method to get size of the tree
   *
   * @param node
   * @return
   */
  private int size(BSTNode<Chugimon> node) {
    if (node == null) {
      return 0;
    } else {
      return (size(node.getRight()) + 1 + size(node.getLeft()));
    }
  }

  /**
   * Returns a String representation of all the Chugimons stored within this
   * ChugiTree in the
   * increasing order with respect to the result of Chugimon.compareTo() method.
   * The string should
   * be a comma-separated list of all the Chugimon toString() values. No spaces
   * are expected to be
   * in the resulting string. No comma should be at the end of the resulting
   * string. For instance,
   * <p>
   * "nameOne#12.25,nameTwo#12.56,nameTwo#89.27"
   *
   * @return a string containing all the Chugimon, in the increasing order.
   * Returns an empty
   * string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the
   * ChugiTree rooted at node. An example of the String representation of the
   * contents of a ChugiTree storing three Chugimons is provided in the
   * description of the above toString() method.
   *
   * @param node references the root of a subtree
   * @return a String representation of all the Chugimons stored in the subtree
   * rooted at node inincreasing order. Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Chugimon> node) {
    // TODO Implement this method
    String toString = "";
    if (node == null){
      return toString; // return empty string if node is null
    }

    toString += toStringHelper(node.getLeft()); // recursive case for left nodes
    toString += node.getData().toString()+","; //adds the toString of the node to the final value
    toString += (node.getRight()); // recursive case for right node

    return toString;
  }

  /**
   * Adds a new Chugimon to this ChugiTree. Duplicate Chugimons are NOT allowed.
   *
   * @param newChugimon Chugimon to add to this ChugiTree
   * @return true if the newChugimon was successfully added to the ChugiTree,
   * false if a match with newChugimon is already present in the tree.
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  newChugimon is null.
   */
  @Override
  public boolean add(Chugimon newChugimon) throws IllegalArgumentException{
    // TODO implement this method
    if(newChugimon == null){
      throw new IllegalArgumentException("Null chugi input"); // exception if chugi is null
    }
    if(isEmpty()){
      root = new BSTNode<Chugimon>(newChugimon); // adds chugi as root if tree null
      size++;
      return true;
    }else{
      boolean adder = addHelper(newChugimon, root);
      if(adder){
        size ++; // general adding
      }
      return adder;
    }
  }


  /**
   * Recursive helper method to insert a new Chugimon to a Pokedex rooted at node.
   *
   * @param node        The "root" of the subtree we are inserting the new
   *                    Chugimon into.
   * @param newChugimon The Chugimon to be added to a BST rooted at node. We
   *                    assume that newChugimon is NOT null.
   * @return true if the newChugimon was successfully added to the ChugiTree,
   * false if a match with
   * newChugimon is already present in the subtree rooted at node.
   */
  protected static boolean addHelper(Chugimon newChugimon, BSTNode<Chugimon> node) {
    // TODO implement this method
    if (node.getData().compareTo(newChugimon) > 0) { // if chugimon is less than the node data
      if (node.getLeft() == null) {
        node.setLeft(new BSTNode<Chugimon>(newChugimon));
        return true;
      } else{
        return addHelper(newChugimon, node.getLeft());//moves forward if no space
      }
    }
    if (node.getData().compareTo(newChugimon) < 0) { // if chugimon is greater than the node data
      if (node.getRight() == null) {
        node.setRight(new BSTNode<Chugimon>(newChugimon));
        return true;
      } else {
        return addHelper(newChugimon, node.getRight()); // moves forward if no space
      }
    }
    return false; 
  }

  /**
   * Searches a Chugimon given its first and second identifiers.
   *
   * @param firstId  First identifier of the Chugimon to find
   * @param secondId Second identifier of the Chugimon to find
   * @return the matching Chugimon if match found, null otherwise.
   */
  @Override
  public Chugimon lookup(int firstId, int secondId) {
    // TODO Implement this method.
    if(isEmpty()){
      return null;
    }
    Chugimon chugi =  new Chugimon(firstId, secondId);
    return lookupHelper(chugi, root);
  }

  /**
   * Recursive helper method to search and return a match with a given Chugimon in
   * the subtree rooted at node, if present.
   *
   * @param toFind a Chugimon to be searched in the BST rooted at node. We assume
   *               that toFind is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a
   *               match to target.
   * @return a reference to the matching Chugimon if found, null otherwise.
   */
  protected static Chugimon lookupHelper(Chugimon toFind, BSTNode<Chugimon> node) {
    if (node != null) {
      if (node.getData().equals(toFind)) {
        return node.getData(); // returns the node data with the same chugimon
      }
      if (node.getData().compareTo(toFind) > 0 && node.getLeft() != null) {
        return lookupHelper(toFind, node.getLeft()); // recursive case if chugimon is greater than the node
      }
      if(node.getData().compareTo(toFind) < 0 && node.getRight() != null) {
        return lookupHelper(toFind, node.getRight());// recursive case if chugimon is less than the node
      }
    }
    return null;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES
   * from root to the deepest leaf.
   *
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at
   * node counting the number of nodes and NOT the number of edges from node to
   * the deepest leaf
   *
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  protected static int heightHelper(BSTNode<Chugimon> node) {
    // TODO Implement this method.
    if (node == null) {
      return 0;
    } else {
      int left = heightHelper(node.getLeft()); // left side height
      int right = heightHelper(node.getRight()); // right side height

      if (left > right) {
        return (left + 1); // increments left height if greater than right side
      } else {
        return (right + 1); // same for right
      }
    }
  }

  /**
   * Recursive method to find and return the first Chugimon, in the increasing
   * order, within this ChugiTree (meaning the smallest element stored in the
   * tree).
   *
   * @return the first element in the increasing order of this BST, and null if
   * the tree is empty.
   */
  @Override
  public Chugimon getFirst() {
    // TODO Implement this method.
    return getFirstHelper(root);
  }

  /**
   * Recursive helper method for getFirst().
   *
   * @param root the node from which to find the minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getFirstHelper(BSTNode<Chugimon> root) {
    // TODO Implement this method.
    // HINT: The smallest element in a non-empty BST is the left most element
    if (root == null) {
      return null; // returns null if root is null
    }
    if (root.getLeft() == null) {
      return root.getData(); // returns the root if there is no left node
    }
    return getFirstHelper(root.getLeft()); // recursive case to find the smallest chugimon
  }

  /**
   * Recursive method to find and return the last Chugimon, in the increasing
   * order, within this ChugiTree (meaning the greatest element stored in the
   * tree).
   *
   * @return the last element in the increasing order of this BST, and null if the
   * tree is empty.
   */
  @Override
  public Chugimon getLast() {
    // TODO Implement this method.
    return getLastHelper(root);
  }

  /**
   * Recursive helper method for getLast().
   *
   * @param root the node from which to find the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getLastHelper(BSTNode<Chugimon> root) {
    // TODO Implement this method.
    // HINT: The smallest element in a non-empty BST is the right most element
    if (root == null) {
      return null; // returns null if root is null
    }
    if (root.getRight() == null) {
      return root.getData(); // returns the root if there is no right node
    }
    return getFirstHelper(root.getRight()); // recursive case to find the largest chugimon
  }

  /**
   * Recursive method to get the number of Chugimon with a primary or secondary
   * type of the specified type, stored in this ChugiTree.
   *
   * @param chugiType the type of Chugimons to count. We assume that chugiType is
   *                  NOT null.
   * @return the number of all the Chugimon objects with a primary or secondary
   * type of the
   * specified type stored in this ChugiTree.
   */
  public int countType(ChugiType chugiType) {
    // TODO(student): Implement method.
    return countHelper(root,chugiType);
  }

  /**
   * Helper method for count type
   * @param node Root node, to start checking from
   * @param chugiType Required type
   * @return number of chugis with the required type
   */
  private int countHelper(BSTNode<Chugimon> node,ChugiType chugiType){
    int counter = 0;
    if(node == null){
      return 0;
    }
    counter += countHelper(node.getLeft(),chugiType);
    if(node.getData().getPrimaryType() == chugiType){ // checks for primary type
      counter ++;
    }else if(node.getData().getSecondaryType() == chugiType){ //checks secondary type
      counter++;
    }
    counter += countHelper(node.getRight(),chugiType); // recursive case
    return counter;
  }

  /**
   * Finds and returns the in-order successor of a specified Chugimon in this
   * ChugiTree
   *
   * @param chugi the Chugimon to find its successor
   * @return the in-order successor of a specified Chugimon in this ChugiTree
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  <b>chugi</b> is null
   * @throws NoSuchElementException   with a descriptive error message if the
   *                                  Chugimon provided as input has no in-order
   *                                  successor in this ChugiTree.
   */
  @Override
  public Chugimon next(Chugimon chugi) throws IllegalArgumentException, NoSuchElementException {
    // TODO: Implement this method.
    if (root == null) {
      BSTNode<Chugimon> rootNode = new BSTNode<Chugimon>(chugi);
    }
    return nextHelper(chugi,root,null);
  }

  /**
   * Recursive helper method to find and return the next Chugimon in the tree
   * rooted at node.
   *
   * @param chugi a Chugimon to search its in-order successor. We assume that
   *              <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param next  a BSTNode which stores a potential candidate for next node
   * @return the next Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the
   *                                Chugimon provided as input has no in-order
   *                                successor in the subtree
   *                                rooted at node.
   */
  protected static Chugimon nextHelper(Chugimon chugi, BSTNode<Chugimon> node, BSTNode next) throws NoSuchElementException {
    if(node == null){
      throw new NoSuchElementException();
    }
    if (chugi != null) {
      if (chugi.compareTo(node.getData()) == 0) {
        if (node.getRight() != null) {
          return getFirstHelper(node.getRight());
        } else if (next != null) {
          return (Chugimon) next.getData(); //base case
        } else {
          throw new NoSuchElementException();
        }
      }
      else if (chugi.compareTo(node.getData()) < 0) {
        next = node;
        return nextHelper(chugi, node.getLeft(), next); // recursive case 1
      } else {
        next = node;
        return nextHelper(chugi, node.getRight(), next); // recursive case 2
      }
    }
    return null;
  }

  /**
   * Finds and returns the in-order predecessor of a specified Chugimon in this
   * ChugiTree
   *
   * @param chugi the Chugimon to find its predecessor
   * @return the in-order predecessor of a specified Chugimon in this ChugiTree.
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  <b>chugi</b> is null
   * @throws NoSuchElementException   if there is no Chugimon directly before the
   *                                  provided Chugimon
   */
  @Override
  public Chugimon previous(Chugimon chugi) {
    return previousHelper(chugi, root, null);
  }

  /**
   * Recursive helper method to find and return the previous Chugimon in the tree
   * rooted at node.
   *
   * @param chugi a Chugimon to search its in-order predecessor. We assume that
   *              <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param prev  a BSTNode which stores a potential candidate for previous node
   * @return the previous Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the
   *                                Chugimon provided as input has no in-order
   *                                predecessor in the subtree rooted at node.
   */
  protected static Chugimon previousHelper(Chugimon chugi, BSTNode<Chugimon> node,
                                           BSTNode<Chugimon> prev) {
    if(node == null){
      throw new NoSuchElementException();
    }
    if(chugi.compareTo(node.getData()) == 0){
      if(node.getLeft() != null){
        return getLastHelper(node.getLeft());
      } else if(prev != null){
        return (Chugimon) prev.getData(); // base case
      } else{
        throw new NoSuchElementException();
      }
    }
    else if(chugi.compareTo(node.getData()) < 0){
      prev = node;
      return previousHelper(chugi,node.getRight(),prev); // recursive case
    } else{
      prev = node;
      return previousHelper(chugi,node.getLeft(),prev); // recursive case
    }
  }

  /**
   * Deletes a specific Chugimon from this ChugiTree.
   *
   * @param chugi the Chugimon to delete
   * @return true if the specific Chugimon is successfully deleted, false if no
   * match found with any
   * Chugimon in this tree.
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  <b>chugi</b> is null
   */
  @Override
  public boolean delete(Chugimon chugi) {
    // TODO Implement this method.
    if(chugi==null) {
      throw new IllegalArgumentException("Chugi null");
    }
    if(lookup(chugi.getFirstID(),chugi.getSecondID())==null) {
      return false;
    }
    root = deleteChugimonHelper(chugi,root);
    size-=1; // decreases size after deleting
    return true;
  }

  /**
   * Recursive helper method to search and delete a specific Chugimon from the BST
   * rooted at node
   *
   * @param target a reference to a Chugimon to delete from the BST rooted at
   *               node. We assume that target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a
   *               match with the target Chugimon.
   * @return the new "root" of the subtree we are checking after trying to remove
   * target
   * @throws NoSuchElementException with a descriptive error message if there is
   *                                no Chugimon matching target in the BST rooted
   *                                at <b>node</b>
   */
  protected static BSTNode<Chugimon> deleteChugimonHelper(Chugimon target, BSTNode<Chugimon> node) throws NoSuchElementException {
    if (node == null) {
      throw new NoSuchElementException("Node is null"); // exception for null node
    }
    if (target.compareTo(node.getData()) < 0) {
      deleteChugimonHelper(target, node.getLeft());
    } else if (target.compareTo(node.getData()) > 0) {
      deleteChugimonHelper(target, node.getRight());
    }
    if(node.equals(target)){
      // Case 1: node may be a leaf (has no children), set node to null.
      if (node.getLeft() == null && node.getRight() == null) {
        node = null;
      }
      // Case 2: node may have only one child, set node to that child (whether left or
      // right child)
      if (node.getLeft() == null && node.getRight() != null) {
        node = node.getRight();
      }
      if (node.getLeft() != null && node.getRight() == null) {
        node = node.getLeft();
      }
      if(node.getLeft() != null && node.getRight() != null){
        node = new BSTNode<Chugimon>(nextHelper((Chugimon)(node.getData()),node,null));
      }
      // Case 3: node may have two children
      return node;
    }
    return null; // Default return statement added to resolve compiler errors
  }
}