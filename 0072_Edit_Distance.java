// recursive (exponential time)
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.length() == 0) return word2.length();
        if(word2.length() == 0) return word1.length();
        
        if(word1.charAt(0) == word2.charAt(0)) 
            return minDistance(word1.substring(1), word2.substring(1));
        
        int insert = minDistance(word1, word2.substring(1));
        int delete = minDistance(word1.substring(1), word2);
        int replace = minDistance(word1.substring(1), word2.substring(1));
        
        return 1 + Math.min(Math.min(insert, delete), replace);
    }
}

// matrix (quadratic time)
class Solution {
    public int minDistance(String word1, String word2) {
        int numRows = word1.length() + 1;
        int numCols = word2.length() + 1;
        
        int[][] distances = new int[numRows][numCols];
        
        // fill row1 and col1 with respective costs (any empty string's distance from 
        // a non-empty string is the length of the non-empty string)
        for(int r = 1; r < numRows; r++) distances[r][0] = r;
        for(int c = 1; c < numCols; c++) distances[0][c] = c;
        
        // populate the rest of distances
        for(int r = 1; r < numRows; r++) {
            for(int c = 1; c < numCols; c++) {
                boolean charsMatch = word1.charAt(r - 1) == word2.charAt(c - 1);
                if(word1.charAt(r - 1) == word2.charAt(c - 1)) {
                    distances[r][c] = distances[r - 1][c - 1];
                } else {
                    int replace = distances[r - 1][c - 1];
                    int delete = distances[r - 1][c];
                    int insert = distances[r][c - 1];
                    distances[r][c] = Math.min(Math.min(replace, delete), insert) + 1;
                }
            }
        }
        
        return distances[numRows - 1][numCols - 1];
    }
}
