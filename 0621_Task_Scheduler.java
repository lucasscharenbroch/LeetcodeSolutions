// Heap, O(nlg(n))
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] taskCounts = new int[26];
        for(char t : tasks) taskCounts[t - 'A']++; // count number of each task
        
        PriorityQueue<Integer> unused = new PriorityQueue<>((a, b) -> b - a); // max-heap
        ArrayList<Integer> used = new ArrayList<>();
        int time = 0;
        
        for(int i = 0; i < 26; i++) { // add each task-count to unused
            if(taskCounts[i] != 0) unused.add(taskCounts[i]);
        }
        
        // while there are tasks remaining, greedily choose groups of (n + 1) unique task types,
        // (choose the tasks with the highest counts) and update time accordingly.
        while(!unused.isEmpty()) {
            int toRemove = n + 1; // there must be (n + 1) consecutive unique tasks before repeats.
            while(unused.size() != 0 && toRemove != 0) { // choose highest-demanded tasks first
                int newCount = unused.poll() - 1;
                if(newCount != 0) used.add(newCount); // move the tasks-counts to used
                time++;                               // so they aren't used again in the same group
                toRemove--;                           // of (n + 1) tasks
            }
            
            if(used.size() != 0 || unused.size() != 0) time += toRemove; // add necessary cooldown
                                                                         // if there are < (n + 1)
                                                                         // unique tasks
            
            // when (n + 1) group is counted, move the tasks back to unused
            while(used.size() != 0) unused.add(used.remove(used.size() - 1));
        }
        
        return time;
    }
}

// O(n)
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] taskCounts = new int[26];
        for(int t : tasks) taskCounts[t - 'A']++;
        Arrays.sort(taskCounts);
        
        int highestFrequency = taskCounts[25];
        // count how many tasks have highestFrequency.
        int i;
        for(i = 25; i >= 0 && taskCounts[i] == taskCounts[25]; i--);
        int numWithHighestFrequency = 25 - i;
        
        // count the number of tasks that don't have the highest frequency
        int totalOfRest = tasks.length - (numWithHighestFrequency * highestFrequency);
        
        // there are enough lower-frequency to fill the gaps, and no time is spent idle
        if(totalOfRest >= (n + 1 - numWithHighestFrequency) * (highestFrequency - 1))
            return tasks.length;
        else // the fastest time is bounded by the tasks with highest frequency
            return (n + 1) * (highestFrequency - 1) + numWithHighestFrequency;
    }
}
