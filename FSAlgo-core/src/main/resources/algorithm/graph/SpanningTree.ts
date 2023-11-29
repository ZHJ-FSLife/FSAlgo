/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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

