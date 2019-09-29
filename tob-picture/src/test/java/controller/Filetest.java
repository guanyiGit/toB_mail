package controller;


import com.TobPictureApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TobPictureApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Filetest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    /*
        UPDATE t_media_info m
            SET m.url = replace(
                    m.url,
                    SUBSTRING_INDEX(m.url,"/",3),
                    "http://192.168.0.69:22222"
            ) ,
            m.thumbnail_url = replace(
                    m.thumbnail_url,
                    SUBSTRING_INDEX(m.thumbnail_url,"/",3),
                    "http://192.168.0.69:22222"
            )
        WHERE m.url LIKE 'https%';
     */

    @Test
    public void whenUploadSuccess() throws Exception {
        String id = "13";
        String type = "2";
        String dir = "C:/Users/jiaju/Desktop/iot/code";

        File file = new File(dir);
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            Arrays.stream(listFiles)
                    .filter(File::isFile)
                    .forEach(x -> {
                        try (FileInputStream fis = new FileInputStream(dir + "/" + x.getName())) {
                            String url = "/biz/fileManager/uploadFileBatch";
                            mockMvc.perform(MockMvcRequestBuilders
                                    .fileUpload(url)
                                    .file(new MockMultipartFile("filePramName", "test.jpg", "multipart/form-data", fis))
                                    .param("id", id)
                                    .param("type", type))
                                    .andExpect(status().isOk());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        }

    }


}
