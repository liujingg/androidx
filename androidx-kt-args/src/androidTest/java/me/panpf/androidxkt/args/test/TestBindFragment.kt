package me.panpf.androidxkt.args.test

import android.os.Parcelable
import android.support.v4.app.Fragment
import me.panpf.androidxkt.args.*

class TestBindFragment : Fragment() {

    val byteRequired by bindByteArgOr("byteRequired")
    val byteArrayRequired by bindByteArrayArg("byteArrayRequired")
    val byteArrayOptional by bindByteArrayArgOrNull("byteArrayOptional")

    val shortRequired by bindShortArgOr("shortRequired")
    val shortArrayRequired by bindShortArrayArg("shortArrayRequired")
    val shortArrayOptional by bindShortArrayArgOrNull("shortArrayOptional")

    val intRequired by bindIntArgOr("intRequired")
    val intArrayRequired by bindIntArrayArg("intArrayRequired")
    val intArrayOptional by bindIntArrayArg("intArrayOptional")
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
}