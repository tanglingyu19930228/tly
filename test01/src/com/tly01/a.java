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
	 *  为什么两次a.hashCode的值不一样?
	 *  是否与final关键字修饰的引用类型变量(变量的所引用的内存地址不变而变量所指的内存地址里面的值可以变化矛盾?)
	 *  不矛盾(String所指向堆内存地确实不能变但是所指向jvm常量池地址可以变)
	 *  
	 */
}
