package com.example.puppicasso.domain.email.templates;

public enum EmailTemplate {
    PASSWORD_RESET("[PUPPICASSO] 임시 비밀번호 발급", "Here is your new password: %s");

    private final String subject;
    private final String message;

    EmailTemplate(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage(String... args) {
        return String.format(message, (Object[]) args);
    }
}
