class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Arrays.sort(hand);
        HashMap<Integer, Integer> cardCounts = new HashMap<>();
        
        // populate cardCounts
        for(int card : hand) {
            cardCounts.putIfAbsent(card, 0);
            cardCounts.put(card, cardCounts.get(card) + 1);
        }
        
        // greedily build groups
        for(int card : hand) {
            int numStraights = cardCounts.get(card); // num straights that must be formed with card as first card
            if(numStraights != 0) {
                for(int i = 0; i < groupSize; i++) {
                    if(cardCounts.computeIfAbsent(card + i, (k) -> 0) < numStraights) return false;
                    cardCounts.put(card + i, cardCounts.get(card + i) - numStraights);
                }
            }
        }
        
        return true;
    }
}
