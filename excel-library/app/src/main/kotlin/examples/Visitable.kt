package examples

abstract class Visitable(val children: Collection<Visitable> = emptyList()) {
    abstract fun accept(visitor: Visitor)
    abstract fun size() : Int
}