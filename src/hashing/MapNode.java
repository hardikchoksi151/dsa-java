package hashing;

public class MapNode<K, V> {
	K key;
	V val;
	MapNode<K, V> next;

	public MapNode(K key, V val) {
		this.key = key;
		this.val = val;
	}
}