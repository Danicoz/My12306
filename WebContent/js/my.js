
//����һ��һά����
			var pros = new Array();
			pros[0] = "����ʡ";
			pros[1] = "����ʡ";
			pros[2] = "����ʡ";
			//����һ����ά����:
			var citys = new Array();
			citys[0] = new Array();
			citys[1] = new Array();
			citys[2] = new Array();
			citys[0][0] = "������";
			citys[0][1] = "������";
			citys[1][0] = "������";
			citys[1][1] = "������";
			citys[2][0] = "�Ͼ���";
			citys[2][1] = "������";
			
function changeCity(){
				var city = document.getElementById("city");
				var province = document.getElementById("province");
				city.options.length = 0;//��ճ���
				//��ȡѡ���ʡ�ݵ�value
				var pvalue = province.options[province.selectedIndex].value;
				//��citys�Ķ�ά�����б�������optionѡ��
				for(var i=0; i<citys[pvalue].length; i++){
					var text = citys[pvalue][i];
					var value = i;
					city.options[city.options.length] = new Option(text,value);
				}
}