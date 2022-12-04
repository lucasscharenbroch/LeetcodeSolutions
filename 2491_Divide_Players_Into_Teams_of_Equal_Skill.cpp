// Greedy sorting
class Solution {
public:
    long long dividePlayers(vector<int>& skill) {
        // most-skillfull must be paired with least-skillful, and 
        // second-most-skillfull to second-least-skillfull, and so on.
        
        sort(skill.begin(), skill.end());
        
        int skillPerTeam = skill[0] + skill[skill.size() - 1]; // must be the same, else return -1
        
        long long sumChemistry = 0;
        
        // verify that all teams have the same skill (skillPerTeam)
        for(int i = 0; i < skill.size() / 2; i++) {
            if(skill[i] + skill[skill.size() - i - 1] != skillPerTeam) return -1;
            sumChemistry += skill[i] * skill[skill.size() - i - 1];
        }
        
        return sumChemistry;
    }
};

// Greedy dp
class Solution {
public:
    long long dividePlayers(vector<int>& skill) {
        // since the max skill is 1,000, dp is more efficient than sorting. (it mirrors a counting sort)
        
        int dp[1000 + 1] = {0}; // dp[i] = number of players with skill i
        for(int& s : skill) dp[s]++;
        
        long long sumChemistry = 0;
        int skillPerTeam = -1;
        
        int i = 1; // pointer to the first teamate
        int j = 1000; // pointer to the second teamate
        
        for(int k = 0; k < skill.size() / 2; k++) {
            while(dp[i] == 0) i++;
            while(dp[j] == 0) j--;
            
            dp[i]--, dp[j]--;
            
            if(k == 0) skillPerTeam = i + j;
            if(i + j != skillPerTeam) return -1;
            sumChemistry += i * j;
        }
        
        return sumChemistry;
    }
};
