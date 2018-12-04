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
import me.panpf.androidx.app.Argsx
import me.panpf.androidxkt.test.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * <P>Created by Vincent on 2018/12/4.</P>
 */
@RunWith(AndroidJUnit4::class)
class ArgsxTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<TestActivity> = object : ActivityTestRule<TestActivity>(TestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val mResActivityTestRule: ActivityTestRule<ResTestActivity> = object : ActivityTestRule<ResTestActivity>(ResTestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val mNoExtrasActivityTestRule: ActivityTestRule<NoExtraActivity> = object : ActivityTestRule<NoExtraActivity>(NoExtraActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return NoExtraActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val uriNoIntentActivityActivityTestRule: ActivityTestRule<TestOnlyUriNoIntentActivity> = object : ActivityTestRule<TestOnlyUriNoIntentActivity>(TestOnlyUriNoIntentActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestOnlyUriNoIntentActivity.createIntentWithUri()
        }
    }

    @get:Rule
    val intentNoUriActivityActivityTestRule: ActivityTestRule<TestOnlyIntentNoUriActivity> = object : ActivityTestRule<TestOnlyIntentNoUriActivity>(TestOnlyIntentNoUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestOnlyIntentNoUriActivity.createIntentWithExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val bothIntentUriActivityActivityTestRule: ActivityTestRule<TestBothIntentUriActivity> = object : ActivityTestRule<TestBothIntentUriActivity>(TestBothIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestBothIntentUriActivity.createIntentWithUriAndExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val noIntentUriActivityActivityTestRule: ActivityTestRule<TestNoIntentUriActivity> = object : ActivityTestRule<TestNoIntentUriActivity>(TestNoIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestNoIntentUriActivity.createIntentWithNothing(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resUriNoIntentActivityActivityTestRule: ActivityTestRule<ResTestOnlyUriNoIntentActivity> = object : ActivityTestRule<ResTestOnlyUriNoIntentActivity>(ResTestOnlyUriNoIntentActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestOnlyUriNoIntentActivity.createIntentWithUri()
        }
    }

    @get:Rule
    val resIntentNoUriActivityActivityTestRule: ActivityTestRule<ResTestOnlyIntentNoUriActivity> = object : ActivityTestRule<ResTestOnlyIntentNoUriActivity>(ResTestOnlyIntentNoUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestOnlyIntentNoUriActivity.createIntentWithExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resBothIntentUriActivityActivityTestRule: ActivityTestRule<ResTestBothIntentUriActivity> = object : ActivityTestRule<ResTestBothIntentUriActivity>(ResTestBothIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestBothIntentUriActivity.createIntentWithUriAndExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resNoIntentUriActivityActivityTestRule: ActivityTestRule<ResTestNoIntentUriActivity> = object : ActivityTestRule<ResTestNoIntentUriActivity>(ResTestNoIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestNoIntentUriActivity.createIntentWithNothing(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val uriActivityTestRule: ActivityTestRule<TestUriActivity> = object : ActivityTestRule<TestUriActivity>(TestUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestUriActivity.createIntent()
        }
    }

    @get:Rule
    val resUriActivityTestRule: ActivityTestRule<ResTestUriActivity> = object : ActivityTestRule<ResTestUriActivity>(ResTestUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestUriActivity.createIntent()
        }
    }

    @Test
    fun activityIntentArgsTest() {
        val activity = mActivityTestRule.activity

        val byteRequired = Argsx.readByteArgOr(activity, "byteRequired", 0.toByte())
        val byteRequiredErrKey = Argsx.readByteArgOr(activity, "byteRequiredErrKey", 0.toByte())
        val byteArrayRequired = Argsx.readByteArrayArg(activity, "byteArrayRequired")
        val byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptional")
        val byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptionalErrKey")
        val byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefault", byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefaultErrKey", byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = Argsx.readShortArgOr(activity, "shortRequired", 0.toShort())
        val shortRequiredErrKey = Argsx.readShortArgOr(activity, "shortRequiredErrKey", 0.toShort())
        val shortArrayRequired = Argsx.readShortArrayArg(activity, "shortArrayRequired")
        val shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptional")
        val shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptionalErrKey")
        val shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefault", shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefaultErrKey", shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = Argsx.readIntArgOr(activity, "intRequired", 0)
        val intRequiredErrKey = Argsx.readIntArgOr(activity, "intRequiredErrKey", 0)
        val intArrayRequired = Argsx.readIntArrayArg(activity, "intArrayRequired")
        val intArrayOptional = Argsx.readIntArrayArgOrNull(activity, "intArrayOptional")
        val intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, "intArrayOptionalErrKey")
        val intArrayOrDefault = Argsx.readIntArrayArgOr(activity, "intArrayOrDefault", intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, "intArrayOrDefaultErrKey", intArrayOf(0, -1))
        val intArrayListRequired = Argsx.readIntArrayListArg(activity, "intArrayListRequired")
        val intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefault", arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefaultErrKey", arrayListOf(0, 0))
        val intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptional")
        val intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptionalErrKey")

        val longRequired = Argsx.readLongArgOr(activity, "longRequired", 0)
        val longRequiredErrKey = Argsx.readLongArgOr(activity, "longRequiredErrKey", 0)
        val longArrayRequired = Argsx.readLongArrayArg(activity, "longArrayRequired")
        val longArrayOptional = Argsx.readLongArrayArgOrNull(activity, "longArrayOptional")
        val longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, "longArrayOptionalErrKey")
        val longArrayOrDefault = Argsx.readLongArrayArgOr(activity, "longArrayOrDefault", longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, "longArrayOrDefaultErrKey", longArrayOf(0, -1))

        val floatRequired = Argsx.readFloatArgOr(activity, "floatRequired", 0f)
        val floatRequiredErrKey = Argsx.readFloatArgOr(activity, "floatRequiredErrKey", 0f)
        val floatArrayRequired = Argsx.readFloatArrayArg(activity, "floatArrayRequired")
        val floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, "floatArrayOptional")
        val floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, "DoubleArrayOptionalErrKey")
        val floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefault", floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefaultErrKey", floatArrayOf(0f, -1f))

        val doubleRequired = Argsx.readDoubleArgOr(activity, "doubleRequired", 0.0)
        val doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, "doubleRequiredErrKey", 0.0)
        val doubleArrayRequired = Argsx.readDoubleArrayArg(activity, "doubleArrayRequired")
        val doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptional")
        val doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptionalErrKey")
        val doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefault", doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefaultErrKey", doubleArrayOf(0.0, -1.0))

        val booleanRequired = Argsx.readBooleanArgOr(activity, "booleanRequired", false)
        val booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, "booleanRequiredErrKey", false)
        val booleanArrayRequired = Argsx.readBooleanArrayArg(activity, "booleanArrayRequired")
        val booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptional")
        val booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptionalErrKey")
        val booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefault", booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefaultErrKey", booleanArrayOf(false, true))

        val charRequired = Argsx.readCharArgOr(activity, "charRequired", 'a')
        val charRequiredErrKey = Argsx.readCharArgOr(activity, "charRequiredErrKey", 'b')
        val charArrayRequired = Argsx.readCharArrayArg(activity, "charArrayRequired")
        val charArrayOptional = Argsx.readCharArrayArgOrNull(activity, "charArrayOptional")
        val charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, "charArrayOptionalErrKey")
        val charArrayOrDefault = Argsx.readCharArrayArgOr(activity, "charArrayOrDefault", charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, "charArrayOrDefaultErrKey", charArrayOf('b', 'a'))

        val stringRequired = Argsx.readStringArgOr(activity, "stringRequired", "stringRequired")
        val stringRequiredErrKey = Argsx.readStringArgOr(activity, "stringRequiredErrKey", "stringRequiredErrKey")
        val stringArrayRequired = Argsx.readStringArrayArg(activity, "stringArrayRequired")
        val stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptional")
        val stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptionalErrKey")
        val stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefault", arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val stringArrayListRequired = Argsx.readStringArrayListArg(activity, "stringArrayListRequired")
        val stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefault", arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptional")
        val stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptionalErrKey")

        val charSequenceRequired = Argsx.readCharSequenceArgOr(activity, "charSequenceRequired", "charSequenceRequired")
        val charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, "charSequenceRequiredErrKey", "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = Argsx.readCharSequenceArrayArg(activity, "charSequenceArrayRequired")
        val charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptional")
        val charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptionalErrKey")
        val charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefault", arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArg(activity, "charSequenceArrayListRequired")
        val charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefault", arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptional")
        val charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptionalErrKey")


        val parcelableRequired = Argsx.readParcelableArgOr(activity, "parcelableRequired", TestParcelable("required"))
        val parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, "parcelableRequiredErrKey", TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = Argsx.readParcelableArrayArg<Parcelable>(activity, "parcelableArrayRequired")
        val parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, "parcelableArrayOptional")
        val parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, "parcelableArrayOptionalErrKey")
        val parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefault", arrayOf())
        val parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = Argsx.readParcelableArrayListArg<Parcelable>(activity, "parcelableArrayListRequired")
        val parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefault", arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefaultErrKey", arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, "parcelableArrayListOptional")
        val parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, "parcelableArrayListOptionalErrKey")

        val serializableRequired = Argsx.readSerializableArg<TestSerializable>(activity, "serializableRequired")
        val serializableOptional = Argsx.readSerializableArgOrNull<TestSerializable>(activity, "serializableOptional")
        val serializableOptionalErrKey = Argsx.readSerializableArgOrNull<TestSerializable>(activity, "serializableOptionalErrKey")
        val serializableOrDefault = Argsx.readSerializableArgOr(activity, "serializableOrDefault", TestSerializable("default"))
        val serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, "serializableOrDefaultErrKey", TestSerializable("errKey"))

        val bundleRequired = Argsx.readBundleArg(activity, "bundleRequired")
        val bundleOptional = Argsx.readBundleArgOrNull(activity, "bundleOptional")
        val bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, "bundleOptionalErrKey")
        val bundleOrDefault = Argsx.readBundleArgOr(activity, "bundleOrDefault", Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, "bundleOrDefaultErrKey", defaultBundle)

        val extrasRequired = Argsx.readExtrasArg(activity)
        val extrasOptional = Argsx.readExtrasArgOrNull(activity)
        val extrasDefault = Argsx.readExtrasArgOr(activity, Bundle())

        //test start
        Assert.assertTrue(extrasRequired.getString("extrasRequired") == "extrasRequired")
        Assert.assertTrue(extrasOptional!!.getString("extrasOptional") == "extrasOptional")
        Assert.assertTrue(extrasDefault.getString("extrasOrDefault") == "extrasOrDefault")

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun supportFragmentArgsTest() {
        val activityHost = mActivityTestRule.activity
        val activity = activityHost.supportFragmentManager.findFragmentById(R.id.testAt_frame)!!

        val byteRequired = Argsx.readByteArgOr(activity, "byteRequired", 0.toByte())
        val byteRequiredErrKey = Argsx.readByteArgOr(activity, "byteRequiredErrKey", 0.toByte())
        val byteArrayRequired = Argsx.readByteArrayArg(activity, "byteArrayRequired")
        val byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptional")
        val byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptionalErrKey")
        val byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefault", byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefaultErrKey", byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = Argsx.readShortArgOr(activity, "shortRequired", 0.toShort())
        val shortRequiredErrKey = Argsx.readShortArgOr(activity, "shortRequiredErrKey", 0.toShort())
        val shortArrayRequired = Argsx.readShortArrayArg(activity, "shortArrayRequired")
        val shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptional")
        val shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptionalErrKey")
        val shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefault", shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefaultErrKey", shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = Argsx.readIntArgOr(activity, "intRequired", 0)
        val intRequiredErrKey = Argsx.readIntArgOr(activity, "intRequiredErrKey", 0)
        val intArrayRequired = Argsx.readIntArrayArg(activity, "intArrayRequired")
        val intArrayOptional = Argsx.readIntArrayArgOrNull(activity, "intArrayOptional")
        val intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, "intArrayOptionalErrKey")
        val intArrayOrDefault = Argsx.readIntArrayArgOr(activity, "intArrayOrDefault", intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, "intArrayOrDefaultErrKey", intArrayOf(0, -1))
        val intArrayListRequired = Argsx.readIntArrayListArg(activity, "intArrayListRequired")
        val intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefault", arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefaultErrKey", arrayListOf(0, 0))
        val intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptional")
        val intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptionalErrKey")

        val longRequired = Argsx.readLongArgOr(activity, "longRequired", 0)
        val longRequiredErrKey = Argsx.readLongArgOr(activity, "longRequiredErrKey", 0)
        val longArrayRequired = Argsx.readLongArrayArg(activity, "longArrayRequired")
        val longArrayOptional = Argsx.readLongArrayArgOrNull(activity, "longArrayOptional")
        val longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, "longArrayOptionalErrKey")
        val longArrayOrDefault = Argsx.readLongArrayArgOr(activity, "longArrayOrDefault", longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, "longArrayOrDefaultErrKey", longArrayOf(0, -1))

        val floatRequired = Argsx.readFloatArgOr(activity, "floatRequired", 0f)
        val floatRequiredErrKey = Argsx.readFloatArgOr(activity, "floatRequiredErrKey", 0f)
        val floatArrayRequired = Argsx.readFloatArrayArg(activity, "floatArrayRequired")
        val floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, "floatArrayOptional")
        val floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, "DoubleArrayOptionalErrKey")
        val floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefault", floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefaultErrKey", floatArrayOf(0f, -1f))

        val doubleRequired = Argsx.readDoubleArgOr(activity, "doubleRequired", 0.0)
        val doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, "doubleRequiredErrKey", 0.0)
        val doubleArrayRequired = Argsx.readDoubleArrayArg(activity, "doubleArrayRequired")
        val doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptional")
        val doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptionalErrKey")
        val doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefault", doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefaultErrKey", doubleArrayOf(0.0, -1.0))

        val booleanRequired = Argsx.readBooleanArgOr(activity, "booleanRequired", false)
        val booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, "booleanRequiredErrKey", false)
        val booleanArrayRequired = Argsx.readBooleanArrayArg(activity, "booleanArrayRequired")
        val booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptional")
        val booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptionalErrKey")
        val booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefault", booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefaultErrKey", booleanArrayOf(false, true))

        val charRequired = Argsx.readCharArgOr(activity, "charRequired", 'a')
        val charRequiredErrKey = Argsx.readCharArgOr(activity, "charRequiredErrKey", 'b')
        val charArrayRequired = Argsx.readCharArrayArg(activity, "charArrayRequired")
        val charArrayOptional = Argsx.readCharArrayArgOrNull(activity, "charArrayOptional")
        val charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, "charArrayOptionalErrKey")
        val charArrayOrDefault = Argsx.readCharArrayArgOr(activity, "charArrayOrDefault", charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, "charArrayOrDefaultErrKey", charArrayOf('b', 'a'))

        val stringRequired = Argsx.readStringArgOr(activity, "stringRequired", "stringRequired")
        val stringRequiredErrKey = Argsx.readStringArgOr(activity, "stringRequiredErrKey", "stringRequiredErrKey")
        val stringArrayRequired = Argsx.readStringArrayArg(activity, "stringArrayRequired")
        val stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptional")
        val stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptionalErrKey")
        val stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefault", arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val stringArrayListRequired = Argsx.readStringArrayListArg(activity, "stringArrayListRequired")
        val stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefault", arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptional")
        val stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptionalErrKey")

        val charSequenceRequired = Argsx.readCharSequenceArgOr(activity, "charSequenceRequired", "charSequenceRequired")
        val charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, "charSequenceRequiredErrKey", "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = Argsx.readCharSequenceArrayArg(activity, "charSequenceArrayRequired")
        val charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptional")
        val charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptionalErrKey")
        val charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefault", arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArg(activity, "charSequenceArrayListRequired")
        val charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefault", arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptional")
        val charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptionalErrKey")


        val parcelableRequired = Argsx.readParcelableArgOr(activity, "parcelableRequired", TestParcelable("required"))
        val parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, "parcelableRequiredErrKey", TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = Argsx.readParcelableArrayArg<Parcelable>(activity, "parcelableArrayRequired")
        val parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, "parcelableArrayOptional")
        val parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, "parcelableArrayOptionalErrKey")
        val parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefault", arrayOf())
        val parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = Argsx.readParcelableArrayListArg<Parcelable>(activity, "parcelableArrayListRequired")
        val parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefault",arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefaultErrKey", arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, "parcelableArrayListOptional")
        val parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, "parcelableArrayListOptionalErrKey")

        val serializableRequired = Argsx.readSerializableArg<TestSerializable>(activity, "serializableRequired")
        val serializableOptional = Argsx.readSerializableArgOrNull<TestSerializable>(activity, "serializableOptional")
        val serializableOptionalErrKey = Argsx.readSerializableArgOrNull<TestSerializable>(activity, "serializableOptionalErrKey")
        val serializableOrDefault = Argsx.readSerializableArgOr(activity, "serializableOrDefault", TestSerializable("default"))
        val serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, "serializableOrDefaultErrKey", TestSerializable("errKey"))

        val bundleRequired = Argsx.readBundleArg(activity, "bundleRequired")
        val bundleOptional = Argsx.readBundleArgOrNull(activity, "bundleOptional")
        val bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, "bundleOptionalErrKey")
        val bundleOrDefault = Argsx.readBundleArgOr(activity, "bundleOrDefault", Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, "bundleOrDefaultErrKey", defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArg<Parcelable>(activity, "sparseParcelableArrayRequired")
        val sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, "sparseParcelableArrayOptional")
        val sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, "sparseParcelableArrayOptionalErrKey")
        val sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(activity, "sparseParcelableArrayOrDefault", sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(activity, "sparseParcelableArrayOrDefaultErrKey", sparseArrayDefault)

        val binderRequired = Argsx.readBinderArg(activity, "binderRequired")
        val binderOptional = Argsx.readBinderArgOrNull(activity, "binderOptional")
        val binderOptionalErrKey = Argsx.readBinderArgOrNull(activity, "binderOptionalErrKey")
        val binderOrDefault = Argsx.readBinderArgOr(activity, "binderOrDefault", TestBinder(""))
        val binderOrDefaultErrKey = Argsx.readBinderArgOr(activity, "binderOrDefaultErrKey", TestBinder("binderOrDefaultErrKey"))

        val sizeRequired = Argsx.readSizeArg(activity, "sizeRequired")
        val sizeOptional = Argsx.readSizeArgOrNull(activity, "sizeOptional")
        val sizeOptionalErrKey = Argsx.readSizeArgOrNull(activity, "sizeOptionalErrKey")
        val sizeOrDefault = Argsx.readSizeArgOr(activity, "sizeOrDefault", Size(0, 0))
        val sizeOrDefaultErrKey = Argsx.readSizeArgOr(activity, "sizeOrDefaultErrKey", Size(4, 4))

        val sizeFRequired = Argsx.readSizeFArg(activity, "sizeFRequired")
        val sizeFOptional = Argsx.readSizeFArgOrNull(activity, "sizeFOptional")
        val sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(activity, "sizeFOptionalErrKey")
        val sizeFOrDefault = Argsx.readSizeFArgOr(activity, "sizeFOrDefault", SizeF(0f, 0f))
        val sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(activity, "sizeFOrDefaultErrKey", SizeF(4f, 4f))

        //test start

        Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
        Assert.assertTrue(sizeFOptional!!.width == 2f && sizeFOptional.height == 2f)
        Assert.assertNull(sizeFOptionalErrKey)
        Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
        Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

        Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
        Assert.assertTrue(sizeOptional!!.width == 2 && sizeOptional.height == 2)
        Assert.assertNull(sizeOptionalErrKey)
        Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
        Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)


        Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
        Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
        Assert.assertNull(binderOptionalErrKey)
        Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
        Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))

        Assert.assertTrue(sparseParcelableArrayRequired.get(-1) == TestParcelable("-1") && sparseParcelableArrayRequired.get(1) == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional!!.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional.get(2) == TestParcelable("2"))
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3) == TestParcelable("-3") && sparseParcelableArrayOrDefault.get(3) == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0) == TestParcelable("0"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun fragmentArgsTest() {
        val activityHost = mActivityTestRule.activity
        val activity = activityHost.fragmentManager.findFragmentById(android.R.id.content)!!

        val byteRequired = Argsx.readByteArgOr(activity, "byteRequired", 0.toByte())
        val byteRequiredErrKey = Argsx.readByteArgOr(activity, "byteRequiredErrKey", 0.toByte())
        val byteArrayRequired = Argsx.readByteArrayArg(activity, "byteArrayRequired")
        val byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptional")
        val byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptionalErrKey")
        val byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefault", byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefaultErrKey", byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = Argsx.readShortArgOr(activity, "shortRequired", 0.toShort())
        val shortRequiredErrKey = Argsx.readShortArgOr(activity, "shortRequiredErrKey", 0.toShort())
        val shortArrayRequired = Argsx.readShortArrayArg(activity, "shortArrayRequired")
        val shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptional")
        val shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptionalErrKey")
        val shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefault", shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefaultErrKey", shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = Argsx.readIntArgOr(activity, "intRequired", 0)
        val intRequiredErrKey = Argsx.readIntArgOr(activity, "intRequiredErrKey", 0)
        val intArrayRequired = Argsx.readIntArrayArg(activity, "intArrayRequired")
        val intArrayOptional = Argsx.readIntArrayArgOrNull(activity, "intArrayOptional")
        val intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, "intArrayOptionalErrKey")
        val intArrayOrDefault = Argsx.readIntArrayArgOr(activity, "intArrayOrDefault", intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, "intArrayOrDefaultErrKey", intArrayOf(0, -1))
        val intArrayListRequired = Argsx.readIntArrayListArg(activity, "intArrayListRequired")
        val intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefault", arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefaultErrKey", arrayListOf(0, 0))
        val intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptional")
        val intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptionalErrKey")

        val longRequired = Argsx.readLongArgOr(activity, "longRequired", 0)
        val longRequiredErrKey = Argsx.readLongArgOr(activity, "longRequiredErrKey", 0)
        val longArrayRequired = Argsx.readLongArrayArg(activity, "longArrayRequired")
        val longArrayOptional = Argsx.readLongArrayArgOrNull(activity, "longArrayOptional")
        val longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, "longArrayOptionalErrKey")
        val longArrayOrDefault = Argsx.readLongArrayArgOr(activity, "longArrayOrDefault", longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, "longArrayOrDefaultErrKey", longArrayOf(0, -1))

        val floatRequired = Argsx.readFloatArgOr(activity, "floatRequired", 0f)
        val floatRequiredErrKey = Argsx.readFloatArgOr(activity, "floatRequiredErrKey", 0f)
        val floatArrayRequired = Argsx.readFloatArrayArg(activity, "floatArrayRequired")
        val floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, "floatArrayOptional")
        val floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, "DoubleArrayOptionalErrKey")
        val floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefault", floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefaultErrKey", floatArrayOf(0f, -1f))

        val doubleRequired = Argsx.readDoubleArgOr(activity, "doubleRequired", 0.0)
        val doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, "doubleRequiredErrKey", 0.0)
        val doubleArrayRequired = Argsx.readDoubleArrayArg(activity, "doubleArrayRequired")
        val doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptional")
        val doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptionalErrKey")
        val doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefault", doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefaultErrKey", doubleArrayOf(0.0, -1.0))

        val booleanRequired = Argsx.readBooleanArgOr(activity, "booleanRequired", false)
        val booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, "booleanRequiredErrKey", false)
        val booleanArrayRequired = Argsx.readBooleanArrayArg(activity, "booleanArrayRequired")
        val booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptional")
        val booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptionalErrKey")
        val booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefault", booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefaultErrKey", booleanArrayOf(false, true))

        val charRequired = Argsx.readCharArgOr(activity, "charRequired", 'a')
        val charRequiredErrKey = Argsx.readCharArgOr(activity, "charRequiredErrKey", 'b')
        val charArrayRequired = Argsx.readCharArrayArg(activity, "charArrayRequired")
        val charArrayOptional = Argsx.readCharArrayArgOrNull(activity, "charArrayOptional")
        val charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, "charArrayOptionalErrKey")
        val charArrayOrDefault = Argsx.readCharArrayArgOr(activity, "charArrayOrDefault", charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, "charArrayOrDefaultErrKey", charArrayOf('b', 'a'))

        val stringRequired = Argsx.readStringArgOr(activity, "stringRequired", "stringRequired")
        val stringRequiredErrKey = Argsx.readStringArgOr(activity, "stringRequiredErrKey", "stringRequiredErrKey")
        val stringArrayRequired = Argsx.readStringArrayArg(activity, "stringArrayRequired")
        val stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptional")
        val stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptionalErrKey")
        val stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefault", arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val stringArrayListRequired = Argsx.readStringArrayListArg(activity, "stringArrayListRequired")
        val stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefault", arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptional")
        val stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptionalErrKey")

        val charSequenceRequired = Argsx.readCharSequenceArgOr(activity, "charSequenceRequired", "charSequenceRequired")
        val charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, "charSequenceRequiredErrKey", "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = Argsx.readCharSequenceArrayArg(activity, "charSequenceArrayRequired")
        val charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptional")
        val charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptionalErrKey")
        val charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefault", arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArg(activity, "charSequenceArrayListRequired")
        val charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefault", arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptional")
        val charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptionalErrKey")


        val parcelableRequired = Argsx.readParcelableArgOr(activity, "parcelableRequired", TestParcelable("required"))
        val parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, "parcelableRequiredErrKey", TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = Argsx.readParcelableArrayArg<Parcelable>(activity, "parcelableArrayRequired")
        val parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, "parcelableArrayOptional")
        val parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, "parcelableArrayOptionalErrKey")
        val parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefault", arrayOf())
        val parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = Argsx.readParcelableArrayListArg<Parcelable>(activity, "parcelableArrayListRequired")
        val parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefault", arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefaultErrKey", arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, "parcelableArrayListOptional")
        val parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, "parcelableArrayListOptionalErrKey")

        val serializableRequired = Argsx.readSerializableArg<TestSerializable>(activity, "serializableRequired")
        val serializableOptional = Argsx.readSerializableArgOrNull<TestSerializable>(activity, "serializableOptional")
        val serializableOptionalErrKey = Argsx.readSerializableArgOrNull<TestSerializable>(activity, "serializableOptionalErrKey")
        val serializableOrDefault = Argsx.readSerializableArgOr(activity, "serializableOrDefault", TestSerializable("default"))
        val serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, "serializableOrDefaultErrKey", TestSerializable("errKey"))

        val bundleRequired = Argsx.readBundleArg(activity, "bundleRequired")
        val bundleOptional = Argsx.readBundleArgOrNull(activity, "bundleOptional")
        val bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, "bundleOptionalErrKey")
        val bundleOrDefault = Argsx.readBundleArgOr(activity, "bundleOrDefault", Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, "bundleOrDefaultErrKey", defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArg<Parcelable>(activity, "sparseParcelableArrayRequired")
        val sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, "sparseParcelableArrayOptional")
        val sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, "sparseParcelableArrayOptionalErrKey")
        val sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(activity, "sparseParcelableArrayOrDefault", sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(activity, "sparseParcelableArrayOrDefaultErrKey", sparseArrayDefault)

        val binderRequired = Argsx.readBinderArg(activity, "binderRequired")
        val binderOptional = Argsx.readBinderArgOrNull(activity, "binderOptional")
        val binderOptionalErrKey = Argsx.readBinderArgOrNull(activity, "binderOptionalErrKey")
        val binderOrDefault = Argsx.readBinderArgOr(activity, "binderOrDefault", TestBinder(""))
        val binderOrDefaultErrKey = Argsx.readBinderArgOr(activity, "binderOrDefaultErrKey", TestBinder("binderOrDefaultErrKey"))

        val sizeRequired = Argsx.readSizeArg(activity, "sizeRequired")
        val sizeOptional = Argsx.readSizeArgOrNull(activity, "sizeOptional")
        val sizeOptionalErrKey = Argsx.readSizeArgOrNull(activity, "sizeOptionalErrKey")
        val sizeOrDefault = Argsx.readSizeArgOr(activity, "sizeOrDefault", Size(0, 0))
        val sizeOrDefaultErrKey = Argsx.readSizeArgOr(activity, "sizeOrDefaultErrKey", Size(4, 4))

        val sizeFRequired = Argsx.readSizeFArg(activity, "sizeFRequired")
        val sizeFOptional = Argsx.readSizeFArgOrNull(activity, "sizeFOptional")
        val sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(activity, "sizeFOptionalErrKey")
        val sizeFOrDefault = Argsx.readSizeFArgOr(activity, "sizeFOrDefault", SizeF(0f, 0f))
        val sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(activity, "sizeFOrDefaultErrKey", SizeF(4f, 4f))

        //test start

        Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
        Assert.assertTrue(sizeFOptional!!.width == 2f && sizeFOptional.height == 2f)
        Assert.assertNull(sizeFOptionalErrKey)
        Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
        Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

        Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
        Assert.assertTrue(sizeOptional!!.width == 2 && sizeOptional.height == 2)
        Assert.assertNull(sizeOptionalErrKey)
        Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
        Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)


        Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
        Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
        Assert.assertNull(binderOptionalErrKey)
        Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
        Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))

        Assert.assertTrue(sparseParcelableArrayRequired.get(-1) == TestParcelable("-1") && sparseParcelableArrayRequired.get(1) == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional!!.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional.get(2) == TestParcelable("2"))
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3) == TestParcelable("-3") && sparseParcelableArrayOrDefault.get(3) == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0) == TestParcelable("0"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun noExtrasActivityTest() {
        val activity = mNoExtrasActivityTestRule.activity

        val extrasOptional = Argsx.readExtrasArgOrNull(activity)
        val bundleDefault = Bundle()
        bundleDefault.putString("errDefault", "default")
        val extrasDefault = Argsx.readExtrasArgOr(activity, bundleDefault)

        Assert.assertNull(extrasOptional)
        Assert.assertTrue(extrasDefault.getString("errDefault") == "default")

    }

    //res
    @Test
    fun resActivityIntentArgsTest() {
        val activity = mResActivityTestRule.activity

        val byteRequired = Argsx.readByteArgOr(activity, R.string.byte_required, 0.toByte())
        val byteRequiredErrKey = Argsx.readByteArgOr(activity, R.string.not_exist_key, 0.toByte())
        val byteArrayRequired = Argsx.readByteArrayArg(activity, R.string.byte_array_required)
        val byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, R.string.byte_array_optional)
        val byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, R.string.not_exist_key)
        val byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, R.string.byte_array_or_default, byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, R.string.not_exist_key, byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = Argsx.readShortArgOr(activity, R.string.short_required, 0.toShort())
        val shortRequiredErrKey = Argsx.readShortArgOr(activity, R.string.not_exist_key, 0.toShort())
        val shortArrayRequired = Argsx.readShortArrayArg(activity, R.string.short_array_required)
        val shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, R.string.short_array_optional)
        val shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, R.string.not_exist_key)
        val shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, R.string.short_array_or_default, shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, R.string.not_exist_key, shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = Argsx.readIntArgOr(activity, R.string.int_required, 0)
        val intRequiredErrKey = Argsx.readIntArgOr(activity, R.string.not_exist_key, 0)
        val intArrayRequired = Argsx.readIntArrayArg(activity, R.string.int_array_required)
        val intArrayOptional = Argsx.readIntArrayArgOrNull(activity, R.string.int_array_optional)
        val intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, R.string.not_exist_key)
        val intArrayOrDefault = Argsx.readIntArrayArgOr(activity, R.string.int_array_or_default, intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, R.string.not_exist_key, intArrayOf(0, -1))
        val intArrayListRequired = Argsx.readIntArrayListArg(activity, R.string.int_array_list_required)
        val intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, R.string.int_array_list_or_default, arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, R.string.not_exist_key, arrayListOf(0, 0))
        val intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, R.string.int_array_list_optional)
        val intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, R.string.not_exist_key)

        val longRequired = Argsx.readLongArgOr(activity, R.string.long_required, 0)
        val longRequiredErrKey = Argsx.readLongArgOr(activity, R.string.not_exist_key, 0)
        val longArrayRequired = Argsx.readLongArrayArg(activity, R.string.long_array_required)
        val longArrayOptional = Argsx.readLongArrayArgOrNull(activity, R.string.long_array_optional)
        val longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, R.string.not_exist_key)
        val longArrayOrDefault = Argsx.readLongArrayArgOr(activity, R.string.long_array_or_default, longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, R.string.not_exist_key, longArrayOf(0, -1))

        val floatRequired = Argsx.readFloatArgOr(activity, R.string.float_required, 0f)
        val floatRequiredErrKey = Argsx.readFloatArgOr(activity, R.string.not_exist_key, 0f)
        val floatArrayRequired = Argsx.readFloatArrayArg(activity, R.string.float_array_required)
        val floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, R.string.float_array_optional)
        val floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, R.string.not_exist_key)
        val floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, R.string.float_array_or_default, floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, R.string.not_exist_key, floatArrayOf(0f, -1f))

        val doubleRequired = Argsx.readDoubleArgOr(activity, R.string.double_required, 0.0)
        val doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, R.string.not_exist_key, 0.0)
        val doubleArrayRequired = Argsx.readDoubleArrayArg(activity, R.string.double_array_required)
        val doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, R.string.double_array_optional)
        val doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, R.string.not_exist_key)
        val doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, R.string.double_array_or_default, doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, R.string.not_exist_key, doubleArrayOf(0.0, -1.0))

        val booleanRequired = Argsx.readBooleanArgOr(activity, R.string.boolean_required, false)
        val booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, R.string.not_exist_key, false)
        val booleanArrayRequired = Argsx.readBooleanArrayArg(activity, R.string.boolean_array_required)
        val booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, R.string.boolean_array_optional)
        val booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, R.string.not_exist_key)
        val booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, R.string.boolean_array_or_default, booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, R.string.not_exist_key, booleanArrayOf(false, true))

        val charRequired = Argsx.readCharArgOr(activity, R.string.char_required, 'a')
        val charRequiredErrKey = Argsx.readCharArgOr(activity, R.string.not_exist_key, 'b')
        val charArrayRequired = Argsx.readCharArrayArg(activity, R.string.char_array_required)
        val charArrayOptional = Argsx.readCharArrayArgOrNull(activity, R.string.char_array_optional)
        val charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, R.string.not_exist_key)
        val charArrayOrDefault = Argsx.readCharArrayArgOr(activity, R.string.char_array_or_default, charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, R.string.not_exist_key, charArrayOf('b', 'a'))

        val stringRequired = Argsx.readStringArgOr(activity, R.string.string_required, "stringRequired")
        val stringRequiredErrKey = Argsx.readStringArgOr(activity, R.string.not_exist_key, "stringRequiredErrKey")
        val stringArrayRequired = Argsx.readStringArrayArg(activity, R.string.string_array_required)
        val stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, R.string.string_array_optional)
        val stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, R.string.not_exist_key)
        val stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, R.string.string_array_or_default, arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"))
        val stringArrayListRequired = Argsx.readStringArrayListArg(activity, R.string.string_array_list_required)
        val stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, R.string.string_array_list_or_default, arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, R.string.string_array_list_optional)
        val stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, R.string.not_exist_key)

        val charSequenceRequired = Argsx.readCharSequenceArgOr(activity, R.string.char_sequence_required, "charSequenceRequired")
        val charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, R.string.not_exist_key, "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = Argsx.readCharSequenceArrayArg(activity, R.string.char_sequence_array_required)
        val charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.char_sequence_array_optional)
        val charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.not_exist_key)
        val charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, R.string.char_sequence_array_or_default, arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"))
        val charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArg(activity, R.string.char_sequence_array_list_required)
        val charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, R.string.char_sequence_array_list_or_default, arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, R.string.not_exist_key, arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.char_sequence_array_list_optional)
        val charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.not_exist_key)


        val parcelableRequired = Argsx.readParcelableArgOr(activity, R.string.parcelable_required, TestParcelable("required"))
        val parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, R.string.not_exist_key, TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = Argsx.readParcelableArrayArg<Parcelable>(activity, R.string.parcelable_array_required)
        val parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, R.string.parcelable_array_optional)
        val parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, R.string.not_exist_key)
        val parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, R.string.parcelable_array_or_default, arrayOf())
        val parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, R.string.not_exist_key, arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = Argsx.readParcelableArrayListArg<Parcelable>(activity, R.string.parcelable_array_list_required)
        val parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, R.string.parcelable_array_list_or_default, arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, R.string.not_exist_key, arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, R.string.parcelable_array_list_optional)
        val parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, R.string.not_exist_key)

        val serializableRequired = Argsx.readSerializableArg<TestSerializable>(activity, R.string.serializable_required)
        val serializableOptional = Argsx.readSerializableArgOrNull<TestSerializable>(activity, R.string.serializable_optional)
        val serializableOptionalErrKey = Argsx.readSerializableArgOrNull<TestSerializable>(activity, R.string.not_exist_key)
        val serializableOrDefault = Argsx.readSerializableArgOr(activity, R.string.serializable_or_default, TestSerializable("default"))
        val serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, R.string.not_exist_key, TestSerializable("errKey"))

        val bundleRequired = Argsx.readBundleArg(activity, R.string.bundle_required)
        val bundleOptional = Argsx.readBundleArgOrNull(activity, R.string.bundle_optional)
        val bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, R.string.not_exist_key)
        val bundleOrDefault = Argsx.readBundleArgOr(activity, R.string.bundle_or_default, Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, R.string.not_exist_key, defaultBundle)

        val extrasRequired = Argsx.readExtrasArg(activity)
        val extrasOptional = Argsx.readExtrasArgOrNull(activity)
        val extrasDefault = Argsx.readExtrasArgOr(activity, Bundle())

        //test start
        Assert.assertTrue(extrasRequired.getString("extrasRequired") == "extrasRequired")
        Assert.assertTrue(extrasOptional!!.getString("extrasOptional") == "extrasOptional")
        Assert.assertTrue(extrasDefault.getString("extrasOrDefault") == "extrasOrDefault")

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun resSupportFragmentArgsTest() {
        val hostActivity = mResActivityTestRule.activity
        val activity = hostActivity.supportFragmentManager.findFragmentById(R.id.testAt_frame)!!

        val byteRequired = Argsx.readByteArgOr(activity, R.string.byte_required, 0.toByte())
        val byteRequiredErrKey = Argsx.readByteArgOr(activity, R.string.not_exist_key, 0.toByte())
        val byteArrayRequired = Argsx.readByteArrayArg(activity, R.string.byte_array_required)
        val byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, R.string.byte_array_optional)
        val byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, R.string.not_exist_key)
        val byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, R.string.byte_array_or_default, byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, R.string.not_exist_key, byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = Argsx.readShortArgOr(activity, R.string.short_required, 0.toShort())
        val shortRequiredErrKey = Argsx.readShortArgOr(activity, R.string.not_exist_key, 0.toShort())
        val shortArrayRequired = Argsx.readShortArrayArg(activity, R.string.short_array_required)
        val shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, R.string.short_array_optional)
        val shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, R.string.not_exist_key)
        val shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, R.string.short_array_or_default, shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, R.string.not_exist_key, shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = Argsx.readIntArgOr(activity, R.string.int_required, 0)
        val intRequiredErrKey = Argsx.readIntArgOr(activity, R.string.not_exist_key, 0)
        val intArrayRequired = Argsx.readIntArrayArg(activity, R.string.int_array_required)
        val intArrayOptional = Argsx.readIntArrayArgOrNull(activity, R.string.int_array_optional)
        val intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, R.string.not_exist_key)
        val intArrayOrDefault = Argsx.readIntArrayArgOr(activity, R.string.int_array_or_default, intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, R.string.not_exist_key, intArrayOf(0, -1))
        val intArrayListRequired = Argsx.readIntArrayListArg(activity, R.string.int_array_list_required)
        val intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, R.string.int_array_list_or_default, arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, R.string.not_exist_key, arrayListOf(0, 0))
        val intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, R.string.int_array_list_optional)
        val intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, R.string.not_exist_key)

        val longRequired = Argsx.readLongArgOr(activity, R.string.long_required, 0)
        val longRequiredErrKey = Argsx.readLongArgOr(activity, R.string.not_exist_key, 0)
        val longArrayRequired = Argsx.readLongArrayArg(activity, R.string.long_array_required)
        val longArrayOptional = Argsx.readLongArrayArgOrNull(activity, R.string.long_array_optional)
        val longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, R.string.not_exist_key)
        val longArrayOrDefault = Argsx.readLongArrayArgOr(activity, R.string.long_array_or_default, longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, R.string.not_exist_key, longArrayOf(0, -1))

        val floatRequired = Argsx.readFloatArgOr(activity, R.string.float_required, 0f)
        val floatRequiredErrKey = Argsx.readFloatArgOr(activity, R.string.not_exist_key, 0f)
        val floatArrayRequired = Argsx.readFloatArrayArg(activity, R.string.float_array_required)
        val floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, R.string.float_array_optional)
        val floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, R.string.not_exist_key)
        val floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, R.string.float_array_or_default, floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, R.string.not_exist_key, floatArrayOf(0f, -1f))

        val doubleRequired = Argsx.readDoubleArgOr(activity, R.string.double_required, 0.0)
        val doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, R.string.not_exist_key, 0.0)
        val doubleArrayRequired = Argsx.readDoubleArrayArg(activity, R.string.double_array_required)
        val doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, R.string.double_array_optional)
        val doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, R.string.not_exist_key)
        val doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, R.string.double_array_or_default, doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, R.string.not_exist_key, doubleArrayOf(0.0, -1.0))

        val booleanRequired = Argsx.readBooleanArgOr(activity, R.string.boolean_required, false)
        val booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, R.string.not_exist_key, false)
        val booleanArrayRequired = Argsx.readBooleanArrayArg(activity, R.string.boolean_array_required)
        val booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, R.string.boolean_array_optional)
        val booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, R.string.not_exist_key)
        val booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, R.string.boolean_array_or_default, booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, R.string.not_exist_key, booleanArrayOf(false, true))

        val charRequired = Argsx.readCharArgOr(activity, R.string.char_required, 'a')
        val charRequiredErrKey = Argsx.readCharArgOr(activity, R.string.not_exist_key, 'b')
        val charArrayRequired = Argsx.readCharArrayArg(activity, R.string.char_array_required)
        val charArrayOptional = Argsx.readCharArrayArgOrNull(activity, R.string.char_array_optional)
        val charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, R.string.not_exist_key)
        val charArrayOrDefault = Argsx.readCharArrayArgOr(activity, R.string.char_array_or_default, charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, R.string.not_exist_key, charArrayOf('b', 'a'))

        val stringRequired = Argsx.readStringArgOr(activity, R.string.string_required, "stringRequired")
        val stringRequiredErrKey = Argsx.readStringArgOr(activity, R.string.not_exist_key, "stringRequiredErrKey")
        val stringArrayRequired = Argsx.readStringArrayArg(activity, R.string.string_array_required)
        val stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, R.string.string_array_optional)
        val stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, R.string.not_exist_key)
        val stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, R.string.string_array_or_default, arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"))
        val stringArrayListRequired = Argsx.readStringArrayListArg(activity, R.string.string_array_list_required)
        val stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, R.string.string_array_list_or_default, arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, R.string.string_array_list_optional)
        val stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, R.string.not_exist_key)

        val charSequenceRequired = Argsx.readCharSequenceArgOr(activity, R.string.char_sequence_required, "charSequenceRequired")
        val charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, R.string.not_exist_key, "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = Argsx.readCharSequenceArrayArg(activity, R.string.char_sequence_array_required)
        val charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.char_sequence_array_optional)
        val charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.not_exist_key)
        val charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, R.string.char_sequence_array_or_default, arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"))
        val charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArg(activity, R.string.char_sequence_array_list_required)
        val charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, R.string.char_sequence_array_list_or_default, arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, R.string.not_exist_key, arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.char_sequence_array_list_optional)
        val charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.not_exist_key)


        val parcelableRequired = Argsx.readParcelableArgOr(activity, R.string.parcelable_required, TestParcelable("required"))
        val parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, R.string.not_exist_key, TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = Argsx.readParcelableArrayArg<Parcelable>(activity, R.string.parcelable_array_required)
        val parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, R.string.parcelable_array_optional)
        val parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, R.string.not_exist_key)
        val parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, R.string.parcelable_array_or_default, arrayOf())
        val parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, R.string.not_exist_key, arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = Argsx.readParcelableArrayListArg<Parcelable>(activity, R.string.parcelable_array_list_required)
        val parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, R.string.parcelable_array_list_or_default, arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, R.string.not_exist_key, arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, R.string.parcelable_array_list_optional)
        val parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, R.string.not_exist_key)

        val serializableRequired = Argsx.readSerializableArg<TestSerializable>(activity, R.string.serializable_required)
        val serializableOptional = Argsx.readSerializableArgOrNull<TestSerializable>(activity, R.string.serializable_optional)
        val serializableOptionalErrKey = Argsx.readSerializableArgOrNull<TestSerializable>(activity, R.string.not_exist_key)
        val serializableOrDefault = Argsx.readSerializableArgOr(activity, R.string.serializable_or_default, TestSerializable("default"))
        val serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, R.string.not_exist_key, TestSerializable("errKey"))

        val bundleRequired = Argsx.readBundleArg(activity, R.string.bundle_required)
        val bundleOptional = Argsx.readBundleArgOrNull(activity, R.string.bundle_optional)
        val bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, R.string.not_exist_key)
        val bundleOrDefault = Argsx.readBundleArgOr(activity, R.string.bundle_or_default, Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, R.string.not_exist_key, defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArg<Parcelable>(activity, R.string.sparse_parcelable_array_required)
        val sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, R.string.sparse_parcelable_array_optional)
        val sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, R.string.not_exist_key)
        val sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(activity, R.string.sparse_parcelable_array_or_default, sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(activity, R.string.not_exist_key, sparseArrayDefault)

        val binderRequired = Argsx.readBinderArg(activity, R.string.binder_required)
        val binderOptional = Argsx.readBinderArgOrNull(activity, R.string.binder_optional)
        val binderOptionalErrKey = Argsx.readBinderArgOrNull(activity, R.string.not_exist_key)
        val binderOrDefault = Argsx.readBinderArgOr(activity, R.string.binder_or_default, TestBinder(""))
        val binderOrDefaultErrKey = Argsx.readBinderArgOr(activity, R.string.not_exist_key, TestBinder("binderOrDefaultErrKey"))

        val sizeRequired = Argsx.readSizeArg(activity, R.string.size_required)
        val sizeOptional = Argsx.readSizeArgOrNull(activity, R.string.size_optional)
        val sizeOptionalErrKey = Argsx.readSizeArgOrNull(activity, R.string.not_exist_key)
        val sizeOrDefault = Argsx.readSizeArgOr(activity, R.string.size_or_default, Size(0, 0))
        val sizeOrDefaultErrKey = Argsx.readSizeArgOr(activity, R.string.not_exist_key, Size(4, 4))

        val sizeFRequired = Argsx.readSizeFArg(activity, R.string.sizeF_required)
        val sizeFOptional = Argsx.readSizeFArgOrNull(activity, R.string.sizeF_optional)
        val sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(activity, R.string.not_exist_key)
        val sizeFOrDefault = Argsx.readSizeFArgOr(activity, R.string.sizeF_or_default, SizeF(0f, 0f))
        val sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(activity, R.string.not_exist_key, SizeF(4f, 4f))

        //test start

        Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
        Assert.assertTrue(sizeFOptional!!.width == 2f && sizeFOptional.height == 2f)
        Assert.assertNull(sizeFOptionalErrKey)
        Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
        Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

        Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
        Assert.assertTrue(sizeOptional!!.width == 2 && sizeOptional.height == 2)
        Assert.assertNull(sizeOptionalErrKey)
        Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
        Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)


        Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
        Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
        Assert.assertNull(binderOptionalErrKey)
        Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
        Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))

        Assert.assertTrue(sparseParcelableArrayRequired.get(-1) == TestParcelable("-1") && sparseParcelableArrayRequired.get(1) == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional!!.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional.get(2) == TestParcelable("2"))
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3) == TestParcelable("-3") && sparseParcelableArrayOrDefault.get(3) == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0) == TestParcelable("0"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun resFragmentArgsTest() {
        val hostActivity = mResActivityTestRule.activity
        val activity = hostActivity.fragmentManager.findFragmentById(android.R.id.content)!!

        val byteRequired = Argsx.readByteArgOr(activity, R.string.byte_required, 0.toByte())
        val byteRequiredErrKey = Argsx.readByteArgOr(activity, R.string.not_exist_key, 0.toByte())
        val byteArrayRequired = Argsx.readByteArrayArg(activity, R.string.byte_array_required)
        val byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, R.string.byte_array_optional)
        val byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, R.string.not_exist_key)
        val byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, R.string.byte_array_or_default, byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, R.string.not_exist_key, byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = Argsx.readShortArgOr(activity, R.string.short_required, 0.toShort())
        val shortRequiredErrKey = Argsx.readShortArgOr(activity, R.string.not_exist_key, 0.toShort())
        val shortArrayRequired = Argsx.readShortArrayArg(activity, R.string.short_array_required)
        val shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, R.string.short_array_optional)
        val shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, R.string.not_exist_key)
        val shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, R.string.short_array_or_default, shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, R.string.not_exist_key, shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = Argsx.readIntArgOr(activity, R.string.int_required, 0)
        val intRequiredErrKey = Argsx.readIntArgOr(activity, R.string.not_exist_key, 0)
        val intArrayRequired = Argsx.readIntArrayArg(activity, R.string.int_array_required)
        val intArrayOptional = Argsx.readIntArrayArgOrNull(activity, R.string.int_array_optional)
        val intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, R.string.not_exist_key)
        val intArrayOrDefault = Argsx.readIntArrayArgOr(activity, R.string.int_array_or_default, intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, R.string.not_exist_key, intArrayOf(0, -1))
        val intArrayListRequired = Argsx.readIntArrayListArg(activity, R.string.int_array_list_required)
        val intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, R.string.int_array_list_or_default, arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, R.string.not_exist_key, arrayListOf(0, 0))
        val intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, R.string.int_array_list_optional)
        val intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, R.string.not_exist_key)

        val longRequired = Argsx.readLongArgOr(activity, R.string.long_required, 0)
        val longRequiredErrKey = Argsx.readLongArgOr(activity, R.string.not_exist_key, 0)
        val longArrayRequired = Argsx.readLongArrayArg(activity, R.string.long_array_required)
        val longArrayOptional = Argsx.readLongArrayArgOrNull(activity, R.string.long_array_optional)
        val longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, R.string.not_exist_key)
        val longArrayOrDefault = Argsx.readLongArrayArgOr(activity, R.string.long_array_or_default, longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, R.string.not_exist_key, longArrayOf(0, -1))

        val floatRequired = Argsx.readFloatArgOr(activity, R.string.float_required, 0f)
        val floatRequiredErrKey = Argsx.readFloatArgOr(activity, R.string.not_exist_key, 0f)
        val floatArrayRequired = Argsx.readFloatArrayArg(activity, R.string.float_array_required)
        val floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, R.string.float_array_optional)
        val floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, R.string.not_exist_key)
        val floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, R.string.float_array_or_default, floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, R.string.not_exist_key, floatArrayOf(0f, -1f))

        val doubleRequired = Argsx.readDoubleArgOr(activity, R.string.double_required, 0.0)
        val doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, R.string.not_exist_key, 0.0)
        val doubleArrayRequired = Argsx.readDoubleArrayArg(activity, R.string.double_array_required)
        val doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, R.string.double_array_optional)
        val doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, R.string.not_exist_key)
        val doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, R.string.double_array_or_default, doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, R.string.not_exist_key, doubleArrayOf(0.0, -1.0))

        val booleanRequired = Argsx.readBooleanArgOr(activity, R.string.boolean_required, false)
        val booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, R.string.not_exist_key, false)
        val booleanArrayRequired = Argsx.readBooleanArrayArg(activity, R.string.boolean_array_required)
        val booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, R.string.boolean_array_optional)
        val booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, R.string.not_exist_key)
        val booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, R.string.boolean_array_or_default, booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, R.string.not_exist_key, booleanArrayOf(false, true))

        val charRequired = Argsx.readCharArgOr(activity, R.string.char_required, 'a')
        val charRequiredErrKey = Argsx.readCharArgOr(activity, R.string.not_exist_key, 'b')
        val charArrayRequired = Argsx.readCharArrayArg(activity, R.string.char_array_required)
        val charArrayOptional = Argsx.readCharArrayArgOrNull(activity, R.string.char_array_optional)
        val charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, R.string.not_exist_key)
        val charArrayOrDefault = Argsx.readCharArrayArgOr(activity, R.string.char_array_or_default, charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, R.string.not_exist_key, charArrayOf('b', 'a'))

        val stringRequired = Argsx.readStringArgOr(activity, R.string.string_required, "stringRequired")
        val stringRequiredErrKey = Argsx.readStringArgOr(activity, R.string.not_exist_key, "stringRequiredErrKey")
        val stringArrayRequired = Argsx.readStringArrayArg(activity, R.string.string_array_required)
        val stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, R.string.string_array_optional)
        val stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, R.string.not_exist_key)
        val stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, R.string.string_array_or_default, arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"))
        val stringArrayListRequired = Argsx.readStringArrayListArg(activity, R.string.string_array_list_required)
        val stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, R.string.string_array_list_or_default, arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, R.string.string_array_list_optional)
        val stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, R.string.not_exist_key)

        val charSequenceRequired = Argsx.readCharSequenceArgOr(activity, R.string.char_sequence_required, "charSequenceRequired")
        val charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, R.string.not_exist_key, "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = Argsx.readCharSequenceArrayArg(activity, R.string.char_sequence_array_required)
        val charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.char_sequence_array_optional)
        val charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.not_exist_key)
        val charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, R.string.char_sequence_array_or_default, arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"))
        val charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArg(activity, R.string.char_sequence_array_list_required)
        val charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, R.string.char_sequence_array_list_or_default, arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, R.string.not_exist_key, arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.char_sequence_array_list_optional)
        val charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.not_exist_key)


        val parcelableRequired = Argsx.readParcelableArgOr(activity, R.string.parcelable_required, TestParcelable("required"))
        val parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, R.string.not_exist_key, TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = Argsx.readParcelableArrayArg<Parcelable>(activity, R.string.parcelable_array_required)
        val parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, R.string.parcelable_array_optional)
        val parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull<Parcelable>(activity, R.string.not_exist_key)
        val parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, R.string.parcelable_array_or_default, arrayOf())
        val parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, R.string.not_exist_key, arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = Argsx.readParcelableArrayListArg<Parcelable>(activity, R.string.parcelable_array_list_required)
        val parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, R.string.parcelable_array_list_or_default, arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, R.string.not_exist_key, arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, R.string.parcelable_array_list_optional)
        val parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull<Parcelable>(activity, R.string.not_exist_key)

        val serializableRequired = Argsx.readSerializableArg<TestSerializable>(activity, R.string.serializable_required)
        val serializableOptional = Argsx.readSerializableArgOrNull<TestSerializable>(activity, R.string.serializable_optional)
        val serializableOptionalErrKey = Argsx.readSerializableArgOrNull<TestSerializable>(activity, R.string.not_exist_key)
        val serializableOrDefault = Argsx.readSerializableArgOr(activity, R.string.serializable_or_default, TestSerializable("default"))
        val serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, R.string.not_exist_key, TestSerializable("errKey"))

        val bundleRequired = Argsx.readBundleArg(activity, R.string.bundle_required)
        val bundleOptional = Argsx.readBundleArgOrNull(activity, R.string.bundle_optional)
        val bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, R.string.not_exist_key)
        val bundleOrDefault = Argsx.readBundleArgOr(activity, R.string.bundle_or_default, Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, R.string.not_exist_key, defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArg<Parcelable>(activity, R.string.sparse_parcelable_array_required)
        val sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, R.string.sparse_parcelable_array_optional)
        val sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull<Parcelable>(activity, R.string.not_exist_key)
        val sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(activity, R.string.sparse_parcelable_array_or_default, sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(activity, R.string.not_exist_key, sparseArrayDefault)

        val binderRequired = Argsx.readBinderArg(activity, R.string.binder_required)
        val binderOptional = Argsx.readBinderArgOrNull(activity, R.string.binder_optional)
        val binderOptionalErrKey = Argsx.readBinderArgOrNull(activity, R.string.not_exist_key)
        val binderOrDefault = Argsx.readBinderArgOr(activity, R.string.binder_or_default, TestBinder(""))
        val binderOrDefaultErrKey = Argsx.readBinderArgOr(activity, R.string.not_exist_key, TestBinder("binderOrDefaultErrKey"))

        val sizeRequired = Argsx.readSizeArg(activity, R.string.size_required)
        val sizeOptional = Argsx.readSizeArgOrNull(activity, R.string.size_optional)
        val sizeOptionalErrKey = Argsx.readSizeArgOrNull(activity, R.string.not_exist_key)
        val sizeOrDefault = Argsx.readSizeArgOr(activity, R.string.size_or_default, Size(0, 0))
        val sizeOrDefaultErrKey = Argsx.readSizeArgOr(activity, R.string.not_exist_key, Size(4, 4))

        val sizeFRequired = Argsx.readSizeFArg(activity, R.string.sizeF_required)
        val sizeFOptional = Argsx.readSizeFArgOrNull(activity, R.string.sizeF_optional)
        val sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(activity, R.string.not_exist_key)
        val sizeFOrDefault = Argsx.readSizeFArgOr(activity, R.string.sizeF_or_default, SizeF(0f, 0f))
        val sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(activity, R.string.not_exist_key, SizeF(4f, 4f))

        //test start

        Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
        Assert.assertTrue(sizeFOptional!!.width == 2f && sizeFOptional.height == 2f)
        Assert.assertNull(sizeFOptionalErrKey)
        Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
        Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

        Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
        Assert.assertTrue(sizeOptional!!.width == 2 && sizeOptional.height == 2)
        Assert.assertNull(sizeOptionalErrKey)
        Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
        Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)


        Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
        Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
        Assert.assertNull(binderOptionalErrKey)
        Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
        Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))

        Assert.assertTrue(sparseParcelableArrayRequired.get(-1) == TestParcelable("-1") && sparseParcelableArrayRequired.get(1) == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional!!.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional.get(2) == TestParcelable("2"))
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3) == TestParcelable("-3") && sparseParcelableArrayOrDefault.get(3) == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0) == TestParcelable("0"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    //uri || intent
    @Test
    fun uriOnlyActivityTest() {
        val activity = uriNoIntentActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, "byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, "shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, "intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, "longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, "floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, "doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, "booleanIntentUriOrDefault", false)
        val stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, "stringIntentUriRequired")
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, "byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, "shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, "intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, "longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, "floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, "doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, "booleanUriIntentOrDefault", false)
        val stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, "stringUriIntentRequired")
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == 1.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 2.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 3)
        Assert.assertTrue(longIntentUriOrDefault == 4L)
        Assert.assertTrue(floatIntentUriOrDefault == 5f)
        Assert.assertTrue(doubleIntentUriOrDefault == 6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentUriRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentUriOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentUriOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun intentOnlyActivityTest() {
        val activity = intentNoUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, "byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, "shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, "intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, "longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, "floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, "doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, "booleanIntentUriOrDefault", false)
        val stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, "stringIntentUriRequired")
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, "byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, "shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, "intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, "longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, "floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, "doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, "booleanUriIntentOrDefault", false)
        val stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, "stringUriIntentRequired")
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == (-11).toByte())
        Assert.assertTrue(shortUriIntentOrDefault == (-12).toShort())
        Assert.assertTrue(intUriIntentOrDefault == -13)
        Assert.assertTrue(longUriIntentOrDefault == -14L)
        Assert.assertTrue(floatUriIntentOrDefault == -15f)
        Assert.assertTrue(doubleUriIntentOrDefault == -16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun bothUriIntentActivityTest() {
        val activity = bothIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, "byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, "shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, "intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, "longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, "floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, "doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, "booleanIntentUriOrDefault", false)
        val stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, "stringIntentUriRequired")
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, "byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, "shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, "intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, "longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, "floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, "doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, "booleanUriIntentOrDefault", false)
        val stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, "stringUriIntentRequired")
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun noUriIntentActivityTest() {
        val activity = noIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, "byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, "shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, "intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, "longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, "floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, "doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, "booleanIntentUriOrDefault", false)
        //        String stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, "stringIntentUriRequired");
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, "byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, "shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, "intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, "longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, "floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, "doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, "booleanUriIntentOrDefault", false)
        //        String stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, "stringUriIntentRequired");
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 0)
        Assert.assertTrue(longIntentUriOrDefault == 0L)
        Assert.assertTrue(floatIntentUriOrDefault == 0f)
        Assert.assertTrue(doubleIntentUriOrDefault == 0.0)
        Assert.assertTrue(!booleanIntentUriOrDefault)
        Assert.assertNull(stringIntentUriOptional)
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "default")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 0.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 0.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 0)
        Assert.assertTrue(longUriIntentOrDefault == 0L)
        Assert.assertTrue(floatUriIntentOrDefault == 0f)
        Assert.assertTrue(doubleUriIntentOrDefault == 0.0)
        Assert.assertTrue(!booleanUriIntentOrDefault)
        Assert.assertNull(stringUriIntentOptional)
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "default")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    //res
    @Test
    fun resUriOnlyActivityTest() {
        val activity = resUriNoIntentActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false)
        val stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, R.string.string_intent_uri_required)
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false)
        val stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, R.string.string_uri_intent_required)
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == 1.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 2.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 3)
        Assert.assertTrue(longIntentUriOrDefault == 4L)
        Assert.assertTrue(floatIntentUriOrDefault == 5f)
        Assert.assertTrue(doubleIntentUriOrDefault == 6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentUriRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentUriOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentUriOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun resIntentOnlyActivityTest() {
        val activity = intentNoUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false)
        val stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, R.string.string_intent_uri_required)
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false)
        val stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, R.string.string_uri_intent_required)
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == (-11).toByte())
        Assert.assertTrue(shortUriIntentOrDefault == (-12).toShort())
        Assert.assertTrue(intUriIntentOrDefault == -13)
        Assert.assertTrue(longUriIntentOrDefault == -14L)
        Assert.assertTrue(floatUriIntentOrDefault == -15f)
        Assert.assertTrue(doubleUriIntentOrDefault == -16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun resBothUriIntentActivityTest() {
        val activity = resBothIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false)
        val stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, R.string.string_intent_uri_required)
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false)
        val stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, R.string.string_uri_intent_required)
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun resNoUriIntentActivityTest() {
        val activity = noIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false)
        //        String stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, R.string.string_intent_uri_required);
        val stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false)
        //        String stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, R.string.string_uri_intent_required);
        val stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 0)
        Assert.assertTrue(longIntentUriOrDefault == 0L)
        Assert.assertTrue(floatIntentUriOrDefault == 0f)
        Assert.assertTrue(doubleIntentUriOrDefault == 0.0)
        Assert.assertTrue(!booleanIntentUriOrDefault)
        Assert.assertNull(stringIntentUriOptional)
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "default")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 0.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 0.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 0)
        Assert.assertTrue(longUriIntentOrDefault == 0L)
        Assert.assertTrue(floatUriIntentOrDefault == 0f)
        Assert.assertTrue(doubleUriIntentOrDefault == 0.0)
        Assert.assertTrue(!booleanUriIntentOrDefault)
        Assert.assertNull(stringUriIntentOptional)
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "default")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    //activity uri
    @Test
    fun uriActivityTest() {
        val activity = uriActivityTestRule.activity

        val byteUriRequired = Argsx.readByteUriArg(activity, "byteUriRequired")
        val byteUriOptional = Argsx.readByteUriArgOrNull(activity, "byteUriOptional")
        val byteUriOptionalErrKey = Argsx.readByteUriArgOrNull(activity, "byteUriOptionalErrKey")
        val byteUriOrDefault = Argsx.readByteUriArgOr(activity, "byteUriOrDefault", 0.toByte())
        val byteUriOrDefaultErrKey = Argsx.readByteUriArgOr(activity, "byteUriOrDefaultErrKey", 0.toByte())

        val shortUriRequired = Argsx.readShortUriArg(activity, "shortUriRequired")
        val shortUriOptional = Argsx.readShortUriArgOrNull(activity, "shortUriOptional")
        val shortUriOptionalErrKey = Argsx.readShortUriArgOrNull(activity, "shortUriOptionalErrKey")
        val shortUriOrDefault = Argsx.readShortUriArgOr(activity, "shortUriOrDefault", 0.toShort())
        val shortUriOrDefaultErrKey = Argsx.readShortUriArgOr(activity, "shortUriOrDefaultErrKey", 0.toShort())

        val intUriRequired = Argsx.readIntUriArg(activity, "intUriRequired")
        val intUriOptional = Argsx.readIntUriArgOrNull(activity, "intUriOptional")
        val intUriOptionalErrKey = Argsx.readIntUriArgOrNull(activity, "intUriOptionalErrKey")
        val intUriOrDefault = Argsx.readIntUriArgOr(activity, "intUriOrDefault", 0)
        val intUriOrDefaultErrKey = Argsx.readIntUriArgOr(activity, "intUriOrDefaultErrKey", 0)

        val longUriRequired = Argsx.readLongUriArg(activity, "longUriRequired")
        val longUriOptional = Argsx.readLongUriArgOrNull(activity, "longUriOptional")
        val longUriOptionalErrKey = Argsx.readLongUriArgOrNull(activity, "longUriOptionalErrKey")
        val longUriOrDefault = Argsx.readLongUriArgOr(activity, "longUriOrDefault", 0L)
        val longUriOrDefaultErrKey = Argsx.readLongUriArgOr(activity, "longUriOrDefaultErrKey", 0L)

        val floatUriRequired = Argsx.readFloatUriArg(activity, "floatUriRequired")
        val floatUriOptional = Argsx.readFloatUriArgOrNull(activity, "floatUriOptional")
        val floatUriOptionalErrKey = Argsx.readFloatUriArgOrNull(activity, "floatUriOptionalErrKey")
        val floatUriOrDefault = Argsx.readFloatUriArgOr(activity, "floatUriOrDefault", 0f)
        val floatUriOrDefaultErrKey = Argsx.readFloatUriArgOr(activity, "floatUriOrDefaultErrKey", -1f)

        val doubleUriRequired = Argsx.readDoubleUriArg(activity, "doubleUriRequired")
        val doubleUriOptional = Argsx.readDoubleUriArgOrNull(activity, "doubleUriOptional")
        val doubleUriOptionalErrKey = Argsx.readDoubleUriArgOrNull(activity, "doubleUriOptionalErrKey")
        val doubleUriOrDefault = Argsx.readDoubleUriArgOr(activity, "doubleUriOrDefault", 1.0)
        val doubleUriOrDefaultErrKey = Argsx.readDoubleUriArgOr(activity, "doubleUriOrDefaultErrKey", -1.0)

        val booleanUriRequired = Argsx.readBooleanUriArg(activity, "booleanUriRequired")
        val booleanUriOptional = Argsx.readBooleanUriArgOrNull(activity, "booleanUriOptional")
        val booleanUriOptionalErrKey = Argsx.readBooleanUriArgOrNull(activity, "booleanUriOptionalErrKey")
        val booleanUriOrDefault = Argsx.readBooleanUriArgOr(activity, "booleanUriOrDefault", true)
        val booleanUriOrDefaultErrKey = Argsx.readBooleanUriArgOr(activity, "booleanUriOrDefaultErrKey", false)

        val stringUriRequired = Argsx.readStringUriArg(activity, "stringUriRequired")
        val stringUriOptional = Argsx.readStringUriArgOrNull(activity, "stringUriOptional")
        val stringUriOptionalErrKey = Argsx.readStringUriArgOrNull(activity, "stringUriOptionalErrKey")
        val stringUriOrDefault = Argsx.readStringUriArgOr(activity, "stringUriOrDefault", "")
        val stringUriOrDefaultErrKey = Argsx.readStringUriArgOr(activity, "stringUriOrDefaultErrKey", "stringUriOrDefaultErrKey")

        Assert.assertNull(byteUriOptionalErrKey)
        Assert.assertNull(shortUriOptionalErrKey)
        Assert.assertNull(intUriOptionalErrKey)
        Assert.assertNull(longUriOptionalErrKey)
        Assert.assertNull(floatUriOptionalErrKey)
        Assert.assertNull(doubleUriOptionalErrKey)
        Assert.assertNull(booleanUriOptionalErrKey)
        Assert.assertNull(stringUriOptionalErrKey)

        Assert.assertTrue(byteUriRequired == 1.toByte())
        Assert.assertTrue(byteUriOptional == (-1).toByte())
        Assert.assertTrue(byteUriOrDefault == 2.toByte())
        Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

        Assert.assertTrue(shortUriRequired == 3.toShort())
        Assert.assertTrue(shortUriOptional == (-3).toShort())
        Assert.assertTrue(shortUriOrDefault == 4.toShort())
        Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

        Assert.assertTrue(intUriRequired == 5)
        Assert.assertTrue(intUriOptional == -5)
        Assert.assertTrue(intUriOrDefault == 6)
        Assert.assertTrue(intUriOrDefaultErrKey == 0)

        Assert.assertTrue(longUriRequired == 7L)
        Assert.assertTrue(longUriOptional == -7L)
        Assert.assertTrue(longUriOrDefault == 8L)
        Assert.assertTrue(longUriOrDefaultErrKey == 0L)

        Assert.assertTrue(floatUriRequired == 9f)
        Assert.assertTrue(floatUriOptional == -9f)
        Assert.assertTrue(floatUriOrDefault == 10f)
        Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

        Assert.assertTrue(doubleUriRequired == 11.0)
        Assert.assertTrue(doubleUriOptional == -11.0)
        Assert.assertTrue(doubleUriOrDefault == 12.0)
        Assert.assertTrue(doubleUriOrDefaultErrKey == -1.0)

        Assert.assertTrue(booleanUriRequired)
        Assert.assertTrue(booleanUriOptional!!)
        Assert.assertTrue(!booleanUriOrDefault)
        Assert.assertTrue(!booleanUriOrDefaultErrKey)

        Assert.assertTrue(stringUriRequired == "stringUriRequired")
        Assert.assertTrue(stringUriOptional == "stringUriOptional")
        Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
        Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")

    }

    @Test
    fun resUriActivityTest() {
        val activity = resUriActivityTestRule.activity

        val byteUriRequired = Argsx.readByteUriArg(activity, R.string.byte_uri_required)
        val byteUriOptional = Argsx.readByteUriArgOrNull(activity, R.string.byte_uri_optional)
        val byteUriOptionalErrKey = Argsx.readByteUriArgOrNull(activity, R.string.not_exist_key)
        val byteUriOrDefault = Argsx.readByteUriArgOr(activity, R.string.byte_uri_or_default, 0.toByte())
        val byteUriOrDefaultErrKey = Argsx.readByteUriArgOr(activity, R.string.not_exist_key, 0.toByte())

        val shortUriRequired = Argsx.readShortUriArg(activity, R.string.short_uri_required)
        val shortUriOptional = Argsx.readShortUriArgOrNull(activity, R.string.short_uri_optional)
        val shortUriOptionalErrKey = Argsx.readShortUriArgOrNull(activity, R.string.not_exist_key)
        val shortUriOrDefault = Argsx.readShortUriArgOr(activity, R.string.short_uri_or_default, 0.toShort())
        val shortUriOrDefaultErrKey = Argsx.readShortUriArgOr(activity, R.string.not_exist_key, 0.toShort())

        val intUriRequired = Argsx.readIntUriArg(activity, R.string.int_uri_required)
        val intUriOptional = Argsx.readIntUriArgOrNull(activity, R.string.int_uri_optional)
        val intUriOptionalErrKey = Argsx.readIntUriArgOrNull(activity, R.string.not_exist_key)
        val intUriOrDefault = Argsx.readIntUriArgOr(activity, R.string.int_uri_or_default, 0)
        val intUriOrDefaultErrKey = Argsx.readIntUriArgOr(activity, R.string.not_exist_key, 0)

        val longUriRequired = Argsx.readLongUriArg(activity, R.string.long_uri_required)
        val longUriOptional = Argsx.readLongUriArgOrNull(activity, R.string.long_uri_optional)
        val longUriOptionalErrKey = Argsx.readLongUriArgOrNull(activity, R.string.not_exist_key)
        val longUriOrDefault = Argsx.readLongUriArgOr(activity, R.string.long_uri_or_default, 0L)
        val longUriOrDefaultErrKey = Argsx.readLongUriArgOr(activity, R.string.not_exist_key, 0L)

        val floatUriRequired = Argsx.readFloatUriArg(activity, R.string.float_uri_required)
        val floatUriOptional = Argsx.readFloatUriArgOrNull(activity, R.string.float_uri_optional)
        val floatUriOptionalErrKey = Argsx.readFloatUriArgOrNull(activity, R.string.not_exist_key)
        val floatUriOrDefault = Argsx.readFloatUriArgOr(activity, R.string.float_uri_or_default, 0f)
        val floatUriOrDefaultErrKey = Argsx.readFloatUriArgOr(activity, R.string.not_exist_key, -1f)

        val doubleUriRequired = Argsx.readDoubleUriArg(activity, R.string.double_uri_required)
        val doubleUriOptional = Argsx.readDoubleUriArgOrNull(activity, R.string.double_uri_optional)
        val doubleUriOptionalErrKey = Argsx.readDoubleUriArgOrNull(activity, R.string.not_exist_key)
        val doubleUriOrDefault = Argsx.readDoubleUriArgOr(activity, R.string.double_uri_or_default, 1.0)
        val doubleUriOrDefaultErrKey = Argsx.readDoubleUriArgOr(activity, R.string.not_exist_key, -1.0)

        val booleanUriRequired = Argsx.readBooleanUriArg(activity, R.string.boolean_uri_required)
        val booleanUriOptional = Argsx.readBooleanUriArgOrNull(activity, R.string.boolean_uri_optional)
        val booleanUriOptionalErrKey = Argsx.readBooleanUriArgOrNull(activity, R.string.not_exist_key)
        val booleanUriOrDefault = Argsx.readBooleanUriArgOr(activity, R.string.boolean_uri_or_default, true)
        val booleanUriOrDefaultErrKey = Argsx.readBooleanUriArgOr(activity, R.string.not_exist_key, false)

        val stringUriRequired = Argsx.readStringUriArg(activity, R.string.string_uri_required)
        val stringUriOptional = Argsx.readStringUriArgOrNull(activity, R.string.string_uri_optional)
        val stringUriOptionalErrKey = Argsx.readStringUriArgOrNull(activity, R.string.not_exist_key)
        val stringUriOrDefault = Argsx.readStringUriArgOr(activity, R.string.string_uri_or_default, "")
        val stringUriOrDefaultErrKey = Argsx.readStringUriArgOr(activity, R.string.not_exist_key, "stringUriOrDefaultErrKey")

        Assert.assertNull(byteUriOptionalErrKey)
        Assert.assertNull(shortUriOptionalErrKey)
        Assert.assertNull(intUriOptionalErrKey)
        Assert.assertNull(longUriOptionalErrKey)
        Assert.assertNull(floatUriOptionalErrKey)
        Assert.assertNull(doubleUriOptionalErrKey)
        Assert.assertNull(booleanUriOptionalErrKey)
        Assert.assertNull(stringUriOptionalErrKey)

        Assert.assertTrue(byteUriRequired == 1.toByte())
        Assert.assertTrue(byteUriOptional == (-1).toByte())
        Assert.assertTrue(byteUriOrDefault == 2.toByte())
        Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

        Assert.assertTrue(shortUriRequired == 3.toShort())
        Assert.assertTrue(shortUriOptional == (-3).toShort())
        Assert.assertTrue(shortUriOrDefault == 4.toShort())
        Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

        Assert.assertTrue(intUriRequired == 5)
        Assert.assertTrue(intUriOptional == -5)
        Assert.assertTrue(intUriOrDefault == 6)
        Assert.assertTrue(intUriOrDefaultErrKey == 0)

        Assert.assertTrue(longUriRequired == 7L)
        Assert.assertTrue(longUriOptional == -7L)
        Assert.assertTrue(longUriOrDefault == 8L)
        Assert.assertTrue(longUriOrDefaultErrKey == 0L)

        Assert.assertTrue(floatUriRequired == 9f)
        Assert.assertTrue(floatUriOptional == -9f)
        Assert.assertTrue(floatUriOrDefault == 10f)
        Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

        Assert.assertTrue(doubleUriRequired == 11.0)
        Assert.assertTrue(doubleUriOptional == -11.0)
        Assert.assertTrue(doubleUriOrDefault == 12.0)
        Assert.assertTrue(doubleUriOrDefaultErrKey == -1.0)

        Assert.assertTrue(booleanUriRequired)
        Assert.assertTrue(booleanUriOptional!!)
        Assert.assertTrue(!booleanUriOrDefault)
        Assert.assertTrue(!booleanUriOrDefaultErrKey)

        Assert.assertTrue(stringUriRequired == "stringUriRequired")
        Assert.assertTrue(stringUriOptional == "stringUriOptional")
        Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
        Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")

    }


    ///////////////////////////////////////////////////////////////////////////
    // class for test used
    ///////////////////////////////////////////////////////////////////////////

    class TestActivity : FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_test)
            val supportFragment = TestSupportFragment.newInstance(intent.extras)
            supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()

            val testFragment = TestFragment.newInstance(intent.extras)
            fragmentManager.beginTransaction().add(android.R.id.content, testFragment).commit()
        }

        companion object {

            fun createIntent(context: Context): Intent {
                val starter = Intent(context, TestActivity::class.java)
                starter.putExtra("byteRequired", 2.toByte())
                starter.putExtra("byteArrayRequired", byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra("byteArrayOptional", byteArrayOf((-2).toByte(), 2.toByte()))


                starter.putExtra("shortRequired", 3.toShort())
                starter.putExtra("shortArrayRequired", shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra("shortArrayOptional", shortArrayOf((-3).toShort(), 3.toShort()))

                starter.putExtra("intRequired", 500)
                starter.putExtra("intArrayRequired", intArrayOf(500, -500))
                starter.putExtra("intArrayOptional", intArrayOf(-500, 500))
                starter.putIntegerArrayListExtra("intArrayListRequired", arrayListOf(500, -500))
                starter.putIntegerArrayListExtra("intArrayListOptional", arrayListOf(-500, 500))

                starter.putExtra("longRequired", 1000L)
                starter.putExtra("longArrayRequired", longArrayOf(1000L, -1000L))
                starter.putExtra("longArrayOptional", longArrayOf(-1000L, 1000L))

                starter.putExtra("floatRequired", 4f)
                starter.putExtra("floatArrayRequired", floatArrayOf(4f, -4f))
                starter.putExtra("floatArrayOptional", floatArrayOf(-4f, 4f))


                starter.putExtra("doubleRequired", 6.0)
                starter.putExtra("doubleArrayRequired", doubleArrayOf(6.0, -6.0))
                starter.putExtra("doubleArrayOptional", doubleArrayOf(-6.0, 6.0))


                starter.putExtra("booleanRequired", true)
                starter.putExtra("booleanArrayRequired", booleanArrayOf(true, false))
                starter.putExtra("booleanArrayOptional", booleanArrayOf(false, true))


                starter.putExtra("charRequired", 'a')
                starter.putExtra("charArrayRequired", charArrayOf('a', 'b'))
                starter.putExtra("charArrayOptional", charArrayOf('b', 'a'))

                starter.putExtra("stringRequired", "stringRequired")
                starter.putExtra("stringOptional", "stringOptional")
                starter.putExtra("stringArrayRequired", arrayOf("stringRequired", "stringOptional"))
                starter.putExtra("stringArrayOptional", arrayOf("stringOptional", "stringRequired"))
                starter.putStringArrayListExtra("stringArrayListRequired", arrayListOf("stringRequired", "stringOptional"))
                starter.putStringArrayListExtra("stringArrayListOptional", arrayListOf("stringOptional", "stringRequired"))

                starter.putExtra("charSequenceRequired", "stringRequired")
                starter.putExtra("charSequenceOptional", "stringOptional")
                starter.putExtra("charSequenceArrayRequired", arrayOf("stringRequired", "stringOptional"))
                starter.putExtra("charSequenceArrayOptional", arrayOf("stringOptional", "stringRequired"))


                starter.putExtra("parcelableRequired", TestParcelable("parcelableRequired"))
                starter.putExtra("parcelableOptional", TestParcelable("parcelableOptional"))
                starter.putExtra("parcelableArrayRequired", arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putExtra("parcelableArrayOptional", arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                starter.putParcelableArrayListExtra("parcelableArrayListRequired", arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putParcelableArrayListExtra("parcelableArrayListOptional", arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))


                starter.putExtra("serializableRequired", TestSerializable("serializableRequired"))
                starter.putExtra("serializableOptional", TestSerializable("serializableOptional"))

                starter.putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra("shortArrayOrDefault", shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra("intArrayOrDefault", intArrayOf(500, -500))
                starter.putIntegerArrayListExtra("intArrayListOrDefault", arrayListOf(600, -600))
                starter.putExtra("longArrayOrDefault", longArrayOf(1000L, -1000L))
                starter.putExtra("floatArrayOrDefault", floatArrayOf(4f, -4f))
                starter.putExtra("doubleArrayOrDefault", doubleArrayOf(6.0, -6.0))
                starter.putExtra("booleanArrayOrDefault", booleanArrayOf(true, false))
                starter.putExtra("charArrayOrDefault", charArrayOf('a', 'b'))
                starter.putExtra("charSequenceOrDefault", "charSequenceOrDefault")
                starter.putExtra("charSequenceArrayOrDefault", arrayOf("charSequence", "default"))


                starter.putCharSequenceArrayListExtra("charSequenceArrayListRequired", arrayListOf("charSequenceArrayListRequired", "required"))
                starter.putCharSequenceArrayListExtra("charSequenceArrayListOptional", arrayListOf("charSequenceArrayListOptional", "optional"))
                starter.putCharSequenceArrayListExtra("charSequenceArrayListOrDefault", arrayListOf("charSequenceArrayListOrDefault", "default"))

                starter.putExtra("stringOrDefault", "stringOrDefault")
                starter.putExtra("stringArrayOrDefault", arrayOf("stringArrayOrDefault", "default"))
                starter.putExtra("stringArrayListOrDefault", arrayListOf("stringArrayListOrDefault", "default"))

                starter.putExtra("parcelableOrDefault", TestParcelable("parcelableOrDefault"))
                starter.putExtra("parcelableArrayOrDefault", arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                starter.putParcelableArrayListExtra("parcelableArrayListOrDefault", arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                starter.putExtra("serializableOrDefault", TestSerializable("serializableOrDefault"))

                val bundleDefault = Bundle()
                bundleDefault.putString("bundle", "bundleOrDefault")
                starter.putExtra("bundleOrDefault", bundleDefault)

                val bundleRequired = Bundle()
                bundleRequired.putString("bundle", "bundleRequired")
                starter.putExtra("bundleRequired", bundleRequired)

                val bundleOptional = Bundle()
                bundleOptional.putString("bundle", "bundleOptional")
                starter.putExtra("bundleOptional", bundleOptional)

                val b = Bundle()
                b.putString("extrasRequired", "extrasRequired")
                b.putString("extrasOptional", "extrasOptional")
                b.putString("extrasOrDefault", "extrasOrDefault")
                starter.putExtras(b)

                val args = Bundle()
                val sparseParcelableArrayRequired = SparseArray<Parcelable>()
                sparseParcelableArrayRequired.put(-1, TestParcelable("-1"))
                sparseParcelableArrayRequired.put(1, TestParcelable("1"))
                args.putSparseParcelableArray("sparseParcelableArrayRequired", sparseParcelableArrayRequired)

                val sparseParcelableArrayOptional = SparseArray<Parcelable>()
                sparseParcelableArrayOptional.put(-2, TestParcelable("-2"))
                sparseParcelableArrayOptional.put(2, TestParcelable("2"))
                args.putSparseParcelableArray("sparseParcelableArrayOptional", sparseParcelableArrayOptional)

                val sparseParcelableArrayOrDefault = SparseArray<Parcelable>()
                sparseParcelableArrayOrDefault.put(-3, TestParcelable("-3"))
                sparseParcelableArrayOrDefault.put(3, TestParcelable("3"))
                args.putSparseParcelableArray("sparseParcelableArrayOrDefault", sparseParcelableArrayOrDefault)

                args.putBinder("binderRequired", TestBinder("binderRequired"))
                args.putBinder("binderOptional", TestBinder("binderOptional"))
                args.putBinder("binderOrDefault", TestBinder("binderOrDefault"))

                args.putSize("sizeRequired", Size(1, 1))
                args.putSize("sizeOptional", Size(2, 2))
                args.putSize("sizeOrDefault", Size(3, 3))

                args.putSizeF("sizeFRequired", SizeF(1f, 1f))
                args.putSizeF("sizeFOptional", SizeF(2f, 2f))
                args.putSizeF("sizeFOrDefault", SizeF(3f, 3f))

                starter.putExtras(args)

                return starter
            }
        }
    }

    class TestSupportFragment : Fragment() {
        companion object {
            fun newInstance(args: Bundle?): TestSupportFragment {
                val fragment = TestSupportFragment()
                fragment.arguments = args
                return fragment
            }
        }
    }

    class TestFragment : android.app.Fragment() {
        companion object {
            fun newInstance(args: Bundle?): TestFragment {
                val fragment = TestFragment()
                fragment.arguments = args
                return fragment
            }
        }
    }

    //res
    class ResTestActivity : FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_test)
            val supportFragment = ResTestSupportFragment.newInstance(intent.extras)
            supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()

            val testFragment = ResTestFragment.newInstance(intent.extras)
            fragmentManager.beginTransaction().add(android.R.id.content, testFragment).commit()
        }

        companion object {

            fun createIntent(context: Context): Intent {
                val starter = Intent(context, ResTestActivity::class.java)
                starter.putExtra(context.getString(R.string.byte_required), 2.toByte())
                starter.putExtra(context.getString(R.string.byte_array_required), byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra(context.getString(R.string.byte_array_optional), byteArrayOf((-2).toByte(), 2.toByte()))


                starter.putExtra(context.getString(R.string.short_required), 3.toShort())
                starter.putExtra(context.getString(R.string.short_array_required), shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra(context.getString(R.string.short_array_optional), shortArrayOf((-3).toShort(), 3.toShort()))

                starter.putExtra(context.getString(R.string.int_required), 500)
                starter.putExtra(context.getString(R.string.int_array_required), intArrayOf(500, -500))
                starter.putExtra(context.getString(R.string.int_array_optional), intArrayOf(-500, 500))
                starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_required), arrayListOf(500, -500))
                starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500))

                starter.putExtra(context.getString(R.string.long_required), 1000L)
                starter.putExtra(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L))
                starter.putExtra(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L))

                starter.putExtra(context.getString(R.string.float_required), 4f)
                starter.putExtra(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f))
                starter.putExtra(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f))


                starter.putExtra(context.getString(R.string.double_required), 6.0)
                starter.putExtra(context.getString(R.string.double_array_required), doubleArrayOf(6.0, -6.0))
                starter.putExtra(context.getString(R.string.double_array_optional), doubleArrayOf(-6.0, 6.0))


                starter.putExtra(context.getString(R.string.boolean_required), true)
                starter.putExtra(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false))
                starter.putExtra(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true))


                starter.putExtra(context.getString(R.string.char_required), 'a')
                starter.putExtra(context.getString(R.string.char_array_required), charArrayOf('a', 'b'))
                starter.putExtra(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'))

                starter.putExtra(context.getString(R.string.string_required), "stringRequired")
                starter.putExtra(context.getString(R.string.string_optional), "stringOptional")
                starter.putExtra(context.getString(R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                starter.putExtra(context.getString(R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                starter.putStringArrayListExtra(context.getString(R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                starter.putStringArrayListExtra(context.getString(R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))

                starter.putExtra(context.getString(R.string.char_sequence_required), "stringRequired")
                starter.putExtra(context.getString(R.string.char_sequence_optional), "stringOptional")
                starter.putExtra(context.getString(R.string.char_sequence_array_required), arrayOf("stringRequired", "stringOptional"))
                starter.putExtra(context.getString(R.string.char_sequence_array_optional), arrayOf("stringOptional", "stringRequired"))


                starter.putExtra(context.getString(R.string.parcelable_required), TestParcelable("parcelableRequired"))
                starter.putExtra(context.getString(R.string.parcelable_optional), TestParcelable("parcelableOptional"))
                starter.putExtra(context.getString(R.string.parcelable_array_required), arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putExtra(context.getString(R.string.parcelable_array_optional), arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_required), arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_optional), arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))


                starter.putExtra(context.getString(R.string.serializable_required), TestSerializable("serializableRequired"))
                starter.putExtra(context.getString(R.string.serializable_optional), TestSerializable("serializableOptional"))

                starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra(context.getString(R.string.short_array_or_default), shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra(context.getString(R.string.int_array_or_default), intArrayOf(500, -500))
                starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600))
                starter.putExtra(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L))
                starter.putExtra(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f))
                starter.putExtra(context.getString(R.string.double_array_or_default), doubleArrayOf(6.0, -6.0))
                starter.putExtra(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false))
                starter.putExtra(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'))
                starter.putExtra(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault")
                starter.putExtra(context.getString(R.string.char_sequence_array_or_default), arrayOf("charSequence", "default"))


                starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_required), arrayListOf("charSequenceArrayListRequired", "required"))
                starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_optional), arrayListOf("charSequenceArrayListOptional", "optional"))
                starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_or_default), arrayListOf("charSequenceArrayListOrDefault", "default"))

                starter.putExtra(context.getString(R.string.string_or_default), "stringOrDefault")
                starter.putExtra(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"))
                starter.putExtra(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"))

                starter.putExtra(context.getString(R.string.parcelable_or_default), TestParcelable("parcelableOrDefault"))
                starter.putExtra(context.getString(R.string.parcelable_array_or_default), arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                starter.putExtra(context.getString(R.string.serializable_or_default), TestSerializable("serializableOrDefault"))

                val bundleDefault = Bundle()
                bundleDefault.putString("bundle", "bundleOrDefault")
                starter.putExtra(context.getString(R.string.bundle_or_default), bundleDefault)

                val bundleRequired = Bundle()
                bundleRequired.putString("bundle", "bundleRequired")
                starter.putExtra(context.getString(R.string.bundle_required), bundleRequired)

                val bundleOptional = Bundle()
                bundleOptional.putString("bundle", "bundleOptional")
                starter.putExtra(context.getString(R.string.bundle_optional), bundleOptional)

                val b = Bundle()
                b.putString("extrasRequired", "extrasRequired")
                b.putString("extrasOptional", "extrasOptional")
                b.putString("extrasOrDefault", "extrasOrDefault")
                starter.putExtras(b)

                val args = Bundle()
                val sparseParcelableArrayRequired = SparseArray<Parcelable>()
                sparseParcelableArrayRequired.put(-1, TestParcelable("-1"))
                sparseParcelableArrayRequired.put(1, TestParcelable("1"))
                args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_required), sparseParcelableArrayRequired)

                val sparseParcelableArrayOptional = SparseArray<Parcelable>()
                sparseParcelableArrayOptional.put(-2, TestParcelable("-2"))
                sparseParcelableArrayOptional.put(2, TestParcelable("2"))
                args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_optional), sparseParcelableArrayOptional)

                val sparseParcelableArrayOrDefault = SparseArray<Parcelable>()
                sparseParcelableArrayOrDefault.put(-3, TestParcelable("-3"))
                sparseParcelableArrayOrDefault.put(3, TestParcelable("3"))
                args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_or_default), sparseParcelableArrayOrDefault)

                args.putBinder(context.getString(R.string.binder_required), TestBinder("binderRequired"))
                args.putBinder(context.getString(R.string.binder_optional), TestBinder("binderOptional"))
                args.putBinder(context.getString(R.string.binder_or_default), TestBinder("binderOrDefault"))

                args.putSize(context.getString(R.string.size_required), Size(1, 1))
                args.putSize(context.getString(R.string.size_optional), Size(2, 2))
                args.putSize(context.getString(R.string.size_or_default), Size(3, 3))

                args.putSizeF(context.getString(R.string.sizeF_required), SizeF(1f, 1f))
                args.putSizeF(context.getString(R.string.sizeF_optional), SizeF(2f, 2f))
                args.putSizeF(context.getString(R.string.sizeF_or_default), SizeF(3f, 3f))

                starter.putExtras(args)

                return starter
            }
        }
    }

    class ResTestSupportFragment : Fragment() {
        companion object {
            fun newInstance(args: Bundle?): ResTestSupportFragment {
                val fragment = ResTestSupportFragment()
                fragment.arguments = args
                return fragment
            }
        }
    }

    class ResTestFragment : android.app.Fragment() {
        companion object {
            fun newInstance(args: Bundle?): ResTestFragment {
                val fragment = ResTestFragment()
                fragment.arguments = args
                return fragment
            }
        }
    }

    //no extras
    class NoExtraActivity : FragmentActivity() {
        companion object {
            fun createIntent(context: Context): Intent {
                return Intent(context, NoExtraActivity::class.java)
            }
        }
    }

    //uri || intent
    class TestOnlyUriNoIntentActivity : FragmentActivity() {
        companion object {
            fun createIntentWithUri(): Intent {
                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/uri?$params")

                return Intent(Intent.ACTION_VIEW, uri)
            }
        }
    }

    class TestOnlyIntentNoUriActivity : FragmentActivity() {
        companion object {
            fun createIntentWithExtras(context: Context): Intent {
                val starter = Intent(context, TestOnlyIntentNoUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                starter.putExtra("byteIntentUriOrDefault", (-1).toByte())
                starter.putExtra("shortIntentUriOrDefault", (-2).toShort())
                starter.putExtra("intIntentUriOrDefault", -3)
                starter.putExtra("longIntentUriOrDefault", -4L)
                starter.putExtra("floatIntentUriOrDefault", -5f)
                starter.putExtra("doubleIntentUriOrDefault", -6.0)
                starter.putExtra("booleanIntentUriOrDefault", true)
                starter.putExtra("stringIntentUriRequired", "stringIntentRequired")
                starter.putExtra("stringIntentUriOptional", "stringIntentOptional")
                starter.putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra("byteUriIntentOrDefault", (-11).toByte())
                starter.putExtra("shortUriIntentOrDefault", (-12).toShort())
                starter.putExtra("intUriIntentOrDefault", -13)
                starter.putExtra("longUriIntentOrDefault", -14L)
                starter.putExtra("floatUriIntentOrDefault", -15f)
                starter.putExtra("doubleUriIntentOrDefault", -16.0)
                starter.putExtra("booleanUriIntentOrDefault", true)
                starter.putExtra("stringUriIntentRequired", "stringUriIntentRequired")
                starter.putExtra("stringUriIntentOptional", "stringUriIntentOptional")
                starter.putExtra("stringUriIntentOrDefault", "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class TestBothIntentUriActivity : FragmentActivity() {
        companion object {

            fun createIntentWithUriAndExtras(context: Context): Intent {

                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/uri/intent?$params")

                val starter = Intent(context, TestBothIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = uri
                starter.putExtra("byteIntentUriOrDefault", (-1).toByte())
                starter.putExtra("shortIntentUriOrDefault", (-2).toShort())
                starter.putExtra("intIntentUriOrDefault", -3)
                starter.putExtra("longIntentUriOrDefault", -4L)
                starter.putExtra("floatIntentUriOrDefault", -5f)
                starter.putExtra("doubleIntentUriOrDefault", -6.0)
                starter.putExtra("booleanIntentUriOrDefault", true)
                starter.putExtra("stringIntentUriRequired", "stringIntentRequired")
                starter.putExtra("stringIntentUriOptional", "stringIntentOptional")
                starter.putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra("byteUriIntentOrDefault", (-11).toByte())
                starter.putExtra("shortUriIntentOrDefault", (-12).toShort())
                starter.putExtra("intUriIntentOrDefault", -13)
                starter.putExtra("longUriIntentOrDefault", -14L)
                starter.putExtra("floatUriIntentOrDefault", -15f)
                starter.putExtra("doubleUriIntentOrDefault", -16.0)
                starter.putExtra("booleanUriIntentOrDefault", true)
                starter.putExtra("stringUriIntentRequired", "stringUriIntentRequired")
                starter.putExtra("stringUriIntentOptional", "stringUriIntentOptional")
                starter.putExtra("stringUriIntentOrDefault", "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class TestNoIntentUriActivity : FragmentActivity() {
        companion object {
            fun createIntentWithNothing(context: Context): Intent {
                val starter = Intent(context, TestNoIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                return starter
            }
        }
    }

    //res
    class ResTestOnlyUriNoIntentActivity : FragmentActivity() {
        companion object {
            fun createIntentWithUri(): Intent {
                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/res/uri?$params")

                return Intent(Intent.ACTION_VIEW, uri)
            }
        }
    }

    class ResTestOnlyIntentNoUriActivity : FragmentActivity() {
        companion object {
            fun createIntentWithExtras(context: Context): Intent {
                val starter = Intent(context, ResTestOnlyIntentNoUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                starter.putExtra(context.getString(R.string.byte_intent_uri_or_default), (-1).toByte())
                starter.putExtra(context.getString(R.string.short_intent_uri_or_default), (-2).toShort())
                starter.putExtra(context.getString(R.string.int_intent_uri_or_default), -3)
                starter.putExtra(context.getString(R.string.long_intent_uri_or_default), -4L)
                starter.putExtra(context.getString(R.string.float_intent_uri_or_default), -5f)
                starter.putExtra(context.getString(R.string.double_intent_uri_or_default), -6.0)
                starter.putExtra(context.getString(R.string.boolean_intent_uri_or_default), true)
                starter.putExtra(context.getString(R.string.string_intent_uri_required), "stringIntentRequired")
                starter.putExtra(context.getString(R.string.string_intent_uri_optional), "stringIntentOptional")
                starter.putExtra(context.getString(R.string.string_intent_uri_or_default), "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra(context.getString(R.string.byte_uri_intent_or_default), (-11).toByte())
                starter.putExtra(context.getString(R.string.short_uri_intent_or_default), (-12).toShort())
                starter.putExtra(context.getString(R.string.int_uri_intent_or_default), -13)
                starter.putExtra(context.getString(R.string.long_uri_intent_or_default), -14L)
                starter.putExtra(context.getString(R.string.float_uri_intent_or_default), -15f)
                starter.putExtra(context.getString(R.string.double_uri_intent_or_default), -16.0)
                starter.putExtra(context.getString(R.string.boolean_uri_intent_or_default), true)
                starter.putExtra(context.getString(R.string.string_uri_intent_required), "stringUriIntentRequired")
                starter.putExtra(context.getString(R.string.string_uri_intent_optional), "stringUriIntentOptional")
                starter.putExtra(context.getString(R.string.string_uri_intent_or_default), "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class ResTestBothIntentUriActivity : FragmentActivity() {
        companion object {

            fun createIntentWithUriAndExtras(context: Context): Intent {

                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/res/uri/intent?$params")

                val starter = Intent(context, ResTestBothIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = uri
                starter.putExtra(context.getString(R.string.byte_intent_uri_or_default), (-1).toByte())
                starter.putExtra(context.getString(R.string.short_intent_uri_or_default), (-2).toShort())
                starter.putExtra(context.getString(R.string.int_intent_uri_or_default), -3)
                starter.putExtra(context.getString(R.string.long_intent_uri_or_default), -4L)
                starter.putExtra(context.getString(R.string.float_intent_uri_or_default), -5f)
                starter.putExtra(context.getString(R.string.double_intent_uri_or_default), -6.0)
                starter.putExtra(context.getString(R.string.boolean_intent_uri_or_default), true)
                starter.putExtra(context.getString(R.string.string_intent_uri_required), "stringIntentRequired")
                starter.putExtra(context.getString(R.string.string_intent_uri_optional), "stringIntentOptional")
                starter.putExtra(context.getString(R.string.string_intent_uri_or_default), "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra(context.getString(R.string.byte_uri_intent_or_default), (-11).toByte())
                starter.putExtra(context.getString(R.string.short_uri_intent_or_default), (-12).toShort())
                starter.putExtra(context.getString(R.string.int_uri_intent_or_default), -13)
                starter.putExtra(context.getString(R.string.long_uri_intent_or_default), -14L)
                starter.putExtra(context.getString(R.string.float_uri_intent_or_default), -15f)
                starter.putExtra(context.getString(R.string.double_uri_intent_or_default), -16.0)
                starter.putExtra(context.getString(R.string.boolean_uri_intent_or_default), true)
                starter.putExtra(context.getString(R.string.string_uri_intent_required), "stringUriIntentRequired")
                starter.putExtra(context.getString(R.string.string_uri_intent_optional), "stringUriIntentOptional")
                starter.putExtra(context.getString(R.string.string_uri_intent_or_default), "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class ResTestNoIntentUriActivity : FragmentActivity() {
        companion object {
            fun createIntentWithNothing(context: Context): Intent {
                val starter = Intent(context, ResTestNoIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                return starter
            }
        }
    }

    //activity uri
    class TestUriActivity : FragmentActivity() {
        companion object {

            fun createIntent(): Intent {
                val params = StringBuilder()
                        .append("byteUriRequired=").append(1.toByte().toInt())
                        .append("&byteUriOptional=").append((-1).toByte().toInt())
                        .append("&byteUriOrDefault=").append(2.toByte().toInt())

                        .append("&shortUriRequired=").append(3.toShort().toInt())
                        .append("&shortUriOptional=").append((-3).toShort().toInt())
                        .append("&shortUriOrDefault=").append(4.toShort().toInt())

                        .append("&intUriRequired=").append(5)
                        .append("&intUriOptional=").append(-5)
                        .append("&intUriOrDefault=").append(6)

                        .append("&longUriRequired=").append(7L)
                        .append("&longUriOptional=").append(-7L)
                        .append("&longUriOrDefault=").append(8L)

                        .append("&floatUriRequired=").append(9f)
                        .append("&floatUriOptional=").append(-9f)
                        .append("&floatUriOrDefault=").append(10f)

                        .append("&doubleUriRequired=").append(11.0)
                        .append("&doubleUriOptional=").append(-11.0)
                        .append("&doubleUriOrDefault=").append(12.0)

                        .append("&booleanUriRequired=").append(true)
                        .append("&booleanUriOptional=").append(true)
                        .append("&booleanUriOrDefault=").append(false)

                        .append("&stringUriRequired=").append("stringUriRequired")
                        .append("&stringUriOptional=").append("stringUriOptional")
                        .append("&stringUriOrDefault=").append("stringUriOrDefault")
                        .toString()
                val uri = Uri.parse("https://github.com/panpf/androidx?$params")
                return Intent(Intent.ACTION_VIEW, uri)
            }
        }
    }

    class ResTestUriActivity : FragmentActivity() {
        companion object {

            fun createIntent(): Intent {
                val params = StringBuilder()
                        .append("byteUriRequired=").append(1.toByte().toInt())
                        .append("&byteUriOptional=").append((-1).toByte().toInt())
                        .append("&byteUriOrDefault=").append(2.toByte().toInt())

                        .append("&shortUriRequired=").append(3.toShort().toInt())
                        .append("&shortUriOptional=").append((-3).toShort().toInt())
                        .append("&shortUriOrDefault=").append(4.toShort().toInt())

                        .append("&intUriRequired=").append(5)
                        .append("&intUriOptional=").append(-5)
                        .append("&intUriOrDefault=").append(6)

                        .append("&longUriRequired=").append(7L)
                        .append("&longUriOptional=").append(-7L)
                        .append("&longUriOrDefault=").append(8L)

                        .append("&floatUriRequired=").append(9f)
                        .append("&floatUriOptional=").append(-9f)
                        .append("&floatUriOrDefault=").append(10f)

                        .append("&doubleUriRequired=").append(11.0)
                        .append("&doubleUriOptional=").append(-11.0)
                        .append("&doubleUriOrDefault=").append(12.0)

                        .append("&booleanUriRequired=").append(true)
                        .append("&booleanUriOptional=").append(true)
                        .append("&booleanUriOrDefault=").append(false)

                        .append("&stringUriRequired=").append("stringUriRequired")
                        .append("&stringUriOptional=").append("stringUriOptional")
                        .append("&stringUriOrDefault=").append("stringUriOrDefault")
                        .toString()
                val uri = Uri.parse("https://github.com/panpf/androidx/res?$params")
                return Intent(Intent.ACTION_VIEW, uri)
            }
        }

    }

    @Parcelize
    data class TestParcelable(val tag: String) : Parcelable

    data class TestSerializable(val tag: String) : java.io.Serializable

    data class TestBinder(val tag: String = "") : Binder()
}