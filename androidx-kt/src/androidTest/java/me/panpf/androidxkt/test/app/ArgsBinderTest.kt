package me.panpf.androidxkt.test.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlinx.android.parcel.Parcelize
import me.panpf.androidxkt.app.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArgsBinderTest {

    @get:Rule
    val testRule: ActivityTestRule<TestActivity> = object : ActivityTestRule<TestActivity>(TestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resTestRule: ActivityTestRule<ResTestActivity> = object : ActivityTestRule<ResTestActivity>(ResTestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @Test
    fun testBindActivity() {
        val activity = testRule.activity

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
        val fragment = testRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as TestFragment

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
        val activity = resTestRule.activity

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
        val fragment = resTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as ResTestFragment

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

    class ResTestActivity : FragmentActivity() {

        companion object {
            fun createIntent(context: Context): Intent {
                return Intent(context, ResTestActivity::class.java).apply {
                    putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_required), "stringRequired")
                    putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_optional), "stringOptional")
                    putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                    putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                    putStringArrayListExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                    putStringArrayListExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))
                }
            }
        }

        val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
        val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
        val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
        val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
        val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
        val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(me.panpf.androidxkt.test.R.layout.at_test)

            val fragment = ResTestFragment()
            fragment.arguments = ResTestFragment.createArguments(this)
            supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.test.R.id.testAt_frame, fragment).commit()
        }
    }

    class ResTestFragment : Fragment() {

        val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
        val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
        val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
        val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
        val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
        val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)

        companion object {
            fun createArguments(context: Context): Bundle = Bundle().apply {
                putString(context.getString(me.panpf.androidxkt.test.R.string.string_required), "stringRequired")
                putString(context.getString(me.panpf.androidxkt.test.R.string.string_optional), "stringOptional")
                putStringArray(context.getString(me.panpf.androidxkt.test.R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                putStringArray(context.getString(me.panpf.androidxkt.test.R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                putStringArrayList(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                putStringArrayList(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))
            }
        }
    }

    class TestActivity : FragmentActivity() {

        val byteRequired by bindByteArgOr("byteRequired")
        val byteArrayRequired by bindByteArrayArg("byteArrayRequired")
        val byteArrayOptional by bindByteArrayArgOrNull("byteArrayOptional")

        val shortRequired by bindShortArgOr("shortRequired")
        val shortArrayRequired by bindShortArrayArg("shortArrayRequired")
        val shortArrayOptional by bindShortArrayArgOrNull("shortArrayOptional")

        val intRequired by bindIntArgOr("intRequired")
        val intArrayRequired by bindIntArrayArg("intArrayRequired")
        val intArrayOptional by bindIntArrayArgOrNull("intArrayOptional")
        val intArrayListRequired by bindIntArrayListArg("intArrayListRequired")
        val intArrayListOptional by bindIntArrayListArgOrNull("intArrayListOptional")

        val longRequired by bindLongArgOr("longRequired")
        val longArrayRequired by bindLongArrayArg("longArrayRequired")
        val longArrayOptional by bindLongArrayArgOrNull("longArrayOptional")

        val floatRequired by bindFloatArgOr("floatRequired")
        val floatArrayRequired by bindFloatArrayArg("floatArrayRequired")
        val floatArrayOptional by bindFloatArrayArgOrNull("floatArrayOptional")

        val doubleRequired by bindDoubleArgOr("doubleRequired")
        val doubleArrayRequired by bindDoubleArrayArg("doubleArrayRequired")
        val doubleArrayOptional by bindDoubleArrayArgOrNull("doubleArrayOptional")

        val booleanRequired by bindBooleanArgOr("booleanRequired")
        val booleanArrayRequired by bindBooleanArrayArg("booleanArrayRequired")
        val booleanArrayOptional by bindBooleanArrayArgOrNull("booleanArrayOptional")

        val charRequired by bindCharArgOr("charRequired")
        val charArrayRequired by bindCharArrayArg("charArrayRequired")
        val charArrayOptional by bindCharArrayArgOrNull("charArrayOptional")

        val charSequenceRequired by bindCharSequenceArg("charSequenceRequired")
        val charSequenceOptional by bindCharSequenceArgOrNull("charSequenceOptional")
        val charSequenceArrayRequired by bindCharSequenceArrayArg("charSequenceArrayRequired")
        val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull("charSequenceArrayOptional")

        val stringRequired by bindStringArg("stringRequired")
        val stringOptional by bindStringArgOrNull("stringOptional")
        val stringArrayRequired by bindStringArrayArg("stringArrayRequired")
        val stringArrayOptional by bindStringArrayArgOrNull("stringArrayOptional")
        val stringArrayListRequired by bindStringArrayListArg("stringArrayListRequired")
        val stringArrayListOptional by bindStringArrayListArgOrNull("stringArrayListOptional")

        val parcelableRequired by bindParcelableArg<TestParcelable>("parcelableRequired")
        val parcelableOptional by bindParcelableArgOrNull<TestParcelable>("parcelableOptional")
        val parcelableArrayRequired by bindParcelableArrayArg<Parcelable>("parcelableArrayRequired")
        val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
        val parcelableArrayListRequired by bindParcelableArrayListArg<TestParcelable>("parcelableArrayListRequired")
        val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>("parcelableArrayListOptional")

        val serializableRequired by bindSerializableArg<TestSerializable>("serializableRequired")
        val serializableOptional by bindSerializableArgOrNull<TestSerializable>("serializableOptional")

        val bundleRequired by bindBundleArg("bundleRequired")
        val bundleOptional by bindBundleArgOrNull("bundleOptional")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(me.panpf.androidxkt.test.R.layout.at_test)

            val fragment = TestFragment()
            fragment.arguments = TestFragment.createArguments(this)
            supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.test.R.id.testAt_frame, fragment).commit()
        }

        companion object {
            fun createIntent(context: Context): Intent {
                return Intent(context, TestActivity::class.java).apply {

                    putExtra("byteRequired", 2.toByte())
                    putExtra("byteArrayRequired", byteArrayOf(2.toByte(), (-2).toByte()))
                    putExtra("byteArrayOptional", byteArrayOf((-2).toByte(), 2.toByte()))

                    putExtra("shortRequired", 3.toShort())
                    putExtra("shortArrayRequired", shortArrayOf(3.toShort(), (-3).toShort()))
                    putExtra("shortArrayOptional", shortArrayOf((-3).toShort(), 3.toShort()))

                    putExtra("intRequired", 500)
                    putExtra("intArrayRequired", intArrayOf(500, -500))
                    putExtra("intArrayOptional", intArrayOf(-500, 500))
                    putIntegerArrayListExtra("intArrayListRequired", arrayListOf(500, -500))
                    putIntegerArrayListExtra("intArrayListOptional", arrayListOf(-500, 500))

                    putExtra("longRequired", 1000L)
                    putExtra("longArrayRequired", longArrayOf(1000L, -1000L))
                    putExtra("longArrayOptional", longArrayOf(-1000L, 1000L))

                    putExtra("floatRequired", 4f)
                    putExtra("floatArrayRequired", floatArrayOf(4f, -4f))
                    putExtra("floatArrayOptional", floatArrayOf(-4f, 4f))

                    putExtra("doubleRequired", 6.toDouble())
                    putExtra("doubleArrayRequired", doubleArrayOf(6.toDouble(), (-6).toDouble()))
                    putExtra("doubleArrayOptional", doubleArrayOf((-6).toDouble(), 6.toDouble()))

                    putExtra("booleanRequired", true)
                    putExtra("booleanArrayRequired", booleanArrayOf(true, false))
                    putExtra("booleanArrayOptional", booleanArrayOf(false, true))

                    putExtra("charRequired", 'a')
                    putExtra("charArrayRequired", charArrayOf('a', 'b'))
                    putExtra("charArrayOptional", charArrayOf('b', 'a'))

                    putExtra("stringRequired", "stringRequired")
                    putExtra("stringOptional", "stringOptional")
                    putExtra("stringArrayRequired", arrayOf("stringRequired", "stringOptional"))
                    putExtra("stringArrayOptional", arrayOf("stringOptional", "stringRequired"))
                    putStringArrayListExtra("stringArrayListRequired", arrayListOf("stringRequired", "stringOptional"))
                    putStringArrayListExtra("stringArrayListOptional", arrayListOf("stringOptional", "stringRequired"))

                    putExtra("charSequenceRequired", "stringRequired")
                    putExtra("charSequenceOptional", "stringOptional")
                    putExtra("charSequenceArrayRequired", arrayOf("stringRequired", "stringOptional"))
                    putExtra("charSequenceArrayOptional", arrayOf("stringOptional", "stringRequired"))

                    putExtra("parcelableRequired", TestParcelable("parcelableRequired"))
                    putExtra("parcelableOptional", TestParcelable("parcelableOptional"))
                    putExtra("parcelableArrayRequired", arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                    putExtra("parcelableArrayOptional", arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                    putParcelableArrayListExtra("parcelableArrayListRequired", arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                    putParcelableArrayListExtra("parcelableArrayListOptional", arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))

                    putExtra("serializableRequired", TestSerializable("serializableRequired"))
                    putExtra("serializableOptional", TestSerializable("serializableOptional"))

                    putExtra("bundleRequired", Bundle().apply { putString("bundle", "bundleRequired") })
                    putExtra("bundleOptional", Bundle().apply { putString("bundle", "bundleOptional") })
                }
            }
        }
    }

    class TestFragment : Fragment() {

        val byteRequired by bindByteArgOr("byteRequired")
        val byteArrayRequired by bindByteArrayArg("byteArrayRequired")
        val byteArrayOptional by bindByteArrayArgOrNull("byteArrayOptional")

        val shortRequired by bindShortArgOr("shortRequired")
        val shortArrayRequired by bindShortArrayArg("shortArrayRequired")
        val shortArrayOptional by bindShortArrayArgOrNull("shortArrayOptional")

        val intRequired by bindIntArgOr("intRequired")
        val intArrayRequired by bindIntArrayArg("intArrayRequired")
        val intArrayOptional by bindIntArrayArgOrNull("intArrayOptional")
        val intArrayListRequired by bindIntArrayListArg("intArrayListRequired")
        val intArrayListOptional by bindIntArrayListArgOrNull("intArrayListOptional")

        val longRequired by bindLongArgOr("longRequired")
        val longArrayRequired by bindLongArrayArg("longArrayRequired")
        val longArrayOptional by bindLongArrayArgOrNull("longArrayOptional")

        val floatRequired by bindFloatArgOr("floatRequired")
        val floatArrayRequired by bindFloatArrayArg("floatArrayRequired")
        val floatArrayOptional by bindFloatArrayArgOrNull("floatArrayOptional")

        val doubleRequired by bindDoubleArgOr("doubleRequired")
        val doubleArrayRequired by bindDoubleArrayArg("doubleArrayRequired")
        val doubleArrayOptional by bindDoubleArrayArgOrNull("doubleArrayOptional")

        val booleanRequired by bindBooleanArgOr("booleanRequired")
        val booleanArrayRequired by bindBooleanArrayArg("booleanArrayRequired")
        val booleanArrayOptional by bindBooleanArrayArgOrNull("booleanArrayOptional")

        val charRequired by bindCharArgOr("charRequired")
        val charArrayRequired by bindCharArrayArg("charArrayRequired")
        val charArrayOptional by bindCharArrayArgOrNull("charArrayOptional")

        val charSequenceRequired by bindCharSequenceArg("charSequenceRequired")
        val charSequenceOptional by bindCharSequenceArgOrNull("charSequenceOptional")
        val charSequenceArrayRequired by bindCharSequenceArrayArg("charSequenceArrayRequired")
        val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull("charSequenceArrayOptional")

        val stringRequired by bindStringArg("stringRequired")
        val stringOptional by bindStringArgOrNull("stringOptional")
        val stringArrayRequired by bindStringArrayArg("stringArrayRequired")
        val stringArrayOptional by bindStringArrayArgOrNull("stringArrayOptional")
        val stringArrayListRequired by bindStringArrayListArg("stringArrayListRequired")
        val stringArrayListOptional by bindStringArrayListArgOrNull("stringArrayListOptional")

        val parcelableRequired by bindParcelableArg<TestParcelable>("parcelableRequired")
        val parcelableOptional by bindParcelableArgOrNull<TestParcelable>("parcelableOptional")
        val parcelableArrayRequired by bindParcelableArrayArg<Parcelable>("parcelableArrayRequired")
        val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
        val parcelableArrayListRequired by bindParcelableArrayListArg<TestParcelable>("parcelableArrayListRequired")
        val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>("parcelableArrayListOptional")

        val serializableRequired by bindSerializableArg<TestSerializable>("serializableRequired")
        val serializableOptional by bindSerializableArgOrNull<TestSerializable>("serializableOptional")

        val bundleRequired by bindBundleArg("bundleRequired")
        val bundleOptional by bindBundleArgOrNull("bundleOptional")

        companion object {
            fun createArguments(activity: TestActivity): Bundle = Bundle().apply {
                putByte("byteRequired", activity.byteRequired)
                putByteArray("byteArrayRequired", activity.byteArrayRequired)
                activity.byteArrayOptional?.let { putByteArray("byteArrayOptional", it) }

                putShort("shortRequired", activity.shortRequired)
                putShortArray("shortArrayRequired", activity.shortArrayRequired)
                activity.shortArrayOptional?.let { putShortArray("shortArrayOptional", it) }

                putInt("intRequired", activity.intRequired)
                putIntArray("intArrayRequired", activity.intArrayRequired)
                activity.intArrayOptional?.let { putIntArray("intArrayOptional", it) }
                putIntegerArrayList("intArrayListRequired", activity.intArrayListRequired)
                activity.intArrayListOptional?.let { putIntegerArrayList("intArrayListOptional", it) }

                putLong("longRequired", activity.longRequired)
                putLongArray("longArrayRequired", activity.longArrayRequired)
                activity.longArrayOptional?.let { putLongArray("longArrayOptional", it) }

                putFloat("floatRequired", activity.floatRequired)
                putFloatArray("floatArrayRequired", activity.floatArrayRequired)
                activity.floatArrayOptional?.let { putFloatArray("floatArrayOptional", it) }

                putDouble("doubleRequired", activity.doubleRequired)
                putDoubleArray("doubleArrayRequired", activity.doubleArrayRequired)
                activity.doubleArrayOptional?.let { putDoubleArray("doubleArrayOptional", it) }

                putBoolean("booleanRequired", activity.booleanRequired)
                putBooleanArray("booleanArrayRequired", activity.booleanArrayRequired)
                activity.booleanArrayOptional?.let { putBooleanArray("booleanArrayOptional", it) }

                putChar("charRequired", activity.charRequired)
                putCharArray("charArrayRequired", activity.charArrayRequired)
                activity.charArrayOptional?.let { putCharArray("charArrayOptional", it) }

                putString("stringRequired", activity.stringRequired)
                activity.stringOptional?.let { putString("stringOptional", it) }
                putStringArray("stringArrayRequired", activity.stringArrayRequired)
                activity.stringArrayOptional?.let { putStringArray("stringArrayOptional", it) }
                putStringArrayList("stringArrayListRequired", activity.stringArrayListRequired)
                activity.stringArrayListOptional?.let { putStringArrayList("stringArrayListOptional", it) }

                putCharSequence("charSequenceRequired", activity.charSequenceRequired)
                activity.charSequenceOptional?.let { putCharSequence("charSequenceOptional", it) }
                putCharSequenceArray("charSequenceArrayRequired", activity.charSequenceArrayRequired)
                activity.charSequenceArrayOptional?.let { putCharSequenceArray("charSequenceArrayOptional", it) }

                putParcelable("parcelableRequired", activity.parcelableRequired)
                activity.parcelableOptional?.let { putParcelable("parcelableOptional", it) }
                putParcelableArray("parcelableArrayRequired", activity.parcelableArrayRequired)
                activity.parcelableArrayOptional?.let { putParcelableArray("parcelableArrayOptional", it) }
                putParcelableArrayList("parcelableArrayListRequired", activity.parcelableArrayListRequired)
                activity.parcelableArrayListOptional?.let { putParcelableArrayList("parcelableArrayListOptional", it) }

                putSerializable("serializableRequired", activity.serializableRequired)
                activity.serializableOptional?.let { putSerializable("serializableOptional", it) }

                putBundle("bundleRequired", activity.bundleRequired)
                activity.bundleOptional?.let { putBundle("bundleOptional", it) }
            }
        }
    }

    @Parcelize
    data class TestParcelable(val tag: String) : Parcelable

    data class TestSerializable(val tag: String) : java.io.Serializable
}