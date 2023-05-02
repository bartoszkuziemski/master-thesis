package com.example.masterthesisspring;

import org.hibernate.dialect.PostgreSQLPGObjectJdbcType;
import org.postgresql.util.PGobject;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration
@ImportRuntimeHints(NativeImageRuntimeHints.HibernateRegistrar.class)
public class NativeImageRuntimeHints {
    static class HibernateRegistrar implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(org.springframework.aot.hint.RuntimeHints hints, ClassLoader classLoader) {
            try {
                hints.reflection()
                        .registerType(PGobject.class,
                                hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INTROSPECT_PUBLIC_METHODS)
                                        .onReachableType(PostgreSQLPGObjectJdbcType.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
