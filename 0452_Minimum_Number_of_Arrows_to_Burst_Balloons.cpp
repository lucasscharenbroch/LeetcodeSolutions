class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        // greedy: all balloons must be popped, so shoot an arrow that pops the leftmost balloon,
        // along with as many consecutive balloons after that as possible
        
        sort(points.begin(), points.end(), [](vector<int>& a, vector<int>& b){ return a[0] < b[0]; });
        int result = 0;
        
        int i = 0;
        while(i < points.size()) {
            result++; // need an arrow the ith balloon
            
            // skip all other balloons popped by this arrow
            int endpoint = points[i][1];
            while(++i < points.size() && points[i][0] <= endpoint) endpoint = min(endpoint, points[i][1]);
        }
        
        return result;
    }
};
