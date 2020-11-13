/*
Reverse alternating k nodes in the linked list
*/

class Program {
    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }

    public static LinkedList reverseAlternatingKNodes(LinkedList head, int k) {
        return reverseAlternatingKNodes(head, k, true);
    }

    public static LinkedList reverseAlternatingKNodes(LinkedList head, int k, boolean alt) {
        LinkedList curr = head;
        LinkedList prev = null;
        LinkedList next = null;

        int count = 0;

        if (alt) {
            while (count < k && curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }
        } else {
            count = 1;
            while (count < k && curr != null) {
                curr = curr.next;
                count++;
            }
        }

        if (alt) {
            if (curr != null) {
                head.next = reverseAlternatingKNodes(curr, k, false);
            }
            return prev;
        } else {
            if (curr != null) {
                curr.next = reverseAlternatingKNodes(curr.next, k, true);
            }
            return head;
        }
    }
}
