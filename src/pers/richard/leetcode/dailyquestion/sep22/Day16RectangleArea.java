package pers.richard.leetcode.dailyquestion.sep22;

import java.util.*;

/**
 * @ClassName: Day16RectangleArea
 * @Description: 矩形面积II
 * 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。
 * 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标
 * (xi1, yi1) 是该矩形左下角的坐标
 * (xi2, yi2) 是该矩形右上角的坐标。
 * 计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 *
 * 返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-16 09:50
 */
public class Day16RectangleArea {

    private Segtree[] tree;
    private List<Integer> hbound;

    /**
     * 离散化 + 扫描线 + 使用线段树实时维护
     * 思路与算法
     * 方法一中对于数组seg的所有操作都可以使用线段树进行维护。线段树中需要存储：
     * 该节点对应的区间被完整覆盖的次数；
     * 该节点对应的区间被覆盖的线段长度。
     * 线段树需要支持：
     * 区间增加 1；
     * 区间减少 1，并且保证每个被增加 1 的区间在之后一定会减少 1；
     * 对于所有非 0 的位置，根据它们的权值进行求和。
     *
     *
     * 官方详解：由于这种方法严重超纲，因此不在这里详细阐述。感兴趣的读者可以参考下面的代码和注释，仅供挑战自我。
     * @param rectangles
     * @return
     */
    public int rectangleArea(int[][] rectangles) {
        final int MOD = 1000000007;
        int n = rectangles.length;
        Set<Integer> set = new HashSet<>();
        for (int[] rect : rectangles) {
            // 下边界
            set.add(rect[1]);
            // 上边界
            set.add(rect[3]);
        }
        hbound = new ArrayList<>(set);
        Collections.sort(hbound);
        int m = hbound.size();
        // 线段树有 m-1 个叶子节点，对应着 m-1 个会被完整覆盖的线段，需要开辟 ~4m 大小的空间
        tree = new Segtree[m * 4 + 1];
        init(1, 1, m - 1);

        List<int[]> sweep = new ArrayList<int[]>();
        for (int i = 0; i < n; ++i) {
            // 左边界
            sweep.add(new int[]{rectangles[i][0], i, 1});
            // 右边界
            sweep.add(new int[]{rectangles[i][2], i, -1});
        }
        Collections.sort(sweep, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        long ans = 0;
        for (int i = 0; i < sweep.size(); ++i) {
            int j = i;
            while (j + 1 < sweep.size() && sweep.get(i)[0] == sweep.get(j + 1)[0]) {
                ++j;
            }
            if (j + 1 == sweep.size()) {
                break;
            }
            // 一次性地处理掉一批横坐标相同的左右边界
            for (int k = i; k <= j; ++k) {
                int[] arr = sweep.get(k);
                int idx = arr[1], diff = arr[2];
                // 使用二分查找得到完整覆盖的线段的编号范围
                int left = binarySearch(hbound, rectangles[idx][1]) + 1;
                int right = binarySearch(hbound, rectangles[idx][3]);
                update(1, 1, m - 1, left, right, diff);
            }
            ans += (long) tree[1].length * (sweep.get(j + 1)[0] - sweep.get(j)[0]);
            i = j;
        }
        return (int) (ans % MOD);
    }

    public void init(int idx, int l, int r) {
        tree[idx] = new Segtree();
        if (l == r) {
            tree[idx].maxLength = hbound.get(l) - hbound.get(l - 1);
            return;
        }
        int mid = (l + r) / 2;
        init(idx * 2, l, mid);
        init(idx * 2 + 1, mid + 1, r);
        tree[idx].maxLength = tree[idx * 2].maxLength + tree[idx * 2 + 1].maxLength;
    }

    public void update(int idx, int l, int r, int ul, int ur, int diff) {
        if (l > ur || r < ul) {
            return;
        }
        if (ul <= l && r <= ur) {
            tree[idx].cover += diff;
            pushup(idx, l, r);
            return;
        }
        int mid = (l + r) / 2;
        update(idx * 2, l, mid, ul, ur, diff);
        update(idx * 2 + 1, mid + 1, r, ul, ur, diff);
        pushup(idx, l, r);
    }

    public void pushup(int idx, int l, int r) {
        if (tree[idx].cover > 0) {
            tree[idx].length = tree[idx].maxLength;
        } else if (l == r) {
            tree[idx].length = 0;
        } else {
            tree[idx].length = tree[idx * 2].length + tree[idx * 2 + 1].length;
        }
    }

    public int binarySearch(List<Integer> hbound, int target) {
        int left = 0, right = hbound.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (hbound.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 离散化 + 扫描线 + 使用简单数组实时维护
     * 思路与算法
     *
     * 我们先解释扫描线的概念：想象一条竖直的直线从平面的最左端扫到最右端，在扫描的过程中，直线上的一些线段会被给定的矩形覆盖。
     * 将这些覆盖的线段长度进行积分，就可以得到矩形的面积之和。每个矩形有一个左边界和一个右边界，在扫描到矩形的左边界时，覆盖的长度可能会增加；
     * 在扫描到矩形的右边界时，覆盖的长度可能会减少。
     * 如果给定了 n 个矩形，那么覆盖的线段长度最多变化 2n 次，此时我们就可以将两次变化之间的部分合并起来，一起计算：
     * 即这一部分矩形的面积，等于覆盖的线段长度，乘以扫描线在水平方向移动过的距离。
     *
     * 因此，我们可以首先将所有矩形的左右边界按照横坐标进行排序，这样就确定了扫描线扫描的顺序。
     * 随后我们遍历这些左右边界，一次性地处理掉一批横坐标相同的左右边界，对应地增加或者减少覆盖的长度。
     * 在这之后，下一个未遍历到的坐右边界的横坐标，减去这一批左右边界的横坐标，就是扫描线在水平方向移动过的距离。
     *
     * 那么我们如何维护「覆盖的线段长度」呢？
     * 这里同样可以使用到离散化的技巧（扫描线就是一种离散化的技巧，将大范围的连续的坐标转化成 2n 个离散的坐标）。
     * 由于矩形的上下边界也只有 2n 个，它们会将 y 轴分成 2n+1 个部分，中间的 2n−1 个部分均为线段
     * 会被矩形覆盖到（最外侧的 2 个部分为射线，不会被矩形覆盖到），并且每一个线段要么完全被覆盖，要么完全不被覆盖。
     * 因此我们可以使用两个长度为 2n−1 的数组 seg 和 length
     * 其中 seg[i] 表示第 i 个线段被矩形覆盖的次数，length[i] 表示第 i 个线段的长度。
     * 当扫描线遇到一个左边界时，我们就将左边界覆盖到的线段对应的 seg[i] 全部加 1；
     * 遇到一个右边界时，我们就将右边界覆盖到的线段对应的 seg[i] 全部减 1。
     * 在处理掉一批横坐标相同的左右边界后，seg[i] 如果大于 0，说明它被覆盖，我们累加所有的 length[i]，即可得到「覆盖的线段长度」。
     *
     * @param rectangles
     * @return
     */
    public int rectangleArea_A(int[][] rectangles) {
        final int MOD = 1000000007;
        int n = rectangles.length;
        Set<Integer> set = new HashSet<>();
        for (int[] rect : rectangles) {
            // 下边界
            set.add(rect[1]);
            // 上边界
            set.add(rect[3]);
        }
        List<Integer> hbound = new ArrayList<>(set);
        Collections.sort(hbound);
        int m = hbound.size();
        // 「思路与算法部分」的 length 数组并不需要显式地存储下来
        // length[i] 可以通过 hbound[i+1] - hbound[i] 得到
        int[] seg = new int[m - 1];

        List<int[]> sweep = new ArrayList<int[]>();
        for (int i = 0; i < n; ++i) {
            // 左边界
            sweep.add(new int[]{rectangles[i][0], i, 1});
            // 右边界
            sweep.add(new int[]{rectangles[i][2], i, -1});
        }
        Collections.sort(sweep, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        long ans = 0;
        for (int i = 0; i < sweep.size(); ++i) {
            int j = i;
            while (j + 1 < sweep.size() && sweep.get(i)[0] == sweep.get(j + 1)[0]) {
                ++j;
            }
            if (j + 1 == sweep.size()) {
                break;
            }
            // 一次性地处理掉一批横坐标相同的左右边界
            for (int k = i; k <= j; ++k) {
                int[] arr = sweep.get(k);
                int idx = arr[1], diff = arr[2];
                int left = rectangles[idx][1], right = rectangles[idx][3];
                for (int x = 0; x < m - 1; ++x) {
                    if (left <= hbound.get(x) && hbound.get(x + 1) <= right) {
                        seg[x] += diff;
                    }
                }
            }
            int cover = 0;
            for (int k = 0; k < m - 1; ++k) {
                if (seg[k] > 0) {
                    cover += (hbound.get(k + 1) - hbound.get(k));
                }
            }
            ans += (long) cover * (sweep.get(j + 1)[0] - sweep.get(j)[0]);
            i = j;
        }
        return (int) (ans % MOD);
    }
}

class Segtree {
    int cover = 0;
    int length = 0;
    int maxLength = 0;
}
