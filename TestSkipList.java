public class TestSkipList {
  public static void main(String[] args) {
    SkipList<Integer> skipList = new SkipList<>();
    skipList.add(3);
    skipList.add(6);
    skipList.add(7);
    skipList.add(9);
    skipList.add(12);
    skipList.add(19);
    skipList.add(17);
    skipList.add(26);
    skipList.add(21);
    skipList.add(25);

    System.out.println("Skip List contains 19: " + skipList.contains(19));
    System.out.println("Skip List contains 15: " + skipList.contains(15));

    skipList.remove(19);
    System.out.println("Skip List contains 19 after removal: " + skipList.contains(19));

    skipList.printList();
  }
}
