package com.compose.myapplication.navigation.navtype

// class EnumNavType<T : Enum<T>>(private val enumClass: Class<T>) : NavType<T>(isNullableAllowed = false) {
//    override val name: String get() = enumClass.name
//
//    override fun put(
//        bundle: Bundle,
//        key: String,
//        value: T,
//    ) {
//        bundle.putString(key, value.name)
//    }
//
//    override fun get(
//        bundle: Bundle,
//        key: String,
//    ): T? {
//        val enumName = bundle.getString(key)
//        return enumName?.let { enumValueOf(enumClass, it) }
//    }
//
//    override fun parseValue(value: String): T {
//        return enumValueOf(enumClass, value)
//    }
//
//    private fun enumValueOf(
//        enumClass: Class<T>,
//        value: String,
//    ): T {
//        return java.lang.Enum.valueOf(enumClass, value)
//    }
// }
