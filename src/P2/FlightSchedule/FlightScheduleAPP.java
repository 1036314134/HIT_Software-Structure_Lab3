package P2.FlightSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import P1.PlanningEntry;

public class FlightScheduleAPP {
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("�������APP");
		FlightScheduleAPP.print();
		in.close();
	}
	
	
	public static void print() throws IOException {
		FlightEntry lines = FlightEntry.getFlightEntry();
		Scanner in = new Scanner(System.in);

		System.out.println("�������Ҫ��ȡ���ļ�������");
		System.out.println("���磺FlightSchedule_1.txt��FlightSchedule_2.txt��FlightSchedule_3.txt��FlightSchedule_4.txt��FlightSchedule_5.txt");

		while ((lines = FlightReader.fileReader(in.next())) == null) {
			System.out.println("������ļ����д�����Ϣ,����������");
		}

		System.out.println("��ѡ����Ҫִ�еĲ���");
		System.out.println("0.����һ������תվ�ĺ���");
		System.out.println("1.����һ�ܷɻ�");
		System.out.println("2.�Ƴ�һ�ܷɻ�");
		System.out.println("3.����һ������");
		System.out.println("4.�Ƴ�һ������");
		System.out.println("5.����һ�κ���");
		System.out.println("6.ȡ��ĳ������");
		System.out.println("7.��ĳ���ɻ�ȥ��ĳ������");
		System.out.println("8.����ĳ������");
		System.out.println("9.����ĳ������");
		System.out.println("10.�鿴ĳ�����̵�״̬");
		System.out.println("11.�鿴��ͻ");
		System.out.println("12.�鿴ĳ���ɻ��ĺ���");
		System.out.println("13.��ʾ�ƻ���");
		System.out.println("14.�뿪");

		boolean flag = true;

		while (flag) {
			switch (in.nextInt()) {
				case 0:
					System.out.println("�����Ҫ���ӵĺ������Ϣ");
					System.out.println("�ú������ƣ�");
					String linename = in.next();
					List<String> locations = new ArrayList<>();
					List<String> times = new ArrayList<>();
					System.out.println("����������ÿ������������");
					for(int i = 0; i < 3; i++) {
						locations.add(in.next());
					}
					System.out.println("����������ÿ��������֮��ʱ���");
					for(int i = 0; i < 4; i++) {
						times.add(in.next() + " " + in.next());
					}
					if (lines.addPlan(linename, locations, times)) {
						System.out.println("���ӳɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 1:
					System.out.println("�������Ҫ��ӵķɻ�����Ϣ");
					System.out.println("�ɻ����� �ɻ����� �ɻ���λ�� �ɻ�����");
					if (lines.addPlane(in.next(), in.next(), in.nextInt(), in.nextDouble())) {
						System.out.println("��ӳɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 2:
					System.out.println("�����Ҫɾ���ķɻ�������");
					String name = in.next();
					if(lines.checkResourceUsing(name)) {
						System.out.println("�ɻ��ѱ�ָ��");
						break;
					}
					if (lines.removeResource(name)) {
						System.out.println("ɾ���ɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 3:
					System.out.println("�����Ҫ���ӵĻ���������");
					if (lines.addLocation(in.next())) {
						System.out.println("���ӳɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 4:
					System.out.println("�����Ҫ�Ƴ��Ļ���������");
					String locationname = in.next();
					if(lines.checkLocationUsing(locationname)) {
						System.out.println("��Դ�ѱ�ָ��");
						break;
					}
					if (lines.removeLocation(locationname)) {
						System.out.println("ɾ���ɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 5:
					System.out.println("�����Ҫ���ӵĺ��̵���Ϣ");
					System.out.println("(�������� ʼ������ ������� ����ʱ�� ����ʱ��)");
					if (lines.addPlan(in.next(), in.next(), in.next(), in.next() + " " + in.next(),in.next() + " " + in.next())) {
						System.out.println("���ӳɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 6:
					System.out.println("�����Ҫɾ���ĺ�������");
					if (lines.cancel(in.next())) {
						System.out.println("ɾ���ɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 7:
					System.out.println("������ɻ����ƺͺ�������");
					if (lines.allocateResource(in.next(), in.next())) {
						System.out.println("�ɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 8:
					System.out.println("�����뺽����");
					if(lines.getPlan(in.next()).start()) {
						System.out.println("�����ɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 9:
					System.out.println("�����뺽����");
					if(lines.getPlan(in.next()).complete()) {
						System.out.println("�����ɹ�");
					}else {
						System.out.println("ʧ��");
					}
					break;
				case 10:
					System.out.println("�����Ҫ��ѯ�ĺ�������");
					PlanningEntry<Plane> plan = lines.getPlan(in.next());
					if (plan == null) {
						System.out.println("δ��ѯ���ú���");
					}else {
						System.out.println("�ú���״̬Ϊ:" + plan.getState().getName());
					}
					break;
				case 11:
					boolean f = false;
					System.out.println("��ѡ����ɻ���ͻ�Ĳ���,1����2");
					int k = in.nextInt();
					if(k == 1) {
						if(lines.checkLocationConflict(true)) {
							System.out.println("�ɻ��г�ͻ");
							f = true;
						}
					}else if(k == 2) {
						if(lines.checkLocationConflict(false)) {
							System.out.println("�ɻ��г�ͻ");
							f = true;
						}
					}else {
						System.out.println("�������");
						break;
					}
					if(lines.checkResourceConflict()) {
						System.out.println("�����г�ͻ");
						f = true;
					}
					if(!f) {
						System.out.println("û�г�ͻ");
					}
					break;
				case 12:
					System.out.println("������ɻ�����");
					String planename = in.next();
					System.out.println(lines.getResourceEntries(planename).toString());
					
					System.out.println("��ѡ������һ������");
					String linename2 = in.next();
					if(lines.getPreEntry(planename, linename2) == null) {
						System.out.println("û��ǰ�򺽰�");
					}else {
						System.out.println("ǰ�򺽰�Ϊ��" + lines.getPreEntry(planename, linename2).toString());
					}
					break;
				case 13:
					System.out.println("�������ǰʱ��ͻ���");
					lines.showBoard(in.next() + " " + in.next(), in.next());
					break;
				case 14:
					flag = false;
					break;
				default:
					System.out.println("���������������");
			}
		}

		in.close();
	}
}
