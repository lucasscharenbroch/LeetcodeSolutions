/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

// Shift values, O(n)
class Solution {
public:
    void deleteNode(ListNode* node) {
        // shift values of nodes backwards along list
        for(; node->next->next != nullptr; node = node->next) node->val = node->next->val;
        node->val = node->next->val;
        node->next = nullptr;
    }
};

// Swap deleted node with next node, O(1)
class Solution {
public:
    void deleteNode(ListNode* node) {
        node->val = node->next->val;
        node->next = node->next->next;
    }
};
