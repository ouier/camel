package sp.rafael.cameltest;


import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import sp.rafael.cameltest.App;

import static junit.framework.Assert.*;


/**
 * Unit test for simple App.
 */
public class AppTest extends CamelTestSupport
{

    static PipedInputStream in;
    static PipedOutputStream out;
    static InputStream originalIn;

    static final String DIR="test";


    @BeforeClass()
    public static void setup() throws IOException{
        originalIn = System.in;
        out = new PipedOutputStream();
        in = new PipedInputStream(out);
        System.setIn(in);
        FileUtils.deleteDirectory(new File(DIR));
    }

    @Test
    public void testAppRoute() throws Exception {
        out.write("This is a test message! \n".getBytes());
        Thread.sleep(2000);
        assertTrue(new File(DIR).listFiles().length==1);
    }

    @After
    public void tearDown() throws IOException{
        out.close();
        System.setIn(originalIn);
    }

    @Override
    public boolean isCreateCamelContextPerClass(){
        return false;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new App.AppRoute();
    }
}
