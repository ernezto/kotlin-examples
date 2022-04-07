package examples

class ReportCategory(
    val name: String,
    children: Collection<Visitable>
) : Visitable(children) {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun size(): Int {

        val size = children.fold(2) { a, b -> a + b.size() }
        println(">>> $size")
        return size
    }
}