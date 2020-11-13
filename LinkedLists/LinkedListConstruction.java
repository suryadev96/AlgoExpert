class Node{
	int value;
	Node prev;
	Node next;

	public Node(int value){
		this.value = value;
	}
}
class DoublyLinkedList{

	Node head;
	Node tail;

	public void setHead(Node node){
		if (head == null){
			head = node;
			tail = node;
			return;
		}
		insertBefore(head,node);
	}

	public void setTail(Node node){
		if (tail == null){
			setHead(node);
			return;
		}
		insertAfter(tail,node);
	}

	//1->2->3->4
	public void insertBefore(Node node, Node nodeToInsert){
		//if there is only one node
		if (nodeToInsert == head && nodeToInsert == tail) return;
		remove(nodeToInsert);

		nodeToInsert.prev = node.prev;
		nodeToInsert.next = node;

		if (node.prev == null){
			head = nodeToInsert;
		}else{
			node.prev.next = nodeToInsert;
		}

		node.prev = nodeToInsert;
	}	

	public void insertAfter(Node node, Node nodeToInsert){
		if (nodeToInsert == head && nodeToInsert == tail) return;
		remove(nodeToInsert);

		nodeToInsert.prev = node;
		nodeToInsert.next = node.next;

		if (node.next == null){
			tail = nodeToInsert;
		}else{
			node.next.prev = nodeToInsert;
		}
		node.next = nodeToInsert;
	}

	public void insertAtPosition(int position, Node nodeToInsert){
		if (position == 1){
			setHead(nodeToInsert);
			return;
		}

		Node node = head;
		int currPos = 1;
		while (node != null && currPos++ != position) node = node.next;

		if (node != null){
			insertBefore(node,nodeToInsert);
		}else{
			setTail(nodeToInsert);
		}

	}

	public void removeNodesWithValue(int value){
		Node node = head;
		while (node != null){
			Node nodeToRemove = node;
			node = node.next;
			if (nodeToRemove.value == value) remove(nodeToRemove);
		}
	}

	public void remove(Node node){
		if (node == head) head = head.next;
		if (node == tail) tail = tail.prev;
		removeNodeBindings(node);
	}

	public void removeNodeBindings(Node node){
		if (node.prev != null) node.prev.next = node.next;
		if (node.next != null) node.next.prev = node.prev;
		node.prev = null;
		node.next = null;
	}

	public boolean containsNodeWithValue(int value){
		Node node = head;
		while (node != null && node.value != value) node = node.next;
		return node != null;
	}

}