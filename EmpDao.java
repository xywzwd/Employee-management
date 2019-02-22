package cn.yuwei.dao;

import java.util.List;

import cn.yuwei.entity.Emp;

public interface EmpDao {

	List<Emp> findAllEmp();
	
	Emp findOneEmp(int empno);
	
	int saveEmp(Emp e);
	
	int deleteEmp(int empno);
}

