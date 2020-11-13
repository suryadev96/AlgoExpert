class BST {

    int value;
    BST left;
    BST right;

    public BST(int value) {
        this.value = value;
    }

    public BST insert(int value) {
        if (value < this.value) {
            if (left == null) {
                BST newBST = new BST(value);
                left = newBST;
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                BST newBST = new BST(value);
                right = newBST;
            } else {
                right.insert(value);
            }
        }
        return this;
    }

    public boolean contains(int value) {
        if (value < this.value) {
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else if (value > this.value) {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        } else {
            return true;
        }
    }

    public BST remove(int value) {
        remove(value, null);
        return this;
    }

    public void remove(int value, BST parent) {
        if (value < this.value) {
            if (left != null) {
                left.remove(value, this);
            }
        } else if (value > this.value) {
            if (right != null) {
                right.remove(value, this);
            }
        } else {
            if (left != null && right != null) { //if left and right subtrees are present
                this.value = right.getMinValue();
                right.remove(this.value, this);
            } else if (parent == null) { //if only one subtree is present and parent node is missing
                if (left != null) {
                    this.value = left.value;
                    right = left.right;
                    left = left.left;
                } else if (right != null) {
                    this.value = right.value;
                    right = right.right;
                    left = right.left;
                } else {
                    //this is single node tree do nothing
                }
            } else if (parent.left == this) { //if only one subtree is present and parent node is present
                parent.left = left != null ? left : right;
            } else if (parent.right == this) {
                parent.right = left != null ? left : right;
            }
        }
    }

    public int getMinValue() {
        if (left == null) {
            return this.value;
        } else {
            return left.getMinValue();
        }
    }

}