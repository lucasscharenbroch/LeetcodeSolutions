class Solution {
public:
    vector<int> getOrder(vector<vector<int>>& tasks) { 
        // queue of {processingTime, index} pairs
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
        
        for(int i = 0; i < tasks.size(); i++) tasks[i].push_back(i); // add index to end of each task vector
        sort(tasks.begin(), tasks.end(), [](auto& a, auto& b){ return a[0] < b[0]; }); // sort by enqueue time
        
        long long time = 0, tasksFinished = 0;
        int i = 0; // position in tasks (points to the first element that has not been enqueued)
        vector<int> order;
        
        while(tasksFinished < tasks.size()) {
            if(!q.empty()) {
                tasksFinished++;
                pair<int, int> current = q.top();
                q.pop();
                order.push_back(current.second);
                time += current.first;
            } else {
                // no task to do- wait until the next task is enqueued
                time = tasks[i][0];
            }
            
            // add new tasks to the queue
            while(i < tasks.size() && tasks[i][0] <= time) {
                q.push(pair<int, int> {tasks[i][1], tasks[i][2]});
                i++;
            }
        }
        
        return order;
    }
};
