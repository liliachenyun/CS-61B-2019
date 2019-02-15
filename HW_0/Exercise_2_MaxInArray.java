public class Exercise_2_MaxInArray {
   public static int max(int[] Array) {
      int x = 0, y = 0;
      while (x<Array.length) {
         if (Array[x]>y) {
            y = Array[x];
         }
         x = x+1;
      }
      return y;
   }

   public static void main(String[] args) {
      int[] numbers = new int[] {9, 2, 15, 2, 22, 10, 6};
      System.out.println (max(numbers));
   }
}