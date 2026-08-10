package com.min.applocker;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;

public class AppLockService extends AccessibilityService {
    String lastPackage = "";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            String pkg = event.getPackageName().toString();
            // MAU KUNCI APP APA? GANTI DI SINI
            if (pkg.equals("com.whatsapp") &&!pkg.equals(lastPackage)) {
                lastPackage = pkg;
                Intent i = new Intent(this, LockActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("target", pkg);
                startActivity(i);
            }
        }
    }
    @Override public void onInterrupt() {}
}