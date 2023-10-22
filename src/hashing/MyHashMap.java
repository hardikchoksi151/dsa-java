package hashing;

import java.util.ArrayList;

public class MyHashMap<K, V> {

	private ArrayList<MapNode<K, V>> buckets;
	private int size;
	private int numBuckets;

	public MyHashMap() {
		numBuckets = 20;
		buckets = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			buckets.add(null);
		}
	}

	private int getBucketIndex(K key) {
		int hashCode = key.hashCode();
		return hashCode % numBuckets;
	}

	// returns the size of HashSet, or number of elements inserted so far.
	public int size() {
		return size;
	}

	// removes element based on key
	public V removeKey(K key) {

		int bucketIndex = getBucketIndex(key);
		MapNode<K, V> head = buckets.get(bucketIndex);
		MapNode<K, V> prev = null;

		while (head != null) {
			if (head.key.equals(key)) {
				// prev is null, means first entry in the bucket matches
				// so we have to remove ii and set head.next as first entry.
				if (prev == null) {
					buckets.set(bucketIndex, head.next);
				} else {
					prev.next = head.next;
					head.next = null;
				}
				size--;
				return head.val;
			}
			prev = head;
			head = head.next;
		}
		return null;
	}

	public V getValue(K key) {

		int bucketIndex = getBucketIndex(key);
		MapNode<K, V> head = buckets.get(bucketIndex);

		while (head != null) {
			if (head.key.equals(key)) {
				return head.val;
			}
			head = head.next;
		}

		return null;
	}

	public double loadFactor() {
		return (1.0 * size) / numBuckets;
	}

	private void rehash() {
		ArrayList<MapNode<K, V>> temp = buckets;
		buckets = new ArrayList<>();

		for (int i = 0; i < 2 * numBuckets; i++) {
			buckets.add(null);
		}

		size = 0;
		numBuckets *= 2;

		for (int i = 0; i < temp.size(); i++) {
			MapNode<K, V> head = temp.get(i);
			while (head != null) {
				K key = head.key;
				V val = head.val;
				insert(key, val);
				head = head.next;
			}
		}
	}

	public void insert(K key, V val) {
		int bucketIndex = getBucketIndex(key);
		MapNode<K, V> head = buckets.get(bucketIndex);

		// update if the key already exists
		while (head != null) {
			if (head.key.equals(key)) {
				head.val = val;
				return;
			}
			head = head.next;
		}

		// make a new entry and set it as first
		MapNode<K, V> newNode = new MapNode<>(key, val);
		head = buckets.get(bucketIndex);
		newNode.next = head;
		buckets.set(bucketIndex, newNode);
		size++;

		// compute the load factor
		double loadFactor = (1.0 * size) / numBuckets;
		if (loadFactor > 0.75) {
			rehash();
		}
	}
}
