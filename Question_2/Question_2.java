import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //ex 입력 값
        int sum = 4;
        int[] coins = {1, 2, 3};
        System.out.println(findCoinCase(sum, coins));
    }

    private static int findCoinCase(int sum, int[] coins) {
        //조건 1. '서로다른' 동전 갯수 = n
        //조건 2. 동전은 무한히 제공
        int n = coins.length;

        //다이나믹 프로그래밍 도입
        //int[i][j] -> 최대 i개의 동전을 사용하여, 합계 j를 만드는 방법
        var dp = new int[n + 1][sum + 1];

        // dp[i][0]은 모두 1가지 가능
        IntStream.range(0, n + 1).forEach(i -> dp[i][0] = 1);

        //main-logic : bottom-up
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < sum + 1; j++)
                dp[i][j] = coins[i - 1] <= j ? dp[i - 1][j] + dp[i][j - coins[i - 1]] : dp[i - 1][j];

        //동전 n개를 사용하여 최종값 sum에 도달하는 가짓수
        return dp[n][sum];
    }
}
