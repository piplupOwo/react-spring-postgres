package com.example.demo.emailsender;

public class Email {
    private String to;
    private String subject;
    private String body;
    private String pathToAttachment;

    public Email() {
    }

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public Email(String to, String subject, String body, String pathToAttachment) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.pathToAttachment = pathToAttachment;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getPathToAttachment() {
        return pathToAttachment;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPathToAttachment(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }
}

