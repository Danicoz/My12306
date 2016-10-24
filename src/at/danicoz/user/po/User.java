package at.danicoz.user.po;

import java.util.Date;

public class User {
private Integer id;
private String username;
private String password;
private String password2;
private String content;
//Ȩ�ޣ�1Ϊ����2Ϊ��ͨ�û���
private String rule;
private String realname;
private String sex;
private City city;
//֤������
private CertType certType;
private String cert;
private Date birthday;
private UserType userType;
//�û�״̬��0Ϊ��Ч��1Ϊ��Ч��
private String status;
private String loginIp;
private String imagePath;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPassword2() {
	return password2;
}
public void setPassword2(String password2) {
	this.password2 = password2;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getRule() {
	return rule;
}
public void setRule(String rule) {
	this.rule = rule;
}
public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public City getCity() {
	return city;
}
public void setCity(City city) {
	this.city = city;
}
public CertType getCertType() {
	return certType;
}
public void setCertType(CertType certType) {
	this.certType = certType;
}
public String getCert() {
	return cert;
}
public void setCert(String cert) {
	this.cert = cert;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public UserType getUserType() {
	return userType;
}
public void setUserType(UserType userType) {
	this.userType = userType;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getLoginIp() {
	return loginIp;
}
public void setLoginIp(String loginIp) {
	this.loginIp = loginIp;
}
public String getImagePath() {
	return imagePath;
}
public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password="
			+ password + ", password2=" + password2 + ", content=" + content
			+ ", rule=" + rule + ", realname=" + realname + ", sex=" + sex
			+ ", city=" + city + ", certType=" + certType + ", cert=" + cert
			+ ", birthday=" + birthday + ", userType=" + userType + ", status="
			+ status + ", loginIp=" + loginIp + ", imagePath=" + imagePath
			+ "]";
}

private boolean autoLogin;
private String code;
public boolean isAutoLogin() {
	return autoLogin;
}
public void setAutoLogin(boolean autoLogin) {
	this.autoLogin = autoLogin;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}


}
