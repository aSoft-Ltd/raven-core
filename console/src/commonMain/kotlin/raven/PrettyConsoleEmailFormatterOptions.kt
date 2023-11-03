package raven

class PrettyConsoleEmailFormatterOptions(
    val separator: String = "=",
    /**
     * It looks better when this value is an odd number
     */
    val charsPerLine: Int = 55,
    val marginWidth: String = "\t".repeat(6),
    val border: String = "|",
    val paddingWidth: String = " ",
)