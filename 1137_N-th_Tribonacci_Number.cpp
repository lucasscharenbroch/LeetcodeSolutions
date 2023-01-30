class Solution {
public:
    int tribonacci(int n) {
        int tri[40];
        
        for(int i = 0; i <= n; i++) {
            if(i == 0) tri[i] = 0;
            else if(i <= 2) tri[i] = 1;
            else tri[i] = tri[i - 1] + tri[i - 2] + tri[i - 3];
        }
        
        return tri[n];
    }
};
