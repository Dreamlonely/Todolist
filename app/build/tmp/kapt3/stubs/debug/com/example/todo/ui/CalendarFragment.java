package com.example.todo.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u001a\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001f\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0002\u00a2\u0006\u0002\u0010&J\b\u0010\'\u001a\u00020\u000eH\u0002J\b\u0010(\u001a\u00020\u001fH\u0002J\u0010\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020+H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006,"}, d2 = {"Lcom/example/todo/ui/CalendarFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_b", "Lcom/example/todo/databinding/FragmentCalendarBinding;", "allTasks", "", "Lcom/example/todo/model/Task;", "b", "getB", "()Lcom/example/todo/databinding/FragmentCalendarBinding;", "dotPurple", "", "selectedDayStartMillis", "", "selectionLightPurple", "vm", "Lcom/example/todo/ui/TaskViewModel;", "getVm", "()Lcom/example/todo/ui/TaskViewModel;", "vm$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "sameDay", "", "taskDueMillis", "dayStartMillis", "(Ljava/lang/Long;J)Z", "startOfToday", "updateCalendarDots", "updateTasksForSelectedDay", "adapter", "Lcom/example/todo/ui/TaskAdapter;", "app_debug"})
public final class CalendarFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.todo.databinding.FragmentCalendarBinding _b;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.todo.model.Task> allTasks;
    private long selectedDayStartMillis;
    private final int dotPurple = -10354450;
    private final int selectionLightPurple = -1999518487;
    
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
    
    private final void updateCalendarDots() {
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