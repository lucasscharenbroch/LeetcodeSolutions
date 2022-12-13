class DetectSquares {
public:
    static const int X_MAX = 1000, Y_MAX = 1000;
    int lattice[X_MAX + 1][Y_MAX + 1] = {0};
    
    void add(vector<int> point) {
        lattice[point[0]][point[1]]++;
    }
    
    int count(vector<int> point) {
        int x = point[0];
        int y = point[1];
        
        int result = 0;
        
        // for each point with the same x value
        for(int y1 = 0; y1 <= Y_MAX; y1++) {
            if(y1 == y) continue;
            
            int sideLength = abs(y1 - y);
            
            // add the total number of each of the two possible squares with points (x, y) and (x, y1)
            if(x - sideLength >= 0)
                result += lattice[x][y1] * lattice[x - sideLength][y] * lattice[x - sideLength][y1];
            if(x + sideLength <= X_MAX)
                result += lattice[x][y1] * lattice[x + sideLength][y] * lattice[x + sideLength][y1];
        }
        
        return result;
    }
};

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares* obj = new DetectSquares();
 * obj->add(point);
 * int param_2 = obj->count(point);
 */
