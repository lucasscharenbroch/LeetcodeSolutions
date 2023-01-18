class Solution {
public:
    // draws the skyline based on the buildings that end before %end%
    void processEndpoints(map<int, int>& endpoints, vector<vector<int>>& result, int end) {
        while(endpoints.rbegin()->second < end) { // while the largest building ends before %end%
            // remove largest from endpoints
            pair<int, int> largest = *endpoints.rbegin();

            // erase all smaller endpoints that end before/in the same place
            while(endpoints.rbegin()->second <= largest.second) endpoints.erase(--endpoints.end());
            
            // draw the old largest endpoint as the new largest height
            result.push_back({largest.second, endpoints.rbegin()->first});
        }
    }
    
    vector<vector<int>> getSkyline(vector<vector<int>>& buildings) {
        map<int, int> endpoints; // contains the endpoints of seen buildings, {height, end-index}
        vector<vector<int>> result;
        
        endpoints[0] = INT_MAX; // ground
        
        for(vector<int>& building : buildings) {
            int s = building[0], e = building[1], h = building[2];
            
            processEndpoints(endpoints, result, s);
            
            // add building to endpoints
            bool isBuildingWithSameHeight = endpoints.find(h) != endpoints.end();
            if(isBuildingWithSameHeight) e = max(e, endpoints[h]);
            endpoints[h] = e;
            
            // draw building if it breaks the skyline (it is the largest)
            if(endpoints.rbegin()->first == h && !isBuildingWithSameHeight) {
                if(!result.empty() && result[result.size() - 1][0] == s) // overwrite buildings w/ same
                    result[result.size() - 1][1] = h;                    // start and smaller height
                else result.push_back({s, h});
            }
        }
        
        processEndpoints(endpoints, result, INT_MAX);
        if(result[result.size() - 1][1] != 0) result.push_back({INT_MAX, 0});
        
        return result;
    }
};
