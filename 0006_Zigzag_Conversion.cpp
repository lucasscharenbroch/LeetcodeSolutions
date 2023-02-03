class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows == 1) return s;
        
        vector<string> rows(numRows, "");
        
        bool goingDown = true;
        int currentRow = 0;
        
        for(char& c : s) {
            rows[currentRow] += c;
            
            if(goingDown && currentRow == numRows - 1) goingDown = false;
            else if(!goingDown && currentRow == 0) goingDown = true;
            currentRow += goingDown ? 1 : -1;
        }
        
        string result = "";
        for(int i = 0; i < numRows; i++) result += rows[i];
        return result;
    }
};
