//package me.panpf.androidx.test.app;
//
//import android.support.annotation.Nullable;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.io.Serializable;
//
//import me.panpf.javax.util.Comparisonx;
//
//public final class TestSerializable implements Serializable {
//    @NotNull
//    private final String tag;
//
//    @NotNull
//    public final String getTag() {
//        return this.tag;
//    }
//
//    public TestSerializable(@NotNull String tag) {
//        this.tag = tag;
//    }
//
//    @NotNull
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
