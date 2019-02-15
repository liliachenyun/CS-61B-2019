public class Exercise_4_WindowPosSum {
  public static void WindowPosSum(int[] a, int n) {
    int x = 0, y = 1;
    while (x < a.length) {
      if (a[x] < 0) {
        x = x+1;
        continue;
      }
      while (y < n+1) {
        if (x+y > a.length-1) {
          break;
          }
        a[x] = a[x]+a[x+y];
        y = y+1;
      }
      x = x+1;
      y = 1;
    }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    WindowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}