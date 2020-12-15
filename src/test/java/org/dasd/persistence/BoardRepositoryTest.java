package org.dasd.persistence;

import org.dasd.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository repo;

    @Test
    public void inspect(){
        //클래스 이름 파악
        Class<?>clz=repo.getClass();
        System.out.println(clz.getName());

        //인터페이스 목록 파악
        Class<?>[]interfaces=clz.getInterfaces();
        Stream.of(interfaces).forEach(inter-> System.out.println(inter.getName()));

        //클래스의 부모 파악

        Class<?>superClass=clz.getSuperclass();

        System.out.println(superClass.getName());

    }

    @Test
    public void insert(){
        Board board=new Board();
        board.setTitle("title");
        board.setContent("content");
        board.setWriter("user0");

        repo.save(board);
    }

    @Test
    public void read(){

        repo.findById(1l).ifPresent((board -> System.out.println(board)));

    }

    @Test
    public void update(){

        System.out.println("read first...");

        Board board=repo.findByBno(1l);

        System.out.println("update title...");

        board.setTitle("updated title");

        System.out.println("call save()...");

        repo.save(board);



    }

    @Test
    public void delete(){
        System.out.println("delete entity");


        Board board=repo.findByBno(1L);
        repo.delete(board);

    }
}