# 🌲 Java Tree Structures — Binary Search Tree & Huffman Tree

This repository contains two classic data structure implementations in Java:

- 🔍 **Binary Search Tree (BST)**
- 🔡 **Huffman Encoding Tree**

Both were developed using the **Model-View-Controller (MVC)** architecture and **Object-Oriented Programming (OOP)** principles, using **Apache NetBeans** as the development environment.

---

## 📘 Binary Search Tree (BST)

### ✅ Features

- Generic implementation using Java Generics (`<T extends Comparable<T)>`)
- Insert and delete elements
- In-order, pre-order, post-order, and level-order (BFS) traversals
- Tree properties:
  - Height
  - Degree
  - Node level (depth)
- Search functionality (binary search)


![BST](https://github.com/user-attachments/assets/2e9e6801-50fc-4e2b-b8ab-c0dd2193de15)

### 🧠 Highlights

This BST implementation supports complete tree traversal methods, and can calculate:
- The **height** (longest path from root to leaf)
- The **degree** (max number of children per node — for binary trees, max is 2)
- The **level** (depth of a given value)

> The recursive logic ensures clean and readable code while still being efficient.

---

## 🔡 Huffman Tree

### ✅ Features

- Huffman tree generation from any input string
- Frequency analysis and priority queue for node construction
- Binary encoding generation per character
- Efficient data compression foundation

![Huffmann](https://github.com/user-attachments/assets/31895f12-20e1-4d80-9b2d-f1787d587578)


### 🧠 Highlights

The Huffman Tree construction follows the standard algorithm:
1. Compute character frequencies.
2. Create a min-heap of nodes.
3. Merge nodes based on frequency until one root node remains.
4. Recursively assign binary codes to each character.

The result is a **map of characters to Huffman codes**, optimized for minimum average code length — a foundational algorithm for data compression.

---

## 🧩 Technologies Used

- Java 17
- Apache NetBeans
- Maven
- MVC Design Pattern
- Java Collections (Map, List, Queue, PriorityQueue)

---

## 👥 Credits

Josh Sebastián López Murcia  
