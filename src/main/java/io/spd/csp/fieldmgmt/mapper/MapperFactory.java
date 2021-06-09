package io.spd.csp.fieldmgmt.mapper;

import java.util.function.Function;

public interface MapperFactory<A, B> {

    Function<A, B> a2b();

    Function<B, A> b2a();
}
