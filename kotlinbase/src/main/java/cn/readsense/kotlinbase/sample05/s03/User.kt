package simple05.s03

/**
 * 数据类  == Java实体Bean
 */

// 不会再修改了，可以是使用val
// get set 构造 equals hashCode toString，  copy
data class User(val id: Int, val name: String, val sex: Char)