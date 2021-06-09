package P1;

public class Timeslot {
	private Time begin;
	private Time end;
	
	//constructor
	private Timeslot(Time begin, Time end) {
		this.begin = Time.getNewTime(begin.getYear(), begin.getMonth(), begin.getDay(), begin.getHour(), begin.getMinute());
		this.end = Time.getNewTime(end.getYear(), end.getMonth(), end.getDay(), end.getHour(), end.getMinute());
	}
	
	public static Timeslot getNewTimeslot(Time begin, Time end) {
		return new Timeslot(begin, end);
	}
	
	public static Timeslot getNewTimeslot(String begin, String end) {
		return new Timeslot(Time.getNewTime(begin), Time.getNewTime(end));
	}
	
	//methods
	public Time getBegin() {
		return this.begin;
	}

	public Time getEnd() {
		return this.end;
	}

	/**
	 * ���begin�Ƿ�����end
	 * @return ��������򷵻�true�����򷵻�false
	 */
	public boolean islegal() {
		if(this.getBegin().compareto(this.getEnd()) < 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * �������ʱ����Ƿ����غ�
	 * @param timeslot ��һ��ʱ���
	 * @return �з���true,û�з���false
	 */
	public boolean isConflict(Timeslot timeslot) {
		if(this.getBegin().compareto(timeslot.getEnd()) > 0 || this.getEnd().compareto(timeslot.getBegin()) < 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "[begin=" + begin + ", end=" + end + "]";
	}
}


