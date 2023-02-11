class Solution {
public:
    vector<int> shortestAlternatingPaths(int n, vector<vector<int>>& red_edges, vector<vector<int>>& blue_edges) {
        vector<int> result(n, -1);
        queue<int> red_q, blue_q;
        vector<bool> red_vis(n, false), blue_vis(n, false);
        vector<vector<int>> red_cnx(n), blue_cnx(n);
        
        // populate graph
        for(vector<int>& edge : red_edges) {
            red_cnx[edge[0]].push_back(edge[1]);
        }
        for(vector<int>& edge : blue_edges) {
            blue_cnx[edge[0]].push_back(edge[1]);
        }
        
        // set up BFS
        red_vis[0] = true, blue_vis[0] = true;
        red_q.push(0), blue_q.push(0);
        result[0] = 0;
        int breadth = 0;
        
        while(!red_q.empty() || !blue_q.empty()) {
            int red_sz = red_q.size(), blue_sz = blue_q.size();
            breadth++;
            
            for(; red_sz != 0; red_sz--) {
                int curr = red_q.front(); red_q.pop();
                
                for(int& neigh : red_cnx[curr]) {
                    if(blue_vis[neigh]) continue;
                    blue_vis[neigh] = true;
                    blue_q.push(neigh);
                    if(result[neigh] == -1) result[neigh] = breadth;
                }
            }
            
            for(; blue_sz != 0; blue_sz--) {
                int curr = blue_q.front(); blue_q.pop();
                
                for(int& neigh : blue_cnx[curr]) {
                    if(red_vis[neigh]) continue;
                    red_vis[neigh] = true;
                    red_q.push(neigh);
                    if(result[neigh] == -1) result[neigh] = breadth;
                }
            }
        }
        
        return result;
    }
};
