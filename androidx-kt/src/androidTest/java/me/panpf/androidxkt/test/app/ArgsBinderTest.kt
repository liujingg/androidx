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
        activity.assert()
    }

    @Test
    fun testBindFragment() {
        val fragment = testRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as TestFragment
        fragment.assert()
    }

    @Test
    fun testBindResActivity() {
        val activity = resTestRule.activity
        activity.assert()
    }

    @Test
    fun testBindResFragment() {
        val fragment = resTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as ResTestFragment
        fragment.assert()
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

        private val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
        private val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
        private val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
        private val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
        private val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
        private val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)

        fun assert(){
            Assert.assertTrue(stringRequired == "stringRequired")
            Assert.assertTrue(stringOptional == "stringOptional")
            Assert.assertTrue(stringArrayRequired[0] == "stringRequired"
                    && stringArrayRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayOptional?.get(0) == "stringOptional"
                    && stringArrayOptional?.get(1) == "stringRequired")
            Assert.assertTrue(stringArrayListRequired[0] == "stringRequired"
                    && stringArrayListRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayListOptional?.get(0) == "stringOptional"
                    && stringArrayListOptional?.get(1) == "stringRequired")
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(me.panpf.androidxkt.test.R.layout.at_test)

            val fragment = ResTestFragment()
            fragment.arguments = ResTestFragment.createArguments(this)
            supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.test.R.id.testAt_frame, fragment).commit()
        }
    }

    class ResTestFragment : Fragment() {

        private val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
        private val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
        private val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
        private val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
        private val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
        private val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)

        fun assert(){
            Assert.assertTrue(stringRequired == "stringRequired")
            Assert.assertTrue(stringOptional == "stringOptional")
            Assert.assertTrue(stringArrayRequired[0] == "stringRequired"
                    && stringArrayRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayOptional?.get(0) == "stringOptional"
                    && stringArrayOptional?.get(1) == "stringRequired")
            Assert.assertTrue(stringArrayListRequired[0] == "stringRequired"
                    && stringArrayListRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayListOptional?.get(0) == "stringOptional"
                    && stringArrayListOptional?.get(1) == "stringRequired")
        }

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

        fun assert() {
            Assert.assertTrue(booleanRequired)
            Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
            Assert.assertTrue(booleanArrayOptional?.get(0) != true
                    && booleanArrayOptional?.get(1) ?: false)

            Assert.assertTrue(byteRequired == 2.toByte())
            Assert.assertTrue(byteArrayRequired[0] == 2.toByte()
                    && byteArrayRequired[1] == (-2).toByte())
            Assert.assertTrue(byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
                    && byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())

            Assert.assertTrue(charRequired == 'a')
            Assert.assertTrue(charArrayRequired[0] == 'a'
                    && charArrayRequired[1] == 'b')
            Assert.assertTrue(charArrayOptional?.get(0) ?: 0.toByte() == 'b'
                    && charArrayOptional?.get(1) ?: 0.toByte() == 'a')

            Assert.assertTrue(shortRequired == 3.toShort())
            Assert.assertTrue(shortArrayRequired[0] == 3.toShort()
                    && shortArrayRequired[1] == (-3).toShort())
            Assert.assertTrue(shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
                    && shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())

            Assert.assertTrue(floatRequired == 4f)
            Assert.assertTrue(floatArrayRequired[0] == 4f
                    && floatArrayRequired[1] == (-4f))
            Assert.assertTrue(floatArrayOptional?.get(0) ?: 0f == (-4f)
                    && floatArrayOptional?.get(1) ?: 0f == 4f)

            Assert.assertTrue(longRequired == 1000L)
            Assert.assertTrue(longArrayRequired[0] == 1000L
                    && longArrayRequired[1] == (-1000L))
            Assert.assertTrue(longArrayOptional?.get(0) ?: 0L == (-1000L)
                    && longArrayOptional?.get(1) ?: 0L == 1000L)

            Assert.assertTrue(intRequired == 500)
            Assert.assertTrue(intArrayRequired[0] == 500
                    && intArrayRequired[1] == (-500))
            Assert.assertTrue(intArrayOptional?.get(0) ?: 0 == (-500)
                    && intArrayOptional?.get(1) ?: 0 == 500)
            Assert.assertTrue(intArrayListRequired[0] == 500
                    && intArrayListRequired[1] == (-500))
            Assert.assertTrue(intArrayListOptional?.get(0) ?: 0 == (-500)
                    && intArrayListOptional?.get(1) ?: 0 == 500)

            Assert.assertTrue(doubleRequired == 6.toDouble())
            Assert.assertTrue(doubleArrayRequired[0] == 6.toDouble()
                    && doubleArrayRequired[1] == (-6).toDouble())
            Assert.assertTrue(doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
                    && doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())

            Assert.assertTrue(stringRequired == "stringRequired")
            Assert.assertTrue(stringOptional == "stringOptional")
            Assert.assertTrue(stringArrayRequired[0] == "stringRequired"
                    && stringArrayRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayOptional?.get(0) == "stringOptional"
                    && stringArrayOptional?.get(1) == "stringRequired")
            Assert.assertTrue(stringArrayListRequired[0] == "stringRequired"
                    && stringArrayListRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayListOptional?.get(0) == "stringOptional"
                    && stringArrayListOptional?.get(1) == "stringRequired")

            Assert.assertTrue(charSequenceRequired == "stringRequired")
            Assert.assertTrue(charSequenceOptional == "stringOptional")
            Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired"
                    && stringArrayRequired[1] == "stringOptional")
            Assert.assertTrue(charSequenceArrayOptional?.get(0) == "stringOptional"
                    && stringArrayOptional?.get(1) == "stringRequired")

            Assert.assertTrue(parcelableRequired == TestParcelable("parcelableRequired"))
            Assert.assertTrue(parcelableOptional == TestParcelable("parcelableOptional"))
            Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
                    && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
            Assert.assertTrue(parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
                    && parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
            Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
                    && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
            Assert.assertTrue(parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
                    && parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))

            Assert.assertTrue(serializableRequired == TestSerializable("serializableRequired"))
            Assert.assertTrue(serializableOptional == TestSerializable("serializableOptional"))

            Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
            Assert.assertTrue(bundleOptional?.getString("bundle") == "bundleOptional")
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

        private val byteRequired by bindByteArgOr("byteRequired")
        private val byteArrayRequired by bindByteArrayArg("byteArrayRequired")
        private val byteArrayOptional by bindByteArrayArgOrNull("byteArrayOptional")

        private val shortRequired by bindShortArgOr("shortRequired")
        private val shortArrayRequired by bindShortArrayArg("shortArrayRequired")
        private val shortArrayOptional by bindShortArrayArgOrNull("shortArrayOptional")

        private val intRequired by bindIntArgOr("intRequired")
        private val intArrayRequired by bindIntArrayArg("intArrayRequired")
        private val intArrayOptional by bindIntArrayArgOrNull("intArrayOptional")
        private val intArrayListRequired by bindIntArrayListArg("intArrayListRequired")
        private val intArrayListOptional by bindIntArrayListArgOrNull("intArrayListOptional")

        private val longRequired by bindLongArgOr("longRequired")
        private val longArrayRequired by bindLongArrayArg("longArrayRequired")
        private val longArrayOptional by bindLongArrayArgOrNull("longArrayOptional")

        private val floatRequired by bindFloatArgOr("floatRequired")
        private val floatArrayRequired by bindFloatArrayArg("floatArrayRequired")
        private val floatArrayOptional by bindFloatArrayArgOrNull("floatArrayOptional")

        private val doubleRequired by bindDoubleArgOr("doubleRequired")
        private val doubleArrayRequired by bindDoubleArrayArg("doubleArrayRequired")
        private val doubleArrayOptional by bindDoubleArrayArgOrNull("doubleArrayOptional")

        private val booleanRequired by bindBooleanArgOr("booleanRequired")
        private val booleanArrayRequired by bindBooleanArrayArg("booleanArrayRequired")
        private val booleanArrayOptional by bindBooleanArrayArgOrNull("booleanArrayOptional")

        private val charRequired by bindCharArgOr("charRequired")
        private val charArrayRequired by bindCharArrayArg("charArrayRequired")
        private val charArrayOptional by bindCharArrayArgOrNull("charArrayOptional")

        private val charSequenceRequired by bindCharSequenceArg("charSequenceRequired")
        private val charSequenceOptional by bindCharSequenceArgOrNull("charSequenceOptional")
        private val charSequenceArrayRequired by bindCharSequenceArrayArg("charSequenceArrayRequired")
        private val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull("charSequenceArrayOptional")

        private val stringRequired by bindStringArg("stringRequired")
        private val stringOptional by bindStringArgOrNull("stringOptional")
        private val stringArrayRequired by bindStringArrayArg("stringArrayRequired")
        private val stringArrayOptional by bindStringArrayArgOrNull("stringArrayOptional")
        private val stringArrayListRequired by bindStringArrayListArg("stringArrayListRequired")
        private val stringArrayListOptional by bindStringArrayListArgOrNull("stringArrayListOptional")

        private val parcelableRequired by bindParcelableArg<TestParcelable>("parcelableRequired")
        private val parcelableOptional by bindParcelableArgOrNull<TestParcelable>("parcelableOptional")
        private val parcelableArrayRequired by bindParcelableArrayArg<Parcelable>("parcelableArrayRequired")
        private val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
        private val parcelableArrayListRequired by bindParcelableArrayListArg<TestParcelable>("parcelableArrayListRequired")
        private val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>("parcelableArrayListOptional")

        private val serializableRequired by bindSerializableArg<TestSerializable>("serializableRequired")
        private val serializableOptional by bindSerializableArgOrNull<TestSerializable>("serializableOptional")

        private val bundleRequired by bindBundleArg("bundleRequired")
        private val bundleOptional by bindBundleArgOrNull("bundleOptional")

        fun assert(){
            Assert.assertTrue(booleanRequired)
            Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
            Assert.assertTrue(booleanArrayOptional?.get(0) != true
                    && booleanArrayOptional?.get(1) ?: false)

            Assert.assertTrue(byteRequired == 2.toByte())
            Assert.assertTrue(byteArrayRequired[0] == 2.toByte()
                    && byteArrayRequired[1] == (-2).toByte())
            Assert.assertTrue(byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
                    && byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())

            Assert.assertTrue(charRequired == 'a')
            Assert.assertTrue(charArrayRequired[0] == 'a'
                    && charArrayRequired[1] == 'b')
            Assert.assertTrue(charArrayOptional?.get(0) ?: 0.toByte() == 'b'
                    && charArrayOptional?.get(1) ?: 0.toByte() == 'a')

            Assert.assertTrue(shortRequired == 3.toShort())
            Assert.assertTrue(shortArrayRequired[0] == 3.toShort()
                    && shortArrayRequired[1] == (-3).toShort())
            Assert.assertTrue(shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
                    && shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())

            Assert.assertTrue(floatRequired == 4f)
            Assert.assertTrue(floatArrayRequired[0] == 4f
                    && floatArrayRequired[1] == (-4f))
            Assert.assertTrue(floatArrayOptional?.get(0) ?: 0f == (-4f)
                    && floatArrayOptional?.get(1) ?: 0f == 4f)

            Assert.assertTrue(longRequired == 1000L)
            Assert.assertTrue(longArrayRequired[0] == 1000L
                    && longArrayRequired[1] == (-1000L))
            Assert.assertTrue(longArrayOptional?.get(0) ?: 0L == (-1000L)
                    && longArrayOptional?.get(1) ?: 0L == 1000L)

            Assert.assertTrue(intRequired == 500)
            Assert.assertTrue(intArrayRequired[0] == 500
                    && intArrayRequired[1] == (-500))
            Assert.assertTrue(intArrayOptional?.get(0) ?: 0 == (-500)
                    && intArrayOptional?.get(1) ?: 0 == 500)
            Assert.assertTrue(intArrayListRequired[0] == 500
                    && intArrayListRequired[1] == (-500))
            Assert.assertTrue(intArrayListOptional?.get(0) ?: 0 == (-500)
                    && intArrayListOptional?.get(1) ?: 0 == 500)

            Assert.assertTrue(doubleRequired == 6.toDouble())
            Assert.assertTrue(doubleArrayRequired[0] == 6.toDouble()
                    && doubleArrayRequired[1] == (-6).toDouble())
            Assert.assertTrue(doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
                    && doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())

            Assert.assertTrue(stringRequired == "stringRequired")
            Assert.assertTrue(stringOptional == "stringOptional")
            Assert.assertTrue(stringArrayRequired[0] == "stringRequired"
                    && stringArrayRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayOptional?.get(0) == "stringOptional"
                    && stringArrayOptional?.get(1) == "stringRequired")
            Assert.assertTrue(stringArrayListRequired[0] == "stringRequired"
                    && stringArrayListRequired[1] == "stringOptional")
            Assert.assertTrue(stringArrayListOptional?.get(0) == "stringOptional"
                    && stringArrayListOptional?.get(1) == "stringRequired")

            Assert.assertTrue(charSequenceRequired == "stringRequired")
            Assert.assertTrue(charSequenceOptional == "stringOptional")
            Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired"
                    && stringArrayRequired[1] == "stringOptional")
            Assert.assertTrue(charSequenceArrayOptional?.get(0) == "stringOptional"
                    && stringArrayOptional?.get(1) == "stringRequired")

            Assert.assertTrue(parcelableRequired == TestParcelable("parcelableRequired"))
            Assert.assertTrue(parcelableOptional == TestParcelable("parcelableOptional"))
            Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
                    && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
            Assert.assertTrue(parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
                    && parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
            Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
                    && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
            Assert.assertTrue(parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
                    && parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))

            Assert.assertTrue(serializableRequired == TestSerializable("serializableRequired"))
            Assert.assertTrue(serializableOptional == TestSerializable("serializableOptional"))

            Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
            Assert.assertTrue(bundleOptional?.getString("bundle") == "bundleOptional")
        }

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