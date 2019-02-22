package cn.yuwei.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import cn.yuwei.dao.EmpDao;
import cn.yuwei.daoimpl.EmpDaoImpl;
import cn.yuwei.entity.Emp;

public class Test {

	public static void main(String[] args) {
		//queryEmp();
		//queryAll();
		//saveEmp();
		delEmp();

	}
	
	public static void delEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("please input delete depno:");
		int empno = sc.nextInt();
		EmpDao ed = new EmpDaoImpl();
		int n = ed.deleteEmp(empno);
		if(n>0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
		
	}

	public static void saveEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("please input emp:");
		int empno=sc.nextInt();
		System.out.println("please input emp:");
		String name=sc.next();
		System.out.println("please input emp:");
		String job=sc.next();
		System.out.println("please input emp:");
		int mgr=sc.nextInt();
		System.out.println("please input emp:");
		String Date=sc.next();
		System.out.println("please input emp:");
		double sal=sc.nextDouble();
		System.out.println("please input emp:");
		double comm=sc.nextDouble();
		System.out.println("please input emp:");
		int deptno=sc.nextInt();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d=null;
		try {
			d = df.parse(Date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Emp e=new Emp(empno,name,job,mgr,d,sal,comm,deptno);
		
		EmpDao ed = new EmpDaoImpl();
		int n = ed.saveEmp(e);
		if(n>0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
	}

	public static void queryAll() {
		
		EmpDao ed = new EmpDaoImpl();
		List<Emp> list = ed.findAllEmp();
		System.out.println(list.size());
		if(list.size()>0) {
			for(Emp e:list) {
				System.out.println(e);
			}
		}else {
			System.out.println("fail");
		}
		
	}

	public static void queryEmp(){
		Scanner sc = new Scanner(System.in);
		System.out.println("please input user id:");
		int empno = sc.nextInt();
		
		EmpDao ed = new EmpDaoImpl();
		Emp emp = ed.findOneEmp(empno);
		
		if(emp!=null) {
			System.out.println(emp);
		}else {
			System.out.println("fail");
		}
		
	}

}
