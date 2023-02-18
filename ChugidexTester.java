//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Chugidex Tester
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
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the Chugimon
 * and ChugiTree classes.
 * 
 * @author Chaitanya Sharma
 *
 */

public class ChugidexTester {


  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Chugimon class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonCompareToEquals() {
	  //tester chugis
	  Chugimon pika = new Chugimon(40,45 );
	  Chugimon pika1 = new Chugimon(40, 45);
	  Chugimon bulba = new Chugimon(3, 9);

	  if (pika.equals(pika1) == false)
		  return false;
	  if (pika.equals(bulba) == true)
		  return false;
	 
	 if (pika.compareTo(pika1) != 0)
		 return false;

    return true;
  }

  /**
   * Checks the correctness of the implementation of Chugimon.toString() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonToString() {
	  Chugimon pika = new Chugimon(8, 9);
	  String toString = ChugidexUtility.getChugimonName(8, 9);
	  if (!pika.toString().equals(toString + "#" + 8 + "." + 9)){
		  return false;
	  }
    return true;
  }

  /**
   * Checks the correctness of the implementation of ChugiTree.isValidBSTHelper() method. This
   * tester should consider at least three scenarios. (1) An empty tree whose root is null should be
   * a valid BST. (2) Consider a valid BST whose height is at least 3. Create the tree using the
   * constructors of the BSTNode and its setters methods, (3) Consider a NON-valid BST where the
   * search order property is violated at at least one internal node.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIsValidBSTHelper() {
	  if (ChugiTree.isValidBSTHelper(null) == false) {
		  return false;
	  }
	  // new chugis and nodes
	  Chugimon pika = new Chugimon(10, 25);
	  BSTNode<Chugimon> NodeA = new BSTNode<Chugimon>(pika);
	  Chugimon charm = new Chugimon(8, 27);
	  BSTNode<Chugimon> NodeB = new BSTNode<Chugimon>(charm);
	  Chugimon bulba = new Chugimon(9, 2);
	  BSTNode<Chugimon> NodeC = new BSTNode<Chugimon>(bulba);
	   /*
	   Makes a tree from the node info available
	    */
	  if(pika.compareTo(charm) > 0){
		  NodeA.setLeft(NodeB);
	  } else{
		  NodeA.setRight(NodeB);
	  }
	  if(pika.compareTo(bulba) > 0){
		  if(NodeA.getLeft() == null){
			  NodeA.setLeft(NodeC);
		  } else{
			  if(charm.compareTo(bulba) > 0){
				  NodeB.setLeft(NodeC);
			  }else{
				  NodeB.setRight(NodeC);
			  }
		  }
	  }
	  if(pika.compareTo(bulba) < 0){
		  if(NodeA.getRight() == null){
			  NodeA.setRight(NodeC);
		  } else{
			  if(charm.compareTo(bulba) < 0){
				  NodeB.setRight(NodeC);
			  }else{
				  NodeB.setLeft(NodeC);
			  }
		  }
	  }

