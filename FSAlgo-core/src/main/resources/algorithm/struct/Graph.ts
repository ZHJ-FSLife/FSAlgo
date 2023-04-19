
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
