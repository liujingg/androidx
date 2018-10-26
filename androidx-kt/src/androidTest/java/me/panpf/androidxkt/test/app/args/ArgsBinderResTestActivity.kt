package me.panpf.androidxkt.test.app.args

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import me.panpf.androidxkt.app.*

class ArgsBinderResTestActivity : FragmentActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ArgsBinderResTestActivity::class.java).apply {
                putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_required), "stringRequired")
                putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_optional), "stringOptional")
                putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                putExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                putStringArrayListExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                putStringArrayListExtra(context.getString(me.panpf.androidxkt.test.R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))
            }
        }
    }

    val stringRequired by bindStringArg(me.panpf.androidxkt.test.R.string.string_required)
    val stringOptional by bindStringArgOrNull(me.panpf.androidxkt.test.R.string.string_optional)
    val stringArrayRequired by bindStringArrayArg(me.panpf.androidxkt.test.R.string.string_array_required)
    val stringArrayOptional by bindStringArrayArgOrNull(me.panpf.androidxkt.test.R.string.string_array_optional)
    val stringArrayListRequired by bindStringArrayListArg(me.panpf.androidxkt.test.R.string.string_array_list_required)
    val stringArrayListOptional by bindStringArrayListArgOrNull(me.panpf.androidxkt.test.R.string.string_array_list_optional)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(me.panpf.androidxkt.test.R.layout.at_test)

        val fragment = ArgsBinderResTestFragment()
        fragment.arguments = ArgsBinderResTestFragment.createArguments(this)
        supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.test.R.id.testAt_frame, fragment).commit()
    }
}