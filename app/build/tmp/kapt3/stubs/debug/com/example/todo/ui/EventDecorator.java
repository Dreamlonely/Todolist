package com.example.todo.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/todo/ui/EventDecorator;", "Lcom/prolificinteractive/materialcalendarview/DayViewDecorator;", "dates", "", "Lcom/prolificinteractive/materialcalendarview/CalendarDay;", "color", "", "(Ljava/util/Set;I)V", "decorate", "", "view", "Lcom/prolificinteractive/materialcalendarview/DayViewFacade;", "shouldDecorate", "", "day", "app_debug"})
public final class EventDecorator implements com.prolificinteractive.materialcalendarview.DayViewDecorator {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<com.prolificinteractive.materialcalendarview.CalendarDay> dates = null;
    private final int color = 0;
    
    public EventDecorator(@org.jetbrains.annotations.NotNull()
    java.util.Set<com.prolificinteractive.materialcalendarview.CalendarDay> dates, int color) {
        super();
    }
    
    @java.lang.Override()
    public boolean shouldDecorate(@org.jetbrains.annotations.NotNull()
    com.prolificinteractive.materialcalendarview.CalendarDay day) {
        return false;
    }
    
    @java.lang.Override()
    public void decorate(@org.jetbrains.annotations.NotNull()
    com.prolificinteractive.materialcalendarview.DayViewFacade view) {
    }
}