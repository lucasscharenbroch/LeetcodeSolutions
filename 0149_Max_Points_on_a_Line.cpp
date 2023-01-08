class Solution {
public:
    // checks if the three given points are on the same line (check if ab x ac == 0)
    bool pointsOnSameLine(vector<int>& a, vector<int>& b, vector<int>& c) {
        return (a[1] - b[1]) * (a[0] - c[0]) - (a[0] - b[0]) * (a[1] - c[1]) == 0;
    }
    
    int maxPoints(vector<vector<int>>& points) {
        if(points.size() <= 2) return points.size();
            
        // for each line (choose any two points), check all other points for collision.
        
        int result = 2;
        
        for(int i = 0; i < points.size(); i++) {
            for(int j = i + 1; j < points.size(); j++) { // (i, j) forms a line
                int current = 2;
                
                for(int k = j + 1; k < points.size(); k++) {
                    if(pointsOnSameLine(points[i], points[j], points[k])) current++;
                }
                
                result = max(result, current);
            }
        }
        
        return result;
    }
};
