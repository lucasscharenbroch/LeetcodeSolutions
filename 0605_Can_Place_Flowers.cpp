class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        for(int i = 0; i < flowerbed.size(); i++) {
            if(i != 0 && flowerbed[i - 1]) continue;
            if(flowerbed[i]) continue;
            if(i != flowerbed.size() - 1 && flowerbed[i + 1]) continue;
            flowerbed[i] = 1;
            n--;
        }
        
        return n <= 0;
    }
};
