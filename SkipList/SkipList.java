import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<T extends Comparable<? super T>> implements Iterable<T> {
  private static final int MAX_LEVEL = 4; 
  private final SkipListNode<T> head = new SkipListNode<>(null, MAX_LEVEL);
  private final Random random = new Random();
  private int level = 0;

  private static class SkipListNode<T> {
    final T value;
    final SkipListNode<T>[] forward;

    @SuppressWarnings("unchecked")
   SkipListNode(T value, int level) {
      this.value = value;
      this.forward = new SkipListNode[level + 1];
    }
  }

  public boolean contains(T value) {
   SkipListNode<T> current = head;
    for (int i = level; i >= 0; i--)
      while (current.forward[i] != null && current.forward[i].value.compareTo(value) < 0)
        current = current.forward[i];

    current = current.forward[0];
    return current != null && current.value.compareTo(value) == 0;
  }

  public void add(T value) {
   SkipListNode<T>[] update = new SkipListNode[MAX_LEVEL + 1];
   SkipListNode<T> current = head;

    for (int i = level; i >= 0; i--) {
      while (current.forward[i] != null && current.forward[i].value.compareTo(value) < 0)
        current = current.forward[i];
      update[i] = current;
    }

    current = current.forward[0];

    if (current == null || !current.value.equals(value)) {
      int newLevel = randomLevel();
      if (newLevel > level) {
        for (int i = level + 1; i <= newLevel; i++)
          update[i] = head;
        level = newLevel;
      }

     SkipListNode<T> newNode = new SkipListNode<>(value, newLevel);
      for (int i = 0; i <= newLevel; i++) {
        newNode.forward[i] = update[i].forward[i];
        update[i].forward[i] = newNode;
      }
    }
  }

  public boolean remove(T value) {
   SkipListNode<T>[] update = new SkipListNode[MAX_LEVEL + 1];
   SkipListNode<T> current = head;
    for (int i = level; i >= 0; i--) {
      while (current.forward[i] != null && current.forward[i].value.compareTo(value) < 0)
        current = current.forward[i];
      update[i] = current;
    }
    current = current.forward[0];
    if (current != null && current.value.equals(value)) {
      for (int i = 0; i <= level; i++) {
        if (update[i].forward[i] != current)
          break;
        update[i].forward[i] = current.forward[i];
      }
      while (level > 0 && head.forward[level] == null)
        level--;
      return true;
    }
    return false;
  }

  private int randomLevel() {
    int lvl = 0;
    while (lvl < MAX_LEVEL && random.nextInt(2) == 0)
      lvl++;
    return lvl;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private SkipListNode<T> current = head.forward[0];

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public T next() {
        if (current == null)
          throw new NoSuchElementException();
        T value = current.value;
        current = current.forward[0];
        return value;
      }
    };
  }

  public void printList() {
    System.out.println("SkipList:");

    for (int i = MAX_LEVEL; i >= 0; i--) {
      SkipListNode current = head.forward[i];
      System.out.print("Level " + i + ": ");

      while (current != null) {
        System.out.print(current.value + " ");
        current = current.forward[i];
      }
      System.out.println();
    }
  }
}
