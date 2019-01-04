
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
public class PageHelperTest {
	@Test
public  void  PageHeler(){
		
		//1、获得mapper代理对象
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//2、设置分页
		PageHelper.startPage(1, 30);
		//3、执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = null;
		try {
			list = itemMapper.selectByExample(example);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} 
		//4、取分页后结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("total:" + total);
		int pages = pageInfo.getPages();
		System.out.println("pages:" + pages);
		int pageSize = pageInfo.getPageSize();
		System.out.println("pageSize:" + pageSize);
}
}
