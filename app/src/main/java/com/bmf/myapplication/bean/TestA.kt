package com.bmf.myapplication.bean

import android.content.Context
import android.widget.Toast
import kotlin.reflect.KProperty

sealed class TestA {
    fun test(context: Context) {
        Toast.makeText(context, "dsdsda", Toast.LENGTH_SHORT).show()
    }

    abstract fun ts()
}

class TestB : TestA() {
    override fun ts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}

class MyList<out T> {
    private var it: T? = null
    fun test(): T? {
        return it
    }

}

class Resource

class Owner {
    val valResource: Resource by ResourceDelegate()
}

class ResourceDelegate {
    operator fun getValue(thisRef: Owner, property: KProperty<*>): Resource {
        return Resource()
    }
}
