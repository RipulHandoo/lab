
import java.util.Arrays;

public class Encrypt {
    int[] plainText;
    int[] key;
    int[] cypherText;

    private int[] p10 = { 2, 4, 1, 6, 3, 9, 0, 8, 7, 5 };
    private int[] p8 = { 5, 2, 6, 3, 7, 4, 9, 8 };
    private int[] ip = { 1, 5, 2, 0, 3, 7, 4, 6 };
    private int[] ip1 = { 3, 0, 2, 4, 6, 1, 7, 5 };
    private int[] p4 = { 1, 3, 2, 0 };
    private int[] ep = { 3, 0, 1, 2, 1, 2, 3, 0 };
    // int[] s0 = { 1, 0, 3, 2, 3, 2, 1, 0, 0, 2, 1, 3, 3, 1, 3, 2 };
    // int[] s1 = { 0, 1, 2, 3, 2, 0, 1, 3, 3, 0, 1, 0, 2, 1, 0, 3 };
    private int[][] s1 = {
        {1, 0, 3, 2},
        {3, 2, 1, 0},
        {0, 2, 1, 3},
        {3, 1, 3, 2}
    };
    private int[][] s2 = {
        {0, 1, 2, 3},
        {2, 0, 1, 3},
        {3, 0, 1, 0},
        {2, 1, 0, 3}
    };

    public Encrypt() {
        plainText = new int[8];
        key = new int[10];
    }

    public Encrypt(int[] plainText, int[] key) {
        this.plainText = plainText;
        this.key = key;
    }

    private int[] permute(int[] original, int[] pattern, int size) {
        int[] res = new int[size];
        int k = 0;
        for (int i = 0; i < pattern.length; i++) {
            res[k++] = original[pattern[i]];
        }
        return res;
    }

    private int[][] split(int[] original, int splitIndex) {
        int[] temp1 = new int[splitIndex], temp2 = new int[splitIndex];
        int i = 0;
        int k = 0;
        for (i = 0; i < splitIndex; i++) {
            temp1[k++] = original[i];
        }
        k = 0;
        while (i < original.length) {
            temp2[k++] = original[i];
            i++;
        }
        return new int[][] { temp1, temp2 };
    }

    private int[][] leftShiftByN(int[] a, int[] b, int n) {
        int[] temp1 = new int[a.length], temp2 = new int[b.length];
        int i = 0;
        int k = 0;
        for (i = 0; i < a.length - n; i++) {
            temp1[k++] = a[i + n];
        }
        for (i = 0; i < n; i++)
            temp1[k++] = a[i];

        k = 0;
        for (i = 0; i < b.length - n; i++) {
            temp2[k++] = b[i + n];
        }
        for (i = 0; i < n; i++)
            temp2[k++] = b[i];

        return new int[][] { temp1, temp2 };
    }

    private int[] xor(int[] a, int [] b) {
        int i = 0;
        int[] xorArr = new int[a.length];
        while (i < a.length) {
            xorArr[i] = a[i] ^ b[i++];
        }
        return xorArr;
    }

    private int[] mergerArr(int[][] matrix) {
        int[] k1 = new int[matrix[0].length * 2];
        int k = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            k1[k++] = matrix[0][i];
            k1[matrix[0].length - 1 + k] = matrix[1][i];
        }
        return k1;
    }

    private int getBinary(int i, int j) {
        return (i * 2) + j;
    }

    private int[] swap(int[] arr1, int[] arr2) {
        int[] ans = new int[arr1.length * 2];
        int i = 0;
        for (int j = 0; j < arr2.length; j++) {
            ans[i++] = arr2[j];
        }
        for (int j = 0; j < arr1.length; j++) {
            ans[i++] = arr1[j];
        }
        return ans;
    }

    private int[] decToBin(int n) {
        int i = 1;
        int[] arr = new int[2];
        while (n > 0) {
            arr[i--] = n % 2;
            n /= 2;
        }
        return arr;
    }

    public void convertText() {
        int[] permutedP10 = permute(key, p10, 10);
        // System.out.println("p10 - " + Arrays.toString(permutedP10));

        int[][] splitP10 = split(permutedP10, 5);
        // System.out.println("splitP10 - " + Arrays.deepToString(splitP10));

        int[][] leftShiftedP10 = leftShiftByN(splitP10[0], splitP10[1], 1);
        // System.out.println("leftShiftedBy1 - " + Arrays.deepToString(leftShiftedP10));

        int[] k1 = permute(mergerArr(leftShiftedP10), p8, 8);
        // System.out.println("k1 - " + Arrays.toString(k1));

        int[][] ls2 = leftShiftByN(leftShiftedP10[0], leftShiftedP10[1], 2);
        // System.out.println("ls2 - " + Arrays.deepToString(ls2));

        int[] joinedLs2 = mergerArr(ls2);
        int[] k2 = permute(joinedLs2, p8, 8);
        // System.out.println("k2 - " + Arrays.toString(k2));


        // Cypher-text
        int[] plaintIp = permute(plainText, ip, 8);
        int[][] plaintTextIpSplit = split(plaintIp, 4);

        int[] eip1 = permute(plaintTextIpSplit[1], ep, 8);
        // System.out.println("eip1 - " + Arrays.toString(eip1));
        int[][] split = split(xor(eip1, k1), 4);

        // System.out.println(Arrays.deepToString(split));


        int[] split0 = split[0];
        int[] split1 = split[1];

        int sp0 = s1[getBinary(split0[0], split0[2])][getBinary(split0[1], split0[3])];
        int sp1 = s2[getBinary(split1[0], split1[2])][getBinary(split1[1], split1[3])];
        // System.out.println(split[1][2]);    

        int[] binSp0 = decToBin(sp0);
        int[] binSp1 = decToBin(sp1);

        // System.out.println(Arrays.deepToString(new int[][]{binSp0, binSp1}));

        int[] sp4 = permute(new int[]{binSp0[0], binSp0[1], binSp1[0], binSp1[1]}, p4, 4);
        // System.out.println(Arrays.toString(sp4));

        int[] xorSp = xor(plaintTextIpSplit[0], sp4);
        
        int[] joined = swap(xorSp, plaintTextIpSplit[1]);
        // System.out.println(Arrays.toString(joined));

        //Remaining half steps
        int[][] swaptext = split(joined, 4);
        int[] eip2 = permute(swaptext[1], ep, 8);
        int[] xorK2 = xor(eip2, k2);
        int[][] splitXorK2 = split(xorK2, 4);
        int sp0K2 = s1[getBinary(splitXorK2[0][0], splitXorK2[0][2])][getBinary(splitXorK2[0][1], splitXorK2[0][3])];
        int sp1K2 = s2[getBinary(splitXorK2[1][0], splitXorK2[1][2])][getBinary(splitXorK2[1][1], splitXorK2[1][3])];
        int[] binSp0K2 = decToBin(sp0K2);
        int[] binSp1K2 = decToBin(sp1K2);
        int[] sp4K2 = permute(new int[]{binSp0K2[0], binSp0K2[1], binSp1K2[0], binSp1K2[1]}, p4, 4);
        int[] xorSp4K2 = xor(swaptext[0], sp4K2);
        int[] joinedK2 = swap(xorSp4K2, swaptext[1]);
        this.cypherText = permute(joinedK2, ip1, 8);
    }
}


