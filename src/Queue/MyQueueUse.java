package Queue;

public class MyQueueUse {
	public static void main(String[] args) throws QueueEmptyException {
		MyQueue i = new MyQueue(4);

		i.enqueue(3);
		i.enqueue(4);
		i.enqueue(1);
		i.enqueue(2);
		i.dequeue();
		i.dequeue();
		i.enqueue(3);
		i.enqueue(4);

		System.out.println(i.toString());

		while (!i.isEmpty()) {
			System.out.println(i.dequeue());
		}
	}
}
