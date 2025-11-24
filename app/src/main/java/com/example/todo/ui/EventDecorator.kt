package com.example.todo.ui

import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.CalendarDay

class EventDecorator(
    private val dates: Set<CalendarDay>,
    private val color: Int
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean = dates.contains(day)

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(8f, color, offset = 0f)) // default slightly lower
    }
}
