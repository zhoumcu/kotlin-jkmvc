package com.jkmvc.tests

import com.jkmvc.common.convertBytes
import com.jkmvc.common.findFunction
import com.jkmvc.common.findProperty
import com.jkmvc.common.to
import com.jkmvc.example.model.UserModel
import com.jkmvc.orm.RelationType
import com.jkmvc.validate.Validation
import org.junit.Test
import java.io.File
import java.lang.reflect.Field
import java.math.BigDecimal
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1

open class A() {}
class B():A() {
    protected val name:String = "test";
}

fun A.echo(){
    println("hi, I'm A")
}

fun B.echo(){
    println("hi, I'm B")
}

class MyTests{

    @Test
    fun testBig(){
        var a= BigDecimal("100")
        var b=BigDecimal("3")
        var c=a/b;
        println(c)
        var d=a.divide(b);
        println(d)
    }

    @Test
    fun testClass(){
        println(UserModel::class)
        println(this.javaClass)
        println(this.javaClass.kotlin)
        println(this::class)
    }

  /*  @Test
    fun testClassName(){
        println(MyTests::class.qualifiedName)
        println(Int::class.qualifiedName)
        println(String::class.qualifiedName)
    }

    @Test
    fun testMap(){
        val a:A = A()
        a.echo() // A
        val b:B = B()
        b.echo() // B
        val c:A = B()
        c.echo() // A
    }

    @Test
    fun testMeta(){
        println(UserModel.m::class.jvmName) // com.jkmvc.tests.UserModel$m
        println(UserModel.m::class.qualifiedName) // com.jkmvc.tests.UserModel.m -- dot
        println(UserModel.m::class.simpleName) // m
        //        println(UserModel.m::class.java.name) // com.jkmvc.tests.UserModel$m
//        println(UserModel.m::class.java.typeName) // com.jkmvc.tests.UserModel$m
//        println(UserModel.m::class.java.canonicalName) // com.jkmvc.tests.UserModel.m -- dot
//        println(UserModel.m::class.java.simpleName) // m
    }

    @Test
    fun testFunc(){
        val f: Lambda = { it:String ->
            it
        } as Lambda
        println(f.javaClass)
        println(f.javaClass.kotlin)
        println(f is KFunction<*>) // false
        println(f is KCallable<*>) // false
        println(f is Lambda) // true
        println(f.javaClass.superclass) // class kotlin.jvm.internal.Lambda
        println(f.javaClass.superclass.superclass) // Object
    }

    @Test
    fun testMember(){
        // kotlin.reflect.jvm.internal.KotlinReflectionInternalError: Reflection on built-in Kotlin types is not yet fully supported. No metadata found for public open val length: kotlin.Int defined in kotlin.String
        /*
        for(m in  String::class.declaredFunctions)
            println(m.name)
        */
        for(m in String.javaClass.methods)
            println(m.name)
    }

    @Test
    fun testType(){
        val type = Validation::trim.parameters[0].type
        println(type::class)
        println(type.javaClass)
        println(type.classifier)
    }
*/
    inline fun <reified T> to(value:String): T
    {
        val clazz:KClass<*> = T::class
        return value.to(clazz) as T
    }

    @Test
    fun testTo(){
       /*
       println("123".to(Int::class))
        println("123.45".to(Float::class))
        println("123.4567".to(Double::class))
        println("true".to(Boolean::class))
        */
        val i:Int? = to("1");
        println(i)
        val b:Boolean? = to("true")
        println(b)
        val f:Float? = to("1.23")
        println(f)
    }

    @Test
    fun testPattern(){
        val reg = "^\\d+$".toRegex()
//        println(reg.matches("123"));
//        println(reg.matches("123#"));
        println("hello".endsWith("")); // true
    }

    @Test
    fun testProperty(){
        // 获得不了getter/setter方法
//        println(UserModel::class.findFunction("getId")) // null
//        println(UserModel::class.findFunction("setName")) // null

        // 获得属性
        val p = UserModel::class.findProperty("id")!!
        println(p)
        println(p is KMutableProperty1) // true
        println(p::class) // class kotlin.reflect.jvm.internal.KMutableProperty1Impl

        // 获得参数类型
        println(p.getter.parameters)
        println(p.getter.parameters[0])
    }

    @Test
    fun testEnum(){
        for (v in RelationType.values())
            println("$v => ${v.ordinal}")
    }

    /**
     * 测试操作符
     * 原来像支持如linq之类的dsl，但是在处理 where(a > b) 的时候，你是无法修改操作符 > 的语义，他本来的语义就是2个对象做对比（调用compareTo()），返回boolean，但是我却想让他做字符串拼接，返回string
     * => 语法上就不支持，不可行
     */
    @Test
    fun testOperator(){
        println("a" < "b") // 调用compareTo()
        println("a" in "b") // 调用contains()
    }

    @Test
    fun testFileSizeUnit(){
        println('K'.convertBytes())
        println('M'.convertBytes())
        println('G'.convertBytes())
        println('T'.convertBytes())
    }

    @Test
    fun testDir(){
        val dir = File("upload")
        println(dir.absolutePath)  // /oldhome/shi/code/java/jkmvc/upload

        // 创建不存在的目录
        if(!dir.exists())
            dir.mkdirs();

        val f = File(dir, "test.txt")
        f.writeText("hello")
    }

    @Test
    fun testProp(){
        // 不能修改属性的访问权限
//        val prop: KProperty1<B, *> = B::class.findProperty("name") as KProperty1<B, *>
//        println(prop.get(B()))

        // kotlin类
        val field:Field = B::class.java.getDeclaredField("name").apply { isAccessible = true } // 修改属性的访问权限
        println(field.get(B()))

        // 试试java原生类
//        for(f in LinkedList::class.java.declaredFields)
//            println(f)

//        val field:Field = LinkedList::class.java.getDeclaredField("size").apply { isAccessible = true } // 修改属性的访问权限
//        val o:LinkedList<String> = LinkedList()
//        println(field.get(o))


    }

}




