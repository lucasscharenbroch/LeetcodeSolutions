class Solution {
public:
    int bestClosingTime(string customers) {
        // count the penalty of keeping the shop open the entire day
        int currentPenalty = 0;
        for(int i = 0; i < customers.length(); i++) {
            if(customers[i] == 'N') currentPenalty++;
        }
        
        // iterate backwards, keeping count of penalty at optimalPosition
        int minPenalty = currentPenalty;
        int minPenaltyPosition = customers.length();
        for(int i = customers.length() - 1; i >= 0; i--) {
            if(customers[i] == 'Y') currentPenalty++;
            else currentPenalty--;
            
            if(currentPenalty <= minPenalty) {
                minPenalty = currentPenalty;
                minPenaltyPosition = i;
            }
        }
        
        return minPenaltyPosition;
    }
};
