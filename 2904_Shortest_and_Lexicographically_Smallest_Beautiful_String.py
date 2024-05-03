class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        # scuffed solution for leetcode contest with Pranav
        N = len(s)
        ss = []
        
        for i in range(N):
            for j in range(i+1, N+1):
                x = s[i:j]
                if(x.count("1") == k):
                    ss.append(x)
                    
        if len(ss) == 0:
            return ""
                    
        mn = min(map(lambda s: len(s), ss))
        ss = list(filter(lambda s: len(s) <= mn, ss))
        
        return sorted(ss)[0]
