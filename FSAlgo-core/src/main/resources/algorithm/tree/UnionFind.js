'use strict';

/**
 *
 * @constructor
 */
const UnionFind = function () {

    let parents = {};

    let heights = {};

    this.add = function (node) {
        if (node == null) {
            return;
        }
        parents[node] = [node];
        heights[node] = 0;
    }

    this.findRoot = function (node) {
        if (!parents[node]) {
            return null;
        }

        let current = node;
        while (current !== parents[current]) {
            current = parents[current];
        }

        let root = current;
        current = node;

        while (current !== root) {
            let parent = parents[current];
            parents[current] = root;
            current = parent;
        }

        return current;
    }

    this.union = function (node1, node2) {
        if (!parents[node1] || !parents[node2]) {
            return;
        }

        let root1 = this.findRoot(node1);
        let root2 = this.findRoot(node2);

        if (root1 === root2) {
            return;
        }

        if (heights[root1] >= heights[root2]) {
            parents[root2] = root1;
            heights[root1] = heights[root2] + 1;
            return;
        }
        parents[root1] = root2;
        heights[root2] = heights[root1] + 1;
    }

    this.check = function (node1, node2) {
        if (!parents[node1] || !parents[node2]) {
            return false;
        }

        let root1 = this.findRoot(node1);
        let root2 = this.findRoot(node2);

        return root1 === root2;
    }

    this.size = function () {
        return parents.length;
    }

}