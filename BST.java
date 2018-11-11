import java.util.ArrayList;
import java.util.Stack;

/*
 * NAME: abstractTree.java
 * Description: This is the BST class containing all methods for traversing the tree
 * Authors: Leslie Ledeboer & Natalie Weingart
 * Question 1: Leslie Ledeboer
 * Question 2: Natalie Weingart
 * Question 3: Leslie Ledeboer
 */

public class  BST<E> implements TreeInterface<E> {

   // Data fields
   public TreeNode root;
   // Store the number of Nodes in this class variables   
   public int size = 0;
   //Store the number of Non Leaf nodes in this class variables
   public int nonleaves;
   
   public ArrayList<E> inOrderTraversal = new ArrayList<>();
   public ArrayList<E> preOrderTraversal = new ArrayList<>();
   public ArrayList<E> postOrderTraversal = new ArrayList<>();
   public ArrayList<E> bstTraversal = new ArrayList<>();

   // empty constructor
   public BST(){
   }
   
      // check if it is empty
public boolean isEmpty() {
       // if the root has nothing then there can be no tree. so True
      if (root == null) {
               return true;
       } else {
            return false;
      }
}// end isEmpty

   // Looks for an item in the tree

    public boolean search(E e){

        String element = (String) e;
        String roote = (String) root.element;

        if(element.compareToIgnoreCase(roote) == 0) {
            return true;
        } 
      
        else if(element.compareToIgnoreCase(roote) < 0) {
            if(root.left == null) {
                return false;
            }

            else {
                BST current =  new BST();
                current.root = this.root.left;
                return current.search(element);
            }
        }

        else if(element.compareToIgnoreCase(roote) > 0) {
            if(root.right == null) {
                return false;
            }
            
            else {
                BST current = new BST ();
                current.root = this.root.right;
                return current.search(element);
            }
        }
        return false;
    }
    // end search method



   public void insert(E e) {
         
            String element = (String) e;
            final int initialSize = size;
            TreeNode node = new TreeNode(e);
            
            if (root == null) {
                root = node;
            }

            else {
                TreeNode currentNode = (TreeNode) root;

                while (initialSize == size) {
                    switch(element.compareToIgnoreCase((String) currentNode.element)) {
                        case -1: {

                            if (currentNode.left == null) {
                                currentNode.left = node;
                                ++size;
                            }

                            else {
                                currentNode = currentNode.left;
                            }
            
                            break;
                        }

                        case 0: {
                            return;
                        }

                        case 1: {

                            if (currentNode.right == null) {
                                currentNode.right = node;
                                ++size;
                            }

                            else {
                                currentNode = currentNode.right;
                            }
                            
                            break;
                        }
                    }
                }
            }

   }



      public boolean delete(E e) {

            String element = (String) e;
            TreeNode parent;
            boolean blResult = false;
            boolean direction = false; // true = left, false = right
            TreeNode currentNode = (TreeNode) root;
            final int initialSize = size;

            if (root != null) {
                while (initialSize == size) {
                    switch(element.compareToIgnoreCase((String) currentNode.element)) {
                        case -1: {

                            if (currentNode.left == null) {
                                return false;
                            }

                            else {
                                direction = true;
                                parent = currentNode;
                                currentNode = currentNode.left;
                            }
            
                            break;
                        }

                        case 0: {
                            
                            if (currentNode.left == null && currentNode.right == null) {
                                if (direction) {
                                    parent.left = null;
                                }

                                else {
                                    parent.right = null;
                                }
                            }

                            else if (currentNode.left == null) {
                                if (direction) {
                                    parent.left = currentNode.right;
                                }

                                else {
                                    parent.right = currentNode.right;
                                }
                            }

                            else if (currentNode.right == null) {
                                if (direction) {
                                    parent.left = currentNode.left;
                                }

                                else {
                                    parent.right = currentNode.left;
                                }
                            }

                            else {
                                TreeNode successorParent;
                                TreeNode successor = currentNode.right;

                                while (successor.left != null) {
                                    successorParent = successor;
                                    successor = successor.left;
                                }

                                if (successor.right == null) {
                                    currentNode = successor;
                                }

                                else {
                                    successorParent.left = successor.right;
                                    currentNode = successor;
                                }
                            }

                            blResult = true;
                            --size;

                            break;
                        }

                        case 1: {

                            if (currentNode.right == null) {
                                return false;
                            }

                            else {
                                direction = false;
                                parent = currentNode;
                                currentNode = currentNode.right;
                            }
                            
                            break;
                        }
                    }
                }
            }
            
            return blResult;

      }

   // returns the size of the tree
   public int getSize(){
        int size = 1;

        if(root.left != null) {
            BST current = new BST();
            current.root = this.root.left;
            size += current.getSize();
        }

        if(root.right != null) {
            BST current = new BST();
            current.root = this.root.right;
            size += current.getSize();
        }

        return size;
    }// end getSize

   //Question1: (Implement postorder traversal without using recursion)  
   public ArrayList<E>  postorderNoRecursion() 
   {
	   ArrayList<E> nonRecursivePostorder= new ArrayList<>();

        Stack<TreeNode> nodes = new Stack<>();
        TreeNode previous = null;
        TreeNode currentNode = (TreeNode) root;

        while (!nodes.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }

            else {
                if (nodes.peek().right != null && previous != nodes.peek().right) {
                    currentNode = nodes.peek().right;
                }

                else {
                    nonRecursivePostorder.add((E) nodes.peek().element);
                    previous = nodes.pop();
                }
            }
        }
       

	   return nonRecursivePostorder;
   }
   
  
  
   // Question 2: get the Number of non-leaves.
   public int getNumberofNonLeaves() {
        int nonleaves = 1;

        if(root.left != null) {
            BST current = new BST();
            current.root = this.root.left;
            nonleaves += current.getNumberofNonLeaves();
        }

        if(root.right != null) {
            BST current = new BST();
            current.root = this.root.right;
            nonleaves += current.getNumberofNonLeaves();
        }

        else {
            return 0;
        }

        return nonleaves;
    }
   
   //Question3: (Implement inorder traversal without using recursion) 
   public ArrayList<E>  inorderNoRecursion() 
   {
	    ArrayList<E> nonRecursiveInorder= new ArrayList<>();
	   
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode currentNode = (TreeNode) root;

        while (!nodes.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }

            else {
                TreeNode node = (TreeNode) nodes.pop();
                nonRecursiveInorder.add(currentNode);
                currentNode = node.right;
            }
        }

	   return nonRecursiveInorder;
   }
   
  
   // traversal with recursion
   public ArrayList<E> inorder() {
	    if(root.left != null) {
           root.left.inorder();
        }

        inOrderTraversal.add(root.element);

        if(root.right != null){
            root.right.inorder();
        }

	    return inOrderTraversal;
    }//end of inorder
 
   
   public ArrayList<E> preorder() {
        inOrderTraversal.add(root.element);
    
        if(root.left != null) {
           root.left.inorder();
        }
        
        if(root.right != null) {
            root.right.inorder();
        }

	    return preOrderTraversal;
    }// end preorder


   public ArrayList<E> postorder() {
        if(root.left != null) {
            root.left.inorder();
        }
     
        if(root.right != null) {
            root.right.inorder();
        }

        inOrderTraversal.add(root.element);
       
	    return postOrderTraversal;
    }

  

}// end class BST