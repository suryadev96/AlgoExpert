import java.util.*;

class Program {
  // This is an input class. Do not edit.
  public static class LinkedList {
    int value;
    LinkedList next;

    LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }

  public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
    LinkedList dummy = new LinkedList(-1);
		LinkedList prev = dummy;
		while (headOne != null && headTwo != null){
			if (headOne.value <= headTwo.value){
				prev.next = headOne;
				headOne = headOne.next;
			}else{
				prev.next = headTwo;
				headTwo = headTwo.next;
			}
			prev = prev.next;
		}
    prev.next = headOne == null ? headTwo : headOne;
		return dummy.next;
  }
}
