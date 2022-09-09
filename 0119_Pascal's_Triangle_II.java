class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<>(rowIndex + 1);
        
        result.add(1);
        if(rowIndex == 0) return result;
        result.add(1);
        
        for(int k = 1; k < rowIndex; k++) { // for each level of the triangle
            result.add(1); // add trailing 1
            for(int i = k; i > 0; i--) { // iterate backwards through the level, doing additions
                result.set(i, result.get(i) + result.get(i -1));
            }
        }
        
        return result;
    }
}
