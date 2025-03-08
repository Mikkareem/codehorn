import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseCollectionType
import com.techullurgy.codehorn.common.model.TestcaseDataType
import com.techullurgy.codehorn.common.model.TestcaseType
import com.techullurgy.codehorn.domain.code.execution.parsers.CodehornTestcaseParserStrategy
import kotlin.test.Test
import kotlin.test.assertEquals

class CodehornTestcaseParserStrategyTest {

    private val parser = CodehornTestcaseParserStrategy()

    private val basicTestcase = ProblemTestcase(
        id = 0,
        isHidden = true,
        inputs = listOf(),
        masks = listOf(),
        parserCodes = listOf()
    )

    @Test
    fun testSet1() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "189",
                "-192"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
            )
        )

        val expectedOutput = """
            189
            -192
            
        """.trimIndent()

        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet2() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "[1,2,3,4]",
                "-192"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.LIST).mask,
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
            )
        )

        val expectedOutput = """
            4
            1
            2
            3
            4
            -192
            
        """.trimIndent()

        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet3() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "-192",
                "[1,2,3,4]"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.SINGLE).mask,
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.LIST).mask,
            )
        )
        val expectedOutput = """
            -192
            4
            1
            2
            3
            4
            
        """.trimIndent()


        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet4() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "[-1, 7,       89, -89]",
                "[1,2,3,4]"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.LIST).mask,
                TestcaseType(TestcaseDataType.INT, TestcaseCollectionType.LIST).mask,
            )
        )
        val expectedOutput = """
            4
            -1
            7
            89
            -89
            4
            1
            2
            3
            4
            
        """.trimIndent()


        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet5() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "189",
                "-192"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.SINGLE).mask,
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.SINGLE).mask,
            )
        )

        val expectedOutput = """
            189
            -192
            
        """.trimIndent()

        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet6() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "[1,2,3,4]",
                "-192"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.LIST).mask,
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.SINGLE).mask,
            )
        )

        val expectedOutput = """
            4
            1
            2
            3
            4
            -192
            
        """.trimIndent()

        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet7() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "-192",
                "[1,2,3,4]"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.SINGLE).mask,
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.LIST).mask,
            )
        )
        val expectedOutput = """
            -192
            4
            1
            2
            3
            4
            
        """.trimIndent()


        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet8() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "[-1, 7,       89, -89]",
                "[1,2,3,4]"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.LIST).mask,
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.LIST).mask,
            )
        )
        val expectedOutput = """
            4
            -1
            7
            89
            -89
            4
            1
            2
            3
            4
            
        """.trimIndent()


        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testSet9() {

        val testcase = basicTestcase.copy(
            inputs = listOf(
                "[[-1, 7,       89, -89], [-9, 4, -1901, 2]]",
                "[1,2,3,4]"
            ),
            masks = listOf(
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.LIST_LIST).mask,
                TestcaseType(TestcaseDataType.LONG, TestcaseCollectionType.LIST).mask,
            )
        )
        val expectedOutput = """
            2
            4
            -1
            7
            89
            -89
            4
            -9
            4
            -1901
            2
            4
            1
            2
            3
            4
            
        """.trimIndent()


        val actualOutput = parser.parse(testcase)

        assertEquals(expectedOutput, actualOutput)
    }
}