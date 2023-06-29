
interface Heap<T> {

    add(t : T) : void;

    peek() : T | void;

    remove() : T | void;

    size() : number;

    isEmpty() : boolean;

    compareTo(x : T, y : T) : boolean;

}

abstract class AbstractHeap<T> implements Heap<T> {

    public abstract add(t : T) : void;

    public abstract peek() : T | void;

    public abstract remove() : T | void;

    public abstract size() : number;

    public abstract isEmpty() : boolean;

    public compareTo(x : T, y : T) : boolean {
        return x > y;
    }
}

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 斐波那契堆
 */
class FibonacciHeapNode<T> {
    private key : T;
    private degree : number = 0;
    private mark : boolean;

    private left   : FibonacciHeapNode<T> | null;
    private right  : FibonacciHeapNode<T> | null;
    private child  : FibonacciHeapNode<T> | null = null;
    private parent : FibonacciHeapNode<T> | null = null;

    constructor(key : T) {
        this.key = key;
        this.mark = false;
        this.left = this;
        this.right = this;
    }

}
class FibonacciHeap<T> extends AbstractHeap<T> {

    private GOLDEN_RATIO : number = (1 + Math.sqrt(5)) / 2;

    private min : FibonacciHeapNode<T> | null;

    private nodeNum : number = 0;

    private rootNum : number = 0;

    constructor(key : T) {
        super();
        this.min = new FibonacciHeapNode<T>(key);
    }

    public add(val : T) : void {

    }

    public peek() : T | void {

    }

    public remove() : T | void {

    }

    public size() : number {
        return this.nodeNum;
    }

    public isEmpty() : boolean {
        return this.nodeNum == 0;
    }

    // private unionNodeToLeft(x : FibonacciHeapNode<T>, y : FibonacciHeapNode<T>) : null {
    //     x.left.right = y;
    //     y.left = x.left;
    //     y.right = x;
    //     x.left = y;
    // }

}

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 二项式堆
 */
// class BinomialHeap<T> extends AbstractHeap<T> {}

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 二叉堆
 */
class BinaryHeap<T> extends AbstractHeap<T> {

    private queue : T[] = [];

    public size() : number {
        return this.queue.length;
    }

    public isEmpty() : boolean {
        return this.size() === 0;
    }

    public peek() : T | void {
        if (this.isEmpty()) {
            return;
        }
        return this.queue[0];
    }

    public addAll(list : T[]): void {
        for (let i = 0; i < list.length; i++) {
            this.add(list[i]);
        }
    }

    public add(val : T) : void {
        this.queue.push(val);
        if (this.size() <= 1) {
            return;
        }
        // 新节点插入队列末尾，下标为 size() - 1
        // 左节点的父节点为 (index - 1) / 2, 右节点的父节点为 (index - 2) / 2
        let index = this.size() - 1;
        let pIndex = Math.floor((index - (index % 2 !== 0 ? 1 : 2)) / 2);
        // 堆末尾节点与父级节点比较，决定是否上移保持堆序
        while (this.compareTo(val, this.queue[pIndex])) {
            this.swap(index, pIndex);
            index = pIndex;
            pIndex = Math.floor((index - (index % 2 !== 0 ? 1 : 2)) / 2);
            if (pIndex < 0) {
                break;
            }
        }
    }

    public remove() : T | void {
        if (this.isEmpty()) {
            return;
        }
        // 堆顶和最后一个节点交换
        this.swap(0, this.size() - 1);
        let result : T = this.queue.pop()!;

        // 堆顶节点向下和子节点比较，决定是否下移保持堆序
        let index = 0;
        while ((index * 2) + 1 < this.size()) {
            let top;
            let left = (index * 2) + 1;
            let right = (index * 2) + 2;

            if (right < this.size()) {
                // 如果存在右子节点，取左、右节点最值，与当前节点比较。
                top = this.compareTo(this.queue[left], this.queue[right]) ? left : right;
                top = this.compareTo(this.queue[top], this.queue[index]) ? top : index;
            } else {
                // 如果只存在左子节点，与当前节点比较
                top = this.compareTo(this.queue[left], this.queue[index]) ? left : index;
            }

            // 如果最值为当前节点，则结束堆节点下移
            if (top === index) {
                break;
            }
            this.swap(index, top);
            index = top;
        }
        return result;
    }

    private swap(x : number, y : number) : void {
        let temp = this.queue[x];
        this.queue[x] = this.queue[y];
        this.queue[y] = temp;
    }
}

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 左倾堆
 */
// class LeftisHeap<T> extends AbstractHeap<T> {}

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 斜堆
 */
// class SkewHeap<T> extends AbstractHeap<T> {}


/**
 * 使用案例
 */
function useCaseOfPriorityQueue() {
    let nums = [3, 1, 2, 4, 6, 0, 9, 7, 8, 5];
    let pq : Heap<number> = new BinaryHeap<number>();

    // 默认对节点值本身比较大小取最大值，可重写该方法后应用于任何类型数据
    pq.compareTo = function (x, y) {
        return x < y;
    }
    // 节点加入堆中
    // pq.addAll(nums);
    for (let index in nums) {
        pq.add(nums[index]);
    }
    // 逐个取出堆顶节点
    while (!pq.isEmpty()) {
        // @ts-ignore
        process.stdout.write(pq.remove() + " ");
    }
    pq.remove();
    console.log();
}

useCaseOfPriorityQueue();
