public class Pair<T> {//Unordered pair
    T first;
    T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return first.hashCode() * second.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Pair other = (Pair) obj;
        boolean standard = this.first.equals(other.first) && this.second.equals(other.second);
        boolean cross = this.first.equals(other.second) && this.second.equals(other.first);
        return standard || cross;//unordered
    }
}
