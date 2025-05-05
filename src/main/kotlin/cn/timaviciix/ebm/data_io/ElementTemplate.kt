package cn.timaviciix.ebm.data_io

class ElementTemplate<T>(
    val elementID:String,
    val elementValue:T,
    val storageType:TransType,
    val elementTree:List<String> = mutableListOf(),
    val elementAttr:List<Map<String,String>> = mutableListOf()
){
    override fun toString(): String {
        return elementValue.toString()
    }

}
