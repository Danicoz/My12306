package at.danicoz.dao;

import java.sql.SQLException;

import at.danicoz.user.po.User;

public interface UserDao {
	
	//�����û�����,���ص���������1��ʾ�ɹ����룬�񲻳ɹ���
	int save(User user) throws SQLException;
	
	//ɾ���û�
	boolean deleteUsers(int[] UserIdList)throws SQLException;
	
	//ͨ���û���Ϣ�����û�
	User findUser(User user) throws SQLException;
	
	//��֤��¼��Ϣ
	User login(String username,String password)throws SQLException;

	boolean updateUser(User one)throws SQLException;
	
}
