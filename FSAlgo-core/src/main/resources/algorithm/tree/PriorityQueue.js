'use strict';

/**
 * 优先队列 - 大顶堆 - 堆排序
 * @constructor
 */
const PriorityQueue = function () {
    /**
     * 存放堆节点
     * @type {*[]}
     */
    let queue = [];

    /**
     * 当前堆大小
     * @returns {number}
     */
    this.size = function () {
        return queue.length;
    }

    /**
     * 当前堆的判空处理
     * @return   {Boolean}                [description]
     */
    this.isEmpty = function () {
        return queue.length === 0;
    }

    /**
     * 将所有节点加到堆中
     * @param list
     */
    this.addAll = function (list) {
        for (let i = 0; i < list.length; i++) {
            this.add(list[i]);
        }
    }

    /**
     * 获取堆顶元素
     * @return   {[type]}                 [description]
     */
    this.peek = function () {
        return this.isEmpty() ? null : queue[0];
    }

    /**
     * 元素添加到堆中，并调整堆序
     * @param val
     */
    this.add = function (val) {
        queue.push(val);
        if (this.size() <= 1) {
            return;
        }
        // 新节点插入队列末尾，下标为 size() - 1
        // 左节点的父节点为 (index - 1) / 2, 右节点的父节点为 (index - 2) / 2
        let index = this.size() - 1;
        let pIndex = Math.floor((index - (index % 2 !== 0 ? 1 : 2)) / 2);
        // 堆末尾节点与父级节点比较，决定是否上移保持堆序
        while (this.compareTo(val, queue[pIndex])) {
            this.swap(index, pIndex);
            index = pIndex;
            pIndex = Math.floor((index - (index % 2 !== 0 ? 1 : 2)) / 2);
            if (pIndex < 0) {
                break;
            }
        }
    }

    /**
     * 移除堆中最值，并调整堆序
     * @returns {null|*}
     */
    this.remove = function () {
        if (this.size() === 0) {
            return null;
        }
        // 堆顶和最后一个节点交换
        this.swap(0, this.size() - 1);
        let result = queue.pop();

        // 堆顶节点向下和子节点比较，决定是否下移保持堆序
        let index = 0;
        while ((index * 2) + 1 < this.size()) {
            let top;
            let left = (index * 2) + 1;
            let right = (index * 2) + 2;

            if (right < this.size()) {
                // 如果存在右子节点，取左、右节点最值，与当前节点比较。
                top = this.compareTo(queue[left], queue[right]) ? left : right;
                top = this.compareTo(queue[top], queue[index]) ? top : index;
            } else {
                // 如果只存在左子节点，与当前节点比较
                top = this.compareTo(queue[left], queue[index]) ? left : index;
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

    /**
     * 比较节点
     * @param x
     * @param y
     * @returns {boolean}
     */
    this.compareTo = function (x, y) {
        return x > y;
    }

    /**
     * 交换节点
     * @param x
     * @param y
     */
    this.swap = function (x, y) {
        let temp = queue[x];
        queue[x] = queue[y];
        queue[y] = temp;
    }
}

/**
 * 使用案例
 */
function useCaseOfPriorityQueue() {
    let nums = [3, 1, 2, 4, 6, 0, 9, 7, 8, 5];
    let pq = new PriorityQueue();

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
    console.log();
}

useCaseOfPriorityQueue();