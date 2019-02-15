
public class Exercise_1b_DrawTriangle {
   public static void DrawTriangle (int N){
      int x = 1, y = 1;
      while (x < N+1) {
         while (y < x+1) {
            System.out.print("*");
            y = y+1;
         }
            x = x+1;
            y = 1;
            System.out.println("\n");
      }
   }
   
   public static void main(String[] args) {
      DrawTriangle (10);
   }
}