/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
// Convert to Array
class Solution {
public:
    bool isPalindrome(ListNode* head) {
        vector<int> list;
        for(; head != nullptr; head = head->next) list.push_back(head->val);
        
        for(int i = 0; i < list.size() / 2; i++) {
            if(list[i] != list[list.size() - i - 1]) return false;
        }
        
        return true;
    }
};

// Reverse Second Half
class Solution {
public:
    bool isPalindrome(ListNode* head) {
        // reverse the second half of the linked list, then compare the two halves
        if(head == nullptr || head->next == nullptr) return true;
        ListNode* slow = head, *fast = head;
        
        while(fast != nullptr && fast->next != nullptr) {
            slow = slow->next;
            fast = fast->next->next;
        }
        
        ListNode* firstHalf = head, *secondHalf = reverseLinkedList(slow);
        
        while(secondHalf != nullptr) {
            if(firstHalf->val != secondHalf->val) return false;
            firstHalf = firstHalf->next;
            secondHalf = secondHalf->next;
        }
        
        return true;
    }
    
    ListNode *reverseLinkedList(ListNode* head) {
        ListNode* newList = nullptr;
        
        while(head != nullptr) {
            ListNode* temp = head;
            head = head->next;
            temp->next = newList;
            newList = temp;
        }
        
        return newList;
    }
};
