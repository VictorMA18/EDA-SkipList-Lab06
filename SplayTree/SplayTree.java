import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class SplayTree<T extends Comparable<T>> {
  private static class SplayNode<T> {
    T key;
    SplayNode<T> left, right;

    SplayNode(T key) {
      this.key = key;
      this.left = this.right = null;
    }
  }

  private SplayNode<T> root;

  public void insert(T key) {
    root = insert(root, key);
    splay(key);
  }

  private SplayNode<T> insert(SplayNode<T> node, T key) {
    if (node == null) {
      return new SplayNode<>(key);
    }
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = insert(node.left, key);
    } else if (cmp > 0) {
      node.right = insert(node.right, key);
    }
    return node;
  }

  public void delete(T key) {
    if (root == null) return;
    root = splay(root, key);
    if (key.compareTo(root.key) != 0) return;

    if (root.left == null) {
      root = root.right;
    } else {
      SplayNode<T> temp = root;
      root = splay(root.left, key);
      root.right = temp.right;
    }
  }

  public boolean contains(T key) {
    root = splay(root, key);
    return root != null && root.key.compareTo(key) == 0;
  }

  public void splay(T key) {
    root = splay(root, key);
  }

  private SplayNode<T> splay(SplayNode<T> node, T key) {
    if (node == null) return null;

    int cmp1 = key.compareTo(node.key);
    if (cmp1 < 0) {
      if (node.left == null) return node;
      int cmp2 = key.compareTo(node.left.key);
      if (cmp2 < 0) {
        node.left.left = splay(node.left.left, key);
        node = rotateRight(node);
      } else if (cmp2 > 0) {
        node.left.right = splay(node.left.right, key);
        if (node.left.right != null) {
          node.left = rotateLeft(node.left);
        }
      }
      return node.left == null ? node : rotateRight(node);
    } else if (cmp1 > 0) {
      if (node.right == null) return node;
      int cmp2 = key.compareTo(node.right.key);
      if (cmp2 < 0) {
        node.right.left = splay(node.right.left, key);
        if (node.right.left != null) {
          node.right = rotateRight(node.right);
        }
      } else if (cmp2 > 0) {
        node.right.right = splay(node.right.right, key);
        node = rotateLeft(node);
      }
      return node.right == null ? node : rotateLeft(node);
    } else {
      return node;
    }
  }

  private SplayNode<T> rotateRight(SplayNode<T> node) {
    SplayNode<T> temp = node.left;
    node.left = temp.right;
    temp.right = node;
    return temp;
  }

  private SplayNode<T> rotateLeft(SplayNode<T> node) {
    SplayNode<T> temp = node.right;
    node.right = temp.left;
    temp.left = node;
    return temp;
  }

  public void inorderTraversal() {
    inorderTraversal(root);
    System.out.println();
  }

  private void inorderTraversal(SplayNode<T> node) {
    if (node != null) {
      inorderTraversal(node.left);
      System.out.print(node.key + " ");
      inorderTraversal(node.right);
    }
  }

  // Métodos y campos para visualizar el arbol
  private static String styleSheet =
		"node {"+
			"	shape: circle;"+
			"	size: 40px;"+
			" text-size: 12;"+
			"	fill-mode: plain;"+
			"	fill-color: skyblue;"+
			"	stroke-mode: plain;"+
			"	stroke-color: black;"+
			"	stroke-width: 1px;"+
		"}"+
		"edge { arrow-shape: arrow; arrow-size: 20px, 4px; }"+
		"node.root { fill-color: yellow; }";
	
  public void displayTree() {
    System.setProperty("org.graphstream.ui", "swing");
    Graph graph = new SingleGraph("Splay Tree");

    addNodesAndEdges(graph, root);

    for (Node node : graph)
      node.setAttribute("ui.label", node.getId());

		graph.setAttribute("ui.stylesheet", styleSheet);
    graph.display();
  }

  private void addNodesAndEdges(Graph graph, SplayNode<T> node) {
    if (node == null) return;

    addNode(graph, node.key.toString());

    if (node.left != null) {
      addNode(graph, node.left.key.toString());
      addEdge(graph, node.key.toString(), node.left.key.toString());
      addNodesAndEdges(graph, node.left);
    }

    if (node.right != null) {
      addNode(graph, node.right.key.toString());
      addEdge(graph, node.key.toString(), node.right.key.toString());
      addNodesAndEdges(graph, node.right);
    }
  }

  private void addNode(Graph graph, String key) {
    if (graph.getNode(key) == null)
      graph.addNode(key);
  }

  private void addEdge(Graph graph, String parentKey, String childKey) {
    String edgeId = parentKey + "-" + childKey;
    if (graph.getEdge(edgeId) == null)
      graph.addEdge(edgeId, parentKey, childKey, true);
  }
}
