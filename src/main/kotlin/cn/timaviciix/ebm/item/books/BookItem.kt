/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-13  22:07
 *@Description: 接口Book抽象类，定义抽象操作
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.books

import cn.timaviciix.ebm.EBMItemGroup
import cn.timaviciix.ebm.item.BaseItem
import cn.timaviciix.ebm.util.GeneralUtil
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.itemgroup.OwoItemSettings
import io.wispforest.owo.nbt.NbtKey
import net.minecraft.item.ItemStack


open class BookItem :BaseItem(OwoItemSettings().group(EBMItemGroup.EBM_ITEM_GROUP).maxCount(64)) {
    companion object {
        //final configurations
        // Max charsets per page
        private const val PAGE_CHAR_LIMIT = 2000


        //default attributes
        //Nbt Storage
        private val BOOK_ID_KEY = NbtKey(GlobalData.BOOK_ID, NbtKey.Type.STRING)
        private val PAGE_COUNT_KEY = NbtKey(GlobalData.PAGE_COUNT, NbtKey.Type.INT)
        private val COPY_PERMISSION_KEY = NbtKey(GlobalData.COPY_PERMISSION, NbtKey.Type.BOOLEAN)
        private val AUTHOR_KEY = NbtKey(GlobalData.AUTHOR, NbtKey.Type.STRING)


        class BookAttributes(private val stack: ItemStack) {
            var bookId: String = GeneralUtil.Nbt.getNbtValue(stack, BOOK_ID_KEY, "") {
                ensureBooID(stack)
            }
                set(value) {
                    field = GeneralUtil.Nbt.setNbtValue(stack, BOOK_ID_KEY, value, null)
                }
            var pageCount: Int = GeneralUtil.Nbt.getNbtValue(stack, PAGE_COUNT_KEY, 0, null)
                set(value) {
                    field = GeneralUtil.Nbt.setNbtValue(stack, PAGE_COUNT_KEY, value, null)
                }
            var copyPermission: Boolean = GeneralUtil.Nbt.getNbtValue(stack, COPY_PERMISSION_KEY, false, null)
                set(value) {
                    var copyPermission: Boolean = GeneralUtil.Nbt.getNbtValue(stack, COPY_PERMISSION_KEY, false, null)
                    field = GeneralUtil.Nbt.setNbtValue(stack, COPY_PERMISSION_KEY, value, null)
                }

            var author: String = GeneralUtil.Nbt.getNbtValue(stack, AUTHOR_KEY, "Unknown Author", null)
                set(value) {
                    field = GeneralUtil.Nbt.setNbtValue(stack, AUTHOR_KEY, value, null)
                }
        }


        /**
         * ensure book has uuid
         *@param stack book's ItemStack
         * @return If there is a UUID, it returns true, and if it is newly added, it returns false
         */
        private fun ensureBooID(stack: ItemStack): Boolean {
            if (!stack.has(BOOK_ID_KEY)) {
                stack.put(BOOK_ID_KEY, GeneralUtil.Uuid.generateShortUUID())
                return false
            }
            return true
        }

        fun setBookContent(stack: ItemStack, bookContent: String) {
            ensureBooID(stack)

            val pages = splitContentIntoPages(bookContent)

            for (i in 0..pages.size) {
                stack.put(NbtKey(GlobalData.PAGE_STORAGE_SUFFIX + (i + 1).toString(), NbtKey.Type.STRING), pages[i])
            }
            stack.put(PAGE_COUNT_KEY, pages.size)
        }

        /**
         * @param stack ItemStack
         * @param authorName Author's Name
         */
        fun setAuthor(stack: ItemStack, authorName: String) {
            stack.put(AUTHOR_KEY, authorName)
        }

        /**
         * @param copyPermission Copy Permission Boolean
         */
        fun setCopyPermission(stack: ItemStack, copyPermission: Boolean) {
            stack.put(COPY_PERMISSION_KEY, copyPermission)
        }

        /**
         * @param pageNumber current page number in current book
         */
        fun getCurrentPageContent(stack: ItemStack, pageNumber: Int): String {
            ensureBooID(stack)
            return stack.get(NbtKey(GlobalData.PAGE_STORAGE_SUFFIX + pageNumber, NbtKey.Type.STRING))
        }

        /**
         * The whole content is divided into multiple pages based on the word limit, and Nbt paging is stored to reduce memory usage
         * @param bookContent all Strings In TheBook
         * @return returns A List Of Pages With words
         */
        private fun splitContentIntoPages(bookContent: String): List<String> {
            val pages: MutableList<String> = mutableListOf()
            val charNumber = bookContent.length

            var i = 0
            while (i < charNumber) {
                val end = (i + PAGE_CHAR_LIMIT).coerceAtMost(charNumber)
                pages.add(bookContent.substring(i, end))
                i += PAGE_CHAR_LIMIT
            }
            return pages
        }

        fun isBookEmpty(stack: ItemStack): Boolean {
            return BookAttributes(stack).pageCount == 0
        }

        //default getters

//        fun getPageCount(stack: ItemStack): Int {
//            return stack.getOr(PAGE_COUNT_KEY, 0)
//        }
//
//        fun getAuthor(stack: ItemStack): String {
//            return stack.getOr(AUTHOR_KEY, "Unknown_Author")
//        }
//
//        fun canCopy(stack: ItemStack): Boolean {
//            return stack.getOr(COPY_PERMISSION_KEY, false)
//        }
//
//        fun getBookId(stack: ItemStack): String {
//            return stack.get(BOOK_ID_KEY)
//        }

        //tooltip
//        fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text?>, context: TooltipContext?) {
//            if (isBookEmpty(stack!!)) {
//                tooltip.add(Text.literal("This book is empty."))
//            } else {
//                tooltip.add(Text.literal("Author: " + getAuthor(stack)))
//                tooltip.add(Text.literal("Page 1: " + getCurrentPageContent(stack, 1)))
//                tooltip.add(Text.literal("Total Pages: " + getPageCount(stack)))
//                tooltip.add(Text.literal("Copy Allowed: " + (if (canCopy(stack)) "Yes" else "No")))
//            }
//        }
    }


}