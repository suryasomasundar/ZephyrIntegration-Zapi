package zapi.DOM;

public class TestStespDOM {
	int id;
    int orderId;
    String step;
    String result;
    long executionId;

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getStep() {
        return step;
    }

    public String getResult() {
        return result;
    }

    public long getExecutionId() {
        return executionId;
    }

    public void setExecutionId(long executionId) {
        this.executionId = executionId;
    }

    public String toString() {
        return "ID : " + this.getId() + "\tSTEP : " + this.getStep() + "\tExecution Id : " + this.executionId;
    }

}
