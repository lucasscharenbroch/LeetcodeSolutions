class Solution {
    public List<Integer> grayCode(int n) {
        if(n == 1) return Arrays.asList(0, 1); // base-case
        
        List<Integer> result = new ArrayList<>();
        List<Integer> subresult = grayCode(n - 1);
        
        // add each:  (0, grayCode(n - 1))
        // (0000 -> 0???) where only one of the ?'s is a 1 
        for(int i = 0; i < subresult.size(); i++) {
            result.add(0 + subresult.get(i)); // add leading 0 (i.e. leave the number unchanged)
        }
        
        // add each: (1, reverse(grayCode(n - 1)))
        // (1??? -> 1000) ??? matches ???, so 0??? differs by 1 from 1???, and 1000 differs by 1 from 0000
        for(int i = subresult.size() - 1; i >= 0; i--) {
            result.add((1 << (n - 1)) + subresult.get(i)); // add leading 1
        }
        
        return result;
    }
}
