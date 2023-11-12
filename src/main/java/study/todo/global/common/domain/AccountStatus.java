package study.todo.global.common.domain;

public enum AccountStatus {
    ACTIVE,
    INACTIVE;

    public boolean isInactive() {
        return this == INACTIVE;
    }
}
