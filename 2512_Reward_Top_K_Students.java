class Solution {
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        // create hash sets for positive and negative words
        HashSet<String> negative = new HashSet<>();
        HashSet<String> positive = new HashSet<>();
        for(String n : negative_feedback) negative.add(n);
        for(String p : positive_feedback) positive.add(p);
        
        HashMap<Integer, Integer> studentScores = new HashMap<>();
        
        for(int i = 0; i < report.length; i++) {
            String[] words = report[i].split(" ");
            int score = 0;
            for(String word : words) {
                if(positive.contains(word)) score += 3;
                else if(negative.contains(word)) score -= 1;
            }
            studentScores.put(student_id[i], score);
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < student_id.length; i++) result.add(student_id[i]);
        
        result.sort((a, b) ->  (studentScores.get(a) == studentScores.get(b) ? a - b : 
                                studentScores.get(b) - studentScores.get(a)));
        
        while(result.size() > k) result.remove(result.size() - 1);
        return result;
    }
}
