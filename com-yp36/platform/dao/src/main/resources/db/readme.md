mybatis采用的是多数据源配置
cf_dataSource 系统的主数据源
bigdata_dataSource 大数据的数据源（预留同步大数据）

示例：
@DataSource(name = DataSource.BIGDATA_DATASOURCE)
   public List<TagFilterRel> selectByCondition(TagFilterRel rel) {
           return tagFilterRelMapper.selectByCondition(rel);
       }

mybatis数据源及事务配置见applicationContext-dao.xml
