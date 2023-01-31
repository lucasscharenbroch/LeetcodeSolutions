class Solution {
public:
    int bestTeamScore(vector<int>& scores, vector<int>& ages) {
        int n = scores.size();
        int bestScore = 0;
        
        vector<pair<int, int>> agesAndScores(n);
        for(int i = 0; i < n; i++) agesAndScores[i] = {ages[i], scores[i]};
        sort(agesAndScores.begin(), agesAndScores.end());
        
        vector<int> dp(n, 0); // dp[i] holds the best score of a team composed player i and other players
                              // from [0, i - 1]
        
        for(int i = 0; i < n; i++) {
            int score = agesAndScores[i].second;
            
            // find dp[i]
            // find best team of players from [0, i - 1] that all have scores lower than i's score
            int bestTeam = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(agesAndScores[j].second <= score) bestTeam = max(bestTeam, dp[j]);
            }
            
            bestScore = max(bestScore, dp[i] = bestTeam + score);
        }
        
        return bestScore;
    }
};
