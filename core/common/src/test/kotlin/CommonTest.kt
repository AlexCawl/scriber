import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CommonTest {
    @Test
    fun testAdd() {
        assertEquals(42, Integer.sum(19, 23))
    }
}
