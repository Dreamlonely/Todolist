package com.example.todo.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0016H\u0016J\u001a\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J$\u0010&\u001a\u00020\u00162\u0006\u0010\'\u001a\u00020(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00160*H\u0002J\b\u0010,\u001a\u00020\u0016H\u0002J\u0010\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u0007H\u0002J\u0006\u0010/\u001a\u00020\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012\u00a8\u00061"}, d2 = {"Lcom/example/todo/ui/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_b", "Lcom/example/todo/databinding/FragmentHomeBinding;", "allTasks", "", "Lcom/example/todo/model/Task;", "b", "getB", "()Lcom/example/todo/databinding/FragmentHomeBinding;", "currentQuery", "", "currentSort", "Lcom/example/todo/ui/HomeFragment$SortMode;", "vm", "Lcom/example/todo/ui/TaskViewModel;", "getVm", "()Lcom/example/todo/ui/TaskViewModel;", "vm$delegate", "Lkotlin/Lazy;", "applyFilterAndSort", "", "adapter", "Lcom/example/todo/ui/TaskAdapter;", "canMutateByPosition", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "pickDateTime", "calendar", "Ljava/util/Calendar;", "onPicked", "Lkotlin/Function1;", "", "showAddDialog", "showEditTaskDialog", "task", "showTools", "SortMode", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.todo.databinding.FragmentHomeBinding _b;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.todo.model.Task> allTasks;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentQuery = "";
    @org.jetbrains.annotations.NotNull()
    private com.example.todo.ui.HomeFragment.SortMode currentSort = com.example.todo.ui.HomeFragment.SortMode.BY_ORDER;
    
    public HomeFragment() {
        super();
    }
    
    private final com.example.todo.databinding.FragmentHomeBinding getB() {
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
    
    public final void showTools() {
    }
    
    private final boolean canMutateByPosition() {
        return false;
    }
    
    private final void applyFilterAndSort(com.example.todo.ui.TaskAdapter adapter) {
    }
    
    private final void showAddDialog() {
    }
    
    private final void showEditTaskDialog(com.example.todo.model.Task task) {
    }
    
    private final void pickDateTime(java.util.Calendar calendar, kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onPicked) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/todo/ui/HomeFragment$SortMode;", "", "(Ljava/lang/String;I)V", "BY_ORDER", "PRIORITY_HIGH_FIRST", "DUE_SOONEST_FIRST", "DUE_LATEST_FIRST", "app_debug"})
    static enum SortMode {
        /*public static final*/ BY_ORDER /* = new BY_ORDER() */,
        /*public static final*/ PRIORITY_HIGH_FIRST /* = new PRIORITY_HIGH_FIRST() */,
        /*public static final*/ DUE_SOONEST_FIRST /* = new DUE_SOONEST_FIRST() */,
        /*public static final*/ DUE_LATEST_FIRST /* = new DUE_LATEST_FIRST() */;
        
        SortMode() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.example.todo.ui.HomeFragment.SortMode> getEntries() {
            return null;
        }
    }
}