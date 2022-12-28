typedef unsigned long long ull;

class Solution {
public:
    int minimizeSet(int d1, int d2, int c1, int c2) {
        return max({minSet(c1, d1), minSet(c2, d2), minSet(c1 + c2, d1, d2)});
    }
    
    // returns the min maximum value of the set that contains c positive integers that
    // aren't divisible by either d1 or d2 (if d2 is one, disregaurd it.)
    int minSet(ull c, ull d1, ull d2 = 1) {
        d1 = lcm(d1, d2);
        
        // find some x, where x - floor(x / d) == c
        //         ~> dx/d - x/d == c
        //         => (dx - x) / d == c
        //         => dx - x == cd
        //         => x(d - 1) == cd
        //         => x == (cd) / (d - 1)
        
        if(((c * d1) % (d1 - 1)) == 0) return (c * d1) / (d1 - 1) - 1; // divisibility edge-case
        else return (c * d1) / (d1 - 1);
    }
};
