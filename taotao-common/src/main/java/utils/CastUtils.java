package utils;
/**
 * 值类型转换
 * @author admin
 *
 */
public class CastUtils {
	public static Long[] ConvertToLong(String[] strArray) {
		Long[] longArray  =  new Long[strArray.length];
		for (int i = 0 ;i < longArray.length ; i++) {
			longArray[i] = Long.parseLong(strArray[i]);
		}
		return longArray;
	}
}
