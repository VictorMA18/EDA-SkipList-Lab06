import java.util.ArrayList;
import java.util.List;

class Node {
    public int key;
    public Node left, right;
}

class SplayTree {
    public Node newNode(int key) {
        Node node = new Node();
        node.key = key;
        node.left = node.right = null;
        return node;
    }

    public Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    public Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    public Node splay(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key > key) {
            if (root.left == null)
                return root;
            if (root.left.key > key) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            }
            else if (root.left.key < key) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }
            return (root.left == null) ? root : rightRotate(root);
        }
        else {
            if (root.right == null)
                return root;
            if (root.right.key > key) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }
            else if (root.right.key < key) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    public Node insert(Node root, int key) {
        if (root == null)
            return newNode(key);

        root = splay(root, key);

        if (root.key == key)
            return root;

        Node node = newNode(key);
        if (root.key > key) {
            node.right = root;
            node.left = root.left;
            root.left = null;
        }
        else {
            node.left = root;
            node.right = root.right;
            root.right = null;
        }
        return node;
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print("\n" + node.key + " ");
            System.out.println();
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public List<Node> getNodes(Node node) {
        List<Node> nodes = new ArrayList<>();
        preOrderGetNodes(node, nodes);
        return nodes;
    }

    public void preOrderGetNodes(Node node, List<Node> nodes) {
        if (node != null) {
            nodes.add(node);
            preOrderGetNodes(node.left, nodes);
            preOrderGetNodes(node.right, nodes);
        }
    }
}
