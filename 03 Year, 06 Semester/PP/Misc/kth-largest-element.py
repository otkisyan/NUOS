import heapq

def findKthLargest(nums, k):
    heap = nums[:k]
    heapq.heapify(heap)
        
    for num in nums[k:]:
        if num > heap[0]:
            heapq.heappop(heap)
            heapq.heappush(heap, num)
    
    return heap[0]
    
    

nums = [3,2,1,5,6,4] # 1 2 3 4 5 6
k = 2

findKthLargest(nums, k)