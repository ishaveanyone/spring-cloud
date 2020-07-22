/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-12-19 11:25
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
package com.xupp.testanimal.dao;


import com.xupp.testanimal.model.po.AnimalPO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAnimalDAO extends PagingAndSortingRepository<AnimalPO,String> {

}
