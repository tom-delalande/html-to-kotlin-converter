package app

import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser

private const val tab = "    "

fun convert(input: String): String {
    var output = ""
    var numTabs = 0
    val handler = KsoupHtmlHandler
        .Builder()
        .onOpenTag { name, attributes, _ ->
            repeat(numTabs) {
                output += tab
            }
            output += "$name {\n"
            numTabs += 1
            if (attributes.isNotEmpty()) {
                attributes.forEach {
                    repeat(numTabs) {
                        output += tab
                    }
                    val attributeText = when (it.key) {
                        "class" -> "classes = setOf(${it.value.split(" ").joinToString(", ") { "\"$it\"" }})\n"
                        "id",
                        "role",
                        "src",
                        "alt",
                        "href",
                        -> "${it.key} = \"${it.value}\"\n"

                        "for" -> "htmlFor = \"${it.value}\"\n"

                        "type" -> "type = ${it.value}\n"

                        else -> "attributes[\"${it.key}\"] = \"${it.value}\"\n"
                    }
                    output += attributeText
                }
            }
        }
        .onText { text ->
            if (text.isBlank()) return@onText
            repeat(numTabs) {
                output += tab
            }
            output += "+\"${text.trim()}\"\n"
        }
        .onCloseTag { _, _ ->
            numTabs -= 1
            repeat(numTabs) {
                output += tab
            }
            output += "}\n"
        }
        .build()

    val parsed = KsoupHtmlParser(handler)
    parsed.write(input)
    parsed.end()

    return output
}
