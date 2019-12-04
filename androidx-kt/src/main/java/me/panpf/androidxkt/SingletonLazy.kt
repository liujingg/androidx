/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.panpf.androidxkt

import android.app.Application


// todo 完善测试用例
class ApplicationSingletonLazy<T>(val create: (application: Application) -> T) {
    private var instance: T? = null

    fun get(application: Application): T {
        val tempInstance = instance
        if (tempInstance != null) return tempInstance

        synchronized(this) {
            val tempInstance2 = instance
            if (tempInstance2 != null) return tempInstance2

            val newInstance = create(application)
            this.instance = newInstance
            return newInstance
        }
    }
}