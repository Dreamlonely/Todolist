package com.example.todo.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u001a\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001f\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\f2\u0006\u0010\"\u001a\u00020\fH\u0002\u00a2\u0006\u0002\u0010#J\b\u0010$\u001a\u00020\fH\u0002J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\'H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006("}, d2 = {"Lcom/example/todo/ui/CalendarFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_b", "Lcom/example/todo/databinding/FragmentCalendarBinding;", "allTasks", "", "Lcom/example/todo/model/Task;", "b", "getB", "()Lcom/example/todo/databinding/FragmentCalendarBinding;", "selectedDayStartMillis", "", "vm", "Lcom/example/todo/ui/TaskViewModel;", "getVm", "()Lcom/example/todo/ui/TaskViewModel;", "vm$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "sameDay", "", "taskDueMillis", "dayStartMillis", "(Ljava/lang/Long;J)Z", "startOfToday", "updateTasksForSelectedDay", "adapter", "Lcom/example/todo/ui/TaskAdapter;", "app_debug"})
public final class CalendarFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.todo.databinding.FragmentCalendarBinding _b;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.todo.model.Task> allTasks;
    private long selectedDayStartMillis;
    
    public CalendarFragment() {
        super();
    }
    
    private final com.example.todo.databinding.FragmentCalendarBinding getB() {
        return null;
    }
    
    private final com.example.todo.ui.TaskViewModel getVm() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateTasksForSelectedDay(com.example.todo.ui.TaskAdapter adapter) {
    }
    
    private final boolean sameDay(java.lang.Long taskDueMillis, long dayStartMillis) {
        return false;
    }
    
    private final long startOfToday() {
        return 0L;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}