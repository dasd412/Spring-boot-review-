package org.dasd.persistence;

import org.dasd.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
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

    @Test
    public void insert200(){

        for (int i = 1; i <=200 ; i++) {
            Board board=new Board();
            board.setTitle("title : "+i);
            board.setContent("content..."+i+" inserted");
            board.setWriter("user0"+(i%10));
            repo.save(board);
        }


    }

    @Test
    public void testByTitle(){
        repo.findBoardByTitle("title : 177").forEach(board-> System.out.println(board));



    }


    @Test

    public void testByWriter(){
        Collection<Board> results=repo.findByWriter("user00");
        results.forEach(
                board-> System.out.println(board)
        );
    }

    @Test
    public void testByWriterContaining(){
        Collection<Board>results=repo.findByWriterContaining("05");

        results.forEach(board-> System.out.println(board));
    }

    @Test
    public void testByTitleAndBno(){
        Collection<Board>results=repo.findByTitleContainingAndBnoGreaterThan("5",50l);

        results.forEach(board-> System.out.println(board));
    }

    @Test
    public void testBnoOrderBy(){
        Collection<Board>results=repo.findByBnoGreaterThanOrderByBnoDesc(90L);

        results.forEach(board-> System.out.println(board));
    }

    @Test
    public void testBnoOrderByPaging(){
        Pageable paging= PageRequest.of(0,10);//<-첫 번째 페이지에 대해 10건의 데이터 가져오기
        Collection<Board>results=repo.findByBnoGreaterThanOrderByBnoDesc(0L,paging);
        results.forEach(board-> System.out.println(board));
    }

    @Test
    public void testBnoPagingSort(){
        Pageable paging=PageRequest.of(0,10, Sort.Direction.ASC,"bno");//<-첫 번쨰 페이지에 대해 10건의 데이터를 가져오되 bno 오름차순으로 가져온다.

        Page<Board> results=repo.findByBnoGreaterThan(0L,paging);

        System.out.println("Page Size" +results.getSize());
        System.out.println("total pages: "+ results.getTotalPages());
        System.out.println("Total count: "+results.getTotalElements());
        System.out.println("next: "+ results.nextPageable());

        List<Board>list= results.getContent();

        list.forEach(board-> System.out.println(board));


    }
}