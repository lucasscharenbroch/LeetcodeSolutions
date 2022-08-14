class Solution {
    private int calculateFactorial(int n) {
        if(n <= 1) return 1;    
        else return n * calculateFactorial(n - 1);
    }
    
    LinkedList<Integer> permutation = new LinkedList<>();
    
    private void findRest(int n, int k) {
        int factorial = calculateFactorial(n - 1);
        if(k == 1) {
            for(int i = 1; i <= n; i++) permutation.add(i); // add 1...n to permutation
        } else if(k == factorial * n) {
            for(int i = n; i >= 1; i--) permutation.add(i); // add n...1 to permutation
        } else {
            findRest(n - 1, (k % factorial == 0 ? factorial : k % factorial)); // calculate the rest of the permation
            int firstElement = ((k - 1) / factorial) + 1;
            permutation.add(0, firstElement);
            // scale the rest of the permutation up
            for(int i = 1; i < permutation.size(); i++) {
                // increment all elements >= permutation[0]
                if(permutation.get(i) >= firstElement) permutation.set(i, permutation.get(i) + 1);
            }
        }
    }
    
    public String getPermutation(int n, int k) {
        findRest(n, k);
        String result = "";
        
        // convert permutation to a string
        for(int i = 0; i < permutation.size(); i++) result += permutation.get(i).toString();
        
        return result;
    }
}
