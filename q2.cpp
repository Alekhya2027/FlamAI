#include <vector>
#include <iostream>

class MyHashMap {
private:
    static const int SIZE = 10007; // A prime number for good distribution

    struct Node {
        int key, value;
        Node* next;
        Node(int k, int v, Node* n = nullptr) : key(k), value(v), next(n) {}
    };

    std::vector<Node*> buckets;

    int hash(int key) {
        return key % SIZE;
    }

    Node* find(Node* head, int key) {
        Node* prev = nullptr;
        Node* curr = head;
        while (curr && curr->key != key) {
            prev = curr;
            curr = curr->next;
        }
        return prev;
    }

public:
    MyHashMap() {
        buckets.resize(SIZE, nullptr);
    }

    void put(int key, int value) {
        int idx = hash(key);
        if (!buckets[idx]) {
            buckets[idx] = new Node(-1, -1); // dummy head
        }
        Node* prev = find(buckets[idx], key);
        if (prev->next) {
            prev->next->value = value; // update
        } else {
            prev->next = new Node(key, value); // insert
        }
    }

    int get(int key) {
        int idx = hash(key);
        if (!buckets[idx]) return -1;
        Node* prev = find(buckets[idx], key);
        if (!prev->next) return -1;
        return prev->next->value;
    }

    void remove(int key) {
        int idx = hash(key);
        if (!buckets[idx]) return;
        Node* prev = find(buckets[idx], key);
        Node* target = prev->next;
        if (target) {
            prev->next = target->next;
            delete target;
        }
    }

    ~MyHashMap() {
        for (auto head : buckets) {
            Node* curr = head;
            while (curr) {
                Node* next = curr->next;
                delete curr;
                curr = next;
            }
        }
    }
};


int main(){
    MyHashMap obj;
    obj.put(1, 10);
    obj.put(2, 20);
    std::cout << obj.get(1) << "\n"; // 10
    std::cout << obj.get(3) << "\n"; // -1
    obj.put(2, 30);
    std::cout << obj.get(2) << "\n"; // 30
    obj.remove(2);
    std::cout << obj.get(2) << "\n"; // -1

    return 0;

}