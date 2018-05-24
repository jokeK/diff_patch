package com.mik.bspatch;

public class MikPatchAndDiff {
    static {
        System.loadLibrary("mikBsPatch");
        System.loadLibrary("mikBsDiff");
    }
    public native static void diff(String old_APK_Path,String new_APK_Path,String patch_Path);
    public native static void patch(String old_APK_Path,String new_APK_Path,String patch_Path);
}
