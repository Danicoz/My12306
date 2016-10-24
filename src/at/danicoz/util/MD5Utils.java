package at.danicoz.util;

import java.security.MessageDigest;

public class MD5Utils {

	public final static String md5(String s){
		
		//���ݿ� USER ���������ֶ�ʹ�õ��ַ�
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		try{
			byte[] btinput = s.getBytes();
			//ʹ�� Java �� MessageDigest �ഴ��һ�� MD5 �Ķ���
			MessageDigest mdinst = MessageDigest.getInstance("MD5");
			mdinst.update(btinput);
			
			//�����µ� MD5 �ֽ�����
			byte[] md = mdinst.digest();
			
			int j = md.length;
			char str[] = new char[j*2];
			int k = 0;
			
			//���� MD5 �㷨�ļ��㲽��
			for(int i = 0;i<j;i++){
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0>>>4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			
			return new String(str);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
}
