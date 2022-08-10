class Solution {
    public int totalNQueens(int n) {
        this.n = n;
        
        // initialize territory arrays
        columnClaims = new boolean[n];
        upDiagClaims = new boolean[n * 2];
        downDiagClaims = new boolean[n * 2];
        
        backtrack(0);
        return numSolutions;
    }
    
    // globals
    private int n;
    private int numSolutions = 0;
    private boolean[] columnClaims, upDiagClaims, downDiagClaims; // keep track of queens' territories
    
    void backtrack(int r) {
        if(r == n) return;        
        
        for(int c = 0; c < n; c++) {
            int upDiag = r + c; // the index of (r,c)'s upward-sloping diagonal
            int downDiag = r - c + n; // the index of (r,c)'s downward-sloping diagonal
            
            // continue if territory is not free
            if(columnClaims[c] || upDiagClaims[upDiag] || downDiagClaims[downDiag]) continue; 

            if(r == n - 1) {
                numSolutions++;
                return;
            }

            // claim territory     
            columnClaims[c] = true;
            upDiagClaims[upDiag] = true;
            downDiagClaims[downDiag] = true;
            
            // recurse
            backtrack(r + 1);
            
            // unclaim territory
            columnClaims[c] = false;
            upDiagClaims[upDiag] = false;
            downDiagClaims[downDiag] = false;
        }
    }
}
