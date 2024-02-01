# README for COMP 352 Assignment 4: Implementation of a Map Using Hash Table

## Overview
This assignment involves implementing a map using a hash table, handling collisions through separate chaining, and exploring the map's performance in terms of hash table load factors.

## Class Implementations

### Class Entry
* **Purpose:** Represents `<Key, Value>` entry pairs in the hash map.
* **Key:** Integer type.
* **Value:** Type of your choice.
* **Methods:**
  * **Constructor:** Generates a new Entry object with a random integer key. The value can be either supplied or generated randomly.
  * **hashCode Override:** Override the Object class's compression function using strategies from the textbook section 10.2.1.

### Abstract Class AbsHashMap
* **Purpose:** Models a hash table without a concrete representation of the underlying data structure.
* **Constructor:** Accepts initial hash table capacity and uses `h(k) = k mod N` as the hash function.
* **Abstract Methods:**
  * `size()`: Returns the number of entries.
  * `isEmpty()`: Returns a boolean indicating if the map is empty.
  * `get(k)`: Returns the value associated with key `k`.
  * `put(k, v)`: Adds or replaces an entry.
  * `remove(k)`: Removes an entry by key.

### Class MyHashMap
* **Purpose:** Implements `AbsHashMap` using separate chaining for key collisions.
* **Implementation:** Can use Java’s ArrayList as buckets.
* **Output Information for `put(k, v)`:**
  * Size of the table.
  * Number of elements after processing.
  * Number of collision keys.
  * Number of items in the bucket storing `v`.
* **Method Performance:** Print the runtime for `get(k)`, `put(k, v)`, and `remove(k)`. Handle excessive run time with an exception.

### Class HashMapDriver
* **Purpose:** To test and validate the implementation of `MyHashMap`.
* **Methods:**
  * `validate()`: Performs functionality tests.
  * `experimentInterpret()`: Analyzes performance as load factor increases.

## Assignment Tasks
1. **Implement Classes:** Write `Entry`, `AbsHashMap`, `MyHashMap`, and `HashMapDriver` classes as specified.
2. **Performance Analysis:** Explore the time to run `put(k, v)` with varying load factors.

## Deliverables
* Written specifications for each implemented class including design decisions.
* Performance report with trial runs and analyses.
* Well-formatted and documented Java source code.
