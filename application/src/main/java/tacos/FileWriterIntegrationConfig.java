package tacos;

import java.io.File;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

@Configuration
public class FileWriterIntegrationConfig {

  @Bean
  public IntegrationFlow fileWriterFlow() {
    return IntegrationFlow
        .from(MessageChannels.direct("textInChannel"))
        .<String, String>transform(String::toUpperCase)
        .handle(Files
            .outboundAdapter(new File("tmp/files"))
            .fileExistsMode(FileExistsMode.APPEND)
            .appendNewLine(true))
        .get();
  }

  @Bean
  public IntegrationFlow numberWriter() {
    return IntegrationFlow
        .from(MessageChannels.direct("numberChannel"))
        .<String, String>route(number ->
                Optional.ofNullable(number)
                    .filter(n -> Long.parseLong(n) % 2 == 0)
                    .map(n -> "EVEN")
                    .orElse("ODD"),
            mapping -> mapping.subFlowMapping("EVEN", sf -> sf
                    .handle(Files
                        .outboundAdapter(new File("tmp/files/even"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true)))
                .subFlowMapping("ODD", sf -> sf
                    .handle(Files
                        .outboundAdapter(new File("tmp/files/odd"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))))
        .get();
  }
}
