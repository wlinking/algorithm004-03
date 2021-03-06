/*

Dp算法解决

定义dp(i, j, k)为长度为i, 是否包含A(j=1包含，j=0不包含)， 是否以L结尾(k=0不是以L结尾，k=1是以L结尾)的合法字符串个数

最后结果是dp(n,0,0) + dp(n,0,1) + dp(n,1,0) + dp(n,1,1)

考虑最后结尾的字符的不同情况，可以推导下面的递推公式


dp(i, 0, 0) = dp(i-1, 0, 0或1)       前i-1个合法，第个是P
dp(i, 1, 0) = dp(i-1, 0, 0或1)       前i-1个合法，前i-1个不包含A,第i个是A
            + dp(i-1, 1, 0或1)       前i-1个合法，第i个是P
dp(i, 0, 1) = dp(i-2, 0, 0)         前i-2个合法且没有A， i-1 和 i两个字符是LL
            +  dp(i-2, 0, 0或者1)    前i-2个合法且没有A, i-1 和 i两个字符是PL

dp(i, 1, 1) = dp(i-2, 1, 0)         前i-2个合法有一个A且不能L结尾，最后两个字符是LL
             + dp(i-2, 1, 0或1)    前i-2个有一个A，最后两个字符是PL
             + dp(i-2,0, 0或1)      前i-2个没有A，最后两个字符是AL

 */



class Solution {
    public int checkRecord(int n) {
        long[][][] dp = new long[n+1][2][2];
        dp[0][0][0] = 1;
        dp[0][1][0] = 0;
        dp[0][0][1] = 0;
        dp[0][1][1] = 0;

        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;
        dp[1][1][1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1]) % 1000000007;
            dp[i][1][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][1][0] + dp[i-1][1][1]) % 1000000007;
            dp[i][0][1] = (dp[i-2][0][0] + dp[i-2][0][0] + dp[i-2][0][1]) % 1000000007;
            dp[i][1][1] = (dp[i-2][1][0] + dp[i-2][1][0] + dp[i-2][1][1] +
                          dp[i-2][0][0] + dp[i-2][0][1]) % 1000000007;
        }

        return (int)((dp[n][0][0] + dp[n][1][0] + dp[n][0][1] + dp[n][1][1]) % 1000000007);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkRecord(2));
    }
}
