package app

import kotlin.test.Test
import kotlin.test.assertEquals

class DivTest {

    @Test
    fun createDiv() {
        val input = """
            <div>Hello world</div>
        """.trimIndent()


        val expectedOutput = """
            div {
                +"Hello world"
            }
            
        """.trimIndent()

        val output = convert(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun attributes() {
        val input = """
            <div att="one two three">Hello world</div>
        """.trimIndent()


        val expectedOutput = """
            div {
                attributes["att"] = "one two three"
                +"Hello world"
            }
            
        """.trimIndent()

        val output = convert(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun nested() {
        val input = """
            <div>
                <p>
                    Hello world
                </p>
            </div>
        """.trimIndent()


        val expectedOutput = """
            div {
                p {
                    +"Hello world"
                }
            }
            
        """.trimIndent()

        val output = convert(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun classes() {
        val input = """
            <div class="one two three">Hello world</div>
        """.trimIndent()


        val expectedOutput = """
            div {
                classes = setOf("one", "two", "three")
                +"Hello world"
            }
            
        """.trimIndent()

        val output = convert(input)

        assertEquals(expectedOutput, output)
    }
}