void main() {

  Runnable runnable = () -> {
    var threadName = Thread.currentThread().getName();
    IO.println("Hello from " + threadName);
  };

  Thread thread = new Thread(runnable, "Child Thread - 1");
  thread.start();

  var thread2 = new Thread(runnable, "Child Thread - 2");
  thread2.start();

}