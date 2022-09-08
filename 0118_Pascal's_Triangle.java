class Solution {
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if(numRows == 0) return result;
        
        result.add(Arrays.asList(1)); // add first row
        
        while(--numRows > 0) {
            ArrayList<Integer> nextRow = new ArrayList<>();
            nextRow.add(1); // add leading 1
            
            List<Integer> lastRow = result.get(result.size() - 1);
            for(int i = 0; i < lastRow.size() - 1; i++) {
                nextRow.add(lastRow.get(i) + lastRow.get(i + 1));
            }
            
            nextRow.add(1); // add trailing 1
            result.add(nextRow);
        }
        
        return result;
    }
}
