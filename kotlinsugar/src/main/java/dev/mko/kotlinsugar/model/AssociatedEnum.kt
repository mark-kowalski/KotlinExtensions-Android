package dev.mko.kotlinsugar.model

sealed class AssociatedEnum {

    object Unknown : AssociatedEnum()

    object UnknownSomething : AssociatedEnum()

    data class UnsupportedSomething(val someInformation: String?) : AssociatedEnum()

    data class Valid(override val value1: String,
                              override val value2: Int,
                              override val value3: Int?) : AssociatedEnum() {

        override val value4: Boolean
            get() {
                return (value2 > value3 ?: 1) && value1.isEmpty()
            }

        override fun getUpdatedValue2(newValue2: Int): AssociatedEnum {
            return copy(value2 = newValue2)
        }

        override fun getPairSomething(): Pair<String?, String>? {
            return Pair("", "")
        }

    }

    open val value1: String? = null
    open val value2: Int? = null
    open val value3: Int? = null

    open val value4: Boolean = false
    open val value5: Boolean
        get() = value2 ?: 0 > value3 ?: 1

    open fun getUpdatedValue2(newValue2: Int): AssociatedEnum = this

    open fun getPairSomething(): Pair<String?, String>? {
        return null
    }

}