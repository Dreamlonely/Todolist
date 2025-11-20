package com.example.todo.notifications;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/todo/notifications/NotificationHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createNotificationChannel", "", "showNotification", "taskTitle", "", "taskId", "app_debug"})
@androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public NotificationHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private final void createNotificationChannel() {
    }
    
    @androidx.annotation.RequiresPermission(value = "android.permission.POST_NOTIFICATIONS")
    public final void showNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String taskTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String taskId) {
    }
}