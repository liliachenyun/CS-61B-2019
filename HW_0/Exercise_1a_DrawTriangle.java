public class Exercise_1a_DrawTriangle {
  public static void main (String[] args) {
    int x = 1, y = 1;
    while (x < 6) {
      while (y < x+1) {
        System.out.print ("*");
        y = y+1;
      }
      x = x+1;
      y = 1;
      System.out.println ("\n");
    }
  }
}