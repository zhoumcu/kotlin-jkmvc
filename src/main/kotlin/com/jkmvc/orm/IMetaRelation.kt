package com.jkmvc.orm

import com.jkmvc.db.IDbQueryBuilder
import kotlin.reflect.KClass

/**
 * 关联类型
 * @author shijianhang
 * @date 2016-10-10
 */
enum class RelationType {
    /**
     * 关联关系 - 有一个
     *    当前表是主表, 关联表是从表
     */
    BELONGS_TO,

    /**
     * 关联关系 - 从属于
     *    当前表是从表, 关联表是主表
     */
    HAS_ONE,

    /**
     * 关联关系 - 有多个
     * 	当前表是主表, 关联表是从表
     */
    HAS_MANY;
}

/**
 * 关联关系的元数据
 * @author shijianhang
 * @date 2016-10-10
 */
interface IMetaRelation{

    /**
     *  关联关系
     */
    val type:RelationType;

    /**
     * 关联模型类型
     */
    val model: KClass<out IOrm>;

    /**
     *  外键
     */
    var foreignKey:String;

    /**
     *  查询条件
     */
    val conditions:((IDbQueryBuilder) -> Unit)?;

    /**
     * 获得关联模型的元数据
     *  伴随对象就是元数据
     */
    val metadata:IMetaData;

    /**
     * 获得关联模型的查询器
     */
    fun queryBuilder():OrmQueryBuilder;

}
