class Solution {
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        // combine profits and capital into a single vector
        vector<pair<int, int>> projects;
        for(int i = 0; i < profits.size(); i++) 
            projects.push_back(pair<int, int> {capital[i], profits[i]});
        
        // sort projects by entry price (capital)
        sort(projects.begin(), projects.end());
        
        // maintain a priority queue of profits of projects that can be
        // done with the given capital (cash)
        priority_queue<int> pq;
        
        int i = 0; // current index in projects.
        int cash = w;
        
        while(k--) {
            // add profit of projects whose start-capital <= cash
            for(; i < projects.size() && projects[i].first <= cash; i++) 
                pq.push(projects[i].second);
            
            if(pq.empty()) break;
            
            int current = pq.top(); pq.pop();
            if(current < 0) break; // best profit is negative
            cash += current;
        }
        
        return cash;
    }
};
