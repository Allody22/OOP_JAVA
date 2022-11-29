package ru.nsu.mbogdanov2;

/**
 * Record class to collect information about one subject.
 *
 * @param mark     mark for subjects spelled in Russian
 * @param subject  just subject name
 * @param semester semester number
 */
public record StudentDataForOneSubject(Mark mark, String subject, int semester) { }

