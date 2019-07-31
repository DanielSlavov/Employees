import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {

    public int employeeId;
    public int projectId;
    public Date startDate;
    public Date endDate;

    public int getEmployeeId() {
        return employeeId;
    }

    public Record(int employeeId, int projectId, Date startDate, Date endDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int result = (int) (this.employeeId ^ (this.employeeId >>> 32));
        result = 31 * result +  (int) (this.projectId ^ (this.projectId >>> 32));
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Record other=(Record)obj;

        return this.employeeId==other.employeeId && this.projectId==other.projectId &&
                this.startDate.equals(other.startDate) &&this.endDate.equals(other.endDate);
    }

    public boolean workedTogether(Record other){
        if(this.projectId!=other.projectId||this.employeeId==other.employeeId){
            return false;
        }
        return other.startDate.compareTo(this.startDate)>=0&&other.startDate.compareTo(this.endDate)<0 ||
                this.startDate.compareTo(other.startDate)>=0&&this.startDate.compareTo(other.endDate)<0;
    }

    @Override
    public String toString() {
        return String.valueOf(this.employeeId);
    }
}
