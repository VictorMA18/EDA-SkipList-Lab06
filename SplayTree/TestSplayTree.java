public class TestSplayTree {
    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();
        Node root = null;
        root = splayTree.insert(root, 2);
        root = splayTree.insert(root, 1);
        root = splayTree.insert(root, 3);
        System.out.println("Preorder traversal of the modified Splay tree:");
        splayTree.preOrder(root);
    }
}
