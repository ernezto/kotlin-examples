package examples

import com.github.javafaker.Faker
import java.util.*
import java.util.concurrent.TimeUnit

data class Report(
    val account: Int,
    val name: String,
    val date: Date,
    val status: String,
    val amount: Double
) : Visitable() {

    companion object Builder {
        private val faker = Faker()
        fun random(): Report {
            return Report(
                account = faker.random().nextInt(1000),
                name = faker.commerce().productName(),
                date = faker.date().past(10, TimeUnit.HOURS),
                status = faker.commerce().color(),
                amount = faker.random().nextDouble()
            )
        }
    }

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun size(): Int {
        return 1
    }
}


