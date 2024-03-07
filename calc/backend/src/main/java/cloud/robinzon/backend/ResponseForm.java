package cloud.robinzon.backend;

public class ResponseForm {

    private String className;
    private String functionName;
    private String status;
    private String text;

    public ResponseForm(String className) {
        this.className = className;
    }

    public void function(String functionName) {
        this.functionName = functionName;
        this.log();
    }

    public ResponseForm error(String text) {
        this.status = "ERROR";
        this.text = text;
        this.log();
        return this;
    }

    public ResponseForm success(String text) {
        this.status = "SUCCESS";
        this.text = text;
        this.log();
        return this;
    }

    public void log() {
        if (this.className == null)
            throw new IllegalArgumentException("[ResponseForm] className cannot be null");
        if (this.functionName == null)
            throw new IllegalArgumentException("[ResponseForm][" + this.className + "] functionName cannot be null");

        StringBuilder sb = new StringBuilder("[");
        sb.append(this.className).append("][").append(this.functionName).append("]");
        if (this.status == null) {
            System.out.println(sb.toString());
            return;
        } else
            sb.append("[").append(this.status).append("]");
        if (this.text == null) {
            System.out.println(sb.toString());
            return;
        } else
            System.out.println(sb.append(" ").append(this.text));

        return;
    }

}
