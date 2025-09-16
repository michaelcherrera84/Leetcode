// You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
// Find two lines that together with the x-axis form a container, such that the container contains the most water.
// Return the maximum amount of water a container can store.
// Notice that you may not slant the container.

// Example 1:
// Input: height = [1,8,6,2,5,4,8,3,7]
// Output: 49
// Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

// Example 2:
// Input: height = [1,1]
// Output: 1
 
// Constraints:
// n == height.length
// 2 <= n <= 105
// 0 <= height[i] <= 104


// Recursive approach
function maxArea(height: number[]): number {
    // Start recursion with full array bounds and initial max = 0
    return maximumArea(height, 0, height.length - 1, 0);
};

// Helper recursive function
function maximumArea(height: number[], i: number, j: number, max: number): number {
    // Base case: when the two pointers meet or cross, stop recursion
    if (i >= j) return max;

    // Current container size = distance between pointers * min(height[i], height[j])
    const size = (j - i) * (height[i] < height[j] ? height[i] : height[j]);

    // Update max if this size is bigger
    if (size > max) max = size;

    // Move the smaller side inward (greedy step):
    // - if left bar shorter, increment i
    // - otherwise, decrement j
    if (height[i] < height[j]) 
        return maximumArea(height, i + 1, j, max);
    else 
        return maximumArea(height, i, j - 1, max);
};


// Iterative (loop) approach
function maxArea1(height: number[]): number {
    let i = 0, j = height.length - 1, max = 0;

    // Continue until pointers meet
    while (i < j) {
        // Compute container size and update max
        max = Math.max(max, Math.min(height[i], height[j]) * (j - i));

        // Move the smaller side inward
        if (height[i] < height[j]) i++;
        else j--;
    }

    return max;
}

const height = [1,8,6,2,5,4,8,3,7];
console.log(maxArea(height));   // recursive
console.log(maxArea1(height));  // iterative
