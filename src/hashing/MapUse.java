package hashing;

public class MapUse {
	public static void main(String[] args) {
		MyHashMap<String, Integer> map = new MyHashMap<>();
		
		for (int i = 0; i < 20; i++) {
			map.insert("abc"+i, i + 1);
		}
		
		System.out.println("Size after Insertion: "+map.size());
		
		for (int i = 0; i < 20; i++) {
			System.out.println("abc"+i+" :"+map.getValue("abc"+i));
		}
		System.out.println("performing deletion...");
		map.removeKey("abc9");
		
		for (int i = 0; i < 20; i++) {
			System.out.println("abc"+i+" :"+map.getValue("abc"+i));
		}
		
		System.out.println("size after deletion: "+map.size());
		
	}
}
