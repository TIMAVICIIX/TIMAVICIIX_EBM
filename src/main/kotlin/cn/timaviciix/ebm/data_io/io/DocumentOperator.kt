/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.io
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-05  21:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.io

object DocumentOperator {

//    fun loadOrCreateDocument(fileName: String): Document {
//        val file = File(fileName)
//        return try {
//            if (file.extension == "ebm" || file.extension == "gz" || file.extension == "zip") {
//                GZIPInputStream(file.inputStream()).use { gzipIn ->
//                    val reader = InputStreamReader(gzipIn, Charsets.UTF_8)
//                    SAXReader().read(reader)
//                }
//            } else {
//                SAXReader().read(file)
//            }
//        } catch (e: Exception) {
//            if (e is FileNotFoundException) {
//                DocumentHelper.createDocument()
//            } else {
//                throw e
//            }
//        }
//    }
//
//    fun loadOrCreateDocument(fileName: URL): Document {
//        return try {
//            if (fileName.path.endsWith(".ebm") || fileName.path.endsWith(".gz") || fileName.path.endsWith(".zip")) {
//                GZIPInputStream(fileName.openStream()).use { gzipIn ->
//                    val reader = InputStreamReader(gzipIn, Charsets.UTF_8)
//                    SAXReader().read(reader)
//                }
//            } else {
//                SAXReader().read(fileName)
//            }
//        } catch (e: Exception) {
//            if (e is FileNotFoundException) {
//                DocumentHelper.createDocument()
//            } else {
//                throw e
//            }
//        }
//    }
//
//    fun loadOrCreateDocument(data: ByteArray): Document {
//        return try {
//            GZIPInputStream(data.inputStream()).use { gzipIn ->
//                val reader = InputStreamReader(gzipIn, Charsets.UTF_8)
//                SAXReader().read(reader)
//            }
//        } catch (e: Exception) {
//            if (e is FileNotFoundException) {
//                DocumentHelper.createDocument()
//            } else {
//                throw e
//            }
//        }
//    }
//
//    fun Document.deployAllElement(dataElement: DataTemplate) {
//        dataElement.elementTemplates.forEach {
//            if (it.storageType != StorageType.NBT) {
//                var element = this.rootElement
//                it.elementTree.forEach { branch ->
//                    element = element.addElement(branch)
//                }
//                element.addElement(it.elementID)
//                element.text = it.elementValue.toString()
//            }
//        }
//    }
//
//    fun <T> Document.deploySpecElement(element: ElementTemplate<T>) {
//        var currentElement = this.rootElement
//        element.elementTree.forEach { branch ->
//            currentElement = currentElement.addElement(branch)
//        }
//        currentElement.addElement(element.elementID)
//        currentElement.text = element.elementValue.toString()
//    }
//
//
//    fun buildPackage(document: Document): ByteArray {
//        // Step 1: Write XML to ByteArrayOutputStream with UTF-8
//        val xmlOut = ByteArrayOutputStream()
//        val format = OutputFormat.createPrettyPrint().apply {
//            encoding = "UTF-8"
//        }
//        val writer = XMLWriter(OutputStreamWriter(xmlOut, Charsets.UTF_8), format)
//        writer.write(document)
//        writer.flush()
//
//        // Step 2: GZIP
//        val gzipOut = ByteArrayOutputStream()
//        GZIPOutputStream(gzipOut).use { gzip ->
//            gzip.write(xmlOut.toByteArray())
//        }
//
//        return gzipOut.toByteArray()
//    }
//
//
//    fun save(document: Document, outputFile: File): Boolean {
//        try {
//            GZIPOutputStream(outputFile.outputStream()).use { gzipOut ->
//                val writer = OutputStreamWriter(gzipOut, Charsets.UTF_8)
//
//                val format = OutputFormat.createPrettyPrint()
//                format.encoding = "UTF-8"
//
//                val xmlWriter = XMLWriter(writer, format)
//                xmlWriter.write(document)
//                xmlWriter.flush()
//            }
//            return true
//        } catch (e: Exception) {
//            GlobalData.LOGGER.error(e.printStackTrace().toString())
//            return false
//        }
//    }
//

}