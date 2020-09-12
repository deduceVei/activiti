package activi.com.mapper;

import activi.com.model.TestActivi;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestActiviMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestActivi record);

    int insertSelective(TestActivi record);

    TestActivi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestActivi record);

    int updateByPrimaryKey(TestActivi record);
}