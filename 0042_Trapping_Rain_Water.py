from itertools import accumulate
from operator import sub

class Solution:
    def trap(self, height: List[int]) -> int:
        left = accumulate(height, max)
        right = reversed(list(accumulate(reversed(height), max)))
        boundaries = map(min, zip(left, right))
        water = map(lambda x: x[0] - x[1], zip(boundaries, height))
        return sum(water)
