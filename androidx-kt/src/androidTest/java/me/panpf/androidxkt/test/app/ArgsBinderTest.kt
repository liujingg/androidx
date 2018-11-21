package me.panpf.androidxkt.test.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.os.Parcelable
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import kotlinx.android.parcel.Parcelize
import me.panpf.androidxkt.app.*
import me.panpf.androidxkt.test.R
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
    val testNoExtrasRule: ActivityTestRule<TestNoExtrasActivity> = object : ActivityTestRule<TestNoExtrasActivity>(TestNoExtrasActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestNoExtrasActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resTestRule: ActivityTestRule<ResTestActivity> = object : ActivityTestRule<ResTestActivity>(ResTestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val uriTestRule: ActivityTestRule<TestUriActivity> = object : ActivityTestRule<TestUriActivity>(TestUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestUriActivity.createIntent()
        }
    }

    @get:Rule
    val resUriTestRule: ActivityTestRule<ResTestUriActivity> = object : ActivityTestRule<ResTestUriActivity>(ResTestUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestUriActivity.createIntent()
        }
    }

    @get:Rule
    val uriOnlyTestOnlyRuleNo: ActivityTestRule<TestOnlyUriNoIntentActivity> = object : ActivityTestRule<TestOnlyUriNoIntentActivity>(TestOnlyUriNoIntentActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestOnlyUriNoIntentActivity.createIntentWithUri()
        }
    }

    @get:Rule
    val intentOnlyTestRuleNoUri: ActivityTestRule<TestOnlyIntentNoUriActivity> = object : ActivityTestRule<TestOnlyIntentNoUriActivity>(TestOnlyIntentNoUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestOnlyIntentNoUriActivity.createIntentWithExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val uriIntentTestRuleUri: ActivityTestRule<TestBothIntentUriActivity> = object : ActivityTestRule<TestBothIntentUriActivity>(TestBothIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestBothIntentUriActivity.createIntentWithUriAndExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val nothingTestRuleUri: ActivityTestRule<TestNoIntentUriActivity> = object : ActivityTestRule<TestNoIntentUriActivity>(TestNoIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestNoIntentUriActivity.createIntentWithNothing(InstrumentationRegistry.getContext())
        }
    }

    @Test
    fun testUriActivity() {
        val activity = uriTestRule.activity
        activity.assert()
    }

    @Test
    fun testResUriActivity() {
        val activity = resUriTestRule.activity
        activity.assert()
    }

    @Test
    fun testUriOnlyActivity() {
        val activity = uriOnlyTestOnlyRuleNo.activity
        activity.assertByUri()
    }

    @Test
    fun testIntentOnlyActivity() {
        val activity = intentOnlyTestRuleNoUri.activity
        activity.assertByIntent()
    }

    @Test
    fun testUriIntentActivity() {
        val activity = uriIntentTestRuleUri.activity
        activity.assertByUriIntent()
    }

    @Test
    fun testNothingActivity() {
        val activity = nothingTestRuleUri.activity
        activity.assertByNothing()
    }

//    @Test
//    fun testBindSupportActivity() {
//        val activity = testRule.activity
//        activity.assert()
//    }
//
//    @Test
//    fun testBindNoExtrasActivity() {
//        val activity = testNoExtrasRule.activity
//        activity.assert()
//    }
//
//    @Test
//    fun testBindSupportFragment() {
//        val fragment = testRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as TestSupportFragment
//        fragment.assert()
//    }
//
//    @Test
//    fun testBindFragment() {
//        val fragment = testRule.activity.fragmentManager.findFragmentById(android.R.id.content) as TestFragment
//        fragment.assert()
//    }
//
//    @Test
//    fun testBindResActivity() {
//        val activity = resTestRule.activity
//        activity.assert()
//    }
//
//    @Test
//    fun testBindResSupportFragment() {
//        val fragment = resTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.test.R.id.testAt_frame) as ResTestSupportFragment
//        fragment.assert()
//    }
//
//    @Test
//    fun testBindResFragment() {
//        val fragment = resTestRule.activity.fragmentManager.findFragmentById(android.R.id.content) as ResTestFragment
//        fragment.assert()
//    }

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

                    putExtra(context.getString(R.string.byte_required), 2.toByte())
                    putExtra(context.getString(R.string.byte_array_required), byteArrayOf(2.toByte(), (-2).toByte()))
                    putExtra(context.getString(R.string.byte_array_optional), byteArrayOf((-2).toByte(), 2.toByte()))

                    putExtra(context.getString(R.string.short_required), 3.toShort())
                    putExtra(context.getString(R.string.short_array_required), shortArrayOf(3.toShort(), (-3).toShort()))
                    putExtra(context.getString(R.string.short_array_optional), shortArrayOf((-3).toShort(), 3.toShort()))

                    putExtra(context.getString(R.string.int_required), 500)
                    putExtra(context.getString(R.string.int_array_required), intArrayOf(500, -500))
                    putExtra(context.getString(R.string.int_array_optional), intArrayOf(-500, 500))
                    putIntegerArrayListExtra(context.getString(R.string.int_array_list_required), arrayListOf(500, -500))
                    putIntegerArrayListExtra(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500))

                    putExtra(context.getString(R.string.long_required), 1000L)
                    putExtra(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L))
                    putExtra(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L))

                    putExtra(context.getString(R.string.float_required), 4f)
                    putExtra(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f))
                    putExtra(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f))

                    putExtra(context.getString(R.string.double_required), 6.toDouble())
                    putExtra(context.getString(R.string.double_array_required), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                    putExtra(context.getString(R.string.double_array_optional), doubleArrayOf((-6).toDouble(), 6.toDouble()))

                    putExtra(context.getString(R.string.boolean_required), true)
                    putExtra(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false))
                    putExtra(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true))

                    putExtra(context.getString(R.string.char_required), 'a')
                    putExtra(context.getString(R.string.char_array_required), charArrayOf('a', 'b'))
                    putExtra(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'))


                    putExtra(context.getString(R.string.char_sequence_required), "stringRequired")
                    putExtra(context.getString(R.string.char_sequence_optional), "stringOptional")
                    putExtra(context.getString(R.string.char_sequence_array_required), arrayOf("stringRequired", "stringOptional"))
                    putExtra(context.getString(R.string.char_sequence_array_optional), arrayOf("stringOptional", "stringRequired"))

                    putExtra(context.getString(R.string.parcelable_required), TestParcelable("parcelableRequired"))
                    putExtra(context.getString(R.string.parcelable_optional), TestParcelable("parcelableOptional"))
                    putExtra(context.getString(R.string.parcelable_array_required), arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                    putExtra(context.getString(R.string.parcelable_array_optional), arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                    putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_required), arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                    putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_optional), arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))

                    putExtra(context.getString(R.string.serializable_required), TestSerializable("serializableRequired"))
                    putExtra(context.getString(R.string.serializable_optional), TestSerializable("serializableOptional"))

                    putExtra(context.getString(R.string.bundle_required), Bundle().apply { putString("bundle", "bundleRequired") })
                    putExtra(context.getString(R.string.bundle_optional), Bundle().apply { putString("bundle", "bundleOptional") })

                    putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                    putExtra(context.getString(R.string.short_array_or_default), shortArrayOf(3.toShort(), (-3).toShort()))
                    putExtra(context.getString(R.string.int_array_or_default), intArrayOf(500, -500))
                    putIntegerArrayListExtra(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600))
                    putExtra(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L))
                    putExtra(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f))
                    putExtra(context.getString(R.string.double_array_or_default), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                    putExtra(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false))
                    putExtra(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'))
                    putExtra(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault" as CharSequence)
                    putExtra(context.getString(R.string.char_sequence_array_or_default), arrayOf<CharSequence>("charSequence", "default"))

                    putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_required), arrayListOf("charSequenceArrayListRequired", "required"))
                    putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_optional), arrayListOf("charSequenceArrayListOptional", "optional"))
                    putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_or_default), arrayListOf("charSequenceArrayListOrDefault", "default"))

                    putExtra(context.getString(R.string.string_or_default), "stringOrDefault")
                    putExtra(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"))
                    putExtra(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"))

                    putExtra(context.getString(R.string.parcelable_or_default), TestParcelable("parcelableOrDefault"))
                    putExtra(context.getString(R.string.parcelable_array_or_default), arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                    putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                    putExtra(context.getString(R.string.serializable_or_default), TestSerializable("serializableOrDefault"))

                    putExtra(context.getString(R.string.bundle_or_default), Bundle().apply { putString("bundle", "bundleOrDefault") })

                }
            }
        }

        private val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
        private val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
        private val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
        private val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
        private val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
        private val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)

        private val byteRequired by bindByteArgOr(R.string.byte_required)
        private val byteArrayRequired by bindByteArrayArg(R.string.byte_array_required)
        private val byteArrayOptional by bindByteArrayArgOrNull(R.string.byte_array_optional)

        private val shortRequired by bindShortArgOr(R.string.short_required)
        private val shortArrayRequired by bindShortArrayArg(R.string.short_array_required)
        private val shortArrayOptional by bindShortArrayArgOrNull(R.string.short_array_optional)

        private val intRequired by bindIntArgOr(R.string.int_required)
        private val intArrayRequired by bindIntArrayArg(R.string.int_array_required)
        private val intArrayOptional by bindIntArrayArgOrNull(R.string.int_array_optional)
        private val intArrayListRequired by bindIntArrayListArg(R.string.int_array_list_required)
        private val intArrayListOptional by bindIntArrayListArgOrNull(R.string.int_array_list_optional)

        private val longRequired by bindLongArgOr(R.string.long_required)
        private val longArrayRequired by bindLongArrayArg(R.string.long_array_required)
        private val longArrayOptional by bindLongArrayArgOrNull(R.string.long_array_optional)

        private val floatRequired by bindFloatArgOr(R.string.float_required)
        private val floatArrayRequired by bindFloatArrayArg(R.string.float_array_required)
        private val floatArrayOptional by bindFloatArrayArgOrNull(R.string.float_array_optional)

        private val doubleRequired by bindDoubleArgOr(R.string.double_required)
        private val doubleArrayRequired by bindDoubleArrayArg(R.string.double_array_required)
        private val doubleArrayOptional by bindDoubleArrayArgOrNull(R.string.double_array_optional)

        private val booleanRequired by bindBooleanArgOr(R.string.boolean_required)
        private val booleanArrayRequired by bindBooleanArrayArg(R.string.boolean_array_required)
        private val booleanArrayOptional by bindBooleanArrayArgOrNull(R.string.boolean_array_optional)

        private val charRequired by bindCharArgOr(R.string.char_required)
        private val charArrayRequired by bindCharArrayArg(R.string.char_array_required)
        private val charArrayOptional by bindCharArrayArgOrNull(R.string.char_array_optional)

        private val charSequenceRequired by bindCharSequenceArg(R.string.char_sequence_required)
        private val charSequenceOptional by bindCharSequenceArgOrNull(R.string.char_sequence_optional)
        private val charSequenceArrayRequired by bindCharSequenceArrayArg(R.string.char_sequence_array_required)
        private val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)


        private val parcelableRequired by bindParcelableArg<TestParcelable>(R.string.parcelable_required)
        private val parcelableOptional by bindParcelableArgOrNull<TestParcelable>(R.string.parcelable_optional)
        private val parcelableArrayRequired by bindParcelableArrayArg<Parcelable>(R.string.parcelable_array_required)
        private val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
        private val parcelableArrayListRequired by bindParcelableArrayListArg<TestParcelable>(R.string.parcelable_array_list_required)
        private val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>(R.string.parcelable_array_list_optional)

        private val serializableRequired by bindSerializableArg<TestSerializable>(R.string.serializable_required)
        private val serializableOptional by bindSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)

        private val bundleRequired by bindBundleArg(R.string.bundle_required)
        private val bundleOptional by bindBundleArgOrNull(R.string.bundle_optional)

        private val byteArrayOrDefault by bindByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf())

        private val shortArrayOrDefault by bindShortArrayArgOr(R.string.short_array_or_default, shortArrayOf())

        private val intArrayOrDefault by bindIntArrayArgOr(R.string.int_array_or_default, intArrayOf())

        private val intArrayListOrDefault by bindIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf())

        private val longArrayOrDefault by bindLongArrayArgOr(R.string.long_array_or_default, longArrayOf())

        private val floatArrayOrDefault by bindFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf())

        private val doubleArrayOrDefault by bindDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf())

        private val booleanArrayOrDefault by bindBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf())

        private val charArrayOrDefault by bindCharArrayArgOr(R.string.char_array_or_default, charArrayOf())

        private val charSequenceOrDefault by bindCharSequenceArgOr(R.string.char_sequence_or_default, "default")

        private val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf())

        private val charSequenceArrayListRequired by bindCharSequenceArrayListArg(R.string.char_sequence_array_list_required)
        private val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
        private val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf())

        private val stringOrDefault by bindStringArgOr(R.string.string_or_default, "")

        private val stringArrayOrDefault by bindStringArrayArgOr(R.string.string_array_or_default, arrayOf())

        private val stringArrayListOrDefault by bindStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf())

        private val parcelableOrDefault by bindParcelableArgOr(R.string.parcelable_or_default, TestParcelable("default"))

        private val parcelableArrayOrDefault by bindParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())

        private val parcelableArrayListOrDefault by bindParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf())

        private val serializableOrDefault by bindSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))

        private val bundleOrDefault by bindBundleArgOr(R.string.bundle_or_default, Bundle())

        fun assert() {
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

            Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())

            Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())

            Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)

            Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)

            Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)

            Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)

            Assert.assertTrue(doubleArrayOrDefault[0] == 6.toDouble() && doubleArrayOrDefault[1] == (-6).toDouble())

            Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])

            Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')

            Assert.assertTrue(charSequenceOrDefault == "charSequenceOrDefault")

            Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")

            Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
            Assert.assertTrue(charSequenceArrayListOptional?.get(0) == "charSequenceArrayListOptional" && charSequenceArrayListOptional?.get(1) == "optional")
            Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")

            Assert.assertTrue(stringOrDefault == "stringOrDefault")

            Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")

            Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")

            Assert.assertTrue(parcelableOrDefault == TestParcelable("parcelableOrDefault"))

            Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(serializableOrDefault == TestSerializable("serializableOrDefault"))

            Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")

        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(me.panpf.androidxkt.test.R.layout.at_test)

            val supportFragment = ResTestSupportFragment()
            supportFragment.arguments = ResTestSupportFragment.createArguments(this)
            supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.test.R.id.testAt_frame, supportFragment).commit()

            val fragment = ResTestFragment()
            fragment.arguments = ResTestFragment.createArguments(this)
            fragmentManager.beginTransaction().add(android.R.id.content, fragment).commit()
        }
    }

    class ResTestSupportFragment : Fragment() {

        private val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
        private val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
        private val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
        private val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
        private val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
        private val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)


        private val byteRequired by bindByteArgOr(R.string.byte_required)
        private val byteArrayRequired by bindByteArrayArg(R.string.byte_array_required)
        private val byteArrayOptional by bindByteArrayArgOrNull(R.string.byte_array_optional)

        private val shortRequired by bindShortArgOr(R.string.short_required)
        private val shortArrayRequired by bindShortArrayArg(R.string.short_array_required)
        private val shortArrayOptional by bindShortArrayArgOrNull(R.string.short_array_optional)

        private val intRequired by bindIntArgOr(R.string.int_required)
        private val intArrayRequired by bindIntArrayArg(R.string.int_array_required)
        private val intArrayOptional by bindIntArrayArgOrNull(R.string.int_array_optional)
        private val intArrayListRequired by bindIntArrayListArg(R.string.int_array_list_required)
        private val intArrayListOptional by bindIntArrayListArgOrNull(R.string.int_array_list_optional)

        private val longRequired by bindLongArgOr(R.string.long_required)
        private val longArrayRequired by bindLongArrayArg(R.string.long_array_required)
        private val longArrayOptional by bindLongArrayArgOrNull(R.string.long_array_optional)

        private val floatRequired by bindFloatArgOr(R.string.float_required)
        private val floatArrayRequired by bindFloatArrayArg(R.string.float_array_required)
        private val floatArrayOptional by bindFloatArrayArgOrNull(R.string.float_array_optional)

        private val doubleRequired by bindDoubleArgOr(R.string.double_required)
        private val doubleArrayRequired by bindDoubleArrayArg(R.string.double_array_required)
        private val doubleArrayOptional by bindDoubleArrayArgOrNull(R.string.double_array_optional)

        private val booleanRequired by bindBooleanArgOr(R.string.boolean_required)
        private val booleanArrayRequired by bindBooleanArrayArg(R.string.boolean_array_required)
        private val booleanArrayOptional by bindBooleanArrayArgOrNull(R.string.boolean_array_optional)

        private val charRequired by bindCharArgOr(R.string.char_required)
        private val charArrayRequired by bindCharArrayArg(R.string.char_array_required)
        private val charArrayOptional by bindCharArrayArgOrNull(R.string.char_array_optional)

        private val charSequenceRequired by bindCharSequenceArg(R.string.char_sequence_required)
        private val charSequenceOptional by bindCharSequenceArgOrNull(R.string.char_sequence_optional)
        private val charSequenceArrayRequired by bindCharSequenceArrayArg(R.string.char_sequence_array_required)
        private val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)


        private val parcelableRequired by bindParcelableArg<TestParcelable>(R.string.parcelable_required)
        private val parcelableOptional by bindParcelableArgOrNull<TestParcelable>(R.string.parcelable_optional)
        private val parcelableArrayRequired by bindParcelableArrayArg<Parcelable>(R.string.parcelable_array_required)
        private val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
        private val parcelableArrayListRequired by bindParcelableArrayListArg<TestParcelable>(R.string.parcelable_array_list_required)
        private val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>(R.string.parcelable_array_list_optional)

        private val serializableRequired by bindSerializableArg<TestSerializable>(R.string.serializable_required)
        private val serializableOptional by bindSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)

        private val bundleRequired by bindBundleArg(R.string.bundle_required)
        private val bundleOptional by bindBundleArgOrNull(R.string.bundle_optional)

        private val byteArrayOrDefault by bindByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf())

        private val shortArrayOrDefault by bindShortArrayArgOr(R.string.short_array_or_default, shortArrayOf())

        private val intArrayOrDefault by bindIntArrayArgOr(R.string.int_array_or_default, intArrayOf())

        private val intArrayListOrDefault by bindIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf())

        private val longArrayOrDefault by bindLongArrayArgOr(R.string.long_array_or_default, longArrayOf())

        private val floatArrayOrDefault by bindFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf())

        private val doubleArrayOrDefault by bindDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf())

        private val booleanArrayOrDefault by bindBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf())

        private val charArrayOrDefault by bindCharArrayArgOr(R.string.char_array_or_default, charArrayOf())

        private val charSequenceOrDefault by bindCharSequenceArgOr(R.string.char_sequence_or_default, "default")

        private val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf())

        private val charSequenceArrayListRequired by bindCharSequenceArrayListArg(R.string.char_sequence_array_list_required)
        private val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
        private val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf())

        private val stringOrDefault by bindStringArgOr(R.string.string_or_default, "")

        private val stringArrayOrDefault by bindStringArrayArgOr(R.string.string_array_or_default, arrayOf())

        private val stringArrayListOrDefault by bindStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf())

        private val parcelableOrDefault by bindParcelableArgOr(R.string.parcelable_or_default, TestParcelable("default"))

        private val parcelableArrayOrDefault by bindParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())

        private val parcelableArrayListOrDefault by bindParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf())

        private val serializableOrDefault by bindSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))

        private val bundleOrDefault by bindBundleArgOr(R.string.bundle_or_default, Bundle())

        private val sparseParcelableArrayRequired by bindSparseParcelableArrayArg<TestParcelable>(R.string.sparse_parcelable_array_required)
        private val sparseParcelableArrayOptional by bindSparseParcelableArrayArgOrNull<TestParcelable>(R.string.sparse_parcelable_array_optional)
        private val sparseParcelableArrayOrDefault by bindSparseParcelableArrayArgOr<TestParcelable>(R.string.sparse_parcelable_array_or_default, SparseArray())
        private val sparseParcelableArrayOrDefaultErrKey by bindSparseParcelableArrayArgOr(R.string.not_exist_key,
                SparseArray<TestParcelable>().apply {
                    put(-4, TestParcelable("-4"))
                    put(4, TestParcelable("4"))
                })

        private val binderRequired by bindBinderArg(R.string.binder_required)
        private val binderOptional by bindBinderArgOrNull(R.string.binder_optional)
        private val binderOrDefault by bindBinderArgOr(R.string.binder_or_default, TestBinder())
        private val binderOrDefaultErrKey by bindBinderArgOr(R.string.not_exist_key, TestBinder("error"))

        private val sizeRequired by bindSizeArg(R.string.size_required)
        private val sizeOptional by bindSizeArgOrNull(R.string.size_optional)
        private val sizeOrDefault by bindSizeArgOr(R.string.size_or_default, Size(0, 0))
        private val sizeOrDefaultErrKey by bindSizeArgOr(R.string.not_exist_key, Size(4, 4))

        private val sizeFRequired by bindSizeFArg(R.string.sizeF_required)
        private val sizeFOptional by bindSizeFArgOrNull(R.string.sizeF_optional)
        private val sizeFOrDefault by bindSizeFArgOr(R.string.sizeF_or_default, SizeF(0f, 0f))
        private val sizeFOrDefaultErrKey by bindSizeFArgOr(R.string.not_exist_key, SizeF(4f, 4f))

        fun assert() {
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

            Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())

            Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())

            Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)

            Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)

            Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)

            Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)

            Assert.assertTrue(doubleArrayOrDefault[0] == 6.toDouble() && doubleArrayOrDefault[1] == (-6).toDouble())

            Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])

            Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')

            Assert.assertTrue(charSequenceOrDefault == "charSequenceOrDefault")

            Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")

            Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
            Assert.assertTrue(charSequenceArrayListOptional?.get(0) == "charSequenceArrayListOptional" && charSequenceArrayListOptional?.get(1) == "optional")
            Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")

            Assert.assertTrue(stringOrDefault == "stringOrDefault")

            Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")

            Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")

            Assert.assertTrue(parcelableOrDefault == TestParcelable("parcelableOrDefault"))

            Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(serializableOrDefault == TestSerializable("serializableOrDefault"))

            Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")

            Assert.assertTrue(sparseParcelableArrayRequired[-1] == TestParcelable("-1") && sparseParcelableArrayRequired[1] == TestParcelable("1"))
            Assert.assertTrue(sparseParcelableArrayOptional?.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional?.get(2) == TestParcelable("2"))
            Assert.assertTrue(sparseParcelableArrayOrDefault[-3] == TestParcelable("-3") && sparseParcelableArrayOrDefault[3] == TestParcelable("3"))
            Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey[-4] == TestParcelable("-4") && sparseParcelableArrayOrDefaultErrKey[4] == TestParcelable("4"))

            Assert.assertTrue(binderRequired == TestBinder("binderRequired"))
            Assert.assertTrue(binderOptional == TestBinder("binderOptional"))
            Assert.assertTrue(binderOrDefault == TestBinder("binderOrDefault"))
            Assert.assertTrue(binderOrDefaultErrKey == TestBinder("error"))

            Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
            Assert.assertTrue(sizeOptional?.width == 2 && sizeOptional?.height == 2)
            Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
            Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)

            Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
            Assert.assertTrue(sizeFOptional?.width == 2f && sizeFOptional?.height == 2f)
            Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
            Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)
        }

        companion object {
            fun createArguments(context: Context): Bundle = Bundle().apply {
                putString(context.getString(me.panpf.androidxkt.test.R.string.string_required), "stringRequired")
                putString(context.getString(me.panpf.androidxkt.test.R.string.string_optional), "stringOptional")
                putStringArray(context.getString(me.panpf.androidxkt.test.R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                putStringArray(context.getString(me.panpf.androidxkt.test.R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                putStringArrayList(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                putStringArrayList(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))

                putByte(context.getString(R.string.byte_required), 2.toByte())
                putByteArray(context.getString(R.string.byte_array_required), byteArrayOf(2.toByte(), (-2).toByte()))
                putByteArray(context.getString(R.string.byte_array_optional), byteArrayOf((-2).toByte(), 2.toByte()))

                putShort(context.getString(R.string.short_required), 3.toShort())
                putShortArray(context.getString(R.string.short_array_required), shortArrayOf(3.toShort(), (-3).toShort()))
                putShortArray(context.getString(R.string.short_array_optional), shortArrayOf((-3).toShort(), 3.toShort()))

                putInt(context.getString(R.string.int_required), 500)
                putIntArray(context.getString(R.string.int_array_required), intArrayOf(500, -500))
                putIntArray(context.getString(R.string.int_array_optional), intArrayOf(-500, 500))
                putIntegerArrayList(context.getString(R.string.int_array_list_required), arrayListOf(500, -500))
                putIntegerArrayList(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500))

                putLong(context.getString(R.string.long_required), 1000L)
                putLongArray(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L))
                putLongArray(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L))

                putFloat(context.getString(R.string.float_required), 4f)
                putFloatArray(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f))
                putFloatArray(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f))

                putDouble(context.getString(R.string.double_required), 6.toDouble())
                putDoubleArray(context.getString(R.string.double_array_required), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putDoubleArray(context.getString(R.string.double_array_optional), doubleArrayOf((-6).toDouble(), 6.toDouble()))

                putBoolean(context.getString(R.string.boolean_required), true)
                putBooleanArray(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false))
                putBooleanArray(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true))

                putChar(context.getString(R.string.char_required), 'a')
                putCharArray(context.getString(R.string.char_array_required), charArrayOf('a', 'b'))
                putCharArray(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'))


                putParcelable(context.getString(R.string.parcelable_required), TestParcelable("parcelableRequired"))
                putParcelable(context.getString(R.string.parcelable_optional), TestParcelable("parcelableOptional"))
                putParcelableArray(context.getString(R.string.parcelable_array_required), arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putParcelableArray(context.getString(R.string.parcelable_array_optional), arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                putParcelableArrayList(context.getString(R.string.parcelable_array_list_required), arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putParcelableArrayList(context.getString(R.string.parcelable_array_list_optional), arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))

                putSerializable(context.getString(R.string.serializable_required), TestSerializable("serializableRequired"))
                putSerializable(context.getString(R.string.serializable_optional), TestSerializable("serializableOptional"))

                putBundle(context.getString(R.string.bundle_required), Bundle().apply { putString("bundle", "bundleRequired") })
                putBundle(context.getString(R.string.bundle_optional), Bundle().apply { putString("bundle", "bundleOptional") })

                putByteArray(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                putShortArray(context.getString(R.string.short_array_or_default), shortArrayOf(3.toShort(), (-3).toShort()))
                putIntArray(context.getString(R.string.int_array_or_default), intArrayOf(500, -500))
                putIntegerArrayList(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600))
                putLongArray(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L))
                putFloatArray(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f))
                putDoubleArray(context.getString(R.string.double_array_or_default), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putBooleanArray(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false))
                putCharArray(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'))
                putCharSequence(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault" as CharSequence)
                putCharSequenceArray(context.getString(R.string.char_sequence_array_or_default), arrayOf<CharSequence>("charSequence", "default"))

                putCharSequenceArrayList(context.getString(R.string.char_sequence_array_list_required), arrayListOf("charSequenceArrayListRequired", "required"))
                putCharSequenceArrayList(context.getString(R.string.char_sequence_array_list_optional), arrayListOf("charSequenceArrayListOptional", "optional"))
                putCharSequenceArrayList(context.getString(R.string.char_sequence_array_list_or_default), arrayListOf("charSequenceArrayListOrDefault", "default"))

                putString(context.getString(R.string.string_or_default), "stringOrDefault")
                putStringArray(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"))
                putStringArrayList(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"))

                putParcelable(context.getString(R.string.parcelable_or_default), TestParcelable("parcelableOrDefault"))
                putParcelableArray(context.getString(R.string.parcelable_array_or_default), arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                putParcelableArrayList(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                putSerializable(context.getString(R.string.serializable_or_default), TestSerializable("serializableOrDefault"))

                putBundle(context.getString(R.string.bundle_or_default), Bundle().apply { putString("bundle", "bundleOrDefault") })

                putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_required), SparseArray<TestParcelable>()
                        .apply {
                            put(-1, TestParcelable("-1"))
                            put(1, TestParcelable("1"))
                        })
                putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_optional), SparseArray<TestParcelable>()
                        .apply {
                            put(-2, TestParcelable("-2"))
                            put(2, TestParcelable("2"))
                        })
                putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_or_default), SparseArray<TestParcelable>()
                        .apply {
                            put(-3, TestParcelable("-3"))
                            put(3, TestParcelable("3"))
                        })

                putBinder(context.getString(R.string.binder_required), TestBinder("binderRequired"))
                putBinder(context.getString(R.string.binder_optional), TestBinder("binderOptional"))
                putBinder(context.getString(R.string.binder_or_default), TestBinder("binderOrDefault"))

                putSize(context.getString(R.string.size_required), Size(1, 1))
                putSize(context.getString(R.string.size_optional), Size(2, 2))
                putSize(context.getString(R.string.size_or_default), Size(3, 3))

                putSizeF(context.getString(R.string.sizeF_required), SizeF(1f, 1f))
                putSizeF(context.getString(R.string.sizeF_optional), SizeF(2f, 2f))
                putSizeF(context.getString(R.string.sizeF_or_default), SizeF(3f, 3f))

            }
        }
    }

    class ResTestFragment : android.app.Fragment() {

        private val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
        private val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
        private val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
        private val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
        private val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
        private val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)


        private val byteRequired by bindByteArgOr(R.string.byte_required)
        private val byteArrayRequired by bindByteArrayArg(R.string.byte_array_required)
        private val byteArrayOptional by bindByteArrayArgOrNull(R.string.byte_array_optional)

        private val shortRequired by bindShortArgOr(R.string.short_required)
        private val shortArrayRequired by bindShortArrayArg(R.string.short_array_required)
        private val shortArrayOptional by bindShortArrayArgOrNull(R.string.short_array_optional)

        private val intRequired by bindIntArgOr(R.string.int_required)
        private val intArrayRequired by bindIntArrayArg(R.string.int_array_required)
        private val intArrayOptional by bindIntArrayArgOrNull(R.string.int_array_optional)
        private val intArrayListRequired by bindIntArrayListArg(R.string.int_array_list_required)
        private val intArrayListOptional by bindIntArrayListArgOrNull(R.string.int_array_list_optional)

        private val longRequired by bindLongArgOr(R.string.long_required)
        private val longArrayRequired by bindLongArrayArg(R.string.long_array_required)
        private val longArrayOptional by bindLongArrayArgOrNull(R.string.long_array_optional)

        private val floatRequired by bindFloatArgOr(R.string.float_required)
        private val floatArrayRequired by bindFloatArrayArg(R.string.float_array_required)
        private val floatArrayOptional by bindFloatArrayArgOrNull(R.string.float_array_optional)

        private val doubleRequired by bindDoubleArgOr(R.string.double_required)
        private val doubleArrayRequired by bindDoubleArrayArg(R.string.double_array_required)
        private val doubleArrayOptional by bindDoubleArrayArgOrNull(R.string.double_array_optional)

        private val booleanRequired by bindBooleanArgOr(R.string.boolean_required)
        private val booleanArrayRequired by bindBooleanArrayArg(R.string.boolean_array_required)
        private val booleanArrayOptional by bindBooleanArrayArgOrNull(R.string.boolean_array_optional)

        private val charRequired by bindCharArgOr(R.string.char_required)
        private val charArrayRequired by bindCharArrayArg(R.string.char_array_required)
        private val charArrayOptional by bindCharArrayArgOrNull(R.string.char_array_optional)

        private val charSequenceRequired by bindCharSequenceArg(R.string.char_sequence_required)
        private val charSequenceOptional by bindCharSequenceArgOrNull(R.string.char_sequence_optional)
        private val charSequenceArrayRequired by bindCharSequenceArrayArg(R.string.char_sequence_array_required)
        private val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)


        private val parcelableRequired by bindParcelableArg<TestParcelable>(R.string.parcelable_required)
        private val parcelableOptional by bindParcelableArgOrNull<TestParcelable>(R.string.parcelable_optional)
        private val parcelableArrayRequired by bindParcelableArrayArg<Parcelable>(R.string.parcelable_array_required)
        private val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
        private val parcelableArrayListRequired by bindParcelableArrayListArg<TestParcelable>(R.string.parcelable_array_list_required)
        private val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>(R.string.parcelable_array_list_optional)

        private val serializableRequired by bindSerializableArg<TestSerializable>(R.string.serializable_required)
        private val serializableOptional by bindSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)

        private val bundleRequired by bindBundleArg(R.string.bundle_required)
        private val bundleOptional by bindBundleArgOrNull(R.string.bundle_optional)

        private val byteArrayOrDefault by bindByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf())

        private val shortArrayOrDefault by bindShortArrayArgOr(R.string.short_array_or_default, shortArrayOf())

        private val intArrayOrDefault by bindIntArrayArgOr(R.string.int_array_or_default, intArrayOf())

        private val intArrayListOrDefault by bindIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf())

        private val longArrayOrDefault by bindLongArrayArgOr(R.string.long_array_or_default, longArrayOf())

        private val floatArrayOrDefault by bindFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf())

        private val doubleArrayOrDefault by bindDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf())

        private val booleanArrayOrDefault by bindBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf())

        private val charArrayOrDefault by bindCharArrayArgOr(R.string.char_array_or_default, charArrayOf())

        private val charSequenceOrDefault by bindCharSequenceArgOr(R.string.char_sequence_or_default, "default")

        private val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf())

        private val charSequenceArrayListRequired by bindCharSequenceArrayListArg(R.string.char_sequence_array_list_required)
        private val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
        private val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf())

        private val stringOrDefault by bindStringArgOr(R.string.string_or_default, "")

        private val stringArrayOrDefault by bindStringArrayArgOr(R.string.string_array_or_default, arrayOf())

        private val stringArrayListOrDefault by bindStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf())

        private val parcelableOrDefault by bindParcelableArgOr(R.string.parcelable_or_default, TestParcelable("default"))

        private val parcelableArrayOrDefault by bindParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())

        private val parcelableArrayListOrDefault by bindParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf())

        private val serializableOrDefault by bindSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))

        private val bundleOrDefault by bindBundleArgOr(R.string.bundle_or_default, Bundle())

        private val sparseParcelableArrayRequired by bindSparseParcelableArrayArg<TestParcelable>(R.string.sparse_parcelable_array_required)
        private val sparseParcelableArrayOptional by bindSparseParcelableArrayArgOrNull<TestParcelable>(R.string.sparse_parcelable_array_optional)
        private val sparseParcelableArrayOrDefault by bindSparseParcelableArrayArgOr<TestParcelable>(R.string.sparse_parcelable_array_or_default, SparseArray())
        private val sparseParcelableArrayOrDefaultErrKey by bindSparseParcelableArrayArgOr(R.string.not_exist_key,
                SparseArray<TestParcelable>().apply {
                    put(-4, TestParcelable("-4"))
                    put(4, TestParcelable("4"))
                })

        private val binderRequired by bindBinderArg(R.string.binder_required)
        private val binderOptional by bindBinderArgOrNull(R.string.binder_optional)
        private val binderOrDefault by bindBinderArgOr(R.string.binder_or_default, TestBinder())
        private val binderOrDefaultErrKey by bindBinderArgOr(R.string.not_exist_key, TestBinder("error"))

        private val sizeRequired by bindSizeArg(R.string.size_required)
        private val sizeOptional by bindSizeArgOrNull(R.string.size_optional)
        private val sizeOrDefault by bindSizeArgOr(R.string.size_or_default, Size(0, 0))
        private val sizeOrDefaultErrKey by bindSizeArgOr(R.string.not_exist_key, Size(4, 4))

        private val sizeFRequired by bindSizeFArg(R.string.sizeF_required)
        private val sizeFOptional by bindSizeFArgOrNull(R.string.sizeF_optional)
        private val sizeFOrDefault by bindSizeFArgOr(R.string.sizeF_or_default, SizeF(0f, 0f))
        private val sizeFOrDefaultErrKey by bindSizeFArgOr(R.string.not_exist_key, SizeF(4f, 4f))

        fun assert() {
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

            Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())

            Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())

            Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)

            Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)

            Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)

            Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)

            Assert.assertTrue(doubleArrayOrDefault[0] == 6.toDouble() && doubleArrayOrDefault[1] == (-6).toDouble())

            Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])

            Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')

            Assert.assertTrue(charSequenceOrDefault == "charSequenceOrDefault")

            Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")

            Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
            Assert.assertTrue(charSequenceArrayListOptional?.get(0) == "charSequenceArrayListOptional" && charSequenceArrayListOptional?.get(1) == "optional")
            Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")

            Assert.assertTrue(stringOrDefault == "stringOrDefault")

            Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")

            Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")

            Assert.assertTrue(parcelableOrDefault == TestParcelable("parcelableOrDefault"))

            Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(serializableOrDefault == TestSerializable("serializableOrDefault"))

            Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")

            Assert.assertTrue(sparseParcelableArrayRequired[-1] == TestParcelable("-1") && sparseParcelableArrayRequired[1] == TestParcelable("1"))
            Assert.assertTrue(sparseParcelableArrayOptional?.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional?.get(2) == TestParcelable("2"))
            Assert.assertTrue(sparseParcelableArrayOrDefault[-3] == TestParcelable("-3") && sparseParcelableArrayOrDefault[3] == TestParcelable("3"))
            Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey[-4] == TestParcelable("-4") && sparseParcelableArrayOrDefaultErrKey[4] == TestParcelable("4"))

            Assert.assertTrue(binderRequired == TestBinder("binderRequired"))
            Assert.assertTrue(binderOptional == TestBinder("binderOptional"))
            Assert.assertTrue(binderOrDefault == TestBinder("binderOrDefault"))
            Assert.assertTrue(binderOrDefaultErrKey == TestBinder("error"))

            Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
            Assert.assertTrue(sizeOptional?.width == 2 && sizeOptional?.height == 2)
            Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
            Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)

            Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
            Assert.assertTrue(sizeFOptional?.width == 2f && sizeFOptional?.height == 2f)
            Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
            Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)
        }

        companion object {
            fun createArguments(context: Context): Bundle = Bundle().apply {
                putString(context.getString(me.panpf.androidxkt.test.R.string.string_required), "stringRequired")
                putString(context.getString(me.panpf.androidxkt.test.R.string.string_optional), "stringOptional")
                putStringArray(context.getString(me.panpf.androidxkt.test.R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                putStringArray(context.getString(me.panpf.androidxkt.test.R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                putStringArrayList(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                putStringArrayList(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))

                putByte(context.getString(R.string.byte_required), 2.toByte())
                putByteArray(context.getString(R.string.byte_array_required), byteArrayOf(2.toByte(), (-2).toByte()))
                putByteArray(context.getString(R.string.byte_array_optional), byteArrayOf((-2).toByte(), 2.toByte()))

                putShort(context.getString(R.string.short_required), 3.toShort())
                putShortArray(context.getString(R.string.short_array_required), shortArrayOf(3.toShort(), (-3).toShort()))
                putShortArray(context.getString(R.string.short_array_optional), shortArrayOf((-3).toShort(), 3.toShort()))

                putInt(context.getString(R.string.int_required), 500)
                putIntArray(context.getString(R.string.int_array_required), intArrayOf(500, -500))
                putIntArray(context.getString(R.string.int_array_optional), intArrayOf(-500, 500))
                putIntegerArrayList(context.getString(R.string.int_array_list_required), arrayListOf(500, -500))
                putIntegerArrayList(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500))

                putLong(context.getString(R.string.long_required), 1000L)
                putLongArray(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L))
                putLongArray(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L))

                putFloat(context.getString(R.string.float_required), 4f)
                putFloatArray(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f))
                putFloatArray(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f))

                putDouble(context.getString(R.string.double_required), 6.toDouble())
                putDoubleArray(context.getString(R.string.double_array_required), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putDoubleArray(context.getString(R.string.double_array_optional), doubleArrayOf((-6).toDouble(), 6.toDouble()))

                putBoolean(context.getString(R.string.boolean_required), true)
                putBooleanArray(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false))
                putBooleanArray(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true))

                putChar(context.getString(R.string.char_required), 'a')
                putCharArray(context.getString(R.string.char_array_required), charArrayOf('a', 'b'))
                putCharArray(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'))


                putParcelable(context.getString(R.string.parcelable_required), TestParcelable("parcelableRequired"))
                putParcelable(context.getString(R.string.parcelable_optional), TestParcelable("parcelableOptional"))
                putParcelableArray(context.getString(R.string.parcelable_array_required), arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putParcelableArray(context.getString(R.string.parcelable_array_optional), arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                putParcelableArrayList(context.getString(R.string.parcelable_array_list_required), arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putParcelableArrayList(context.getString(R.string.parcelable_array_list_optional), arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))

                putSerializable(context.getString(R.string.serializable_required), TestSerializable("serializableRequired"))
                putSerializable(context.getString(R.string.serializable_optional), TestSerializable("serializableOptional"))

                putBundle(context.getString(R.string.bundle_required), Bundle().apply { putString("bundle", "bundleRequired") })
                putBundle(context.getString(R.string.bundle_optional), Bundle().apply { putString("bundle", "bundleOptional") })

                putByteArray(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                putShortArray(context.getString(R.string.short_array_or_default), shortArrayOf(3.toShort(), (-3).toShort()))
                putIntArray(context.getString(R.string.int_array_or_default), intArrayOf(500, -500))
                putIntegerArrayList(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600))
                putLongArray(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L))
                putFloatArray(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f))
                putDoubleArray(context.getString(R.string.double_array_or_default), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putBooleanArray(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false))
                putCharArray(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'))
                putCharSequence(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault" as CharSequence)
                putCharSequenceArray(context.getString(R.string.char_sequence_array_or_default), arrayOf<CharSequence>("charSequence", "default"))

                putCharSequenceArrayList(context.getString(R.string.char_sequence_array_list_required), arrayListOf("charSequenceArrayListRequired", "required"))
                putCharSequenceArrayList(context.getString(R.string.char_sequence_array_list_optional), arrayListOf("charSequenceArrayListOptional", "optional"))
                putCharSequenceArrayList(context.getString(R.string.char_sequence_array_list_or_default), arrayListOf("charSequenceArrayListOrDefault", "default"))

                putString(context.getString(R.string.string_or_default), "stringOrDefault")
                putStringArray(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"))
                putStringArrayList(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"))

                putParcelable(context.getString(R.string.parcelable_or_default), TestParcelable("parcelableOrDefault"))
                putParcelableArray(context.getString(R.string.parcelable_array_or_default), arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                putParcelableArrayList(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                putSerializable(context.getString(R.string.serializable_or_default), TestSerializable("serializableOrDefault"))

                putBundle(context.getString(R.string.bundle_or_default), Bundle().apply { putString("bundle", "bundleOrDefault") })

                putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_required), SparseArray<TestParcelable>()
                        .apply {
                            put(-1, TestParcelable("-1"))
                            put(1, TestParcelable("1"))
                        })
                putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_optional), SparseArray<TestParcelable>()
                        .apply {
                            put(-2, TestParcelable("-2"))
                            put(2, TestParcelable("2"))
                        })
                putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_or_default), SparseArray<TestParcelable>()
                        .apply {
                            put(-3, TestParcelable("-3"))
                            put(3, TestParcelable("3"))
                        })

                putBinder(context.getString(R.string.binder_required), TestBinder("binderRequired"))
                putBinder(context.getString(R.string.binder_optional), TestBinder("binderOptional"))
                putBinder(context.getString(R.string.binder_or_default), TestBinder("binderOrDefault"))

                putSize(context.getString(R.string.size_required), Size(1, 1))
                putSize(context.getString(R.string.size_optional), Size(2, 2))
                putSize(context.getString(R.string.size_or_default), Size(3, 3))

                putSizeF(context.getString(R.string.sizeF_required), SizeF(1f, 1f))
                putSizeF(context.getString(R.string.sizeF_optional), SizeF(2f, 2f))
                putSizeF(context.getString(R.string.sizeF_or_default), SizeF(3f, 3f))

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

        val byteArrayOrDefault by bindByteArrayArgOr("byteArrayOrDefault", byteArrayOf())

        val shortArrayOrDefault by bindShortArrayArgOr("shortArrayOrDefault", shortArrayOf())

        val intArrayOrDefault by bindIntArrayArgOr("intArrayOrDefault", intArrayOf())

        val intArrayListOrDefault by bindIntArrayListArgOr("intArrayListOrDefault", arrayListOf())

        val longArrayOrDefault by bindLongArrayArgOr("longArrayOrDefault", longArrayOf())

        val floatArrayOrDefault by bindFloatArrayArgOr("floatArrayOrDefault", floatArrayOf())

        val doubleArrayOrDefault by bindDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf())

        val booleanArrayOrDefault by bindBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf())

        val charArrayOrDefault by bindCharArrayArgOr("charArrayOrDefault", charArrayOf())

        val charSequenceOrDefault by bindCharSequenceArgOr("charSequenceOrDefault", "default")

        val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf())

        val charSequenceArrayListRequired by bindCharSequenceArrayListArg("charSequenceArrayListRequired")
        val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
        val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf())

        val stringOrDefault by bindStringArgOr("stringOrDefault", "")

        val stringArrayOrDefault by bindStringArrayArgOr("stringArrayOrDefault", arrayOf())

        val stringArrayListOrDefault by bindStringArrayListArgOr("stringArrayListOrDefault", arrayListOf())

        val parcelableOrDefault by bindParcelableArgOr("parcelableOrDefault", TestParcelable("default"))

        val parcelableArrayOrDefault by bindParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())

        val parcelableArrayListOrDefault by bindParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf())

        val serializableOrDefault by bindSerializableArgOr("serializableOrDefault", TestSerializable("default"))

        val bundleOrDefault by bindBundleArgOr("bundleOrDefault", Bundle())

        private val extrasRequired by bindExtrasArg()
        private val extrasOptional by bindExtrasArgOrNull()
        private val extrasOrDefault by bindExtrasArgOr(Bundle())

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(me.panpf.androidxkt.test.R.layout.at_test)

            val supportFragment = TestSupportFragment()
            supportFragment.arguments = TestSupportFragment.createArguments(this)
            supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.test.R.id.testAt_frame, supportFragment).commit()

            val fragment = TestFragment()
            fragment.arguments = TestFragment.createArguments(this)
            fragmentManager.beginTransaction().add(android.R.id.content, fragment).commit()
        }

        fun assert() {

            Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())

            Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())

            Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)

            Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)

            Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)

            Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)

            Assert.assertTrue(doubleArrayOrDefault[0] == 6.toDouble() && doubleArrayOrDefault[1] == (-6).toDouble())

            Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])

            Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')

            Assert.assertTrue(charSequenceOrDefault == "charSequenceOrDefault")

            Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")

            Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
            Assert.assertTrue(charSequenceArrayListOptional?.get(0) == "charSequenceArrayListOptional" && charSequenceArrayListOptional?.get(1) == "optional")
            Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")

            Assert.assertTrue(stringOrDefault == "stringOrDefault")

            Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")

            Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")

            Assert.assertTrue(parcelableOrDefault == TestParcelable("parcelableOrDefault"))

            Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(serializableOrDefault == TestSerializable("serializableOrDefault"))

            Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")

            Assert.assertTrue(extrasRequired.getString("extrasRequired") == "extrasRequired")
            Assert.assertTrue(extrasOptional?.getString("extrasOptional") == "extrasOptional")
            Assert.assertTrue(extrasOrDefault.getString("extrasOrDefault") == "extrasOrDefault")

            //-------------

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

                    //---
                    putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
                    putExtra("shortArrayOrDefault", shortArrayOf(3.toShort(), (-3).toShort()))
                    putExtra("intArrayOrDefault", intArrayOf(500, -500))
                    putIntegerArrayListExtra("intArrayListOrDefault", arrayListOf(600, -600))
                    putExtra("longArrayOrDefault", longArrayOf(1000L, -1000L))
                    putExtra("floatArrayOrDefault", floatArrayOf(4f, -4f))
                    putExtra("doubleArrayOrDefault", doubleArrayOf(6.toDouble(), (-6).toDouble()))
                    putExtra("booleanArrayOrDefault", booleanArrayOf(true, false))
                    putExtra("charArrayOrDefault", charArrayOf('a', 'b'))
                    putExtra("charSequenceOrDefault", "charSequenceOrDefault" as CharSequence)
                    putExtra("charSequenceArrayOrDefault", arrayOf<CharSequence>("charSequence", "default"))

                    putCharSequenceArrayListExtra("charSequenceArrayListRequired", arrayListOf("charSequenceArrayListRequired", "required"))
                    putCharSequenceArrayListExtra("charSequenceArrayListOptional", arrayListOf("charSequenceArrayListOptional", "optional"))
                    putCharSequenceArrayListExtra("charSequenceArrayListOrDefault", arrayListOf("charSequenceArrayListOrDefault", "default"))

                    putExtra("stringOrDefault", "stringOrDefault")
                    putExtra("stringArrayOrDefault", arrayOf("stringArrayOrDefault", "default"))
                    putExtra("stringArrayListOrDefault", arrayListOf("stringArrayListOrDefault", "default"))

                    putExtra("parcelableOrDefault", TestParcelable("parcelableOrDefault"))
                    putExtra("parcelableArrayOrDefault", arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                    putParcelableArrayListExtra("parcelableArrayListOrDefault", arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                    putExtra("serializableOrDefault", TestSerializable("serializableOrDefault"))

                    putExtra("bundleOrDefault", Bundle().apply { putString("bundle", "bundleOrDefault") })

                    putExtras(Bundle().apply { putString("extrasRequired", "extrasRequired") })
                    putExtras(Bundle().apply { putString("extrasOptional", "extrasOptional") })
                    putExtras(Bundle().apply { putString("extrasOrDefault", "extrasOrDefault") })
                }
            }

        }
    }

    class TestSupportFragment : Fragment() {

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

        private val byteArrayOrDefault by bindByteArrayArgOr("byteArrayOrDefault", byteArrayOf())

        private val shortArrayOrDefault by bindShortArrayArgOr("shortArrayOrDefault", shortArrayOf())

        private val intArrayOrDefault by bindIntArrayArgOr("intArrayOrDefault", intArrayOf())

        private val intArrayListOrDefault by bindIntArrayListArgOr("intArrayListOrDefault", arrayListOf())

        private val longArrayOrDefault by bindLongArrayArgOr("longArrayOrDefault", longArrayOf())

        private val floatArrayOrDefault by bindFloatArrayArgOr("floatArrayOrDefault", floatArrayOf())

        private val doubleArrayOrDefault by bindDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf())

        private val booleanArrayOrDefault by bindBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf())

        private val charArrayOrDefault by bindCharArrayArgOr("charArrayOrDefault", charArrayOf())

        private val charSequenceOrDefault by bindCharSequenceArgOr("charSequenceOrDefault", "default")

        private val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf())

        private val charSequenceArrayListRequired by bindCharSequenceArrayListArg("charSequenceArrayListRequired")
        private val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
        private val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf())

        private val stringOrDefault by bindStringArgOr("stringOrDefault", "")

        private val stringArrayOrDefault by bindStringArrayArgOr("stringArrayOrDefault", arrayOf())

        private val stringArrayListOrDefault by bindStringArrayListArgOr("stringArrayListOrDefault", arrayListOf())

        private val parcelableOrDefault by bindParcelableArgOr("parcelableOrDefault", TestParcelable("default"))

        private val parcelableArrayOrDefault by bindParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())

        private val parcelableArrayListOrDefault by bindParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf())

        private val serializableOrDefault by bindSerializableArgOr("serializableOrDefault", TestSerializable("default"))

        private val bundleOrDefault by bindBundleArgOr("bundleOrDefault", Bundle())

        private val sparseParcelableArrayRequired by bindSparseParcelableArrayArg<TestParcelable>("sparseParcelableArrayRequired")
        private val sparseParcelableArrayOptional by bindSparseParcelableArrayArgOrNull<TestParcelable>("sparseParcelableArrayOptional")
        private val sparseParcelableArrayOrDefault by bindSparseParcelableArrayArgOr<TestParcelable>("sparseParcelableArrayOrDefault", SparseArray())
        private val sparseParcelableArrayOrDefaultErrKey by bindSparseParcelableArrayArgOr("sparseParcelableArrayOrDefaultErrKey",
                SparseArray<TestParcelable>().apply {
                    put(-4, TestParcelable("-4"))
                    put(4, TestParcelable("4"))
                })

        private val binderRequired by bindBinderArg("binderRequired")
        private val binderOptional by bindBinderArgOrNull("binderOptional")
        private val binderOrDefault by bindBinderArgOr("binderOrDefault", TestBinder())
        private val binderOrDefaultErrKey by bindBinderArgOr("binderOrDefaultErrKey", TestBinder("error"))

        private val sizeRequired by bindSizeArg("sizeRequired")
        private val sizeOptional by bindSizeArgOrNull("sizeOptional")
        private val sizeOrDefault by bindSizeArgOr("sizeOrDefault", Size(0, 0))
        private val sizeOrDefaultErrKey by bindSizeArgOr("sizeOrDefaultErrKey", Size(4, 4))

        private val sizeFRequired by bindSizeFArg("sizeFRequired")
        private val sizeFOptional by bindSizeFArgOrNull("sizeFOptional")
        private val sizeFOrDefault by bindSizeFArgOr("sizeFOrDefault", SizeF(0f, 0f))
        private val sizeFOrDefaultErrKey by bindSizeFArgOr("sizeFOrDefaultErrKey", SizeF(4f, 4f))

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

            Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())

            Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())

            Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)

            Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)

            Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)

            Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)

            Assert.assertTrue(doubleArrayOrDefault[0] == 6.toDouble() && doubleArrayOrDefault[1] == (-6).toDouble())

            Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])

            Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')

            Assert.assertTrue(charSequenceOrDefault == "charSequenceOrDefault")

            Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")

            Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
            Assert.assertTrue(charSequenceArrayListOptional?.get(0) == "charSequenceArrayListOptional" && charSequenceArrayListOptional?.get(1) == "optional")
            Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")

            Assert.assertTrue(stringOrDefault == "stringOrDefault")

            Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")

            Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")

            Assert.assertTrue(parcelableOrDefault == TestParcelable("parcelableOrDefault"))

            Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(serializableOrDefault == TestSerializable("serializableOrDefault"))

            Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")

            Assert.assertTrue(sparseParcelableArrayRequired[-1] == TestParcelable("-1") && sparseParcelableArrayRequired[1] == TestParcelable("1"))
            Assert.assertTrue(sparseParcelableArrayOptional?.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional?.get(2) == TestParcelable("2"))
            Assert.assertTrue(sparseParcelableArrayOrDefault[-3] == TestParcelable("-3") && sparseParcelableArrayOrDefault[3] == TestParcelable("3"))
            Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey[-4] == TestParcelable("-4") && sparseParcelableArrayOrDefaultErrKey[4] == TestParcelable("4"))

            Assert.assertTrue(binderRequired == TestBinder("binderRequired"))
            Assert.assertTrue(binderOptional == TestBinder("binderOptional"))
            Assert.assertTrue(binderOrDefault == TestBinder("binderOrDefault"))
            Assert.assertTrue(binderOrDefaultErrKey == TestBinder("error"))

            Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
            Assert.assertTrue(sizeOptional?.width == 2 && sizeOptional?.height == 2)
            Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
            Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)

            Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
            Assert.assertTrue(sizeFOptional?.width == 2f && sizeFOptional?.height == 2f)
            Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
            Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

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

                putByteArray("byteArrayOrDefault", activity.byteArrayOrDefault)
                putShortArray("shortArrayOrDefault", activity.shortArrayOrDefault)
                putIntArray("intArrayOrDefault", activity.intArrayOrDefault)
                putIntegerArrayList("intArrayListOrDefault", activity.intArrayListOrDefault)
                putLongArray("longArrayOrDefault", activity.longArrayOrDefault)
                putFloatArray("floatArrayOrDefault", activity.floatArrayOrDefault)
                putDoubleArray("doubleArrayOrDefault", activity.doubleArrayOrDefault)
                putBooleanArray("booleanArrayOrDefault", activity.booleanArrayOrDefault)
                putCharArray("charArrayOrDefault", activity.charArrayOrDefault)
                putCharSequence("charSequenceOrDefault", activity.charSequenceOrDefault)
                putCharSequenceArray("charSequenceArrayOrDefault", activity.charSequenceArrayOrDefault)

                putCharSequenceArrayList("charSequenceArrayListRequired", activity.charSequenceArrayListRequired)
                putCharSequenceArrayList("charSequenceArrayListOptional", activity.charSequenceArrayListOptional)
                putCharSequenceArrayList("charSequenceArrayListOrDefault", activity.charSequenceArrayListOrDefault)

                putString("stringOrDefault", activity.stringOrDefault)
                putStringArray("stringArrayOrDefault", activity.stringArrayOrDefault)
                putStringArrayList("stringArrayListOrDefault", activity.stringArrayListOrDefault)

                putParcelable("parcelableOrDefault", activity.parcelableOrDefault)
                putParcelableArray("parcelableArrayOrDefault", activity.parcelableArrayOrDefault)
                putParcelableArrayList("parcelableArrayListOrDefault", activity.parcelableArrayListOrDefault)

                putSerializable("serializableOrDefault", activity.serializableOrDefault)

                putBundle("bundleOrDefault", activity.bundleOrDefault)

                putSparseParcelableArray("sparseParcelableArrayRequired", SparseArray<TestParcelable>()
                        .apply {
                            put(-1, TestParcelable("-1"))
                            put(1, TestParcelable("1"))
                        })
                putSparseParcelableArray("sparseParcelableArrayOptional", SparseArray<TestParcelable>()
                        .apply {
                            put(-2, TestParcelable("-2"))
                            put(2, TestParcelable("2"))
                        })
                putSparseParcelableArray("sparseParcelableArrayOrDefault", SparseArray<TestParcelable>()
                        .apply {
                            put(-3, TestParcelable("-3"))
                            put(3, TestParcelable("3"))
                        })

                putBinder("binderRequired", TestBinder("binderRequired"))
                putBinder("binderOptional", TestBinder("binderOptional"))
                putBinder("binderOrDefault", TestBinder("binderOrDefault"))

                putSize("sizeRequired", Size(1, 1))
                putSize("sizeOptional", Size(2, 2))
                putSize("sizeOrDefault", Size(3, 3))

                putSizeF("sizeFRequired", SizeF(1f, 1f))
                putSizeF("sizeFOptional", SizeF(2f, 2f))
                putSizeF("sizeFOrDefault", SizeF(3f, 3f))

            }
        }
    }

    class TestFragment : android.app.Fragment() {

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

        private val byteArrayOrDefault by bindByteArrayArgOr("byteArrayOrDefault", byteArrayOf())

        private val shortArrayOrDefault by bindShortArrayArgOr("shortArrayOrDefault", shortArrayOf())

        private val intArrayOrDefault by bindIntArrayArgOr("intArrayOrDefault", intArrayOf())

        private val intArrayListOrDefault by bindIntArrayListArgOr("intArrayListOrDefault", arrayListOf())

        private val longArrayOrDefault by bindLongArrayArgOr("longArrayOrDefault", longArrayOf())

        private val floatArrayOrDefault by bindFloatArrayArgOr("floatArrayOrDefault", floatArrayOf())

        private val doubleArrayOrDefault by bindDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf())

        private val booleanArrayOrDefault by bindBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf())

        private val charArrayOrDefault by bindCharArrayArgOr("charArrayOrDefault", charArrayOf())

        private val charSequenceOrDefault by bindCharSequenceArgOr("charSequenceOrDefault", "default")

        private val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf())

        private val charSequenceArrayListRequired by bindCharSequenceArrayListArg("charSequenceArrayListRequired")
        private val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
        private val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf())

        private val stringOrDefault by bindStringArgOr("stringOrDefault", "")

        private val stringArrayOrDefault by bindStringArrayArgOr("stringArrayOrDefault", arrayOf())

        private val stringArrayListOrDefault by bindStringArrayListArgOr("stringArrayListOrDefault", arrayListOf())

        private val parcelableOrDefault by bindParcelableArgOr("parcelableOrDefault", TestParcelable("default"))

        private val parcelableArrayOrDefault by bindParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())

        private val parcelableArrayListOrDefault by bindParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf())

        private val serializableOrDefault by bindSerializableArgOr("serializableOrDefault", TestSerializable("default"))

        private val bundleOrDefault by bindBundleArgOr("bundleOrDefault", Bundle())

        private val sparseParcelableArrayRequired by bindSparseParcelableArrayArg<TestParcelable>("sparseParcelableArrayRequired")
        private val sparseParcelableArrayOptional by bindSparseParcelableArrayArgOrNull<TestParcelable>("sparseParcelableArrayOptional")
        private val sparseParcelableArrayOrDefault by bindSparseParcelableArrayArgOr<TestParcelable>("sparseParcelableArrayOrDefault", SparseArray())
        private val sparseParcelableArrayOrDefaultErrKey by bindSparseParcelableArrayArgOr("sparseParcelableArrayOrDefaultErrKey",
                SparseArray<TestParcelable>().apply {
                    put(-4, TestParcelable("-4"))
                    put(4, TestParcelable("4"))
                })

        private val binderRequired by bindBinderArg("binderRequired")
        private val binderOptional by bindBinderArgOrNull("binderOptional")
        private val binderOrDefault by bindBinderArgOr("binderOrDefault", TestBinder())
        private val binderOrDefaultErrKey by bindBinderArgOr("binderOrDefaultErrKey", TestBinder("error"))

        private val sizeRequired by bindSizeArg("sizeRequired")
        private val sizeOptional by bindSizeArgOrNull("sizeOptional")
        private val sizeOrDefault by bindSizeArgOr("sizeOrDefault", Size(0, 0))
        private val sizeOrDefaultErrKey by bindSizeArgOr("sizeOrDefaultErrKey", Size(4, 4))

        private val sizeFRequired by bindSizeFArg("sizeFRequired")
        private val sizeFOptional by bindSizeFArgOrNull("sizeFOptional")
        private val sizeFOrDefault by bindSizeFArgOr("sizeFOrDefault", SizeF(0f, 0f))
        private val sizeFOrDefaultErrKey by bindSizeFArgOr("sizeFOrDefaultErrKey", SizeF(4f, 4f))

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

            Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())

            Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())

            Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)

            Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)

            Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)

            Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)

            Assert.assertTrue(doubleArrayOrDefault[0] == 6.toDouble() && doubleArrayOrDefault[1] == (-6).toDouble())

            Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])

            Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')

            Assert.assertTrue(charSequenceOrDefault == "charSequenceOrDefault")

            Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")

            Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
            Assert.assertTrue(charSequenceArrayListOptional?.get(0) == "charSequenceArrayListOptional" && charSequenceArrayListOptional?.get(1) == "optional")
            Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")

            Assert.assertTrue(stringOrDefault == "stringOrDefault")

            Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")

            Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")

            Assert.assertTrue(parcelableOrDefault == TestParcelable("parcelableOrDefault"))

            Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

            Assert.assertTrue(serializableOrDefault == TestSerializable("serializableOrDefault"))

            Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")

            Assert.assertTrue(sparseParcelableArrayRequired[-1] == TestParcelable("-1") && sparseParcelableArrayRequired[1] == TestParcelable("1"))
            Assert.assertTrue(sparseParcelableArrayOptional?.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional?.get(2) == TestParcelable("2"))
            Assert.assertTrue(sparseParcelableArrayOrDefault[-3] == TestParcelable("-3") && sparseParcelableArrayOrDefault[3] == TestParcelable("3"))
            Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey[-4] == TestParcelable("-4") && sparseParcelableArrayOrDefaultErrKey[4] == TestParcelable("4"))

            Assert.assertTrue(binderRequired == TestBinder("binderRequired"))
            Assert.assertTrue(binderOptional == TestBinder("binderOptional"))
            Assert.assertTrue(binderOrDefault == TestBinder("binderOrDefault"))
            Assert.assertTrue(binderOrDefaultErrKey == TestBinder("error"))

            Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
            Assert.assertTrue(sizeOptional?.width == 2 && sizeOptional?.height == 2)
            Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
            Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)

            Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
            Assert.assertTrue(sizeFOptional?.width == 2f && sizeFOptional?.height == 2f)
            Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
            Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

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

                putByteArray("byteArrayOrDefault", activity.byteArrayOrDefault)
                putShortArray("shortArrayOrDefault", activity.shortArrayOrDefault)
                putIntArray("intArrayOrDefault", activity.intArrayOrDefault)
                putIntegerArrayList("intArrayListOrDefault", activity.intArrayListOrDefault)
                putLongArray("longArrayOrDefault", activity.longArrayOrDefault)
                putFloatArray("floatArrayOrDefault", activity.floatArrayOrDefault)
                putDoubleArray("doubleArrayOrDefault", activity.doubleArrayOrDefault)
                putBooleanArray("booleanArrayOrDefault", activity.booleanArrayOrDefault)
                putCharArray("charArrayOrDefault", activity.charArrayOrDefault)
                putCharSequence("charSequenceOrDefault", activity.charSequenceOrDefault)
                putCharSequenceArray("charSequenceArrayOrDefault", activity.charSequenceArrayOrDefault)

                putCharSequenceArrayList("charSequenceArrayListRequired", activity.charSequenceArrayListRequired)
                putCharSequenceArrayList("charSequenceArrayListOptional", activity.charSequenceArrayListOptional)
                putCharSequenceArrayList("charSequenceArrayListOrDefault", activity.charSequenceArrayListOrDefault)

                putString("stringOrDefault", activity.stringOrDefault)
                putStringArray("stringArrayOrDefault", activity.stringArrayOrDefault)
                putStringArrayList("stringArrayListOrDefault", activity.stringArrayListOrDefault)

                putParcelable("parcelableOrDefault", activity.parcelableOrDefault)
                putParcelableArray("parcelableArrayOrDefault", activity.parcelableArrayOrDefault)
                putParcelableArrayList("parcelableArrayListOrDefault", activity.parcelableArrayListOrDefault)

                putSerializable("serializableOrDefault", activity.serializableOrDefault)

                putBundle("bundleOrDefault", activity.bundleOrDefault)

                putSparseParcelableArray("sparseParcelableArrayRequired", SparseArray<TestParcelable>()
                        .apply {
                            put(-1, TestParcelable("-1"))
                            put(1, TestParcelable("1"))
                        })
                putSparseParcelableArray("sparseParcelableArrayOptional", SparseArray<TestParcelable>()
                        .apply {
                            put(-2, TestParcelable("-2"))
                            put(2, TestParcelable("2"))
                        })
                putSparseParcelableArray("sparseParcelableArrayOrDefault", SparseArray<TestParcelable>()
                        .apply {
                            put(-3, TestParcelable("-3"))
                            put(3, TestParcelable("3"))
                        })

                putBinder("binderRequired", TestBinder("binderRequired"))
                putBinder("binderOptional", TestBinder("binderOptional"))
                putBinder("binderOrDefault", TestBinder("binderOrDefault"))

                putSize("sizeRequired", Size(1, 1))
                putSize("sizeOptional", Size(2, 2))
                putSize("sizeOrDefault", Size(3, 3))

                putSizeF("sizeFRequired", SizeF(1f, 1f))
                putSizeF("sizeFOptional", SizeF(2f, 2f))
                putSizeF("sizeFOrDefault", SizeF(3f, 3f))

            }
        }
    }

    class TestNoExtrasActivity : FragmentActivity() {

        private val byteArrayOrDefaultErrKey by bindByteArrayArgOr("byteArrayOrDefaultErrorKey", byteArrayOf(120.toByte()))

        private val shortArrayOrDefaultErrKey by bindShortArrayArgOr("shortArrayOrDefaultErrorKey", shortArrayOf(130.toShort()))

        private val intArrayOrDefaultErrKey by bindIntArrayArgOr("intArrayOrDefaultErrorKey", intArrayOf(140))

        private val intArrayListOrDefaultErrKey by bindIntArrayListArgOr("intArrayListOrDefaultErrorKey", arrayListOf(150))

        private val longArrayOrDefaultErrKey by bindLongArrayArgOr("longArrayOrDefaultErrorKey", longArrayOf(160L))

        private val floatArrayOrDefaultErrKey by bindFloatArrayArgOr("floatArrayOrDefaultErrorKey", floatArrayOf(170f))

        private val doubleArrayOrDefaultErrKey by bindDoubleArrayArgOr("doubleArrayOrDefaultErrorKey", doubleArrayOf(180.toDouble()))

        private val booleanArrayOrDefaultErrKey by bindBooleanArrayArgOr("booleanArrayOrDefaultErrorKey", booleanArrayOf(true))

        private val charArrayOrDefaultErrKey by bindCharArrayArgOr("charArrayOrDefaultErrorKey", charArrayOf('e'))

        private val charSequenceOrDefaultErrKey by bindCharSequenceArgOr("charSequenceOrDefaultErrorKey", "error")

        private val charSequenceArrayOrDefaultErrKey by bindCharSequenceArrayArgOr("charSequenceArrayOrDefaultErrorKey", arrayOf("error" as CharSequence))

        private val charSequenceArrayListOrDefaultErrKey by bindCharSequenceArrayListArgOr("charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "error"))

        private val stringOrDefaultErrKey by bindStringArgOr("stringOrDefaultErrKey", "error")

        private val stringArrayOrDefaultErrKey by bindStringArrayArgOr("stringArrayOrDefaultErrKey", arrayOf("stringArrayOrDefaultErrKey", "error"))

        private val stringArrayListOrDefaultErrKey by bindStringArrayListArgOr("stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "error"))

        private val parcelableOrDefaultErrKey by bindParcelableArgOr("parcelableOrDefaultErrKey", TestParcelable("error"))

        private val parcelableArrayOrDefaultErrKey by bindParcelableArrayArgOr("parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("parcelableArrayOrDefaultErrKey"), TestParcelable("error")))

        private val parcelableArrayListOrDefaultErrKey by bindParcelableArrayListArgOr("parcelableArrayListOrDefaultErrKey", arrayListOf(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("error")))

        private val serializableOrDefaultErrKey by bindSerializableArgOr("serializableOrDefaultErrKey", TestSerializable("error"))

        private val bundleOrDefaultErrKey by bindBundleArgOr("bundleOrDefaultErrKey", Bundle().apply { putString("bundle", "error") })

        private val extrasOrDefaultErrKey by bindExtrasArgOr(Bundle().apply { putString("extrasOrDefaultErrKey", "error") })

        companion object {
            fun createIntent(context: Context) = Intent(context, TestNoExtrasActivity::class.java)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(me.panpf.androidxkt.test.R.layout.at_test)
        }

        fun assert() {
            Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 120.toByte())

            Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 130.toShort())

            Assert.assertTrue(intArrayOrDefaultErrKey[0] == 140)

            Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 150)

            Assert.assertTrue(longArrayOrDefaultErrKey[0] == 160L)

            Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 170f)

            Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 180.toDouble())

            Assert.assertTrue(booleanArrayOrDefaultErrKey[0])

            Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'e')

            Assert.assertTrue(charSequenceOrDefaultErrKey == "error")

            Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error")

            Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "error")

            Assert.assertTrue(stringOrDefaultErrKey == "error")

            Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "stringArrayOrDefaultErrKey" && stringArrayOrDefaultErrKey[1] == "error")

            Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "error")

            Assert.assertTrue(parcelableOrDefaultErrKey == TestParcelable("error"))

            Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("parcelableArrayOrDefaultErrKey") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("error"))

            Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("error"))

            Assert.assertTrue(serializableOrDefaultErrKey == TestSerializable("error"))

            Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "error")

            Assert.assertTrue(extrasOrDefaultErrKey.getString("extrasOrDefaultErrKey") == "error")
        }
    }

    class TestUriActivity : FragmentActivity() {

        private val byteUriRequired by bindByteUriArg("byteUriRequired")
        private val byteUriOptional by bindByteUriArgOrNull("byteUriOptional")
        private val byteUriOrDefault by bindByteUriArgOr("byteUriOrDefault")
        private val byteUriOrDefaultErrKey by bindByteUriArgOr("byteUriOrDefaultErrKey")

        private val shortUriRequired by bindShortUriArg("shortUriRequired")
        private val shortUriOptional by bindShortUriArgOrNull("shortUriOptional")
        private val shortUriOrDefault by bindShortUriArgOr("shortUriOrDefault")
        private val shortUriOrDefaultErrKey by bindShortUriArgOr("shortUriOrDefaultErrKey")

        private val intUriRequired by bindIntUriArg("intUriRequired")
        private val intUriOptional by bindIntUriArgOrNull("intUriOptional")
        private val intUriOrDefault by bindIntUriArgOr("intUriOrDefault")
        private val intUriOrDefaultErrKey by bindIntUriArgOr("intUriOrDefaultErrKey")

        private val longUriRequired by bindLongUriArg("longUriRequired")
        private val longUriOptional by bindLongUriArgOrNull("longUriOptional")
        private val longUriOrDefault by bindLongUriArgOr("longUriOrDefault")
        private val longUriOrDefaultErrKey by bindLongUriArgOr("longUriOrDefaultErrKey")

        private val floatUriRequired by bindFloatUriArg("floatUriRequired")
        private val floatUriOptional by bindFloatUriArgOrNull("floatUriOptional")
        private val floatUriOrDefault by bindFloatUriArgOr("floatUriOrDefault", 0f)
        private val floatUriOrDefaultErrKey by bindFloatUriArgOr("floatUriOrDefaultErrKey", -1f)

        private val doubleUriRequired by bindDoubleUriArg("doubleUriRequired")
        private val doubleUriOptional by bindDoubleUriArgOrNull("doubleUriOptional")
        private val doubleUriOrDefault by bindDoubleUriArgOr("doubleUriOrDefault", 1.toDouble())
        private val doubleUriOrDefaultErrKey by bindDoubleUriArgOr("doubleUriOrDefaultErrKey", (-1).toDouble())

        private val booleanUriRequired by bindBooleanUriArg("booleanUriRequired")
        private val booleanUriOptional by bindBooleanUriArgOrNull("booleanUriOptional")
        private val booleanUriOrDefault by bindBooleanUriArgOr("booleanUriOrDefault", true)
        private val booleanUriOrDefaultErrKey by bindBooleanUriArgOr("booleanUriOrDefaultErrKey", false)

        private val stringUriRequired by bindStringUriArg("stringUriRequired")
        private val stringUriOptional by bindStringUriArgOrNull("stringUriOptional")
        private val stringUriOrDefault by bindStringUriArgOr("stringUriOrDefault", "")
        private val stringUriOrDefaultErrKey by bindStringUriArgOr("stringUriOrDefaultErrKey", "stringUriOrDefaultErrKey")


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        companion object {
            private val params = StringBuilder()
                    .append("byteUriRequired=").append(1.toByte())
                    .append("&byteUriOptional=").append((-1).toByte())
                    .append("&byteUriOrDefault=").append(2.toByte())

                    .append("&shortUriRequired=").append(3.toShort())
                    .append("&shortUriOptional=").append((-3).toShort())
                    .append("&shortUriOrDefault=").append(4.toShort())

                    .append("&intUriRequired=").append(5)
                    .append("&intUriOptional=").append(-5)
                    .append("&intUriOrDefault=").append(6)

                    .append("&longUriRequired=").append(7L)
                    .append("&longUriOptional=").append((-7L))
                    .append("&longUriOrDefault=").append(8L)

                    .append("&floatUriRequired=").append(9f)
                    .append("&floatUriOptional=").append(-9f)
                    .append("&floatUriOrDefault=").append(10f)

                    .append("&doubleUriRequired=").append(11.toDouble())
                    .append("&doubleUriOptional=").append((-11).toDouble())
                    .append("&doubleUriOrDefault=").append(12.toDouble())

                    .append("&booleanUriRequired=").append(true)
                    .append("&booleanUriOptional=").append(true)
                    .append("&booleanUriOrDefault=").append(false)

                    .append("&stringUriRequired=").append("stringUriRequired")
                    .append("&stringUriOptional=").append("stringUriOptional")
                    .append("&stringUriOrDefault=").append("stringUriOrDefault")

            private val uri = Uri.parse("https://github.com/panpf/androidx?$params")
            fun createIntent() = Intent(Intent.ACTION_VIEW, uri)
        }

        fun assert() {
            Assert.assertTrue(byteUriRequired == 1.toByte())
            Assert.assertTrue(byteUriOptional?.run { this == (-1).toByte() } ?: false)
            Assert.assertTrue(byteUriOrDefault == 2.toByte())
            Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

            Assert.assertTrue(shortUriRequired == 3.toShort())
            Assert.assertTrue(shortUriOptional?.run { this == (-3).toShort() } ?: false)
            Assert.assertTrue(shortUriOrDefault == 4.toShort())
            Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

            Assert.assertTrue(intUriRequired == 5)
            Assert.assertTrue(intUriOptional?.run { this == -5 } ?: false)
            Assert.assertTrue(intUriOrDefault == 6)
            Assert.assertTrue(intUriOrDefaultErrKey == 0)

            Assert.assertTrue(longUriRequired == 7L)
            Assert.assertTrue(longUriOptional?.run { this == -7L } ?: false)
            Assert.assertTrue(longUriOrDefault == 8L)
            Assert.assertTrue(longUriOrDefaultErrKey == 0L)

            Assert.assertTrue(floatUriRequired == 9f)
            Assert.assertTrue(floatUriOptional?.run { this == -9f } ?: false)
            Assert.assertTrue(floatUriOrDefault == 10f)
            Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

            Assert.assertTrue(doubleUriRequired == 11.toDouble())
            Assert.assertTrue(doubleUriOptional?.run { this == (-11).toDouble() } ?: false)
            Assert.assertTrue(doubleUriOrDefault == 12.toDouble())
            Assert.assertTrue(doubleUriOrDefaultErrKey == (-1).toDouble())

            Assert.assertTrue(booleanUriRequired)
            Assert.assertTrue(booleanUriOptional ?: false)
            Assert.assertTrue(!booleanUriOrDefault)
            Assert.assertTrue(!booleanUriOrDefaultErrKey)

            Assert.assertTrue(stringUriRequired == "stringUriRequired")
            Assert.assertTrue(stringUriOptional?.run { this == "stringUriOptional" } ?: false)
            Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
            Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")
        }
    }

    class ResTestUriActivity : FragmentActivity() {

        private val byteUriRequired by bindByteUriArg(R.string.byte_uri_required)
        private val byteUriOptional by bindByteUriArgOrNull(R.string.byte_uri_optional)
        private val byteUriOrDefault by bindByteUriArgOr(R.string.byte_uri_or_default)
        private val byteUriOrDefaultErrKey by bindByteUriArgOr(R.string.not_exist_key)

        private val shortUriRequired by bindShortUriArg(R.string.short_uri_required)
        private val shortUriOptional by bindShortUriArgOrNull(R.string.short_uri_optional)
        private val shortUriOrDefault by bindShortUriArgOr(R.string.short_uri_or_default)
        private val shortUriOrDefaultErrKey by bindShortUriArgOr(R.string.not_exist_key)

        private val intUriRequired by bindIntUriArg(R.string.int_uri_required)
        private val intUriOptional by bindIntUriArgOrNull(R.string.int_uri_optional)
        private val intUriOrDefault by bindIntUriArgOr(R.string.int_uri_or_default)
        private val intUriOrDefaultErrKey by bindIntUriArgOr(R.string.not_exist_key)

        private val longUriRequired by bindLongUriArg(R.string.long_uri_required)
        private val longUriOptional by bindLongUriArgOrNull(R.string.long_uri_optional)
        private val longUriOrDefault by bindLongUriArgOr(R.string.long_uri_or_default)
        private val longUriOrDefaultErrKey by bindLongUriArgOr(R.string.not_exist_key)

        private val floatUriRequired by bindFloatUriArg(R.string.float_uri_required)
        private val floatUriOptional by bindFloatUriArgOrNull(R.string.float_uri_optional)
        private val floatUriOrDefault by bindFloatUriArgOr(R.string.float_uri_or_default, 0f)
        private val floatUriOrDefaultErrKey by bindFloatUriArgOr(R.string.not_exist_key, -1f)

        private val doubleUriRequired by bindDoubleUriArg(R.string.double_uri_required)
        private val doubleUriOptional by bindDoubleUriArgOrNull(R.string.double_uri_optional)
        private val doubleUriOrDefault by bindDoubleUriArgOr(R.string.double_uri_or_default, 1.toDouble())
        private val doubleUriOrDefaultErrKey by bindDoubleUriArgOr(R.string.not_exist_key, (-1).toDouble())

        private val booleanUriRequired by bindBooleanUriArg(R.string.boolean_uri_required)
        private val booleanUriOptional by bindBooleanUriArgOrNull(R.string.boolean_uri_optional)
        private val booleanUriOrDefault by bindBooleanUriArgOr(R.string.boolean_uri_or_default, true)
        private val booleanUriOrDefaultErrKey by bindBooleanUriArgOr(R.string.not_exist_key, false)

        private val stringUriRequired by bindStringUriArg(R.string.string_uri_required)
        private val stringUriOptional by bindStringUriArgOrNull(R.string.string_uri_optional)
        private val stringUriOrDefault by bindStringUriArgOr(R.string.string_uri_or_default, "")
        private val stringUriOrDefaultErrKey by bindStringUriArgOr(R.string.not_exist_key, "stringUriOrDefaultErrKey")


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        companion object {
            private val params = StringBuilder()
                    .append("byteUriRequired=").append(1.toByte())
                    .append("&byteUriOptional=").append((-1).toByte())
                    .append("&byteUriOrDefault=").append(2.toByte())

                    .append("&shortUriRequired=").append(3.toShort())
                    .append("&shortUriOptional=").append((-3).toShort())
                    .append("&shortUriOrDefault=").append(4.toShort())

                    .append("&intUriRequired=").append(5)
                    .append("&intUriOptional=").append(-5)
                    .append("&intUriOrDefault=").append(6)

                    .append("&longUriRequired=").append(7L)
                    .append("&longUriOptional=").append((-7L))
                    .append("&longUriOrDefault=").append(8L)

                    .append("&floatUriRequired=").append(9f)
                    .append("&floatUriOptional=").append(-9f)
                    .append("&floatUriOrDefault=").append(10f)

                    .append("&doubleUriRequired=").append(11.toDouble())
                    .append("&doubleUriOptional=").append((-11).toDouble())
                    .append("&doubleUriOrDefault=").append(12.toDouble())

                    .append("&booleanUriRequired=").append(true)
                    .append("&booleanUriOptional=").append(true)
                    .append("&booleanUriOrDefault=").append(false)

                    .append("&stringUriRequired=").append("stringUriRequired")
                    .append("&stringUriOptional=").append("stringUriOptional")
                    .append("&stringUriOrDefault=").append("stringUriOrDefault")

            private val uri = Uri.parse("https://github.com/panpf/androidx/res?$params")
            fun createIntent() = Intent(Intent.ACTION_VIEW, uri)
        }

        fun assert() {
            Assert.assertTrue(byteUriRequired == 1.toByte())
            Assert.assertTrue(byteUriOptional?.run { this == (-1).toByte() } ?: false)
            Assert.assertTrue(byteUriOrDefault == 2.toByte())
            Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

            Assert.assertTrue(shortUriRequired == 3.toShort())
            Assert.assertTrue(shortUriOptional?.run { this == (-3).toShort() } ?: false)
            Assert.assertTrue(shortUriOrDefault == 4.toShort())
            Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

            Assert.assertTrue(intUriRequired == 5)
            Assert.assertTrue(intUriOptional?.run { this == -5 } ?: false)
            Assert.assertTrue(intUriOrDefault == 6)
            Assert.assertTrue(intUriOrDefaultErrKey == 0)

            Assert.assertTrue(longUriRequired == 7L)
            Assert.assertTrue(longUriOptional?.run { this == -7L } ?: false)
            Assert.assertTrue(longUriOrDefault == 8L)
            Assert.assertTrue(longUriOrDefaultErrKey == 0L)

            Assert.assertTrue(floatUriRequired == 9f)
            Assert.assertTrue(floatUriOptional?.run { this == -9f } ?: false)
            Assert.assertTrue(floatUriOrDefault == 10f)
            Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

            Assert.assertTrue(doubleUriRequired == 11.toDouble())
            Assert.assertTrue(doubleUriOptional?.run { this == (-11).toDouble() } ?: false)
            Assert.assertTrue(doubleUriOrDefault == 12.toDouble())
            Assert.assertTrue(doubleUriOrDefaultErrKey == (-1).toDouble())

            Assert.assertTrue(booleanUriRequired)
            Assert.assertTrue(booleanUriOptional ?: false)
            Assert.assertTrue(!booleanUriOrDefault)
            Assert.assertTrue(!booleanUriOrDefaultErrKey)

            Assert.assertTrue(stringUriRequired == "stringUriRequired")
            Assert.assertTrue(stringUriOptional?.run { this == "stringUriOptional" } ?: false)
            Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
            Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")
        }
    }

    class TestOnlyUriNoIntentActivity : FragmentActivity() {

        private val byteIntentUriOrDefault by bindByteIntentUriArgOr("byteIntentUriOrDefault")
        private val shortIntentUriOrDefault by bindShortIntentUriArgOr("shortIntentUriOrDefault")
        private val intIntentUriOrDefault by bindIntIntentUriArgOr("intIntentUriOrDefault")
        private val longIntentUriOrDefault by bindLongIntentUriArgOr("longIntentUriOrDefault")
        private val floatIntentUriOrDefault by bindFloatIntentUriArgOr("floatIntentUriOrDefault", 0f)
        private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)

        private val stringIntentUriRequired by bindStringIntentUriArg("stringIntentUriRequired")
        private val stringIntentUriOptional by bindStringIntentUriArgOrNull("stringIntentUriOptional")
        private val stringIntentUriOrDefault by bindStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        private val stringIntentUriOrDefaultErrKey by bindStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")

        companion object {
            private val params = StringBuilder()
                    .append("byteIntentUriOrDefault=").append(1.toByte())
                    .append("&shortIntentUriOrDefault=").append(2.toShort())
                    .append("&intIntentUriOrDefault=").append(3)
                    .append("&longIntentUriOrDefault=").append(4L)
                    .append("&floatIntentUriOrDefault=").append(5.toFloat())
                    .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                    .append("&booleanIntentUriOrDefault=").append(true)

                    .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                    .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                    .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

            private val uri = Uri.parse("https://github.com/panpf/androidx/uri?$params")

            fun createIntentWithUri() = Intent(Intent.ACTION_VIEW, uri)

        }

        fun assertByUri() {
            Assert.assertTrue(byteIntentUriOrDefault == 1.toByte())
            Assert.assertTrue(shortIntentUriOrDefault == 2.toShort())
            Assert.assertTrue(intIntentUriOrDefault == 3)
            Assert.assertTrue(longIntentUriOrDefault == 4L)
            Assert.assertTrue(floatIntentUriOrDefault == 5.toFloat())
            Assert.assertTrue(doubleIntentUriOrDefault == 6.toDouble())
            Assert.assertTrue(booleanIntentUriOrDefault)

            Assert.assertTrue(stringIntentUriRequired == "stringIntentUriRequired")
            Assert.assertTrue(stringIntentUriOptional?.run { this == "stringIntentUriOptional" }
                    ?: false)
            Assert.assertTrue(stringIntentUriOrDefault == "stringIntentUriOrDefault")
            Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")
        }

    }

    class TestOnlyIntentNoUriActivity : FragmentActivity() {

        private val byteIntentUriOrDefault by bindByteIntentUriArgOr("byteIntentUriOrDefault")
        private val shortIntentUriOrDefault by bindShortIntentUriArgOr("shortIntentUriOrDefault")
        private val intIntentUriOrDefault by bindIntIntentUriArgOr("intIntentUriOrDefault")
        private val longIntentUriOrDefault by bindLongIntentUriArgOr("longIntentUriOrDefault")
        private val floatIntentUriOrDefault by bindFloatIntentUriArgOr("floatIntentUriOrDefault", 0f)
        private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)

        private val stringIntentUriRequired by bindStringIntentUriArg("stringIntentUriRequired")
        private val stringIntentUriOptional by bindStringIntentUriArgOrNull("stringIntentUriOptional")
        private val stringIntentUriOrDefault by bindStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        private val stringIntentUriOrDefaultErrKey by bindStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")

        companion object {

            fun createIntentWithExtras(context: Context) = Intent(context, TestOnlyIntentNoUriActivity::class.java).apply {

                action = Intent.ACTION_VIEW
                data = Uri.parse("https://github.com/panpf")

                putExtra("byteIntentUriOrDefault", (-1).toByte())
                putExtra("shortIntentUriOrDefault", (-2).toShort())
                putExtra("intIntentUriOrDefault", -3)
                putExtra("longIntentUriOrDefault", -4L)
                putExtra("floatIntentUriOrDefault", -5f)
                putExtra("doubleIntentUriOrDefault", (-6).toDouble())
                putExtra("booleanIntentUriOrDefault", true)

                putExtra("stringIntentUriRequired", "stringIntentRequired")
                putExtra("stringIntentUriOptional", "stringIntentOptional")
                putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")
            }

        }

        fun assertByIntent() {
            Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
            Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
            Assert.assertTrue(intIntentUriOrDefault == -3)
            Assert.assertTrue(longIntentUriOrDefault == -4L)
            Assert.assertTrue(floatIntentUriOrDefault == (-5).toFloat())
            Assert.assertTrue(doubleIntentUriOrDefault == (-6).toDouble())
            Assert.assertTrue(booleanIntentUriOrDefault)

            Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
            Assert.assertTrue(stringIntentUriOptional?.run { this == "stringIntentOptional" }
                    ?: false)
            Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
            Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")
        }

    }

    class TestBothIntentUriActivity : FragmentActivity() {

        private val byteIntentUriOrDefault by bindByteIntentUriArgOr("byteIntentUriOrDefault")
        private val shortIntentUriOrDefault by bindShortIntentUriArgOr("shortIntentUriOrDefault")
        private val intIntentUriOrDefault by bindIntIntentUriArgOr("intIntentUriOrDefault")
        private val longIntentUriOrDefault by bindLongIntentUriArgOr("longIntentUriOrDefault")
        private val floatIntentUriOrDefault by bindFloatIntentUriArgOr("floatIntentUriOrDefault", 0f)
        private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)

        private val stringIntentUriRequired by bindStringIntentUriArg("stringIntentUriRequired")
        private val stringIntentUriOptional by bindStringIntentUriArgOrNull("stringIntentUriOptional")
        private val stringIntentUriOrDefault by bindStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        private val stringIntentUriOrDefaultErrKey by bindStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")

        companion object {

            private val params = StringBuilder()
                    .append("byteIntentUriOrDefault=").append(1.toByte())
                    .append("&shortIntentUriOrDefault=").append(2.toShort())
                    .append("&intIntentUriOrDefault=").append(3)
                    .append("&longIntentUriOrDefault=").append(4L)
                    .append("&floatIntentUriOrDefault=").append(5.toFloat())
                    .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                    .append("&booleanIntentUriOrDefault=").append(true)

                    .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                    .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                    .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

            private val uri = Uri.parse("https://github.com/panpf/androidx/uri/intent?$params")

            fun createIntentWithUriAndExtras(context: Context) = Intent(context, TestBothIntentUriActivity::class.java).apply {

                action = Intent.ACTION_VIEW
                data = uri

                putExtra("byteIntentUriOrDefault", (-1).toByte())
                putExtra("shortIntentUriOrDefault", (-2).toShort())
                putExtra("intIntentUriOrDefault", -3)
                putExtra("longIntentUriOrDefault", -4L)
                putExtra("floatIntentUriOrDefault", -5f)
                putExtra("doubleIntentUriOrDefault", (-6).toDouble())
                putExtra("booleanIntentUriOrDefault", true)

                putExtra("stringIntentUriRequired", "stringIntentRequired")
                putExtra("stringIntentUriOptional", "stringIntentOptional")
                putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")
            }

        }

        fun assertByUriIntent() {
            Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
            Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
            Assert.assertTrue(intIntentUriOrDefault == -3)
            Assert.assertTrue(longIntentUriOrDefault == -4L)
            Assert.assertTrue(floatIntentUriOrDefault == (-5).toFloat())
            Assert.assertTrue(doubleIntentUriOrDefault == (-6).toDouble())
            Assert.assertTrue(booleanIntentUriOrDefault)

            Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
            Assert.assertTrue(stringIntentUriOptional?.run { this == "stringIntentOptional" }
                    ?: false)
            Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
            Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")
        }

    }

    class TestNoIntentUriActivity : FragmentActivity() {

        private val byteIntentUriOrDefault by bindByteIntentUriArgOr("byteIntentUriOrDefault")
        private val shortIntentUriOrDefault by bindShortIntentUriArgOr("shortIntentUriOrDefault")
        private val intIntentUriOrDefault by bindIntIntentUriArgOr("intIntentUriOrDefault")
        private val longIntentUriOrDefault by bindLongIntentUriArgOr("longIntentUriOrDefault")
        private val floatIntentUriOrDefault by bindFloatIntentUriArgOr("floatIntentUriOrDefault", 0f)
        private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)

        //private val stringIntentUriRequired by bindStringIntentUriArg("stringIntentUriRequired")
//        private val stringIntentUriOptional by bindStringIntentUriArgOrNull("stringIntentUriOptional")
        private val stringIntentUriOrDefault by bindStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        private val stringIntentUriOrDefaultErrKey by bindStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")

        companion object {

            fun createIntentWithNothing(context: Context) = Intent(context, TestNoIntentUriActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://github.com/panpf")
            }
        }

        fun assertByNothing() {
            Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
            Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
            Assert.assertTrue(intIntentUriOrDefault == 0)
            Assert.assertTrue(longIntentUriOrDefault == 0L)
            Assert.assertTrue(floatIntentUriOrDefault == 0f)
            Assert.assertTrue(doubleIntentUriOrDefault == 0.toDouble())
            Assert.assertTrue(!booleanIntentUriOrDefault)

//            Assert.assertTrue(stringIntentUriOptional == null)
            Assert.assertTrue(stringIntentUriOrDefault == "default")
            Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")
        }

    }


    @Parcelize
    data class TestParcelable(val tag: String) : Parcelable

    data class TestSerializable(val tag: String) : java.io.Serializable

    data class TestBinder(val tag: String = "") : Binder()
}