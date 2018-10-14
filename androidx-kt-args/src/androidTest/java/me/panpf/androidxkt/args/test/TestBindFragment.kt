package me.panpf.androidxkt.args.test

import android.os.Parcelable
import android.support.v4.app.Fragment
import me.panpf.androidxkt.args.*

class TestBindFragment : Fragment() {

    val byteRequired by bindByteArg("byteRequired")
    val byteArrayRequired by bindByteArrayArg("byteArrayRequired")
    val byteArrayOptional by bindOptionalByteArrayArg("byteArrayOptional")

    val shortRequired by bindShortArg("shortRequired")
    val shortArrayRequired by bindShortArrayArg("shortArrayRequired")
    val shortArrayOptional by bindOptionalShortArrayArg("shortArrayOptional")

    val intRequired by bindIntArg("intRequired")
    val intArrayRequired by bindIntArrayArg("intArrayRequired")
    val intArrayOptional by bindOptionalIntArrayArg("intArrayOptional")
    val intArrayListRequired by bindIntArrayListArg("intArrayListRequired")
    val intArrayListOptional by bindOptionalIntArrayListArg("intArrayListOptional")

    val longRequired by bindLongArg("longRequired")
    val longArrayRequired by bindLongArrayArg("longArrayRequired")
    val longArrayOptional by bindOptionalLongArrayArg("longArrayOptional")

    val floatRequired by bindFloatArg("floatRequired")
    val floatArrayRequired by bindFloatArrayArg("floatArrayRequired")
    val floatArrayOptional by bindOptionalFloatArrayArg("floatArrayOptional")

    val doubleRequired by bindDoubleArg("doubleRequired")
    val doubleArrayRequired by bindDoubleArrayArg("doubleArrayRequired")
    val doubleArrayOptional by bindOptionalDoubleArrayArg("doubleArrayOptional")

    val booleanRequired by bindBooleanArg("booleanRequired")
    val booleanArrayRequired by bindBooleanArrayArg("booleanArrayRequired")
    val booleanArrayOptional by bindOptionalBooleanArrayArg("booleanArrayOptional")

    val charRequired by bindCharArg("charRequired")
    val charArrayRequired by bindCharArrayArg("charArrayRequired")
    val charArrayOptional by bindOptionalCharArrayArg("charArrayOptional")

    val charSequenceRequired by bindCharSequenceArg("charSequenceRequired")
    val charSequenceOptional by bindOptionalCharSequenceArg("charSequenceOptional")
    val charSequenceArrayRequired by bindCharSequenceArrayArg("charSequenceArrayRequired")
    val charSequenceArrayOptional by bindOptionalCharSequenceArrayArg("charSequenceArrayOptional")

    val stringRequired by bindStringArg("stringRequired")
    val stringOptional by bindOptionalStringArg("stringOptional")
    val stringArrayRequired by bindStringArrayArg("stringArrayRequired")
    val stringArrayOptional by bindOptionalStringArrayArg("stringArrayOptional")
    val stringArrayListRequired by bindStringArrayListArg("stringArrayListRequired")
    val stringArrayListOptional by bindOptionalStringArrayListArg("stringArrayListOptional")

    val parcelableRequired by bindParcelableArg<TestParcelable>("parcelableRequired")
    val parcelableOptional by bindOptionalParcelableArg<TestParcelable>("parcelableOptional")
    val parcelableArrayRequired by bindParcelableArrayArg<Parcelable>("parcelableArrayRequired")
    val parcelableArrayOptional by bindOptionalParcelableArrayArg<Parcelable>("parcelableArrayOptional")
    val parcelableArrayListRequired by bindParcelableArrayListArg<TestParcelable>("parcelableArrayListRequired")
    val parcelableArrayListOptional by bindOptionalParcelableArrayListArg<TestParcelable>("parcelableArrayListOptional")

    val serializableRequired by bindSerializableArg<TestSerializable>("serializableRequired")
    val serializableOptional by bindOptionalSerializableArg<TestSerializable>("serializableOptional")

    val bundleRequired by bindBundleArg("bundleRequired")
    val bundleOptional by bindOptionalBundleArg("bundleOptional")
}