
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


// class FibonacciHeap<T> extends AbstractHeap<T> {}
//
// class BinomialHeap<T> extends AbstractHeap<T> {}
//
class BinaryHeap<T> extends AbstractHeap<T> {

    private queue : T[] = [];

    public size() : number {
        return this.queue.length;
    }

    public isEmpty() : boolean {
        return this.queue.length == 0;
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

// class LeftisHeap<T> extends AbstractHeap<T> {}
//
// class SkewHeap<T> extends AbstractHeap<T> {}
//


/**
 * 使用案例
 */
function useCaseOfPriorityQueue() {
    let nums = [3, 1, 2, 4, 6, 0, 9, 7, 8, 5];
    let pq = new BinaryHeap<number>();

    // 默认对节点值本身比较大小取最大值，可重写该方法后应用于任何类型数据
    pq.compareTo = function (x, y) {
        return x < y;
    }
    // 节点加入堆中
    pq.addAll(nums);
    // 逐个取出堆顶节点
    while (!pq.isEmpty()) {
        process.stdout.write(pq.remove() + " ");
    }
    pq.remove();
    console.log();
}

useCaseOfPriorityQueue();
