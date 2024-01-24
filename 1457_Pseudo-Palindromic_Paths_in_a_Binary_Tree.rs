use std::collections::HashMap;

// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
// 
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    pub fn pseudo_palindromic_paths (root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut res = 0;
        let mut cnts = HashMap::new();
        
        fn traverse(res: &mut i32, cnts: &mut HashMap<i32, i32>, node: &Option<Rc<RefCell<TreeNode>>>, odd_cnts: i32) {
            match node {
                None => (),
                Some(node) => {
                    let node = (*node).borrow();
                    let mut cnt = cnts.entry(node.val).or_insert(0);
                    *cnt += 1;
                    let odd_cnts = odd_cnts + if *cnt % 2 == 1 { 1 } else { -1 };
                    
                    match (&node.left, &node.right) {
                        (None, None) => if odd_cnts <= 1 { *res += 1; }
                        (left@Some(_), None) => traverse(res, cnts, &left, odd_cnts),
                        (None, right@Some(_)) => traverse(res, cnts, &right, odd_cnts),
                        (left@Some(_), right@Some(_)) => {
                            traverse(res, cnts, &node.left, odd_cnts);
                            traverse(res, cnts, &node.right, odd_cnts);
                        },
                    }
                    
                    *cnts.entry(node.val).or_insert(0) -= 1;
                },
            }
        }
        
        traverse(&mut res, &mut cnts, &root, 0);
        
        res
    }
}
