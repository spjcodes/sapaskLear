import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class Connect2Hadoop {



    private FileSystem fs ;

    @Before
    public void initHadoopConnection() {
        System.out.println("获取fileSystem");

        try {
            fs = FileSystem.get(URI.create("hdfs://node01:8020"),new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void put()  {


        try {
            fs.copyToLocalFile(new Path("/test/app.log"), new Path("D:\\hdfs\\app.log"));
            fs.close();
            System.out.println("get 文件成功");
        } catch (IOException e) {
            System.out.println("get 文件失败");
            e.printStackTrace();
        }

    }


    @After
    public void closeInitHadoopCon() {
        System.out.println("关闭fs");
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
