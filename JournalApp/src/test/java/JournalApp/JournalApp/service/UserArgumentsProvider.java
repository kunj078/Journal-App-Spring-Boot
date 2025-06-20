package JournalApp.JournalApp.service;

import JournalApp.JournalApp.Entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(User.builder()
                        .username("seven")
                        .password("seven")
                        .build()),

                Arguments.of(User.builder()
                        .username("eight")
                        .password("eight")
                        .build())
        );
    }
}
