package ru.nsu.mbogdanov2;

/** Enum class for mark iteration.
 *
 */

public enum Mark {
    BEST("Отлично"),
    GOOD("Хорошо"),
    NICE("Удовлетворительно"),
    OK("Зачёт");

    private final String mark;

    Mark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public static Mark rusValuesOf(String mark) {
        switch (mark) {
            case "Удовлетворительно" -> {
                return NICE;
            }
            case "Хорошо" -> {
                return GOOD;
            }
            case "Отлично" -> {
                return BEST;
            }
            case "Зачёт" -> {
                return OK;
            }
            default -> throw new
                    IllegalArgumentException("Такой оценки не бывает");
        }
    }
}