/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-18  12:47
 *@Description: 注解使用类，针对RegistryId进行注解使用，将使用该注解的属性赋予Item并注册，其流程无需硬编码item id
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

import cn.timaviciix.ebm.util.annotations.RegistryBlock
import cn.timaviciix.ebm.util.annotations.RegistryItem
import com.google.auto.service.AutoService
import net.minecraft.registry.DefaultedRegistry
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

// @Imp:Due to Kotlin's reflection optimization mechanism, property names are erased in the JVM, so the owo library cannot be used. Instead, custom annotations are declared as a replacement.
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_17)
class AutoRegister() : AbstractProcessor() {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)


    //DEBUG
    override fun init(processingEnv: ProcessingEnvironment?) {
        print("AutoRegister Init!!")
        super.init(processingEnv)
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            RegistryItem::class.java.canonicalName,
            RegistryBlock::class.java.canonicalName
        )
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {

        logger.info("Register Launched!")

        if (roundEnv == null) {
            logger.error("RoundEnv is null!")
            return false
        }

        roundEnv?.let {
            for (element in it.getElementsAnnotatedWith(RegistryItem::class.java)) {
                propertyProcess(element, Registries.ITEM)
            }
            for (element in it.getElementsAnnotatedWith(RegistryBlock::class.java)) {
                propertyProcess(element, Registries.BLOCK)
            }
        }
        return true
    }


    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> propertyProcess(element: Element, registryType: DefaultedRegistry<T>): Boolean {
        //get element Name and entity
        val field = element as VariableElement
        val fieldName = field.simpleName.toString()

        val lowercaseName = fieldName.lowercase()

        val defaultValue = field.constantValue as T

        var registryTarget: T? = null

        if (element.kind == ElementKind.FIELD) {
            //registry it
            registryTarget = registryProcess(registryType, lowercaseName, defaultValue)
        }

        //find and field property
        val clazz = field.enclosingElement as TypeElement
        val property = clazz::class.memberProperties.find { p -> p.name == fieldName }

        if (property != null && registryTarget != null) {
            property.isAccessible = true
            property.call(registryTarget)


        } else {
            throw TypeCastException("Can't Registry this Target ,Plz check it!")
        }
        return true
    }

    private fun <T : Any> registryProcess(registryType: DefaultedRegistry<T>, name: String, registryTarget: T): T {
        return Registry.register(registryType, Identifier(GlobalData.MOD_ID, name), registryTarget)
    }

}