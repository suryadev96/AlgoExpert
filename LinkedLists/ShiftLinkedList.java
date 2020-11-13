/*
Write a function that takes in the head of a singly linked list and an integer k , shifts the list in place

head = 0 -> 1 -> 2 -> 3 -> 4 -> 5
k = 2;

4 -> 5 -> 0 -> 1 -> 2 -> 3
*/
import java.util.*;

class Program {
  public static LinkedList shiftLinkedList(LinkedList head, int k) {
    int count = getLength(head);
	k = k % count;
		
	if (k < 0) k += count;
		
	if (k == 0) return head;
		
	int tail = count - k;
		
	head = rotate(head,tail);
    return head;
  }
	
	public static LinkedList rotate(LinkedList head, int tail){
		
		LinkedList curr = head;
		LinkedList newHead = null;
		
		int i=1;
		
		while (i != tail){
			curr = curr.next;
			i++;
		}
		
		//curr points to tail
		
		newHead = curr.next;
		
		curr.next = null;
		
		LinkedList temp = newHead;
		
		while (temp.next != null){
			temp = temp.next;
		}
		
		temp.next = head;
		return newHead;
	}
	
	public static int getLength(LinkedList head){
		int count = 0;
		while (head != null){
			head = head.next;
			count++;
		}
		return count;
	}

  static class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      next = null;
    }
  }
}
