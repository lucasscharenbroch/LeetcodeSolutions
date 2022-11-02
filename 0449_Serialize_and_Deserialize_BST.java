// general solution with start and end symbols, works on any binary tree
public class Codec {
    final char START = (char) (10000 + 1);
    final char END = (char) (10000 + 2);

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return START + "" + END;
        return START + "" + (char) (root.val) + serialize(root.left) + serialize(root.right) + END;
    }
    
    final TreeNode NULL_PLACEHOLDER = new TreeNode(0); // used instead of null to decide where to insert later children
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        ArrayList<TreeNode> nodeStack = new ArrayList<>();
        
        for(int c = 0; c < data.length(); c++) {
            if(data.charAt(c) == START) {
                if(data.charAt(c + 1) == END) {
                    nodeStack.add(NULL_PLACEHOLDER);
                } else {
                    nodeStack.add(new TreeNode(data.charAt(++c)));
                }
            } else { // data.charAt(c) == END (the only numbers in data are immediately after START)
                TreeNode child = nodeStack.remove(nodeStack.size() - 1);
                if(child.left == NULL_PLACEHOLDER) child.left = null;
                if(child.right == NULL_PLACEHOLDER) child.right = null;
                if(nodeStack.isEmpty()) return (child == NULL_PLACEHOLDER) ? null : child;
                
                TreeNode parent = nodeStack.get(nodeStack.size() - 1);
                if(parent.left == null) parent.left = child;
                else parent.right = child;
            }
        }
        return null; // shouldn't be reached
    }
}

// solution with implied start and ends, more compact but only works on BSTs
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        return (char) root.val + serialize(root.left) + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    int current;
    public TreeNode deserialize(String data) {
        current = 0;
        return construct(data, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode construct(String data, int min, int max) {
        if(current == data.length() || (int) data.charAt(current) >= max || 
                                       (int) data.charAt(current) <= min) return null;
        
        TreeNode node = new TreeNode((int) data.charAt(current++));
        node.left = construct(data, min, node.val);
        node.right = construct(data, node.val, max);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