	  if (ChugiTree.isValidBSTHelper(NodeA) == false) {
		  return false; // returns false if the tree isnt valid
	  }
	  BSTNode<Chugimon> NodeD = new BSTNode<Chugimon>(pika); // adds an existing element
	  NodeC.setRight(NodeD);
	  if (ChugiTree.isValidBSTHelper(NodeA) == true) {
		  return false; // returns false if an element repeats twice and is still true
	  }
    return true; // no bugs
  }

  /**
   * Checks the correctness of the implementation of both add() and toString() methods implemented
   * in the ChugiTree class. This unit test considers at least the following scenarios. (1) Create a
   * new empty ChugiTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one Chugimon and then check that the add()
   * method call returns true, the tree is not empty, its size is 1, and the toString() called on
   * the tree returns the expected output. (3) Try adding another Chugimon which is less than the
   * Chugimon at the root, (4) Try adding a third Chugimon which is greater than the one at the
   * root, (5) Try adding at least two further Chugimons such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, that the ChugiTree is a valid BST, and that the toString() method returns the expected
   * string representation of the contents of the binary search tree in an increasing order from the
   * smallest to the greatest Chugimon. (6) Try adding a Chugimon already stored in the tree. Make
   * sure that the add() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddToStringSize() {
	  ChugiTree tree = new ChugiTree(); // new binary tree
	  if (tree.isEmpty() == false || tree.size() != 0 || tree.toString() != "") {
		  return false; // checks for initialization errors
	  }

	  Chugimon pika = new Chugimon(5,1);
	  if (tree.add(pika) != true) { // adds chugi to tree
		  return false;
	  }
	  if(tree.size() != 1){
		  return false;
	  }
	  Chugimon charm = new Chugimon(4,1); //lesser chugi
	  if (tree.add(charm) != true){
		  System.out.println("11");
		  return false;
	  }
	  if(tree.size() != 2){
		  System.out.println("5");
		  return false;
	  }
	  Chugimon bulba  = new Chugimon(6,1); // greater chugi
	  if (tree.add(bulba) != true){
		  return false;
	  } if(tree.size() != 3){
		  return false;
	  }

	  Chugimon dino = new Chugimon(9, 1);//left chugi
	  Chugimon eggo = new Chugimon(7, 1); // right chugi
	  if (tree.add(dino) != true  || tree.add(eggo) != true){
		  return false; // tests further for a lesser and greater chugi
	  }
	  if(tree.size() != 5){
		  return false;
	  }

	  Chugimon bat = new Chugimon(4, 1); // adds an existing chugi
	  if (tree.add(bat) == true){
		  return false;
	  } if(tree.size() != 5) {
		  return false;
	  }
	  return true;
  }

  /**
   * This method checks mainly for the correctness of the ChugiTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ChugiTree. Then, check that
   * calling the lookup() method on an empty ChugiTree returns false. (2) Consider a ChugiTree of
   * height 3 which contains at least 5 Chugimons. Then, try to call lookup() method to search for a
   * Chugimon having a match at the root of the tree. (3) Then, search for a Chugimon at the right
   * and left subtrees at different levels considering successful and unsuccessful search
   * operations. Make sure that the lookup() method returns the expected output for every method
   * call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
	  ChugiTree tree = new ChugiTree();
	  // invalid argument test
	  if(tree.lookup(2,4) != null){
		  return false;
	  }
	  // new chugimons in tree
	  Chugimon charm = new Chugimon(5, 1);
	  tree.add(charm);
	  Chugimon pika = new Chugimon(4, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  tree.add(bulba);
	  Chugimon meta = new Chugimon(11,1);
	  tree.add(meta);
	  Chugimon cart = new Chugimon(10,1);
	  tree.add(cart);
	  try {
		  if (!tree.lookup(5, 1).equals(charm)) { //root level test
			  return false;
		  }
		  if (!tree.lookup(4, 1).equals(pika)) { //left subtree 1st level test
			  return false;
		  }

		  if (!tree.lookup(11, 1).equals(meta)) { //right subtree 2nd level test
			  return false;
		  }
	  }catch(NullPointerException e){
		  System.out.println("Null lookup output");
		  return false;
	  }

	  return true; //all lookup passed
  }

  /**
   * Checks for the correctness of ChugiTree.countType() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCountType() {
	  ChugiTree tree = new ChugiTree();
	  // new chugimons in tree
	  Chugimon charm = new Chugimon(5, 1);
	  tree.add(charm);
	  Chugimon pika = new Chugimon(4, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  tree.add(bulba);
	  if(tree.countType(ChugiType.FIRE) != 3){
		  return false;
	  }
    return true;
  }
  
  /**
   * Checks for the correctness of ChugiTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Chugimon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ChugiTree with four levels for instance, is 4.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
	  ChugiTree tree = new ChugiTree();

	  if (tree.height() != 0) {
		  return false;
	  }
	  Chugimon squirtle = new Chugimon(4, 1);
	  tree.add(squirtle);
	  if (tree.height() != 1) {
		  return false;
	  }
	  // new chugimons in tree
	  Chugimon pika = new Chugimon(5, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  tree.add(bulba);
	  Chugimon meta = new Chugimon(11,1);
	  tree.add(meta);
	  Chugimon shell = new Chugimon(90,5);
	  tree.add(shell);
	  if (tree.height() != 5) {
		  return false;
	  }
    return true; // if correct height returned
  }

  /**
   * Checks for the correctness of ChugiTree.getFirst() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetFirst() {
	  // new chugimons in tree
	  ChugiTree tree = new ChugiTree();
	  Chugimon charm = new Chugimon(5, 1);
	  tree.add(charm);
	  Chugimon pika = new Chugimon(4, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  tree.add(bulba);

	  if (tree.getFirst().equals(pika) != true) {
		  return false;
	  }
	  return true; // no bug
  }

  /**
   * Checks for the correctness of ChugiTree.getLast() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLast() {
	  // new chugimons in tree
	  ChugiTree tree = new ChugiTree();
	  Chugimon charm = new Chugimon(5, 1);
	  tree.add(charm);
	  Chugimon pika = new Chugimon(4, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  tree.add(bulba);
	  
	  if (tree.getLast().equals(bulba) != true) {
		  return false;
	  }
	  return true; // true
  }

  /**
   * Checks for the correctness of ChugiTree.delete() method. This test must consider at least 3
   * test scenarios. (1) Remove a Chugimon that is at leaf node (2) Remove a Chugimon at non-leaf
   * node. For each of these scenarios, check that the size of the tree was decremented by one and
   * that the resulting ChugiTree is a valid BST, (3) ensures that the ChugiTree.delete() method
   * returns false when called on an Chugimon that is not present in the BST.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testDelete() {
	  // new chugimons in tree
	  ChugiTree tree = new ChugiTree();
	  Chugimon charm = new Chugimon(5, 1);
	  tree.add(charm);
	  Chugimon pika = new Chugimon(4, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  // case 1
	  tree.delete(bulba);
	  if(tree.lookup(6,1) != null){
		  return false;
	  }
    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.next() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testNext() {
	  // new chugimons in tree
	  ChugiTree tree = new ChugiTree();
	  Chugimon charm = new Chugimon(5, 1);
	  tree.add(charm);
	  Chugimon pika = new Chugimon(4, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  tree.add(bulba);
	  
	  if (tree.next(charm).equals(bulba) != true) {
		  return false;
	  }
    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.previous() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testPrevious() {
	  // new chugimons in tree
	  ChugiTree tree = new ChugiTree();
	  Chugimon charm = new Chugimon(5, 1);
	  tree.add(charm);
	  Chugimon pika = new Chugimon(4, 1);
	  tree.add(pika);
	  Chugimon bulba = new Chugimon(6, 1);
	  tree.add(bulba);

	  if (!tree.previous(charm).equals(pika)) {
		  return false;
	  }
	  return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testChugimonCompareToEquals: " + testChugimonCompareToEquals());
    System.out.println("testChugimonToString(): " + testChugimonToString());
    System.out.println("testIsValidBSTHelper(): " + testIsValidBSTHelper());
    System.out.println("testAddToStringSize(): " + testAddToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testCountType(): " + testCountType());
    System.out.println("testGetFirst(): " + testGetFirst());
    System.out.println("testGetLast(): " + testGetLast());
    System.out.println("testDelete(): " + testDelete());
    System.out.println("testNext(): " + testNext());
    System.out.println("testPrevious(): " + testPrevious());
  }

}
