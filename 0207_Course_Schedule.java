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
