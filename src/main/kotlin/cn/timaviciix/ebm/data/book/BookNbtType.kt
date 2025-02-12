package cn.timaviciix.ebm.data.book

enum class BookNbtType(
    val maxPage: Int,
    val charsPerPage: Int,
    val backgroundAndStorageType: Int,
) {
    //@Imp: Temp init
    //15 lines & 14 chars per line
    JournalBook(200, 198, 0),
    GeneralBook(800, 198, 1),
    LightBook(500, 198, 2),
    LargeBook(2000, 198, 3)
}