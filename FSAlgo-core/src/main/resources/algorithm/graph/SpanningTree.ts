import '../struct/Graph';

interface SpanningTreeAlgorithm<N> {

    getSpanningTree() : SpanningTree<N> | null;

}

interface SpanningTree<N> {

    getWeight() : number;

    // @ts-ignore
    getEdges() : Set<Edge<N>> | null;
}

class SpanningTreeImpl<N> implements SpanningTree<N> {
    
    public weight: number;

    // @ts-ignore
    public edges: Set<Edge<N>> | null;

    // @ts-ignore
    constructor(edges : Set<Edge<N>>, weight : number) {
        this.edges = edges;
        this.weight = weight;
    }

    getWeight() : number {
        return this.weight;
    }

    // @ts-ignore
    getEdges() : Set<Edge<N>> | null {
        return this.edges;
    }
}

