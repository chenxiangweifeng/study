package com.example.study.core.service.jpa;

import com.example.study.common.pool.MyThreadExecutor;
import com.example.study.core.model.entity.Person;
import com.example.study.core.repository.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * person 类的多条件查询
 */
@Service
public class JpaPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 批量的执行数据库的插入操作，利用多线程执行
     */
    public void batchSavePerson(int n){
        Long start = System.currentTimeMillis();
        MyThreadExecutor executor = new MyThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i =0; i<n;i++){
            executor.submit(()->{savePerson(6,countDownLatch);});
        }
        try {
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("------------------分割线---------------");
        Long end = System.currentTimeMillis();
        System.out.println("插入"+n+"个数据共用时"+(end-start)+"毫秒");
    }

public void savePerson(int i,CountDownLatch countDownLatch){
    Person p = new Person();
    p.setUserName("alice"+i);
    p.setPhoneNum("18018"+i);
    p.setAddr("beijing"+i);
    p.setAge(i);
    personRepository.save(p);
    try {
        Thread.sleep(1000);
        countDownLatch.countDown();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    /**
     * 测试定时任务1，每30 秒钟打印一次
     */
//    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("每隔10秒执行一次" + sdf.format(new Date()));
    }

    public void batchPerson(List<Person> list) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
        String sql = "insert into tb_person(id,age,user_name) values(:id,:age,:userName)";
        namedParameterJdbcTemplate.batchUpdate(sql, batch);
    }


    public Person findByPersonById(Integer id) {
        Person p = personRepository.findByPersonById(id);
        return p;
    }

    /**
     * 保存一个人
     * @param person
     */
    public void save(Person person) {
        personRepository.save(person);
    }

    //@Transactional
    public void updatePersonBySave(Integer id, String name) {
//   查询出来是非常有必要的，不然很多字段都被置空了。
//      一定要查询出该条记录的全量，再进行save，否则很多字段就被置空了
        Optional<Person> optional = personRepository.findById(id);
        if (optional.isPresent()) {
            Person person = optional.get();
            person.setUserName(name);
            personRepository.save(person);
        }
    }


}
