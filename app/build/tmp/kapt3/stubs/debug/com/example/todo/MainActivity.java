package com.example.todo;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0011H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/example/todo/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "allTasks", "", "Lcom/example/todo/model/Task;", "b", "Lcom/example/todo/databinding/ActivityMainBinding;", "currentQuery", "", "vm", "Lcom/example/todo/ui/TaskViewModel;", "getVm", "()Lcom/example/todo/ui/TaskViewModel;", "vm$delegate", "Lkotlin/Lazy;", "applyFilter", "", "adapter", "Lcom/example/todo/ui/TaskAdapter;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showAddDialog", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.todo.databinding.ActivityMainBinding b;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.todo.model.Task> allTasks;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentQuery = "";
    
    public MainActivity() {
        super();
    }
    
    private final com.example.todo.ui.TaskViewModel getVm() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void applyFilter(com.example.todo.ui.TaskAdapter adapter) {
    }
    
    private final void showAddDialog() {
    }
}