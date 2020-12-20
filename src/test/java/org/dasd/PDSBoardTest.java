package org.dasd;

import lombok.extern.java.Log;
import org.dasd.domain.PDSBoard;
import org.dasd.domain.PDSFile;
import org.dasd.persistence.PDSBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTest {

    @Autowired
    private PDSBoardRepository repo;

    @Test
    public void testInsertPDS(){

        PDSBoard board=new PDSBoard();
        board.setPname("document");

        PDSFile file1=new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2=new PDSFile();
        file2.setPdsfile("file2.doc");

        board.setFiles(Arrays.asList(file1,file2));

        log.info("try to save pds");

        repo.save(board);

    }

    @Transactional//<-update나 delete를 위해선 반드시 사용해야 함.
    @Test
    public void updateFileName(){
        Long fno=1L;
        String newName="updatedFile1.doc";

        int count=repo.updatePDSFile(fno,newName);

        log.info("update count : "+count);


    }


    @Transactional
    @Test
    public void testUpdateFileName2(){

        String newName="updatedFile2.doc";

        Optional<PDSBoard> result=repo.findById(2L);

        result.ifPresent(pds->{
                    log.info("trying to update data..");


                    PDSFile target=new PDSFile();
                    target.setFno(2L);

                    target.setPdsfile(newName);
                    int index=pds.getFiles().indexOf(target);

                    if(index>-1){

                        List<PDSFile>list=pds.getFiles();
                        list.remove(index);
                        list.add(target);
                        pds.setFiles(list);
                    }

                    repo.save(pds);

                }

                );

    }

    @Transactional
    @Test
    public void testDeleteFile(){

        Long fno=2L;

        int count=repo.deletePDSFile(fno);

        log.info("Dleleted pdsfile : "+count);
    }

    @Test
    public void insertDummies(){

        List<PDSBoard>list=new ArrayList<>();

        IntStream.range(1,100).forEach(i->{

            PDSBoard pds=new PDSBoard();
            pds.setPname("board : "+i);

            PDSFile file1 =new PDSFile();
            file1.setPdsfile("file1.doc");

            PDSFile file2 =new PDSFile();
            file2.setPdsfile("file2.doc");

            pds.setFiles(Arrays.asList(file1,file2));

            log.info("try to save pds");

            list.add(pds);

        });

        repo.saveAll(list);

    }

    @Test
    public void testSummary(){
        repo.getSummary().forEach(arr->
                log.info(Arrays.toString(arr)));
    }
}

