import java.util.*;

public class Main {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int evaluateExpression(String expression) {
        // This works just for integers. But we could also use BigInteger If we want to work with bug numbers.
        if (!(expression).matches("^([-]?\\d+)([+-]\\d+)*$")) throw new IllegalArgumentException("Illegal Format!");
        int result = 0;
        boolean sign = true;
        for (int i = 0; i < expression.length(); i++) {
            int start = i;
            if (!Character.isDigit(expression.charAt(i))) {
                sign = (expression.charAt(i) == '+');
                start = ++i;
            }
            while (i < expression.length() && Character.isDigit(expression.charAt(i))) i++;
            int temp = Integer.parseInt(expression.substring(start, i));
            result += (sign ? temp : -temp);
            i--;
        }
        return result;
    }

    public static int numberOfHappyStrings(List<String> strings) {
        int result = 0;
        boolean happy = true;
        for (String s : strings) {
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    happy = false;
                    break;
                }
            }
            if (happy) result++;
            happy = true;
        }
        return result;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode y = head.next;
        ListNode z = null;

        while (y != null) {
            head.next = z;
            z = head;
            head = y;
            y = y.next;
        }
        head.next = z;
        return head;
    }

    public static int[] findIntersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) throw new IllegalArgumentException("Illegal Format!");
        HashSet<Integer> hashSet1 = new HashSet<>();
        HashSet<Integer> hashSet2 = new HashSet<>();
        for (int x : nums1) hashSet1.add(x);
        for (int x : nums2)
            if (hashSet1.contains(x))
                hashSet2.add(x);
        return hashSet2.stream().mapToInt(a -> a).toArray();
    }

    public static boolean isValidSequence(int[] array, int[] sequence) {
        if (array == null || sequence == null) throw new IllegalArgumentException("Illegal Format!");
        if (array.length < sequence.length) return false;
        int j = 0;
        for (int i = 0; i < array.length && j < sequence.length; i++) {
            if (array[i] == sequence[j]) j++;
        }
        return j == sequence.length;
    }

    public static int lenOfLongSubarr(int[] candidates, int target) {
        // I use backtraking method in order to calulate all possible sums which are equal to k and  then via streams easily calculate max list size.
        List<List<Integer>> l = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack(l, l1, candidates, target, 0);
        return l.stream()
                .max(Comparator.comparingInt(List::size))
                .map(List::size)
                .orElse(0);
    }

    private static void backTrack(List<List<Integer>> l, List<Integer> l1, int[] candidates, int target, int num) {
        if (target < 0) return;
        if (target == 0) {
            l.add(new ArrayList<>(l1));
            return;
        }

        for (int i = num; i < candidates.length; i++) {
            if (i > num && candidates[i] == candidates[i - 1]) continue;
            l1.add(candidates[i]);
            backTrack(l, l1, candidates, target - candidates[i], i + 1);
            l1.remove(l1.size() - 1);
        }
    }
}