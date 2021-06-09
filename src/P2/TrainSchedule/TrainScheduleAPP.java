package P2.TrainSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import P1.PlanningEntry;

public class TrainScheduleAPP {
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("�������ι���APP");
		TrainScheduleAPP.print();
		in.close();
	}
	
	public static void print() throws IOException {
		TrainEntry lines = TrainEntry.getTrainEntry();
		Scanner in = new Scanner(System.in);
		
		System.out.println("��ѡ����Ҫִ�еĲ���");
		System.out.println("1.����һ������");
		System.out.println("2.�Ƴ�һ������");
		System.out.println("3.����һ��վ��");
		System.out.println("4.�Ƴ�һ��վ��");
		System.out.println("5.����һ��·��");
		System.out.println("6.ȡ��ĳ��·��");
		System.out.println("7.��ĳ�鳵��ȥĳ��·��");
		System.out.println("8.����ĳ��·��");
		System.out.println("9.����ĳ��·��");
		System.out.println("10.����ĳ��·��");
		System.out.println("11.�鿴ĳ��·�ߵ�״̬");
		System.out.println("12.�鿴��ͻ");
		System.out.println("13.�鿴ĳ�ڳ����·��");
		System.out.println("14.��ʾ�ƻ���");
		System.out.println("15.�뿪");
		
		boolean flag = true;
		
		while(flag) {
			switch(in.nextInt()) {
			case 1:
				System.out.println("��������복�����Ϣ");
				System.out.println("(���� ���� ��λ�� �������)");
				
				if (lines.addTrain(in.next(), in.next(), in.nextInt(), in.nextInt())) {
					System.out.println("��ӳɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 2:
				System.out.println("������Ҫɾ�����������");
				String name = in.next();
				if(lines.checkResourceUsing(name)) {
					System.out.println("�����ѱ�ָ��");
					break;
				}
				if (lines.removeResource(name)) {
					System.out.println("��ӳɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 3:
				System.out.println("������Ҫ���ӵ�վ�������");
				if (lines.addLocation(in.next())) {
					System.out.println("��ӳɹ�");
				}else {
					System.out.println("ʧ��");
				}
					
				break;
			case 4:
				System.out.println("�����Ҫ�Ƴ���վ�������");
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
				System.out.println("�����Ҫ���ӵ�·�ߵ���Ϣ");
				System.out.println("����·���ƣ�");
				String linename = in.next();
				System.out.println("��·��һ���м�վ�����������վ���յ�վ��");
				int max = in.nextInt();
				List<String> locations = new ArrayList<>();
				List<String> times = new ArrayList<>();
				System.out.println("����������ÿ��վ������");
				for(int i = 0; i < max; i++) {
					locations.add(in.next());
				}
				System.out.println("����������ÿ����վ��֮��ʱ���");
				for(int i = 0; i < (max-1)*2; i++) {
					times.add(in.next() + " " + in.next());
				}
				if (lines.addPlan(linename, locations, times)) {
					System.out.println("���ӳɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 6:
				System.out.println("�����Ҫȡ����·������");
				if (lines.cancel(in.next())) {
					System.out.println("ȡ���ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 7:
				System.out.println("������������ƺ�·������");
				if (lines.allocateResource(in.next(), in.next())) {
					System.out.println("�ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 8:
				System.out.println("�����Ҫ������·������");
				if (lines.start(in.next())) {
					System.out.println("ȡ���ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 9:
				System.out.println("�����Ҫ������·������");
				if (lines.block(in.next())) {
					System.out.println("ȡ���ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 10:
				System.out.println("�����Ҫ������·������");
				if (lines.complete(in.next())) {
					System.out.println("ȡ���ɹ�");
				}else {
					System.out.println("ʧ��");
				}
				break;
			case 11:
				System.out.println("�����Ҫ��ѯ��·������");
				PlanningEntry<Train> plan = lines.getPlan(in.next());
				if (plan == null) {
					System.out.println("δ��ѯ����·��");
				}else {
					System.out.println("��·��״̬Ϊ:" + plan.getState().getName());
				}
				break;
			case 12:
				boolean f = false;
				System.out.println("��ѡ���鳵���ͻ�Ĳ���,1����2");
				int k = in.nextInt();
				if(k == 1) {
					if(lines.checkLocationConflict(true)) {
						System.out.println("�����г�ͻ");
						f = true;
					}
				}else if(k == 2) {
					if(lines.checkLocationConflict(false)) {
						System.out.println("�����г�ͻ");
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
			case 13:
				System.out.println("�����복������");
				String trainname = in.next();
				System.out.println(lines.getResourceEntries(trainname).toString());
				
				System.out.println("��ѡ������һ�����");
				String coursename2 = in.next();
				if(lines.getPreEntry(trainname, coursename2) == null) {
					System.out.println("û��ǰ����");
				}else {
					System.out.println("ǰ����Ϊ��" + lines.getPreEntry(trainname, coursename2).toString());
				}
				break;
			case 14:
				System.out.println("�������ǰʱ���վ��");
				lines.showBoard(in.next() + " " + in.next(), in.next());
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
