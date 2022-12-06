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
class Solution {
public:
    ListNode* oddEvenList(ListNode* head) {
        if(head == nullptr || head->next == nullptr || head->next->next == nullptr) return head;

        ListNode *odds_head = head;
        ListNode *evens_head = head->next;
        ListNode *odds_tail = odds_head, *evens_tail = evens_head;

        ListNode *current = head->next->next;

        while(current != nullptr) {
            odds_tail = odds_tail->next = current;
            if(current->next == nullptr) break;
            evens_tail = evens_tail->next = current->next;
            current = current->next->next;
        }
        
        odds_tail->next = evens_head;
        evens_tail->next = nullptr;
        return odds_head;
    }
};
