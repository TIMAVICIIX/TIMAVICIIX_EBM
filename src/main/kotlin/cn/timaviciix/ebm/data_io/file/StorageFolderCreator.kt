/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.book.storage
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-04-29  18:13
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.file

import cn.timaviciix.ebm.config.ConfigCreator
import java.nio.file.Files
import java.nio.file.Path

object StorageFolderCreator {

    val bookStoragePath: Path = Path.of(ConfigCreator.modConfigEntity.bookStoragePath)

    val bookDataStoragePath: Path = Path.of("$bookStoragePath\\book_data")
    val bookCommentStorage: Path = Path.of("$bookStoragePath\\book_comment")

    fun createStorageFolder() {
        if (!Files.exists(bookStoragePath)) {
            Files.createDirectory(bookStoragePath)
        }
        if (!Files.exists(bookCommentStorage)) {
            Files.createDirectory(bookCommentStorage)
        }
        if (!Files.exists(bookDataStoragePath)) {
            Files.createDirectory(bookDataStoragePath)
        }
    }

    fun createFolder(targetPath:Path,editorUUID:String){
        val targetFolder = Path.of(targetPath.toString(),editorUUID)
        if(!Files.exists(targetFolder)){
            Files.createDirectory(targetFolder)
        }
    }


}