package models;

public class Attendance {

	private long studentId;
	private boolean attended;

	public Attendance(long studentId, boolean attended) {
		this.studentId = studentId;
		this.attended = attended;
	}


	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public boolean hasAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	@Override
	public String toString() {
		return "Attendance{" +
				"studentId=" + studentId +
				", attended=" + attended +
				'}';
	}
}
