package cn.timaviciix.ebm.data.book

enum class BookNbtType(
    val maxPage: Int,
    val charsPerPage: Int,
    val backgroundAndStorageType: Int,
) {
    //@Imp: Temp init
    //15 lines & 14 chars per line
    JournalBook(200, 15 * 14, 0),
    GeneralBook(800, 15 * 14, 1),
    LightBook(500, 15 * 14, 2),
    LargeBook(2000, 15 * 14, 3)
}