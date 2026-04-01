Welcome to the ultimate repository for mastering Java concurrency! This course takes a deep dive from the bare metal to the bleeding edge of Java 25.

Use this README to track your progress as you work through each lesson in your IDE.

---

## 🗺️ The Master Index

### Phase 0: The Prelude (History, OS & Hardware Core)
- [ ] **0.1 The "Why" of Concurrency:** Moore's law, the multicore revolution, and why single-core speed stalled.
- [ ] **0.2 Process vs. Thread:** OS scheduling, context switching overhead, and thread states at the OS level.
- [ ] **0.3 The Evolution of Java Threads:** From "Green Threads" to heavy OS-native 1:1 mapping.

### Phase 1: The Foundation & The JVM Internals
- [ ] **1.1 Thread Fundamentals:** Lifecycle, states, `Thread` vs. `Runnable`, and `ThreadFactory`.
- [ ] **1.2 Threads & JVM Memory:** Thread Stacks, the Heap, the Metaspace, and lock-free allocation via TLABs.
- [ ] **1.3 Thread Interruption:** Safely canceling threads and understanding the interrupt flag.
- [ ] **1.4 Memory Model & Visibility:** CPU caches (L1/L2/L3), cache coherence protocols (MESI), and the "Happens-Before" edge.
- [ ] **1.5 Memory Barriers (Fences):** The raw assembly instructions behind visibility (Load/Store fences) and the JVM's `Unsafe`/`VarHandle` mappings.
- [ ] **1.6 Synchronization Basics:** Intrinsic locks (`synchronized`), monitor objects, object headers (Mark Word), and deadlocks/livelocks/starvation.
- [ ] **1.7 JIT Concurrency Optimizations:** How Tiered Compilation applies Escape Analysis, Lock Elision, and Lock Coarsening to your code.
- [ ] **1.8 Wait/Notify Mechanism:** Inter-thread communication using `wait()`, `notify()`, and `notifyAll()`.

### Phase 2: The java.util.concurrent (JUC) Revolution
- [ ] **2.1 Explicit Locks:** `ReentrantLock`, `ReadWriteLock`, and `StampedLock`.
- [ ] **2.2 The Backbone (AQS):** Deconstructing `AbstractQueuedSynchronizer` and CLH lock queues.
- [ ] **2.3 The Executor Framework:** ThreadPools, `ExecutorService`, tuning pool sizes (Amdahl's Law), and rejection policies.
- [ ] **2.4 Algorithmic Guarantees:** Defining Lock-Free, Wait-Free, and Obstruction-Free programming.
- [ ] **2.5 Atomic Variables & CAS:** Lock-free programming with `AtomicInteger`, `LongAdder`, and `VarHandle`.
- [ ] **2.6 Synchronizer's:** `CountDownLatch`, `CyclicBarrier`, `Semaphore`, `Exchanger`, and `Phaser`.
- [ ] **2.7 Concurrent Collections & Internal DS:** Tearing apart `ConcurrentHashMap` (node routing, `KeySetView`), `CopyOnWriteArrayList`, and `BlockingQueue` internals.

### Phase 3: High-Performance, Reactive & Mechanical Sympathy
- [ ] **3.1 CompletableFuture:** Asynchronous pipelines, monads in Java, and non-blocking exception handling.
- [ ] **3.2 The Flow API (Reactive Streams):** Java 9's Publisher/Subscriber model and backpressure.
- [ ] **3.3 Fork/Join Framework:** Work-stealing algorithms (the engine behind Parallel Streams).
- [ ] **3.4 Extreme Performance (False Sharing):** CPU cache line invalidation, the `@Contended` annotation, and mechanical sympathy.
- [ ] **3.5 JVM Safe-point & Thread Pauses:** How Garbage Collection stops your threads, Time-To-Safe-point (TTSP), and uncounted loops.

### Phase 4: Modern Java (Project Loom & Beyond)
- [ ] **4.1 Virtual Threads (Java 21+):** Sub-millisecond context switching, continuations, and the massive shift to "Thread-per-request" scalability.
- [ ] **4.2 Structured Concurrency:** Managing families of sub-tasks with `StructuredTaskScope`.
- [ ] **4.3 Scoped Values:** A modern, memory-safe, and immutable alternative to `ThreadLocal` (and solving `ThreadLocal` memory leaks).
- [ ] **4.4 Java 25 Specifics:** The latest refinements in thread coordination, pinned virtual threads, and JVM-level loom optimizations.

### Phase 5: The Real World (Testing, Debugging & Profiling)
- [ ] **5.1 Thread Dumps & Analysis:** Capturing and reading dumps via `jstack` / `jcmd` to find deadlocks and bottlenecks.
- [ ] **5.2 Profiling Concurrency:** Using Java Flight Recorder (JFR) and VisualVM to monitor thread contention and lock profiling.
- [ ] **5.3 Stress Testing:** Mathematically proving your concurrent code doesn't break using `jcstress` (Java Concurrency Stress tool).