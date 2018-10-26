package me.panpf.androidxkt.test.app

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.test.app.args.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArgsBinderTest {

    @get:Rule
    val argsBinderTestRule: ActivityTestRule<ArgsBinderTestActivity> = object : ActivityTestRule<ArgsBinderTestActivity>(ArgsBinderTestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ArgsBinderTestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val argsBinderResTestRule: ActivityTestRule<ArgsBinderResTestActivity> = object : ActivityTestRule<ArgsBinderResTestActivity>(ArgsBinderResTestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ArgsBinderResTestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @Test
    fun testBindActivity() {
        val activity = argsBinderTestRule.activity

        Assert.assertTrue(activity.booleanRequired)
        Assert.assertTrue(activity.booleanArrayRequired[0] && !activity.booleanArrayRequired[1])
        Assert.assertTrue(!(activity.booleanArrayOptional?.get(0) == true)
                && activity.booleanArrayOptional?.get(1) ?: false)

        Assert.assertTrue(activity.byteRequired == 2.toByte())
        Assert.assertTrue(activity.byteArrayRequired[0] == 2.toByte()
                && activity.byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(activity.byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
                && activity.byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())

        Assert.assertTrue(activity.charRequired == 'a')
        Assert.assertTrue(activity.charArrayRequired[0] == 'a'
                && activity.charArrayRequired[1] == 'b')
        Assert.assertTrue(activity.charArrayOptional?.get(0) ?: 0.toByte() == 'b'
                && activity.charArrayOptional?.get(1) ?: 0.toByte() == 'a')

        Assert.assertTrue(activity.shortRequired == 3.toShort())
        Assert.assertTrue(activity.shortArrayRequired[0] == 3.toShort()
                && activity.shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(activity.shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
                && activity.shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())

        Assert.assertTrue(activity.floatRequired == 4f)
        Assert.assertTrue(activity.floatArrayRequired[0] == 4f
                && activity.floatArrayRequired[1] == (-4f))
        Assert.assertTrue(activity.floatArrayOptional?.get(0) ?: 0f == (-4f)
                && activity.floatArrayOptional?.get(1) ?: 0f == 4f)

        Assert.assertTrue(activity.longRequired == 1000L)
        Assert.assertTrue(activity.longArrayRequired[0] == 1000L
                && activity.longArrayRequired[1] == (-1000L))
        Assert.assertTrue(activity.longArrayOptional?.get(0) ?: 0L == (-1000L)
                && activity.longArrayOptional?.get(1) ?: 0L == 1000L)

        Assert.assertTrue(activity.intRequired == 500)
        Assert.assertTrue(activity.intArrayRequired[0] == 500
                && activity.intArrayRequired[1] == (-500))
        Assert.assertTrue(activity.intArrayOptional?.get(0) ?: 0 == (-500)
                && activity.intArrayOptional?.get(1) ?: 0 == 500)
        Assert.assertTrue(activity.intArrayListRequired[0] == 500
                && activity.intArrayListRequired[1] == (-500))
        Assert.assertTrue(activity.intArrayListOptional?.get(0) ?: 0 == (-500)
                && activity.intArrayListOptional?.get(1) ?: 0 == 500)

        Assert.assertTrue(activity.doubleRequired == 6.toDouble())
        Assert.assertTrue(activity.doubleArrayRequired[0] == 6.toDouble()
                && activity.doubleArrayRequired[1] == (-6).toDouble())
        Assert.assertTrue(activity.doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
                && activity.doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())

        Assert.assertTrue(activity.stringRequired == "stringRequired")
        Assert.assertTrue(activity.stringOptional == "stringOptional")
        Assert.assertTrue(activity.stringArrayRequired[0] == "stringRequired"
                && activity.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(activity.stringArrayOptional?.get(0) == "stringOptional"
                && activity.stringArrayOptional?.get(1) == "stringRequired")
        Assert.assertTrue(activity.stringArrayListRequired[0] == "stringRequired"
                && activity.stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(activity.stringArrayListOptional?.get(0) == "stringOptional"
                && activity.stringArrayListOptional?.get(1) == "stringRequired")

        Assert.assertTrue(activity.charSequenceRequired == "stringRequired")
        Assert.assertTrue(activity.charSequenceOptional == "stringOptional")
        Assert.assertTrue(activity.charSequenceArrayRequired[0] == "stringRequired"
                && activity.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(activity.charSequenceArrayOptional?.get(0) == "stringOptional"
                && activity.stringArrayOptional?.get(1) == "stringRequired")

        Assert.assertTrue(activity.parcelableRequired == TestParcelable("parcelableRequired"))
        Assert.assertTrue(activity.parcelableOptional == TestParcelable("parcelableOptional"))
        Assert.assertTrue(activity.parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
                && activity.parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(activity.parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
                && activity.parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
        Assert.assertTrue(activity.parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
                && activity.parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(activity.parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
                && activity.parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))

        Assert.assertTrue(activity.serializableRequired == TestSerializable("serializableRequired"))
        Assert.assertTrue(activity.serializableOptional == TestSerializable("serializableOptional"))

        Assert.assertTrue(activity.bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(activity.bundleOptional?.getString("bundle") == "bundleOptional")
    }

    @Test
    fun testBindFragment() {
        val fragment = argsBinderTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as ArgsBinderTestFragment

        Assert.assertTrue(fragment.booleanRequired)
        Assert.assertTrue(fragment.booleanArrayRequired[0] && !fragment.booleanArrayRequired[1])
        Assert.assertTrue(!(fragment.booleanArrayOptional?.get(0) == true)
                && fragment.booleanArrayOptional?.get(1) ?: false)

        Assert.assertTrue(fragment.byteRequired == 2.toByte())
        Assert.assertTrue(fragment.byteArrayRequired[0] == 2.toByte()
                && fragment.byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(fragment.byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
                && fragment.byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())

        Assert.assertTrue(fragment.charRequired == 'a')
        Assert.assertTrue(fragment.charArrayRequired[0] == 'a'
                && fragment.charArrayRequired[1] == 'b')
        Assert.assertTrue(fragment.charArrayOptional?.get(0) ?: 0.toByte() == 'b'
                && fragment.charArrayOptional?.get(1) ?: 0.toByte() == 'a')

        Assert.assertTrue(fragment.shortRequired == 3.toShort())
        Assert.assertTrue(fragment.shortArrayRequired[0] == 3.toShort()
                && fragment.shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(fragment.shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
                && fragment.shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())

        Assert.assertTrue(fragment.floatRequired == 4f)
        Assert.assertTrue(fragment.floatArrayRequired[0] == 4f
                && fragment.floatArrayRequired[1] == (-4f))
        Assert.assertTrue(fragment.floatArrayOptional?.get(0) ?: 0f == (-4f)
                && fragment.floatArrayOptional?.get(1) ?: 0f == 4f)

        Assert.assertTrue(fragment.longRequired == 1000L)
        Assert.assertTrue(fragment.longArrayRequired[0] == 1000L
                && fragment.longArrayRequired[1] == (-1000L))
        Assert.assertTrue(fragment.longArrayOptional?.get(0) ?: 0L == (-1000L)
                && fragment.longArrayOptional?.get(1) ?: 0L == 1000L)

        Assert.assertTrue(fragment.intRequired == 500)
        Assert.assertTrue(fragment.intArrayRequired[0] == 500
                && fragment.intArrayRequired[1] == (-500))
        Assert.assertTrue(fragment.intArrayOptional?.get(0) ?: 0 == (-500)
                && fragment.intArrayOptional?.get(1) ?: 0 == 500)
        Assert.assertTrue(fragment.intArrayListRequired[0] == 500
                && fragment.intArrayListRequired[1] == (-500))
        Assert.assertTrue(fragment.intArrayListOptional?.get(0) ?: 0 == (-500)
                && fragment.intArrayListOptional?.get(1) ?: 0 == 500)

        Assert.assertTrue(fragment.doubleRequired == 6.toDouble())
        Assert.assertTrue(fragment.doubleArrayRequired[0] == 6.toDouble()
                && fragment.doubleArrayRequired[1] == (-6).toDouble())
        Assert.assertTrue(fragment.doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
                && fragment.doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())

        Assert.assertTrue(fragment.stringRequired == "stringRequired")
        Assert.assertTrue(fragment.stringOptional == "stringOptional")
        Assert.assertTrue(fragment.stringArrayRequired[0] == "stringRequired"
                && fragment.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(fragment.stringArrayOptional?.get(0) == "stringOptional"
                && fragment.stringArrayOptional?.get(1) == "stringRequired")
        Assert.assertTrue(fragment.stringArrayListRequired[0] == "stringRequired"
                && fragment.stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(fragment.stringArrayListOptional?.get(0) == "stringOptional"
                && fragment.stringArrayListOptional?.get(1) == "stringRequired")

        Assert.assertTrue(fragment.charSequenceRequired == "stringRequired")
        Assert.assertTrue(fragment.charSequenceOptional == "stringOptional")
        Assert.assertTrue(fragment.charSequenceArrayRequired[0] == "stringRequired"
                && fragment.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(fragment.charSequenceArrayOptional?.get(0) == "stringOptional"
                && fragment.stringArrayOptional?.get(1) == "stringRequired")

        Assert.assertTrue(fragment.parcelableRequired == TestParcelable("parcelableRequired"))
        Assert.assertTrue(fragment.parcelableOptional == TestParcelable("parcelableOptional"))
        Assert.assertTrue(fragment.parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
                && fragment.parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(fragment.parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
                && fragment.parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
        Assert.assertTrue(fragment.parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
                && fragment.parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(fragment.parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
                && fragment.parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))

        Assert.assertTrue(fragment.serializableRequired == TestSerializable("serializableRequired"))
        Assert.assertTrue(fragment.serializableOptional == TestSerializable("serializableOptional"))

        Assert.assertTrue(fragment.bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(fragment.bundleOptional?.getString("bundle") == "bundleOptional")
    }

    @Test
    fun testBindResActivity() {
        val activity = argsBinderResTestRule.activity

        Assert.assertTrue(activity.stringRequired == "stringRequired")
        Assert.assertTrue(activity.stringOptional == "stringOptional")
        Assert.assertTrue(activity.stringArrayRequired[0] == "stringRequired"
                && activity.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(activity.stringArrayOptional?.get(0) == "stringOptional"
                && activity.stringArrayOptional?.get(1) == "stringRequired")
        Assert.assertTrue(activity.stringArrayListRequired[0] == "stringRequired"
                && activity.stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(activity.stringArrayListOptional?.get(0) == "stringOptional"
                && activity.stringArrayListOptional?.get(1) == "stringRequired")
    }

    @Test
    fun testBindResFragment() {
        val fragment = argsBinderResTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as ArgsBinderResTestFragment

        Assert.assertTrue(fragment.stringRequired == "stringRequired")
        Assert.assertTrue(fragment.stringOptional == "stringOptional")
        Assert.assertTrue(fragment.stringArrayRequired[0] == "stringRequired"
                && fragment.stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(fragment.stringArrayOptional?.get(0) == "stringOptional"
                && fragment.stringArrayOptional?.get(1) == "stringRequired")
        Assert.assertTrue(fragment.stringArrayListRequired[0] == "stringRequired"
                && fragment.stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(fragment.stringArrayListOptional?.get(0) == "stringOptional"
                && fragment.stringArrayListOptional?.get(1) == "stringRequired")
    }
}