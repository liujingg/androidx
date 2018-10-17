//package me.panpf.androidx.test.app;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.support.annotation.Nullable;
//
//import org.jetbrains.annotations.NotNull;
//
//import me.panpf.javax.util.Comparisonx;
//
//public final class TestParcelable implements Parcelable {
//    @NotNull
//    private final String tag;
//    public static final android.os.Parcelable.Creator CREATOR = new TestParcelable.Creator();
//
//    @NotNull
//    public final String getTag() {
//        return this.tag;
//    }
//
//    public TestParcelable(@NotNull String tag) {
//        this.tag = tag;
//    }
//
//    @NotNull
//    public final String component1() {
//        return this.tag;
//    }
//
//    @NotNull
//    public final TestParcelable copy(@NotNull String tag) {
//        return new TestParcelable(tag);
//    }
//
//    // $FF: synthetic method
//    // $FF: bridge method
//    @NotNull
//    public static TestParcelable copy$default(TestParcelable var0, String var1, int var2, Object var3) {
//        if ((var2 & 1) != 0) {
//            var1 = var0.tag;
//        }
//
//        return var0.copy(var1);
//    }
//
//    @NotNull
//    public String toString() {
//        return "TestParcelable(tag=" + this.tag + ")";
//    }
//
//    public int hashCode() {
//        return this.tag != null ? this.tag.hashCode() : 0;
//    }
//
//    public boolean equals(@Nullable Object var1) {
//        if (this != var1) {
//            if (var1 instanceof TestParcelable) {
//                TestParcelable var2 = (TestParcelable)var1;
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
//
//    public int describeContents() {
//        return 0;
//    }
//
//    public void writeToParcel(@NotNull Parcel parcel, int flags) {
//        parcel.writeString(this.tag);
//    }
//
//    public static class Creator implements android.os.Parcelable.Creator {
//        @NotNull
//        public final Object[] newArray(int size) {
//            return new TestParcelable[size];
//        }
//
//        @NotNull
//        public final Object createFromParcel(@NotNull Parcel in) {
//            return new TestParcelable(in.readString());
//        }
//    }
//}
