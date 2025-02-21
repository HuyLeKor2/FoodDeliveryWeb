import java.util.Arrays;

public class Main {
    int[] nums1 = {1,2,3,0,0,0};
    int n = 3;
    int m = 3;
    int[] nums2 = {2,5,6};
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] mergedArray = new int[m + n];
        System.arraycopy(nums1, 0, mergedArray, 0, m);
        System.arraycopy(nums2, 0, mergedArray, m, n);
        System.out.println("Merged Array: " + Arrays.toString(mergedArray));
    }
}