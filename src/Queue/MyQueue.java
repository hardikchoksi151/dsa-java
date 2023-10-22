package Queue;

import java.util.Arrays;

public class MyQueue {
	private int[] data;
	private int front;
	private int rear;
	private int size;

	public MyQueue() {
		data = new int[10];
		front = -1;
		rear = -1;
		size = 0;
	}

	public MyQueue(int capacity) {
		data = new int[capacity];
		front = -1;
		rear = -1;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(int x) {
		if (size == data.length)
			doubleSize();

		if (size == 0)
			front = 0;

		size++;
		rear = (rear + 1) % data.length;
		data[rear] = x;
	}

	private void doubleSize() {
		int[] temp = data;
		data = new int[2 * temp.length];
		int index = 0;
		for (int i = front; i < temp.length; i++)
			data[index++] = temp[i];

		for (int i = 0; i < front; i++)
			data[index++] = temp[i];

		front = 0;
		rear = temp.length - 1;
	}

	public int dequeue() throws QueueEmptyException {
		if (size == 0)
			throw new QueueEmptyException();
		
		int temp = data[front];
		front = (front + 1) % data.length;
		size--;
		return temp;
	}

	public int front() throws QueueEmptyException {
		if (size == 0)
			throw new QueueEmptyException();

		return data[front];
	}

	@Override
	public String toString() {
		return "MyQueue [data=" + Arrays.toString(data) + ", front=" + front + ", rear=" + rear + ", size=" + size
				+ "]";
	}


}
