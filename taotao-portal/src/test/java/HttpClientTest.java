import java.io.IOException;

import javax.xml.ws.Response;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	
	//get测试
	@Test
	public void getTest() throws IOException {
		// 第一步：把HttpClient使用的jar包添加到工程中。
		// 第二步：创建一个HttpClient的测试类
		// 第三步：创建测试方法。
		// 第四步：创建一个HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 第五步：创建一个HttpGet对象，需要制定一个请求的url
		HttpGet	get = new HttpGet("http://www.baidu.com");
		// 第六步：执行请求。
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			// 第七步：接收返回结果。HttpEntity对象。
			HttpEntity entity = response.getEntity();
			// 第八步：取响应的内容。
			String html = EntityUtils.toString(entity);
			System.out.println(html);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 第九步：关闭response、HttpClient。
			response.close();
			httpClient.close();
		}
	}	
}
