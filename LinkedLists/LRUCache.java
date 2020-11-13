import java.util.HashMap;
import java.util.Map;

// Do not edit the class below except for the insertKeyValuePair,
// getValueFromKey, and getMostRecentKey methods. Feel free
// to add new properties and methods to the class.
class Node {

    String key;
    int value;
    Node next;
    Node prev;

    public Node(String key, int value) {
        this.key = key;
        this.value = value;
    }

}

class Program {
    static class LRUCache {
        int maxSize;

        //to quickly find the node in the doubly linked list
        Map<String, Node> map;

        //no of elements present in cache
        int count;

        Node head, tail;

        public LRUCache(int maxSize) {
            this.maxSize = maxSize > 1 ? maxSize : 1;
            map = new HashMap<>();
            head = new Node("", -1); //dummy
            tail = new Node("", -1); //dummy
            head.next = tail;
            tail.prev = head;
            count = 0;
        }

        public void insertKeyValuePair(String key, int value) {
            //if key is already present in cache
            if (map.get(key) != null) {
                Node node = map.get(key);
                node.value = value;
                deleteNode(node);
                addToHead(node);
            } else {
                Node node = new Node(key, value);
                map.put(key, node);
                if (count < maxSize) {
                    count++;
                    addToHead(node);
                } else {
                    map.remove(tail.prev.key);
                    deleteNode(tail.prev);
                    addToHead(node);
                }
            }
        }

        //head->node->tail
        public void deleteNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        //head->next
        //head->node->next
        public void addToHead(Node node) {
            node.next = head.next;
            node.next.prev = node;
            node.prev = head;
            head.next = node;
        }

        public LRUResult getValueFromKey(String key) {
            //if key is already present in the cache
            if (map.get(key) != null) {
                Node node = map.get(key);
                LRUResult result = new LRUResult(true, node.value);
                deleteNode(node);
                addToHead(node);
                return result;
            }
            return new LRUResult(false, -1);
        }

        public String getMostRecentKey() {
            if (count == 0) return null;
            return head.next.key;
        }
    }

    static class LRUResult {
        boolean found;
        int value;

        public LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }
    }
}
