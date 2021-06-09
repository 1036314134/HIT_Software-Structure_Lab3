package P2.CourseSchedule;

import java.io.IOException;
import java.util.Scanner;

import P1.PlanningEntry;


public class CourseScheduleAPP {
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("��ѧ�α����");
		CourseScheduleAPP.print();
		in.close();
	}
	
	public static void print() {
		CourseEntry courses = CourseEntry.getCourseEntry();
		Scanner in = new Scanner(System.in);

		System.out.println("��ѡ����Ҫִ�еĲ���");
		System.out.println("1.����һ����ʦ");
		System.out.println("2.�Ƴ�һ����ʦ");
		System.out.println("3.����һ�����");
		System.out.println("4.�Ƴ�һ�����");
		System.out.println("5.����һ�ڿ�");
		System.out.println("6.ȡ��һ�ڿ�");
		System.out.println("7.��ĳ����ʦ�ſ�");
		System.out.println("8.�ı�ĳ�ڿεĽ���");
		System.out.println("9.����ĳ�ڿ�");
		System.out.println("10.����ĳ�ڿ�");
		System.out.println("11.��ȡĳ�ڿε�״̬");
		System.out.println("12.�鿴��ͻ");
		System.out.println("13.�鿴ĳ����ʦ�ϵĿ�");
		System.out.println("14.չʾ�α�");
		System.out.println("15.����");

		boolean flag = true;

		while (flag) {
			switch (in.nextInt()) {
			case 1:
				System.out.println("��������ʦ������, ���֤��, �Ա�, ְλ��");
				if (courses.addTeacher(in.next(), in.next(), in.next(), in.next())) {
					System.out.println("��ӳɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 2:
				System.out.println("������Ҫɾ������ʦ������");
				String name = in.next();
				if(courses.checkResourceUsing(name)) {
					System.out.println("��ʦ�ѱ�ָ��");
					break;
				}
				if (courses.removeResource(name)) {
					System.out.println("��ӳɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 3:
				System.out.println("�����Ҫ���ӵĽ��ҵ�����");
				if (courses.addLocation(in.next())) {
					System.out.println("������");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 4:
				System.out.println("�����Ҫɾ���Ľ��ҵ�����");
				String locationname = in.next();
				if(courses.checkLocationUsing(locationname)) {
					System.out.println("��Դ�ѱ�ָ��");
					break;
				}
				if (courses.removeLocation(locationname)) {
					System.out.println("ɾ���ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 5:
				System.out.println("�����Ҫ���ӵĿε���Ϣ");
				System.out.println("(�γ��� ������ ��ʼʱ�� �¿�ʱ��)");
				if(courses.addPlan(in.next(), in.next(), in.next() + " " + in.next(), in.next() + " " + in.next())) {
					System.out.println("��ӳɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 6:
				System.out.println("�����Ҫȡ���Ŀε���Ϣ");
				String coursename = in.next();
				if(courses.cancel(coursename)) {
					System.out.println("ȡ���ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 7:
				System.out.println("��������ʦ�� �γ���");
				if(courses.allocateResource(in.next(), in.next())) {
					System.out.println("����ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 8:
				System.out.println("������γ��� ������");
				if(courses.changeclassroom(in.next(), in.next())) {
					System.out.println("�ı�ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 9:
				System.out.println("������γ���");
				if(courses.getPlan(in.next()).start()) {
					System.out.println("�����ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 10:
				System.out.println("������γ���");
				if(courses.getPlan(in.next()).complete()) {
					System.out.println("�����ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 11:
				System.out.println("������γ���");
				PlanningEntry<Teacher> course = courses.getPlan(in.next());
				if(course == null){
					System.out.println("δ��ѯ���ÿγ�");
				}else {
					System.out.println("�ÿγ�״̬Ϊ��" + course.getState().getName());
				}
				break;
			case 12:
				boolean f = false;
				System.out.println("��ѡ������ҳ�ͻ�Ĳ���,1����2");
				int k = in.nextInt();
				if(k == 1) {
					if(courses.checkLocationConflict(true)) {
						System.out.println("�����г�ͻ");
						f = true;
					}
				}else if(k == 2) {
					if(courses.checkLocationConflict(false)) {
						System.out.println("�����г�ͻ");
						f = true;
					}
				}else {
					System.out.println("�������");
					break;
				}
				if(courses.checkResourceConflict()) {
					System.out.println("��ʦ�г�ͻ");
					f = true;
				}
				if(!f) {
					System.out.println("û�г�ͻ");
				}
				break;
			case 13:
				System.out.println("��������ʦ����");
				String teachername = in.next();
				System.out.println(courses.getResourceEntries(teachername).toString());
				
				System.out.println("��ѡ������һ�ڿ�");
				String coursename2 = in.next();
				if(courses.getPreEntry(teachername, coursename2) == null) {
					System.out.println("û��ǰ��γ�");
				}else {
					System.out.println("ǰ��γ�Ϊ��" + courses.getPreEntry(teachername, coursename2).toString());
				}
				break;
			case 14:
				System.out.println("�������ǰʱ��ͽ���");
				courses.showBoard(in.next() + " " + in.next(), in.next());
				break;
			case 15:
				flag = false;
				break;
			default:
				System.out.println("�����������������");
			}
		}

		in.close();
	}

}
