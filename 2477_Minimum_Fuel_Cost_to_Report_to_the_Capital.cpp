class Solution {
public:
    int seats;
    long long result;
    
    long long minimumFuelCost(vector<vector<int>>& roads, int seats) {
        result = 0;
        this->seats = seats;
        vector<vector<int>> cnx(roads.size() + 1);
        
        for(vector<int>& road : roads) {
            cnx[road[0]].push_back(road[1]);
            cnx[road[1]].push_back(road[0]);
        }
        
        dfs(cnx, 0, 0);
        
        return result;
    }
    
    int dfs(vector<vector<int>>& cnx, int current, int depth, int prev = -1) {
        int seatsLeft = 0; // seats left in all of the cars of neighbors (which are all
                           // coming through this city to the capital, since the graph is
                           // a tree). The cost of those seats to the capital is already recorded.
        for(int& neighbor : cnx[current]) {
            if(neighbor == prev) continue; // wrong direction (towards the capital)
            seatsLeft += dfs(cnx, neighbor, depth + 1, current);
        }
        
        if(seatsLeft == 0) { // need to take this city's car
            seatsLeft += seats;
            result += depth; // cost for this car to travel to the capital
        }
        
        seatsLeft--; // reserve current's seat
        
        // merge any cars
        result -= (seatsLeft / seats) * depth; // remove remaining distance for emptied cars
        seatsLeft %= seats;                    // remove seats from emptied cars
        
        return seatsLeft;
    }
};
