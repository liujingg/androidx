//package me.panpf.androidx.test.app;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.support.annotation.Nullable;
//
//import me.panpf.javax.util.Comparisonx;
//
//public final class TestParcelable implements Parcelable {
//    @NonNull
//    private final String tag;
//    public static final android.os.Parcelable.Creator CREATOR = new TestParcelable.Creator();
//
//    @NonNull
//    public final String getTag() {
//        return this.tag;
//    }
//
//    public TestParcelable(@NonNull String tag) {
//        this.tag = tag;
//    }
//
//    @NonNull
//    public final String component1() {
//        return this.tag;
//    }
//
//    @NonNull
//    public final TestParcelable copy(@NonNull String tag) {
//        return new TestParcelable(tag);
//    }
//
//    // $FF: synthetic method
//    // $FF: bridge method
//    @NonNull
//    public static TestParcelable copy$default(TestParcelable var0, String var1, int var2, Object var3) {
//        if ((var2 & 1) != 0) {
//            var1 = var0.tag;
//        }
//
//        return var0.copy(var1);
//    }
//
//    @NonNull
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
//    public void writeToParcel(@NonNull Parcel parcel, int flags) {
//        parcel.writeString(this.tag);
//    }
//
//    public static class Creator implements android.os.Parcelable.Creator {
//        @NonNull
//        public final Object[] newArray(int size) {
//            return new TestParcelable[size];
//        }
//
//        @NonNull
//        public final Object createFromParcel(@NonNull Parcel in) {
//            return new TestParcelable(in.readString());
//        }
//    }
//}
