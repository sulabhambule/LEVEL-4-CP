public class main1 {
  public static void main(String[] args) {
    Sum s1 = new Sum();
    int ans = s1.sum(2, 2);
    System.out.println(ans);
  }

}

class Sum {
  public int sum(int a, int b) {
    return a + b;
  }

  public int sum(int a, int b, int c) {
    return a + b + c;
  }
}
