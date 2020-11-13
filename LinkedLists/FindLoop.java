class Program {
    public static LinkedList findLoop(LinkedList head) {
        LinkedList first = head.next;
        LinkedList second = head.next.next;

        while (first != second) {
            first = first.next;
            second = second.next.next;
        }

        first = head;

        while (first != second) {
            first = first.next;
            second = second.next;
        }

        return first;
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
