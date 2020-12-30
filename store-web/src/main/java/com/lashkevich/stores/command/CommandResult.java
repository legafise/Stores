package com.lashkevich.stores.command;

public class CommandResult {
    public enum ResponseType {
        FORWARD,
        REDIRECT
    }

    private ResponseType responseType;
    private String page;

    public CommandResult() {
    }

    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandResult)) return false;

        CommandResult that = (CommandResult) o;
        if (responseType != that.responseType) return false;
        return that.page != null ? page.equals(that.page) : page == null;
    }

    @Override
    public int hashCode() {
        int hash = responseType != null ? responseType.hashCode() : 0;
        hash = 31 * hash + (page != null ? page.hashCode() : 0);
        return hash;
    }
}
