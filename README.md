There were 4 questions in total in which we can choose three 
I did the Android-assignment1 questions 1,2,3

 

#  Custom Data Structures in C++

This repository contains implementations of two commonly used data structures from scratch in C++, without relying on STL container classes like `unordered_map` or `map` for internal logic.

 

##  Contents

1. [`LRUCache`](#1-lrucache)
2. [`MyHashMap`](#2-myhashmap)

 

## 1. `LRUCache`

###  Description

Implements an **LRU (Least Recently Used) Cache** that supports the following operations in **O(1)** time:

* `get(key)` – Returns the value for the given key if present, otherwise returns `-1`.
* `put(key, value)` – Inserts or updates the key-value pair. If the cache exceeds its capacity, the **least recently used** item is evicted.

###  Internal Design

* Uses a **doubly linked list** to maintain order of use (most recently used at the front).
* Uses a **hash map** for O(1) access to list nodes by key.

###  Example

```cpp
LRUCache lru(2);
lru.put(1, 1);
lru.put(2, 2);
lru.get(1);    // returns 1
lru.put(3, 3); // evicts key 2
lru.get(2);    // returns -1 (not found)
lru.put(4, 4); // evicts key 1
lru.get(1);    // returns -1
lru.get(3);    // returns 3
lru.get(4);    // returns 4
```

###  File Structure

* `LRUCache.h / LRUCache.cpp` – Contains class definition and implementation.

 

## 2. `MyHashMap`

###  Description

Implements a simplified version of a **HashMap** (similar to `unordered_map`) supporting:

* `put(key, value)` – Inserts or updates the value.
* `get(key)` – Returns the value for the given key, or `-1` if not present.
* `remove(key)` – Removes the key-value pair if it exists.

###  Internal Design

* Uses an array of buckets (`vector<list<pair<int, int>>>`) for **separate chaining** to resolve collisions.
* A simple **modulus-based hash function** is used for bucketing.

### Constraints

* Key and value are integers in the range `[0, 10^6]`.
* Max operations: `10^5`

###  Example

```cpp
MyHashMap obj;
obj.put(1, 10);
obj.put(2, 20);
obj.get(1);     // returns 10
obj.get(3);     // returns -1
obj.put(2, 30); // updates value
obj.get(2);     // returns 30
obj.remove(2);
obj.get(2);     // returns -1
```

###  File Structure

* `MyHashMap.h / MyHashMap.cpp` – Contains class definition and implementation.

 

##  Build & Run

Compile with any standard C++ compiler (C++11 or above):

```bash
g++ -std=c++11 q1.cpp -o lru
./lru

g++ -std=c++11 q2.cpp -o hashmap
./hashmap
```

> You can also use online compilers like [godbolt.org](https://godbolt.org) or run tests via a main function provided in each file.

 


 

Let me know if you also need:

* A sample `main.cpp` to test both.
* Unit tests using `assert()`.
* Separate README files instead of one.

For the 3rd question we created an app that fetches weather from a Fake API 

 

#  Book Review MVP App – Java (MVVM Architecture)

This project is a **Minimum Viable Product (MVP)** version of a **Book Review Android App**, implemented entirely in **Java** using the **MVVM architectural pattern**. It enables users to **browse books, view details**, and **save favorites for offline access**.

 

##  Project Overview

* **Architecture**: MVVM (Model-View-ViewModel)
* **Networking**: Retrofit
* **Persistence**: Room (SQLite)
* **Language**: Java (No Kotlin)
* **Offline Support**: Available via local database
* **UI Updates**: LiveData used for reactive programming

 

##  Files Submitted

### 1. `Book.java`

Represents the **data model** for a book.

* Annotated with `@Entity` for Room.
* Fields: `id`, `title`, `author`, `description`, `rating`, `thumbnailUrl`.
* Implements `Serializable` for easy passing between Activities.

> Used in both local storage (Room) and UI display.

 

### 2. `BookViewModel.java`

Acts as the **ViewModel** in the MVVM architecture.

* Communicates with `BookRepository` to fetch books from the API and database.
* Holds LiveData for:

  * `bookList`: All books from the API
  * `favoriteBooks`: Locally saved favorites
* Exposes methods to:

  * Fetch book list
  * Save a book as favorite
  * Retrieve favorites

> Maintains UI state and handles business logic separation.

 

### 3. `BookRepository.java`

Handles **data operations** by abstracting the data sources.

* Talks to:

  * `BookApiService` (Retrofit) – For remote book data
  * `BookDao` (Room) – For local database operations
* Performs API calls and Room transactions on background threads using `Executors`.
* Implements both `getBooksFromApi()` and `getFavoriteBooks()`.

> Follows the Repository pattern to isolate data layer logic from the rest of the app.

 

##  How to Run

1. Clone this repository.
2. Add it to an Android Studio project.
3. Add your fake API endpoint or local JSON file.
4. Build and run on an Android device or emulator.

 

##  Notes

* No external image loading libraries (placeholders used instead).
* MVVM clean separation: **UI ↔ ViewModel ↔ Repository ↔ API/DB**.
* Prepared for offline viewing via Room DB.

 

##  Future Enhancements

* Add paging support for large book lists.
* Use WorkManager for background syncing.
* Add real image loading (e.g., Glide) if allowed.




