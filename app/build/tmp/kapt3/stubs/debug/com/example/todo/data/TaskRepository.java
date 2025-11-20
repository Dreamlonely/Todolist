package com.example.todo.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u001e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0002J\u0016\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006!"}, d2 = {"Lcom/example/todo/data/TaskRepository;", "", "dao", "Lcom/example/todo/data/TaskDao;", "context", "Landroid/content/Context;", "(Lcom/example/todo/data/TaskDao;Landroid/content/Context;)V", "tasks", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/todo/model/Task;", "getTasks", "()Lkotlinx/coroutines/flow/Flow;", "addTask", "", "task", "(Lcom/example/todo/model/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelNotification", "taskId", "", "deleteAt", "position", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "move", "from", "to", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleNotification", "toggleDone", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTask", "app_debug"})
public final class TaskRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.todo.data.TaskDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.todo.model.Task>> tasks = null;
    
    public TaskRepository(@org.jetbrains.annotations.NotNull()
    com.example.todo.data.TaskDao dao, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.todo.model.Task>> getTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addTask(@org.jetbrains.annotations.NotNull()
    com.example.todo.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTask(@org.jetbrains.annotations.NotNull()
    com.example.todo.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleDone(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAt(int position, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object move(int from, int to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void scheduleNotification(com.example.todo.model.Task task) {
    }
    
    private final void cancelNotification(java.lang.String taskId) {
    }
}