/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
 *
 * Copyright (C) [2023] [ZhongHongJie]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

class Edge<N> {

    private source : N | null;
    private target : N | null;
    private weight : number = 1.0;

    constructor();
    constructor(source : N, target : N);
    constructor(source : N, target : N, weight : number);
    constructor(source? : N, target? : N, weight? : number) {
        this.source = source ?? null;
        this.target = target ?? null;
        this.weight = weight ?? 1.0;
    }
}

interface EdgeSetFactory<N> {
    // @ts-ignore
    createEdgeSet(node : N) : Set<Edge<N>> | null;
}
interface NodeSetFactory<N> {
    // @ts-ignore
    createNodeSet(node : N) : Set<N> | null;
}
class DefaultEdgeSetFactory<N> implements EdgeSetFactory<N> {
    // @ts-ignore
    public createEdgeSet(node : N) : Set<Edge<N>> | null {
        // @ts-ignore
        return new Set();
    }
}
class DefaultNodeSetFactory<N> implements NodeSetFactory<N> {
    // @ts-ignore
    public createNodeSet(node : N) : Set<N> | null {
        // @ts-ignore
        return new Set();
    }
}

class EdgeContainer<N> {
    // @ts-ignore
    private incoming : Set<Edge<N>> | null;
    // @ts-ignore
    private outgoing : Set<Edge<N>> | null;

    constructor(edgeSetFactory : EdgeSetFactory<N>, node : N) {
        this.incoming = edgeSetFactory.createEdgeSet(node);
        this.outgoing = edgeSetFactory.createEdgeSet(node);
    }

    public setIncoming(edge : Edge<N>) : void { (this.incoming)!.add(edge); }
    public setOutgoing(edge : Edge<N>) : void { (this.outgoing)!.add(edge); }
    // @ts-ignore
    public getIncoming() : Set<Edge<N>> { return this.incoming!; }
    // @ts-ignore
    public getOutgoing() : Set<Edge<N>> { return this.outgoing!; }
}

class NodeContainer<N> {
    // @ts-ignore
    private incoming : Set<N> | null;
    // @ts-ignore
    private outgoing : Set<N> | null;
    constructor(nodeSetFactory : NodeSetFactory<N>, node : N) {
        this.incoming = nodeSetFactory.createNodeSet(node);
        this.outgoing = nodeSetFactory.createNodeSet(node);
    }
    public setIncoming(node : N) : void { (this.incoming)!.add(node); }
    public setOutgoing(node : N) : void { (this.outgoing)!.add(node); }
    // @ts-ignore
    public getIncoming() : Set<N> { return this.incoming!; }
    // @ts-ignore
    public getOutgoing() : Set<N> { return this.outgoing!; }
}

interface Graph<N> {
    addNode(node : N) : void;
    addEdge(edge : Edge<N>) : void;
    addEdge(source : N, target : N) : void;
    addEdge(source : N, target : N, weight : number) : void;
    // @ts-ignore
    nodes() : Set<N> | null;
    // @ts-ignore
    edges() : Set<Edge<N>> | null;
}

abstract class AbstractGraph<N> implements Graph<N> {
    // @ts-ignore
    private nodeMap : Map<N, NodeContainer<N>> | null;
    // @ts-ignore
    private edgeMap : Map<N, EdgeContainer<N>> | null;

    constructor() {
        // @ts-ignore
        this.nodeMap = new Map();
        // @ts-ignore
        this.edgeMap = new Map();
    }

    public addNode(node : N) : void {
        if ((this.nodeMap)!.has(node)) {
            return;
        }
        (this.nodeMap)!.set(node, new NodeContainer(new DefaultNodeSetFactory(), node));
    }

    public abstract addEdge(edge : Edge<N>) : void;
    public abstract addEdge(source : N, target : N) : void;
    public abstract addEdge(source : N, target : N, weight : number) : void;
    // @ts-ignore
    public abstract nodes() : Set<N> | null;
    // @ts-ignore
    public abstract edges() : Set<Edge<N>> | null;

    public addEdgeContainer(node : N) : void {
        if ((this.edgeMap)!.has(node)) {
            return;
        }
        (this.edgeMap)!.set(node, new EdgeContainer(new DefaultEdgeSetFactory(), node));
    }
}

// class DirectedGraph<N> extends AbstractGraph<N> {}
// class UndirectesGraph<N> extends AbstractGraph<N> {}
