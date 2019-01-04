import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.cj.util.Util;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.taotao.pojo.TbItem;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;
import com.taotao.service.impl.ItemServiceImpl;

import utils.CastUtils;
import utils.IDUtils;
public class itemServiceTest {
	@Test
	public void testItemService() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		ItemService itemService = applicationContext.getBean(ItemService.class);
		TbItem item = new TbItem();
		item.setCid(10000L);
		item.setBarcode("");
		item.setImage("");
		item.setNum(10);
		item.setPrice(10L);
		item.setTitle("ceshi ");
		String desc = "neiRONG";
		TaotaoResult result = itemService.addItem(item, desc,"");
		System.out.println(result);
		
	}
	@Test
	public void CommonTest() {			
	}
}
