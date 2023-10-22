package test;

public class ToStringDemo {
	public static void main(String[] args) {
		ToStringDemoGeneric<Integer> obj = new ToStringDemoGeneric<>();
		obj.setData(100);
		System.out.println(obj.toString());
	}
}	

class ToStringDemoGeneric<T>{
	private T data;
	public void setData(T data) {
		this.data = data;
	}
	public String toString() {
		return data.toString();
	}
}