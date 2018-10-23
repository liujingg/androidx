//package me.panpf.androidx.test.app;
//
//import android.support.annotation.Nullable;
//
//import java.io.Serializable;
//
//import me.panpf.javax.util.Comparisonx;
//
//public final class TestSerializable implements Serializable {
//    @NonNull
//    private final String tag;
//
//    @NonNull
//    public final String getTag() {
//        return this.tag;
//    }
//
//    public TestSerializable(@NonNull String tag) {
//        this.tag = tag;
//    }
//
//    @NonNull
//    public String toString() {
//        return "TestSerializable(tag=" + this.tag + ")";
//    }
//
//    public int hashCode() {
//        return this.tag != null ? this.tag.hashCode() : 0;
//    }
//
//    public boolean equals(@Nullable Object var1) {
//        if (this != var1) {
//            if (var1 instanceof TestSerializable) {
//                TestSerializable var2 = (TestSerializable)var1;
//                if (Comparisonx.areEqual(this.tag, var2.tag)) {
//                    return true;
//                }
//            }
//
//            return false;
//        } else {
//            return true;
//        }
//    }
//}
