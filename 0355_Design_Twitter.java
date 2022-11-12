class Twitter {
    class Tweet {
        public int tweetId;
        public int timestamp;
        
        private static int time = 0;
        
        Tweet(int id) {
            tweetId = id;
            timestamp = time++;
        }
    }
    
    HashMap<Integer, List<Tweet>> tweetsByUser = new HashMap<>();
    HashMap<Integer, List<Integer>> followingByUser = new HashMap<>();
    
    public void postTweet(int userId, int tweetId) {
        if(tweetsByUser.get(userId) == null) tweetsByUser.put(userId, new ArrayList<>());
        tweetsByUser.get(userId).add(new Tweet(tweetId));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        ArrayList<Integer> result = new ArrayList<>();
        // q will be used to sort candidates for feed; it will prioritize late timestamps
        PriorityQueue<Tweet> q = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);
        
        // add top 10 recent tweets of each followed person to q
        List<Integer> following = followingByUser.get(userId);
        if(following != null) {
            for(int followedUser : following) {
                if(followedUser == userId) continue; // don't double-count user's own tweets
                List<Tweet> tweets = tweetsByUser.get(followedUser);
                if(tweets == null) continue;

                for(int i = tweets.size() - 1; i >= 0 && i >= tweets.size() - 10; i--) {
                    q.add(tweets.get(i));
                }
            }
        }
        
        // add the user's own tweets
        List<Tweet> tweets = tweetsByUser.get(userId);
        if(tweets != null) {
            for(int i = tweets.size() - 1; i >= 0 && i >= tweets.size() - 10; i--) {
                q.add(tweets.get(i));
            }
        }

        // remove top 10 most recent tweets from q, and return them
        for(int i = 0; i < 10; i++) {
            if(q.isEmpty()) break;
            result.add(q.poll().tweetId);
        }
        
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        if(followingByUser.get(followerId) == null) followingByUser.put(followerId, new LinkedList<>());
        if(followingByUser.get(followerId).contains(followeeId)) return; // can't double-follow
        followingByUser.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(followingByUser.get(followerId) == null) return;
        followingByUser.get(followerId).remove((Object) followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
