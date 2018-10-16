package me.panpf.androidxkt.args.test

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import me.panpf.androidxkt.args.*

class TestBindActivity : FragmentActivity() {

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

        setContentView(me.panpf.androidxkt.args.test.R.layout.at_test)

        val fragment = TestBindFragment()
        fragment.arguments = Bundle().apply {
            putByte("byteRequired", byteRequired)
            putByteArray("byteArrayRequired", byteArrayRequired)
            byteArrayOptional?.let { putByteArray("byteArrayOptional", it) }

            putShort("shortRequired", shortRequired)
            putShortArray("shortArrayRequired", shortArrayRequired)
            shortArrayOptional?.let { putShortArray("shortArrayOptional", it) }

            putInt("intRequired", intRequired)
            putIntArray("intArrayRequired", intArrayRequired)
            intArrayOptional?.let { putIntArray("intArrayOptional", it) }
            putIntegerArrayList("intArrayListRequired", intArrayListRequired)
            intArrayListOptional?.let { putIntegerArrayList("intArrayListOptional", it) }

            putLong("longRequired", longRequired)
            putLongArray("longArrayRequired", longArrayRequired)
            longArrayOptional?.let { putLongArray("longArrayOptional", it) }

            putFloat("floatRequired", floatRequired)
            putFloatArray("floatArrayRequired", floatArrayRequired)
            floatArrayOptional?.let { putFloatArray("floatArrayOptional", it) }

            putDouble("doubleRequired", doubleRequired)
            putDoubleArray("doubleArrayRequired", doubleArrayRequired)
            doubleArrayOptional?.let { putDoubleArray("doubleArrayOptional", it) }

            putBoolean("booleanRequired", booleanRequired)
            putBooleanArray("booleanArrayRequired", booleanArrayRequired)
            booleanArrayOptional?.let { putBooleanArray("booleanArrayOptional", it) }

            putChar("charRequired", charRequired)
            putCharArray("charArrayRequired", charArrayRequired)
            charArrayOptional?.let { putCharArray("charArrayOptional", it) }

            putString("stringRequired", stringRequired)
            stringOptional?.let { putString("stringOptional", it) }
            putStringArray("stringArrayRequired", stringArrayRequired)
            stringArrayOptional?.let { putStringArray("stringArrayOptional", it) }
            putStringArrayList("stringArrayListRequired", stringArrayListRequired)
            stringArrayListOptional?.let { putStringArrayList("stringArrayListOptional", it) }

            putCharSequence("charSequenceRequired", charSequenceRequired)
            charSequenceOptional?.let { putCharSequence("charSequenceOptional", it) }
            putCharSequenceArray("charSequenceArrayRequired", charSequenceArrayRequired)
            charSequenceArrayOptional?.let { putCharSequenceArray("charSequenceArrayOptional", it) }

            putParcelable("parcelableRequired", parcelableRequired)
            parcelableOptional?.let { putParcelable("parcelableOptional", it) }
            putParcelableArray("parcelableArrayRequired", parcelableArrayRequired)
            parcelableArrayOptional?.let { putParcelableArray("parcelableArrayOptional", it) }
            putParcelableArrayList("parcelableArrayListRequired", parcelableArrayListRequired)
            parcelableArrayListOptional?.let { putParcelableArrayList("parcelableArrayListOptional", it) }

            putSerializable("serializableRequired", serializableRequired)
            serializableOptional?.let { putSerializable("serializableOptional", it) }

            putBundle("bundleRequired", bundleRequired)
            bundleOptional?.let { putBundle("bundleOptional", it) }
        }
        supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.args.test.R.id.testAt_frame, fragment).commit()
    }
}