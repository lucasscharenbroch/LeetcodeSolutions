class Solution {
    private boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
    
    public List<List<String>> partition(String s) {
        Object[] resultAtIndex = new Object[s.length() + 1]; // Obj => List<List<String>>
        
        resultAtIndex[s.length()] = new ArrayList<List<String>>();
        ((List<List<String>>) resultAtIndex[s.length()]).add(new ArrayList<>());
        
        for(int i = s.length() - 1; i >= 0; i--) {
            List<List<String>> currentResult = new ArrayList<>();
            
            for(int j = i; j < s.length(); j++) {
                if(!isPalindrome(s, i, j)) continue;
                
                for(List<String> partitionEnd : (List<List<String>>) resultAtIndex[j + 1]) {
                    ArrayList<String> partition = new ArrayList<String>();
                    partition.add(s.substring(i, j + 1));
                    partition.addAll(partitionEnd);
                    currentResult.add(partition);
                }
            }
            resultAtIndex[i] = currentResult;
        }
        
        return (List<List<String>>) resultAtIndex[0];
    }
}
