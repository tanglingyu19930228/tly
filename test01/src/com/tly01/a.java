package sb;
/**
 * String
 * @author Administrator
 *
 */
public class a {
	public static void main(String[] args) {
		String a = "sb";
		System.out.println(a.hashCode());
		a="ss";
		System.out.println(a.hashCode());
	}
	/**
	 *  Ϊʲô����a.hashCode��ֵ��һ��?
	 *  �Ƿ���final�ؼ������ε��������ͱ���(�����������õ��ڴ��ַ�����������ָ���ڴ��ַ�����ֵ���Ա仯ì��?)
	 *  ��ì��(String��ָ����ڴ��ȷʵ���ܱ䵫����ָ��jvm�����ص�ַ���Ա�)
	 *  
	 */
}
