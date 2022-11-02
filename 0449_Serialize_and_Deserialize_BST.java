/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
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

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
