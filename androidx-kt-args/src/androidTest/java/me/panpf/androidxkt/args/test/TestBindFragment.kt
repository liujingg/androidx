package me.panpf.androidxkt.args.test

import android.os.Parcelable
import android.support.v4.app.Fragment
import me.panpf.androidxkt.args.*

class TestBindFragment : Fragment(){

    val booleanRequired by bindBooleanArg("booleanRequired")
    val booleanOptional by bindOptionalBooleanArg("booleanOptional")
    val booleanArrayRequired by bindBooleanArrayArg("booleanArrayRequired")
    val booleanArrayOptional by bindOptionalBooleanArrayArg("booleanArrayOptional")

    val byteRequired by bindByteArg("byteRequired")
    val byteOptional by bindOptionalByteArg("byteOptional")
    val byteArrayRequired by bindByteArrayArg("byteArrayRequired")
    val byteArrayOptional by bindOptionalByteArrayArg("byteArrayOptional")

    val charRequired by bindCharArg("charRequired")
    val charOptional by bindOptionalCharArg("charOptional")
    val charArrayRequired by bindCharArrayArg("charArrayRequired")
    val charArrayOptional by bindOptionalCharArrayArg("charArrayOptional")

    val shortRequired by bindShortArg("shortRequired")
    val shortOptional by bindOptionalShortArg("shortOptional")
    val shortArrayRequired by bindShortArrayArg("shortArrayRequired")
    val shortArrayOptional by bindOptionalShortArrayArg("shortArrayOptional")

    val floatRequired by bindFloatArg("floatRequired")
    val floatOptional by bindOptionalFloatArg("floatOptional")
    val floatArrayRequired by bindFloatArrayArg("floatArrayRequired")
    val floatArrayOptional by bindOptionalFloatArrayArg("floatArrayOptional")

    val intRequired by bindIntArg("intRequired")
    val intOptional by bindOptionalIntArg("intOptional")
    val intArrayRequired by bindIntArrayArg("intArrayRequired")
    val intArrayOptional by bindOptionalIntArrayArg("intArrayOptional")
    val intArrayListRequired by bindIntArrayListArg("intArrayListRequired")
    val intArrayListOptional by bindOptionalIntArrayListArg("intArrayListOptional")

    val doubleRequired by bindDoubleArg("doubleRequired")
    val doubleOptional by bindOptionalDoubleArg("doubleOptional")
    val doubleArrayRequired by bindDoubleArrayArg("doubleArrayRequired")
    val doubleArrayOptional by bindOptionalDoubleArrayArg("doubleArrayOptional")

    val longRequired by bindLongArg("longRequired")
    val longOptional by bindOptionalLongArg("longOptional")
    val longArrayRequired by bindLongArrayArg("longArrayRequired")
    val longArrayOptional by bindOptionalLongArrayArg("longArrayOptional")

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