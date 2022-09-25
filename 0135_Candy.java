class Solution {
    public int candy(int[] ratings) {
        if(ratings.length == 1) return 1;
        
        int total = ratings.length; // pre-record 1-candy for each child
        int[] extraCandy = new int[ratings.length];
        ArrayList<int[]> stack = new ArrayList<>();
        
        for(int i = 0; i < ratings.length; i++) {
            int leftRating = (i == 0) ? Integer.MAX_VALUE : ratings[i - 1];
            int rightRating = (i == ratings.length - 1) ? Integer.MAX_VALUE : ratings[i + 1];
            
            System.out.printf("i: %d , ir: %d, lr %d rr %d\n", i, ratings[i], leftRating, rightRating);

            
            if(ratings[i] <= leftRating && ratings[i] <= rightRating) { // MINIMUM
                // no extra candy
            } else if(ratings[i] > leftRating && ratings[i] <= rightRating) { // UPWARD SLOPING
                total += extraCandy[i] = extraCandy[i - 1] + 1;
            } else if(ratings[i] > leftRating && ratings[i] > rightRating) { // MAXIMUM
                stack.add(new int[] {i, ratings[i - 1]}); // add max(r[i-1], r[i+1])
            } else { // downward sloping
                stack.add(new int[] {i, 0});
            }
        }
        
        while(!stack.isEmpty()) {
            int[] current = stack.remove(stack.size() - 1);
            System.out.println("current = " + current[0] + " " + current[1]);
            int numCandy = Math.max(current[1], extraCandy[current[0] + 1] + 1);
            total += numCandy;
            extraCandy[current[0]] = numCandy;
        }
        
        for(int i = 0; i < ratings.length; i++) {
            System.out.println(i + ": " + extraCandy[i]);
        }
        
        return total;
    }
}
