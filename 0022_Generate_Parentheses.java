// recursive solution
class Solution {
    public ArrayList<String> generateParenthesis(int n, int numUnclosed) {
        ArrayList<String> solutions = new ArrayList<>();
        
        if(n == 0 && numUnclosed == 0) { // base case
            
        } else if(n == 0) { // no more parens can be opened
            String closers = ""; 
            for(int i = 0; i < numUnclosed; i++) {
                closers += ")";
            }
            solutions.add(closers);
        } else if(numUnclosed == 0) { // a new paren must be opened
            solutions = generateParenthesis(n - 1, numUnclosed + 1);    
            for(int i = 0; i < solutions.size(); i++) {
                solutions.set(i, "(" + solutions.get(i));
            }
        } else { // a paren can be opened or closed
            ArrayList<String> solutionsIfOpened = generateParenthesis(n - 1, numUnclosed + 1);
            for(String s : solutionsIfOpened) {
                solutions.add("(" + s);
            }
            
            ArrayList<String> solutionsIfClosed = generateParenthesis(n, numUnclosed - 1);
            for(String s : solutionsIfClosed) {
                solutions.add(")" + s);
            }
        }
        
        return solutions;
    }
    
    public List<String> generateParenthesis(int n) {
        return generateParenthesis(n, 0);
    }
}

// backtracking (faster)
class Solution {
    ArrayList<String> solutions = new ArrayList<>();
    
    private void backtrack(String s, int n, int numUnclosed) {
        if(n == 0 && numUnclosed == 0) {
            solutions.add(s);
            return;
        }
        
        if(n != 0) {
            backtrack(s + "(", n - 1, numUnclosed + 1);
        }
        
        if(numUnclosed != 0) {
            backtrack(s + ")", n, numUnclosed - 1);    
        }
    }
    
    public List<String> generateParenthesis(int n) {
        backtrack("", n, 0);
        return solutions;
    }
}
