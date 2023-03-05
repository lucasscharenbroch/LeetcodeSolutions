class Solution {
public:
    int minJumps(vector<int>& arr) {
        // view the array as an undirected graph: there are connections between
        // adjacent indices and matching elements.
        // BFS to find shortest path from 0 to n - 1.
        
        unordered_map<int, vector<int>> elem_list; // { element : indices with that element }
        
        for(int i = 0; i < arr.size(); i++) {
            elem_list[arr[i]].push_back(i);
        }
        
        // bfs from index 0
        queue<int> q;
        unordered_map<int, bool> vis;
        q.push(0);
        vis[0] = true;
        int breadth = 0;
        
        while(true) {
            int n = q.size();
            
            while(n--) {
                int current = q.front(); q.pop();
                if(current == arr.size() - 1) return breadth;
                
                for(int neigh : {current - 1, current + 1}) {
                    if(neigh < 0 || neigh >= arr.size() || vis[neigh]) continue;
                    vis[neigh] = true;
                    q.push(neigh);
                }
                
                for(int neigh : elem_list[arr[current]]) {
                    if(vis[neigh]) continue;
                    vis[neigh] = true;
                    q.push(neigh);
                }
                
                elem_list[arr[current]].clear(); // clear to prevent redundant edge-checks
            }
            
            breadth++;
        }
    }
};
