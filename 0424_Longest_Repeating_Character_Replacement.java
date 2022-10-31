// Binary-Search-The-Answer O(nlg(n))
class Solution {
    public boolean canUnify(String s, int k, int w) { // w = window size
        int[] charCounts = new int[26];
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> charCounts[b] - charCounts[a]);
        
        // build initial char counts
        for(int i = 0; i < w; i++) {
            int addedChar = s.charAt(i) - 'A';
            charCounts[addedChar]++;
            
            // update its position in the priority queue
            q.remove(addedChar);
            q.add(addedChar);
        }
        
        // slide window
        for(int i = 1; i <= s.length() - w; i++) {
            if(charCounts[q.peek()] >= w - k) return true;
            int removedChar = s.charAt(i - 1) - 'A';
            int addedChar = s.charAt(i + w - 1) - 'A';
            charCounts[removedChar]--;
            charCounts[addedChar]++;
            
            q.remove(addedChar);
            q.add(addedChar);
            q.remove(removedChar);
            q.add(removedChar);
        }
        
        return charCounts[q.peek()] >= w - k;
    }
  
    /*
        // array instead of queue
        private boolean canUnify(String s, int k, int w) { // w = window size
          int[] charCounts = new int[26];

          // build initial char counts
          for(int i = 0; i < w; i++) {
              int addedChar = s.charAt(i) - 'A';
              charCounts[addedChar]++;
          }

          // slide window
          for(int i = 1; i <= s.length() - w; i++) {
              if(arrayMax(charCounts) >= w - k) return true;
              int removedChar = s.charAt(i - 1) - 'A';
              int addedChar = s.charAt(i + w - 1) - 'A';
              charCounts[removedChar]--;
              charCounts[addedChar]++;
          }

          return arrayMax(charCounts) >= w - k;
      }
      */
  
    public int characterReplacement(String s, int k) {
        int low = 1; int high = s.length();
        
        while(low < high) {
            int mid = (low + high + 1) / 2;
            if(canUnify(s, k, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        
        return low;
    }
}

// Sliding-Window O(n)
class Solution {
    private int arrMax(int[] arr, int start, int end) {
        int max = Integer.MIN_VALUE;
        while(start != end) max = Math.max(max, arr[start++]);
        return max;
    }
    
    public int characterReplacement(String s, int k) {
        int left = 0; // inclusive
        int right = 1; // exclusive
        int[] counts = new int['Z' + 1];
        counts[s.charAt(0)]++;
        
        while(right < s.length()) {
            // if counts has less than k unpure characters, expand the window
            if((right - left) - k < arrMax(counts, 'A', 'Z')) {
                counts[s.charAt(right++)]++;
            } else { // counts has exactly k unpure characters
                if((right - left) - k == counts[s.charAt(right)]) { // expand the window if s[right] is pure
                    counts[s.charAt(right++)]++;
                } else { // otherwise, slide window
                    counts[s.charAt(left++)]--;
                    counts[s.charAt(right++)]++;
                }
            }
        }
        
        return right - left;
    }
}
