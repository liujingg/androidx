package me.panpf.androidxkt.test.app.args

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import me.panpf.androidxkt.app.*

class ArgsBinderTestActivity : FragmentActivity() {

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

        val fragment = ArgsBinderTestFragment()
        fragment.arguments = ArgsBinderTestFragment.createArguments(this)
        supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.test.R.id.testAt_frame, fragment).commit()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ArgsBinderTestActivity::class.java).apply {

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