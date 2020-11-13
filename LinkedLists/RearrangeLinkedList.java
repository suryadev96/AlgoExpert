/*
Rearranging a linked list around nodes with value k means moving all nodes with a value smaller than k before all nodes 
with value k and moving all nodes with a value greater than k after all nodes with value k

All moved nodes should maintain their original relative ordering if possible

Note that the linked list should be rearranged even if it doesn't have any nodes with value k

head = 3 -> 0 -> 5 -> 2 -> 1 -> 4
k = 3;

0 -> 2 -> 1 -> 3 -> 5 -> 4
//new head node with value 0
//maintained their original relative ordering
//so have the nodes with values 5,4
*/

class Program {
    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
        LinkedList dummy1 = new LinkedList(0);
        LinkedList dummy2 = new LinkedList(0);
        LinkedList dummy3 = new LinkedList(0);

        LinkedList current1 = dummy1;
        LinkedList current2 = dummy2;
        LinkedList current3 = dummy3;

        while (head != null) {
            if (head.value < k) {
                current1.next = new LinkedList(head.value);
                current1 = current1.next;
            } else if (head.value == k) {
                current2.next = new LinkedList(head.value);
                current2 = current2.next;
            } else {
                current3.next = new LinkedList(head.value);
                current3 = current3.next;
            }
            head = head.next;
        }
        if (current1 == dummy1 && current2 == dummy2) {
            return dummy3.next;
        } else if (current2 == dummy2) {
            current1.next = dummy3.next;
            return dummy1.next;
        } else { //if both list1 , list2 are present
            current1.next = dummy2.next;
            current2.next = dummy3.next;
            return dummy1.next;
        }
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
