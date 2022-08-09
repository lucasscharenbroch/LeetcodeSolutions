class Solution {
    
    // alphabetically sorts the characters of s, and returns the sorted string
    private String sortString(String s) {
        char[] strAsArr = s.toCharArray(); 
        Arrays.sort(strAsArr, 0, strAsArr.length);
        return new String(strAsArr);
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groups = new HashMap<>();
        
        // add each string to its respective group
        for(String str : strs) {
            String groupId = sortString(str);
            if(groups.get(groupId) == null) {
                groups.put(groupId, new ArrayList<String>());    
            }
            
            groups.get(groupId).add(str);
        }
        
        // convert groups to a List<List<Integer>>
        ArrayList<List<String>> result = new ArrayList<>();
        for(String group : groups.keySet()) {
            result.add(groups.get(group));    
        }
        
        return result;
    }
}
