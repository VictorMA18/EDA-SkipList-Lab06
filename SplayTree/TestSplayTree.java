public class TestSplayTree {
  public static void main(String[] args) {
    SplayTree<Integer> splayTree = new SplayTree<>();
    splayTree.insert(10);
    splayTree.insert(20);
    splayTree.insert(30);
    splayTree.insert(40);
    splayTree.insert(50);
    splayTree.insert(25);

    splayTree.inorderTraversal();

    splayTree.displayTree();
  }
}
