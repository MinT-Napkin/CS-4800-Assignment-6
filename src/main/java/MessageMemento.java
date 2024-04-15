class MessageMemento {
    private String contentState;
    private double timestampState;

    public MessageMemento(String contentState, double timestampState) {
        this.contentState = contentState;
        this.timestampState = timestampState;
    }

    public String getContentState() {
        return contentState;
    }

    public double getTimestampState() {
        return timestampState;
    }

    public void setContentState(String contentState)
    {
        this.contentState = contentState;
    }

    public void setTimestampState(double timestampState)
    {
        this.timestampState = timestampState;
    }

}