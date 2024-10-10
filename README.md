# Multiprogramming Operating System

Welcome to the *Multiprogramming Operating System* project! 🎉 This project simulates a multiprogramming environment, implementing various phases of execution and management. Below, you'll find an overview of the three phases and their functionalities.

## Phases Overview

### Phase 1: Instruction Processing 🧩

*Description*: In this phase, the system processes instructions and handles basic memory operations. It reads instructions from an input file, executes them and writes results to an output file.

*Key Features*:
- *Instruction Register (IR)*: Stores the current instruction being executed.
- *Memory Management (M)*: A simulated memory array used for storing and manipulating data.
- *File Operations*: Manages reading from an input file and writing results to an output file.

### Phase 2: Memory and Job Management 🗃

*Description*: This phase builds on Phase 1 by integrating advanced memory management and job handling techniques. It includes dynamic memory allocation, error handling and job execution management to simulate a more realistic operating system environment.

*Key Features*:
- *Dynamic Memory Allocation*: Allocates memory dynamically based on job requirements, simulating page faults and memory allocation strategies.
- *Error Handling*: Detects and manages various errors, such as opcode errors, operand errors, and page faults, ensuring the system handles exceptions gracefully.
- *Job Execution Management*: Executes jobs based on instructions, including handling read and write operations, managing timers and tracking execution progress.
- *System Status Reporting*: Logs detailed information about system status, including timers, line counts and error codes, to provide insights into job execution and system performance.

## Getting Started

To get started with this project:
1. Clone the repository: git clone https://github.com/CodeCraftsman123/MultiProgramming-Operating-System.git
2. Compile and run the program

Happy coding! 🚀
