class MedianFinder {
    class AVLNode {
        AVLNode left, right, parent;
        int value, height, size;
        
        // ~ ~ ~ ~ ~ ~ ~ ~ Instance Methods ~ ~ ~ ~ ~ ~ ~ ~ 
        
        public AVLNode(AVLNode parent, int value) {
            this.parent = parent;
            this.value = value;
            height = size = 1;
        }
        
        // updates the height based on its children
        public void updateHeightAndSize() {
            this.height = 1 + Math.max(height(left), height(right));
            this.size = 1 + size(left) + size(right);
        }
        
        // returns the value of the Nth smallest node in the tree (0 = smallest)
        public int findNth(int n) {
            if(n == size(this.left)) return this.value;
            if(n < size(this.left)) {
                return left.findNth(n);
            } else {
                return right.findNth(n - size(this.left) - 1);
            }
        }
        
        public AVLNode rightRotate() {
            /*
                     |            |
                     y            x
                    / \          / \
                   x   C   ->   A   y 
                  / \              / \
                 A   B            B   C
                 
                 A stays connected to x,
                 C stays connected to y
            */
            
            AVLNode y = this; // y cannot be null
            AVLNode x = this.left; // x cannot be null
            AVLNode B = x.right;  // B might be null
            
            // swap x and y's positions
            if(y.parent != null) {
                if(y.parent.left == y) y.parent.left = x;
                else y.parent.right = x;
            }
            
            x.parent = y.parent;
            y.parent = x;
            x.right = y;
            
            // put B into place
            y.left = B;
            if(B != null) B.parent = y;
            
            // update heights
            y.updateHeightAndSize();
            x.updateHeightAndSize();
            
            return x; // return new "root"
        }
        
        public AVLNode leftRotate() {
            /*
                     |            |
                     x            y
                    / \          / \
                   A   y   ->   x   C 
                      / \      / \
                     B   C    A   B
                 
                 A stays connected to x,
                 C stays connected to y
            */
            
            AVLNode x = this; // x cannot be null
            AVLNode y = this.right; // y cannot be null
            AVLNode B = y.left;  // B might be null
            
            // swap x and y's positions
            if(x.parent != null) {
                if(x.parent.left == x) x.parent.left = y;
                else x.parent.right = y;
            }
            
            y.parent = x.parent;
            x.parent = y;
            y.left = x;
            
            // put B into place
            x.right = B;
            if(B != null) B.parent = x;
            
            // update heights
            x.updateHeightAndSize();
            y.updateHeightAndSize();
            
            return y; // return new "root"
        }
        
        public AVLNode insert(int num) {
            AVLNode insertedNode;
            { // regular bst insert
                AVLNode current = this;
                while(true) {
                    if(current.value > num) {
                        if(current.left == null) break;
                        else current = current.left;
                    } else {
                        if(current.right == null) break;
                        else current = current.right;
                    }
                }
                
                if(current.value > num) current.left = insertedNode = new AVLNode(current, num);
                else current.right = insertedNode = new AVLNode(current, num);
            }
            { // fix avl property, heights, and sizes
                AVLNode current = insertedNode;
                while(current != null) {
                    current.updateHeightAndSize();
                    int lHeight = height(current.left);
                    int rHeight = height(current.right);
                    
                    if(lHeight == rHeight + 2) { // doubly left heavy
                        int llHeight = height(current.left.left);
                        int lrHeight = height(current.left.right);
                        
                        if(lrHeight > llHeight) {
                            current.left.leftRotate();
                        }
                        current = current.rightRotate();
                        
                    } else if(lHeight + 2 == rHeight) { // doubly right heavy
                        int rrHeight = height(current.right.right);
                        int rlHeight = height(current.right.left);
                        
                        if(rlHeight > rrHeight) {
                            current.right.rightRotate();
                        }
                        current = current.leftRotate();
                        
                    }
                    
                    if(current.parent == null) return current;
                    current = current.parent;
                }
            }
            return null; // this line shouldn't be reached
        }
        
        // ~ ~ ~ ~ ~ ~ ~ ~ Static Methods ~ ~ ~ ~ ~ ~ ~ ~ 
        
        public static int height(AVLNode n) {
            return (n == null) ? 0 : n.height;
        }
        
        public static int size(AVLNode n) {
            return (n == null) ? 0 : n.size;
        }
    }
    
    AVLNode tree;

    public MedianFinder() {}
    
    public void addNum(int num) {
        if(tree == null) {
            tree = new AVLNode(null, num);
        } else {
            tree = tree.insert(num);
        }
    }
    
    public double findMedian() {
        int size = tree.size;
        
        if(size % 2 == 0) { // average of two middle
            return (tree.findNth(size / 2) + tree.findNth(size / 2 - 1)) / 2.0;
        } else { // single middle element
            return (double) tree.findNth(size / 2);
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
