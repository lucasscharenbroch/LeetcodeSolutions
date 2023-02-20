class Solution {
public:
    int nthUglyNumber(int n) {
        vector<int> ugly_numbers = {1};
        
        int two_ptr = 0, three_ptr = 0, five_ptr = 0; // points to the smallest number not multiplied
                                                      // by the two/three/five.
        
        while(ugly_numbers.size() < n) {
            // add the smallest ugly number that isn't already in ugly_numbers
            int two_product = ugly_numbers[two_ptr] * 2;
            int three_product = ugly_numbers[three_ptr] * 3;
            int five_product = ugly_numbers[five_ptr] * 5;
            
            int smallest = min(two_product, min(three_product, five_product));
            ugly_numbers.push_back(smallest);
            
            // note that the below may advance more than one pointer-
            // this is important to prevent repeats (e.g. 2*3 and 3*2).
            // (it will prevent all collisions, because the same products
            //  shoulld always be reached at the same time (by the greedy approach))
            if(smallest == two_product) two_ptr++;
            if(smallest == three_product) three_ptr++;
            if(smallest == five_product) five_ptr++;
        }
        
        return ugly_numbers[n - 1];
    }
};
