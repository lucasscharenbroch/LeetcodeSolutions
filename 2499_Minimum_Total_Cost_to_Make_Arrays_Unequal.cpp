class Solution {
public:
    long long minimumTotalCost(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> matchingElementIndices;
        unordered_map<int, int> elementCounts; // counts of element values that are matching
                                               // e.g. if nums1[i] = nums2[i] = 5, elementCounts[5]++
        int majorityElement;
        int majorityElementCount = 0;
        long long indexSum = 0;
        
        // record which indices have matching elements, and count the occurences of
        // the values of the matching elements
        for(int i = 0; i < nums1.size(); i++) {
            if(nums1[i] == nums2[i]) {
                matchingElementIndices.insert(i);
                elementCounts[nums1[i]]++;
                indexSum += i;
                if(elementCounts[nums1[i]] > majorityElementCount) {
                    majorityElement = nums1[i];
                    majorityElementCount = elementCounts[nums1[i]];
                }
            }
        }
        
        // if <= half of the matching elements are the same, they can be permutated
        // amongst themselves, costing the sum of their indices.
        if(majorityElementCount * 2 <= matchingElementIndices.size()) {
            return indexSum;
        }
        
        // otherwise, greedily add elements to this subsest, while
        // a.) minimizing the indexSum (by iterating forwards)
        // b.) not including the majority element (which is counterproductive)
        int subsetSize = matchingElementIndices.size();
        for(int i = 0; i < nums1.size(); i++) {
            if(matchingElementIndices.find(i) != matchingElementIndices.end()) continue; // matching elem
            if(nums1[i] == majorityElement || nums2[i] == majorityElement) continue;
            
            indexSum += i;
            subsetSize++;
            
            if(majorityElementCount * 2 <= subsetSize) return indexSum; // found a valid subset
        }
        
        return -1;
    }
};
