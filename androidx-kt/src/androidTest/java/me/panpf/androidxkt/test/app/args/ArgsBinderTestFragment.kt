package me.panpf.androidxkt.test.app.args

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import me.panpf.androidxkt.app.*

class ArgsBinderTestFragment : Fragment() {

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
        fun createArguments(activity: ArgsBinderTestActivity): Bundle = Bundle().apply {
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