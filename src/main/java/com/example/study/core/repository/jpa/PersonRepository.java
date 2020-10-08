package com.example.study.core.repository.jpa;

import com.example.study.core.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * jpa的多条件查询
 *  JpaRepository 继承了PagingAndSortingRepositoy, PagingAndSortingRepositoy又继承了CrudRepository,
 *  所以JpaRepositoy 同时继承了这两个接口，那么就具备了基本的增删改查以及排序与分页功能
 *  说白了，JPA做的就是一个映射工作，可以方便我们更加快捷的操作数据库！
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,String>, JpaSpecificationExecutor<Person> {

    /**
     * @Query 自定义查询
     * @param id
     * @param age
     * @param userName
     */
    @Modifying
    @Transactional
    @Query(value = "insert into tb_person(id,age,user_name) values(:id,:age,:userName)",nativeQuery = true)
    void save2(@Param("id") Integer id, @Param("age") Integer age, @Param("userName") String userName);

    @Query("select p from Person p where p.id=:id")
    Person findByPersonById(@Param("id") String id);

    Person findByUserName(String userName);

    /**
     * 先写字段的名字然后再写操作的关键词如in greaterthan lessThanEqual等等这样的判断条件
     * 约定大于配置，大家一起都强制的遵守这个约定，那么就可以省去非常多的麻烦；缺点：方法名可能会比较长
     *
     * @param name
     * @param age
     * @return
     */
    List<Person> findByUserNameStartingWithAndAgeGreaterThanEqual(String name, Integer age);

    /**
     * 可以使用符合嵌套查询，也即是子查询 jql
     * 符合查询，子查询
     * @return
     */
    @Query("select p from Person p where id = (select max(id) from Person p2)")
    Person findMaxIdPerson();

    /**
     * 使用问号索引占位符，更加的快捷方便
     * ?1,?2 注入索引参数使用起来也挺方便的
     * @param userName
     * @param age
     * @return
     */
    @Query("select o from Person o where o.userName=?1 and o.age=?2")
    List<Person> findByParam1(String userName, Integer age);


}
