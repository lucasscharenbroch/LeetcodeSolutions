class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses]; // prerequisite counts for each course
        // requisites[c] => {classes that can't be taken until c is completed}
        HashMap<Integer, List<Integer>> requisites = new HashMap<>();
        
        // fill requisites and indegrees
        for(int[] prereq : prerequisites) {
            int firstCourse = prereq[1]; // must be completed before secondCourse
            int secondCourse = prereq[0]; // must be completed after firstCourse
            
            if(requisites.get(firstCourse) == null) requisites.put(firstCourse, new ArrayList<>());
            requisites.get(firstCourse).add(secondCourse);
            
            indegrees[secondCourse]++;
        }
        
        LinkedList<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++) if(indegrees[i] == 0) q.add(i); // populate q with zero-indegree courses
        
        int numTaken = 0;
        int[] topOrder = new int[numCourses];
        
        while(!q.isEmpty()) {
            int current = q.poll();
            topOrder[numTaken++] = current;
            
            // decrement indegrees of courses of whom current is a requisite (removing their dependence on it)
            if(requisites.get(current) == null) continue;
            for(int course : requisites.get(current)) {
                if(--indegrees[course] == 0) q.add(course);
            }
        }
        
        return numTaken == numCourses ? topOrder : new int[0];
    }
}
