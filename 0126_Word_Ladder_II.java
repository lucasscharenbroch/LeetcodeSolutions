// "Tree" structure for recording ladders
class Solution {
    private class TreeNode {
        public String val;
        public TreeNode prev;
        
        TreeNode(String value) {
            val = value;
        }
        
        TreeNode(String value, TreeNode previous) {
            val = value;
            prev = previous;
        }
    }
    
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        HashSet<String> wordSet = new HashSet<>(wordList);
        LinkedList<String> wordQueue = new LinkedList<>();
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        ArrayList<TreeNode> validLadderNodes = new ArrayList<>();
        
        wordQueue.add(beginWord);
        nodeQueue.add(new TreeNode(beginWord));
        
        // BFS, forming a tree of visited nodes
        while(!wordQueue.isEmpty() && validLadderNodes.isEmpty()) {
            int size = wordQueue.size();
            
            while(size-- > 0) {
                String currentWord = wordQueue.remove();
                TreeNode currentNode = nodeQueue.remove();
                
                if(currentWord.equals(endWord)) {
                    validLadderNodes.add(currentNode);
                }
                
                wordSet.remove(currentWord);
                
                // for every string that differs by one
                for(int i = 0; i < currentWord.length(); i++) {
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(c == currentWord.charAt(i)) continue;
                        char[] possibleNeighborAsArr = currentWord.toCharArray();
                        possibleNeighborAsArr[i] = c;
                        String possibleNeighbor = String.valueOf(possibleNeighborAsArr);
                        if(wordSet.contains(possibleNeighbor)) {
                            wordQueue.add(possibleNeighbor);
                            nodeQueue.add(new TreeNode(possibleNeighbor, currentNode));
                        }
                    }
                }
            }
        }
        
        // form the solution array based on the correctly found tree nodes
        ArrayList<List<String>> result = new ArrayList<>();
        for(TreeNode ladderNode : validLadderNodes) {
            int i = result.size();
            result.add(new LinkedList<>());
            while(ladderNode != null) {
                result.get(i).add(0, ladderNode.val);
                ladderNode = ladderNode.prev;
            }
        }
        
        return result;
    }
}
