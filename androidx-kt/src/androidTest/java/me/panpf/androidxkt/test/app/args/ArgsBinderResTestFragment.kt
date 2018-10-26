package me.panpf.androidxkt.test.app.args

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import me.panpf.androidxkt.app.*

class ArgsBinderResTestFragment : Fragment() {

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