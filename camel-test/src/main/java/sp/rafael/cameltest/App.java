package sp.rafael.cameltest;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
//import org.apache.camel.main.Main;
import org.apache.camel.spring.Main;

/**
 * Hello world!
 *
 */
public class App 
{
    static class AppRoute extends RouteBuilder{
        @Override
        public void configure() throws Exception{
            from("stream:in").to("file:test").to("log:end?level=INFO");
        }
    }


    public static void main( String[] args ) throws Exception{
        Main m = new Main();
        m.addRouteBuilder(new AppRoute());
        m.run();
    }


//    public static void main( String[] args ) throws Exception
//    {
//        CamelContext ctx = new DefaultCamelContext();
//        ctx.addRoutes(new RouteBuilder() {
//            @java.lang.Override
//            public void configure() throws Exception {
//                from("direct:start").to("log:end?level=INFO");
//            }
//        });
//        ctx.start();
//        ctx.createProducerTemplate().sendBody("direct:start","Hello, World!");
//        ctx.stop();
//    }

//    public static void main( String[] args ) throws Exception{
//        Main m = new Main();
//        m.addRouteBuilder(new RouteBuilder() {
//            @java.lang.Override
//            public void configure() throws Exception {
//                from("stream:in")
//                        .setHeader(Exchange.FILE_NAME,constant("test.txt"))
//                        .setHeader(Exchange.CHARSET_NAME,constant("UTF-8"))
//                        .to("log:end?level=INFO").to("file:test");
//            }
//        });
//        m.run();
//    }



}
