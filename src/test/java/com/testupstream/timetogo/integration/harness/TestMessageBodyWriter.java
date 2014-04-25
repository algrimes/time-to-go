package com.testupstream.timetogo.integration.harness;

import com.sun.jersey.spi.service.ServiceFinder;
import io.dropwizard.views.View;
import io.dropwizard.views.ViewRenderException;
import io.dropwizard.views.ViewRenderer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Locale;

public class TestMessageBodyWriter implements MessageBodyWriter<View> {

    private Iterable<ViewRenderer> renderers;
    private static final String MISSING_TEMPLATE_MSG =
            "<html>" +
                    "<head><title>Missing Template</title></head>" +
                    "<body><h1>Missing Template</h1><p>{0}</p></body>" +
                    "</html>";

    public TestMessageBodyWriter() {
        renderers = ServiceFinder.find(ViewRenderer.class);
    }

    @Override
    public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return View.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(View t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(View t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try {
            for (ViewRenderer renderer : renderers) {
                if (renderer.isRenderable(t)) {
                    renderer.render(t, Locale.ENGLISH, entityStream);
                    return;
                }
            }
            throw new ViewRenderException("Unable to find a renderer for " + t.getTemplateName());
        } catch (FileNotFoundException e) {
            final String msg = MessageFormat.format(MISSING_TEMPLATE_MSG, e.getMessage());
            throw new WebApplicationException(Response.serverError()
                    .type(MediaType.TEXT_HTML_TYPE)
                    .entity(msg)
                    .build());
        }
    }
}
