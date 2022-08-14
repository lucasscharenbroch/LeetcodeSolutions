class Solution {
    // calculates n!
    private int factorial(int n) {
        if(n == 1) return 1;
        else return n * factorial(n - 1);
    }
    
    
    LinkedList<Integer> permutation = new LinkedList<>();
    
    // sets permutation to the kth permutation of 1...n, where (k = 0) => (1...n) 
    private void permuteRest(int n, int k) {
        if(k == 0) {
            for(int i = 0; i < n; i++) permutation.add(i + 1);
        } else {
            int lastFactorial = factorial(n - 1);
            permuteRest(n - 1, k % lastFactorial);
            int firstElement = (k / lastFactorial) + 1;
            
            // increment every element that is >= firstElement
            for(int i = 0; i < permutation.size(); i++) {
                if(permutation.get(i) >= firstElement) permutation.set(i, permutation.get(i) + 1);
            }
            
            permutation.add(0, firstElement); // insert firstElement at beginning of permutation
        }
    }
    
    public String getPermutation(int n, int k) {
        permuteRest(n, k - 1); // k = 1 -> k = 0
        
        // convert permutation to string
        String result = new String();
        for(int i = 0; i < permutation.size(); i++) result += permutation.get(i).toString();
        
        return result;
    }
}
