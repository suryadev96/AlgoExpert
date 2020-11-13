import java.util.HashMap;
import java.util.Map;

/*
Start by finding the two input descendants depth. if one of them is deeper, iterate up through its ancestors until u reach the 
depth of the higher descendant. then iterate through both descendant ancestors in tandem until u find the first common ancestor
*/
class AncestralNode {

    char name;
    AncestralNode ancestor; //nothing but a parent

    public AncestralNode(char name) {
        this.name = name;
        this.ancestor = null;
    }

    //descendants here is nothing but children
    public void addAsAncestor(AncestralNode[] descendants) {
        for (AncestralNode descendant : descendants) {
            descendant.ancestor = this;
        }
    }
}

class Question {

    public static AncestralNode getYoungestCommonAncestor(AncestralNode topAncestor, AncestralNode descendantOne, AncestralNode descendantTwo) {
        int depthOne = getDescendantDepth(descendantOne, topAncestor);
        int depthTwo = getDescendantDepth(descendantTwo, topAncestor);
        if (depthOne > depthTwo) {
            return backtrackAncestralTree(descendantOne, descendantTwo, depthOne - depthTwo);
        } else {
            return backtrackAncestralTree(descendantTwo, descendantOne, depthTwo - depthOne);
        }
    }

    public static AncestralNode backtrackAncestralTree(AncestralNode lowerDescendant, AncestralNode higherDescendant, int diff) {
        while (diff > 0) {
            lowerDescendant = lowerDescendant.ancestor;
            diff--;
        }
        while (lowerDescendant != higherDescendant) {
            lowerDescendant = lowerDescendant.ancestor;
            higherDescendant = higherDescendant.ancestor;
        }
        return lowerDescendant;
    }

    public static int getDescendantDepth(AncestralNode descendant, AncestralNode topAncestor) {
        int depth = 0;
        while (descendant != topAncestor) {
            depth++;
            descendant = descendant.ancestor;
        }
        return depth;
    }

    public static void main(String[] args) {

        Map<Character, AncestralNode> ancestralNodes = new HashMap<Character, AncestralNode>();

        String alphabet = "ABCDEFGHI";
        for (char a : alphabet.toCharArray()) {
            ancestralNodes.put(a, new AncestralNode(a));
        }

        //A is ancestor for B,C
        ancestralNodes.get('A').addAsAncestor(new AncestralNode[]{ancestralNodes.get('B'), ancestralNodes.get('C')});
        //B is ancestor of D E
        ancestralNodes.get('B').addAsAncestor(new AncestralNode[]{ancestralNodes.get('D'), ancestralNodes.get('E')});
        //C is ancestor for F G
        ancestralNodes.get('C').addAsAncestor(new AncestralNode[]{ancestralNodes.get('F'), ancestralNodes.get('G')});
        //D is ancestor for H I
        ancestralNodes.get('D').addAsAncestor(new AncestralNode[]{ancestralNodes.get('H'), ancestralNodes.get('I')});

        AncestralNode topAncestor = ancestralNodes.get('A');
        AncestralNode descendantOne = ancestralNodes.get('E');
        AncestralNode descendantTwo = ancestralNodes.get('I');

        AncestralNode lca = getYoungestCommonAncestor(topAncestor, descendantOne, descendantTwo);
        System.out.println(lca.name);
    }
}
/*
B
*/