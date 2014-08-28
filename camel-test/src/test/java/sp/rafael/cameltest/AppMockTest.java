package sp.rafael.cameltest;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Rafael on 27/08/14.
 */
public class AppMockTest extends CamelTestSupport {

    @Override
    public String isMockEndpoints() {
        return "*";
    }

    @Test
    public void testAppRoute() throws Exception{
        final String testMessage = "This is a message";
        getMockEndpoint("mock:file:test").expectedBodiesReceived(testMessage);
        template.sendBody("direct:in",testMessage);
        assertMockEndpointsSatisfied();
    }

    @Before
    public void replaceStreamIn() throws Exception{
        context.getRouteDefinitions().get(0).adviceWith(context,new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:in");
            }
        });
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new App.AppRoute();
    }
}
