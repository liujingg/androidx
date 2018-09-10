package me.panpf.androidxkt.args.test

import android.content.Intent
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.args.buildArgsActivityIntent
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArgsTest {

    @get:Rule
    val argsTestRule: ActivityTestRule<TestArgsActivity> = object : ActivityTestRule<TestArgsActivity>(TestArgsActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return InstrumentationRegistry.getContext().buildArgsActivityIntent(TestArgsActivity::class.java, TestArgsActivity.Args(2, TestParcelable("testArgsActivity")))
        }
    }

    @get:Rule
    val bindTestRule: ActivityTestRule<TestBindActivity> = object : ActivityTestRule<TestBindActivity>(TestBindActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent(InstrumentationRegistry.getContext(), TestBindActivity::class.java).apply {
                putExtra("booleanRequired", true)
                putExtra("booleanOptional", false)
                putExtra("booleanArrayRequired", booleanArrayOf(true, false))
                putExtra("booleanArrayOptional", booleanArrayOf(false, true))

                putExtra("byteRequired", 2.toByte())
                putExtra("byteOptional", (-2).toByte())
                putExtra("byteArrayRequired", byteArrayOf(2.toByte(), (-2).toByte()))
                putExtra("byteArrayOptional", byteArrayOf((-2).toByte(), 2.toByte()))

                putExtra("charRequired", 'a')
                putExtra("charOptional", 'b')
                putExtra("charArrayRequired", charArrayOf('a', 'b'))
                putExtra("charArrayOptional", charArrayOf('b', 'a'))

                putExtra("shortRequired", 3.toShort())
                putExtra("shortOptional", (-3).toShort())
                putExtra("shortArrayRequired", shortArrayOf(3.toShort(), (-3).toShort()))
                putExtra("shortArrayOptional", shortArrayOf((-3).toShort(), 3.toShort()))

                putExtra("floatRequired", 4f)
                putExtra("floatOptional", -4f)
                putExtra("floatArrayRequired", floatArrayOf(4f, -4f))
                putExtra("floatArrayOptional", floatArrayOf(-4f, 4f))

                putExtra("longRequired", 1000L)
                putExtra("longOptional", -1000L)
                putExtra("longArrayRequired", longArrayOf(1000L, -1000L))
                putExtra("longArrayOptional", longArrayOf(-1000L, 1000L))

                putExtra("intRequired", 500)
                putExtra("intOptional", -500)
                putExtra("intArrayRequired", intArrayOf(500, -500))
                putExtra("intArrayOptional", intArrayOf(-500, 500))
                putExtra("intArrayListRequired", arrayListOf(500, -500))
                putExtra("intArrayListOptional", arrayListOf(-500, 500))

                putExtra("doubleRequired", 6.toDouble())
                putExtra("doubleOptional", (-6).toDouble())
                putExtra("doubleArrayRequired", doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putExtra("doubleArrayOptional", doubleArrayOf((-6).toDouble(), 6.toDouble()))

                putExtra("stringRequired", "stringRequired")
                putExtra("stringOptional", "stringOptional")
                putExtra("stringArrayRequired", arrayOf("stringRequired", "stringOptional"))
                putExtra("stringArrayOptional", arrayOf("stringOptional", "stringRequired"))
                putExtra("stringArrayListRequired", arrayListOf("stringRequired", "stringOptional"))
                putExtra("stringArrayListOptional", arrayListOf("stringOptional", "stringRequired"))

                putExtra("charSequenceRequired", "stringRequired")
                putExtra("charSequenceOptional", "stringOptional")
                putExtra("charSequenceArrayRequired", arrayOf("stringRequired", "stringOptional"))
                putExtra("charSequenceArrayOptional", arrayOf("stringOptional", "stringRequired"))

                putExtra("parcelableRequired", TestParcelable("parcelableRequired"))
                putExtra("parcelableOptional", TestParcelable("parcelableOptional"))
                putExtra("parcelableArrayRequired", arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putExtra("parcelableArrayOptional", arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                putExtra("parcelableArrayListRequired", arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putExtra("parcelableArrayListOptional", arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))

                putExtra("serializableRequired", TestSerializable("serializableRequired"))
                putExtra("serializableOptional", TestSerializable("serializableOptional"))

                putExtra("bundleRequired", Bundle().apply { putString("bundle", "bundleRequired") })
                putExtra("bundleOptional", Bundle().apply { putString("bundle", "bundleOptional") })
            }
        }
    }

    @Test
    fun testArgsActivity() {
        Assert.assertTrue(argsTestRule.activity.args.int == 2)
        Assert.assertEquals(argsTestRule.activity.args.child.tag, "testArgsActivity")
    }

    @Test
    fun testArgsFragment() {
        val argsFragment = argsTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.args.test.R.id.testAt_frame) as TestArgsFragment

        Assert.assertTrue(argsFragment.args.int == 2)
        Assert.assertEquals(argsFragment.args.child.tag, "testArgsActivity")
    }

    @Test
    fun testBindActivity() {
        Assert.assertTrue(bindTestRule.activity.booleanRequired)
        Assert.assertTrue(bindTestRule.activity.booleanOptional == false)
        Assert.assertTrue(bindTestRule.activity.booleanArrayRequired[0] && !bindTestRule.activity.booleanArrayRequired[1])
        Assert.assertTrue(!(bindTestRule.activity.booleanArrayOptional?.get(0) == true)
                && bindTestRule.activity.booleanArrayOptional?.get(1) ?: false)

        Assert.assertTrue(bindTestRule.activity.byteRequired == 2.toByte())
        Assert.assertTrue(bindTestRule.activity.byteOptional == (-2).toByte())
        Assert.assertTrue(bindTestRule.activity.byteArrayRequired[0] == 2.toByte()
                && bindTestRule.activity.byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(bindTestRule.activity.byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
                && bindTestRule.activity.byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())

        Assert.assertTrue(bindTestRule.activity.charRequired == 'a')
        Assert.assertTrue(bindTestRule.activity.charOptional == 'b')
        Assert.assertTrue(bindTestRule.activity.charArrayRequired[0] == 'a'
                && bindTestRule.activity.charArrayRequired[1] == 'b')
        Assert.assertTrue(bindTestRule.activity.charArrayOptional?.get(0) ?: 0.toByte() == 'b'
                && bindTestRule.activity.charArrayOptional?.get(1) ?: 0.toByte() == 'a')

        Assert.assertTrue(bindTestRule.activity.shortRequired == 3.toShort())
        Assert.assertTrue(bindTestRule.activity.shortOptional == (-3).toShort())
        Assert.assertTrue(bindTestRule.activity.shortArrayRequired[0] == 3.toShort()
                && bindTestRule.activity.shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(bindTestRule.activity.shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
                && bindTestRule.activity.shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())

        Assert.assertTrue(bindTestRule.activity.floatRequired == 4f)
        Assert.assertTrue(bindTestRule.activity.floatOptional == (-4f))
        Assert.assertTrue(bindTestRule.activity.floatArrayRequired[0] == 4f
                && bindTestRule.activity.floatArrayRequired[1] == (-4f))
        Assert.assertTrue(bindTestRule.activity.floatArrayOptional?.get(0) ?: 0f == (-4f)
                && bindTestRule.activity.floatArrayOptional?.get(1) ?: 0f == 4f)

        Assert.assertTrue(bindTestRule.activity.longRequired == 1000L)
        Assert.assertTrue(bindTestRule.activity.longOptional == (-1000L))
        Assert.assertTrue(bindTestRule.activity.longArrayRequired[0] == 1000L
                && bindTestRule.activity.longArrayRequired[1] == (-1000L))
        Assert.assertTrue(bindTestRule.activity.longArrayOptional?.get(0) ?: 0L == (-1000L)
                && bindTestRule.activity.longArrayOptional?.get(1) ?: 0L == 1000L)

        Assert.assertTrue(bindTestRule.activity.intRequired == 500)
        Assert.assertTrue(bindTestRule.activity.intOptional == (-500))
        Assert.assertTrue(bindTestRule.activity.intArrayRequired[0] == 500
                && bindTestRule.activity.intArrayRequired[1] == (-500))
        Assert.assertTrue(bindTestRule.activity.intArrayOptional?.get(0) ?: 0 == (-500)
                && bindTestRule.activity.intArrayOptional?.get(1) ?: 0 == 500)
        Assert.assertTrue(bindTestRule.activity.intArrayListRequired[0] == 500
                && bindTestRule.activity.intArrayListRequired[1] == (-500))
        Assert.assertTrue(bindTestRule.activity.intArrayListOptional?.get(0) ?: 0 == (-500)
                && bindTestRule.activity.intArrayListOptional?.get(1) ?: 0 == 500)

        Assert.assertTrue(bindTestRule.activity.doubleRequired == 6.toDouble())
        Assert.assertTrue(bindTestRule.activity.doubleOptional == (-6).toDouble())
        Assert.assertTrue(bindTestRule.activity.doubleArrayRequired[0] == 6.toDouble()
                && bindTestRule.activity.doubleArrayRequired[1] == (-6).toDouble())
        Assert.assertTrue(bindTestRule.activity.doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
                && bindTestRule.activity.doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())

        Assert.assertTrue(bindTestRule.activity.stringRequired == "stringRequired")
        Assert.assertTrue(bindTestRule.activity.stringOptional == "stringOptional")
        Assert.assertTrue(bindTestRule.activity.stringArrayRequired[0] == "stringRequired"
                && bindTestRule.activity.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(bindTestRule.activity.stringArrayOptional?.get(0) == "stringOptional"
                && bindTestRule.activity.stringArrayOptional?.get(1) == "stringRequired")
        Assert.assertTrue(bindTestRule.activity.stringArrayListRequired[0] == "stringRequired"
                && bindTestRule.activity.stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(bindTestRule.activity.stringArrayListOptional?.get(0) == "stringOptional"
                && bindTestRule.activity.stringArrayListOptional?.get(1) == "stringRequired")

        Assert.assertTrue(bindTestRule.activity.charSequenceRequired == "stringRequired")
        Assert.assertTrue(bindTestRule.activity.charSequenceOptional == "stringOptional")
        Assert.assertTrue(bindTestRule.activity.charSequenceArrayRequired[0] == "stringRequired"
                && bindTestRule.activity.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(bindTestRule.activity.charSequenceArrayOptional?.get(0) == "stringOptional"
                && bindTestRule.activity.stringArrayOptional?.get(1) == "stringRequired")

        Assert.assertTrue(bindTestRule.activity.parcelableRequired == TestParcelable("parcelableRequired"))
        Assert.assertTrue(bindTestRule.activity.parcelableOptional == TestParcelable("parcelableOptional"))
        Assert.assertTrue(bindTestRule.activity.parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
                && bindTestRule.activity.parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(bindTestRule.activity.parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
                && bindTestRule.activity.parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
        Assert.assertTrue(bindTestRule.activity.parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
                && bindTestRule.activity.parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(bindTestRule.activity.parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
                && bindTestRule.activity.parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))

        Assert.assertTrue(bindTestRule.activity.serializableRequired == TestSerializable("serializableRequired"))
        Assert.assertTrue(bindTestRule.activity.serializableOptional == TestSerializable("serializableOptional"))

        Assert.assertTrue(bindTestRule.activity.bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bindTestRule.activity.bundleOptional?.getString("bundle") == "bundleOptional")
    }

    @Test
    fun testBindFragment() {
        val bindFragment = bindTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.args.test.R.id.testAt_frame) as TestBindFragment

        Assert.assertTrue(bindFragment.booleanRequired)
        Assert.assertTrue(bindFragment.booleanOptional == false)
        Assert.assertTrue(bindFragment.booleanArrayRequired[0] && !bindFragment.booleanArrayRequired[1])
        Assert.assertTrue(!(bindFragment.booleanArrayOptional?.get(0) == true)
                && bindFragment.booleanArrayOptional?.get(1) ?: false)

        Assert.assertTrue(bindFragment.byteRequired == 2.toByte())
        Assert.assertTrue(bindFragment.byteOptional == (-2).toByte())
        Assert.assertTrue(bindFragment.byteArrayRequired[0] == 2.toByte()
                && bindFragment.byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(bindFragment.byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
                && bindFragment.byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())

        Assert.assertTrue(bindFragment.charRequired == 'a')
        Assert.assertTrue(bindFragment.charOptional == 'b')
        Assert.assertTrue(bindFragment.charArrayRequired[0] == 'a'
                && bindFragment.charArrayRequired[1] == 'b')
        Assert.assertTrue(bindFragment.charArrayOptional?.get(0) ?: 0.toByte() == 'b'
                && bindFragment.charArrayOptional?.get(1) ?: 0.toByte() == 'a')

        Assert.assertTrue(bindFragment.shortRequired == 3.toShort())
        Assert.assertTrue(bindFragment.shortOptional == (-3).toShort())
        Assert.assertTrue(bindFragment.shortArrayRequired[0] == 3.toShort()
                && bindFragment.shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(bindFragment.shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
                && bindFragment.shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())

        Assert.assertTrue(bindFragment.floatRequired == 4f)
        Assert.assertTrue(bindFragment.floatOptional == (-4f))
        Assert.assertTrue(bindFragment.floatArrayRequired[0] == 4f
                && bindFragment.floatArrayRequired[1] == (-4f))
        Assert.assertTrue(bindFragment.floatArrayOptional?.get(0) ?: 0f == (-4f)
                && bindFragment.floatArrayOptional?.get(1) ?: 0f == 4f)

        Assert.assertTrue(bindFragment.longRequired == 1000L)
        Assert.assertTrue(bindFragment.longOptional == (-1000L))
        Assert.assertTrue(bindFragment.longArrayRequired[0] == 1000L
                && bindFragment.longArrayRequired[1] == (-1000L))
        Assert.assertTrue(bindFragment.longArrayOptional?.get(0) ?: 0L == (-1000L)
                && bindFragment.longArrayOptional?.get(1) ?: 0L == 1000L)

        Assert.assertTrue(bindFragment.intRequired == 500)
        Assert.assertTrue(bindFragment.intOptional == (-500))
        Assert.assertTrue(bindFragment.intArrayRequired[0] == 500
                && bindFragment.intArrayRequired[1] == (-500))
        Assert.assertTrue(bindFragment.intArrayOptional?.get(0) ?: 0 == (-500)
                && bindFragment.intArrayOptional?.get(1) ?: 0 == 500)
        Assert.assertTrue(bindFragment.intArrayListRequired[0] == 500
                && bindFragment.intArrayListRequired[1] == (-500))
        Assert.assertTrue(bindFragment.intArrayListOptional?.get(0) ?: 0 == (-500)
                && bindFragment.intArrayListOptional?.get(1) ?: 0 == 500)

        Assert.assertTrue(bindFragment.doubleRequired == 6.toDouble())
        Assert.assertTrue(bindFragment.doubleOptional == (-6).toDouble())
        Assert.assertTrue(bindFragment.doubleArrayRequired[0] == 6.toDouble()
                && bindFragment.doubleArrayRequired[1] == (-6).toDouble())
        Assert.assertTrue(bindFragment.doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
                && bindFragment.doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())

        Assert.assertTrue(bindFragment.stringRequired == "stringRequired")
        Assert.assertTrue(bindFragment.stringOptional == "stringOptional")
        Assert.assertTrue(bindFragment.stringArrayRequired[0] == "stringRequired"
                && bindFragment.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(bindFragment.stringArrayOptional?.get(0) == "stringOptional"
                && bindFragment.stringArrayOptional?.get(1) == "stringRequired")
        Assert.assertTrue(bindFragment.stringArrayListRequired[0] == "stringRequired"
                && bindFragment.stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(bindFragment.stringArrayListOptional?.get(0) == "stringOptional"
                && bindFragment.stringArrayListOptional?.get(1) == "stringRequired")

        Assert.assertTrue(bindFragment.charSequenceRequired == "stringRequired")
        Assert.assertTrue(bindFragment.charSequenceOptional == "stringOptional")
        Assert.assertTrue(bindFragment.charSequenceArrayRequired[0] == "stringRequired"
                && bindFragment.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(bindFragment.charSequenceArrayOptional?.get(0) == "stringOptional"
                && bindFragment.stringArrayOptional?.get(1) == "stringRequired")

        Assert.assertTrue(bindFragment.parcelableRequired == TestParcelable("parcelableRequired"))
        Assert.assertTrue(bindFragment.parcelableOptional == TestParcelable("parcelableOptional"))
        Assert.assertTrue(bindFragment.parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
                && bindFragment.parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(bindFragment.parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
                && bindFragment.parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
        Assert.assertTrue(bindFragment.parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
                && bindFragment.parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(bindFragment.parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
                && bindFragment.parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))

        Assert.assertTrue(bindFragment.serializableRequired == TestSerializable("serializableRequired"))
        Assert.assertTrue(bindFragment.serializableOptional == TestSerializable("serializableOptional"))

        Assert.assertTrue(bindFragment.bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bindFragment.bundleOptional?.getString("bundle") == "bundleOptional")
    }
}