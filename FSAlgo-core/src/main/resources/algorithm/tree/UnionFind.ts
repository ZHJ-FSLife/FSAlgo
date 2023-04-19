class UnionFind<T> {

    private parents : Map<T, T> = new Map();

    private heights : Map<T, number> = new Map();

    public add(node : T) : void {
        if (node == null) {
            return;
        }
        this.parents.set(node, node);
        this.heights.set(node, 0);
    }

    private findRoot(node : T) : T | null {
        if (!this.parents.has(node)) {
            return null;
        }

        let current : T = node;
        while (current !== this.parents.get(current)!) {
            current = this.parents.get(current)!;
        }

        let root : T = current;
        current = node;

        while (current !== root) {
            let parent : T = this.parents.get(current)!;
            this.parents.set(current, root);
            current = parent;
        }

        return current;
    }

    public union (node1 : T, node2 : T) : void {
        if (!this.parents.has(node1) || !this.parents.has(node2)) {
            return;
        }

        let root1 : T = this.findRoot(node1)!;
        let root2 : T = this.findRoot(node2)!;

        if (root1 === root2) {
            return;
        }

        if (this.heights.get(node1)! >= this.heights.get(node2)!) {
            this.parents.set(root2, root1);
            this.heights.set(root1, this.heights.get(root2)! + 1);
            return;
        }
        this.parents.set(root1, root2);
        this.heights.set(root2, this.heights.get(root1)! + 1);
    }

    public check(node1 : T, node2 : T) : boolean {
        if (!this.parents.has(node1) || !this.parents.has(node2)) {
            return false;
        }

        let root1 : T = this.findRoot(node1)!;
        let root2 : T = this.findRoot(node2)!;

        return root1 === root2;
    }

    public size() : number {
        return this.parents.size;
    }

}
