public class HelloNumbers {
	public static void main(String[] args) {
		int sum = 0;
        for (int i = 0; i < 10; i++ ) {
        	for (int x = 0; x < i + 1; x++){
        		sum += x;
        	}
            System.out.print(sum + " ");
            sum = 0;
        }
    }
}
