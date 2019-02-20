package com.sample;

import io.netty.bootstrap.Bootstrap;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.JdkLoggerFactory;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.RecomputeFieldValue.Kind;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

import reactor.netty.tcp.SslProvider;
import reactor.util.Loggers;

@TargetClass(io.netty.util.internal.logging.InternalLoggerFactory.class)
final class Target_io_netty_util_internal_logging_InternalLoggerFactory {

    @Substitute
    private static InternalLoggerFactory newDefaultFactory(String name) {
        return JdkLoggerFactory.INSTANCE;
    }

}

@TargetClass(reactor.netty.tcp.SslProvider.class)
final class Target_reactor_netty_tcp_SslProvider {

    @Substitute
    private static SslProvider findSslSupport(Bootstrap bootstrap) {
        return null;
    }

    @Substitute
    private static SslProvider defaultClientProvider() {
        return null;
    }

}

@TargetClass(reactor.util.Loggers.class)
final class Target_reactor_util_Loggers {

    @Substitute
    private static void resetLoggerFactory() {
        Loggers.useJdkLoggers();
    }

}

@TargetClass(className = "io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess")
final class Target_io_netty_util_internal_shaded_org_jctools_util_UnsafeRefArrayAccess {

    @Alias
    @RecomputeFieldValue(kind = Kind.ArrayIndexShift, declClass = Object[].class) //
    public static int REF_ELEMENT_SHIFT;

}

public class NettySubstitutions {

}