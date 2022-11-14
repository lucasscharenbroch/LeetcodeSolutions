// BFS with indegree counts
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses]; // number of untaken prerequisites for each course
        // dependents: {prerequisite: list of courses that depend on it}
        HashMap<Integer, List<Integer>> dependents = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>(); // clases that can be taken
        
        // add each prerequisite to the dependents and calculate indegrees
        for(int[] pr : prerequisites) {
            int firstClass = pr[1];
            int secondClass = pr[0];
            
            if(dependents.get(firstClass) == null) dependents.put(firstClass, new ArrayList<>());
            dependents.get(firstClass).add(secondClass);
            indegrees[secondClass]++;
        }
        
        // find all nodes with zero indegrees
        for(int i = 0; i < numCourses; i++) {
            if(indegrees[i] == 0) {
                queue.add(i);
            }
        }
        
        int numCoursesTaken = 0;
        while(numCoursesTaken++ != numCourses) {
            if(queue.isEmpty()) return false;
            int current = queue.poll();
            if(dependents.get(current) == null) continue;
            for(int d : dependents.get(current)) {
                if(--indegrees[d] == 0) {
                    queue.add(d);
                }
            }
        }
        
        return true;
    }
}

// DFS cycle-detection
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // dependents: {prerequisite: list of courses that depend on it}
        HashMap<Integer, List<Integer>> dependents = new HashMap<>();
        
        // add each prerequisite to the dependents and calculate indegrees
        for(int[] pr : prerequisites) {
            int firstClass = pr[1];
            int secondClass = pr[0];
            
            if(dependents.get(firstClass) == null) dependents.put(firstClass, new ArrayList<>());
            dependents.get(firstClass).add(secondClass);
        }
        
        boolean[] vis = new boolean[numCourses]; // courses that have been reached (don't dfs again)
        boolean[] onStack = new boolean[numCourses]; // courses that are being explored (if one of these
                                                     // is accessible, a cycle has been detected)
        
        // search for cycle
        for(int i = 0; i < numCourses; i++) {
            if(!vis[i]) {
                if(isCycle(dependents, vis, onStack, i)) return false;
            }
        }
        
        return true;
    }
    
    private boolean isCycle(HashMap<Integer, List<Integer>> connections, boolean[] vis, 
                                                                         boolean[] onStack, int current) {
        vis[current] = true;
        onStack[current] = true;
        
        // dfs neighboring nodes, and return false if any of them are currently being visited
        if(connections.get(current) != null) {
            for(int neigh : connections.get(current)) {
                if(onStack[neigh] || (!vis[neigh] && isCycle(connections, vis, onStack, neigh))) return true;
            }
        }
        
        onStack[current] = false;
        
        return false;
    }
}
