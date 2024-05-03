class Solution:
    def maxScore(self, nums: List[int]) -> int:
        # scuffed solution for leetcode contest with Pranav
        
        xs = reversed(sorted(nums))
        sm = 0
        res = 0
        
        for x in xs:
            sm += x
            if sm > 0:
                res += 1
                
        return res
